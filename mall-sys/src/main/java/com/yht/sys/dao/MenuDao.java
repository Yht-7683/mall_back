package com.yht.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.yht.common.DO.MenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDao extends BaseMapper<MenuDO> {
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<MenuDO> queryListParentId(Long parentId);

    /**
     * 修改菜单名字
     */
    void update(MenuDO menuDO);

}
