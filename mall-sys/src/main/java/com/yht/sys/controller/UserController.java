package com.yht.sys.controller;


import com.yht.sys.DO.SysUserDO;
import com.yht.sys.service.UserRoleService;
import com.yht.sys.service.UserService;
import com.yht.sys.utils.JwtUtils;
import com.yht.sys.utils.PageUtils;
import com.yht.sys.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return Result.ok().put("page", page);
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public Result info(){
        SysUserDO user = userService.selectByUserId(JwtUtils.getUserId(request.getHeader("token")));
        return Result.ok().put("user",user);
    }
    /**
     * 删除用户
     */
    @PostMapping("/delete")
    public Result delete(HttpServletRequest request, @RequestBody List<Long> userIds){
        String token = request.getHeader("token");
        if(userIds.contains(1L)){
            return Result.error("系统管理员不能删除");
        }
        if(userIds.contains(JwtUtils.getUserId(token))){
            return Result.error("当前用户不能删除");
        }
        userService.deleteByIds(userIds);
        return Result.ok();
    }

    /**
     * 修改前先获取用户信息
     */
    @GetMapping("/info/{userId}")
    public Result getUserInfo(@PathVariable("userId") Long userId){

        SysUserDO user = userService.selectByUserId(userId);
        //获取用户对应的角色
        List<Long> roleIds = userRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIds);
        return Result.ok().put("user",user);
    }
    /**
     * 修改用户
     */
    @PostMapping("/update")
    public Result update(HttpServletRequest request,@RequestBody SysUserDO user){
        String token = request.getHeader("token");
        long userId = JwtUtils.getUserId(token);
        //普通角色不能修改超级管理员信息
        if (userId!=1 && user.getUserId() == 1){
            return Result.error("不能修改超级管理员信息");
        }
        user.setCreateUserId(userId);
        userService.update(user);
        return Result.ok();
    }
    /**
     * 保存用户
     */
    @PostMapping("/save")
    public Result save(@RequestBody SysUserDO user){
        String token = request.getHeader("token");
        long userId = JwtUtils.getUserId(token);
        user.setCreateUserId(userId);
        userService.saveUser(user);
        return Result.ok();
    }
}
