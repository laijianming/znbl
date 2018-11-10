/**
 * Copyright &copy;2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.entity;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.znbl.common.persistence.TreeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 树结构生成Entity
 * @author cgli
 * @version 2016-02-29
 */
public class Office extends TreeEntity<Office> {

	private static final long serialVersionUID = 1L;
//	private Office parent;		// 父级编号
//	private String parentIds;		// 所有父级编号
//	private String name;		// 名称
//	private Integer sort;		// 排序
	private Area area;//
	private String code;		// 区域编码
	private String type;		// 机构类型
	private String grade;		// 机构等级
	private String address;		// 联系地址
	private String zipCode;		// 邮政编码
	private String master;		// 负责人
	private String phone;		// 电话
	private String fax;		// 传真
	private String email;		// 邮箱
	private String useable;		// 是否启用
	private String primaryPerson;		// 主负责人
	private String deputyPerson;		// 副负责人
	private List<String> childDeptList;//快速添加子部门
	
	public Office() {
		super();
	}

	public Office(String id){
		super(id);
	}

	@JsonBackReference
	@NotNull(message="父级编号不能为空")
	public Office getParent() {
		return parent;
	}

	public void setParent(Office parent) {
		this.parent = parent;
	}

//	@Length(min=1, max=2000, message="所有父级编号长度必须介于 1 和 2000 之间")
//	public String getParentIds() {
//		return parentIds;
//	}
//
//	public void setParentIds(String parentIds) {
//		this.parentIds = parentIds;
//	}
//
//	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Integer getSort() {
//		return sort;
//	}
//
//	public void setSort(Integer sort) {
//		this.sort = sort;
//	}

	@Length(min=0, max=100, message="区域编码长度必须介于 0 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Length(min=1, max=1, message="机构类型长度必须介于 1 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min=1, max=1, message="机构等级长度必须介于 1 和 1 之间")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Length(min=0, max=255, message="联系地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Length(min=0, max=100, message="邮政编码长度必须介于 0 和 100 之间")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Length(min=0, max=100, message="负责人长度必须介于 0 和 100 之间")
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	@Length(min=0, max=200, message="电话长度必须介于 0 和 200 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Length(min=0, max=200, message="传真长度必须介于 0 和 200 之间")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Length(min=0, max=200, message="邮箱长度必须介于 0 和 200 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(min=0, max=64, message="是否启用长度必须介于 0 和 64 之间")
	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	@Length(min=0, max=64, message="主负责人长度必须介于 0 和 64 之间")
	public String getPrimaryPerson() {
		return primaryPerson;
	}

	public void setPrimaryPerson(String primaryPerson) {
		this.primaryPerson = primaryPerson;
	}

	@Length(min=0, max=64, message="副负责人长度必须介于 0 和 64 之间")
	public String getDeputyPerson() {
		return deputyPerson;
	}

	public void setDeputyPerson(String deputyPerson) {
		this.deputyPerson = deputyPerson;
	}

	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<String> getChildDeptList() {
		return childDeptList;
	}

	public void setChildDeptList(List<String> childDeptList) {
		this.childDeptList = childDeptList;
	}
}