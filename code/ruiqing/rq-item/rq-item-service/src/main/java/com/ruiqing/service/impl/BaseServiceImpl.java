package com.ruiqing.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruiqing.common.utils.SpringUtils;
import com.ruiqing.dao.base.BaseMapper;
import com.ruiqing.dto.base.CommonPageDTO;
import com.ruiqing.entity.BaseEntity;
import com.ruiqing.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 服务层操作基类
 * @author ZHUANGLIBO1
 *
 * @param <M>
 * @param <T>
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> implements BaseService<T> {

	@Autowired
	protected M mapper;

	@Override
	public T getById(String id) throws Exception {
		return mapper.getById(id);
	}

	@Override
	public int insert(T t) throws Exception {
		return mapper.insert(t);
	}

	@Override
	public int deleteById(String id) throws Exception {
		return mapper.deleteById(id);
	}

	@Override
	public int deleteBatch(List<String> list) throws Exception {
		return mapper.batchDelete(list);
	}

	@Override
	public int update(T t) throws Exception {
		return mapper.update(t);
	}

	@Override
	public CommonPageDTO findPage(T t) throws Exception {
		CommonPageDTO commonPageDTO=t.getCommonPageDTO();
		Page<?> page= PageHelper.startPage(commonPageDTO.getCurrentPage(), commonPageDTO.getPageSize());
		commonPageDTO.setRecordList(findList(t));
		commonPageDTO.initiatePage(page.getTotal());
		return commonPageDTO;
	}

	@Override
	public List<T> findList(T t) throws Exception {
		return mapper.findList(t);
	}
	
	/**
	 * 从beanFactory中取得当前Service对象，
	 * 用于解决同一个Service类中的方法调用，如果最外层方法不加@Transactional注解事务,则里面被调用方法的@Transactional注解事务不起作用的问题
	 * @author: chenxuanzhuang1
	 * @date: 2018年12月10日
	 * @return
	 */
	public BaseService<?> getService(){
		return SpringUtils.getBean(this.getClass());
	}
	
}
