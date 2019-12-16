package com.ruiqing.common.email;

/**
 * 发送邮件接口
 */
public interface SendMailService {
	
	public void sendEmail(final Email email) throws SendMailException;

}
