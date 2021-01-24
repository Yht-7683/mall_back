package com.yht.mall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.common.DO.OrderProductDO;
import com.yht.mall.order.dao.OrderProductDao;
import com.yht.mall.order.service.OrderProductService;
import org.springframework.stereotype.Service;

/**
 * 订单商品
 */
@Service
public class OrderProductSerivceImpl extends ServiceImpl<OrderProductDao, OrderProductDO> implements OrderProductService {


}
