package com.ruiqing.util.api;

import com.ruiqing.enums.ErrorCodeEnum;

import java.io.Serializable;

/**
 * API接口返回的对象（实现序列化）
 * 
 * @author chengjiarong1
 * @since 2017-12-27
 */
public class ApiResult<T extends Serializable> extends ApiReturnObject implements Serializable {

	private static final long serialVersionUID = 106653974319187284L;

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <U extends Serializable> ApiResult<U> newFailResult() {
		return newFailResult(ErrorCodeEnum.FAIL.getStatusCode(), "The server is busy,please try again later", null);
	}

	public static <U extends Serializable> ApiResult<U> newFailResult(String code, String message) {
		return newFailResult(code, message, null);
	}

	public static <U extends Serializable> ApiResult<U> newFailResult(String code, String message, U data) {
		ApiResult<U> result = new ApiResult<U>();
		result.setStatusCode(code);
		result.setMsg(message);
		result.setData(data);
		return result;
	}

	public static <U extends Serializable> ApiResult<U> newSuccessResult(U data) {
		ApiResult<U> result = new ApiResult<U>();
		result.setStatusCode(ErrorCodeEnum.SUCCESS.getStatusCode());
		result.setMsg("success");
		result.setData(data);
		return result;
	}
}
