/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.znbl.common.service.TreeService;
import com.znbl.common.utils.StringUtils;
import com.znbl.modules.sys.entity.SysFileType;
import com.znbl.modules.sys.dao.SysFileTypeDao;

/**
 * 树结构生成Service
 * @author Gray
 * @version 2016-04-05
 */
@Service
@Transactional(readOnly = true)
public class SysFileTypeService extends TreeService<SysFileTypeDao, SysFileType> {

	public SysFileType get(String id) {
		return super.get(id);
	}

	public List<SysFileType> findList(SysFileType sysFileType) {
		if (StringUtils.isNotBlank(sysFileType.getParentIds())){
			sysFileType.setParentIds(","+sysFileType.getParentIds()+",");
		}
		return super.findList(sysFileType);
	}

	@Transactional(readOnly = false)
	public void save(SysFileType sysFileType) {
		super.save(sysFileType);
	}

	@Transactional(readOnly = false)
	public void delete(SysFileType sysFileType) {
		super.delete(sysFileType);
	}

}