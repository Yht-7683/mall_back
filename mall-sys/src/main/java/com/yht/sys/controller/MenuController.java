package com.yht.sys.controller;


import com.yht.sys.DO.MenuDO;
import com.yht.sys.service.MenuService;
import com.yht.sys.utils.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 菜单功能
 */
@RestController
public class MenuController{
    @Autowired
    private MenuService menuService;
    /**
     * 登录后获得所有菜单列表
     */
//    @GetMapping("/nav")
//    public Result nav(){
//        List<MenuDO> menuList = menuService.getUserMenuList(getUser().getUserId());
//        Set<String> permissions = shiroService.getUserPermissions(getUserId());
//        return Result.ok().put("menuList", menuList);
//    }
    /**
     * 显示树型菜单
     */

    @GetMapping("/list")
//    @RequiresPermissions("sys:menu:list")
    public List<MenuDO> list(){
        List<MenuDO> menuList = menuService.list();
        for(MenuDO sysMenuEntity : menuList){
            MenuDO parentMenuEntity = menuService.getById(sysMenuEntity.getParentId());
            if(parentMenuEntity != null){
                sysMenuEntity.setParentName(parentMenuEntity.getName());
            }
        }

        return menuList;
    }
}
