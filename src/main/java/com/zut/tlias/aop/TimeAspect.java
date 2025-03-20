package com.zut.tlias.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;




@Slf4j
@Component
//此注解标识该类为aop类
@Aspect
public class TimeAspect {

    @Pointcut("execution(* com.zut.tlias.service.impl.DeptServiceImpl.*(..))")
    public void pt(){}

    //自定义切点
    //@Pointcut("@annotation(com.zut.tlias.aop.MyLog)")
    //切入点表达式
    @Around("pt()")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        //若方法有返回值，需要接受
        //调用原始方法
        Object obj =joinPoint.proceed();

        long end = System.currentTimeMillis();
        System.out.println("方法执行时间："+(end-start));
        return obj;
    }

}
