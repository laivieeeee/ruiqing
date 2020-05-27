package com.ruiqing.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/5/27 14:24
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerTest {
    /**
     * 实际消耗 >= 60 即输出耗时日志
     * @return
     */
    long value() default 60;

    /**
     * 单位
     * @return
     */
    TimeUnit unit() default TimeUnit.SECONDS;
}
