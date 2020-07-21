package com.ruiqing.demo;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/7/21 14:35
 */

public class Daemon {
    public static class T1 extends Thread {

        public T1(String name) {

            super(name);

        }


        @Override

        public void run() {

            System.out.println(this.getName() + "开始执行," + (this.isDaemon() ? "我是守护线程" : "我是用户线程"));

            while (true) {
                ;
            }

        }

    }


    public static void main(String[] args) {

        T1 t1 = new T1("子线程1");
        //设置守护线程，需要在start()方法之前进行
        t1.setDaemon(true);//true是守护线程(当系统只剩下守护进程的时候，java虚拟机会自动退出)，false是用户线程
        t1.start();

        System.out.println("主线程结束");

    }
}
