package com.sc.tradmaster.controller.visitRecords;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.visitRecords.VisitRecordsService;
import com.sc.tradmaster.service.visitRecords.impl.visitRecordDTO.ProjectCustomersDTO;
import com.sc.tradmaster.service.visitRecords.impl.visitRecordDTO.VisitCustomerDTO;

@Controller("visitRecordsController")
public class VisitRecordsController extends BaseController{

	@Resource(name = "visitRecordsService")
	private VisitRecordsService visitRecordsService;
	
	
	
	/**
	 * 中介到访客户表
	 */
	@RequestMapping("/to_visitList")
	public ModelAndView visitList(String projectId, String startTime, String endTime)throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		List<VisitCustomerDTO> list = visitRecordsService.findVisitCustomer(u, projectId, startTime, endTime);
		ModelAndView model = new ModelAndView("app/house/visitingCustomers");
		model.addObject("data", list);
		return model;
	}
	
	/**
	 * 置业顾问到访客户表
	 */
	@RequestMapping("/to_saleVisitList")
	public ModelAndView getSaleVisitList(){
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		
		List<ProjectCustomersDTO> vList = visitRecordsService.findSaleVisitList(u);
		ModelAndView model = new ModelAndView("app/house/agentVisitingCustomers");
		model.addObject("data", vList);
		return model;
	}
 }
