package com.znbl.modules.cms.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.znbl.common.persistence.Page;


public class Result {
	private boolean success = true;
	private String desc;
	private Map exception;
	private Map data;
	private String resultCode;
	private List dataList;
	private Page<?> page;

	public Page<?> getPage() {
		return page;
	}

	public void setPage(Page<?> page) {
		this.page = page;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public Result setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getDesc() {
		return desc;
	}

	public Result setDesc(String desc) {
		this.desc = desc;
		return this;
	}

	public Map getException() {
		return exception;
	}

	public void setException(Map exception) {
		this.exception = exception;
		setSuccess(false);
	}

	public void setExceptionId(int exceptionId) {
		if (exception == null) {
			exception = new HashMap<String, String>();
		}
		exception.put("id", String.valueOf(exceptionId));
		setSuccess(false);
	}

	public void setError(String error) {
		if (exception == null) {
			exception = new HashMap<String, String>();
		}
		exception.put("message", error);
		setSuccess(false);
	}

	public static Result createResult() {
		return new Result();
	}

	public Map getData() {
		return data;
	}

	public void setData(Map data) {
		this.data = data;
	}

	public void setDataValue(String key, Object value) {
		if (data == null) {
			data = new HashMap();
		}
		data.put(key, value);
	}

}
