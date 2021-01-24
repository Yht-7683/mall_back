package com.yht.sys.service;

import com.yht.common.DO.GoodsDO;
import com.yht.common.DO.GoodsStockDO;
import com.yht.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 商品信息crud
 */
@Service
@FeignClient("mall-product")
public interface MallStockService {
    @GetMapping("/mall/stock/list")
    Result queryPage(@RequestParam Map<String, Object> params);
    @GetMapping("mall/stock/info/{skuId}")
    Result getGoodsStockInfo(@PathVariable("skuId") Long skuId);
    @PostMapping("mall/stock/update")
    Result update(@RequestBody GoodsStockDO stock);
}
