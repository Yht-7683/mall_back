package com.yht.mall.order.controller;

import com.yht.common.DO.OrderInfoDO;
import com.yht.common.utils.JwtUtils;
import com.yht.common.utils.PageUtils;
import com.yht.common.utils.Result;
import com.yht.mall.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    /**
     * 查询自己的订单
     */
    @GetMapping("/select")
    public Result select(HttpServletRequest request, @RequestParam Map<String, Object> params){
        Long userId = JwtUtils.getUserId(request.getHeader("token"));
        params.put("userId",userId);
        PageUtils page = orderInfoService.select(params);
        return Result.ok().put("page", page);
    }

    /**
     * 购物车下单生成订单操作
     */
    @PostMapping("/save")
    public Result save(HttpServletRequest request, @RequestBody Map<String, Object> map){
        Long userId = JwtUtils.getUserId(request.getHeader("token"));
        map.put("userId",userId);
        return orderInfoService.saveOrder(map);
    }
    /**
     * 商品界面购买生成订单
     */
    @PostMapping("/buy")
    public Result buy(HttpServletRequest request, @RequestBody Map<String, Object> map){
        Long userId = JwtUtils.getUserId(request.getHeader("token"));
        map.put("userId",userId);
        return orderInfoService.saveGoodsOrder(map);
    }
}
