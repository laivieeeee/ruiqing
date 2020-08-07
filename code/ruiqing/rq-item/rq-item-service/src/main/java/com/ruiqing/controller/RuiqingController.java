package com.ruiqing.controller;

import com.ruiqing.annotation.LoggerTest;
import com.ruiqing.dto.RuiqingDTO;
import com.ruiqing.entity.Money;
import com.ruiqing.service.RuiqingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
	public Object getRuiqingInfo(@ApiParam(value = "应用DTO对象", required = true) @RequestBody Money dto) {
		try {

			List<Money> ruiqing = ruiqingService.getRuiqingInfo(dto);
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
	public Object insertRuiqingInfo(@ApiParam(value = "DTO对象", required = true) @RequestBody Money dto) throws Exception {
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


