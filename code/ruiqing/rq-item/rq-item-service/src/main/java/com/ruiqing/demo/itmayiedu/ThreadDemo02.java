
package com.ruiqing.demo.itmayiedu;

class CreateThread extends Thread {

	/**
	 * run方法执行 需要线程执行的任务，代码。
	 */
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("run() i:" + i);
		}
	}

}

/**
 * 
 * @classDesc: 功能描述:(创建多线程第一种继承Thread类 重写run方法)
 * @author: 余胜军
 * @createTime: 2017年8月19日 下午3:03:21
 * @version: v1.0
 * @copyright:上海每特教育科技有限公司
 */
public class ThreadDemo02 {

	public static void main(String[] args) {
		System.out.println("创建线程开始  main");
		// 1.定义一个类，继承Thread类 重写run方法。
		// 2. 如何启动线程？
		CreateThread createThread = new CreateThread();
		// 启动一个线程 是调用start方法 不是run方法。 调用run方法相当于主线程执行。
		// 注意 使用多线程情况，代码不会重写往下进行执行。会同时并行执行。
		createThread.start();
		System.out.println("线程已经开始启动 main");
		for (int i = 0; i < 100; i++) {
			System.out.println("main() i:" + i);
		}

	}

}
