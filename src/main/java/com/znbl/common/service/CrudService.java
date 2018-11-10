/**
 * Copyright &copy; 2012-2014 cnjson.attendance All rights reserved.
 */
package com.znbl.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.znbl.common.persistence.CrudDao;
import com.znbl.common.persistence.DataEntity;
import com.znbl.common.persistence.Page;

/**
 * Service基类
 * 
 * @author Gray
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	public boolean checkExisted(String colName, String colValue) {
		int ret = dao.checkExisted(colName, colValue);
		return (ret > 0);
	}

	@Transactional(readOnly = false)
	public void insertBatch(String sqlCommand) {
		dao.insertBatch(sqlCommand);
	}

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return dao.get(id);
	}

	/**
	 * 获取单条数据
	 * 
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.get(entity);
	}
	
	/**
	 * 获取单条数据
	 * 
	 * @param entity
	 * @return
	 */
	public T getBytitle(T entity) {
		return dao.getBytitle(entity);
	}
	
	/**
	 * 查询列表数据
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}

	/**
	 * 查询列表所有数据
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findAllList() {
		return dao.findAllList();
	}
	
	/**
	 * 获取主页面的数据
	 * @param entity
	 * @return
	 */
	public List<T> findListToFront(T entity){
		return dao.findListToFront(entity);
		
	}
	
	/**
	 * 获取轮播图片
	 * @param entity
	 * @return
	 */
	public List<T> getFrontPic(T entity){
		return dao.getFrontPic(entity);		
	}

	/**
	 * 查询分页数据
	 * 
	 * @param page
	 *            分页对象
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}

	/**
	 * 保存数据（插入或更新）
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getIsNewRecord()) {
			entity.preInsert();
			dao.insert(entity);
		} else {
			entity.preUpdate();
			dao.update(entity);
		}
	}

	/**
	 * 保存数据（指定自己生成的UUID插入） 
	 * 此处UUID不能为空
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity, String uuId) {
		if (uuId != null && uuId != "") {
			entity.exactInsert(uuId);
			dao.insert(entity);
			System.out.println("指定ID插入成功");
		}
	}

	/**
	 * 删除数据
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}
	
	/**
	 * 审核数据
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public int examine(T entity) {
		return dao.examine(entity);
	}

}
