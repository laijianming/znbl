package com.znbl.front.interest.list.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.znbl.common.persistence.Page;
import com.znbl.common.utils.StringUtils;
import com.znbl.common.web.BaseController;
import com.znbl.modules.interest.entity.Comic;
import com.znbl.modules.interest.service.ComicService;
import com.znbl.modules.sys.service.SystemService;

/**
 * 动漫板块话题列表Controller
 * @author Gray
 * @version 2017年7月29日
 */
@Controller
@RequestMapping(value="/interest")
public class FrontComicListController extends BaseController{
	
	
	@Autowired
	private ComicService comicService;
	@Autowired
	private SystemService systemService;
	
	//设置页面大小
	private final int pageSize = 2;
	
	@ModelAttribute
	public Comic get(@RequestParam(required = false) String id) {
		Comic entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = comicService.get(id);
		}
		if (entity == null) {
			entity = new Comic();
		}
		return entity;
	}
	
	/**
	 * 列表分页与查找
	 * @param comic
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="/comic/signuplist/",method=RequestMethod.POST)
	public String index(Comic comic,HttpServletRequest request, HttpServletResponse response, Model model) {
		//Page<Comic> page = comicService.findPage(new Page<Comic>(request, response), comic);
		Page<Comic> page = new Page<Comic>(request, response);
		//设置页面大小
		page.setPageSize(pageSize);
		page=comicService.findPage(page,comic);
		model.addAttribute("page", page);
		return "front/interest/list/comicSpList";
	}
	
	
	/**
	 * 跳转聊天页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/comic/talk/",method=RequestMethod.GET)
	public String talkPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "front/interest/chatRoom";
	}

}
