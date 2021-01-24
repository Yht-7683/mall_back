package com.yht.sys.controller;

import com.yht.common.DO.GoodsStockDO;
import com.yht.common.utils.Result;
import com.yht.sys.annotation.MyLog;
import com.yht.sys.service.MallStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MallStockController {
    @Autowired
    private MallStockService mallStockService;
    /**
     * 所有商品库存列表
     */
    @GetMapping("/mall/stock/list")
    public Result queryPage(@RequestParam Map<String, Object> map){
        return mallStockService.queryPage(map);
    }
    /**
     * 修改前先获取商品库存信息
     */
    @GetMapping("/mall/stock/info/{skuId}")
    public Result getGoodsStockInfo(@PathVariable("skuId") Long skuId){
        return mallStockService.getGoodsStockInfo(skuId);
    }
    /**
     * 修改商品库存信息
     */
    @MyLog("修改商品库存信息")
    @PostMapping("/mall/stock/update")
    public Result update(@RequestBody GoodsStockDO stock){
        return mallStockService.update(stock);
    }


}
