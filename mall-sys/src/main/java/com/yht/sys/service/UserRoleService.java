package com.yht.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.sys.DO.UserRoleDO;

import java.util.List;

public interface UserRoleService extends IService<UserRoleDO> {
    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    void saveOrUpdate(Long userId, List<Long> roleIdList);
}
