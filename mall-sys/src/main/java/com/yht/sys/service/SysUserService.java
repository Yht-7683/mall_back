package com.yht.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.common.DO.SysUserDO;
import com.yht.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 后台管理系统的用户crud
 * @author yht
 */
public interface SysUserService extends IService<SysUserDO> {
    /**
     * 按照用户名查找（登录操作）
     */
    SysUserDO selectByUserName(String userName);

    /**
     * 按照用户id查找
     */
    SysUserDO selectByUserId(Long id);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 查询所有用户
     */
    PageUtils queryPage(Map<String, Object> map);
    /**
     * 批量（单个）删除用户
     */
    void deleteByIds(List<Long> userId);
    /**
     * 修改用户
     */
    void update(SysUserDO user);

    /**
     * 保存用户
     */
    void saveUser(SysUserDO user);

    /**
     * 修改自己的密码
     */
    void updatePassword(String password, long id);
}
