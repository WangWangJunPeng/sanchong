package com.sc.tradmaster.controller.user;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.SysContent;


/**
 * 
 * @author grl 2017-01-01
 *
 */
@Controller("userController")
public class UserController extends BaseController {
	
	@Resource(name="userService")	//指定名称注入，testDao对应注入对象指定的名称
	private UserService userService;	
	
	/**
	 * 通过id查看指定用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/look_user_info")
	public ModelAndView findUserById(String id){
		User u = userService.findById(id);
		System.out.println(u);
		ModelAndView mv = new ModelAndView("login/hello");
		mv.addObject("data", u);
		return mv;
	}
	/**
	 * 中介设置 跳转控制器
	 * @return
	 */
	@RequestMapping("/to_settingMid")
	public String toSettingInfo() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		return "app/house/myInstall";
	}
	
	/**
	 * 置业顾问设置 跳转控制器
	 * @return
	 */
	@RequestMapping("/to_settingSale")
	public String toSettingSaleInfo() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		return "app/house/agentInstall";
	}
	
	/**
	 * 中介app个人资料跳转控制器
	 * @return
	 */
	@RequestMapping("/to_midUserInfo")
	public String toMidUserInfoPage() {
		return "app/house/myMaterial";
	}
	
	/**
	 * 置业顾问app个人资料跳转控制器
	 * @return
	 */
	@RequestMapping("/to_saleUserInfo")
	public String toSaleUserInfoPage() {
		return "app/house/agentMaterial";
	}
	
	/**
	 * 中介或置业顾问app个人资料页面
	 * @return
	 */
	@RequestMapping("/to_getUserInfo")
	public void getUserInfo() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		Map<String, Object> map = userService.findUserInfo(user);
		this.outObjectString(map, null);
	}
	
	
	/**
	 * 中介我的验证原来密码
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/to_validationOldPassword")
	public void validationOldPassword(String password) throws IOException {
		// 获取当前登录用户对象
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		int i = 0;
		
		if(SysContent.md5(password).equals(user.getPassword())) {
			i = 1;
		} else {
			i = 0;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", i);
		this.outObjectString(map, null);
	}
	
	/**
	 * 中介和置业顾问app密码修改
	 * @return
	 */
	@RequestMapping("/to_goToChangePassword")
	public String toGoToChangePassword() {
		// 获取当前登录用户对象
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Set<Role> sr = user.getRoleId();
		for (Role role : sr) {
			if (role.getRoleId() == 1 || role.getRoleId() == 2){
				return "app/house/changePassword";
			}
			if (role.getRoleId() == 3){
				return "app/house/agentChangePassword";
			}
		}
		return null;
	}
	
	/**
	 * 中介和置业顾问app密码修改
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/to_updatePassword")
	public void changePassword(String password, String truepassword) throws IOException {
		// 获取当前登录用户对象
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		String jump = "error";
		if (password != null && !"".equals(password) && truepassword !=null && !"".equals(truepassword)){
			if (password.equals(truepassword)){
				userService.updatePassowrd(user, password, truepassword);
				jump = "true";
			} else {
				jump = "error";
			}
		}
		Map<String, String> map = new HashMap<>();
		map.put("jump", jump);
		this.outObjectString(map, null);
	}
	
	/**
	 * 中介我的页面顶部信息显示
	 * @return
	 */
	@RequestMapping("/to_midTopRecord")
	public void getMidInfo() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
				
		Map<String, Object> map = userService.findMidInfo(user);
		
		this.outObjectString(map, null);
	}
	
	/**
	 * 中介我的页面业务统计
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/to_myMidBusiness")
	public void getMidBusiness() throws ParseException {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Map<String, Object> map = userService.findMidBusiness(user);
		this.outObjectString(map, null);
	}
	
	/**
	 * 置业顾问我的页面顶部信息显示
	 * @return
	 */
	@RequestMapping("/to_saleTopRecord")
	public void getSaleInfo() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Map<String, Object> map = userService.findSaleInfo(user);
		this.outObjectString(map, null);
	}
	
	/**
	 * 置业顾问我的页面业务统计
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/to_mySaleBusiness")
	public void getSaleBusiness() throws ParseException {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Map<String, Object> map = userService.findSaleBusiness(user);
		this.outObjectString(map, null);
	}
	
	/**
	 * 置业顾问我的页面 我的客户跳转控制器
	 * @return
	 */
	@RequestMapping("/to_saleMyAllCustomerAndSubscribe") 
	public String getSaleMyAllCustomerAndSubscribe() {
		return "app/house/agentMyCustomerList";
	}
	
	
	/** 用户退出
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/user_logout")
	public Object userLogout(){
		Map<String, String> map = new HashMap<>();
		User userInfo = (User)SysContent.getSession().getAttribute("userInfo");
		String loginSign = (String) SysContent.getSession().getAttribute("loginSign");
		if(userInfo != null && loginSign != null){
			this.request.getSession().removeAttribute("userInfo");
			this.request.getSession().removeAttribute("loginSign");
			map.put("code", "200");
			map.put("url", "app/tologin");
			return map;
		}else{
			map.put("code", "202");
			map.put("url", "app/tologin");
			return map;
		}
	}
	
	/**
	 * 中介经纪人我的动态跳转控制器
	 * @return
	 */
	@RequestMapping("/to_Mydynamic")
	public String toGotoMydynamic(){
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		Set<Role> rs = user.getRoleId();
		for (Role role : rs) {
			if (role.getRoleId() == 1 || role.getRoleId() == 2){
				return "app/house/mytidings";
			}else if(role.getRoleId() == 3){
				return "app/house/agentMytidings";
			}
		}
		
		return "";
	}
	/**
	 * 中介经纪人我的动态
	 * @return
	 */
	@RequestMapping("/to_getMydynamic")
	public void toGetMidMydynamic(){
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		List<Mydynamic> mList = userService.findMidMydynamic(user);
		this.outListString(mList);
	}
	
	/**
	 * 中介经纪人我的动态改已读状态
	 * @return
	 */
	@RequestMapping("/to_updateMydynamic")
	public void toUpdateMidMydynamic(Integer dynamicId){
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		userService.updateMidMydynamic(user,dynamicId);
		
	}
	
	/**
	 * 中介经纪人我的动态未读数量显示
	 * @return
	 */
	@RequestMapping("/to_getMydynamicNum")
	public void toGetMydynamicNum(){
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		int count = userService.toGetMydynamicNotReadNum(user);
		Map<String, Integer> map = new HashMap<>();
		if (count>0){
			map.put("notReadNum", count);
		}else {
			map.put("notReadNum", 0);
		}
		this.outObjectString(map, null);
	}
}
