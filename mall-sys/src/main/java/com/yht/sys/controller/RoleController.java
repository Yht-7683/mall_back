package com.yht.sys.controller;

import com.yht.sys.DO.RoleDO;
import com.yht.sys.service.RoleService;
import com.yht.sys.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/role")
public class RoleController {
    @Autowired
    private RoleService RoleService;
    @GetMapping("/select")
    public Result select(){
        List<RoleDO> list =  RoleService.listRole();
        return Result.ok().put("list", list);
    }
}
