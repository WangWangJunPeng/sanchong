package com.sc.tradmaster.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.SysContent;

@Controller
public class SystemUserController extends BaseController {

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("/to_edit_passworld")
	public String toUpdateUserPsw(){
		return "login/alterPsd";
	}
	
	@RequestMapping("/to_shop_edit_passworld")
	public String toUpdateShopUserPsw(){
		return "login/shopAlterPsd";
	}
	
	@ResponseBody
	@RequestMapping("/update_passworld")
	public Object UpdateUserPassWorld(String oldPsw, String newPsw) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		Map map = new HashMap<>();
		Boolean flag = userService.addOrUpdateUserPassWorld(u, oldPsw, newPsw);
		if (flag) {
			map.put("returnCode", "200");
			map.put("url", "index");
		} else {
			map.put("returnCode", "201");
			map.put("msg", "旧密码输入错误！");
		}
		return map;
	}

	@RequestMapping("/web_logout")
	public String logOutLogin() {
		// 获取当前登录用户的session信息
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String loginSign = (String) SysContent.getSession().getAttribute("loginSign");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if(u!=null && loginSign!=null){
			this.request.getSession().removeAttribute("userInfo");
			this.request.getSession().removeAttribute("loginSign");
		}
		return "redirect:/";
	}

}
