package com.yht.sys.config;


import com.yht.common.DO.SysLogDO;
import com.yht.common.utils.JwtUtils;
import com.yht.sys.annotation.MyLog;
import com.yht.sys.service.SysLogService;
import com.yht.sys.service.SysUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 日志
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private SysUserService sysUserService;

    @AfterReturning(value = "@annotation(com.yht.sys.annotation.MyLog)")
    public void around(JoinPoint joinPoint){
        SysLogDO logDO = new SysLogDO();
        //获取用户名
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Long uid = JwtUtils.getUserId(request.getHeader("token"));
        String userName = sysUserService.selectByUserId(uid).getUsername();
        logDO.setUsername(userName);
        //获取操作
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);
        if(myLog != null){
            //注解上的描述
            logDO.setOperation(myLog.value());
        }
        //获取时间
        logDO.setCreateDate(new Date());
        sysLogService.save(logDO);
    }
}
