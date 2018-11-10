/**
 * Copyright &copy; 2012-2014 cnjson.attendance All rights reserved.
 */
package com.znbl.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;

import com.znbl.common.utils.SpringContextHolder;
import com.znbl.modules.sys.entity.SysPic;
import com.znbl.modules.sys.service.SysPicService;

/**
 * 字典工具类
 * @author Gray
 * @version 2013-5-29
 */
public class SysPicUtils {
	
	private static SysPicService sysPicService = SpringContextHolder.getBean(SysPicService.class);

	/**
	 * 获取图册列表
	 * @return
	 */
	public static List<SysPic> getPicList(){
		List<SysPic> list = new ArrayList<SysPic>();
		SysPic sysPic = new SysPic();
		sysPic.setType("图册");
		list=sysPicService.findList(sysPic);
		System.err.print("list--"+list);
		return list;
	}

}
