/**
 * Copyright &copy; 2012-2014 cnjson.attendance All rights reserved.
 */
package com.znbl.common.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * DAO支持类实现
 * @author Gray
 * @version 2014-05-16
 * @param <T>
 */
public interface CrudDao<T> extends BaseDao {

	/**
	 * Query to check the specific given column whether if existed.
	 * @param colName the given column name.
	 * @param colValue the given column name.
	 * @return more than zero if existed else less one.
	 */
	public int checkExisted(@Param("colName")String colName, @Param("colValue")String colValue);
	
	/**
	 * Ties to execute the SQL command by batch.
	 * @param sqlCommand the sql command text without "insert into" segment.
	 * such as after "insert into dd_student values" ....
	 */
	public void insertBatch(@Param("sqlCommand")String sqlCommand);
	
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id);
	
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T getBytitle(T entity);
	
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
	 * 查询所有数据列表
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity);
	
	/**
	 * 查询所有数据列表
	 * @see public List<T> findAllList(T entity)
	 * @return
	 */
	@Deprecated
	public List<T> findAllList();
	
	/**
	 * 获取主页面的数据
	 * @param entity
	 * @return
	 */
	public List<T> findListToFront(T entity);
	
	/**
	 * 获取轮播图片
	 * @param entity
	 * @return
	 */
	public List<T> getFrontPic(T entity);
	
	
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
	 * 记录审核
	 * @param entity
	 * @return
	 */
	public int examine(T entity);
	
	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param id
	 * @see public int delete(T entity)
	 * @return
	 */
	@Deprecated
	public int delete(String id);
	
	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param entity
	 * @return
	 */
	public int delete(T entity);
	
}