package com.yht.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.common.DO.SysUserTokenDO;
import com.yht.common.utils.Result;


public interface TokenService extends IService<SysUserTokenDO> {
    /**
     * 生成token
     * @param userId
     * @return
     */
    Result createToken(long userId);
    /**
     * 退出，修改token值
     * @param userId  用户ID
     */
    void logout(long userId);


    SysUserTokenDO queryByToken(String token);
}
