package com.yht.mall.product.controller;

import com.yht.common.DO.GoodsDO;
import com.yht.common.utils.PageUtils;
import com.yht.common.utils.Result;
import com.yht.mall.product.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 商品，商品列表中类型名，修改前获取商品信息的类型名，通过多表联查获取
 * 添加商品直接返回的是类型id，修改确认返回的也是类型id
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

    /**
     *将图片保存在本地
     */
    @PostMapping("/fileUpload")
    public Result  fileUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()){
            return Result.error("图片为空");
        }else {
            //获取文件字节数组
            // 文件名
            String fileName = file.getOriginalFilename();
            // 后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 上传后的路径
            String filePath = "D://img//";
            // 新文件名
            fileName = UUID.randomUUID().toString().replace("-", "") + suffixName;
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Result.ok("图片上传成功");
        }
    }

    /**
     * 按照不同类别列出商品信息（前台系统）
     */
    @GetMapping("/select")
    public Result select(@RequestParam String classificationName){
        List<Map<String, Object>> list = goodsService.listByClass(classificationName);
        return Result.ok().put("list",list);
    }
    @GetMapping("/detail")
    public  Result detail(@RequestParam Long goodsId){
        Map<String, Object> map = goodsService.detail(goodsId);
        return Result.ok().put("map",map);
    }
}

