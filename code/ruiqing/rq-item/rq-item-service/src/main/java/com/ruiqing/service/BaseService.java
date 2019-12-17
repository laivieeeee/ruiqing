package com.ruiqing.service;


import com.ruiqing.dto.base.CommonPageDTO;

import java.util.List;

/**
 * 
 * @author wanghuaiwei1
 * @since 2019-01-22
 *
 */
public interface BaseService<T> {
	/**
	 * 根据ID查询对象
	 * 
	 * @param id
	 * @return DTO
	 * 
	 */
	public T getById(String id) throws Exception;
	
	/**
	 * 保存
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int insert(T t) throws Exception;

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 * @return 影响条数
	 * 
	 */
	public int deleteById(String id) throws Exception;

	/**
	 * 根据ID集合批量删除对象
	 * 
	 * @param list
	 * @return 影响条数
	 * 
	 */
	public int deleteBatch(List<String> list) throws Exception;
	
	/**
	 * 修改
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int update(T t) throws Exception;
	
	
		/**
	 * 根据条件查询list(分页查询)
	 * 
	 * @param T
	 * @return
	 * @throws Exception
	 */
	public CommonPageDTO findPage(T t) throws Exception;
	
		/**
	 * 根据条件查询list(不分页查询)
	 * 
	 * @param T
	 * @return List<DTO>
	 */
	public List<T> findList(T t) throws Exception;
	
}
