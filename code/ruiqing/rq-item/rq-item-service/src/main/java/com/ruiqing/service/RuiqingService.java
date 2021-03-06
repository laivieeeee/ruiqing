package com.ruiqing.service;


import com.ruiqing.dto.RuiqingDTO;
import com.ruiqing.entity.Money;

import java.util.List;

/**
 *  服务类
 * @author zouwanchen
 * @since 2019-10-24
 */
public interface RuiqingService extends BaseService<RuiqingDTO> {
	

	List<Money> getRuiqingInfo(Money dto);

	Integer updateRuiqingInfo(RuiqingDTO dto);

	void insertRuiqingInfo(RuiqingDTO dto);

	Integer deleteRuiqingInfo(String ruiqingId);
	void dd (String dd);
}