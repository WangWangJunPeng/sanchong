package com.sc.tradmaster.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.TripleDES;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 2017-01-02
 * @author Administrator
 *
 */
@Controller
public class LoginController extends BaseController {

	@Resource(name="userService")	//指定名称注入，testDao对应注入对象指定的名称
	private UserService userService;
	
	@Resource(name = "houseService")
	private HouseService houseService;
	
	@Resource(name = "projectService")
	private ProjectService projectService;
	
	private static Logger log = Logger.getLogger(LoginController.class);

	@RequestMapping("/exception")
	public void exception(){
		userService.getArray();
	}
	
	/**
	 * 地图显示页面
	 */
	@RequestMapping("/mapInfo")
	public String forMapInfo(){
		return "login/otherMap";
	}
	
	/**
	 * 首页页面控制器
	 */
	@RequestMapping("/index")
	public String forBasePage(){
		return "../../index";
	}
	
	@RequestMapping("/tologinPage")
	public String toLoginPage(){
		return "login/login";
	}
	
	/**
	 * 案场助理登录页面控制器
	 * @return
	 */
	@RequestMapping("/engin_login")
	public String forMediToLogin(){
		//ModelAndView mv = new ModelAndView();
		return "login/engin_login";
	}
	/**
	 * 店长登录页面控制器
	 * @return
	 */
	@RequestMapping("/store_login")
	public String forStoreToLogin(){
		return "login/store_login";
	}
	/**
	 * app客户端登录页面控制器
	 * @return
	 */
	@RequestMapping("/app/tologin")
	public String forApplogin(){
		
		return "app/login/app_login";
	}
	
	/**
	 * app登录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/applogin")
	public Object forAppToLogin(User user,String sign){
		User u = userService.addUserTokenAndlogin(user);
		
		Map<String,Object> map = new HashMap<>();
		if (u != null) {
			map.put("userToken", u.getUserToken());
			this.request.getSession().setAttribute("userInfo", u);
			this.request.getSession().setAttribute("loginSign", "app");
			
			
			String cookieStr = u.getPhone() + "," + user.getPassword();
			Cookie cookie=new Cookie("phone",cookieStr);
			//设置Maximum Age
			cookie.setMaxAge(30*24*60*60);
			cookie.setPath("/");
			this.response.addCookie(cookie);
			Set<Role> rSet = u.getRoleId();
			String roleName = null;
			for(Role role:rSet){
				roleName = role.getRoleName();
			}
			//经理
			if(roleName.equals("director")){
				map.put("returenCode", "200");
				map.put("skipURL", "to_my_task_page");//暂时跳转到职业顾问页面
			}
			//店长/中介
			else if(roleName.equals("shopowner") || roleName.equals("medi")){
				map.put("returenCode", "200");
				map.put("skipURL", "to_goToChoice");
				//存城市session
				CitySessionDTO  csdto = userService.findCityIntoSession(u);
				this.request.getSession().setAttribute("csdto", csdto);
			}
			//置业顾问
			else if(roleName.equals("agent")){
				map.put("returenCode", "200");
				map.put("skipURL", "to_my_task_page");
			}else{
				map.put("returenCode", "401");
				map.put("msg", "您没有此登录权限!");
			}
			map.put("cookie", cookie);
		}else{
			map.put("returenCode", "402");
			map.put("msg", "你的用户名或密码错误!");
		}
		return map;
	}
	
	/**
	 * app记住密码时调用接口
	 * @param userToken
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/form_userToken_to_my_task_page")
	public Object chectUserToken(String userToken){
		Map map = new HashMap<>();
		if(userToken!=null && !userToken.equals("")){
			User u = userService.findByUserToken(userToken);
			if(u!=null){
				this.request.getSession().setAttribute("userInfo", u);
				this.request.getSession().setAttribute("loginSign", "app");
				Set<Role> role = u.getRoleId();
				for(Role r : role){
					if(r.getRoleId()==3){
						map.put("returenCode", "200");
						map.put("skipURL", "to_my_task_page");
					}else if(r.getRoleId()==1 || r.getRoleId()==2){
						map.put("returenCode", "200");
						map.put("skipURL", "to_goToChoice");
					}
				}
			}else{
				map.put("returenCode", 403);
				map.put("msg", "用户信息失效，请重新登录");
			}
		}
		return map;
	}
	
	/** 陈冬华2017-03-06
	 * 当用户点击首页门店注册进行跳转
	 * @return
	 */
	@RequestMapping("/shop_regs")
	public String shopReg(Model model){
		 List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		 model.addAttribute("provinces", findAllProvince);
		return "login/shop_regs";
	}
	
