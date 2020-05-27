
package com.ruiqing.demo;

import java.util.ArrayList;
import java.util.List;


class UserThread extends Thread {
	private List<UserEntity> list;

	/**
	 * 通过构造函数 传入每个线程需要执行的发送短信内容
	 * 
	 * @param list
	 */
	public UserThread(List<UserEntity> list) {
		this.list = list;
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(分批发送短信)
	 * @author: 余胜军
	 * @param:
	 * @createTime:2017年8月7日 下午9:09:54
	 * @returnType: void
	 * @copyright:上海每特教育科技有限公司
	 */
	@Override
	public void run() {
		for (UserEntity userEntity : list) {
			System.out.println("threadName:" + Thread.currentThread().getName() + "-学员编号:" + userEntity.getUserId()
					+ "---学员名称:" + userEntity.getUserName());
			// 调用发送短信具体代码
		}
	}

}

/**
 * 
 * @classDesc: 功能描述:(使用多线程技术分批发送短信)
 * @author: 余胜军
 * @createTime: 2017年8月7日 下午9:02:29
 * @version: v1.0
 * @copyright:上海每特教育科技有限公司
 */
public class SendThread {

	/**
	 * 
	 * @methodDesc: 功能描述:(初始化数据)
	 * @author: 余胜军
	 * @param:
	 * @createTime:2017年8月7日 下午9:16:53
	 * @returnType: void
	 * @copyright:上海每特教育科技有限公司
	 */
	public static List<UserEntity> init() {
		List<UserEntity> list = new ArrayList<UserEntity>();
		for (int i = 1; i <= 140; i++) {
			UserEntity userEntity = new UserEntity();
			userEntity.setUserId("userId" + i);
			userEntity.setUserName("userName" + i);
			list.add(userEntity);
		}
		return list;

	}

	public static void main(String[] args) {
		// 1.初始化用户数据
		List<UserEntity> listUserEntity = init();
		// 2.计算创建创建多少个线程并且每一个线程需要执行“分批发送短信用户”
		// 每一个线程分批跑多少
		int userThreadPage = 50;
		// 计算所有线程数
		List<List<UserEntity>> splitUserList = ListUtils.splitList(listUserEntity, userThreadPage);
		int threadSaze = splitUserList.size();
		for (int i = 0; i < threadSaze; i++) {
			List<UserEntity> list = splitUserList.get(i);
			UserThread userThread = new UserThread(list);
			// 3.执行任务发送短信
			userThread.start();
		}

	}

}
