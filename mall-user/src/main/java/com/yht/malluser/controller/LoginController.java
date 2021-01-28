package com.yht.malluser.controller;

import com.yht.common.DO.UserDO;
import com.yht.common.utils.JwtUtils;
import com.yht.common.utils.Result;
import com.yht.malluser.form.LoginForm;
import com.yht.malluser.service.CaptchaService;
import com.yht.malluser.service.TokenService;
import com.yht.malluser.service.UserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * 商城前台登录页面相关,详情见sys模块的登录，原版为sys模块
 * @author yht
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CaptchaService captchaService;
    /**
     * 登录
     */
    @PostMapping("/user/login")
    public Result login(@RequestBody LoginForm form)throws IOException {
        boolean captcha = captchaService.validate(form.getUuid(), form.getCaptcha());
        if (!captcha) {
            return Result.error("验证码过不正确或已过期");
        }
        //获取用户信息
        UserDO user = userService.selectByUserName(form.getUsername());
        // 账号不存在、密码错误。密码由shiro的散列算法加密带密比较
        if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return Result.error("账号或密码不正确");
        }
        Result result = tokenService.createToken(user.getUserId());
        return result;
    }
    /**
     * 验证码
     */
    @GetMapping("/user/captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = captchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }


    /**
     * 退出
     */
    @PostMapping("/user/logout")
    public Result logout(HttpServletRequest request) {
        tokenService.logout(JwtUtils.getUserId(request.getHeader("token")));
        return Result.ok();
    }

}
