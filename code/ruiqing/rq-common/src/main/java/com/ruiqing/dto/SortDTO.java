package com.ruiqing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "排序对象")
public class SortDTO implements Serializable {

	private static final long serialVersionUID = 240298395850131008L;

	@ApiModelProperty(value = "排序字段名", required = false)
    private String fieldName;
    
	@ApiModelProperty(value = "排序方式", required = false)
    private String orderBy = "ASC";

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
