package com.yht.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.sys.DO.RoleDO;

import java.util.List;

public interface RoleService extends IService<RoleDO> {
    /**
     * 查询看所有角色
     */
    List<RoleDO> listRole();

}
