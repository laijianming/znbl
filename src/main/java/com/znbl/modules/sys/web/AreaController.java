/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.web;

import java.util.List;
import java.util.Map;

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

import com.znbl.common.utils.StringUtils;
import com.znbl.common.web.BaseController;
import com.znbl.modules.sys.entity.Area;
import com.znbl.modules.sys.service.AreaService;
import com.znbl.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 树结构生成Controller
 * @author cgli
 * @version 2016-02-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/area")
public class AreaController extends BaseController {

//	@Autowired
//	private AreaService areaService;
//
//	@ModelAttribute
//	public Area get(@RequestParam(required=false) String id) {
//		Area entity = null;
//		if (StringUtils.isNotBlank(id)){
//			entity = areaService.get(id);
//		}
//		if (entity == null){
//			entity = new Area();
//		}
//		return entity;
//	}
//
//	@RequiresPermissions("sys:area:view")
//	@RequestMapping(value = {"list", ""})
//	public String list(Area area, HttpServletRequest request, HttpServletResponse response, Model model) {
//		List<Area> list = areaService.findList(area);
//		model.addAttribute("list", list);
//		return "modules/sys/areaList";
//	}
//
//	@RequiresPermissions("sys:area:view")
//	@RequestMapping(value = "form")
//	public String form(Area area, Model model) {
//		if (area.getParent()!=null && StringUtils.isNotBlank(area.getParent().getId())){
//			area.setParent(areaService.get(area.getParent().getId()));
//			// 获取排序号，最末节点排序号+30
//			if (StringUtils.isBlank(area.getId())){
//				Area areaChild = new Area();
//				areaChild.setParent(new Area(area.getParent().getId()));
//				List<Area> list = areaService.findList(area);
//				if (list.size() > 0){
//					area.setSort(list.get(list.size()-1).getSort());
//					if (area.getSort() != null){
//						area.setSort(area.getSort() + 30);
//					}
//				}
//			}
//		}
//		if (area.getSort() == null){
//			area.setSort(30);
//		}
//		model.addAttribute("area", area);
//		return "modules/sys/areaForm";
//	}
//
//	@RequiresPermissions("sys:area:edit")
//	@RequestMapping(value = "save")
//	public String save(Area area, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, area)){
//			return form(area, model);
//		}
//		areaService.save(area);
//		addMessage(redirectAttributes, "保存树结构成功");
//		return "redirect:"+Global.getAdminPath()+"/sys/area/?repage";
//	}
//
//	@RequiresPermissions("sys:area:edit")
//	@RequestMapping(value = "delete")
//	public String delete(Area area, RedirectAttributes redirectAttributes) {
//		areaService.delete(area);
//		addMessage(redirectAttributes, "删除树结构成功");
//		return "redirect:"+Global.getAdminPath()+"/sys/area/?repage";
//	}
//
//	@RequiresPermissions("user")
//	@ResponseBody
//	@RequestMapping(value = "treeData")
//	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
//		List<Map<String, Object>> mapList = Lists.newArrayList();
//		List<Area> list = areaService.findList(new Area());
//		for (int i=0; i<list.size(); i++){
//			Area e = list.get(i);
//			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
//				Map<String, Object> map = Maps.newHashMap();
//				map.put("id", e.getId());
//				map.put("pId", e.getParentId());
//				map.put("name", e.getName());
//				mapList.add(map);
//			}
//		}
//		return mapList;
//	}
	@Autowired
	private AreaService areaService;
	
	@ModelAttribute("area")
	public Area get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return areaService.get(id);
		}else{
			return new Area();
		}
	}

	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = {"list", ""})
	public String list(Area area, Model model) {
		model.addAttribute("list", areaService.findAll());
		return "modules/sys/areaList";
	}

	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = "form")
	public String form(Area area, Model model) {
		if (area.getParent()==null||area.getParent().getId()==null){
			area.setParent(UserUtils.getUser().getOffice().getArea());
		}
		area.setParent(areaService.get(area.getParent().getId()));
//		// 自动获取排序号
//		if (StringUtils.isBlank(area.getId())){
//			int size = 0;
//			List<Area> list = areaService.findAll();
//			for (int i=0; i<list.size(); i++){
//				Area e = list.get(i);
//				if (e.getParent()!=null && e.getParent().getId()!=null
//						&& e.getParent().getId().equals(area.getParent().getId())){
//					size++;
//				}
//			}
//			area.setCode(area.getParent().getCode() + StringUtils.leftPad(String.valueOf(size > 0 ? size : 1), 4, "0"));
//		}
		model.addAttribute("area", area);
		return "modules/sys/areaForm";
	}
	
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "save")
	public String save(Area area, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, area)){
			return form(area, model);
		}
		areaService.save(area);
		addMessage(redirectAttributes, "保存区域'" + area.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/area/";
	}
	
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "delete")
	public String delete(Area area, RedirectAttributes redirectAttributes) {
//		if (Area.isRoot(id)){
//			addMessage(redirectAttributes, "删除区域失败, 不允许删除顶级区域或编号为空");
//		}else{
			areaService.delete(area);
			addMessage(redirectAttributes, "删除区域成功");
//		}
		return "redirect:" + adminPath + "/sys/area/";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Area> list = areaService.findAll();
		for (int i=0; i<list.size(); i++){
			Area e = list.get(i);
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