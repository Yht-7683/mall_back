package com.yht.common.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 系统日志
 *
 */
@Data
@TableName("sys_log")
public class SysLogDO {
    @TableId
    private Long id;
    //用户名
    private String username;
    //用户操作
    private String operation;
    //创建时间
    private Date createDate;
}
