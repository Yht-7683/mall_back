package com.yht.malluser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.common.DO.SysUserDO;
import com.yht.common.DO.UserDO;
import com.yht.common.utils.PageUtils;


import java.util.List;
import java.util.Map;
/**
 * 商城用户管理crud
 * @author yht
 */
public interface UserService extends IService<UserDO> {
    /**
     * 按照用户名查找（登录操作）
     */
    UserDO selectByUserName(String userName);
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
    void update(UserDO user);

    /**
     * 保存用户
     */
    void saveUser(UserDO user);

    /**
     * 修改自己的密码
     */
    void updatePassword(String password, long id);

    /**
     * 按照用户id查找
     */
    UserDO selectByUserId(Long id);

}
