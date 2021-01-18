package com.yht.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.sys.DO.UserRoleDO;

import java.util.List;

public interface UserRoleService extends IService<UserRoleDO> {
    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     *添加或者修改功能，先删除在添加
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);
    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
