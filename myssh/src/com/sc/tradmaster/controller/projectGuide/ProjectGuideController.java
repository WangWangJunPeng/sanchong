package com.sc.tradmaster.controller.projectGuide;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.projectGuide.ProjectGuideService;
import com.sc.tradmaster.service.projectGuide.impl.dto.ProjectHouse;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.SysContent;

@Controller("projectGuideController")
public class ProjectGuideController extends BaseController {

	@Resource(name = "projectGuideService")
	private ProjectGuideService projectGuideService;

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name="houseService")
	private HouseService houseService;

	/**
	 * start***********************************(grl 2017-01-15 添加)
	 ********************************************/

	/**
	 * 带看业务定义页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_serviceDef")
	public String toServiceDefinition() {
		return "project/serviceDefinition";
	}

	/**
	 * 添加获取更新带看业务定义
	 * 
	 * @param pg
	 * @param isSave
	 * @return
	 */
	@RequestMapping("/add_updata_pro_guide")
	public String addOrUpdataProGuide(ProjectGuide pg, String checkIsDaiKan, String checkIsYiDi, String checkIsFast,
			String isSave) {
		// 获取登录用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		// 封装带看对象
		if (checkIsDaiKan!=null && checkIsDaiKan.equals("on")) {
			pg.setIsDaiKan(1);
		} else {
			pg.setIsDaiKan(0);
		}
		if (checkIsYiDi!=null && checkIsYiDi.equals("on")) {
			pg.setIsYiDi(1);
		} else {
			pg.setIsYiDi(0);
		}
		if (checkIsFast!=null && checkIsFast.equals("on")) {
			pg.setIsFast(1);
		} else {
			pg.setIsFast(0);
		}
		// 执行添加户型业务逻辑
		projectGuideService.addOrUpdate(pg, user);
		// 页面跳转
		if (isSave.equals("保存")) {
			return "redirect:/to_serviceDef";
		} else {
			return "redirect:/to_idManage";
		}
	}

	/**
	 * 通过Id获取当前带看义务定义
	 * 
	 * @param cpgId
	 */
	@RequestMapping("/get_current_pro_guide_info")
	public void getCurrentProGuide() {
		// 获取登录用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		if (user.getParentId() != null && !user.getParentId().equals("")) {
			ProjectGuide proGuide = projectGuideService.findById(user.getParentId());
			this.outObjectString(proGuide, null);
		}
	}

	/** end *******************************************************************************/


	/**
	 * 中介管理员案场列表跳转页
	 */
	@RequestMapping("/to_allProjects")
	public String toAllProjects(){
		return "house/search";
	}
	
	@ResponseBody
	@RequestMapping("get_city_name_by_parent_id")
	public Map<String, String> findCityNameByParentId(){
		User user = (User) SysContent.getSession().getAttribute("userInfo");
		String parentId = user.getParentId();
		Map<String, String> map = houseService.getCityByParentId(parentId);
		return map;
	}
	
	/**
	 * 中介端按条件搜案场信息ajax
	 */
	@RequestMapping("/findProjectHouse")
	public void selectProject(String sheng,String shi,String qu,String projectName,String isYouhui,String isDaiKan ) {
		Page page = new Page();
		page.setStart(this.getStart());
		page.setLimit(this.getLimit());
		String youhui = null;
		if (isYouhui!= null && isYouhui.equals("on") ) {
			youhui = "1";
		} else {
			youhui = "0";
		}
		int daikan;
		if (isDaiKan!=null && isDaiKan.equals("on") ) {
			daikan = 1;
		}else  {
			daikan = 0;
		}
		if (qu != null && !"".equals(qu)){
			String[] selectNames = {"city","projectName",isYouhui,"isDaiKan"};
			String[] selectValue = {sheng+"-"+shi+"-"+qu,projectName,youhui+"",daikan+""};
			List projectList = projectGuideService.findByProperty(selectNames, selectValue, page);
			this.outListString(projectList);
		} else {
			String[] selectNames = {"city","projectName",isYouhui,"isDaiKan"};
			String[] selectValue = {sheng+"-"+shi,projectName,youhui+"",daikan+""};
			List projectList = projectGuideService.findByProperty(selectNames, selectValue, page);
			this.outListString(projectList);
		}
	}
}
