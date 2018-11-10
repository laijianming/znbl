/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.web;

import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.znbl.common.config.Global;
import com.znbl.common.web.BaseController;
import com.znbl.common.utils.StringUtils;
import com.znbl.modules.sys.entity.SysFileType;
import com.znbl.modules.sys.service.SysFileTypeService;

/**
 * 树结构生成Controller
 * @author Gray
 * @version 2016-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysFileType")
public class SysFileTypeController extends BaseController {

	@Autowired
	private SysFileTypeService sysFileTypeService;

	@ModelAttribute
	public SysFileType get(@RequestParam(required=false) String id) {
		SysFileType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysFileTypeService.get(id);
		}
		if (entity == null){
			entity = new SysFileType();
		}
		return entity;
	}

	@RequiresPermissions("sys:sysFileType:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysFileType sysFileType, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<SysFileType> list = sysFileTypeService.findList(sysFileType);
		model.addAttribute("list", list);
		return "modules/sys/sysFileTypeList";
	}

	@RequiresPermissions("sys:sysFileType:view")
	@RequestMapping(value = "form")
	public String form(SysFileType sysFileType, Model model) {
		if (sysFileType.getParent()!=null && StringUtils.isNotBlank(sysFileType.getParent().getId())){
			sysFileType.setParent(sysFileTypeService.get(sysFileType.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(sysFileType.getId())){
				SysFileType sysFileTypeChild = new SysFileType();
				sysFileTypeChild.setParent(new SysFileType(sysFileType.getParent().getId()));
				List<SysFileType> list = sysFileTypeService.findList(sysFileType);
				if (list.size() > 0){
					sysFileType.setSort(list.get(list.size()-1).getSort());
					if (sysFileType.getSort() != null){
						sysFileType.setSort(sysFileType.getSort() + 30);
					}
				}
			}
		}
		if (sysFileType.getSort() == null){
			sysFileType.setSort(30);
		}
		model.addAttribute("sysFileType", sysFileType);
		return "modules/sys/sysFileTypeForm";
	}

	@RequiresPermissions("sys:sysFileType:edit")
	@RequestMapping(value = "save")
	public String save(SysFileType sysFileType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysFileType)){
			return form(sysFileType, model);
		}
		sysFileTypeService.save(sysFileType);
		addMessage(redirectAttributes, "保存树结构成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysFileType/?repage";
	}

	@RequiresPermissions("sys:sysFileType:edit")
	@RequestMapping(value = "delete")
	public String delete(SysFileType sysFileType, RedirectAttributes redirectAttributes) {
		sysFileTypeService.delete(sysFileType);
		addMessage(redirectAttributes, "删除树结构成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysFileType/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SysFileType> list = sysFileTypeService.findList(new SysFileType());
		for (int i=0; i<list.size(); i++){
			SysFileType e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

}