package com.yht.mall.product.controller;

import com.yht.common.DO.GoodsClassificationDO;
import com.yht.common.utils.Result;
import com.yht.mall.product.service.GoodsClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 种类
 */
@RestController
@RequestMapping("/mall/class")
public class GoodsClassificationController {
    @Autowired
    private GoodsClassificationService goodsClassificationService;
    @GetMapping("/list")
    public Result list(){
        List<GoodsClassificationDO> list = goodsClassificationService.list();
        return Result.ok().put("list",list);
    }
}
