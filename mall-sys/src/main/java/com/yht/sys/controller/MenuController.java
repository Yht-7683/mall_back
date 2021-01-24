package com.yht.sys.controller;


import com.yht.common.DO.MenuDO;
import com.yht.common.utils.JwtUtils;
import com.yht.common.utils.Result;
import com.yht.sys.service.MenuService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
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

    /**
     *修改菜单名字
     */
    @PostMapping("update")
    public Result update(@RequestBody MenuDO menuDO){
        menuService.update(menuDO);
        return Result.ok();
    }
}
