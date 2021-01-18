package com.yht.sys.service.impl;

import com.yht.sys.DO.CaptchaDO;
import com.yht.sys.service.CaptchaService;
import com.yht.sys.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.code.kaptcha.Producer;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * 计划验证码放入Redis中实现功能，暂时先放在mysql中后续在改
 * 完全转移至Redis后，将此类的继承删除
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

