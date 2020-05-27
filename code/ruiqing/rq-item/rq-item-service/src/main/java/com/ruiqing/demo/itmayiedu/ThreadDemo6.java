
package com.ruiqing.demo.itmayiedu;

class DemoThread3 implements Runnable {
	// 在run方法当中不能抛出异常，只能try
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			// sleep 是毫秒
			try {
				// sleep作用让当先线程从运行状态变为休眠状态，如果时间到期有会到运行状态。
				// sleep 不能释放锁，多线程之间实现同步
				Thread.sleep(1000);
				// 获取到线程的ID ID是多线程随机进行分配不重复主键。

			} catch (Exception e) {
				// TODO: handle exception
			}
			// System.out.println("id:"+getId()+"name:"+getName()+"-i:" + i);
			// Thread.currentThread() 获取当前线程对象
			System.out.println("id:" + Thread.currentThread().getId() + "-i:" + i);
		}
	}
}

public class ThreadDemo6 {

	public static void main(String[] args) {
		DemoThread3 demoThread3 = new DemoThread3();
		Thread thread = new Thread(demoThread3);
		thread.start();
		// DemoThread3 demoThread = new DemoThread3();
		// demoThread.setName("线程①");
		// DemoThread3 demoThread2 = new DemoThread3();
		// demoThread2.setName("线程②");
		// demoThread.start();
		// demoThread2.start();
	}

}
