package com.ruiqing.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis操作类
 */
public class RedisUtil {

	private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

	@SuppressWarnings("unchecked")
	/*private static RedisTemplate<String, Object> redisTemplate = SpringUtils.getBean("redisTemplate",
			RedisTemplate.class);*/

	/*private static StringRedisTemplate stringRedisTemplate = SpringUtils.getBean("stringRedisTemplate",
			StringRedisTemplate.class);*/

	/**
	 * 删除缓存<br>
	 * 根据key精确匹配删除
	 * 
	 * @param key
	 */
	//@SuppressWarnings("unchecked")
	public static void del(String... key) {
	/*	if (key != null && key.length > 0) {
			if (key.length == 1) {
				if(redisTemplate.hasKey(key[0])){
					redisTemplate.delete(key[0]);
				}
			} else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}*/
	}

	/**
	 * 批量删除<br>
	 * （该操作会执行模糊查询，请尽量不要使用，以免影响性能或误删）
	 * 
	 * @param pattern
	 */
	public static void batchDel(String... pattern) {
		/*for (String kp : pattern) {
			redisTemplate.delete(redisTemplate.keys(kp + "*"));
		}*/
	}

	/**
	 * 取得缓存（int型）
	 * 
	 * @param key
	 * @return
	 */
	public static Integer getInt(String key) {
		/*String value = stringRedisTemplate.boundValueOps(key).get();
		if (StringUtils.isNotBlank(value)) {
			return Integer.valueOf(value);
		}*/
		return null;
	}

	/**
	 * 取得缓存（字符串类型）
	 * 
	 * @param key
	 * @return
	 */
	/*public static String getStr(String key) {
		return stringRedisTemplate.boundValueOps(key).get();
	}*/

	/**
	 * 取得缓存（字符串类型）
	 * 
	 * @param key
	 * @return
	 */
	/*public static String getStr(String key, boolean retain) {
		String value = stringRedisTemplate.boundValueOps(key).get();
		if (!retain) {
			redisTemplate.delete(key);
		}
		return value;
	}*/

	/**
	 * 获取缓存<br>
	 * 注：基本数据类型(Character除外)，请直接使用get(String key, Class<T> clazz)取值
	 * 
	 * @param key
	 * @return
	 */
	/*public static Object getObj(String key) {
		return redisTemplate.boundValueOps(key).get();
	}*/

	/**
	 * 获取缓存<br>
	 * 注：java 8种基本类型的数据请直接使用get(String key, Class<T> clazz)取值
	 * 
	 * @param key
	 * @param retain
	 *            是否保留
	 * @return
	 */
	/*public static Object getObj(String key, boolean retain) {
		*//*Object obj = redisTemplate.boundValueOps(key).get();
		if (!retain) {
			redisTemplate.delete(key);
		}*//*
		return obj;
	}*/

	/**
	 * 获取缓存<br>
	 * 注：该方法暂不支持Character数据类型
	 * 
	 * @param key
	 *            key
	 * @param clazz
	 *            类型
	 * @return
	 */
	//@SuppressWarnings("unchecked")
	/*public static <T> T get(String key, Class<T> clazz) {
		if (clazz.equals(String.class)) {
			return (T) stringRedisTemplate.boundValueOps(key).get();
		} else if (clazz.equals(Integer.class)) {
			return (T) stringRedisTemplate.boundValueOps(key).get();
		} else if (clazz.equals(Double.class)) {
			return (T) stringRedisTemplate.boundValueOps(key).get();
		} else if (clazz.equals(Float.class)) {
			return (T) stringRedisTemplate.boundValueOps(key).get();
		} else if (clazz.equals(Short.class)) {
			return (T) stringRedisTemplate.boundValueOps(key).get();
		} else if (clazz.equals(Long.class)) {
			return (T) stringRedisTemplate.boundValueOps(key).get();
		} else if (clazz.equals(Boolean.class)) {
			return (T) stringRedisTemplate.boundValueOps(key).get();
		} else {
			return (T) redisTemplate.boundValueOps(key).get();
		}
	}*/

