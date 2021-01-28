package com.yht.malluser.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.yht.common.DO.SysUserDO;
import com.yht.common.DO.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao extends BaseMapper<UserDO> {
    /**
     * 获取用户用于登录验证
     */
    UserDO selectByUserName(String userName);
    /**
     * 修改自己的密码
     */
    void updatePassword(@Param("password") String password, @Param("id") long id);

}
