package com.soecode.lyf.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 全局异常处理器
 * @author Administrator
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{

	@Override
	/**
	 * ex 系统抛出异常
	 */
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception ex) {
		CustomException customException=null;
		if(ex instanceof CustomException) {
			customException=(CustomException) ex;
		}else {
			customException=new CustomException("未知错误");
		}
		//错误信息
		String message=customException.getMessage();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("error");
		return modelAndView;
	}

	/*public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		
		CustomException customException = null;
		if(exception instanceof CustomException){
			customException = (CustomException)exception;
		}else{
			customException = new CustomException("未知错误！");
		}
		
		String message = customException.getMessage();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message);
		
		modelAndView.setViewName("error");
		
		return modelAndView;
		
	}*/

}
