package com.ruiqing.controller;

import com.ruiqing.entity.MailVo;
import com.ruiqing.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/6/12 14:57
 */

@RestController()
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;
    /**
     * 发送邮件的主界面
     */
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/sendMail.html");//打开发送邮件的页面
        mv.addObject("from", mailService.getMailSendFrom());//邮件发信人
        return mv;
    }
    /**
     * 发送邮件
     */
    @PostMapping("/mail/send")
    public Object sendMail(@RequestBody MailVo mailVo) {
        try{

            return mailService.sendMail(mailVo);//发送邮件和附件
        } catch (Exception e) {
            return e;
        }
    }
}
