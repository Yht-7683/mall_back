package com.yht.mall.product.controller;


import com.yht.common.DO.GoodsStockDO;
import com.yht.common.utils.PageUtils;
import com.yht.common.utils.Result;
import com.yht.mall.product.service.GoodsStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 库存管理，只能查和修改，不可直接删除和添加
 * 新增商品后自动生成一个价格数量为0下架的库存状态
 * 删除该商品后库存对应自动删除
 */
@RestController
@RequestMapping("mall/stock")
public class GoodsStockController {
    @Autowired
    private GoodsStockService goodsStockService;
    /**
     * 所有商品的库存
     */
    @GetMapping("/list")
    public Result queryPage(@RequestParam Map<String, Object> params) {
        PageUtils page = goodsStockService.queryPage(params);
        return Result.ok().put("page", page);
    }
    /**
     * 修改前先获取商品库存信息
     */
    @GetMapping("/info/{skuId}")
    public Result getGoodsStockInfo(@PathVariable("skuId") Long skuId) {
        GoodsStockDO stock = goodsStockService.selectByskuId(skuId);
        return Result.ok().put("stock", stock);
    }
    /**
     * 修改商品信息
     */
    @PostMapping("/update")
    public Result update(@RequestBody GoodsStockDO stock ){
        goodsStockService.update(stock);
        return Result.ok();
    }
    /**
     * 下单前查询库存量
     */
    @PostMapping("/getAmount")
    public Long getGoods(@RequestParam Long skuId){
        return goodsStockService.getGoodsAmount(skuId);
    }
    /**
     * 购买成功修改库存数量
     */
    @PostMapping("/updateAmount")
    public Result update(@RequestParam("skuId") Long skuId,@RequestParam("amount") Long amount){
        goodsStockService.updataGoodsAmount(skuId,amount);
        return Result.ok();
    }
}
