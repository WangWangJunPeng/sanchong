package com.sc.tradmaster.controller.projectBenefits;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.ProjectBenefits;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.projectBenefits.ProjectBenefitsService;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.SysContent;

@Controller("projectBenefitsController")
public class ProjectBenefitsController extends BaseController{

	@Resource(name="projectBenefitsService")
	private ProjectBenefitsService projectBenefitsService;
	
	/**
	 * 案场助理优惠条款首页跳转控制器
	 * @throws ParseException 
	 * 
	 */	
	@RequestMapping("/to_benefitsManage")
	public String toGoToBenefitsManage(){
		return "projectBenefits/preferenceClause";
	}
	
	/**
	 * 案场助理优惠条款首页
	 * @throws ParseException 
	 * 
	 */	
	@RequestMapping("/to_selectBenefitsManage")
	public void getAllBenefits(Integer start,Integer limit) throws ParseException {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		projectBenefitsService.findAllHouseAndBenefits(user,page);
		this.outPageString(page);
	}
	
	
	/**
	 * 一个案场下所有价格优惠条款显示
	 * @throws ParseException 
	 * 
	 */	
	@RequestMapping("/to_allBenefits")
	public ModelAndView selectAllBenefits() throws ParseException {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		List list = projectBenefitsService.findAllBenefits(user);
		ModelAndView model = new ModelAndView("projectBenefits/manageClause");
		model.addObject("data", list);
		return model;
	} 
	
	/**
	 * 单条删除优惠条款
	 * 
	 */	
	@RequestMapping(value="/to_deleteOneBenefits")
	public ModelAndView dropOneBenefits(String benefitId) {
		
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		projectBenefitsService.dropOneBenefits(user, benefitId);
		ModelAndView model = new ModelAndView();
		model.setViewName("forward:/to_allBenefits");
		return model;
	}
	
	/**
	 * 批量删除优惠条款
	 * 
	 */	
	@RequestMapping("/to-deleteSomeBenefits")
	public String dropSomeBenefits(String someBenefitsId) {

		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		String[] benefitId = someBenefitsId.split(",");
		
		projectBenefitsService.dropSomeBenefits(user, benefitId);
		
		return "redirect:/to_allBenefits";
	}
	
	/**
	 * 新增优惠条款页面跳转
	 * 
	 */	
	@RequestMapping("/to_addBenefits")
	public String toAddBenefits() {
		return "projectBenefits/addClause";
	}
	
	/**
	 * 新增优惠条款 保存
	 * 
	 */	
	@RequestMapping("/to_insertBenefits")
	public ModelAndView insertBenefits(String benefitsName,String startTime,String endTime,Double fee,
			Integer type,Double benefitMoney,String caption) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
				
		projectBenefitsService.addBenefits(user, benefitsName,startTime,endTime,fee,type,benefitMoney,caption);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("forward:/to_allBenefits");
		
		return model;
	}
	
	/**
	 * 修改优惠条款页面跳转
	 * 
	 */
	@RequestMapping("/to_updateBenefits")
	public ModelAndView toUpdateBenefits(String benefitId) {
		
		ModelAndView model = new ModelAndView("projectBenefits/updateClause");
		model.addObject("data", benefitId);
		
		return model;
	}
	
	/**
	 * 修改优惠条款页面跳转
	 * 
	 */
	@ResponseBody
	@RequestMapping("/to_getOneBenefits")
	public void getBenefits(String benefitId) {
		
		ProjectBenefits pb = projectBenefitsService.findProjectBenefitsById(benefitId);
		pb.setFeeStr(SysContent.get2Double(pb.getFee()));
		pb.setBenefitMoneyStr(SysContent.get2Double(pb.getBenefitMoney()));
		pb.setBenefitPercentStr(SysContent.get2Double(pb.getBenefitPercent()));
		this.outObjectString(pb, null);
	}
	
	/**
	 * 修改优惠条款 保存
	 * 
	 */	
	@RequestMapping("/to_changeBenefits")
	public ModelAndView changeBenefits(String benefitId, String benefitsName,String startTime,String endTime,Double fee,
			Integer type,Double benefitMoney,String caption) {
		
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		projectBenefitsService.updateBenefits(user, benefitId,benefitsName,startTime,endTime,fee,type,benefitMoney,caption);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("forward:/to_allBenefits");
		
		return model;
	}
	
	/**
	 * 房源优惠组合页面
	 * @throws ParseException 
	 * 
	 */	
	@RequestMapping("/toBenefitsPage")
	public ModelAndView toBenefitsPage(String[] houseNums) throws ParseException {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		if (houseNums.length>0) {
			Map<String, Object> map = projectBenefitsService.findUseBenefits(user, houseNums);
			ModelAndView model = new ModelAndView("projectBenefits/setPreferenceGroup");
			model.addObject("data", map);
			return model;
		} else {
			ModelAndView model = new ModelAndView();
			model.setViewName("forward:/to_benefitsManage");
			return model;
		}
	}
	/**
	 * 获取houseNumber
	 * @throws ParseException 
	 * 
	 */	
	@ResponseBody
	@RequestMapping("/to_benefitsGroup")
	public void getDoingBenefits(String houseNumber) throws ParseException {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		String[] houseNum = houseNumber.split(",");
		
		this.outListString(Arrays.asList(houseNum));
	}
	
	/**
	 * 房源优惠组合排序
	 * @throws IOException 
	 * 
	 */	
	@RequestMapping("/to_sortBenefits")
	public void getSortBenefits(String benefitsNumber,String houseNum ) throws IOException {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		if (benefitsNumber != null && !"".equals(benefitsNumber)) {
			String[] benefits = benefitsNumber.split(",");
			String houseNumStr = (String) houseNum.substring(1, houseNum.length()-1);
			System.out.println(houseNumStr);
			String[] houseNums = houseNumStr.split(",");
			projectBenefitsService.updateHighMoney(user, benefits, houseNums);
		} 
	}
}
