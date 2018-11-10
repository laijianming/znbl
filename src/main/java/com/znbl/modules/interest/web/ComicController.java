package com.znbl.modules.interest.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.znbl.common.persistence.Page;
import com.znbl.common.utils.StringUtils;
import com.znbl.common.web.BaseController;
import com.znbl.modules.interest.entity.Comic;
import com.znbl.modules.interest.service.ComicService;
import com.znbl.modules.sys.service.SystemService;

@Controller
@RequestMapping(value = "${adminPath}/interest/comic")
public class ComicController extends BaseController{
	
	@Autowired
	private ComicService comicService;
	@Autowired
	private SystemService systemService;
	
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
	
	@RequiresPermissions("interest:comic:view")
	@RequestMapping(value = { "adminlist", "" })
	public String list(Comic comic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Comic> page = comicService.findPage(new Page<Comic>(request, response), comic);
		model.addAttribute("page", page);
		return "modules/interest/comicList";
	}
	
	
}
