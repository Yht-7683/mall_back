package com.yht.common.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 商城用户
 */
@Data
@TableName("mall_user")
public class UserDO {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message="用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message="密码不能为空")
    private String password;

    /**
     * 盐
     */
    private String salt;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 创建时间
     */
    private Date createTime;
}
