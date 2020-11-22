package com.yht.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yht.sys.DO.SysUserDO;
import org.apache.ibatis.annotations.Mapper;

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

}
