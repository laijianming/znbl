package com.znbl.front.interest.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.znbl.common.web.BaseController;


/**
 * 兴趣资讯
 * @author Gray
 */
@Controller
@RequestMapping(value ="/interest")
public class FrontInterestController extends BaseController{
	
	/**
	 * 兴趣资讯主页
	 * @param token
	 * @param userId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="/index/")
	public String redirectToIndex(@RequestParam(value="token",required=false) String token,
			@RequestParam(value="userId",required=false) String userId,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		return "front/interest/index";
	}

}
