/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.znbl.common.persistence.Page;
import com.znbl.common.service.CrudService;
import com.znbl.modules.sys.entity.SysMessage;
import com.znbl.modules.sys.dao.SysMessageDao;

/**
 * 单表生成Service
 * @author Gray
 * @version 2016-04-05
 */
@Service
@Transactional(readOnly = true)
public class SysMessageService extends CrudService<SysMessageDao, SysMessage> {

	public SysMessage get(String id) {
		return super.get(id);
	}

	public List<SysMessage> findList(SysMessage sysMessage) {
		return super.findList(sysMessage);
	}

	public Page<SysMessage> findPage(Page<SysMessage> page, SysMessage sysMessage) {
		return super.findPage(page, sysMessage);
	}

	@Transactional(readOnly = false)
	public void save(SysMessage sysMessage) {
		super.save(sysMessage);
	}

	@Transactional(readOnly = false)
	public void delete(SysMessage sysMessage) {
		super.delete(sysMessage);
	}

}