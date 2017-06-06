package com.sc.tradmaster.utils;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.interceptor.LogHandlerInterceptor;
import com.sc.tradmaster.service.agent.impl.visitDTO.UserDTO;
import com.sc.tradmaster.service.user.UserService;

import net.sf.json.JSONObject;

/**
 * 记录登录用户轨迹
 * @author grl 2017-01-01
 *
 */
@Component
@Aspect
public class LogerBean{
	
	private static Log log = LogFactory.getLog(LogHandlerInterceptor.class);
	
	//切入点  web检测
	@Pointcut("within(com.sc.tradmaster.controller..*) && !within(com.sc.tradmaster.controller.directorApp..*)"
			+ "&& !within(com.sc.tradmaster.controller.user.LoginController) "
			+ "&& !within(com.sc.tradmaster.controller.machine.MachineController) && !within(com.sc.tradmaster.controller.user.UserController) "
			+ "&& !within(com.sc.tradmaster.controller.ad.AdvertisementController)"
			+ "&& !within(com.sc.tradmaster.controller.agent.AgentController)"
			+ "&& !within(com.sc.tradmaster.controller.app.AppUploadPicsController)"
			+ "&& !within(com.sc.tradmaster.controller.contractRecords.ContractRecordsController)"
			+ "&& !within(com.sc.tradmaster.controller.favorites.FavoritesController)"
			+ "&& !within(com.sc.tradmaster.controller.feedback.FeeedbakController)"
			+ "&& !within(com.sc.tradmaster.controller.guideRecords.GuideRecordsController)"
			+ "&& !within(com.sc.tradmaster.controller.visitRecords.VisitRecordsController)"
			+ "&& !within(com.sc.tradmaster.controller.error.ErrorController)")
	public void pointCut(){}
	

	// 切入点  app检测
	@Pointcut("within(com.sc.tradmaster.controller..*) && !within(com.sc.tradmaster.controller.directorWeb..*)"
			+ "&& !within(com.sc.tradmaster.controller.user.LoginController) "
			+ "&& !within(com.sc.tradmaster.controller.machine.MachineController) "
			+ "&& !within(com.sc.tradmaster.controller.bank.BankCountController) "
			+ "&& !within(com.sc.tradmaster.controller.enterbuy.EnterBuyController)"
			+ "&& !within(com.sc.tradmaster.controller.excelFiles.ExcelfileController)"
			+ "&& !within(com.sc.tradmaster.controller.house.HouseController)"
			+ "&& !within(com.sc.tradmaster.controller.housetype.HouseTypeController)"
			+ "&& !within(com.sc.tradmaster.controller.project.ProjectController)"
			+ "&& !within(com.sc.tradmaster.controller.projectBenefits.ProjectBenefitsController)"
			+ "&& !within(com.sc.tradmaster.controller.projectGuide.ProjectGuideController)"
			+ "&& !within(com.sc.tradmaster.controller.projectpics.ProjectPicsController)"
			+ "&& !within(com.sc.tradmaster.controller.system.SystemController)"
			+ "&& !within(com.sc.tradmaster.controller.projectcustomer.ProjectCustomerController)"
			+ "&& !within(com.sc.tradmaster.controller.user.SystemUserController)"
			+ "&& !within(com.sc.tradmaster.controller.error.ErrorController)"
			+ "&& !within(com.sc.tradmaster.controller.tag.TagController)")
	public void appPointCut() {
	}
	//system
	@Pointcut("within(com.sc.tradmaster.controller.system.SystemController)")
	public void systemPointCut(){}
	

	//环绕通知处理方法
	@Around("pointCut()")
	public Object trackInfo(ProceedingJoinPoint pjp) throws Throwable{
		User user = (User) SysContent.getSession().getAttribute("userInfo");
		String loginSign = (String) SysContent.getSession().getAttribute("loginSign");
		if (loginSign == null || user == null) {
			SysContent.getRequest().getSession().setAttribute("loginSign", "web");
			return "redirect:/";
		}
		
		//执行目标方法
		return pjp.proceed();
	}
	
	//环绕通知处理方法
	@Around("appPointCut()")
	public Object appTrackInfo(ProceedingJoinPoint pjp) throws Throwable{
		User user = (User) SysContent.getSession().getAttribute("userInfo");
		String loginSign = (String) SysContent.getSession().getAttribute("loginSign");
		if (loginSign == null || user == null) {
			HttpSession session = SysContent.getRequest().getSession();
			session.setAttribute("noSession", "201");
			SysContent.getRequest().getSession().setAttribute("loginSign", "app");
			//model.addAttribute
			return "redirect:/app/tologin";
		}
		//执行目标方法
		return pjp.proceed();
	}
	
	@Around("systemPointCut()")
	public Object systemTrackInfo(ProceedingJoinPoint pjp) throws Throwable{
		User user = (User) SysContent.getSession().getAttribute("userInfo");
		String loginSign = (String) SysContent.getSession().getAttribute("loginSign");
		if (loginSign == null || user == null) {
			System.out.println("----------------------asdasda--------------------------------------");
			SysContent.getRequest().getSession().setAttribute("loginSign", "web");
			return "redirect:/system_login";
		}
		
		//执行目标方法
		return pjp.proceed();
	}

}