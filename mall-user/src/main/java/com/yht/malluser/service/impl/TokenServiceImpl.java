package com.yht.malluser.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.common.DO.MallUserTokenDO;
import com.yht.common.DO.SysUserTokenDO;
import com.yht.common.utils.JwtUtils;
import com.yht.common.utils.Result;

import com.yht.malluser.dao.TokenDao;
import com.yht.malluser.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl extends ServiceImpl<TokenDao, MallUserTokenDO> implements TokenService {
    @Autowired
    private TokenDao tokenDao;
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    /**
     * 生成token目前暂时放置于mysql中，后续将放置于redis中
     * @param userId
     * @return
     */
    @Override
    public Result createToken(long userId){
        //生成一个token
        String token = null;
        try {
            token = JwtUtils.createToken(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        MallUserTokenDO tokenDO = this.getById(userId);
        if(tokenDO == null){
            tokenDO = new MallUserTokenDO();
            tokenDO.setUserId(userId);
            tokenDO.setToken(token);
            tokenDO.setUpdateTime(now);
            tokenDO.setExpireTime(expireTime);

            //保存token
            this.save(tokenDO);
        }else{
            tokenDO.setToken(token);
            tokenDO.setUpdateTime(now);
            tokenDO.setExpireTime(expireTime);

            //更新token
            this.updateById(tokenDO);
        }

        Result r = Result.ok().put("token", token).put("expire", EXPIRE);

        return r;
    }
    @Override
    public void logout(long userId){
        //生成一个token
        String token = null;
        try {
            token = JwtUtils.createToken(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //修改token
        MallUserTokenDO tokenDO = new MallUserTokenDO();
        tokenDO.setUserId(userId);
        tokenDO.setToken(token);
        this.removeById(tokenDO);
    }
    @Override
    public MallUserTokenDO queryByToken(String token){
        return tokenDao.queryByToken(token);
    }
}
