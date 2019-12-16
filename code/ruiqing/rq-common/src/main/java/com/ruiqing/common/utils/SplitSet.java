package com.ruiqing.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ExcelUtil
 * @Description: excel文件操作工具类
 * 
 */
public class SplitSet {
	/**
	 * 按指定大小，分隔集合，将集合按规定个数分为n个部分
	 * 
	 * @param list
	 * @param len
	 * @return
	 */
	public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
		List<List<T>> listArray = new ArrayList<List<T>>(); // 创建list数组

		if (list.size() != 0) {
			// 零售客户数量
			int len = list.size();
			// 一个线程的零售客户数量
			int limit = pageSize;

			// 线程个数
			int pages = 0;

			if (len % limit == 0) {
				pages = len / limit;
			} else {
				pages = len / limit + 1;
			}

			for (int page = 0; page < pages; page++) {
				int max = (page + 1) * limit;
				int start = page * limit;
				if (max > len) {
					List<T> list1 = list.subList(start, len);
					listArray.add(list1);
				} else {
					List<T> list1 = list.subList(start, max);
					listArray.add(list1);
				}
			}

		}

		return listArray;
	}
}