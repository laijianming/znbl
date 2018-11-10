/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.dao;

import com.znbl.common.persistence.TreeDao;
import com.znbl.common.persistence.annotation.MyBatisDao;
import com.znbl.modules.sys.entity.SysPic;

/**
 * 树结构生成DAO接口
 * @author Gray
 * @version 2016-04-05
 */
@MyBatisDao
public interface SysPicDao extends TreeDao<SysPic> {

}