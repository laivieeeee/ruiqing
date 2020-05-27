package com.ruiqing.service.impl;


import com.ruiqing.dao.ImsdlProjBoundHydroelectricMapper;
import com.ruiqing.dto.ImsdlProjBoundHydroelectricDTO;
import com.ruiqing.service.ImsdlProjBoundHydroelectricService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 
 * @author zouwanchen
 * @since 2019-10-24
 */
//@CacheConfig(cacheNames = "users")：配置该数据访问对象中返回的内容将存储于名为users的缓存对象中
@CacheConfig(cacheNames = "users")
@Service
public class ImsdlProjBoundHydroelectricServiceImpl extends BaseServiceImpl<ImsdlProjBoundHydroelectricMapper, ImsdlProjBoundHydroelectricDTO> implements ImsdlProjBoundHydroelectricService {

	@Override
	public void saveOrUpdate(String userId, ImsdlProjBoundHydroelectricDTO dto) throws Exception {
		// TODO Auto-generated method stub

	}
	//#p0的意思是指加有@Cacheable注解的方法中的第一个参数
	@Cacheable(key ="#p0")
	@Override
	public ImsdlProjBoundHydroelectricDTO getBoundHydroelectricByExtId(String projExtId) {
		return mapper.getBoundHydroelectricByExtId(projExtId);
	}

	@Override
	public void deleteByProjExtId(String projExtId) throws Exception {
		// TODO Auto-generated method stub
		mapper.deleteByProjExtId(projExtId);
	}

}