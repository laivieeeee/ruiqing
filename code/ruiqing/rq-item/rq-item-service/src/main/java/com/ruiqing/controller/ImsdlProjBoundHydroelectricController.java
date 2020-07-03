package com.ruiqing.controller;

import com.alibaba.fastjson.JSON;
import com.ruiqing.annotation.LoggerTest;
import com.ruiqing.common.utils.RedisUtil;
import com.ruiqing.dao.ImsdlProjBoundHydroelectricMapper;
import com.ruiqing.dto.ImsdlProjBoundHydroelectricDTO;
import com.ruiqing.dto.LaivieUserInfoDTO;
import com.ruiqing.dto.SortDTO;
import com.ruiqing.service.ImsdlProjBoundHydroelectricService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author zouwanchen
 * @since 2019-10-24
 */
@RestController
@Api(value = "", tags = "测试水电")
@RequestMapping("/ruiqing")
public class ImsdlProjBoundHydroelectricController extends BaseController {
	/** 记录日志 */
	private static final Logger logger = LoggerFactory.getLogger(ImsdlProjBoundHydroelectricController.class);

	@Autowired
	private ImsdlProjBoundHydroelectricService imsdlProjBoundHydroelectricService;

	@Autowired
	private ImsdlProjBoundHydroelectricMapper mapper;
	
	/**
	 * 查询列表
	 */
	@ApiOperation(value = "查询列表")
	@PostMapping("/find")
	@LoggerTest
	public Object find(@ApiParam(value = "应用DTO对象", required = true) @RequestBody ImsdlProjBoundHydroelectricDTO dto) {
		try {

			ImsdlProjBoundHydroelectricDTO boundHydroelectricByExtId = imsdlProjBoundHydroelectricService.getBoundHydroelectricByExtId(dto.getId());
			boundHydroelectricByExtId.setCompPrice(new BigDecimal("124"));
			//RedisUtil.addBoundSetOps("keys", JSON.toJSONString(boundHydroelectricByExtId));
			return renderSuccess(boundHydroelectricByExtId);
		} catch (Exception e ){
			return renderError(e.getMessage());
		}
	}
	/**
	 * 查询列表
	 */
	@ApiOperation(value = "查询列表")
	@PostMapping("/LaivieUserInfoById")
	@LoggerTest
	public Object LaivieUserInfoById(@ApiParam(value = "应用DTO对象", required = true) @RequestBody LaivieUserInfoDTO dto) throws Exception {

		return renderSuccess(mapper.getUserInfoById("1"));
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
	public Object delete(@RequestBody Map<String, Object> idsMap) throws Exception {
		String ids = (String) idsMap.get("ids");
		Object o = idsMap.get("dd");
		SortDTO sortDTO = new SortDTO();
		BeanUtils.copyProperties(idsMap.get("ddd"), sortDTO);
		if(o!=null){
			SortDTO dd = JSON.parseObject(JSON.toJSONString(o), SortDTO.class);
			if(ObjectUtils.anyNotNull(dd)){
				dd.getFieldName();

			}
			dd.getFieldName();
			System.out.println("ddd"+dd.getFieldName());
		}
		int i = imsdlProjBoundHydroelectricService.deleteBatch(Arrays.asList(ids.split(",")));
		return renderSuccess(i);
	}

	public static void main(String[] args) {
		Map<String, Object> idsMap = new HashMap<>();
		idsMap.put("dd","");
		Object o = idsMap.get("dd");
		if(o!= null){
			SortDTO dd = JSON.parseObject(JSON.toJSONString(o), SortDTO.class);
			System.out.println("1"+o);
		}else{
			System.out.println("2"+o);
		}
	}
}


