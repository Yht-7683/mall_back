package com.yht.malluser.controller;

import com.yht.common.DO.GoodsDO;
import com.yht.common.utils.PageUtils;
import com.yht.common.utils.Result;
import com.yht.malluser.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商品
 */
@RestController
@RequestMapping("/mall/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    /**
     * 所有商品列表
     */
    @GetMapping("/list")
    public Result queryPage(@RequestParam Map<String, Object> params) {
        PageUtils page = goodsService.queryPage(params);

        return Result.ok().put("page", page);
    }
    /**
     * 删除商品
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody List<Long> goodsIds){
        goodsService.deleteByIds(goodsIds);
        return Result.ok();
    }
    /**
     * 添加商品
     */
    @PostMapping("/save")
    public Result save(@RequestBody GoodsDO goods){
        goodsService.saveGoods(goods);
        return Result.ok();
    }
    /**
     * 修改前先获取商品信息
     */
    @GetMapping("/info/{goodsId}")
    public Result getGoodsInfo(@PathVariable("goodsId") Long goodsId) {
        GoodsDO goods = goodsService.selectByGoodsId(goodsId);
        return Result.ok().put("goods", goods);
    }
    /**
     * 修改商品信息
     */
    @PostMapping("/update")
    public Result update(@RequestBody GoodsDO goods ){
        goodsService.update(goods);
        return Result.ok();
    }

}
