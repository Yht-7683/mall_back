package com.yht.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yht.common.DO.MenuDO;
import com.yht.sys.dao.MenuDao;
import com.yht.sys.dao.UserDao;
import com.yht.sys.service.MenuService;
import com.yht.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuDO> implements MenuService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private UserDao userDao;
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
        //默认超级管理员
        if (userId == 1){
            return getAllMenuList(null);
        }
        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
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
    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if(userId == 1){
            List<MenuDO> menuList = menuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(MenuDO menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = userDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
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
    @Override
    public void update(MenuDO menuDO){
        baseMapper.update(menuDO);
    }
}
