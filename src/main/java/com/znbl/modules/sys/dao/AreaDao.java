/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.dao;

import com.znbl.common.persistence.TreeDao;
import com.znbl.common.persistence.annotation.MyBatisDao;
import com.znbl.modules.sys.entity.Area;

/**
 * 树结构生成DAO接口
 * @author cgli
 * @version 2016-02-29
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {

}