package com.soecode.lyf.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("HandlerInterceptor1...afterCompletion");
		
	}
//进入Handler方法之后，返回modelAndView之前
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("HandlerInterceptor1...postHandle");
		// TODO Auto-generated method stub
		
	}
//之前执行
	//用于身份认证，身份授权
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String url=request.getRequestURI();
		//判断是否为公开地址
		if(url.indexOf("login.action")>=0) {
			return true;
		}
		//判断session
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");
		if(username!=null) {
			//身份存在，放行
			return true;
			
		}
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);;
		//System.out.println("HandlerInterceptor1...preHandle");
		return false;
	}

}
