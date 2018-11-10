/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.znbl.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author Gray
 * @version 2016-04-05
 */
public class SysFile extends DataEntity<SysFile> {

	private static final long serialVersionUID = 1L;
	private String uploadUserId;		// 上传者ID
	private String fileTypeId;		// 生物类型ID
	private String fileUrl;		// 文件路径
	private String status;		// 0通过1未通过2待审核

	public SysFile() {
		super();
	}

	public SysFile(String id){
		super(id);
	}

	@Length(min=1, max=64, message="上传者ID长度必须介于 1 和 64 之间")
	public String getUploadUserId() {
		return uploadUserId;
	}

	public void setUploadUserId(String uploadUserId) {
		this.uploadUserId = uploadUserId;
	}

	@Length(min=0, max=50, message="生物类型ID长度必须介于 0 和 50 之间")
	public String getFileTypeId() {
		return fileTypeId;
	}

	public void setFileTypeId(String fileTypeId) {
		this.fileTypeId = fileTypeId;
	}

	@Length(min=0, max=2000, message="文件路径长度必须介于 0 和 2000 之间")
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Length(min=0, max=1, message="0通过1未通过2待审核长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}