package com.yht.sys.service;

import com.yht.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 订单管理
 */
@Service
@FeignClient("mall-order")
public interface OrderService {
    @GetMapping("/mall/order/list")
    Result queryPage(@RequestParam Map<String, Object> params);
    @GetMapping("mall/order/product/list/{orderId}")
    Result list(@PathVariable("orderId") Long orderId);
}
