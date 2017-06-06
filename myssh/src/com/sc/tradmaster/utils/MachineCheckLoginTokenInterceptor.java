package com.sc.tradmaster.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.user.UserService;

public class MachineCheckLoginTokenInterceptor extends HandlerInterceptorAdapter {
	/** 
     * Handler执行完成之后调用这个方法 
     */  
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception exc) throws Exception {  
    }  
  
    /** 
     * Handler执行之后，ModelAndView返回之前调用这个方法 
     */  
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {  
    }  
  
    /** 
     * Handler执行之前调用这个方法 
     */ 
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
		String token = request.getParameter("visitToken");
		if (token != null && !token.equals("")) {
			String userId = token.substring(0, token.indexOf('-'));
			UserService userSer = ServiceHelper.getUserService();
			User user = userSer.findById(userId);
			if (!user.getUserToken().equals(token)) {
				System.out.println("用户未登录，请重新登录......");
				response.sendRedirect("machine_error_out_info");
				return false;
			}
		}
        return true;
    }  
}
