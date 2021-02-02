package com.yht.mall.order.service;

import com.yht.common.DO.GoodsStockDO;
import com.yht.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


/**
 * 下单时查询和修改商品信息
 */
@FeignClient("mall-product")
@Service
public interface OrderGoodsStockService {
    /**
     *购买成功修改库存
     */
    @PostMapping("mall/stock/updateAmount")
    Result updateGoodsAmount(@RequestParam(value = "skuId",required = false) Long skuId,@RequestParam(value = "amount",required = false) Long amount);

    /**
     * 下单前查询库存
     */
    @PostMapping("mall/stock/getAmount")
    Long getGoodsAmount(@RequestParam(value = "skuId") Long skuId);
}