	/**
	 * cdh2017-03-27
	 * 注册门店失败跳转页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/shop_error")
	public String shopErrorView(Model model){
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		model.addAttribute("provinces", findAllProvince);
		model.addAttribute("data", "注册出错，请重试");
		return "/shop_regs";
	}
	
	/**
	 * 登录验证控制器
	 * @param user 用户
	 * @param sign 电脑登录/手机登录
	 * @param r_sign 中介/店长/职业顾问/案场助理	(Web:店长shopowner、案场助理engineer；App：职业顾问agent、中介经纪人medi)
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(User user,String sign,String r_sign){
		User u = userService.addUserTokenAndlogin(user);
		if (u != null) {
			this.request.getSession().setAttribute("userInfo", u);
			this.request.getSession().setAttribute("loginSign","web");
			Set<Role> rSet = u.getRoleId();
			String roleName = null;
			ModelAndView mv = new ModelAndView();
			if(sign!=null && sign.equals("app")){
				mv.setViewName("redirect:/app/tologin");
			}
			for(Role role:rSet){
				roleName = role.getRoleName();
			}
			//案场经理
			if(roleName.equals("director") && sign.equals("web") && r_sign.equals("engineer")){
				mv.setViewName("project/projectIndex");//暂时跳转到案场助理页面
				mv.addObject("data", u);
			}
			//案场助理
			else if(roleName.equals("engineer") && sign.equals("web") && r_sign.equals("engineer")){
				mv.setViewName("project/projectIndex");
				mv.addObject("data", u);
			}
			//店长web端
			else if(roleName.equals("shopowner") && sign.equals("web") && r_sign.equals("shopowner")){
				mv.setViewName("publicpage/shopsPublicPage");
				mv.addObject("data", u);
			}
			//店长app端
			/*else if(roleName.equals("shopowner") && sign.equals("app")){
				mv.setViewName("app/ad/choice");
				mv.addObject("data", u);
			}*/
			//中介经纪人
			/*else if(roleName.equals("medi") && sign.equals("app")){
				mv.setViewName("app/ad/choice");
				mv.addObject("data", u);
			}*/
			//置业顾问
			/*else if(roleName.equals("agent") && sign.equals("app")){
				mv.setViewName("redirect:/to_my_task_page");
				mv.addObject("data", u);
			}*/else{
				mv.addObject("data", "您没有此登录权限！！！");
				if(r_sign!=null && r_sign.equals("engineer")){
					mv.setViewName("login/login");
				}else if(r_sign!=null && r_sign.equals("shopowner") && sign.equals("web")){
					mv.setViewName("login/login");
				}
			}
			return mv;
		}else{
			ModelAndView mv = new ModelAndView();
			mv.addObject("data", "该用户不存在");
			if(r_sign!=null && r_sign.equals("engineer")){
				mv.setViewName("login/login");
			}else if(r_sign!=null && r_sign.equals("shopowner")){
				mv.setViewName("login/login");
			}else if(sign!=null && sign.equals("app")){
				mv.setViewName("redirect:/app/tologin");
			}
			return mv;
		}
	}
	
	/**
	 * Machine 连接访问方法
	 */
	@ResponseBody
	@RequestMapping("/SystemTime")
	public void machineConnection(){
		Map map = new HashMap<>();
		map.put("ReturnCode", 200);
		map.put("systemTime", System.currentTimeMillis()/1000);
		this.outMachineObjectString(map,null);
	}
	
	/**
	 * Machine 登录方法方法
	 * @param loginName
	 * @param Password
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("/base.login")
	public void machineLogin(@RequestParam(value="phone") String loginName,@RequestParam(value="password") String Password) throws IOException{
		Map map = new HashMap<>();
		User user = new User();
		user.setPhone(TripleDES.SimpleDecript(loginName));
		user.setPassword(TripleDES.SimpleDecript(Password));
		User u = userService.addUserTokenAndlogin(user);
		if(u!=null){
			this.request.getSession().setAttribute("token", u.getUserToken());
			JsonConfig jsonConfig = new JsonConfig(); 
			jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
			jsonConfig.setExcludes(new String[]{"roleId","password"});
			JSONObject jsonObject = JSONObject.fromObject(u, jsonConfig);
			map.put("ReturnCode", 200);
			map.put("User", jsonObject);
			map.put("token", u.getUserToken());
		}else{
			map.put("ReturnCode",400);
			map.put("User", "登录用户不存在");
		}
		this.outMachineObjectString(map,null);
	}
	
	 
	/**
	 * 2017-3-6 maoxy
	 * 平台页面登入控制器
	 * @return
	 */
	@RequestMapping("/system_login")
	public String forSystemToLogin(){
		return "login/system_login";
	}
	/**
	 * 2017-3-6 maoxy
	 * 处理平台用户登入
	 * @param u
	 * @param model
	 * @return
	 */
	
	@RequestMapping("system.login")
	public String systemLogin(User u,Model model){
		if(u==null){
			return "/login/system_index";
		}
		String password = u.getPassword();
		//密码加密后进行比对
		//u.setPassword(SysContent.md5(password));
		User user = userService.addUserTokenAndlogin(u);
		if(user!=null){
			Set<Role> roles = user.getRoleId();
			int roleId = -1 ;
			for(Role r:roles){
				roleId = r.getRoleId();
			}
			if(roleId!=5){
				model.addAttribute("data", "没有权限登入");
				return "/login/system_login";
			}
			//平台用户
			this.session.setAttribute("userInfo", user);
			this.request.getSession().setAttribute("loginSign","web");
			model.addAttribute("data", user);
			return "/system/all_count_count";
			
		}
		model.addAttribute("data", "密码或用户名错误");
		return "/login/system_login";
	}
	
	
	/**
	 * 校验前台注册的手机号码是否已经注册过
	 */
	@ResponseBody
	@RequestMapping("find_only_phone")
	public Map<String,Object> findExistPhone(String phoneNum){
		
		String regEx = "1(3|5|7|8|4)\\d{9}";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(phoneNum.trim());
		Map<String, Object> map = new HashMap<>();
		if(matcher.matches()){
			
			if(phoneNum != null && !phoneNum.equals("")){
				if(userService.findExistPhoneNum(phoneNum)){
					map.put("status", 200);//200代表数据库的手机号码没有重复
					map.put("message", "该号码可以使用");
					return map;
				}else{
					map.put("status", 202);//202代表手机号码重复
					map.put("message", "该号码已经注册，请更换手机号码");
					return map;
				}
			}
		}
		map.put("status", 202);
		map.put("message", "请输入正确的号码");
		return map;
	}
	
	/**
	 * 陈冬华 2017-03-06
	 * 门店注册
	 * @param shop
	 * @return
	 */
	@RequestMapping("/shop_register")
	public ModelAndView shopRegister(Shops shop, MultipartFile photoPic, MultipartFile licensePic, String province, String market, String area){
			ModelAndView mv = new ModelAndView();
				try {
					userService.addShop(shop,photoPic,licensePic, province, market, area);
				} catch (Exception e) {
					log.error(e.getMessage());
					mv.setViewName("redirect:shop_error");
					return mv;
				}
				mv.addObject("data", "注册成功，请等候审核通过");
				mv.setViewName("login/login");
			return mv;
	}
	
	
	/**
	 * 通过ajax异步获取所有的省份
	 */
	@RequestMapping("/get_all_prov")
	public void getProvinceByAjax(){
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		this.outListString(findAllProvince);
	}
	
	/**
	 *市区联动动态菜单
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/menu_list_city_area")
	public void getCityAreaMenu(String shengOrShi) {
		List<Map<String, String>> lmList = houseService.findCityAreaByShi(shengOrShi);
		this.outListString(lmList);
	}

	
	/**
	 * 2013-3-14 maoxy
	 * 异步加载加载所有的省
	 */
	@RequestMapping("/menu_list_province_first")
	public void getAllProvince(){
		List<CountryProvinceInfo> allProvince = houseService.findAllProvince();
		this.outListString(allProvince);
	}
	/**
	 * App 二维码下载
	 * @param name
	 * @return
	 */
	@RequestMapping("downloadApp/{name}")
	public String downLoad(@PathVariable(value="name") String name){
		return "app/apk/download";
	}
	
	/**
	 * 跳转到案场和门店在地图上展示信息页面
	 * @return
	 */
	@RequestMapping("/to_pro_shop_map_info")
	public String toProAndShopOnMapInfo(){
		return "project/proAndShopOnMapInfo";
	}
	
	/**
	 * 返回所有案场和门店的信息
	 */
	@ResponseBody
	@RequestMapping("/getProAndShopInfo")
	public Object showroAndShopOnMapInfo(String prvo,String city,String qu){
		String pcq = null;
		if(prvo!=null){
			pcq = prvo;
		}
		if(prvo!=null && city!=null){
			pcq += "-" + city;
		}
		if(prvo!=null && city!=null && qu!=null){
			pcq += "-" + qu;
		}
		Map mapList = projectService.findAllProAndShopInfo(pcq);
		return mapList;
	}
//	public ResponseEntity<byte[]> downloadLog(@PathVariable(value="name") String name){
//		String p = File.separator;
//		name = name+".apk";
//		String realPath = request.getSession().getServletContext().getRealPath("");
//		String path = realPath.substring(0, realPath.lastIndexOf(p));
//		File file=new File(path+p+"App"+p+name); 
//		HttpHeaders headers = new HttpHeaders();    
//		headers.setContentDispositionFormData("attachment", name);   
//		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
//		try {
//			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
//					headers, HttpStatus.CREATED);
//		} catch (IOException e) {
//			e.printStackTrace();
//			log.error(e.getMessage());
//			return null;
//		}
//	}
	
	
}
