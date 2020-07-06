package com.ruiqing.threadUtils;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/7/6 15:31
 */

public class RunnableTest implements Runnable{

    //线程体
    @Override
    public void run() {
        System.out.println("我是线程");
    }
    public static void main(String[] args){
        //线程的执行目标对象
        RunnableTest myRunnable = new RunnableTest();
        //实际的线程对象
        Thread thread = new Thread(myRunnable);
        //启动线程
        thread.start();
    }
}
