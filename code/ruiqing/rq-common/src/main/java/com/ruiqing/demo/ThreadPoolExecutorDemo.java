package com.ruiqing.demo;

import java.util.concurrent.*;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/7/23 14:46
 * corePoolSize：核心线程大小，当提交一个任务到线程池时，线程池会创建一个线程来执行任务，即使有其他空闲线程可以处理任务也会创新线程，等到工作的线程数大于核心线程数时就不会在创建了。如果调用了线程池的prestartAllCoreThreads方法，线程池会提前把核心线程都创造好，并启动
 * maximumPoolSize：线程池允许创建的最大线程数。如果队列满了，并且以创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。如果我们使用了无界队列，那么所有的任务会加入队列，这个参数就没有什么效果了
 * keepAliveTime：线程池的工作线程空闲后，保持存活的时间。如果没有任务处理了，有些线程会空闲，空闲的时间超过了这个值，会被回收掉。如果任务很多，并且每个任务的执行时间比较短，避免线程重复创建和回收，可以调大这个时间，提高线程的利用率
 * unit：keepAliveTIme的时间单位，可以选择的单位有天、小时、分钟、毫秒、微妙、千分之一毫秒和纳秒。类型是一个枚举java.util.concurrent.TimeUnit，这个枚举也经常使用，有兴趣的可以看一下其源码
 * workQueue：工作队列，用于缓存待处理任务的阻塞队列，常见的有4种，本文后面有介绍
 * threadFactory：线程池中创建线程的工厂，可以通过线程工厂给每个创建出来的线程设置更有意义的名字
 * handler：饱和策略，当线程池无法处理新来的任务了，那么需要提供一种策略处理提交的新任务，默认有4种策略，文章后面会提到
 *
 * 线程池中常见5种工作队列
 * 任务太多的时候，工作队列用于暂时缓存待处理的任务，jdk中常见的5种阻塞队列：
 * ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按照先进先出原则对元素进行排序
 * LinkedBlockingQueue：是一个基于链表结构的阻塞队列，此队列按照先进先出排序元素，吞吐量通常要高于ArrayBlockingQueue。静态工厂方法Executors.newFixedThreadPool使用了这个队列。
 * SynchronousQueue ：一个不存储元素的阻塞队列，每个插入操作必须等到另外一个线程调用移除操作，否则插入操作一直处理阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool使用这个队列
 * PriorityBlockingQueue：优先级队列，进入队列的元素按照优先级会进行排序
 *
 * 4种常见饱和策略
 * 当线程池中队列已满，并且线程池已达到最大线程数，线程池会将任务传递给饱和策略进行处理。这些策略都实现了RejectedExecutionHandler接口。接口中有个方法：
 * void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
 *     参数说明：
 *     r：需要执行的任务
 *     executor：当前线程池对象
 * JDK中提供了4种常见的饱和策略:
 * AbortPolicy：直接抛出异常
 * CallerRunsPolicy：在当前调用者的线程中运行任务，即随丢来的任务，由他自己去处理
 * DiscardOldestPolicy：丢弃队列中最老的一个任务，即丢弃队列头部的一个任务，然后执行当前传入的任务
 * DiscardPolicy：不处理，直接丢弃掉，方法内部为空
 *
 * 线程池中的2个关闭方法：shutdown和shutdownNow，当调用者两个方法之后，线程池会遍历内部的工作线程，然后调用每个工作线程的interrrupt方法给线程发送中断信号，内部如果无法响应中断信号的可能永远无法终止，所以如果内部有无线循环的，最好在循环内部检测一下线程的中断信号，合理的退出。调用者两个方法中任意一个，线程池的isShutdown方法就会返回true，当所有的任务线程都关闭之后，才表示线程池关闭成功，这时调用isTerminaed方法会返回true。
 * 调用shutdown方法之后，线程池将不再接口新任务，内部会将所有已提交的任务处理完毕，处理完毕之后，工作线程自动退出。
 * 而调用shutdownNow方法后，线程池会将还未处理的（在队里等待处理的任务）任务移除，将正在处理中的处理完毕之后，工作线程自动退出。
 * 至于调用哪个方法来关闭线程，应该由提交到线程池的任务特性决定，多数情况下调用shutdown方法来关闭线程池，如果任务不一定要执行完，则可以调用shutdownNow方法。
 *
 * 线程池中线程数量的配置
 * 线程池中总线程大小对系统的性能有一定的影响，我们的目标是希望系统能够发挥最好的性能，过多或者过小的线程数量无法有效的使用机器的性能。在Java Concurrency in Practice书中给出了估算线程池大小的公式：
 * Ncpu = CUP的数量
 * Ucpu = 目标CPU的使用率，0<=Ucpu<=1
 * W/C = 等待时间与计算时间的比例
 * 为保存处理器达到期望的使用率，最有的线程池的大小等于：
 * Nthreads = Ncpu × Ucpu × (1+W/C)
 *
 * 线程池不允许使用Executors去创建，而要通过ThreadPoolExecutor方式，这一方面是由于jdk中Executor框架虽然提供了如newFixedThreadPool()、newSingleThreadExecutor()、newCachedThreadPool()等创建线程池的方法，但都有其局限性，不够灵活；
 */

public class ThreadPoolExecutorDemo {
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(100,
            100,
            10,
            TimeUnit.SECONDS,
            new PriorityBlockingQueue <Runnable>(),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int j = i;
            String taskName = "任务" + j;
            executor.execute(() -> {
                //模拟任务内部处理耗时
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + taskName + "处理完毕");
            });
        }
        //关闭线程池
        executor.shutdown();
    }
}
