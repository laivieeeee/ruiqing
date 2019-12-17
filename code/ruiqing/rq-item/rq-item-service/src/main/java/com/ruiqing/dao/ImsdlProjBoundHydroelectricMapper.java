package com.ruiqing.dao;

import com.ruiqing.dao.base.BaseMapper;
import com.ruiqing.dto.ImsdlProjBoundHydroelectricDTO;
import org.apache.ibatis.annotations.Param;

public interface ImsdlProjBoundHydroelectricMapper extends BaseMapper<ImsdlProjBoundHydroelectricDTO> {
	
	public ImsdlProjBoundHydroelectricDTO getBoundHydroelectricByExtId(@Param("projExtId") String projExtId);

	public void deleteByProjExtId(@Param("projExtId") String projExtId) throws Exception;
}