package com.ruiqing.entity;

import com.ruiqing.dto.RuiqingDTO;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/8/6 15:38
 * 指标
 */

@Data
public class Money extends RuiqingDTO {
    private String zqname;
    private String jrkpj;
    private String zrkpj;
    private String zrspj;
    private String dqjg;
    private String jrzgj;
    private String jrzdj;
    private String mr1;
    private String mc1;
    private String cjgps;
    //元
    private String cjjr;
    private String m1g;
    private String m1j;
    private String m2g;
    private String m2j;
    private String m3g;
    private String m3j;
    private String m4g;
    private String m4j;
    private String m5g;
    private String m5j;
    private String c1g;
    private String c1j;
    private String c2g;
    private String c2j;
    private String c3g;
    private String c3j;
    private String c4g;
    private String c4j;
    private String c5g;
    private String c5j;
    private String day;
    private String time;

    private String createBy;
    private Date createTime;

    @Override
    public String toString() {
        return "最新 = '" + dqjg + '\'' +
                ", 最高 = '" + jrzgj + '\'' +
                ", 最低 = '" + jrzdj + '\'' +
                ", 数 = '" + cjgps + '\'' +
                ", 金额 = '" + cjjr + '\'' +
                ", 时间 = '" + time + '\'' +

                "\n" + c1j +
                " = " + c1g +
                ", " + c2j +
                " = " + c2g +
                ", " + c3j +
                " = " + c3g +
                ", " + c4j +
                " = " + c4g +
                ", " + c5j +
                " = " + c5g +
                "\n" + m1j +
                " = " + m1g +
                ", " + m2j +
                " = " + m2g +
                ", " + m3j +
                " = " + m3g +
                ", " + m4j +
                " = " + m4g +
                ", " + m5j +
                " = " + m5g;
    }

}
