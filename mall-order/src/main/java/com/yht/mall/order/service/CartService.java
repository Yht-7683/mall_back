package com.yht.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.common.DO.CartDO;

import java.util.List;

/**
 * 购物车
 */
public interface CartService extends IService<CartDO> {
    /**
     * 获取列表
     */
    List<CartDO> listCart(Long userId);
    /**
     * 加入购物车
     */
    void saveCart(CartDO cart);
}
