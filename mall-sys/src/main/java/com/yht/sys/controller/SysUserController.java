package com.yht.sys.controller;



import com.yht.common.DO.SysUserDO;
import com.yht.common.utils.JwtUtils;
import com.yht.common.utils.PageUtils;
import com.yht.common.utils.Result;
import com.yht.sys.annotation.MyLog;
import com.yht.sys.service.UserRoleService;
import com.yht.sys.service.SysUserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
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
public class SysUserController {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = sysUserService.queryPage(params);

        return Result.ok().put("page", page);
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public Result info(){
        SysUserDO user = sysUserService.selectByUserId(JwtUtils.getUserId(request.getHeader("token")));
        return Result.ok().put("user",user);
    }
    /**
     * 删除用户
     */
    @MyLog("删除管理用户")
    @PostMapping("/delete")
    public Result delete(@RequestBody List<Long> userIds){
        String token = request.getHeader("token");
        if(userIds.contains(1L)){
            return Result.error("系统管理员不能删除");
        }
        if(userIds.contains(JwtUtils.getUserId(token))){
            return Result.error("当前用户不能删除");
        }
        sysUserService.deleteByIds(userIds);
        return Result.ok();
    }

    /**
     * 修改前先获取用户信息
     */
    @GetMapping("/info/{userId}")
    public Result getUserInfo(@PathVariable("userId") Long userId){

        SysUserDO user = sysUserService.selectByUserId(userId);
        //获取用户对应的角色
        List<Long> roleIds = userRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIds);
        return Result.ok().put("user",user);
    }
    /**
     * 修改用户
     */
    @MyLog("修改管理用户信息")
    @PostMapping("/update")
    public Result update(HttpServletRequest request,@RequestBody SysUserDO user){
        String token = request.getHeader("token");
        long userId = JwtUtils.getUserId(token);
        //普通角色不能修改超级管理员信息
        if (userId!=1 && user.getUserId() == 1){
            return Result.error("不能修改超级管理员信息");
        }
        user.setCreateUserId(userId);
        sysUserService.update(user);
        return Result.ok();
    }
    /**
     * 保存用户
     */
    @MyLog("添加管理用户")
    @PostMapping("/save")
    public Result save(@RequestBody SysUserDO user){
        String token = request.getHeader("token");
        long userId = JwtUtils.getUserId(token);
        user.setCreateUserId(userId);
        sysUserService.saveUser(user);
        return Result.ok();
    }
    /**
     * 头部菜单，修改自己的密码
     */
    @MyLog("修改自己密码")
    @PostMapping("/password")
    public Result updatePassword(@RequestBody Map<String,String> map){
        String password = map.get("password");
        String newPassword = map.get("newPassword");
        if(newPassword == null || newPassword == ""){
            return Result.error("新密码不能为空");
        }
        SysUserDO user = sysUserService.selectByUserId(JwtUtils.getUserId(request.getHeader("token")));
        //sha256加密不可逆带密比较
        if (user.getPassword().equals(new Sha256Hash(password,user.getSalt()).toHex())){
            return Result.error("原密码不正确");
        }
        //sha256加密
        String newPassword2 = new Sha256Hash(newPassword, user.getSalt()).toHex();
        sysUserService.updatePassword(newPassword2,user.getUserId());
        return Result.ok();
    }
}
