package com.yht.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.sys.DO.MenuDO;
import com.yht.sys.dao.MenuDao;
import com.yht.sys.service.MenuService;
import com.yht.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuDO> implements MenuService {
    @Autowired
    private UserService userService;

    @Override
    public List<MenuDO> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<MenuDO> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<MenuDO> userMenuList = new ArrayList<>();
        for(MenuDO menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }
    public List<MenuDO> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }
    @Override
    public List<MenuDO> getUserMenuList(Long userId){
        //用户菜单列表
        List<Long> menuIdList = userService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }
    /**
     * 获取所有菜单列表
     */
    private List<MenuDO> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<MenuDO> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<MenuDO> getMenuTreeList(List<MenuDO> menuList, List<Long> menuIdList){
        List<MenuDO> subMenuList = new ArrayList<>();

        for(MenuDO entity : menuList){
            //最高级的目录
            if(entity.getType() == 0){
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}