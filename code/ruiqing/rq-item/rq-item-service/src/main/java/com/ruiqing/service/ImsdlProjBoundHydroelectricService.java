package com.ruiqing.service;


import com.ruiqing.dto.ImsdlProjBoundHydroelectricDTO;

/**
 *  服务类
 * @author zouwanchen
 * @since 2019-10-24
 */
public interface ImsdlProjBoundHydroelectricService extends BaseService<ImsdlProjBoundHydroelectricDTO> {
	
	public void saveOrUpdate(String userId, ImsdlProjBoundHydroelectricDTO dto) throws Exception;
	
	public ImsdlProjBoundHydroelectricDTO getBoundHydroelectricByExtId(String projExtId);
	
	public void deleteByProjExtId(String projExtId) throws Exception;
}