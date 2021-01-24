package com.yht.sys.controller;


import com.yht.common.DO.UserDO;
import com.yht.common.utils.Result;
import com.yht.sys.annotation.MyLog;
import com.yht.sys.service.MallUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mall/user")
public class MallUserController {
    @Autowired
    private MallUserService mallUserService;
    /**
     * 所有商城用户列表
     */
    @GetMapping("list")
    public Result queryPage(@RequestParam Map<String, Object> map){
        return mallUserService.queryPage(map);
    }
    /**
     * 修改前先获取商城用户信息
     */
    @GetMapping("/info/{userId}")
    public Result getUserInfo(@PathVariable("userId") Long userId){
        return mallUserService.getUserInfo(userId);
    }

    /**
     * 修改商城用户信息
     */
    @MyLog("修改商城用户信息")
    @PostMapping("/update")
    public Result update(@RequestBody UserDO userDO){
        return mallUserService.update(userDO);
    }

    /**
     * 添加商城用户
     */
    @MyLog("添加商城用户")
    @PostMapping("/save")
    public Result save(@RequestBody UserDO user){
        return mallUserService.save(user);
    }
    /**
     * 删除商城用户
     */
    @MyLog("商城商城用户")
    @PostMapping("/delete")
    public Result delete(@RequestBody List<Long> userIds){
        return mallUserService.delete(userIds);
    }
}
