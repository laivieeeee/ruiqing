package com.ruiqing.demo;

import java.util.concurrent.*;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/7/20 13:49
 */

public class ExecutorLock {

    private static ExecutorService single = Executors.newSingleThreadExecutor();


    public static class AnotherCallable implements Callable<String> {

        @Override

        public String call() throws Exception {

            System.out.println("in AnotherCallable");

            return "annother success";

        }

    }



    public static class MyCallable implements Callable<String> {

        @Override

        public String call() throws Exception {

            System.out.println("in MyCallable");

            Future<String> submit = single.submit(new AnotherCallable());

            return "success:" + submit.get();

        }

    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyCallable task = new MyCallable();

        Future<String> submit = single.submit(task);

        System.out.println(submit.get());

        System.out.println("over");

        single.shutdown();

    }

}
