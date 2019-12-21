package com.ruiqing.controller;

import com.alibaba.fastjson.JSON;
import com.ruiqing.common.utils.RedisUtil;
import com.ruiqing.common.utils.RedisUtil2;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

/**
 * @Author: Lai JianZheng
 * @Date: 2019/12/21 14:15
 */
@RestController
@Api(value = "", tags = "redis测试")
@RequestMapping("/redisTest")
public class RedisTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @PostMapping("/get{id}")
    public Object get(@RequestBody String id){
        return RedisUtil.getJson(id,JSON.class);
    }

    @PostMapping("/set")
    public Object set(@RequestBody Map<String, Object> map){
        String id = (String) map.get("id");
        RedisUtil.set(id, JSON.toJSONString(map),10L);
        return get(id);
    }

}
