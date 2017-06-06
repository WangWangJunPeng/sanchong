package com.sc.tradmaster.controller.agent;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.SignRecords;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.agent.AgentVisitRecordService;
import com.sc.tradmaster.service.agent.impl.visitDTO.Customer;
import com.sc.tradmaster.service.agent.impl.visitDTO.ProjectCustomerDTO;
import com.sc.tradmaster.service.user.UserService;

/**
 * 2017-02-03
 * 
 * @author grl
 *
 */
@Controller("agentController")
public class AgentController extends BaseController {

	@Resource(name = "agentVisitRecordService")
	private AgentVisitRecordService agentVisitRecordService;

	@Resource(name = "userService")
	private UserService userService;
	
	@RequestMapping("/to_my_task_page")
	public String toMyTaskPage(){
		return "app/login/agent_index";
	}
	
	/**
	 * 获取当前职业顾问的任务信息
	 */
	@RequestMapping("/all_my_task_list")
	public void toMyTask(String dataStr) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		List<VisitRecords> visitRecords = agentVisitRecordService.findVisitInfoByUser(user,dataStr);
		if (visitRecords != null) {
			this.outListString(visitRecords);
		}
	}

	/**
	 * 通过手机号获取客户信息
	 * 
	 * @param phone
	 */
	@RequestMapping("/get_customer_by_phone")
	public void findVisitCustomerInfo(String phone,Integer visitNo) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		ProjectCustomerDTO customer = agentVisitRecordService.findCustomerInfoByPhone(user, phone);
		this.outObjectString(customer, null);
		agentVisitRecordService.addorUpdataVistInfo(user,visitNo,phone);

	}

	/**
	 * 置业顾问填写客户信息的业务逻辑
	 * 
	 * @param phone
	 * @param visitNo
	 * @return
	 */
	@RequestMapping("/agent_input_customer_info")
	public String saveOrUpdateCustomerOrupdateGuide(String cName, String phone, String desc, Integer visitNo) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		try {
			agentVisitRecordService.addOrUpdateAgentInsertCustomerInfo(user, cName, phone, desc, visitNo);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:/to_my_task_page";
	}

	/**
	 * 4个住按钮跳转到置业顾问房源信息页
	 */
	@RequestMapping(value="/to_goToSaleHouseDetail")
	public String toGoToSaleHouseDetail() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
//		String projectId = user.getParentId();
//		ModelAndView model = new ModelAndView("app/house/saleHouseDetail");
//		model.addObject("dataInfo", projectId);
//		return model;
		
		return "app/house/saleHouseDetail";
	}
	
	/**
	 * 4个住按钮跳转到置业顾问任务首页
	 */
	@RequestMapping(value="/to_goToAgentIndex")
	public String toGoToAgentIndex() {
		return "app/login/agent_index";
	}
	
	/**
	 * 4个住按钮跳转到置业顾问业务首页
	 */
	@RequestMapping(value="/to_goToAgentMyService")
	public String toGoToAgentMyService() {
		return "app/house/agentMyService";
	}
	
	/**
	 * 4个住按钮跳转到置业顾问我的首页
	 */
	@RequestMapping(value="/to_goToAgentMyPerson")
	public String toGoToAgentMyPerson() {
		return "app/house/agentMyPerson";
	}
}
