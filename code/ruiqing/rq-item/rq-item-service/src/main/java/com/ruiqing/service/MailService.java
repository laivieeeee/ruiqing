package com.ruiqing.service;

import com.ruiqing.entity.MailVo;
import org.springframework.stereotype.Service;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/6/12 14:56
 */

public interface MailService {
    MailVo sendMail(MailVo mailVo);
    String getMailSendFrom();
}
