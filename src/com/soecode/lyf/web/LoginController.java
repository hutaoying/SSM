package com.soecode.lyf.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login(HttpSession session,String username,String password) {
		//调用service进行用户身份验证
		
		
		session.setAttribute("username", username);
		return "redirect:items/queryItems.action";
	}
	//退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:items/queryItems.action";
		
	}
}
