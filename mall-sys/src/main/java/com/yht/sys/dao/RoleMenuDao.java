package com.yht.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yht.sys.DO.RoleMenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMenuDao extends BaseMapper<RoleMenuDO> {
    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);
}
