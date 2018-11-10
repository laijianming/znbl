/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.dao;

import com.znbl.common.persistence.CrudDao;
import com.znbl.common.persistence.annotation.MyBatisDao;
import com.znbl.modules.sys.entity.SysFile;

/**
 * 单表生成DAO接口
 * @author Gray
 * @version 2016-04-05
 */
@MyBatisDao
public interface SysFileDao extends CrudDao<SysFile> {

}