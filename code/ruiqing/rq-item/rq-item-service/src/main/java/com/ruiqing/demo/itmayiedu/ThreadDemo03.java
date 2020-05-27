
package com.ruiqing.demo.itmayiedu;

class CreateRunnable  implements Runnable {

	/**
	 * run方法 执行 线程需要执行的任务 执行的代码
	 */
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("run() i:" + i);
		}

	}

}

/**
 * 
 * @classDesc: 功能描述:()
 * @author: 余胜军
 * @createTime: 2017年8月19日 下午3:58:21
 * @version: v1.0
 * @copyright:上海每特教育科技有限公司
 */
public class ThreadDemo03 {

	public static void main(String[] args) {
		// 第一个类，实现Runnable接口 重写run方法
		System.out.println("创建线程开始! main");
		CreateRunnable createRunnable = new CreateRunnable();
		Thread thread = new Thread(createRunnable);
		thread.start();
		System.out.println("线程已经启动 main");
		for (int i = 0; i < 100; i++) {
			System.out.println("main() i:" + i);
		}
	}

}
