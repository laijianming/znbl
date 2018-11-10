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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.znbl.common.config.Global;
import com.znbl.common.persistence.Page;
import com.znbl.common.web.BaseController;
import com.znbl.common.utils.StringUtils;

import com.znbl.modules.sys.entity.SysMessage;
import com.znbl.modules.sys.service.SysMessageService;

/**
 * 平台留言Controller
 * @author Gray
 * @version 2016-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysMessage")
public class SysMessageController extends BaseController {

	@Autowired
	private SysMessageService sysMessageService;

	@ModelAttribute
	public SysMessage get(@RequestParam(required=false) String id) {
		SysMessage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysMessageService.get(id);
		}
		if (entity == null){
			entity = new SysMessage();
		}
		return entity;
	}

	@RequiresPermissions("sys:sysMessage:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysMessage sysMessage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysMessage> page = sysMessageService.findPage(new Page<SysMessage>(request, response), sysMessage);
		model.addAttribute("page", page);
		return "modules/sys/sysMessageList";
	}

	@RequiresPermissions("sys:sysMessage:view")
	@RequestMapping(value = "form")
	public String form(SysMessage sysMessage, Model model) {
		model.addAttribute("sysMessage", sysMessage);
		return "modules/sys/sysMessageForm";
	}

	@RequiresPermissions("sys:sysMessage:edit")
	@RequestMapping(value = "save")
	public String save(SysMessage sysMessage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysMessage)){
			return form(sysMessage, model);
		}
		sysMessageService.save(sysMessage);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysMessage/?repage";
	}

	@RequiresPermissions("sys:sysMessage:edit")
	@RequestMapping(value = "delete")
	public String delete(SysMessage sysMessage, RedirectAttributes redirectAttributes) {
		sysMessageService.delete(sysMessage);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysMessage/?repage";
	}

}