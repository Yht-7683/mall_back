package com.yht.sys.controller;

import com.yht.common.utils.PageUtils;
import com.yht.common.utils.Result;
import com.yht.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 日志
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 查询所有日志
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = sysLogService.queryPage(params);
        return Result.ok().put("page",page);
    }

}
