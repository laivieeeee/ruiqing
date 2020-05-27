
package com.ruiqing.demo.itmayiedu;

class DemoThread23 implements Runnable {
	private static int trainCount = 100;
	private Object oj = new Object();
	public static boolean flag = true;

	@Override
	public void run() {
		if (flag) {
			while (trainCount > 0) {
				synchronized (oj) {
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
		synchronized (oj) {
			if (trainCount > 0) {
				try {
					Thread.sleep(40);
				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println(Thread.currentThread().getName() + "出售火车票第" + (100 - trainCount + 1) + "张票");
				trainCount--;
			}
		}
	}

}

public class Thread22 {

	public static void main(String[] args) throws InterruptedException {
		// 定义一个实例
		DemoThread23 demoThread23 = new DemoThread23();
		Thread thread1 = new Thread(demoThread23, "一号窗口");
		Thread thread2 = new Thread(demoThread23, "二号窗口");
		thread1.start();
		Thread.sleep(40);
		DemoThread23.flag = false;
		thread2.start();
	}

}
