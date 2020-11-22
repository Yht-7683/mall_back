package com.yht.sys.form;

import lombok.Data;

/**
 *后台管理系统登录表单
 * @author yht
 */
@Data
public class SysLoginForm {
    private String username;
    private String password;
    private String captcha;
    private String uuid;
}
