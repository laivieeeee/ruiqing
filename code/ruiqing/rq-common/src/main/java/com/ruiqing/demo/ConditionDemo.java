package com.ruiqing.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/7/22 9:44
 */

public class ConditionDemo {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    public static class T1 extends Thread {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备获取锁!");
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "获取锁成功!");
                //condition.await();// 线程等待
                condition.await(2, TimeUnit.SECONDS);// 线程等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "释放锁成功!");
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备获取锁!");
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "获取锁成功!");
                condition.signal();//线程通知，但是继续执行该线程，再执行等待的线程
                System.out.println(System.currentTimeMillis() + "," + this.getName() + " signal!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备释放锁!");
            } finally {
                lock.unlock();
            }
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "释放锁成功!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.setName("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        T2 t2 = new T2();
        t2.setName("t2");
        t2.start();
        //给t1线程发送中断信号
        System.out.println("1、t1中断标志：" + t1.isInterrupted());
        t1.interrupt();
        System.out.println("2、t1中断标志：" + t1.isInterrupted());
    }
}
