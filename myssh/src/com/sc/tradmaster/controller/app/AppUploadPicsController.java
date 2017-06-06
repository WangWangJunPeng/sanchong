package com.sc.tradmaster.controller.app;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
import com.sc.tradmaster.service.agent.AgentVisitRecordService;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.house.impl.dto.SalesHouseDTO;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.projectGuide.ProjectGuideService;
import com.sc.tradmaster.service.user.UserService;

import net.sf.json.JSONObject;

/**
 * 
 * @author Administrator
 *
 */
@Controller
public class AppUploadPicsController extends BaseController {
	
	@Resource(name = "agentVisitRecordService")
	private AgentVisitRecordService agentVisitRecordService;

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "houseService")
	private HouseService houseService;
	
	@Resource(name = "projectService")
	private ProjectService projectService;
	
	@Resource(name = "projectGuideService")
	private ProjectGuideService projectGuideService;
	
	/**
	 * 日历
	 * @return
	 */
	@RequestMapping("/to_calendar_page")
	public String toCalendar(){
		return "app/house/datePicker";
	}
	
	/**
	 * 将选择日期返回到index页面
	 * @param dateStr
	 * @param model
	 * @return
	 */
	@RequestMapping("/add_date_to_agent_index_page")
	public String calendarDataToIndexPage(String dateStr,Model model){
		if(dateStr!=null && !dateStr.equals("")){
			model.addAttribute("date", dateStr);
		}
		return "app/login/agent_index";
	}
	
	/**
	 * 置业顾问初始页(所有房源)
	 */
	@RequestMapping("/salesAllHouses")
	public ModelAndView appAllHouseList(User user) {
		List list = houseService.findAllHouses(user);
		ModelAndView model = new ModelAndView("app/house/saleHouseDetail");
		model.addObject("data", list);
		return model;
	}

	/**
	 * 置业顾问房源列表(搜索)
	 */
	@RequestMapping("/to_searchSalesHouses")
	public void housesList(String houseType, Double usefulArea, Integer floor)throws NumberFormatException, ParseException  {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		List list = houseService.findHouses(user, houseType, usefulArea, floor);
		this.outListString(list);
	}

	/**
	 * 置业顾问一房一档
	 */
	@RequestMapping("/to_salesOneHouse")
	public ModelAndView selectOneHouseBySales(Integer houseNum)throws ParseException {
		Map<String, Object> map = houseService.findById(houseNum);
		ModelAndView model = new ModelAndView();
		model.addObject("data", map);
		model.setViewName("app/house/agentOneHouseOneFile");
		return model;
	}

	/**
	 * 中介经纪人一房一档
	 */
	@RequestMapping("/to_jingjirenOneHouse")
	public ModelAndView jingjirenOneHouse(Integer houseNum)throws ParseException {
		Map<String, Object> map = houseService.findById(houseNum);
		ModelAndView model = new ModelAndView();
		model.addObject("data", map);
		model.setViewName("app/house/InventoryFile");
		return model;
	}


	/**
	 * 中介经纪人房源详情(搜索) 
	 */
	@RequestMapping("/to_jingjirenSearchHouse")
	public void midSearch(String projectId, String houseType, Double usefulArea, Integer floor)throws NumberFormatException, ParseException {
		List<SalesHouseDTO> list = houseService.findMidHouseByProperty(projectId, houseType, usefulArea, floor);
		
		this.outListString(list);
	}
	
	/**
	 *to_returnbackchioce
	 * @return
	 */
	@RequestMapping("/to_returnbackChoice")
	public ModelAndView toGoBackToChoice(String shengOrShi,String citySigle) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		String cityName = houseService.findCityNameById(shengOrShi);
		//获取session中的城市
		CitySessionDTO  csdto = (CitySessionDTO) this.request.getSession().getAttribute("csdto");
		//存城市session
		csdto = userService.findCityIntoSessionByCityId(shengOrShi,user);
		this.request.getSession().setAttribute("csdto", csdto);
		
		Map<String, String> map = new HashMap<>();
		map.put("shengshiId", csdto.getCityId());
		map.put("cityName", csdto.getCityName());
		map.put("citySigle", citySigle);
		
		ModelAndView model = new ModelAndView("app/ad/choice");
		model.addObject("cityDataInfo", map);
		return model;
	}
	
	/**
	 *to_returnbackmessageApp
	 * @return
	 */
	@RequestMapping("/to_returnbackMessageApp")
	public ModelAndView toGoBackToMessageApp(String shengOrShi,String citySigle) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		String cityName = houseService.findCityNameById(shengOrShi);
		//获取session中的城市
		CitySessionDTO  csdto = (CitySessionDTO) this.request.getSession().getAttribute("csdto");
		//存城市session
		csdto = userService.findCityIntoSessionByCityId(shengOrShi,user);
		this.request.getSession().setAttribute("csdto", csdto);
		
		Map<String, String> map = new HashMap<>();
		map.put("shengshiId", csdto.getCityId());
		map.put("cityName", csdto.getCityName());
		map.put("citySigle", citySigle);
		
		ModelAndView model = new ModelAndView("app/house/messageApp");
		model.addObject("cityDataInfo", map);
		return model;
	}

	
	/**
	 *选城市到城市列表页
	 * @return
	 */
	@RequestMapping("/to_goToCityList")
	public String toGoBackToGetCityId() {
		
		return "app/house/cityList";
	}
	/**
	 *选城市到城市列表页222222
	 * @return
	 */
	@RequestMapping("/to_goToCityToAreaList")
	public String toGoToCityToAreaList() {
		
		return "app/house/cityToAreaList";
	}
	
	/**
	 *精选页面默认的城市
	 */
	@RequestMapping("/to_getDefaultCity")
	public void toGetDefaultCity(String shengOrShi,String citySigle) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		//获取session中的城市
		CitySessionDTO  csdto = (CitySessionDTO) this.request.getSession().getAttribute("csdto");
		
		Map<String, String> map = houseService.findLocationCity(user,shengOrShi,citySigle,csdto);
		this.outObjectString(map, null);
	}
	

	/**
	 * 中介经纪人项目列表(按条件搜索案场信息)
	 * 
	 * @return
	 */
	@RequestMapping("/to_zhongjieSelectProject")
	public void midSelectProject(String conditions, String shi, String qu, String housType, String area,
			String recommend, String more)throws IOException {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		//获取session中的城市
		CitySessionDTO  csdto = (CitySessionDTO) this.request.getSession().getAttribute("csdto");
		
		String city = shi + "-" + qu;
		
		if ("".equals(qu) || qu == null) {
			String[] selectNames = {"conditions","city","housType","area","recommend","more"};
			String[] selectValues = {conditions,shi+"-",housType,area,recommend,more};
			List list = projectService.findProjectByConditions(user,selectNames, selectValues,csdto);
			this.outListString(list);
		} else {
			
			String[] selectNames = {"conditions","city","housType","area","recommend","more"};
			String[] selectValues = {conditions,shi+"-"+qu,housType,area,recommend,more};
			List list = projectService.findProjectByConditions(user,selectNames, selectValues,csdto);
			this.outListString(list);
		}

	}


	/**
	 * 中介案场详情跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_midOneProject")
	public ModelAndView toMidOneProject(String projectId) {
		ModelAndView model = new ModelAndView("app/house/midHouseDetail");
		model.addObject("dataInfo", projectId);
		return model;
	}

	/**
	 * 中介案场详情
	 * 
	 * @param projects
	 * @return
	 */
	@RequestMapping("/to_getMidOneProjectInfo")
	public void midSelectProject(String projectId) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		Map<String, Object> map = projectService.findProjectByProperty(u,projectId);
		this.outObjectString(map, null);
	}

	/**
	 * 置业顾问案场详情
	 * 
	 * @param projects
	 * @return
	 */
	@RequestMapping("/to_getSaleOneProject")
	public void saleSelectProject() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = u.getParentId();
		Map<String, Object> map = projectService.findProjectByProperty(u,projectId);
		this.outObjectString(map, null);
	}

	/**
	 * 中介端我的业务
	 */
	@RequestMapping("/to_midBusiness")
	public void allMidBusiness(String startTime, String endTime, String projectName) throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");

		List list = projectService.findMidBusiness(u, startTime, endTime, projectName);
		this.outListString(list);
	}

	/**
	 * 置业顾问app端我的业务
	 */
	@RequestMapping("/to_saleBusiness")
	public void allSaleBusiness() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		Map map = projectService.findSaleBusiness(u);

		this.outObjectString(map, null);
	}
	
	/**
	 * 新增分割线...................................................................................
	 * 
	 */
	
	/**
	 * 分销规则查看
	 */
	
	@RequestMapping("/get_projectGuideRule")
	public String getProjectGuideRule(String projectId,Model model){
		// 获取登录用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		ProjectGuide proGuide = projectGuideService.findById(projectId);
		
		model.addAttribute("projectGuideRule", proGuide);
		
		return "app/house/distributionRule";
	}
	
	/**业务___________________________________________________________获取中介门店批准时间
	 * 获取门店批准时间
	 */
	@RequestMapping("/to_getShopApproveTime")
	public void getShopApproveTime(){
		// 获取登录用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
	
		Map<String, Object> map = projectGuideService.findShopApproveTime(user);
		this.outObjectString(map, null);
	}
}
