package com.ruiqing.dao;

import com.ruiqing.dao.base.BaseMapper;
import com.ruiqing.dto.RuiqingDTO;
import com.ruiqing.dto.LaivieUserInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LaivieUserInfoMapper extends BaseMapper<RuiqingDTO> {
	

	List<LaivieUserInfoDTO> getUserInfoById(@Param("userId") String userId) throws Exception;
}