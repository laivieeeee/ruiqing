package com.ruiqing.service.impl;


import com.ruiqing.dao.RuiqingMapper;
import com.ruiqing.dto.RuiqingDTO;
import com.ruiqing.service.RuiqingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
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
	public List<RuiqingDTO> getRuiqingInfo(RuiqingDTO dto) {
		return ruiqingMapper.getRuiqingInfo(dto);
	}

	@Override
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
}