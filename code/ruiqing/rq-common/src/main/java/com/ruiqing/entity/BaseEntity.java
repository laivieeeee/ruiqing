package com.ruiqing.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.ruiqing.dto.LoginUserDTO;
import com.ruiqing.dto.SortDTO;
import com.ruiqing.dto.base.CommonPageDTO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Entity支持类
 * Created by zlb on 2017年12月12日
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -8758282688297520354L;

	/**
	 * 当前用户
	 */
	@JSONField(serialize=false)
	@ApiModelProperty(hidden=true)
	protected LoginUserDTO loginUser;
	
	/**
	 * 当前时间
	 */
	@JSONField(serialize=false)
	@ApiModelProperty(hidden=true)
	protected Date currentTime;
	
	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	/**
	 * 当前用户id
	 */
	@JSONField(serialize=false)
	@ApiModelProperty(hidden=true)
	protected String loginUserId;
	
	/**
	 * 当前角色id
	 */
	@JSONField(serialize=false)
	@ApiModelProperty(hidden=true)
	protected String currRoleId;
	
	/**
	 * 当前token
	 */
	@JSONField(serialize=false)
	@ApiModelProperty(hidden=true)
	protected String crctoken;
	
	/**
	 * 参数MAP
	 */
	@JSONField(serialize=false)
	@ApiModelProperty(hidden=true)
	protected Map<String,Object> paramsMap;
	
	/**
	 * 当前实体分页对象
	 */
	@ApiModelProperty(value = "分页对象(不涉及分页查询时，可忽略)", required = false)
	protected CommonPageDTO commonPageDTO;
	
	/**
	 * 当前实体排序对象
	 */
	@ApiModelProperty(value = "排序对象(不涉及排序查询时，可忽略)", required = false)
	protected SortDTO sortDTO;
	
	/**
	 * 自定义SQL（SQL标识，SQL内容）
	 */
	@JSONField(serialize=false)
	@ApiModelProperty(hidden=true)
	protected String dataScope;

	public LoginUserDTO getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUserDTO loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}


	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	public CommonPageDTO getCommonPageDTO() {
		return commonPageDTO==null?new CommonPageDTO():commonPageDTO;
	}

	public void setCommonPageDTO(CommonPageDTO commonPageDTO) {
		this.commonPageDTO = commonPageDTO;
	}

	public SortDTO getSortDTO() {
		return sortDTO==null?new SortDTO():sortDTO;
	}

	public void setSortDTO(SortDTO sortDTO) {
		this.sortDTO = sortDTO;
	}


	public String getCrctoken() {
		return crctoken;
	}

	public void setCrctoken(String crctoken) {
		this.crctoken = crctoken;
	}

	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public String getCurrRoleId() {
		return currRoleId;
	}

	public void setCurrRoleId(String currRoleId) {
		this.currRoleId = currRoleId;
	}
	
	
}
