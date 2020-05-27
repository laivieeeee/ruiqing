package com.ruiqing.aspect;

import com.ruiqing.annotation.LoggerTest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/5/27 14:26
 */

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
    @Pointcut("@annotation(com.ruiqing.annotation.LoggerTest)")
    private void loggetTestPoint() {
    }
    @Around("loggetTestPoint()  && @annotation(loggerTest)")
    private Object around(ProceedingJoinPoint proceedingJoinPoint, LoggerTest loggerTest) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        LoggerTest annotation = method.getAnnotation(LoggerTest.class);
        logger.info("annotation:{}", annotation.annotationType());
        // =====执行方法开始=====
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        // =====执行方法结束=====
        long expect = loggerTest.value();
        TimeUnit unit = loggerTest.unit();
        long actual = unit.convert(end - start, TimeUnit.MILLISECONDS);
        if (actual > expect) {
            logger.info("{}执行超时,实际时间:{},预期时间:{},单位:{}", method.toGenericString(), actual, expect, unit.name());
        }
        // 返回结果
        return result;
    }

    /**
     * 拦截所有包含注解(此注解内置使用@RequestMapping):PostMapping GetMapping DelelteMapping等
     */
    @Pointcut("execution(@(@org.springframework.web.bind.annotation.RequestMapping *) * *(..)) || execution(@org.springframework.web.bind.annotation.RequestMapping * *(..))")
    private void controllerMapping() {

    }
    @Around("controllerMapping()")
    public Object validate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 开始执行方法
        Object result = proceedingJoinPoint.proceed();

        return result;
    }
}
