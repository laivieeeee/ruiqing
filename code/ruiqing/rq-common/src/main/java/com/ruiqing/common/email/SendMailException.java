package com.ruiqing.common.email;

/**
 * 发送邮件异常类
 * @author LUWEIMIAO1
 * @date 2017年10月23日 下午8:16:45
 */
public class SendMailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5788685153792402343L;

	public SendMailException(String message) {
		super(message);
	}

	public SendMailException(String message, Throwable cause) {
		super(message, cause);
	}

}
