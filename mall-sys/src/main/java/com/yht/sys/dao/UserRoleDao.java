package com.yht.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yht.sys.DO.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleDao extends BaseMapper<UserRoleDO> {
    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);
    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
