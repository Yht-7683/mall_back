package com.yht.malluser.controller;

import com.yht.common.DO.SysUserDO;
import com.yht.common.DO.UserDO;
import com.yht.common.utils.JwtUtils;
import com.yht.common.utils.PageUtils;
import com.yht.common.utils.Result;
import com.yht.malluser.service.UserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
/**
 * 商城用户
 *
 */
@RestController
@RequestMapping("mall/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = userService.queryPage(params);

        return Result.ok().put("page", page);
    }

    /**
     * 修改前先获取用户信息
     */
    @GetMapping("/info/{userId}")
    public Result getUserInfo(@PathVariable("userId") Long userId) {
        UserDO userDO = userService.selectByUserId(userId);
        return Result.ok().put("user", userDO);
    }
    /**
     * 修改商城用户信息
     */
    @PostMapping("/update")
    public Result update(@RequestBody UserDO user){
        userService.update(user);
        return Result.ok();
    }
    /**
     * 保存商城用户
     */
    @PostMapping("/save")
    public Result save(@RequestBody UserDO user){
        userService.saveUser(user);
        return Result.ok();
    }
    /**
     * 删除用户
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody List<Long> userIds){
        if(userIds.contains(1L)){
            return Result.error("root用户不能删除");
        }
        userService.deleteByIds(userIds);
        return Result.ok();
    }
    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public Result info(HttpServletRequest request){
        UserDO user = userService.selectByUserId(JwtUtils.getUserId(request.getHeader("token")));
        return Result.ok().put("user",user);
    }

    /**
     * 修改自己密码
     */
    @PostMapping("/password")
    public Result updatePassword(HttpServletRequest request,@RequestBody Map<String,String> map){
        String password = map.get("password");
        String newPassword = map.get("newPassword");
        if(newPassword == null || newPassword == ""){
            return Result.error("新密码不能为空");
        }
        UserDO user = userService.selectByUserId(JwtUtils.getUserId(request.getHeader("token")));
        //sha256加密不可逆带密比较
        if (!user.getPassword().equals(new Sha256Hash(password,user.getSalt()).toHex())){
            return Result.error("原密码不正确");
        }
        //sha256加密
        String newPassword2 = new Sha256Hash(newPassword, user.getSalt()).toHex();
        userService.updatePassword(newPassword2,user.getUserId());
        return Result.ok();
    }
}
