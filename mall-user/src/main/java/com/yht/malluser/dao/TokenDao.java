package com.yht.malluser.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yht.common.DO.MallUserTokenDO;
import com.yht.common.DO.SysUserTokenDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenDao extends BaseMapper<MallUserTokenDO> {
    /**
     * 查询改用户是否存在token
     * @param token
     * @return
     */
    MallUserTokenDO queryByToken(String token);
}
