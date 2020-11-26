package com.yht.sys.controller;


import com.yht.sys.DO.MenuDO;
import com.yht.sys.service.MenuService;
import com.yht.sys.utils.JwtUtils;
import com.yht.sys.utils.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 菜单功能
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuController{
    @Autowired
    private MenuService menuService;
    @Autowired
    private HttpServletRequest request;
    /**
     * 登录后获得所有菜单列表
     */
    @GetMapping("/nav")
    public Result nav(){
        String token = request.getHeader("token");
        List<MenuDO> menuList = menuService.getUserMenuList(JwtUtils.getUserId(token));
        Set<String> permissions = menuService.getUserPermissions(JwtUtils.getUserId(token));
        return Result.ok().put("menuList", menuList).put("permissions",permissions);
    }
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
