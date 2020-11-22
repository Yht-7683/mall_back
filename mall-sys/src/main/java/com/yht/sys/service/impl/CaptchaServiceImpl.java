package com.yht.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.sys.DO.CaptchaDO;
import com.yht.sys.dao.CapthaDao;
import com.yht.sys.service.CaptchaService;
import com.yht.sys.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.code.kaptcha.Producer;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 计划验证码放入Redis中实现功能，暂时先放在mysql中后续在改
 * 完全转移至Redis后，将此类的继承删除
 * @author
 */
@Service
public class CaptchaServiceImpl extends ServiceImpl<CapthaDao, CaptchaDO> implements CaptchaService {

    @Autowired
    private Producer producer;
    @Autowired
    private RedisUtils redisUtils;

    public BufferedImage getCaptcha(String uuid){
        //生成文字验证码
        String code = producer.createText();
        CaptchaDO captcha = new CaptchaDO();
        captcha.setUuid(uuid);
        captcha.setCode(code);
        captcha.setExpireTime(new Date());
        this.save(captcha);
        redisUtils.set(uuid,code,120L, TimeUnit.SECONDS);
        return producer.createImage(code);

    }
    public boolean validate(String uuid, String code) {
//        CaptchaDO captcha = this.getOne(new QueryWrapper<CaptchaDO>().eq("uuid", uuid));
        if (!redisUtils.exists(uuid)) {
            return false;
        }
//        if(captcha == null){
//            return false;
//        }
//        if(captcha.getCode().equalsIgnoreCase(code)){
//            return true;
//        }
//        //删除验证码
//        this.removeById(uuid);
        if (redisUtils.get(uuid).toString().equals(code)) {
            return true;
        }
        redisUtils.remove(uuid);
        return false;
    }
    }

