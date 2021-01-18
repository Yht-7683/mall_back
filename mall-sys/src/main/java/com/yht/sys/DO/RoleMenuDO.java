package com.yht.sys.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色对应的菜单实体类
 * @author yht
 */
@TableName("sys_role_menu")
@Data
public class RoleMenuDO {
    @TableId
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;
}
