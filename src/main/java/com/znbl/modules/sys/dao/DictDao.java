/**
 * Copyright &copy; 2012-2014 cnjson.attendance All rights reserved.
 */
package com.znbl.modules.sys.dao;

import java.util.List;
import java.util.Map;

import com.znbl.common.persistence.CrudDao;
import com.znbl.common.persistence.annotation.MyBatisDao;
import com.znbl.modules.sys.entity.Dict;

/**
 * 字典DAO接口
 * @author Gray
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
	
	public List<Dict> findByMap(Map map);
	
}
