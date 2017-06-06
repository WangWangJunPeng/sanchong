package com.sc.tradmaster.controller.projectcustomer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.projectcustomer.ProjectCustomerService;
import com.sc.tradmaster.service.tagService.TagService;
import com.sc.tradmaster.service.tagService.impl.dto.TagLib;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.Page;

/**
 * 2017-02-08
 * @author grl
 *
 */
@Controller("projectCustomerController")
public class ProjectCustomerController extends BaseController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "projectCustomerService")
	private ProjectCustomerService projectCustomerService;
	
	@Resource(name = "projectService")
	private ProjectService projectService;
	
	@Resource(name = "tagService")
	private TagService tagService;

	/**
	 * 客户管理-页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/pro_customer_manager_page_info")
	public String toCustomerManagePage() {
		return "projectCustomer/customerManagement";
	}
	
	/**
	 * 中介店长客户管理-页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/shop_customer_manager_page_info")
	public String toShopCustomerManagePage() {
		return "projectCustomer/shopCustomerManager";
	}

	/**
	 * 客户管理 list
	 * @param start
	 * @param limit
	 * @param selectValue
	 */
	@ResponseBody
	@RequestMapping("/list_pro_customers_info")
	public void listCustomerInfo(Integer start, Integer limit, String selectValue) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if (user != null) {
			projectCustomerService.findProCustomersByUser(user, selectValue, page);
			this.outPageString(page);
		}
	}

	/**
	 * 客户管理 修改归属置业顾问 菜单
	 */
	@ResponseBody
	@RequestMapping("/grl_get_agents_menu")
	public void getAgentsMenu() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if (user.getParentId() != null && !user.getParentId().equals("")) {
			List<Map<String, String>> agMenuList = projectCustomerService.findAgentsToMenu(user.getParentId());
			this.outListString(agMenuList);
		}
	}
	
	/**
	 * 客户职业顾问归属设置
	 * @param proCursId
	 * @param agentId
	 * @return
	 */
	@RequestMapping("/update_pro_customers_info")
	public String setUpNewAgentForProCustomer(String[] proCursId , String agentId){
		if(proCursId!=null){
			projectCustomerService.addOrUpdateProCustomerownerAgent(proCursId,agentId);
		}
		return "redirect:/pro_customer_manager_page_info";
	}
	
	/**
	 * shop 客户管理 list
	 * @param start
	 * @param limit
	 * @param selectValue
	 */
	@ResponseBody
	@RequestMapping("/list_shop_customers_info")
	public void listShopCustomerInfo(Integer start, Integer limit, String selectValue) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if (user != null) {
			projectCustomerService.findShopCustomersByUser(user, selectValue, page);
			this.outPageString(page);
		}
	}
	
	/**
	 * 进入用户信息的页面
	 * @return
	 */
	@RequestMapping("to_customer_info_page")
	public ModelAndView toCustomerInfoPage(ProjectCustomers customer){
		
		if(customer == null){
			ModelAndView mv = new ModelAndView();
			mv.addObject("edata", 202);//当phone为空
			mv.setViewName("forword:/pro_customer_manager_page_info");
			return mv;
		}else{
			ModelAndView mv = new ModelAndView();
			System.out.println(customer);
			mv.addObject("customerInfo", customer);
			mv.setViewName("");//跳转的页面，后续有页面补上
			return mv;
		}
	}
	
	
	/**
	 * ajax异步获取客户的详细信息包括标签
	 * @param customer
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get_customer_tag_info")
	public Map<String, Object> getCustomerInfo(ProjectCustomers customer){
		
		//根据当前登录的用户获取当前的案场
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		Map<String, Object> map = projectCustomerService.findCustomerCAndVInfo(customer.getProjectCustomerPhone(), pro.getProjectId());
		
		//获取客户的动态
		List<Mydynamic> dList = projectCustomerService.findCustomerDynamicByCustomerId(customer.getProjectCustomerId(), "projectCustomerId");
		
		map.put("dynamic", dList);
		
		//查找所有用户的标签
		//首先要根据用户的phone查找用户的id
		ProjectCustomers cust = projectCustomerService.findProjectCustomersByPhone(customer.getProjectCustomerPhone());
		
		List<TagLib> tagLibList = tagService.showTagLib(null, pro.getProjectId(), null, null);
		Integer tagTypeId = 0;
		for(TagLib t : tagLibList){
			if("客户标签".equals(t.getTagTypeName())){
				tagTypeId = t.getTagTypeId();
			}
		}
		//DONE 查找客户选中的所有的标签和标签类目
		List<TagLib> list = tagService.showTagLib_use(cust.getProjectCustomerId(), tagTypeId, pro.getProjectId());
		
		map.put("customerTag", list);
		
		return map;
	}
	
	
	
	

}
