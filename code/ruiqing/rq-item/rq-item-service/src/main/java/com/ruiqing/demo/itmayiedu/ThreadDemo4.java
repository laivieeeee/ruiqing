
package com.ruiqing.demo.itmayiedu;

/**
 * 
 * @classDesc: 功能描述:(使用匿名内部类方式创建线程)
 * @author: 余胜军
 * @createTime: 2017年8月19日 下午4:11:14
 * @version: v1.0
 * @copyright:上海每特教育科技有限公司
 */
public class ThreadDemo4 {

	public static void main(String[] args) {
		System.out.println("创建线成功！");
		//使用匿名内部类方式创建线程
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("run() i:" + i);
				}
			}
		}).start();

		System.out.println("创建线結束！");
//		thread.start();
		for (int i = 0; i < 100; i++) {
			System.out.println("main() i:" + i);
		}
	}

}
