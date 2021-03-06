package com.yht.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.yht.common.DO.SysUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<SysUserDO> {

    /**
     * 获取用户用于登录验证
     * @param userName
     * @return
     */
    SysUserDO selectByUserName(String userName);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 修改自己的密码
     */
    void updatePassword(@Param("password") String password, @Param("id") long id);


}
