package com.yht.malluser.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.yht.common.DO.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao extends BaseMapper<UserDO> {
    /**
     * 修改自己的密码
     */
    void updatePassword(@Param("password") String password, @Param("id") long id);

}
