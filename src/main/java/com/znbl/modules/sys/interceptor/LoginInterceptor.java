package com.znbl.modules.sys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.znbl.common.entity.JwtData;
import com.znbl.common.service.BaseService;
import com.znbl.common.utils.JWTUtils;

/**
 * 信息展示登录拦截器(使用jwt)
 * @author Gray
 * @version 2017年7月29日
 */
public class LoginInterceptor extends BaseService implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try{
			// TODO 自动生成的方法存根
			String token = request.getParameter("token");
			String userId = request.getParameter("userId");
			if(token!=null&&userId!=null&&!token.equals("null")&&!userId.equals("null")){
				JwtData jwtData = JWTUtils.unsign(token, JwtData.class);
				if(jwtData.getJwtFlag().equals(userId)){
					return true;
				}
			}
			//System.out.println(request.getMethod());
			//request.getRequestDispatcher("/redirect-index/").forward(request, response);
			response.sendRedirect("/znbl/redirect-index/");
			return false;	
		}catch(Exception e) {
			response.sendRedirect("/znbl/redirect-index/");
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO 自动生成的方法存根
		
	}

}
