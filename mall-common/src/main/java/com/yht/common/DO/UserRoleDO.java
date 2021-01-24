package com.yht.common.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 后台用户角色对应关系表
 */
@Data
@TableName("sys_user_role")
public class UserRoleDO {
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

}
