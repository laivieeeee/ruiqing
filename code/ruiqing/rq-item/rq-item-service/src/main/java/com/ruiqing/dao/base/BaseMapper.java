package com.ruiqing.dao.base;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * DAO支持类实现
 * Created by zlb on 2018年1月22日
 */
@Repository
public interface BaseMapper<T> {

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T getById(String id);
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity);

	
	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);
	
	/**
	 * 查询数据列表(带数据权限过滤)
	 * @param entity
	 * @return
	 */
	public List<T> findAuthorityList(T entity);
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param id
	 * @see public int delete(T entity)
	 * @return
	 */
	public int deleteById(String id);
	
	/**
	 * 删除数据（逻辑删除，更新deleted字段为1,在表包含字段deleted时，可以调用此方法，将数据隐藏）
	 * @param id
	 * @see public int delete(T entity)
	 * @return
	 */
	public int deleteByLogic(String id);
	
	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param entity
	 * @return
	 */
	public int delete(T entity);
	
	/**
	 * 删除数据（逻辑删除，更新deleted字段为1,在表包含字段deleted时，可以调用此方法，将数据隐藏）
	 * @param entity
	 * @return
	 */
	public int deleteByLogic(T entity);
	
	/**
	 * 批量删除
	 * @param idList
	 */
	public int batchDelete(List<String> idList);
	
	@Select("${sql}")
	public List<Object> execSelectSql(@Param(value = "sql") String sql);

	@Update("${sql}")
	public void execUpdateSql(@Param(value = "sql") String sql);

	@Insert("${sql}")
	public void execInsertSql(@Param(value = "sql") String sql);

	@Delete("${sql}")
	public void execDeleteSql(@Param(value = "sql") String sql);
	
}