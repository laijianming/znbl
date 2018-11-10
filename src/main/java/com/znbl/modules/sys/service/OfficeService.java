/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.znbl.common.service.TreeService;
import com.znbl.common.utils.StringUtils;
import com.znbl.modules.sys.dao.OfficeDao;
import com.znbl.modules.sys.entity.Office;

/**
 * 树结构生成Service
 * @author cgli
 * @version 2016-02-29
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {

	public Office get(String id) {
		return super.get(id);
	}

	public List<Office> findList(Office office) {
		if (StringUtils.isNotBlank(office.getParentIds())){
			office.setParentIds(","+office.getParentIds()+",");
		}
		return super.findList(office);
	}

	@Transactional(readOnly = false)
	public void save(Office office) {
		super.save(office);
	}

	@Transactional(readOnly = false)
	public void delete(Office office) {
		super.delete(office);
	}

}