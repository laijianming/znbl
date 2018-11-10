package com.znbl.modules.interest.dao;

import com.znbl.common.persistence.CrudDao;
import com.znbl.common.persistence.annotation.MyBatisDao;
import com.znbl.modules.interest.entity.Comic;

/**
 * 动漫兴趣dao
 * @author Gray
 *
 */
@MyBatisDao
public interface ComicDao extends CrudDao<Comic>{
	
	public int updateHitsAddOne(String id);

}
