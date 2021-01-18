package com.yht.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.sys.DO.MenuDO;

import java.util.List;
import java.util.Set;

/**
 *后台系统菜单的crud
 * @author yht
 */
public interface MenuService extends IService<MenuDO> {
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<MenuDO> queryListParentId(Long parentId, List<Long> menuIdList);

//    /**
//     * 根据父菜单，查询子菜单
//     */
//    List<MenuDO> queryListParentId(Long parentId);
//
//    /**
//     * 获取不包含按钮的菜单列表
//     */
//    List<MenuDO> queryNotButtonList();
//
    /**
     * 获取用户菜单列表
     */
    List<MenuDO> getUserMenuList(Long userId);
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    /**
     * 修改菜单名字
     */
    void update(MenuDO menuDO);
}
