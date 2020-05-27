package com.ruiqing.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * REDIS分布式锁工具类
 */
public class RedisLockUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisLockUtil.class);

    @SuppressWarnings("unchecked")
    private static RedisTemplate<String, Object> redisTemplate =
            SpringUtils.getBean("redisTemplate", RedisTemplate.class);

    private static long lockTimeout = 5 * 60 * 1000; //锁定时间

    /**
     * 加锁
     *
     * @param key
     * @param value 当前时间+ 超时时间
     * @return
     */
    public static boolean lock(String key, long currvalue) {
        String value = currvalue + lockTimeout + "";
        //未加锁，则加锁
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }

        //获取锁解锁时间
        String currentValue = (String) redisTemplate.opsForValue().get(key);
        //如果锁过期
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获得上一个锁的时间
            String olcValue = (String) redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(olcValue) && olcValue.equals(currentValue)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public static void unlock(String key, long currvalue) {
        try {
            String value = currvalue + lockTimeout + "";
            String currentVlue = (String) redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentVlue) && currentVlue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            logger.error("【redis分布式锁】 解锁异常" + e.getMessage());
        }
    }
}
