package com.yht.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.sys.DO.RoleMenuDO;

import java.util.List;

/**
 * 角色与菜单的对应关系
 * @author yht
 */
public interface RoleMenuService extends IService<RoleMenuDO> {
    /**
     * 修改关系，先全删在添加
     */
    void saveOrUpdate(Long roleId, List<Long> menuIdList);
    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
    /**
     * 更加角色id查询对应菜单
     */
    List<Long> queryMenuIdList(Long roleId);

}
