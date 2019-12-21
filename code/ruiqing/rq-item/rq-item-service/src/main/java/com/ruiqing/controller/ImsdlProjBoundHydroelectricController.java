package com.ruiqing.controller;

import com.alibaba.fastjson.JSON;
import com.ruiqing.common.utils.RedisUtil;
import com.ruiqing.dto.ImsdlProjBoundHydroelectricDTO;
import com.ruiqing.service.ImsdlProjBoundHydroelectricService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

/**
 * 
 * @author zouwanchen
 * @since 2019-10-24
 */
@RestController
@Api(value = "", tags = "测试水电")
@RequestMapping("/imsdlProjBoundHydroelectric")
public class ImsdlProjBoundHydroelectricController extends BaseController {
	/** 记录日志 */
	private static final Logger logger = LoggerFactory.getLogger(ImsdlProjBoundHydroelectricController.class);

	@Autowired
	private ImsdlProjBoundHydroelectricService imsdlProjBoundHydroelectricService;
	
	
	
	/**
	 * 查询列表
	 */
	@ApiOperation(value = "查询列表")
	@PostMapping("/query")
	public Object query(@ApiParam(value = "应用DTO对象", required = true) @RequestBody ImsdlProjBoundHydroelectricDTO dto) {
		ImsdlProjBoundHydroelectricDTO boundHydroelectricByExtId = imsdlProjBoundHydroelectricService.getBoundHydroelectricByExtId(dto.getId());
        boundHydroelectricByExtId.setCompPrice(new BigDecimal("124"));
        RedisUtil.addBoundSetOps("keys", JSON.toJSONString(boundHydroelectricByExtId));
		return renderSuccess(boundHydroelectricByExtId);
	}

	
	/**
	 * 更新数据
	 */
	@ApiOperation(value = "更新")
	@PostMapping(value = { "/update" })
	public Object update(@ApiParam(value = "应用DTO对象", required = true) @RequestBody ImsdlProjBoundHydroelectricDTO dto) throws Exception {
		int i = imsdlProjBoundHydroelectricService.update(dto);
		return renderSuccess(i);
	}



	/**
	 * 新增数据
	 */
	@ApiOperation(value = "新增")
	@PostMapping(value = { "/add" })
	public Object add(@ApiParam(value = "DTO对象", required = true) @RequestBody ImsdlProjBoundHydroelectricDTO dto) throws Exception {
		imsdlProjBoundHydroelectricService.insert(dto);
		return renderSuccess();
	}
	
	/**
	 * 根据id查看数据
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查看数据")
	@PostMapping(value = { "/detail/{id}" })
	public Object view(@PathVariable(name="id") String id) throws Exception {
		ImsdlProjBoundHydroelectricDTO dto = imsdlProjBoundHydroelectricService.getById(id);
		return renderSuccess(dto);
	}

	/**
	 * 根据id删除数据
	 * 
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "删除数据")
	@PostMapping(value = "/delete")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "param", value = "{ \"ids\":\"1,2,3\"}", required = true, dataType = "") })
	public Object delete(@RequestBody Map<String, String> idsMap) throws Exception {
		String ids = idsMap.get("ids");
		int i = imsdlProjBoundHydroelectricService.deleteBatch(Arrays.asList(ids.split(",")));
		return renderSuccess(i);
	}
}


