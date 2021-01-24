package com.yht.sys.controller;

import com.yht.common.utils.Result;
import com.yht.sys.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("mall/order/list")
    public Result queryPage(@RequestParam Map<String, Object> params){
        return orderService.queryPage(params);
    }
    @GetMapping("mall/order/product/list/{orderId}")
    public Result list(@PathVariable("orderId") Long orderId){
        return orderService.list(orderId);
    }
}
