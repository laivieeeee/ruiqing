package com.ruiqing.demo;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/7/21 15:22
 */

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock是Lock的默认实现，在聊ReentranLock之前，我们需要先弄清楚一些概念：
 *
 *     可重入锁：可重入锁是指同一个线程可以多次获得同一把锁；ReentrantLock和关键字Synchronized都是可重入锁
 *
 *     可中断锁：可中断锁时只线程在获取锁的过程中，是否可以相应线程中断操作。synchronized是不可中断的，ReentrantLock是可中断的
 *
 *     公平锁和非公平锁：公平锁是指多个线程尝试获取同一把锁的时候，获取锁的顺序按照线程到达的先后顺序获取，
 *     而不是随机插队的方式获取。synchronized是非公平锁，而ReentrantLock是两种都可以实现，不过默认是非公平锁
 */
public class ReentrantLockDemo {
    //我们使用3个线程来对一个共享变量++操作，先使用synchronized实现，然后使用ReentrantLock实现
    private static int num = 0;

    private static synchronized void addSynchronized() {
        num++;
    }
    public static class T extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                //ReentrantLockDemo.addSynchronized();
                ReentrantLockDemo.addReentrantLock();
            }
        }
    }
    private static ReentrantLock lock = new ReentrantLock();//默认非公平锁
    //private static ReentrantLock lock = new ReentrantLock(true);//true时公平锁
    private static void addReentrantLock() {
        /**
         * ReentrantLock的使用过程：
         *     创建锁：ReentrantLock lock = new ReentrantLock();
         *     获取锁：lock.lock()
         *     释放锁：lock.unlock();
         */
        lock.lock();
        lock.lock();
        try {
            num++;
        } finally {
            /**
             * ReentrantLock是可重入锁
             *     lock()方法和unlock()方法需要成对出现，锁了几次，也要释放几次，否则后面的线程无法获取锁了；
             *     可以将add中的unlock删除一个事实，上面代码运行将无法结束
             *     unlock()方法放在finally中执行，保证不管程序是否有异常，锁必定会释放
             */
            lock.unlock();
            lock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        T t1 = new T();
        T t2 = new T();
        T t3 = new T();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(ReentrantLockDemo.num);

    }
}
