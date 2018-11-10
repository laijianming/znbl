package com.znbl.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.znbl.common.utils.Encodes;
import com.znbl.common.utils.StringUtils;
import com.znbl.common.web.BaseController;
import com.znbl.front.utils.FrontUserUtils;
import com.znbl.modules.sys.entity.User;
import com.znbl.modules.sys.service.SystemService;

/**
 * 主页Controller
 * @author Gray
 * @version 2017年7月29日
 */
@Controller
public class FrontIndexController extends BaseController {
	
	@Autowired
	private SystemService systemService;
	
	/**
	 * 跳转至主页
	 * @param message
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/front/home", "/front/", "/" ,"/front"})
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		String message = request.getParameter("message");
		if(StringUtils.isNotBlank(message)){
			model.addAttribute("message", message);
		}
		return "front/index";
	}
	
	/**
	 * 注册页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="/register/")
	public String register(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "front/register";
	}
	
	
	/**
	 * 跳转登录页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="/front/login/")
	public String userLogin(@RequestHeader("Referer") String gourl,HttpServletRequest request, HttpServletResponse response, Model model) {
		//将前一个页面的url保存，当登录完成后直接跳转
		model.addAttribute("gourl", gourl);
		return "front/userlogin";
	}
	
	
	/**
	 * 登录成功跳转
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userLogin/",method=RequestMethod.POST)
	public String userLogin(HttpServletRequest request, HttpServletResponse response,Model model)
	{
        String gourl = request.getParameter("gourl");
        String finalUrl=FrontUserUtils.linkUrl(gourl);
		return "redirect:"+finalUrl;
	}
	
	
	/**
	 * 重定向至主页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/redirect-index/",method=RequestMethod.GET)
	public String redirectToIndex(HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes Ra){
		String message = request.getParameter("message");
		if(message==null){
			message = "还未登录或登录已过期";
		}
		Ra.addFlashAttribute("message", message);
		return "redirect:/";
	}
}