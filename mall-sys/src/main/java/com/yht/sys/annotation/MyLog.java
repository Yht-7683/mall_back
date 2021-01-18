package com.yht.sys.annotation;


import java.lang.annotation.*;

/**
 * 操作日志添加，aop实现
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface MyLog {

    String value() default "";
}
