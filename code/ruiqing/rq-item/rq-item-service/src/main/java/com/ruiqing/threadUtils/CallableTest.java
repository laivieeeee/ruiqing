package com.ruiqing.threadUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/7/6 15:37
 */

public class CallableTest implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println("好好好");
        return "你好啊";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableTest callableTest = new CallableTest();
        FutureTask<Object> futureTask = new FutureTask<Object>(callableTest);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
