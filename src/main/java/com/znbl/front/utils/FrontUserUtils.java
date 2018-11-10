package com.znbl.front.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.znbl.common.utils.Encodes;
import com.znbl.common.utils.SpringContextHolder;
import com.znbl.modules.sys.dao.RoleDao;
import com.znbl.modules.sys.entity.Office;
import com.znbl.modules.sys.entity.Role;
import com.znbl.modules.sys.entity.User;
import com.znbl.modules.sys.service.OfficeService;
import com.znbl.modules.sys.service.SystemService;


public class FrontUserUtils {
	
	private static SystemService systemService=SpringContextHolder.getBean(SystemService.class);
	private static OfficeService officeService=SpringContextHolder.getBean(OfficeService.class);
	private static RoleDao roleDao=SpringContextHolder.getBean(RoleDao.class);
	
	/**
	 * 验证用户账号密码正确性
	 * @param userName
	 * @param passWord
	 * @return 用户id
	 */
	public static String checkIdentity(String userName,String passWord)
	{
		User user=systemService.getUserByLoginName(userName);
		if(null==user)
		{
			System.err.println("null==user");
			return null;
		}else
		{
			if(SystemService.validatePassword(passWord, user.getPassword()))
			{
				if(!user.isOrdinaryUsers(user.getUserType()))
				{
					System.err.println("user not an OrdinaryUsers");
					return null;
				}
				return user.getId();
			}else
			{
				System.err.println("password erro");
				return null;
			}
		}
	}
	
	/**
	 * 保存用户 
	 * @param user
	 * 		此对象已将登录名，密码与邮箱设置
	 * 		返回true则保存成功
	 * 		否则保存出错
	 * @return
	 */
	public static Boolean addUserInfo(User user)
	{
		try
		{
		Office office=new Office();
		Role role=new Role();
		List<Office> officeList=new ArrayList<Office>();
		List<Role> roleList=new ArrayList<Role>();
		
		//角色设定为普通用户
		role.setName("普通用户");
		roleList=roleDao.findList(role);
		List<String> roleIdList=new ArrayList<String>();
		roleIdList.add(roleList.get(0).getId());
		
		//上级设定为平台
		office.setName("平台");
		officeList=officeService.findList(office);
		
		user.setUserType("7");
		user.setName(user.getLoginName());
		user.setCompany(officeList.get(0));
		user.setOffice(officeList.get(0));
		user.setCreateBy(user);
		user.setUpdateBy(user);
		user.setRole(roleList.get(0));
		user.setRoleIdList(roleIdList);
		
		systemService.saveUser(user);
		
		return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * url拼接
	 * @param oldUrl
	 * @return
	 */
	public static String linkUrl(String oldUrl)
	{
		URL url=null;
		String newUrl=null;
		try {
			url = new URL(oldUrl);	
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String path=url.getPath();
		//System.out.println("path is " + path);
		String[] pathArray = path.split("/");
		
		//注册完后后缀有success
		if(pathArray.length>3&&pathArray[2].equals("front")&&pathArray[3].equals("success")){
			newUrl="/";
			return newUrl;
		}else
		{
			if(pathArray.length==2&&pathArray[1].equals("znbl")){
				newUrl="/";
				return newUrl;
			}
			if(pathArray.length>=3){
				newUrl="/";
				for(int i=2;i<pathArray.length;i++){
					newUrl=newUrl+pathArray[i]+"/";
				}
			}
		}
		return newUrl;
	}
}
