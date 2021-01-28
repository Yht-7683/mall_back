package com.yht.malluser.service.impl;

import com.google.code.kaptcha.Producer;
import com.yht.common.DO.CaptchaDO;
import com.yht.malluser.service.CaptchaService;

import com.yht.malluser.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * 从sys模块复制过来，详细见sys模块
 * @author
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private Producer producer;
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public BufferedImage getCaptcha(String uuid){
        //生成文字验证码
        String code = producer.createText();
        CaptchaDO captcha = new CaptchaDO();
        captcha.setUuid(uuid);
        captcha.setCode(code);
        redisUtils.set(uuid,code,120L, TimeUnit.SECONDS);
        return producer.createImage(code);
    }
    @Override
    public boolean validate(String uuid, String code) {
        if (!redisUtils.exists(uuid)) {
            return false;
        }
        if (redisUtils.get(uuid).toString().equals(code)) {
            return true;
        }
        redisUtils.remove(uuid);
        return false;
    }
    }

