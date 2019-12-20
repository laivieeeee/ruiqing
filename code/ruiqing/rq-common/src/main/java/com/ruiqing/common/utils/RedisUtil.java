package com.ruiqing.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis操作类
 */
public class RedisUtil {

	private RedisUtil(){

	}
    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @SuppressWarnings("unchecked")
    private static RedisTemplate<String, Object> redisTemplate = SpringUtils.getBean("redisTemplate",
            RedisTemplate.class);

    private static StringRedisTemplate stringRedisTemplate = SpringUtils.getBean("stringRedisTemplate",
            StringRedisTemplate.class);

    /**
     * 删除缓存<br>
     * 根据key精确匹配删除
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public static void deleteByKey(String key) {
		Boolean delete = redisTemplate.delete(key);
		logger.info("RedisUtil.deleteByKey{}", delete);
	}

    /**
     * 批量删除<br>
     */
    public static void batchDeleteByKeys(List<String> list) {
        list.stream().forEach(RedisUtil::deleteByKey);
    }

    /**
     * 取得缓存（字符串类型）
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        return stringRedisTemplate.boundValueOps(key).get();
    }


    /**
     * 获取缓存<br>
     *
     * @param key
     * @return
     */
    public static Object getObject(String key) {
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     * 获取缓存json对象<br>
     *
     * @param key   key
     * @param clazz 类型
     * @return
     */
    public static <T> T getJson(String key, Class<T> clazz) {
        return JSON.parseObject(stringRedisTemplate.boundValueOps(key).get(), clazz);
    }

    /**
     * 将value对象写入缓存
     *
     * @param key
     * @param value
     * @param time  失效时间(秒)
     */
    public static void set(String key, Object value, long time) {
        if (value.getClass().equals(String.class) || value.getClass().equals(Integer.class)
                || value.getClass().equals(Double.class) || value.getClass().equals(Float.class)
                || value.getClass().equals(Short.class) || value.getClass().equals(Long.class)
                || value.getClass().equals(Boolean.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
            if (time > 0) {
                stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } else {
            redisTemplate.opsForValue().set(key, value);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        }

    }


    /**
     * 递减操作
     *
     * @param key
     * @param by
     * @return
     */
    public static double decr(String key, double by) {
        return redisTemplate.opsForValue().increment(key, -by);
    }

    /*
     * 递增操作
     * @param key
     * @param by
     */
    public static double incr(String key, double by) {
        return redisTemplate.opsForValue().increment(key, by);
    }


    /**
     * 获取double类型值
     *
     * @param key
     * @return
     */
    public static double getDouble(String key) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        if (StringUtils.isNotBlank(value)) {
            return Double.valueOf(value);
        }
        return 0d;
    }

    /**
     * 设置double类型值
     *
     * @param key
     * @param value
     * @param time  失效时间(秒)
     */
    public static void setDouble(String key, double value, long time) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        if (time > 0) {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 设置double类型值
     *
     * @param key
     * @param value
     * @param time  失效时间(秒)
     */
    public static void setInt(String key, int value, long time) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        if (time > 0) {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 将map写入缓存
     *
     * @param key
     * @param map 失效时间(秒)
     */
    public static <T> void setMap(String key, Map<String, T> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 向key对应的map中添加缓存对象
     *
     * @param key
     * @param map
     */
    public static <T> void addMap(String key, Map<String, T> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 向key对应的map中添加缓存对象
     *
     * @param key   cache对象key
     * @param field map对应的key
     * @param obj   对象
     */
    public static <T> void addMap(String key, String field, T obj) {
        redisTemplate.opsForHash().put(key, field, obj);
    }

    public static <T> void addMap(String key, String field, T obj, long time) {
        redisTemplate.opsForHash().put(key, field, obj);
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 获取map缓存
     *
     * @param key
     * @return
     */
    public static <T> Map<String, T> getMap(String key) {
        BoundHashOperations<String, String, T> boundHashOperations = redisTemplate.boundHashOps(key);
        return boundHashOperations.entries();
    }

    /**
     * 获取map缓存中的某个对象
     *
     * @param key
     * @param field
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getMapField(String key, String field) {
        return (T) redisTemplate.boundHashOps(key).get(field);
    }


    /**
     * 删除map中的某个对象
     *
     * @param key   map对应的key
     * @param field map中该对象的key
     * @author lh
     * @date 2016年8月10日
     */
    public static void delMapField(String key, String... field) {
        BoundHashOperations<String, String, Object> boundHashOperations = redisTemplate.boundHashOps(key);
        boundHashOperations.delete(field);
    }

    /**
     * 指定缓存的失效时间
     *
     * @param key  缓存KEY
     * @param time 失效时间(秒)
     * @author FangJun
     * @date 2016年8月14日
     */
    public static void expire(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 添加set
     *
     * @param key
     * @param value
     */
    public static void addBoundSetOps(String key, String... value) {
        redisTemplate.boundSetOps(key).add(value);
    }

    /**
     * 删除set集合中的对象
     *
     * @param key
     * @param value
     */
    public static void removeBoundSetOps(String key, String... value) {
        redisTemplate.boundSetOps(key).remove(value);
    }

    /**
     * set重命名
     *
     * @param oldkey
     * @param newkey
     */
    public static void renameBoundSetOps(String oldkey, String newkey) {
        redisTemplate.boundSetOps(oldkey).rename(newkey);
    }

    /**
     * 模糊查询keys
     *
     * @param pattern
     * @return
     */
    public static Set<String> likeSelectKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }

}
