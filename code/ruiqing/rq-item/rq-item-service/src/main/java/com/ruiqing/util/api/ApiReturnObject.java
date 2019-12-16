package com.ruiqing.util.api;

import com.ruiqing.enums.ErrorCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

public class ApiReturnObject {

	private String statusCode = ErrorCodeEnum.FAIL.getStatusCode();

	private String msg = null;

	@ApiModelProperty(value = "状态编码")
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@ApiModelProperty(value = "提示信息")
	public String getMsg() {
		if (StringUtils.isBlank(this.msg) && StringUtils.equals(ErrorCodeEnum.FAIL.getStatusCode(), this.statusCode))
			//return ErrorCodeEnum.FAIL.getStatusMsg();
        {
            return "API RETURN ERROR";
        }
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}