	/**
	 * 获取缓存json对象<br>
	 * 
	 * @param key
	 *            key
	 * @param clazz
	 *            类型
	 * @return
	 */
	/*public static <T> T getJson(String key, Class<T> clazz) {
		return JSON.parseObject(stringRedisTemplate.boundValueOps(key).get(), clazz);
	}*/

	/**
	 * 将value对象写入缓存
	 * 
	 * @param key
	 * @param value
	 * @param time
	 *            失效时间(秒)
	 */
	/*public static void set(String key, Object value, long time) {
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
		
	}*/

	/**
	 * 将value对象写入缓存
	 * 
	 * @param key
	 * @param value
	 * @param time
	 *            失效时间(秒)
	 */
	/*public static void set(String key, Object value) {
		if (value.getClass().equals(String.class)) {
			stringRedisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Integer.class)) {
			stringRedisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Double.class)) {
			stringRedisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Float.class)) {
			stringRedisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Short.class)) {
			stringRedisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Long.class)) {
			stringRedisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Boolean.class)) {
			stringRedisTemplate.opsForValue().set(key, value.toString());
		} else {
			redisTemplate.opsForValue().set(key, value);
		}
	}*/

	/**
	 * 将value对象以JSON格式写入缓存
	 * 
	 * @param key
	 * @param value
	 * @param time
	 *            失效时间(秒)
	 */
	/*public static void setJson(String key, Object value, long time) {
		stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value));
		if (time > 0) {
			stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
	}*/

	/**
	 * 更新key对象field的值
	 * 
	 * @param key
	 *            缓存key
	 * @param field
	 *            缓存对象field
	 * @param value
	 *            缓存对象field值
	 */
	/*public static void setJsonField(String key, String field, String value) {
		JSONObject obj = JSON.parseObject(stringRedisTemplate.boundValueOps(key).get());
		obj.put(field, value);
		stringRedisTemplate.opsForValue().set(key, obj.toJSONString());
	}*/

