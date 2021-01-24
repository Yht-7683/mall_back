package com.yht.sys.service;


import com.yht.common.DO.GoodsDO;
import com.yht.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 商城用户的crud
 */
@Service
@FeignClient("mall-product")
public interface MallGoodsService {
    @GetMapping("/mall/goods/list")
    Result queryPage(@RequestParam Map<String, Object> params);
    @GetMapping("mall/goods/info/{goodsId}")
    Result getGoodsInfo(@PathVariable("goodsId") Long goodsId);
    @PostMapping("mall/goods/update")
    Result update(@RequestBody GoodsDO goodsDO);
    @PostMapping("mall/goods/save")
    Result save(@RequestBody GoodsDO goodsDO);
    @PostMapping("mall/goods/delete")
    Result delete(@RequestBody List<Long> goodsIds);
    @GetMapping("mall/class/list")
    Result list();
    }
