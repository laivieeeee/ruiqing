package com.ruiqing.dao;

import com.ruiqing.dao.base.BaseMapper;
import com.ruiqing.dto.ImsdlProjBoundHydroelectricDTO;
import com.ruiqing.dto.LaivieUserInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImsdlProjBoundHydroelectricMapper extends BaseMapper<ImsdlProjBoundHydroelectricDTO> {
	
	ImsdlProjBoundHydroelectricDTO getBoundHydroelectricByExtId(@Param("projExtId") String projExtId);

	List<LaivieUserInfoDTO> getUserInfoById(@Param("userId") String userId) throws Exception;
}