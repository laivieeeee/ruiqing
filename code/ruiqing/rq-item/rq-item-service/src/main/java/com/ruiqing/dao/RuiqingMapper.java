package com.ruiqing.dao;

import com.ruiqing.dao.base.BaseMapper;
import com.ruiqing.dto.RuiqingDTO;
import com.ruiqing.dto.LaivieUserInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RuiqingMapper extends BaseMapper<RuiqingDTO> {
	

	List<RuiqingDTO> getRuiqingInfo(RuiqingDTO dto);

	Integer updateRuiqingInfo(RuiqingDTO dto);

	void insertRuiqingInfo(RuiqingDTO dto);

	Integer deleteRuiqingInfo(String ruiqingId);
}