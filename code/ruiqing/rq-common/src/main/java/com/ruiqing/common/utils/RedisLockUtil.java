package com.ruiqing.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * REDIS分布式锁工具类
 */
public class RedisLockUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisLockUtil.class);

   /* @SuppressWarnings("unchecked")
    private static RedisTemplate<String, Object> redisTemplate =
            SpringUtils.getBean("redisTemplate", RedisTemplate.class);*/

    private static RedisTemplate redisTemplate = new RedisTemplate();
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

    public static void main(String[] args) {
        //redisPool();
        // 获取当前线程路径
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
        // 当前执行方法的类名+方法名作为锁的key
        String key = StringUtils.joinWith(".", stackTraceElement.getClassName(), stackTraceElement.getMethodName());
        long currentTimeMillis = System.currentTimeMillis();
        if (RedisLockUtil.lock(key, currentTimeMillis)) {
            logger.info("执行任务");
            RedisLockUtil.unlock(key, currentTimeMillis);
        }
    }

    public static void redisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        config.setMaxTotal(30);
        //最大连接空闲数
        config.setMaxIdle(2);

        JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
        Jedis jedis = null;

        try  {
            jedis = pool.getResource();
            jedis.set("name", "lisi");
            String name = jedis.get("name");
            System.out.println(name);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(jedis != null){
                //关闭连接
                jedis.close();
            }
        }
    }
}
