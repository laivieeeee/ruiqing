
package com.ruiqing.demo.itmayiedu;

class ThreadTrain12 implements Runnable {
	// 定义火车票总数
	static int trainCount = 100;
	private Object oj = new Object();
	public boolean flag = true;

	@Override
	public void run() {
		if (flag) {
			while (trainCount > 0) {

				synchronized (oj) {
					// 如果flag为true 先拿到obj 这把锁 才能进入show方法， 进入show方法后需要this锁。
					// 如果flag为false 先拿到this 这把锁 ，才能进入show方法，进入到show方法  obj这把锁。
					// 死锁的原因就是 同步中嵌套了同步
					show();
				}

			}
		} else {
			while (trainCount > 0) {
				show();
			}
		}

	}

	public synchronized void show() {
		//
		synchronized (oj) {
			if (trainCount > 0) {
				try {
					Thread.sleep(40);
				} catch (Exception e) {

				}
				System.out.println(Thread.currentThread().getName() + ",出售第" + (100 - trainCount + 1) + "票");
				trainCount--;
			}
		}
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
public class ThreadDemo11 {
	public static void main(String[] args) throws InterruptedException {
		// 定义一个实例
		ThreadTrain12 threadTrain10 = new ThreadTrain12();
		Thread thread1 = new Thread(threadTrain10, "一号窗口");
		Thread thread2 = new Thread(threadTrain10, "二号窗口");
		thread1.start();
		Thread.sleep(40);
		threadTrain10.flag = false;
		thread2.start();
	}
}
