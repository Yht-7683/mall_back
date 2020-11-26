package com.yht.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.sys.DO.RoleDO;
import com.yht.sys.dao.RoleDao;
import com.yht.sys.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleDO> implements RoleService {
    @Override
    public List<RoleDO> listRole(){
        return this.list();
    }
}
