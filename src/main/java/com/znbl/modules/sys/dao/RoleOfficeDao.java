/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.dao;

import com.znbl.common.persistence.CrudDao;
import com.znbl.common.persistence.annotation.MyBatisDao;
import com.znbl.modules.sys.entity.RoleOffice;

/**
 * 单表生成DAO接口
 * @author cgli
 * @version 2016-02-29
 */
@MyBatisDao
public interface RoleOfficeDao extends CrudDao<RoleOffice> {

}