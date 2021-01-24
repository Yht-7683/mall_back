package com.yht.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.common.DO.RoleDO;
import com.yht.common.utils.PageUtils;


import java.util.List;
import java.util.Map;

public interface RoleService extends IService<RoleDO> {
    /**
     * 查询看所有角色
     */
    List<RoleDO> listRole();
    /**
     * 添加角色
     */
    void saveRole(RoleDO roleDO);
    /**
     * 查询看所有角色
     */
    PageUtils queryPage(Map<String, Object> map);

    /**
     *修改角色
     */
    void update(RoleDO role);

    /**
     * 删除角色
     */
    void deleteBatch(Long[] roleIds);


}
