package com.znbl.common.entity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 前端数据交互工具类entity
 * @author Gray
 * @version 2017年7月29日
 */
public class ResponseData implements Serializable{

	private static final long serialVersionUID = 1L;
	private final String message;
    private final int code;
    private final Map<String, Object> data = new HashMap<String, Object>();
    public final static int CODE_SUCCESS = 1; //成功标识
    public final static int CODE_FAIL  = 0;  //失败标识

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResponseData putDataValue(String key, Object value) {
        data.put(key, value);
        return this;
    }

    private ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseData ok() {
        return new ResponseData(200, "Ok");
    }

    public static ResponseData notFound() {
        return new ResponseData(404, "Not Found");
    }

    public static ResponseData badRequest() {
        return new ResponseData(400, "Bad Request");
    }

    public static ResponseData forbidden() {
        return new ResponseData(403, "Forbidden");
    }

    public static ResponseData unauthorized() {
        return new ResponseData(401, "unauthorized");
    }

    public static ResponseData serverInternalError() {
        return new ResponseData(500, "Server Internal Error");
    }

    public static ResponseData customerError() {
        return new ResponseData(1001, "customer Error");
    }
}