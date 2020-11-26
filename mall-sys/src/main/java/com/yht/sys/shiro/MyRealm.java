package com.yht.sys.shiro;

import com.yht.sys.DO.SysUserDO;
import com.yht.sys.DO.SysUserTokenDO;
import com.yht.sys.dao.UserDao;
import com.yht.sys.service.TokenService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserDao userDao;
    /**
     * 授权(验证权限用)
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     *认证（登录是用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //token中取用户名
        String accessToken = (String) token.getPrincipal();
        //根据accessToken，查询用户信息
        SysUserTokenDO sysUserToken = tokenService.queryByToken(accessToken);
        //token失效
        if(sysUserToken == null ){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        SysUserDO user = userDao.selectById(sysUserToken.getUserId());
        if(user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
