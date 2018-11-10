/**
 * Copyright &copy; 2012-2014 cnjson.attendance All rights reserved.
 */
package com.znbl.modules.sys.dao;

import com.znbl.common.persistence.CrudDao;
import com.znbl.common.persistence.annotation.MyBatisDao;
import com.znbl.modules.sys.entity.Log;

/**
 * 日志DAO接口
 * @author Gray
 * @version 2014-05-16
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
