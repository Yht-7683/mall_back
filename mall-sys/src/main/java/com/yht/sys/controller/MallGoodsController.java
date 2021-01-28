package com.yht.sys.controller;


import com.yht.common.DO.GoodsDO;
import com.yht.common.utils.Result;
import com.yht.sys.annotation.MyLog;
import com.yht.sys.service.MallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class MallGoodsController {
    @Autowired
    private MallGoodsService mallGoodsService;
    /**
     * 所有商品列表
     */
    @GetMapping("/mall/goods/list")
    public Result queryPage(@RequestParam Map<String, Object> map){
        return mallGoodsService.queryPage(map);
    }
    /**
     * 修改前先获取商品信息
     */
    @GetMapping("/mall/goods//info/{goodsId}")
    public Result getGoodsInfo(@PathVariable("goodsId") Long goodsId){
        return mallGoodsService.getGoodsInfo(goodsId);
    }

    /**
     * 修改商品信息
     */
    @MyLog("修改商品信息")
    @PostMapping("/mall/goods/update")
    public Result update(@RequestBody GoodsDO goodsDO){
        return mallGoodsService.update(goodsDO);
    }

    /**
     * 添加商品
     */
    @MyLog("添加商品")
    @PostMapping("/mall/goods/save")
    public Result save(@RequestBody GoodsDO goodsDO){
        return mallGoodsService.save(goodsDO);
    }
    /**
     * 删除商品
     */
    @MyLog("删除商品")
    @PostMapping("/mall/goods/delete")
    public Result delete(@RequestBody List<Long> goodsIds){
        return mallGoodsService.delete(goodsIds);
    }
    /**
     * 获取商品类别
     */
    @GetMapping("mall/class/list")
    public Result list(){
        return mallGoodsService.list();
    }
    /**
     *将图片保存在本地
     */
    @PostMapping("mall/goods/fileUpload")
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
//            String filePath = "D://img//";
            String filePath = "C://apache-tomcat-8.5.57//webapps//img//";
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
            String img = "http://121.41.91.101:2333/img/"+fileName;
            return Result.ok().put("img",img);
        }
    }

    }

