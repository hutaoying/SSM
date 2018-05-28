package com.soecode.lyf.exception;
/**
 * 自定义异常类  针对预期的异常
 * @author Administrator
 *
 */
public class CustomException extends Exception {
	private static final long serialVersionUID = 1L;
   //异常信息
	private String message;
	
	public CustomException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
