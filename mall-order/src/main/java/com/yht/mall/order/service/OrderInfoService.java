package com.yht.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.common.DO.OrderInfoDO;
import com.yht.common.utils.PageUtils;
import com.yht.common.utils.Result;

import java.util.List;
import java.util.Map;

/**
 * 订单信息
 */
public interface OrderInfoService extends IService<OrderInfoDO> {
    /**
     * 查询所有订单信息
     */
    PageUtils queryPage(Map<String, Object> map);
    /**
     * 查询当前用户订单
     */
    PageUtils select(Map<String, Object> map);
    /**
     * 购物车中下单生成订单
     */
    Result saveOrder(Map<String,Object> map);
    /**
     * 商品页面直接购买生成订单
     */
    Result saveGoodsOrder(Map<String,Object> map);

}
