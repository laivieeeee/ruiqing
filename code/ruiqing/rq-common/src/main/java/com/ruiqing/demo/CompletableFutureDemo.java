package com.ruiqing.demo;

import com.ruiqing.dto.SortDTO;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.*;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/8/3 19:07
 * runAsync方法不支持返回值。
 * <p>
 * supplyAsync可以支持返回值。
 */

public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("222");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("11111");
        voidCompletableFuture.get();

        SortDTO dto = new SortDTO();
        dto.setFieldName("ddd");
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(dto::getFieldName)
                .thenCompose((param) -> CompletableFuture.supplyAsync(() -> param + ":thenCompose"));
        TimeUnit.SECONDS.sleep(1);
        dto.setFieldName("aaa");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
           // int i = 1 / 0;
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return dto.getFieldName();
        });

        // get()方法会阻塞，一般放到最后执行
        System.out.println(f1.get() + "----" + f2.get());

        f1.runAfterEither(f2, () -> {
            try {
                //f1,f2都完成了才会执行下一步的操作（Runnable）
                System.out.println("f1,f2任意一个执行完成都进行下一步的操作（runAfterEither）" + f1.get() + "--");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        f1.runAfterBoth(f2, () -> {
            try {
                //f1,f2都完成了才会执行下一步的操作（Runnable）
                System.out.println("f1,f2都完成了才会执行下一步的操作（runAfterBoth）" + f1.get() + "--" + f2.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private static void thenCompose() throws Exception {
        //thenCompose 方法允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作。
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            System.out.println("t1=" + t);
            return t;
        }).thenCompose(param -> CompletableFuture.supplyAsync(() -> {
            int t = param * 2;
            System.out.println("t2=" + t);
            return t;
        }));
        System.out.println("thenCompose result : " + f.get());
    }

    private static void runAfterBoth() throws Exception {
        //两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1=" + t);
                return t;
            }
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2=" + t);
                return t;
            }
        });
        f1.runAfterBoth(f2, () -> System.out.println("上面两个任务都执行完成了。"));
    }

    private static void runAfterEither() throws Exception {
        //两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + t);
            return t;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2=" + t);
            return t;
        });
        f1.runAfterEither(f2, () -> System.out.println("上面有一个已经完成了。"));
    }

    private static void acceptEither() throws Exception {
        //两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作。
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + t);
            return t;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2=" + t);
            return t;
        });
        f1.acceptEither(f2, System.out::println);
    }

    private static void applyToEither() throws Exception {
        //两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + t);
            return t;
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2=" + t);
            return t;
        });

        CompletableFuture<Integer> result = f1.applyToEither(f2, t -> {
            System.out.println(t);
            return t * 2;
        });

        System.out.println(result.get());
    }

    private static void thenAcceptBoth() throws Exception {
        //当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + t);
            return t;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2=" + t);
            return t;
        });
        f1.thenAcceptBoth(f2, (t, u) -> System.out.println("f1=" + t + ";f2=" + u + ";"));
    }

    private static void thenCombine() throws Exception {
        //thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "world");
        CompletableFuture<String> result = future1.thenCombine(future2, (t, u) -> t + " " + u);
        System.out.println(result.get());
    }

    public static void thenRun() throws Exception {
        //跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenAccept 。
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> new Random().nextInt(10))
                .thenRun(() -> {
            System.out.println("thenRun ...");
        });
        future.get();
    }

    public static void thenAccept() throws Exception {
        //接收任务的处理结果，并消费处理，无返回结果。
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> new Random().nextInt(10)).thenAccept(integer -> {
            System.out.println(integer);
        });
        future.get();
    }

    public static void handle() throws Exception {
        //handle 是执行任务完成时对结果的处理。
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 10 / 0;
            return new Random().nextInt(10);
        }).handle((param, throwable) -> {
            int result = -1;
            if (throwable == null) {
                result = param * 2;
            } else {
                System.out.println(throwable.getMessage());
            }
            return result;
        });
        System.out.println(future.get());
    }

    //无返回值
    public static void runAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
        });

        future.get();
    }

    //有返回值
    public static void supplyAsync() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
            return System.currentTimeMillis();
        });

        long time = future.get();
        System.out.println("time = " + time);
    }

    public static void whenComplete() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if (new Random().nextInt() % 2 >= 0) {
                int i = 12 / 0;
            }
            System.out.println("run end ...");
        });

        future.whenComplete((t, action) -> System.out.println("执行完成！"));
        future.exceptionally(t -> {
            System.out.println("执行失败！" + t.getMessage());
            return null;
        });

        TimeUnit.SECONDS.sleep(2);
    }

    private static void thenApply() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                System.out.println("result1=" + result);
                return result;
            }
        }).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long t) {
                long result = t * 5;
                System.out.println("result2=" + result);
                return result;
            }
        });

        long result = future.get();
        System.out.println(result);
    }
}
