package com.kh.mw.interceptor;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.mw.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		UserVo userVo= (UserVo)session.getAttribute("loginInfo");
		// 로그인 되어있지 않다면
		if (userVo == null ) {
			String uri = request.getRequestURI();
			System.out.println("uri:" + uri);
			String queryString = request.getQueryString();
			System.out.println("queryString:" + queryString);
			String targetLocation = uri + "?" + queryString;
			session.setAttribute("targetLocation", targetLocation);
			response.sendRedirect("/user/login");
			return false;
		} 
		return true;
	}
	
}