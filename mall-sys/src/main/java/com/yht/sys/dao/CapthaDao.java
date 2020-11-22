package com.yht.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yht.sys.DO.CaptchaDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CapthaDao extends BaseMapper<CaptchaDO> {
}
