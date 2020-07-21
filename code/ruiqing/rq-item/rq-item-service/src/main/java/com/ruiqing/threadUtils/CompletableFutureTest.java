package com.ruiqing.threadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/7/6 15:59
 */

public class CompletableFutureTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.runAsync(()->{
            System.out.println("你好啊");
        });
        CompletableFuture<String> hahaha = CompletableFuture.supplyAsync(() -> {
            System.out.println("好傻好啊");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("说你傻你还不信");
            return "一点都不好";
        });
        System.out.println(hahaha.get());
        Thread.sleep(1000L);
    }
}
