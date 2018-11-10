package com.znbl.front;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.znbl.common.config.Global;
import com.znbl.common.entity.JwtData;
import com.znbl.common.entity.ResponseData;
import com.znbl.common.security.shiro.session.SessionDAO;
import com.znbl.common.servlet.ValidateCodeServlet;
import com.znbl.common.utils.CacheUtils;
import com.znbl.common.utils.CookieUtils;
import com.znbl.common.utils.Encodes;
import com.znbl.common.utils.IdGen;
import com.znbl.common.utils.JWTUtils;
import com.znbl.common.utils.JsonUtils;
import com.znbl.common.utils.SendMessageUtils;
import com.znbl.common.utils.StringUtils;
import com.znbl.common.web.BaseController;
import com.znbl.front.utils.FrontUserUtils;
import com.znbl.modules.sys.entity.User;
import com.znbl.modules.sys.security.FormAuthenticationFilter;
import com.znbl.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.znbl.modules.sys.service.SystemService;
import com.znbl.modules.sys.utils.UserUtils;

@Controller
public class FrontUserController extends BaseController {

	@Autowired
	private SystemService systemService;
	@Autowired
	private SessionDAO sessionDAO;

	/**
	 * 管理员登录
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public String toLoginPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		
		if (logger.isDebugEnabled()){
			logger.debug("login, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		
		// 如果已登录，再次访问主页，则退出原账号。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			CookieUtils.setCookie(response, "LOGINED", "false");
		}
		
		// 如果已经登录，则跳转到管理首页
		if(principal != null && !principal.isMobileLogin()){
			return "redirect:" + adminPath;
		}
		return "front/login";
	}
	
	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		
		// 如果已经登录，则跳转到管理首页
		if(principal != null){
			return "redirect:" + adminPath;
		}

		String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
		boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
		String exception = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
		
		if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")){
			message = "用户或密码错误, 请重试.";
		}

		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM, mobile);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
		
		if (logger.isDebugEnabled()){
			logger.debug("login fail, active session size: {}, message: {}, exception: {}", 
					sessionDAO.getActiveSessions(false).size(), message, exception);
		}
		
		// 非授权异常，登录失败，验证码加1。
		if (!UnauthorizedException.class.getName().equals(exception)){
			model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
		}
		
		// 验证失败清空验证码
		request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, IdGen.uuid());
		
		// 如果是手机登录，则返回JSON字符串
		if (mobile){
	        return renderString(response, model);
		}
		
		//return "modules/sys/sysLogin";
		return "front/login";
	}

	/**
	 * 登录成功，进入管理首页
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "${adminPath}")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		Principal principal = UserUtils.getPrincipal();

		// 登录成功后，验证码计算器清零
		isValidateCodeLogin(principal.getLoginName(), false, true);
		
		if (logger.isDebugEnabled()){
			logger.debug("show index, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		
		// 如果已登录，再次访问主页，则退出原账号。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			String logined = CookieUtils.getCookie(request, "LOGINED");
			if (StringUtils.isBlank(logined) || "false".equals(logined)){
				CookieUtils.setCookie(response, "LOGINED", "true");
			}else if (StringUtils.equals(logined, "true")){
				UserUtils.getSubject().logout();
				return "redirect:/login";
			}
		}
		
		// 如果是手机登录，则返回JSON字符串
		if (principal.isMobileLogin()){
			if (request.getParameter("login") != null){
				return renderString(response, principal);
			}
			if (request.getParameter("index") != null){
				return "modules/sys/sysIndex";
			}
			return "redirect:/login";
		}
		

		return "modules/sys/sysIndex";
	}
	
	/**
	 * 获取主题方案
	 */
	@RequestMapping(value = "/theme/{theme}")
	public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request, HttpServletResponse response){
		if (StringUtils.isNotBlank(theme)){
			CookieUtils.setCookie(response, "theme", theme);
		}else{
			theme = CookieUtils.getCookie(request, "theme");
		}
		return "redirect:"+request.getParameter("url");
	}
	
	/**
	 * 是否是验证码登录
	 * @param useruame 用户名
	 * @param isFail 计数加1
	 * @param clean 计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean){
		Map<String, Integer> loginFailMap = (Map<String, Integer>)CacheUtils.get("loginFailMap");
		if (loginFailMap==null){
			loginFailMap = Maps.newHashMap();
			CacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum==null){
			loginFailNum = 0;
		}
		if (isFail){
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean){
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}
	
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="/registe/",method=RequestMethod.POST)
	public String registe(HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes Ra) {
		User newUser=new User();
		String message = null;
		newUser.setLoginName(Encodes.urlDecode(request.getParameter("username")));
		newUser.setPassword(SystemService.entryptPassword(request.getParameter("password")));
		newUser.setEmail(request.getParameter("email"));
		newUser.setPhone(request.getParameter("phone"));
		if(FrontUserUtils.addUserInfo(newUser)){
			message="注册成功";
		}
		Ra.addFlashAttribute("message", message);
		return "redirect:/";
	}
	
	
	/**
	 * 验证用户名是否重复
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkLoginName/{loginName}")
	public String checkLoginName(@PathVariable(value="loginName") String loginName) {
		Map<String, String> map =new HashMap<String, String>();
		if (loginName !=null && systemService.getUserByLoginName(loginName) == null) {
			String check="true";
			map.put("check", check);
			String result=JsonUtils.toJson(map);
			return result;
		}		
		String check="false";
		map.put("check", check);
		String result=JsonUtils.toJson(map);
		return result;
	}
	
	
	/**
	 * 信息验证
	 * @param username
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkUserInfo/{username}/{password}")
	public String checkUserInfo(@PathVariable(value="username") String username,@PathVariable(value="password") String password) {
		String loginResult=FrontUserUtils.checkIdentity(username, password);
		ResponseData responseData = ResponseData.ok();
		if(loginResult!=null){
			responseData.putDataValue("code", ResponseData.CODE_SUCCESS);
			JwtData jwtData = new JwtData();
			jwtData.setJwtFlag(loginResult);
			String token = JWTUtils.sign(jwtData, 60L* 1000L* 10L);
			responseData.putDataValue("token", token);
			responseData.putDataValue("userId", loginResult);
		}else{
			responseData.putDataValue("code", ResponseData.CODE_FAIL);
		}
		String result=JsonUtils.toJson(responseData.getData());
		return result;
	}
	
	
	/**
	 * 是否登录检验，并将登录的用户名传给前端(可做session是否过期用)
	 * @param httpSession
	 * @return
	 */
	@ResponseBody 
	@RequestMapping(value = "/isLogin")
	public String isLogin(HttpSession httpSession)
	{
		Map<String, String> map = new HashMap<String, String>();
		User user=(User) httpSession.getAttribute("loginUser");
		if(user==null)
		{
			String check="noLogin";
			map.put("check", check);
		}else
		{
			String check="Login";
			map.put("check", check);
			map.put("userName", user.getLoginName());
		}
		String result=JsonUtils.toJson(map);
		return result;
	}
	
	/**
	 * 用户登出
	 * @param request
	 * @param response
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/login-out/")
	public String loginOut(
			//@RequestHeader("Referer") String gourl,
			HttpServletRequest request, HttpServletResponse response,Model model,HttpSession httpSession)
	{
		//String finalUrl=FrontUserUtils.linkUrl(gourl);
		//return "redirect:"+finalUrl;
		return "redirect:/";
	}
	
	
	/**
	 * 验证码发送
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody 
	@RequestMapping(value = "/send-message/",method=RequestMethod.POST)
	public String sendMessage(HttpServletResponse response,HttpServletRequest request)
	{
		String phone = request.getParameter("phone");
		int messageCode = 1;
		if(StringUtils.isNotBlank(phone)){
			int code = SendMessageUtils.sendMessage(phone);
			if(code!=0){
				HttpSession session = request.getSession(); 
				session.setAttribute("phoneCode", code);  
				session.setMaxInactiveInterval(60 * 100); //保存100分钟
				messageCode = 0;
			}
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("code", messageCode);
		String result=JsonUtils.toJson(map);
		return result;
	}
	
	/**
	 * 验证码验证
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody 
	@RequestMapping(value = "/check-phone-code/",method=RequestMethod.POST)
	public String checkPhoneCode(HttpServletResponse response,HttpServletRequest request)
	{
		HttpSession session = request.getSession(); 
		String entercode = request.getParameter("enterCode");
		String sessionCode= session.getAttribute("phoneCode")+"";
		int messageCode = 1;
		if(StringUtils.isNotBlank(entercode)&&StringUtils.isNotBlank(sessionCode)){
			if(entercode.equals(sessionCode)){
				messageCode = 0;
			}
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("code", messageCode);
		String result=JsonUtils.toJson(map);
		return result;
	}
	
	
}
