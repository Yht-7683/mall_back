package com.yht.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.sys.DO.SysUserDO;

import java.util.List;

/**
 * 后台管理系统的用户crud
 * @author yht
 */
public interface UserService extends IService<SysUserDO> {

    SysUserDO selectByUserName(String userName);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

}
