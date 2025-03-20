package com.zut.tlias.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//指定该注解在运行时生效
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
//指定该注解作用于方法
@Target(ElementType.METHOD)
public @interface MyLog {
}
