package com.yht.mall.order.controller;

import com.yht.common.utils.PageUtils;
import com.yht.common.utils.Result;
import com.yht.mall.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 订单总体
 */
@RestController
@RequestMapping("mall/order")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;
    /**
     * 查询所有订单信息
     */
    @GetMapping("/list")
    public Result queryPage(@RequestParam Map<String, Object> params) {
        PageUtils page = orderInfoService.queryPage(params);
        return Result.ok().put("page", page);
    }
}
