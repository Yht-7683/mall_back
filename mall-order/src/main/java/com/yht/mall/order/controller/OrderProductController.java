package com.yht.mall.order.controller;

import com.yht.common.DO.OrderProductDO;
import com.yht.common.utils.Result;
import com.yht.mall.order.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("mall/order/product")
public class OrderProductController {
    @Autowired
    private OrderProductService orderProductService;
    @GetMapping("list/{orderId}")
    public Result list(@PathVariable("orderId") Long orderId){
        Map<String,Object> map = new HashMap<>(1);
        map.put("order_id",orderId);
        List<OrderProductDO> list = orderProductService.listByMap(map);
        return Result.ok().put("list",list);
    }
}
