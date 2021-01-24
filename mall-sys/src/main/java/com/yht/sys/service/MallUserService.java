package com.yht.sys.service;


import com.yht.common.DO.UserDO;
import com.yht.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商城用户的crud
 */
@Service
@FeignClient("mall-user")
public interface MallUserService {

    @GetMapping("mall/user/list")
    Result queryPage(@RequestParam Map<String, Object> map);
    @GetMapping("mall/user/info/{userId}")
    Result getUserInfo(@PathVariable("userId") Long userId);
    @PostMapping("mall/user/update")
    Result update(@RequestBody UserDO userDO);
    @PostMapping("mall/user/save")
    Result save(@RequestBody UserDO userDO);
    @PostMapping("mall/user/delete")
    Result delete(@RequestBody List<Long> userIds);




}
