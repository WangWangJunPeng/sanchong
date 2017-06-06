package com.sc.tradmaster.controller.house;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.Page;

import net.sf.json.JSONObject;

@Controller("houseController")
public class HouseController extends BaseController {

	@Resource(name = "houseService")
	private HouseService houseService;

	@Resource(name = "userService")
	private UserService userService;

	/**
	 * 房源管理跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_house_manage_page")
	public String toHouseManage() {
		return "house/houseManage";
	}

	/**
	 * 跳转添加房源信息页面控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_add_house_manage_info")
	public String toAddHousesPage() {
		return "house/addHouseManage";
	}

	/**
	 * 户型菜单
	 * 2017-01-21 grl add
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/menu_list_house_type")
	public void getHoustTypeMenu() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if(user.getParentId()!=null && !user.getParentId().equals("")){
			List<Map<String,String>> htMenuList = houseService.findHouseTypeByProId(user.getParentId());
			this.outListString(htMenuList);
		}
	}

	/**
	 * 2017-01-20 grl add 案场助理新增房源
	 */
	@RequestMapping("/addHouse")
	public String insertHouse(House house, String checkIsOpen,String isSave) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if (checkIsOpen != null && checkIsOpen.equals("on")) {
			house.setIsOpen(1);
		} else {
			house.setIsOpen(0);
		}
		house.setHouseStatus(0);
		house.setProjectId(u.getParentId());
		houseService.addOrUpdate(house);
		if(isSave.equals("提交并返回房源列表")){
			return "redirect:/to_house_manage_page";
		}else{
			return "redirect:/to_add_house_manage_info";
		}
		
	}

	/**
	 * 2017-01-21 grl add 删除房源信息
	 */
	@RequestMapping("/delete_curren_house_info")
	public String deleteHouse(Integer hNum) {
		House house = houseService.findHouseByNum(hNum);
		house.setHouseStatus(2);
		houseService.addOrUpdate(house);
		return "redirect:/to_house_manage_page";
	}

	/**
	 * 2017-01-22 grl add 批量删除房源信息
	 * @param selectedHIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete_more_house_infos")
	public void deleteMorehouses(String[] selectedHIds){
		if(selectedHIds!=null && selectedHIds.length>0){
			for(String hId : selectedHIds){
				House house = houseService.findHouseByNum(Integer.valueOf(hId));
				house.setHouseStatus(2);
				houseService.addOrUpdate(house);
			}
		}
		JSONObject json = JSONObject.fromObject("{success:'true',data:'删除成功!'}");
		this.outString(json.toString());
	}
	/**
	 * 2017-01-21 grl add
	 * 
	 * @param hNum
	 */
	@ResponseBody
	@RequestMapping("/to_update_house_info")
	public ModelAndView updateHouse(Integer hNum) {
		ModelAndView mv = new ModelAndView("house/alterHouseManage");
		if (hNum != null && !hNum.equals("")) {
			House house = houseService.findHouseByNum(hNum);
			mv.addObject("house", house);
		}
		return mv;
	}

	/**
	 * 案场助理修改房源
	 */
	@RequestMapping("/updateHouseInfo")
	public String changeHouse(House house) {
		houseService.addOrUpdate(house);
		return "redirect:/selectHouse";
	}

	/**
	 * 案场助理删除房源
	 */
	@RequestMapping("/delHouse")
	public String delHouse(House[] house) {
		houseService.dropHouseByHouseStatus(house);
		return "redirect:/selectHouse";
	}

	/**
	 * 2017-01-19 grl 修改 案场助理搜索房源
	 */
	@ResponseBody
	@RequestMapping("/selectHouse")
	public void houseList(String decorationStandard, String houseTypeId, String houseStatus, String houseKind,Integer start,Integer limit) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if (user.getParentId() != null && !user.getParentId().equals("")) {
			Page page = new Page();
			page.setStart(start);
			page.setLimit(limit);
			String[] selectNames = { "decorationStandard", "houseStatus", "houseKind", "housType" };
			String[] selectValue = { decorationStandard, houseStatus, houseKind, houseTypeId };
			houseService.findByProperty(user, selectNames, selectValue, page);
			this.outPageString(page);
		}
	}

	/**
	 * 案场助理批量上架
	 */
	@RequestMapping("/updateUp")
	public String changeStatusToUp(String houseNumber) {
		String[] houseNum = houseNumber.split(",");
		
		houseService.updateUp(houseNum);
		return "redirect:/to_selectHouseUpDown";
	}

	/**
	 * 案场助理批量下架
	 */
	@RequestMapping("/updateDown")
	public String changeStatusToDown(String houseNumber) {
		String[] houseNum = houseNumber.split(",");
		houseService.updateDown(houseNum);
		return "redirect:/to_selectHouseUpDown";
	}

	/**
	 * 案场助理批量对经济人可见
	 */
	@RequestMapping("/updateIsOpen")
	public String changeOpen(String houseNumber) {
		String[] houseNum = houseNumber.split(",");
		houseService.updateIsOpen(houseNum);
		return "redirect:/to_selectHouseUpDown";
	}

	/**
	 * 案场助理批量对经济人不可见
	 */
	@RequestMapping("/updateIsClose")
	public String changeClose(String houseNumber) {
		String[] houseNum = houseNumber.split(",");
		houseService.updateIsClose(houseNum);
		return "redirect:/to_selectHouseUpDown";
	}

	
	/**
	 * 上下架管理搜索页面
	 */	
	@RequestMapping("/to_selectHouseUpDown")
	public String toSelectHouseUpDown(){
		return "house/upDownManage";
	}
	
	/**
	 * 上下架管理搜索页面
	 */	
	@RequestMapping("/selectHouseUpDown")
	public void selectHouses(String decorationStandard,String housType,String houseStatus,String houseKind,String isOpen,Integer start,Integer limit) {
		// 获取当前登录用户对象
		User u = (User) this.request.getSession().getAttribute("userInfo");
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		String[] selectNames = {"decorationStandard","housType","houseStatus","houseKind","isOpen"};
		String[] selectValue = {decorationStandard,housType,houseStatus,houseKind,isOpen};
		
		houseService.findHouseByProperty4(u, selectNames, selectValue, page);
		this.outPageString(page);
	}

	/**
	 * 一个案场下的所有房型(户型)
	 */	
	@RequestMapping("/selectHouseType")
	public ModelAndView allHouseType(User user){
		Page page = new Page();
		page.setStart(this.getStart());
		page.setLimit(this.getLimit());
		User u = (User) this.session.getAttribute("userInfo");
		TreeSet ts = houseService.findAllHouseType(u, page);
		ModelAndView model = new ModelAndView("house/upDownManage");
		model.addObject("data", ts);
		return model;
	}
	/**
	 * 中介管理员可售房源列表
	 */
	@RequestMapping("/zhongjieHouseList")
	public ModelAndView upList(House house, @RequestParam(value = "projectId", required = false) String projectId) {
		List list = houseService.findUp(projectId);
		ModelAndView model = new ModelAndView("house/search_list");
		model.addObject("data", list);
		return model;
	}

	/**
	 * 中介管理员一房一档(房源id搜索)
	 */
	@RequestMapping("/oneHouse")
	public ModelAndView OneHouse(Integer houseNum) throws ParseException{
		Map<String, Object> map = houseService.findById(houseNum);
		ModelAndView model = new ModelAndView("house/search_result");
		model.addObject("data", map);
		return model;

	}

	
	/**
	 * 房型动态菜单(web助理)
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upAndDown_menu_list_house_type")
	public void getUpAndDownHoustTypeMenu() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if(user.getParentId()!=null && !user.getParentId().equals("")){
			String projectId = user.getParentId();
			TreeSet<String> ts = houseService.findHouseCaptionByProIdWeb(projectId);
			this.outSetString(ts);
		}
	}

	/**
	 *置业顾问project的区域获得
	 * @return
	 */
	@RequestMapping("/to_getAgentProjectCity")
	public void getAgentProjectCity() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		String cityName = houseService.findAgentProjectCity(user);
		Map<String, String> map = new HashMap<>();
		map.put("cityName", cityName);
		this.outObjectString(map, null);
	}
	
	/**
	 * 房型动态菜单app中介
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/app_menu_list_house_houseType")
	public void getHouseTypeMenu(String projectId) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		TreeSet<String> ts = houseService.findHouseCaptionByProId( user, projectId);
		this.outSetString(ts);
	}
	
	/**
	 * 房型动态菜单app置业顾问
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/app_menu_list_house_houseType_agent")
	public void getHouseTypeMenuAgent() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		String projectId = user.getParentId();
		TreeSet<String> ts = houseService.findHouseCaptionByProId(user,projectId);
		this.outSetString(ts);
	}
	
	/**
	 * 面积动态菜单app中介
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/menu_list_house_usefulArea")
	public void getHouseUsefulAreaMenu(String projectId) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		TreeSet<Double> ts = houseService.findHouseUsefulAreaByProId(user,projectId);
		this.outSetString(ts);
	}
	
	/**
	 * 面积动态菜单app置业顾问
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/menu_list_house_usefulArea_agent")
	public void getHouseUsefulAreaMenuAgent() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		String projectId = user.getParentId();
		TreeSet<Double> ts = houseService.findHouseUsefulAreaByProId(user,projectId);
		this.outSetString(ts);
	}
	
	/**
	 *楼层动态菜单appzhongjie
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/menu_list_house_floor")
	public void getHouseFloorMenu(String projectId) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		TreeSet<Integer> ts = houseService.findHouseFloorByProId( user, projectId);
		this.outSetString(ts);
	}
	
	/**
	 *楼层动态菜单app置业顾问
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/menu_list_house_floor_agent")
	public void getHouseFloorMenuAgent() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		String projectId = user.getParentId();
		TreeSet<Integer> ts = houseService.findHouseFloorByProId( user, projectId);
		this.outSetString(ts);
	}
	
	/**
	 *根据省市区id获得Name
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getCityNameById")
	public void getProvinceCityMenu(String shengOrShi) {
		String cityName = houseService.findCityNameById(shengOrShi);
		Map<String, String> map = new HashMap<>();
		map.put("cityName", cityName);
		this.outObjectString(map, null);
	}
	
	
	/**
	 * 
	 * C测试使用
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/json_test")
	public Object thisIsTest(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/Administrator/Desktop/test.json"),"utf-8"));
			
			try {
				
				StringBuilder sb = new StringBuilder();
				String data = null;
				while ((data = br.readLine())!=null) {
					sb.append(data);
				}
				System.out.println(sb.toString());
				return sb.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/to_json_page")
	public String inToTestPage(){
		return "/test/testPic";
	}
	
}
