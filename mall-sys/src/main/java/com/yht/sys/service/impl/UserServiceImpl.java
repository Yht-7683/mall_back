package com.yht.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.sys.DO.SysUserDO;
import com.yht.sys.dao.UserDao;
import com.yht.sys.service.UserRoleService;
import com.yht.sys.service.UserService;
import com.yht.sys.utils.PageUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao,SysUserDO> implements UserService{
    @Autowired
    private UserRoleService userRoleService;
    @Override
    public SysUserDO selectByUserName(String userName){
        return baseMapper.selectByUserName(userName);
    }
    @Override
    public SysUserDO selectByUserId(Long id){
        return baseMapper.selectById(id);
    }
    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }
    @Override
    public PageUtils queryPage(Map<String, Object> map) {
        int pageSize = Integer.parseInt(String.valueOf(map.get("limit")));
        int currPage =  Integer.parseInt(String.valueOf( map.get("page")));
        IPage<SysUserDO> page = this.page(new Page<>(currPage, pageSize), null);

        return new PageUtils(page);
    }
    @Override
    public void deleteByIds(List<Long> userId){
        this.removeByIds(userId);
    }
    @Override
    public void update(SysUserDO user){
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(null);
        }else{
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        this.updateById(user);
        userRoleService.update();
        userRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());

    }
    @Override
    public void saveUser(SysUserDO user){
        user.setCreateTime(new Date());
        //shiro框架下的sha256加密，随机数用做秘钥
        String salt = RandomStringUtils.randomAlphanumeric(15);
        //用秘钥与密码进行加密
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        this.save(user);
        //保存用户与角色关系
        userRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }
}
