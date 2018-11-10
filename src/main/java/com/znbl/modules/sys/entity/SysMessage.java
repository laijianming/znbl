/**
 * Copyright &copy; 2016 CNJSON All rights reserved.
 */
package com.znbl.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.znbl.common.persistence.DataEntity;

/**
 * 平台留言Entity
 * @author Gray
 * @version 2016-04-05
 */
public class SysMessage extends DataEntity<SysMessage> {

	private static final long serialVersionUID = 1L;
	private String messageContent;		// 内容
	private String sendUserName;		// 发送人
	private String sendUserMobile;		// 发送人手机
	private String sendUserEmail;		// 发送人邮件
	private Date sendTime;		// 发送时间
	private Date operationTime;		// 操作时间
	private String status;		// 消息状态：0未处理，1已处理
	private String type;		// 留言类型：数据字典

	public SysMessage() {
		super();
	}

	public SysMessage(String id){
		super(id);
	}

	@Length(min=0, max=2000, message="内容长度必须介于 0 和 2000 之间")
	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	@Length(min=0, max=50, message="发送人长度必须介于 0 和 50 之间")
	public String getSendUserName() {
		return sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	@Length(min=0, max=20, message="发送人手机长度必须介于 0 和 20 之间")
	public String getSendUserMobile() {
		return sendUserMobile;
	}

	public void setSendUserMobile(String sendUserMobile) {
		this.sendUserMobile = sendUserMobile;
	}

	@Length(min=0, max=50, message="发送人邮件长度必须介于 0 和 50 之间")
	public String getSendUserEmail() {
		return sendUserEmail;
	}

	public void setSendUserEmail(String sendUserEmail) {
		this.sendUserEmail = sendUserEmail;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="发送时间不能为空")
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="操作时间不能为空")
	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	@Length(min=0, max=2, message="消息状态：0未处理，1已处理长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Length(min=0, max=50, message="留言类型：数据字典长度必须介于 0 和 50 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}