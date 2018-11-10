/**
 * Copyright &copy; 2012-2014 cnjson.attendance All rights reserved.
 */
package com.znbl.modules.sys.dao;

import java.util.List;

import com.znbl.common.persistence.CrudDao;
import com.znbl.common.persistence.annotation.MyBatisDao;
import com.znbl.modules.sys.entity.Menu;

/**
 * 菜单DAO接口
 * @author Gray
 * @version 2014-05-16
 */
@MyBatisDao
public interface MenuDao extends CrudDao<Menu> {

	public List<Menu> findByParentIdsLike(Menu menu);

	public List<Menu> findByUserId(Menu menu);
	
	public int updateParentIds(Menu menu);
	
	public int updateSort(Menu menu);
	
}
