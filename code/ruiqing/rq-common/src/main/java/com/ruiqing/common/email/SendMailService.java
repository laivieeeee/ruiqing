package com.ruiqing.common.email;

/**
 * 发送邮件接口
 * @author LUWEIMIAO1
 * @date 2017年10月23日 下午8:10:52
 */
public interface SendMailService {
	
	public void sendEmail(final Email email) throws SendMailException;

}
