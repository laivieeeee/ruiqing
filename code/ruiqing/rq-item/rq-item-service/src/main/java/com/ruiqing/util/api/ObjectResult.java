package com.ruiqing.util.api;

import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

public class ObjectResult extends ApiReturnObject {

	private Object dataResult;

	private Map<?, ?> extra;

	@ApiModelProperty(dataType = "java.util.Map", value = "扩展对象")
	public Map<?, ?> getExtra() {
		return extra;
	}

	public void setExtra(Map<?, ?> extra) {
		this.extra = extra;
	}

	@ApiModelProperty(value = "数据对象")
	public Object getDataResult() {
		return dataResult;
	}

	public void setDataResult(Object dataResult) {
		this.dataResult = dataResult;
	}
}
