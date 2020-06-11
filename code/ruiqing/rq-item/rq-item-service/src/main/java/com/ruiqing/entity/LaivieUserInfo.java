package com.ruiqing.entity;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/6/11 14:55
 */
@Data
public class LaivieUserInfo {
    @Column(name = "user_id")
    private String userId;
    @Column
    private String name;
    @Column
    private String age;
    @Column(name = "birth_day")
    private Date birthDay;
}