	/**
	 * 递减操作
	 * 
	 * @param key
	 * @param by
	 * @return
	 */
	/*public static double decr(String key, double by) {
		return redisTemplate.opsForValue().increment(key, -by);
	}

	*//**
	 * 递增操作
	 * 
	 * @param key
	 * @param by
	 * @return
	 *//*
	public static double incr(String key, double by) {
		return redisTemplate.opsForValue().increment(key, by);
	}

	*//**
	 * 获取double类型值
	 * 
	 * @param key
	 * @return
	 *//*
	public static double getDouble(String key) {
		String value = stringRedisTemplate.boundValueOps(key).get();
		if (StringUtils.isNotBlank(value)) {
			return Double.valueOf(value);
		}
		return 0d;
	}

	*//**
	 * 设置double类型值
	 * 
	 * @param key
	 * @param value
	 * @param time
	 *            失效时间(秒)
	 *//*
	public static void setDouble(String key, double value, long time) {
		stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
		if (time > 0) {
			stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
	}

	*//**
	 * 设置double类型值
	 * 
	 * @param key
	 * @param value
	 * @param time
	 *            失效时间(秒)
	 *//*
	public static void setInt(String key, int value, long time) {
		stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
		if (time > 0) {
			stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
	}

	*//**
	 * 将map写入缓存
	 * 
	 * @param key
	 * @param map
	 * @param time
	 *            失效时间(秒)
	 *//*
	public static <T> void setMap(String key, Map<String, T> map, long time) {
		redisTemplate.opsForHash().putAll(key, map);
	}

	*//**
	 * 向key对应的map中添加缓存对象
	 * 
	 * @param key
	 * @param map
	 *//*
	public static <T> void addMap(String key, Map<String, T> map) {
		redisTemplate.opsForHash().putAll(key, map);
	}

	*//**
	 * 向key对应的map中添加缓存对象
	 * 
	 * @param key
	 *            cache对象key
	 * @param field
	 *            map对应的key
	 * @param obj
	 *            对象
	 *//*
	public static <T> void addMap(String key, String field, T obj) {
		redisTemplate.opsForHash().put(key, field, obj);
	}

	public static <T> void addMap(String key, String field, T obj, long time) {
		redisTemplate.opsForHash().put(key, field, obj);
		redisTemplate.expire(key, time, TimeUnit.SECONDS);
	}

	*//**
	 * 获取map缓存
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 *//*
	public static <T> Map<String, T> mget(String key, Class<T> clazz) {
		BoundHashOperations<String, String, T> boundHashOperations = redisTemplate.boundHashOps(key);
		return boundHashOperations.entries();
	}

	*//**
	 * 获取map缓存
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 *//*
	public static <T> Map<String, T> getMap(String key, Class<T> clazz) {
		BoundHashOperations<String, String, T> boundHashOperations = redisTemplate.boundHashOps(key);
		Map<String, T> map = boundHashOperations.entries();
		return map;
	}

	*//**
	 * 获取map缓存中的某个对象
	 * 
	 * @param key
	 * @param field
	 * @param clazz
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public static <T> T getMapField(String key, String field, Class<T> clazz) {
		return (T) redisTemplate.boundHashOps(key).get(field);
	}

	*//**
	 * 获取map缓存中的某个对象
	 * 
	 * @param key
	 * @param field
	 * @param clazz
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public static <T> List<T> getMapFieldList(String key, String field, Class<T> clazz) {
		return (List<T>) redisTemplate.boundHashOps(key).get(field);
	}

	*//**
	 * 获取map缓存中的某个对象
	 * 
	 * @param key
	 * @param field
	 * @param clazz
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> getMapFieldMap(String key, String field, Class<T> clazz) {
		return (Map<String, T>) redisTemplate.boundHashOps(key).get(field);
	}

	*//**
	 * 删除map中的某个对象
	 * 
	 * @author lh
	 * @date 2016年8月10日
	 * @param key
	 *            map对应的key
	 * @param field
	 *            map中该对象的key
	 *//*
	public static void delMapField(String key, String... field) {
		BoundHashOperations<String, String, Object> boundHashOperations = redisTemplate.boundHashOps(key);
		boundHashOperations.delete(field);
	}

	*//**
	 * 指定缓存的失效时间
	 * 
	 * @author FangJun
	 * @date 2016年8月14日
	 * @param key
	 *            缓存KEY
	 * @param time
	 *            失效时间(秒)
	 *//*
	public static void expire(String key, long time) {
		if (time > 0) {
			redisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
	}

	*//**
	 * 添加set
	 * 
	 * @param key
	 * @param value
	 *//*
	public static void sadd(String key, String... value) {
		redisTemplate.boundSetOps(key).add(value);
	}

	*//**
	 * 删除set集合中的对象
	 * 
	 * @param key
	 * @param value
	 *//*
	public static void srem(String key, String... value) {
		redisTemplate.boundSetOps(key).remove(value);
	}

	*//**
	 * set重命名
	 * 
	 * @param oldkey
	 * @param newkey
	 *//*
	public static void srename(String oldkey, String newkey) {
		redisTemplate.boundSetOps(oldkey).rename(newkey);
	}

	*//**
	 * 短信缓存
	 * 
	 * @author fxl
	 * @date 2016年9月11日
	 * @param key
	 * @param value
	 * @param time
	 *//*
	public static void setIntForPhone(String key, Object value, int time) {
		stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value));
		if (time > 0) {
			stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
	}

	*//**
	 * 模糊查询keys
	 * 
	 * @param pattern
	 * @return
	 *//*
	public static Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	public static boolean hasFiledInKey(String key, String field) {
		return redisTemplate.hasKey(key) && redisTemplate.boundHashOps(key).hasKey(field);
	}*/
}
