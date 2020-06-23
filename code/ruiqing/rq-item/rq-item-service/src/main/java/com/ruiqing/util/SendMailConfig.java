package com.ruiqing.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/6/12 14:32
 */

public class SendMailConfig {
    @Autowired
    private JavaMailSenderImpl mailSender;

    public void sendMail() throws MessagingException {
        //简单邮件
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("admin@163.com");
        simpleMailMessage.setTo("socks@qq.com");
        simpleMailMessage.setSubject("Happy New Year");
        simpleMailMessage.setText("新年快乐！");
        mailSender.send(simpleMailMessage);

        //复杂邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        messageHelper.setFrom("admin@163.com");
        messageHelper.setTo("socks@qq.com");
        messageHelper.setSubject("Happy New Year");
        messageHelper.setText("新年快乐！");
        messageHelper.addInline("doge.gif", new File("xx/xx/doge.gif"));
        messageHelper.addAttachment("work.docx", new File("xx/xx/work.docx"));
        mailSender.send(mimeMessage);
    }
}
