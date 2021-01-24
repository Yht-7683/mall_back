package com.yht.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yht.common.DO.GoodsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品信息
 */
@Mapper
public interface GoodsDao extends BaseMapper<GoodsDO> {
    IPage<GoodsDO> searchPage(IPage<GoodsDO> page, @Param("name") String name, @Param("classificationName") String classificationName);
}
