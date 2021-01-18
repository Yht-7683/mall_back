package com.yht.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.sys.DO.RoleMenuDO;
import com.yht.sys.dao.RoleMenuDao;
import com.yht.sys.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuDO> implements RoleMenuService {
    @Override
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
//先删除角色与菜单关系
        deleteBatch(new Long[]{roleId});

        if(menuIdList.size() == 0){
            return ;
        }

        //保存角色与菜单关系
        for(Long menuId : menuIdList){
            RoleMenuDO roleMenuDO = new RoleMenuDO();
            roleMenuDO.setMenuId(menuId);
            roleMenuDO.setRoleId(roleId);

            this.save(roleMenuDO);
        }
    }
    @Override
    public int deleteBatch(Long[] roleIds){
        return baseMapper.deleteBatch(roleIds);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }
}
