/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.znbl.common.config.Global;
import com.znbl.common.persistence.Page;
import com.znbl.common.utils.StringUtils;
import com.znbl.common.web.BaseController;
import com.znbl.modules.sys.entity.RoleOffice;
import com.znbl.modules.sys.service.RoleOfficeService;

/**
 * 单表生成Controller
 * @author cgli
 * @version 2016-02-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/roleOffice")
public class RoleOfficeController extends BaseController {

	@Autowired
	private RoleOfficeService roleOfficeService;

	@ModelAttribute
	public RoleOffice get(@RequestParam(required=false) String id) {
		RoleOffice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = roleOfficeService.get(id);
		}
		if (entity == null){
			entity = new RoleOffice();
		}
		return entity;
	}

	@RequiresPermissions("sys:roleOffice:view")
	@RequestMapping(value = {"list", ""})
	public String list(RoleOffice roleOffice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RoleOffice> page = roleOfficeService.findPage(new Page<RoleOffice>(request, response), roleOffice);
		model.addAttribute("page", page);
		return "modules/sys/roleOfficeList";
	}

	@RequiresPermissions("sys:roleOffice:view")
	@RequestMapping(value = "form")
	public String form(RoleOffice roleOffice, Model model) {
		model.addAttribute("roleOffice", roleOffice);
		return "modules/sys/roleOfficeForm";
	}

	@RequiresPermissions("sys:roleOffice:edit")
	@RequestMapping(value = "save")
	public String save(RoleOffice roleOffice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, roleOffice)){
			return form(roleOffice, model);
		}
		roleOfficeService.save(roleOffice);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/roleOffice/?repage";
	}

	@RequiresPermissions("sys:roleOffice:edit")
	@RequestMapping(value = "delete")
	public String delete(RoleOffice roleOffice, RedirectAttributes redirectAttributes) {
		roleOfficeService.delete(roleOffice);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/roleOffice/?repage";
	}

}