package com.znbl.modules.interest.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.znbl.common.persistence.Page;
import com.znbl.common.service.CrudService;
import com.znbl.modules.interest.dao.ComicDao;
import com.znbl.modules.interest.entity.Comic;

/**
 * 漫画兴趣Service
 * @author Gray
 *
 */
@Service
@Transactional(readOnly = true)
public class ComicService extends CrudService<ComicDao, Comic>{
	
	public Comic get(String id) {
		return super.get(id);
	}

	public List<Comic> findList(Comic comic) {
		return super.findList(comic);
	}

	public Page<Comic> findPage(Page<Comic> page, Comic comic) {
		return super.findPage(page, comic);
	}

	@Transactional(readOnly = false)
	public void save(Comic comic) {
		super.save(comic);
	}

	@Transactional(readOnly = false)
	public void delete(Comic comic) {
		super.delete(comic);
	}
	
	/**
	 * 点击数加一
	 */
	@Transactional(readOnly = false)
	public void updateHitsAddOne(String id) {
		dao.updateHitsAddOne(id);
	}

}
