package com.ruiqing.controller;

import com.alibaba.fastjson.JSON;
import com.ruiqing.annotation.LoggerTest;
import com.ruiqing.dao.RuiqingMapper;
import com.ruiqing.dto.RuiqingDTO;
import com.ruiqing.dto.LaivieUserInfoDTO;
import com.ruiqing.dto.SortDTO;
import com.ruiqing.service.RuiqingService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zouwanchen
 * @since 2019-10-24
 */
@RestController
@Api(value = "", tags = "测试模板")
@RequestMapping("/ruiqing")
public class RuiqingController extends BaseController {
	/** 记录日志 */
	private static final Logger logger = LoggerFactory.getLogger(RuiqingController.class);

	@Autowired
	private RuiqingService ruiqingService;

	/**
	 * 查询列表
	 */
	@ApiOperation(value = "查询")
	@PostMapping("/getRuiqingInfo")
	@LoggerTest
	public Object getRuiqingInfo(@ApiParam(value = "应用DTO对象", required = true) @RequestBody RuiqingDTO dto) {
		try {

			List<RuiqingDTO> ruiqing = ruiqingService.getRuiqingInfo(dto);
			//RedisUtil.addBoundSetOps("keys", JSON.toJSONString(boundHydroelectricByExtId));
			return renderSuccess(ruiqing);
		} catch (Exception e ){
			return renderError(e.getMessage());
		}
	}

	
	/**
	 * 更新数据
	 */
	@ApiOperation(value = "更新")
	@PostMapping(value = { "/updateRuiqingInfo" })
	public Object updateRuiqingInfo(@ApiParam(value = "应用DTO对象", required = true) @RequestBody RuiqingDTO dto) throws Exception {
		int i = ruiqingService.updateRuiqingInfo(dto);
		return renderSuccess(i);
	}



	/**
	 * 新增数据
	 */
	@ApiOperation(value = "新增")
	@PostMapping(value = { "/insertRuiqingInfo" })
	public Object insertRuiqingInfo(@ApiParam(value = "DTO对象", required = true) @RequestBody RuiqingDTO dto) throws Exception {
		ruiqingService.insertRuiqingInfo(dto);
		return renderSuccess(ruiqingService.getRuiqingInfo(dto));
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除")
	@PostMapping(value = { "/deleteRuiqingInfo" })
	public Object deleteRuiqingInfo(@Param("ruiqingId")String ruiqingId){
		Integer num = ruiqingService.deleteRuiqingInfo(ruiqingId);
		return renderSuccess(num);
	}

}


