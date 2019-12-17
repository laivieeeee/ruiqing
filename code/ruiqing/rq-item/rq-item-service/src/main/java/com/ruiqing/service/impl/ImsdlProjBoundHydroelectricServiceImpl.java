package com.ruiqing.service.impl;


import com.ruiqing.dao.ImsdlProjBoundHydroelectricMapper;
import com.ruiqing.dto.ImsdlProjBoundHydroelectricDTO;
import com.ruiqing.service.ImsdlProjBoundHydroelectricService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author zouwanchen
 * @since 2019-10-24
 */
@Service
public class ImsdlProjBoundHydroelectricServiceImpl extends BaseServiceImpl<ImsdlProjBoundHydroelectricMapper, ImsdlProjBoundHydroelectricDTO> implements ImsdlProjBoundHydroelectricService {

	@Override
	public void saveOrUpdate(String userId, ImsdlProjBoundHydroelectricDTO dto) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ImsdlProjBoundHydroelectricDTO getBoundHydroelectricByExtId(String projExtId) {
		// TODO Auto-generated method stub
		return mapper.getBoundHydroelectricByExtId(projExtId);
	}

	@Override
	public void deleteByProjExtId(String projExtId) throws Exception {
		// TODO Auto-generated method stub
		mapper.deleteByProjExtId(projExtId);
	}

}