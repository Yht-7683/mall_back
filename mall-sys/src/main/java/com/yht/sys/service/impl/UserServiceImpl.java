package com.yht.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.sys.DO.SysUserDO;
import com.yht.sys.dao.UserDao;
import com.yht.sys.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao,SysUserDO> implements UserService{
    @Override
    public SysUserDO selectByUserName(String userName){
        return baseMapper.selectByUserName(userName);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }


}
