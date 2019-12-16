package com.ruiqing.common.email;

import freemarker.template.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * 邮件服务器及freemarker配置工具类
 */
public class EMailConfigUtil {

	private EMailConfigUtil() {
		throw new Error("不需要创建对象！");
	}

	public static JavaMailSender getMailSender(Email email) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		// Using gmail.
		mailSender.setHost(email.getHost());
		mailSender.setPort(email.getPort());
		mailSender.setUsername(email.getName());
		mailSender.setPassword(email.getPassword());
		mailSender.setProtocol(email.getProtocol());

		Properties javaMailProperties = new Properties();
		//javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		//javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	/*
	 * FreeMarker configuration.
	 */
	public static Configuration getFreeMarkerConfiguration() {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
		configuration.setDefaultEncoding("utf-8");
		configuration.setClassForTemplateLoading(EMailConfigUtil.class, "/mailfmtemplates");
		return configuration;
	}

}
