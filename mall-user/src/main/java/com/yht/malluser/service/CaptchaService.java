package com.yht.malluser.service;

import java.awt.image.BufferedImage;

/**
 * 验证码,从sys模块复制过来，详细见sys模块
 * @author yht
 */
public interface CaptchaService {
    /**
     * 获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     * @param uuid  uuid
     * @param code  验证码
     * @return  true：成功  false：失败
     */
    boolean validate(String uuid, String code);

}
