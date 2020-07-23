package com.ruiqing.demo.CyclicBarrierDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/7/23 14:08
 */

public class CyclicBarrier1 {
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    public static class T extends Thread {
        int sleep;
        public T(String name, int sleep) {
            super(name);
            this.sleep = sleep;
        }

        @Override
        public void run() {
            try {
                //模拟休眠
                TimeUnit.SECONDS.sleep(sleep);
                long starTime = System.currentTimeMillis();
                //调用await()的时候，当前线程将会被阻塞，需要等待其他员工都到达await了才能继续
                cyclicBarrier.await();
                long endTime = System.currentTimeMillis();
                System.out.println(this.getName() + ",sleep:" + this.sleep + " 等待了" + (endTime - starTime) + "(ms),开始吃饭了！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 21; i++) {
            T t = new T("员工" + i, i);
            t.start();
            if (i == 5) {
                //模拟员工5接了个电话，将自己等待吃饭给打断了
                TimeUnit.SECONDS.sleep(1);
                System.out.println(t.getName() + ",有点急事，我先开干了！");
                t.interrupt();
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }
}
