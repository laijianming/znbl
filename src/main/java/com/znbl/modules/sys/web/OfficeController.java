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

import com.znbl.common.config.Global;
import com.znbl.common.utils.StringUtils;
import com.znbl.common.web.BaseController;
import com.znbl.modules.sys.entity.Office;
import com.znbl.modules.sys.entity.User;
import com.znbl.modules.sys.service.OfficeService;
import com.znbl.modules.sys.utils.DictUtils;
import com.znbl.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 树结构生成Controller
 * 
 * @author cgli
 * @version 2016-02-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/office")
public class OfficeController extends BaseController {

	@Autowired
	private OfficeService officeService;

	@ModelAttribute
	public Office get(@RequestParam(required = false) String id) {
		Office entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = officeService.get(id);
		}
		if (entity == null) {
			entity = new Office();
		}
		return entity;
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = { "" })
	public String index(Office office, Model model) {
		// model.addAttribute("list", officeService.findAll());
		return "modules/sys/officeIndex";
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = { "list" })
	public String list(Office office, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Office> list = officeService.findList(office);
		model.addAttribute("list", list);
		return "modules/sys/officeList";
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "form")
	public String form(Office office, Model model) {

		// if (office.getParent()!=null &&
		// StringUtils.isNotBlank(office.getParent().getId())){
		// office.setParent(officeService.get(office.getParent().getId()));
		// // 获取排序号，最末节点排序号+30
		// if (StringUtils.isBlank(office.getId())){
		// Office officeChild = new Office();
		// officeChild.setParent(new Office(office.getParent().getId()));
		// List<Office> list = officeService.findList(office);
		// if (list.size() > 0){
		// office.setSort(list.get(list.size()-1).getSort());
		// if (office.getSort() != null){
		// office.setSort(office.getSort() + 30);
		// }
		// }
		// }
		// }
		// if (office.getSort() == null){
		// office.setSort(30);
		// }
		// model.addAttribute("office", office);
		// return "modules/sys/officeForm";

		User user = UserUtils.getUser();
		if (office.getParent() == null || office.getParent().getId() == null) {
			office.setParent(user.getOffice());
		}
		office.setParent(officeService.get(office.getParent().getId()));
		if (office.getArea() == null) {
			office.setArea(user.getOffice().getArea());
		}
		// 自动获取排序号
		if (StringUtils.isBlank(office.getId()) && office.getParent() != null) {
			int size = 0;
			List<Office> list = officeService.findAllList();
			for (int i = 0; i < list.size(); i++) {
				Office e = list.get(i);
				if (e.getParent() != null && e.getParent().getId() != null
						&& e.getParent().getId().equals(office.getParent().getId())) {
					size++;
				}
			}
			office.setCode(office.getParent().getCode()
					+ StringUtils.leftPad(String.valueOf(size > 0 ? size + 1 : 1), 3, "0"));
		}
		model.addAttribute("office", office);
		return "modules/sys/officeForm";
	}

	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "save")
	public String save(Office office, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, office)) {
			return form(office, model);
		}
		officeService.save(office);
//		addMessage(redirectAttributes, "保存树结构成功");
//		return "redirect:" + Global.getAdminPath() + "/sys/office/?repage";

		if(office.getChildDeptList()!=null){
			Office childOffice = null;
			for(String id : office.getChildDeptList()){
				childOffice = new Office();
				childOffice.setName(DictUtils.getDictLabel(id, "sys_office_common", "未知"));
				childOffice.setParent(office);
				childOffice.setArea(office.getArea());
				childOffice.setType("BM");
				childOffice.setGrade(String.valueOf(Integer.valueOf(office.getGrade())+1));
				childOffice.setUseable(Global.YES);
				officeService.save(childOffice);
			}
		}
		
		addMessage(redirectAttributes, "保存机构'" + office.getName() + "'成功");
		String id = "0".equals(office.getParentId()) ? "" : office.getParentId();
		return "redirect:" + adminPath + "/sys/office/list?id="+id+"&parentIds="+office.getParentIds();
	}

	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "delete")
	public String delete(Office office, RedirectAttributes redirectAttributes) {
		officeService.delete(office);
		addMessage(redirectAttributes, "删除机构成功");
		return "redirect:" + Global.getAdminPath() + "/sys/office/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Office> list = officeService.findList(new Office());
		for (int i = 0; i < list.size(); i++) {
			Office e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId())
					&& e.getParentIds().indexOf("," + extId + ",") == -1)) {
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