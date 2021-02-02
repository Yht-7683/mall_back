package com.yht.mall.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.common.DO.CartDO;
import com.yht.common.DO.OrderInfoDO;
import com.yht.common.DO.OrderProductDO;
import com.yht.common.utils.PageUtils;
import com.yht.common.utils.Result;
import com.yht.mall.order.dao.OrderInfoDao;
import com.yht.mall.order.service.CartService;
import com.yht.mall.order.service.OrderGoodsStockService;
import com.yht.mall.order.service.OrderInfoService;
import com.yht.mall.order.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoDao, OrderInfoDO> implements OrderInfoService {
    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderGoodsStockService orderGoodsStockService;

    @Override
    public PageUtils queryPage(Map<String, Object> map) {
        int pageSize = Integer.parseInt(String.valueOf(map.get("limit")));
        int currPage =  Integer.parseInt(String.valueOf( map.get("page")));
        String name = (String)map.get("username");
        IPage<OrderInfoDO> page = baseMapper.searchPage(new Page<>(currPage,pageSize),name);
        return new PageUtils(page);
    }

    @Override
    public PageUtils select(Map<String, Object> map) {
        int pageSize = Integer.parseInt(String.valueOf(map.get("limit")));
        int currPage =  Integer.parseInt(String.valueOf( map.get("page")));
        Long uerId = Long.parseLong(String.valueOf(map.get("userId")));
        IPage<OrderInfoDO> page = baseMapper.searchUserPage(new Page<>(currPage,pageSize),uerId);
        return new PageUtils(page);
    }

    @Override
    public Result saveOrder(Map<String,Object> map){

        OrderInfoDO order = new OrderInfoDO();
        order.setUserId(Long.valueOf(map.get("userId").toString()));
        String orderId = UUID.randomUUID().toString().replace("-", "");
        order.setOrderId(orderId);
        order.setCreateTime(new Date());
        order.setFreight(BigDecimal.valueOf(Double.valueOf(map.get("freight").toString())));
        order.setPay(Integer.valueOf(map.get("pay").toString()));
        order.setStatus(Integer.valueOf(map.get("status").toString()));
        order.setTotalPrice(BigDecimal.valueOf(Double.valueOf(map.get("totalPrice").toString())));
        //map转为list，直接强转出现异常.LinkedHashMap cannot be cast to
        List<CartDO> cartList =JSONObject.parseArray(JSON.toJSONString((map.get("orderProductList"))),CartDO.class);
        List<OrderProductDO> orderProductList = new ArrayList<>();
        for (CartDO c: cartList){
            OrderProductDO o = new OrderProductDO();
            o.setOrderId(orderId);
            o.setSkuId(c.getSkuId());
            o.setGoodsName(c.getName());
            o.setGoodsAmount(c.getAmount());
            o.setGoodsPrice(c.getPrice());
            orderProductList.add(o);
        }
        //查询库存是否足够
        for (OrderProductDO orderProductDO : orderProductList){
            Long goodsAmount = orderGoodsStockService.getGoodsAmount(orderProductDO.getSkuId());
            if(orderProductDO.getGoodsAmount()>goodsAmount){
                return Result.error("商品"+orderProductDO.getGoodsName()+"的库存不足");
            }
        }

        //保存订单总体
        this.save(order);
        //保存订单详细
        orderProductService.saveBatch(orderProductList);
        //清除购物车
        cartService.removeByIds((List<Long>)map.get("cartIds"));
        //修改库存
        for (OrderProductDO orderProductDO : orderProductList){
            Long stockAmount = orderGoodsStockService.getGoodsAmount(orderProductDO.getSkuId());
            Long newAmount = stockAmount - orderProductDO.getGoodsAmount();
            orderGoodsStockService.updateGoodsAmount(orderProductDO.getSkuId(),newAmount);
        }
        return Result.ok("购买成功");
    }

    @Override
    public Result saveGoodsOrder(Map<String, Object> map) {
        OrderInfoDO order = new OrderInfoDO();
        order.setUserId(Long.valueOf(map.get("userId").toString()));
        String orderId = UUID.randomUUID().toString().replace("-", "");
        order.setOrderId(orderId);
        order.setCreateTime(new Date());
        order.setFreight(BigDecimal.valueOf(Double.valueOf(map.get("freight").toString())));
        order.setPay(Integer.valueOf(map.get("pay").toString()));
        order.setStatus(Integer.valueOf(map.get("status").toString()));
        order.setTotalPrice(BigDecimal.valueOf(Double.valueOf(map.get("totalPrice").toString())));
        //直接强转出现异常：.LinkedHashMap cannot be cast to
        OrderProductDO orderProduct = JSON.parseObject(JSON.toJSONString(map.get("orderProduct")),OrderProductDO.class);
       orderProduct.setOrderId(orderId);

       //查询库存是否足够
        Long goodsAmount = orderGoodsStockService.getGoodsAmount(orderProduct.getSkuId());
        if(orderProduct.getGoodsAmount()>goodsAmount){
            return Result.error("商品"+orderProduct.getGoodsName()+"的库存不足");
        }

        //保存订单总体
        this.save(order);
        //保存订单对应的商品
        orderProductService.save(orderProduct);

        //修改库存
        Long newAmount = goodsAmount - orderProduct.getGoodsAmount();
        orderGoodsStockService.updateGoodsAmount(orderProduct.getSkuId(),newAmount);

        return Result.ok("购买成功");
    }
}
