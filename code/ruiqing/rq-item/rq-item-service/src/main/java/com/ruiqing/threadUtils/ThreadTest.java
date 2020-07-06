package com.ruiqing.threadUtils;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/7/6 15:28
 */

public class ThreadTest extends Thread {
    //线程体
    @Override
    public void run() {
        System.out.println("我是线程");
    }
    public static void main(String[] args){
        //实例化自定义线程类实例
        Thread thread = new ThreadTest();
        //调用start()实例方法启动线程
        thread.start();
    }
}
