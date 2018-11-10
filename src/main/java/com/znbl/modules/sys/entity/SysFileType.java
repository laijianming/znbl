/**
 * Copyright &copy;2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;

import com.znbl.common.persistence.TreeEntity;

/**
 * 树结构生成Entity
 * @author Gray
 * @version 2016-04-05
 */
public class SysFileType extends TreeEntity<SysFileType> {

	private static final long serialVersionUID = 1L;
	private String name;		// 类型名称
	private SysFileType parent;		// 父类编号
	private String parentIds;		// 多层父id串
	private Integer sort;		// 排序号，越小越前

	public SysFileType() {
		super();
	}

	public SysFileType(String id){
		super(id);
	}

	@Length(min=1, max=125, message="类型名称长度必须介于 1 和 125 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonBackReference
	@NotNull(message="父类编号不能为空")
	public SysFileType getParent() {
		return parent;
	}

	public void setParent(SysFileType parent) {
		this.parent = parent;
	}

	@Length(min=0, max=1000, message="多层父id串长度必须介于 0 和 1000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}