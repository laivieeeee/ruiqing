
package com.ruiqing.demo.itmayiedu;

class ThreadTrain8 implements Runnable {
	// 定义火车票总数
	int trainCount = 100;
	private Object oj = new Object();

	@Override
	public void run() {

		while (trainCount > 0) {
			// try {
			// Thread.sleep(40);
			// } catch (Exception e) {
			// }
			show();
		}
	}

	public synchronized void show() {
		//
		// synchronized (oj) {
		if (trainCount > 0) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {

			}
			System.out.println(Thread.currentThread().getName() + ",出售第" + (100 - trainCount + 1) + "票");
			trainCount--;
		}
		// }
	}

}

/**
 * 
 * @classDesc: 功能描述:()
 * @author: 余胜军
 * @createTime: 2017年8月19日 下午6:41:58
 * @version: v1.0
 * @copyright:上海每特教育科技有限公司
 */
public class ThreadDemo8 {
	public static void main(String[] args) {
		// 定义一个实例
		ThreadTrain8 threadTrain8 = new ThreadTrain8();
		Thread thread1 = new Thread(threadTrain8);
		thread1.setName("售票窗口①");
		Thread thread2 = new Thread(threadTrain8);
		thread2.setName("售票窗口②");
		thread1.start();
		thread2.start();
	}
}
