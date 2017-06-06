package com.sc.tradmaster.controller.error;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.utils.SysContent;

@Controller
public class ErrorController extends BaseController {
	/**
	 * 异常处理
	 * @return
	 */
	@RequestMapping("/ERROR")
	public String errorHandlePage(){
		System.out.println("-------------------");
		String loginSign = (String) SysContent.getSession().getAttribute("loginSign");
		if(loginSign.equals("web")){
			return "error/ERROR";
		}else if(loginSign.equals("app")){
			return "error/APPERROR";
		}else{
			return "error/ERROR";
		}
	}
	
	/**
	 * app页面异常，返回到登录页面
	 * @return
	 */
	@RequestMapping("/errorPageToLoginPage")
	public String errorPageToLogin(){
		HttpSession session = SysContent.getRequest().getSession();
		session.setAttribute("noSession", "201");
		return "redirect:/app/tologin";
	}
}
