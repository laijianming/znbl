/**
 * Copyright &copy; 2012-2014 cnjson.attendance All rights reserved.
 */
package com.znbl.modules.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.znbl.common.service.CrudService;
import com.znbl.common.utils.CacheUtils;
import com.znbl.modules.sys.dao.DictDao;
import com.znbl.modules.sys.entity.Dict;
import com.znbl.modules.sys.utils.DictUtils;

/**
 * 字典Service
 * @author Gray
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
	
	@Autowired
	private DictDao dictDao;
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	public List<Dict> findByMap(Map map){
		return dictDao.findByMap(map);
	}
}
