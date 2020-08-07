package com.ruiqing.dao;

import com.ruiqing.dao.base.BaseMapper;
import com.ruiqing.dto.RuiqingDTO;
import com.ruiqing.entity.Money;

import java.util.List;

public interface RuiqingMapper extends BaseMapper<RuiqingDTO> {
	

	List<Money> getRuiqingInfo(Money dto);

	Integer updateRuiqingInfo(RuiqingDTO dto);

	void insertRuiqingInfo(RuiqingDTO dto);

	void insertMoneyIndex(Money dto);

	Integer deleteRuiqingInfo(String ruiqingId);
}