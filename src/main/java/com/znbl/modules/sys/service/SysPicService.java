/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.znbl.common.persistence.Page;
import com.znbl.common.service.TreeService;
import com.znbl.common.utils.StringUtils;
import com.znbl.modules.sys.entity.SysPic;
import com.znbl.modules.sys.dao.SysPicDao;

/**
 * 树结构生成Service
 * @author Gray
 * @version 2016-04-05
 */
@Service
@Transactional(readOnly = true)
public class SysPicService extends TreeService<SysPicDao, SysPic> {

	public SysPic get(String id) {
		return super.get(id);
	}

	public List<SysPic> findByParentId(SysPic sysPic){
		return super.findList(sysPic);
	}
	
	/**
	 * 查询分页数据
	 * 
	 * @param page
	 *            分页对象
	 * @param entity
	 * @return
	 */
	public Page<SysPic> findPage(Page<SysPic> page,SysPic entity) {
		entity.setPage(page);
		List<SysPic> list = dao.findList(entity);
		for (SysPic sp:list) {
			sp.setPicUrl(sp.getPicUrl().replaceAll("\\\\", "/"));
		}
		page.setList(list);
		return page;
	}
	
	public List<SysPic> findList(SysPic sysPic) {
		if (StringUtils.isNotBlank(sysPic.getParentIds())){
			sysPic.setParentIds(","+sysPic.getParentIds()+",");
		}
		return super.findList(sysPic);
	}

	@Transactional(readOnly = false)
	public void save(SysPic sysPic) {
		super.save(sysPic);
	}

	@Transactional(readOnly = false)
	public void delete(SysPic sysPic) {
		super.delete(sysPic);
	}

}