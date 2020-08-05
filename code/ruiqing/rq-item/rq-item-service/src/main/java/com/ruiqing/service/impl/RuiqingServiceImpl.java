package com.ruiqing.service.impl;


import com.ruiqing.dao.RuiqingMapper;
import com.ruiqing.dto.RuiqingDTO;
import com.ruiqing.service.RuiqingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author zouwanchen
 * @since 2019-10-24
 */
//@CacheConfig(cacheNames = "users")：配置该数据访问对象中返回的内容将存储于名为users的缓存对象中
@CacheConfig(cacheNames = "users")
@Service
public class RuiqingServiceImpl extends BaseServiceImpl<RuiqingMapper, RuiqingDTO> implements RuiqingService {

	@Autowired
	RuiqingMapper ruiqingMapper;

	@Override
	//#p0的意思是指加有@Cacheable注解的方法中的第一个参数
	//@Cacheable(key ="#p0")
	@Cacheable(value="ruiqing",key="#dto.getRuiqingId()")
	public List<RuiqingDTO> getRuiqingInfo(RuiqingDTO dto) {
		return ruiqingMapper.getRuiqingInfo(dto);
	}

	@Override
	//@CachePut 注释，这个注释可以确保方法被执行，同时方法的返回值也被记录到缓存中，实现缓存与数据库的同步更新。
	@CachePut(value="ruiqing",key="#dto.getRuiqingId()")// 更新accountCache 缓存
	public Integer updateRuiqingInfo(RuiqingDTO dto) {
		return ruiqingMapper.updateRuiqingInfo(dto);
	}

	@Override
	public void insertRuiqingInfo(RuiqingDTO dto) {
		ruiqingMapper.insertRuiqingInfo(dto);
	}

	@Override
	public Integer deleteRuiqingInfo(String ruiqingId) {
		return ruiqingMapper.deleteRuiqingInfo(ruiqingId);
	}
	@Override
	public void dd (String dd) {
		System.out.println("你好啊" + dd);
	}
}