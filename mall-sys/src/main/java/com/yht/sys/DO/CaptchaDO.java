package com.yht.sys.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 验证码实体类
 * @author
 */
@Data
@TableName("sys_captcha")
public class CaptchaDO {
    private String uuid;
    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间
     */
    private Date expireTime;
}
