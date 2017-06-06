package com.sc.tradmaster.controller.enterbuy;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.enterbuy.EnterBuyService;
import com.sc.tradmaster.service.user.UserService;

/**
 * 
 * @author grl 2017-01-16
 *
 */
@Controller("enterBuyController")
public class EnterBuyController extends BaseController {

	@Resource(name = "enterBuyService")
	private EnterBuyService enterBuyService;

	@Resource(name = "userService")
	private UserService userService;

	/**
	 * 认购规则页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_buyRule")
	public String toBuyRule() {
		return "project/buyRule";
	}

	/**
	 * 添加或更新认购规则
	 * 
	 * @param eb
	 * @return
	 */
	@RequestMapping("/add_or_update_enter_buy")
	public String addOrUpdateEnterBuy(EnterBuy eb , String checkIsSupportBuy) {
		// 获取登录用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if(checkIsSupportBuy!=null && checkIsSupportBuy.equals("on")){
			eb.setIsSupportBuy("1");
		}else{
			eb.setIsSupportBuy("0");
		}
		// 执行后台业务
		enterBuyService.addOrupdateEnterBuy(eb, user);
		return "redirect:/to_buyRule";
	}

	/**
	 * 获取当前认购规则的信息
	 */
	@ResponseBody
	@RequestMapping("/get_current_enter_buy_info")
	public void getCurrentEnterBuy() {
		// 获取登录用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		// 执行后台业务
		EnterBuy enterBuy = enterBuyService.findById(user);
		this.outObjectString(enterBuy, null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
