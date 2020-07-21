package com.ruiqing.demo;

import org.apache.poi.ss.formula.functions.T;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/7/21 14:48
 */

public class ThreadSave implements Runnable{
    static ThreadSave instance = new ThreadSave();
    static int num = 0;

    /**
     * synchronize作用于实例方法需要注意：
     *
     *     实例方法上加synchronized，线程安全的前提是，多个线程操作的是同一个实例，如果多个线程作用于不同的实例，那么线程安全是无法保证的
     *
     *     同一个实例的多个实例方法上有synchronized，这些方法都是互斥的，同一时间只允许一个线程操作同一个实例的其中的一个synchronized方法
     */
    public synchronized void add() {
        num++;
    }

    @Override
    public void run() {
        //使用同步代码块对变量i进行同步操作,锁对象为instance

        synchronized (instance) {

            for (int j = 0; j < 10000; j++) {

                num++;

            }

        }
    }

    public static class T extends Thread {
        private ThreadSave threadSave;

        public T(ThreadSave threadSave) {
            this.threadSave = threadSave;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {

                this.threadSave.add();

            }
        }
    }

    //当synchronized作用于静态方法时，锁的对象就是当前类的Class对象
    public synchronized static void m1() {
        for (int i = 0; i < 10000; i++) {

            num++;

        }

    }


    public static class T1 extends Thread {

        @Override

        public void run() {

            ThreadSave.m1();

        }

    }


    public static void main(String[] args) throws InterruptedException {

       /* T1 t1 = new T1();

        T1 t2 = new T1();

        T1 t3 = new T1();

        t1.start();

        t2.start();

        t3.start();


        //等待3个线程结束打印num

        t1.join();

        t2.join();

        t3.join();


        System.out.println(ThreadSave.num);*/

        /**
         * 打印结果：
         * 25572

         */
        /*ThreadSave threadSave = new ThreadSave();

        T t1 = new T(threadSave);

        T t2 = new T(threadSave);

        t1.start();

        t2.start();


        t1.join();

        t2.join();


        System.out.println(num);*/
        Thread t1 = new Thread(instance);

        Thread t2 = new Thread(instance);

        t1.start();

        t2.start();


        t1.join();

        t2.join();


        System.out.println(num);
    }
}
