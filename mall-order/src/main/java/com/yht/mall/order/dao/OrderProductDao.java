package com.yht.mall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yht.common.DO.OrderProductDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单商品
 */
@Mapper
public interface OrderProductDao extends BaseMapper<OrderProductDO> {
}
