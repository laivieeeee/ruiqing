package com.ruiqing.mqUtils;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/8/13 14:31
 */

public class test {
    public static void main(String[] args) {
        int dd = dd();
        System.out.println(dd);
    }
    public static int dd(){
        try {
            return 1/0;
        } catch (Exception e) {
            return 1;
        }
    }
}
