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
public class SysPic extends TreeEntity<SysPic> {

	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private SysPic parent;		// 父类编号
	private String parentIds;		// 多层父id串
	private String picUrl;		// 图片路径
	private String type;		// 类型:0图册 1图片
	private String status;		// 0是被使用 1在图册中
	private Integer sort;		// 排序号，越小越前

	public SysPic() {
		super();
	}

	public SysPic(String id){
		super(id);
	}

	@Length(min=1, max=125, message="名称长度必须介于 1 和 125 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonBackReference
	@NotNull(message="父类编号不能为空")
	public SysPic getParent() {
		return parent;
	}

	public void setParent(SysPic parent) {
		this.parent = parent;
	}

	@Length(min=0, max=1000, message="多层父id串长度必须介于 0 和 1000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Length(min=0, max=2000, message="图片路径长度必须介于 0 和 2000 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Length(min=0, max=10, message="类型:0图册 1图片长度必须介于 0 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min=0, max=1, message="0是被使用 1在图册中长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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