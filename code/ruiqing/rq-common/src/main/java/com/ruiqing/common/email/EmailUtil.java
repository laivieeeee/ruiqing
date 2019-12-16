package com.ruiqing.common.email;

import org.springframework.stereotype.Component;


/**
 * 发送邮件辅助类
 * 
 */
@Component
public final class EmailUtil {
	
	private static SendMailService sendMailService; 
	
	
	private EmailUtil() {
	}


	/**
	 * 发送邮件
	 * @param email Email对象
	 */
	public static void sendEmail(Email email) throws SendMailException {
//		if ( null == sendMailService){
//			sendMailService = (SendMailService) DubboUtil.refer(SendMailService.class);
//		}
//		sendMailService.sendEmail(email);
		throw new UnsupportedOperationException("dubbo is no longer supported");
	}


	public static SendMailService getSendMailService() {
		return sendMailService;
	}


	public static void setSendMailService(SendMailService sendMailService) {
		EmailUtil.sendMailService = sendMailService;
	}
}
