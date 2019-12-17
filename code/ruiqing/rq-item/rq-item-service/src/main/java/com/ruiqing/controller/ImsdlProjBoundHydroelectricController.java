package com.ruiqing.controller;

import com.ruiqing.dto.ImsdlProjBoundHydroelectricDTO;
import com.ruiqing.dto.base.CommonPageDTO;
import com.ruiqing.service.ImsdlProjBoundHydroelectricService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 
 * @author zouwanchen
 * @since 2019-10-24
 */
@RestController
@Api(value = "", description = "")
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
	public Object query(@ApiParam(value = "应用DTO对象", required = true) @RequestBody ImsdlProjBoundHydroelectricDTO dto) throws Exception {
		CommonPageDTO commonPage = imsdlProjBoundHydroelectricService.findPage(dto);
		return renderSuccess(commonPage);
	}

	
	/**
	 * 更新数据
	 */
	@ApiOperation(value = "更新")
	@PostMapping(value = { "/update" })
	public Object update(@ApiParam(value = "应用DTO对象", required = true) @RequestBody ImsdlProjBoundHydroelectricDTO dto) throws Exception {
		//Assert.isNotBlank("", "");
		int i = imsdlProjBoundHydroelectricService.update(dto);
		return renderSuccess(i);
	}



	/**
	 * 新增数据
	 */
	@ApiOperation(value = "新增")
	@PostMapping(value = { "/add" })
	public Object add(@ApiParam(value = "DTO对象", required = true) @RequestBody ImsdlProjBoundHydroelectricDTO dto) throws Exception {
		//Assert.isNotBlank("", "");
		//dto.setCreatedBy(LoginUserSessionHelper.getLoginUserDTO(request).getUserId());
		//dto.setCreatedAt(new Date());
		//dto.setAppId(IdCreater.newId());
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
		//Assert.isNotBlank(id, "");
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
		//Assert.isNotBlank(ids, "");
		int i = imsdlProjBoundHydroelectricService.deleteBatch(Arrays.asList(ids.split(",")));
		return renderSuccess(i);
	}
}


