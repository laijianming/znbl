/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.znbl.common.persistence.Page;
import com.znbl.common.service.CrudService;
import com.znbl.modules.sys.entity.SysFile;
import com.znbl.modules.sys.dao.SysFileDao;

/**
 * 单表生成Service
 * @author Gray
 * @version 2016-04-05
 */
@Service
@Transactional(readOnly = true)
public class SysFileService extends CrudService<SysFileDao, SysFile> {

	public SysFile get(String id) {
		return super.get(id);
	}

	public List<SysFile> findList(SysFile sysFile) {
		return super.findList(sysFile);
	}

	public Page<SysFile> findPage(Page<SysFile> page, SysFile sysFile) {
		return super.findPage(page, sysFile);
	}

	@Transactional(readOnly = false)
	public void save(SysFile sysFile) {
		super.save(sysFile);
	}

	@Transactional(readOnly = false)
	public void delete(SysFile sysFile) {
		super.delete(sysFile);
	}

}