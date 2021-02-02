package com.yht.mall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.common.DO.CartDO;
import com.yht.mall.order.dao.CartDao;
import com.yht.mall.order.service.CartService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CartServiceImpl extends ServiceImpl<CartDao,CartDO> implements CartService {
    @Override
    public List<CartDO> listCart(Long userId) {
        return baseMapper.listCart(userId);
    }

    @Override
    public void saveCart(CartDO cart) {
        HashMap<String,Object> map = new HashMap<>(2);
        map.put("sku_id",cart.getSkuId());
        map.put("user_id",cart.getUserId());
        List<CartDO> cartDO = baseMapper.selectByMap(map);
        //是否已经存在购物车，存在就只修改数量，否则新增一条
        if(cartDO.size() != 0){
            Long newAmont = cartDO.get(0).getAmount()+ cart.getAmount();
            cart.setAmount(newAmont);
            baseMapper.updateAmount(cart);
        }else {
            this.save(cart);
        }
    }
}
