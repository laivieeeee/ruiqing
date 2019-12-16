package com.ruiqing.common.email;

import freemarker.template.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class EmailTest {

	public static void main(String[] args) {
		try {
			Email email = new Email();
			email.setHost("smtp.163.com");
			email.setPort(25);
			email.setName("707944442@qq.com");
			email.setPassword("asdf123456");
			email.setFrom("707944442@qq.com");
			String[] sendTo = {"707944442@qq.com"};
			email.setSendTo(sendTo);
			String[] copyTo = {"707944442@qq.com"};
			email.setCopyTo(copyTo);
			email.setTopic("测试邮件!！");
			email.setBody("bbbbbbbbbbbbbb");
			sendEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 发送邮件
	 * @param email Email对象
	 */
	public static void sendEmail(Email email) throws SendMailException {
		if ( email == null ){
			throw new SendMailException("邮件对象Email为空！");
		}

		
		JavaMailSender mailSender = EMailConfigUtil.getMailSender(email);
		MimeMessagePreparator preparator = getMessagePreparator(email);
		mailSender.send(preparator);
	}

	private static MimeMessagePreparator getMessagePreparator(final Email email) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws SendMailException {
				try{
					MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
					//标题
					helper.setSubject(email.getTopic());
					//发件人呢称
					if ( StringUtils.isNoneBlank(email.getSenderNick()) ){
						helper.setFrom(new InternetAddress(email.getFrom(), email.getSenderNick()));
					}else{
						helper.setFrom(email.getFrom());
					}
					//收件人
					helper.setTo(email.getSendTo());
					//抄送人
					if ( email.getCopyTo() != null && email.getCopyTo().length > 0 ){
						helper.setCc(email.getCopyTo());
					}
	
					Map<String, Object> model = new HashMap<String, Object>();
					model.put("body", email.getBody());
	
					String text = geFreeMarkerTemplateContent(model, email.getMailfmTemplate());
	
					//设置邮件内容
					helper.setText(text, true);
	
					//增加邮件附件
					if ( email.getFileAffix() != null && email.getFileAffix().length > 0 ){
						for(String attFilePath : email.getFileAffix() ){
							File file = new File(attFilePath);
							if ( file.isFile() && file.exists() ){
								String attachmentFilename = file.getName();
								helper.addAttachment(attachmentFilename, file);
							}
						}
					}
				}catch(Exception e){
					throw new SendMailException("发送邮件异常！", e);
				}
			}
		};
		return preparator;
	}
	
	/**
	 * freeMarker模板填充内容
	 * @param model 值对象
	 * @param mailfmTemplate 模板名称
	 * @return
	 * @throws SendMailException
	 */
	private static String geFreeMarkerTemplateContent(Map<String, Object> model, String mailfmTemplate) throws SendMailException {
		StringBuffer content = new StringBuffer(500);
		//默认模板文件
		String templateFileName = "simpleMailTemplate.fml";
		if ( StringUtils.isNoneBlank(mailfmTemplate) ){
			templateFileName = mailfmTemplate;
		}
		try {
			Configuration freemarkerConfiguration = EMailConfigUtil.getFreeMarkerConfiguration();
			content.append(FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfiguration.getTemplate(templateFileName), model));
		} catch (Exception e) {
			throw new SendMailException("获取邮件模板出错 fmtemplate:["+templateFileName+"]", e);
		}
		return content.toString();
	}


}
