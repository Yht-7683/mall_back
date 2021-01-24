package com.yht.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yht.common.DO.GoodsClassificationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类信息
 */
@Mapper
public interface GoodsClassificationDao extends BaseMapper<GoodsClassificationDO> {
}
