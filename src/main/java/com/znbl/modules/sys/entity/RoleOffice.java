/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.znbl.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author cgli
 * @version 2016-02-29
 */
public class RoleOffice extends DataEntity<RoleOffice> {

	private static final long serialVersionUID = 1L;
	private String roleId;		// 角色编号
	private String officeId;		// 机构编号

	public RoleOffice() {
		super();
	}

	public RoleOffice(String id){
		super(id);
	}

	@Length(min=1, max=64, message="角色编号长度必须介于 1 和 64 之间")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Length(min=1, max=64, message="机构编号长度必须介于 1 和 64 之间")
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

}