package com.yht.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.sys.DO.RoleDO;
import com.yht.sys.dao.RoleDao;
import com.yht.sys.service.RoleMenuService;
import com.yht.sys.service.RoleService;
import com.yht.sys.service.UserRoleService;
import com.yht.sys.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleDO> implements RoleService {
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private UserRoleService userRoleService;
    @Override
    public List<RoleDO> listRole(){
        return this.list();
    }

    @Override
    public void saveRole(RoleDO role){
        role.setCreateTime(new Date());
        this.save(role);
        //保存角色与菜单关系
        roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }
    @Override
    public PageUtils queryPage(Map<String, Object> map){
        int pageSize = Integer.parseInt(String.valueOf(map.get("limit")));
        int currPage =  Integer.parseInt(String.valueOf( map.get("page")));
        String roleName = (String)map.get("roleName");
        IPage<RoleDO>  page = baseMapper.selectPage(new Page<>(currPage, pageSize),
                new QueryWrapper<RoleDO>().like(StringUtils.isNotBlank(roleName),"role_name",roleName));
        return new PageUtils(page);
    }

    @Override
    public void update(RoleDO role) {

        this.updateById(role);
        //更新角色与菜单关系
        roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.removeByIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        roleMenuService.deleteBatch(roleIds);
        //删除角色与用户关联
        userRoleService.deleteBatch(roleIds);
    }

}
