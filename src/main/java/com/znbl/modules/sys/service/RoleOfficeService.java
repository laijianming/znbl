/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.znbl.common.persistence.Page;
import com.znbl.common.service.CrudService;
import com.znbl.modules.sys.dao.RoleOfficeDao;
import com.znbl.modules.sys.entity.RoleOffice;

/**
 * 单表生成Service
 * @author cgli
 * @version 2016-02-29
 */
@Service
@Transactional(readOnly = true)
public class RoleOfficeService extends CrudService<RoleOfficeDao, RoleOffice> {

	public RoleOffice get(String id) {
		return super.get(id);
	}

	public List<RoleOffice> findList(RoleOffice roleOffice) {
		return super.findList(roleOffice);
	}

	public Page<RoleOffice> findPage(Page<RoleOffice> page, RoleOffice roleOffice) {
		return super.findPage(page, roleOffice);
	}

	@Transactional(readOnly = false)
	public void save(RoleOffice roleOffice) {
		super.save(roleOffice);
	}

	@Transactional(readOnly = false)
	public void delete(RoleOffice roleOffice) {
		super.delete(roleOffice);
	}

}