package com.yht.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.yht.common.DO.SysUserTokenDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenDao extends BaseMapper<SysUserTokenDO> {
    /**
     * 查询改用户是否存在token
     * @param token
     * @return
     */
    SysUserTokenDO queryByToken(String token);
}
