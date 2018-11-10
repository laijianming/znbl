/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.znbl.common.service.TreeService;
import com.znbl.common.utils.StringUtils;
import com.znbl.modules.sys.dao.AreaDao;
import com.znbl.modules.sys.entity.Area;
import com.znbl.modules.sys.utils.UserUtils;

/**
 * 树结构生成Service
 * @author cgli
 * @version 2016-02-29
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public Area get(String id) {
		return super.get(id);
	}
	
	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}


	public List<Area> findList(Area area) {
		if (StringUtils.isNotBlank(area.getParentIds())){
			area.setParentIds(","+area.getParentIds()+",");
		}
		return super.findList(area);
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}

	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}

}