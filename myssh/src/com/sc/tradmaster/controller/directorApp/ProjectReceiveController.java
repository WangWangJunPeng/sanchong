package com.sc.tradmaster.controller.directorApp;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.director.ProjectReceiveService;
import com.sc.tradmaster.service.user.UserService;

/**
 * 2017-05-22
 * 
 * @author grl
 *
 */
// 案场接访
@Controller("ProjectReceiveController")
public class ProjectReceiveController extends BaseController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "projectReceiveService")
	private ProjectReceiveService projectReceiveService;

	// 跳转今日接访首页
	@RequestMapping("/to_director_today_receiver_index")
	public String toTodayReceiveFirstPage() {
		return "app/director/index";
	}

	// 获取今日接访首页数据
	@ResponseBody
	@RequestMapping("/get_today_receiver_data")
	public Object getTodayReceiveFirstPageData() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = projectReceiveService.findTodayReceiveFirstPageData(user);

		return map;
	}

	// 跳转顾问状态页面
	@RequestMapping("/to_agent_status_page")
	public String toAgentStatusPage(String usesId, Model model) {
		model.addAttribute("userId", usesId);
		return "app/director/agentStatus.jsp";
	}

	// 获取顾问状态数据
	@ResponseBody
	@RequestMapping("/get_agent_status_data")
	public Object getAgentStatusData(String userId, String startDate, String endDate) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = projectReceiveService.findAgentStatusDataById(user, userId, startDate, endDate);

		return map;
	}

	// 跳转详细接访数据页面
	@RequestMapping("/to_toDay_detail_Receive_page")
	public String toToDayDetailedReceivePage() {
		return "app/director/detailedReceive.jsp";
	}

	// 获取详细接访数据
	@ResponseBody
	@RequestMapping("/get_toDay_detail_Receive_data")
	public Object getToDayDetailedReceiveData(String startDate, String endDate) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map map = projectReceiveService.findToDayDetailedReceiveDataByTime(user, startDate, endDate);
		return map;
	}

	// 跳转成交数据页面
	@RequestMapping("to_deal_data_page")
	public String toDealDataPage() {
		return "app/director/DealDataPage.jsp";
	}

	// 获取详细成交数据
	public Object getDealDataData(String startDate, String endDate) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map map = projectReceiveService.findDealDataByTime(user,startDate,endDate);
		return map;
	}

}
