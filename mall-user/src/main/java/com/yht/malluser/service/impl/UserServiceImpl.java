package com.yht.malluser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.common.DO.UserDO;
import com.yht.common.utils.PageUtils;
import com.yht.malluser.dao.UserDao;
import com.yht.malluser.service.UserService;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserDO> implements UserService {
    @Override
    public UserDO selectByUserName(String userName) {
        return baseMapper.selectByUserName(userName);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> map) {
        int pageSize = Integer.parseInt(String.valueOf(map.get("limit")));
        int currPage =  Integer.parseInt(String.valueOf( map.get("page")));
        String username = (String)map.get("username");
        IPage<UserDO> page = baseMapper.selectPage(new Page<>(currPage,pageSize),
                new QueryWrapper<UserDO>().like(StringUtils.isNotBlank(username),"username", username));
        return new PageUtils(page);
    }

    @Override
    public void deleteByIds(List<Long> userId){
        this.removeByIds(userId);
    }

    @Override
    public void update(UserDO user) {
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(null);
        }else{
            //sha256加密
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        this.updateById(user);
    }

    @Override
    public void saveUser(UserDO user) {
        user.setCreateTime(new Date());
        //shiro框架下的sha256加密，随机数用做秘钥
        String salt = RandomStringUtils.randomAlphanumeric(15);
        //用秘钥与密码进行加密
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        this.save(user);
    }

    @Override
    public void updatePassword(String password, long id) {
        baseMapper.updatePassword(password,id);
    }

    @Override
    public UserDO selectByUserId(Long id) {
        return baseMapper.selectById(id);
    }
}
