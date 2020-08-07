package com.ruiqing.demo;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/7/21 14:32
 */

public class Demo1 extends jz1{
    String d ;
    Demo1 () {
        System.out.println("6");
    }
    Demo1 (String s) {
        System.out.println("6"+s);
    }
    static {
        System.out.println("3");
    }

    public static void main(String[] args) {
        Demo1 d = new Demo1("7");
        Demo1 d2 = new Demo1();

    }

}
class jz1 extends jz2{
    jz1 () {
        System.out.println("5");
    }
    static {
        System.out.println("2");
    }
}
class jz2{
    jz2 () {
        System.out.println("4");
    }
    jz2 (String str) {
        System.out.println("4"+str);
    }
    static {
        System.out.println("1");
    }
}