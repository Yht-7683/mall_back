package com.yht.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.common.DO.OrderInfoDO;
import com.yht.common.utils.PageUtils;

import java.util.Map;

/**
 * 订单信息
 */
public interface OrderInfoService extends IService<OrderInfoDO> {
    /**
     * 查询所有商品的库存
     */
    PageUtils queryPage(Map<String, Object> map);
}
