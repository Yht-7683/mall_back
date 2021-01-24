package com.yht.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yht.common.DO.UserRoleDO;
import com.yht.sys.dao.UserRoleDao;
import com.yht.sys.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleDO> implements UserRoleService {
    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return baseMapper.queryRoleIdList(userId);
    }
    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList){
        HashMap<String,Object> map = new HashMap<>();
        map.put("user_id", userId);
        //mybatisplus方法删除用户与角色关系
        this.removeByMap(map);
        if(roleIdList == null || roleIdList.size() == 0){
            return ;
        }
        //保存用户与角色关系
        for(Long roleId : roleIdList){
            UserRoleDO userRole = new UserRoleDO();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            this.save(userRole);
        }
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}
