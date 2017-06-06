package com.sc.tradmaster.controller.system;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.Advertisement;
import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.advertisement.AdvertisementService;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.system.SystemService;
import com.sc.tradmaster.service.system.impl.dto.AdDTO;
import com.sc.tradmaster.service.system.impl.dto.Partner;
import com.sc.tradmaster.service.system.impl.dto.ProjectsAndShopDTO;
import com.sc.tradmaster.service.system.impl.dto.ProjectsAndUsers;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.Page;

/**
 * 平台管理控制器
 * 
 * @author cdh
 *
 */
@Controller("systemController")
public class SystemController extends BaseController {
	
	@Resource(name = "advertisementService")
	private AdvertisementService advertisementService;
	
	@Resource(name="systemService")
	private SystemService systemService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name = "houseService")
	private HouseService houseService;
	
	@Resource(name = "projectService")
	private ProjectService projectService;
	
	
	/**
	 * 跳转合伙人新建关联页面
	 * 2017-3-9 maoxy
	 * @return
	 */
	@RequestMapping("toAdPartner_Relation")
	public String toAdManageRelation(Model model){
		//查询所有合伙人
		Set<User> users = systemService.findUsersByRoleId();
		List<User> userList = new ArrayList<>();
		for(User u:users){
			userList.add(u);
		}
		model.addAttribute("users", userList);
		return "system/addpartner_relation";
	}
	
	/**
	 * 2017-3-10 maoxy
	 * 异步加载合伙人关联页面数据
	 * @param status 1-案场请求；2-门店请求
	 */
	@RequestMapping("query_all")
	public void queryAll(Integer status){
		if(status==1){ //关联案场
			List<Project> othersProjects = systemService.findPartnerOtherProjects();
			this.outListString(othersProjects);
		}
		if(status==2){
			List<Shops> othersShops = systemService.findPartnerOtherShops();
			this.outListString(othersShops);
		}
	}
	/**
	 * 2017-3-10 maoxy
	 * 添加合伙人与门店或者案场的信息
	 * @param userId
	 * @param Pvalidity
	 * @param Svalidity
	 * @param projectIds
	 * @param shopIds
	 * @param isSave
	 * @return
	 */
	@RequestMapping("addPartner_relation")
	public String addPartnerRelation(String userId,Integer Pvalidity,Integer Svalidity,String[] projectIds,String[] shopIds,String isSave){
		if(projectIds!=null){
			if(projectIds.length>0){
				systemService.addPartnerAndProjects(userId,projectIds,Pvalidity);
			}
		}
		if(shopIds!=null){
			if(shopIds.length>0){
				systemService.addPartnerAndShops(userId,shopIds,Svalidity);
			}
		}
		if("提交并新增下一条".equals(isSave)){
			return "redirect: toAdPartner_Relation";
		}else{
			return "redirect: toPartnerList";
		}
		
	}
	
	/**
	 * 2017-3-11 maoxy
	 * 跳转至合伙人管理页面
	 * @return
	 */
	@RequestMapping("toPartnerList")
	public String toPartnerList(){
		return "system/partnerList";
	}
	
	/**
	 * 2017-3-12 maoxy
	 * 异步加载合伙人列表
	 */
	@RequestMapping("partner_relation_list")
	public void findPartnerAll(){
		List<Partner> partners = systemService.findPartners();
		this.outListString(partners);
//		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"roleId"}); 
//		String jsonData = JSONArray.fromObject(partners,config).toString();
//		String jsonString = "{total:" + partners.size() + ",root:"
//				+ jsonData + "}";
//		return jsonString;
		//this.outListString(partners);
	}
	
	/**
	 * 2017-3-13 maoxy
	 * 根据Id查看合伙人详情页
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping("query_partner")
	public String findPartnerById(String userId,Model model){
		Partner partner = systemService.findPartnerById(userId);
		model.addAttribute("partner", partner);
		return "system/partner_relation";
	}
	/**
	 * 更新合伙人的电话和关联消息
	 * @param user
	 * @param projectIds
	 * @param shopIds
	 * @param isSave
	 * @return
	 */
	@RequestMapping("changePartner_relation")
	public String changePartnerRel(User user,String[] projectIds,String[] shopIds,String isSave){
		systemService.updatePartnerRel(user,projectIds,shopIds);
		return "system/partnerList";
	}



//	/**
//	 * 获取平台管理中项目总数，置业顾问总数...
//	 */
//	@RequestMapping("all_system")
//	public String toSystemPage() {
//		return "/system/all_count_count";
//	}

	/**
	 * 将所有的项目总数，置业顾问总数传给前台
	 */
	@RequestMapping("all_system_count")
	public void allSystemCount() {
		List<Map<String, String>> list = systemService.getSystemCount();
		if (list != null) {
			this.outListString(list);
		}

	}

	/**
	 * 所有的项目中到访登记的数据
	 * 
	 * @param startTime
	 * @param endTime
	 */
	@RequestMapping("all_visit_person_count")
	public void allVisitPersonCount(String startTime, String endTime) {
		List<Map<String, String>> list = systemService.getRecordsNum(startTime, endTime);
		if (list != null) {
			this.outListString(list);
		}
	}

	/**
	 * 中介管理的备案，确认，认购的数据
	 * 
	 * @param startTime
	 * @param endTime
	 */
	@RequestMapping("all_meid_data_count")
	public void allMeidData(String startTime, String endTime) {

		List<Map<String, String>> list = systemService.getMeidNum(startTime, endTime);
		if (list != null) {
			this.outListString(list);
		}
	}
	
	/**
	 * 2017-3-15 maoxy
	 * 首页跳转
	 * @return
	 */
	@RequestMapping("system_index")
	public String goBackIndex(){
		return "system/all_count_count";
	}
	/**
	 * 2017-3-15 maoxy
	 * 跳转至广告列表
	 * @return
	 */
	@RequestMapping("system_to_adList")
	public String goAdList(Model model){
		//查询所有的广告
		List<AdDTO> adList = systemService.findAllAdvertisement();
		model.addAttribute("adList", adList);
		return "system/adList";
	}
	/**
	 * 2017-3-22 maoxy
	 * 跳转至修改广告列表
	 * @param adId
	 * @return
	 */
	@RequestMapping("system_to_updataAd")
	public String goUpdataAd(String adId,Model model){
		AdDTO ad = systemService.findAdByAdId(adId);
		List<CountryProvinceInfo> allProvince = houseService.findAllProvince();
		List<Project> projects = projectService.findAllBySystem();
		model.addAttribute("projects", projects);
		model.addAttribute("ad", ad);
		model.addAttribute("provinces", allProvince);
		return "system/alterAd";
	}
	/**
	 * 2017-3-15 maoxy
	 * 跳转至项目列表
	 * @return
	 */
	@RequestMapping("system_to_projectList")
	public String goProjectList(Model model){
		List<ProjectsAndUsers> pad = systemService.findAllProjectAndUsers();
		model.addAttribute("dataList", pad);
		return "system/projectList";
	}
	/**
	 * 2017-3-18 maoxy
	 * 显示log列表
	 * @param model
	 * @return
	 */
	@RequestMapping("showloadLog")
	public String showLogsList(Model model){
		List<String> names = new ArrayList<>();
		String p = File.separator;
		String realPath = this.request.getSession().getServletContext().getRealPath(p);
		String path =realPath+"static"+p+"logs"; 
        File file=new File(path);  
        File[] listFiles = file.listFiles();
        for(File f:listFiles){
        	String name = f.getName();
        	names.add(name);
        }
        model.addAttribute("logsData", names);
        return "system/logsList";
        
	}
	
	@RequestMapping("downloadLog")
	public ResponseEntity<byte[]> downloadLog(String name){
		try {
			String p = File.separator;
			String realPath = this.request.getSession().getServletContext().getRealPath(p);
			File file=new File(realPath+p+"static"+p+"logs"+p+name); 
			HttpHeaders headers = new HttpHeaders();    
			headers.setContentDispositionFormData("attachment", name);   
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
					headers, HttpStatus.CREATED);
		} catch (IOException e) {
			try {
				e.printStackTrace();
				this.response.getWriter().write(new String("下载出错，请稍后重试".getBytes(),"ISO8859-1"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}    
	}
	
	/**
	 * 2017-3-21 maoxy
	 * 跳转至增加合伙人页面
	 * @return
	 */
	@RequestMapping("system_toAddPartner")
	public String toAddPartner(){
		return "system/addPartner";
	}
	
	/**
	 * 2017-3-21 maoxy
	 * 新增合伙人
	 * @param user
	 * @param isSave
	 * @return
	 */
	
	@RequestMapping("system_addPartner")
	public String addPartner(User user,String isSave){
		boolean b = systemService.addPartner(user);
		if(b){
			if("提交后继续新增".equals(isSave)){
				return "redirect:system_toAdPartner";
			}else{
				return "redirect:toPartnerList";
			}
		}else{
			try {
				this.response.getWriter().write(new String("新增出错，请稍后重试".getBytes(),"ISO8859-1"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}


	/**
	 * 账号管理页面的跳转
	 * @return
	 */
	@RequestMapping("all_user_info_page")
	public String allUserInfoPage(){
		return "system/all_user_system";
	}


	/**
	 * 异步获取所有的用户信息
	 */
	@RequestMapping("system_all_user")
	public void findAllUser(String status){
		this.outListString(userService.findAllUser(status));
	}
	
	/**
	 * 
	 * 账号管理页面的用户信息编辑
	 * @param userId
	 * @param model
	 * @return	跳转页面
	 */
	@RequestMapping("to_edit_user_page")
	public String toEditUserPage(String userId,Integer roleId, Model model){
		User user = userService.findById(userId);
		model.addAttribute("userId", userId);
		model.addAttribute("phone", user.getPhone());
		model.addAttribute("userCaption", user.getUserCaption());
		model.addAttribute("roleId", roleId);
		model.addAttribute("idCard", user.getIdCard());
		return "system/edit_user_page";
	}
	
	/**
	 * 更改账号信息
	 * @param user
	 * @return
	 */
	@RequestMapping("update_user_info")
	public String updateUserSystemInfo(User user, String doSign, String role) {
		User u = (User) this.request.getSession().getAttribute("userInfo");
		userService.updateSystemUser(u,user, doSign, role); // reset表示重置密码
		return "redirect:all_user_info_page";
	}
	/**
	 * 2017-3-31 maoxy
	 * 转向标签管理页面
	 * @return
	 */
	@RequestMapping("system_tagManage")
	public String toTagMmanager(Model model){
		//List<TagLib> tagLibs = tagService.showTagLib(0);
		//model.addAttribute("tagLibs", tagLibs);
		return "system/tagmanager";
	}
	
	//跳转到对账单页面
	@RequestMapping("to_staement_page")
	public String toStatementPage(Model model){
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		model.addAttribute("provinces", findAllProvince);
		return "system/statement_count";
	}
	
	/**
	 * ajax异步查找对账单中的数据
	 * @param city
	 * @param startTime
	 * @param endTime
	 */
	@ResponseBody
	@RequestMapping("system_statement_count")
	public List<ProjectsAndShopDTO> getStatementOfAccount(String city, String startTime, String endTime){
		List<ProjectsAndShopDTO> list = systemService.findStatementOfAccount(city, startTime, endTime);
		return list;
	}
	
	/**
	 * 确认和取消到款
	 * @param houseNum
	 * @param isConfirm
	 * @param isSystemPayConfirm
	 * @return
	 */
	@RequestMapping("edit_commission_statement")
	public ModelAndView editCommissionStatement(Integer houseNum, Integer isConfirm, Integer isSystemPayConfirm){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		boolean flag = systemService.updateCommissionForm(houseNum, isConfirm, user);
		if(flag){
			ModelAndView mv = new ModelAndView();
			mv.addObject("data", 200);
			mv.setViewName("redirect:to_staement_page");
			return mv;
		}else{
			ModelAndView mv = new ModelAndView();
			mv.addObject("data", 202);
			mv.setViewName("redirect:to_staement_page");
			return mv;
		}
		
	}
	
	/**
	 * 进行门店报表页面的跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("system_shop_report")
	public String gotoShopReportPage(Model model){
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		model.addAttribute("provinces", findAllProvince);
		return "system/shop_report";
	}
	
	/**
	 * ajax异步获取门店的报表
	 * @param city
	 * @param startTime
	 * @param endTime
	 */
	@ResponseBody
	@RequestMapping("system_shop_report_forms")
	public List<Map<String, Object>> getShopReportForms(String city, String startTime, String endTime){
		List<Map<String, Object>> list = systemService.findShopReportForms(city, startTime, endTime);
		return list;
	}
	
	@RequestMapping("system_medi_report")
	public String gotoMediResportPage(Model model){
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		model.addAttribute("provinces", findAllProvince);
		return "system/medi_report";
	}

	/**
	 * ajax异步获取门店的报表
	 * @param city
	 * @param startTime
	 * @param endTime
	 */
	@ResponseBody
	@RequestMapping("system_medi_report_forms")
	public List<Map<String, Object>> getMediReportForms(String city, String startTime, String endTime){
		List<Map<String, Object>> list = systemService.findMediReportForms(city, startTime, endTime);
		return list;
	}
	
	
	/**
	 * 
	 * 进行项目报表页面的跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("system_poroject_report")
	public String gotoProjectReportPage(Model model){
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		model.addAttribute("provinces", findAllProvince);
		return "system/project_report";
	}


	/**
	 * ajax异步获取项目报表
	 * @param city
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping("system_project_report_forms")
	public List<Map<String, Object>> getProjectReportForms(String city, String startTime, String endTime){
		List<Map<String, Object>> list = systemService.findProjectReportForms(city, startTime, endTime);
		return list;
	}

	/**
	 * 跳转到添加广告页面 2017-3-6 maoxy
	 * 
	 * @return
	 */
	@RequestMapping("/toAddAd")
	public String toAddAd(Model model) {
		// 查询所有项目
		List<Project> projects = projectService.findAllBySystem();
		model.addAttribute("projects", projects);
		List<CountryProvinceInfo> allProvince = houseService.findAllProvince();
		model.addAttribute("provinces", allProvince);
		return "/system/addAdManage";
	}

	/**
	 * 添加广告 2017-3-6 maoxy
	 * 
	 * @param ad
	 * @param province
	 * @param market
	 * @param area
	 * @param type
	 * @return
	 */

	@RequestMapping("/add_advertisement")
	public String addAds(Advertisement ad, String province, String market, MultipartFile picFile, Model model,
			String isSave) {
		ad.setAdCity(province + "-" + market);
		if (ad.getState() == null) {
			ad.setState(0);
		}
		try {
			advertisementService.addAds(ad, picFile);
		} catch (Exception e) {
			model.addAttribute("data", "保存出错");
			return "forward:/toAddAd";
		}
		if (!"提交并返回广告列表".equals(isSave)) {// 提交后继续新增
			return "redirect:/toAddAd";
		} else {// 提交后返回列表
			return "redirect:/system_to_adList";
		}
	}
	
	/**
	 * 广告修改
	 * @param ad
	 * @return
	 */
	@RequestMapping("/ad_update_by_grl")
	public String alterAd(Advertisement ad,String province, String market,MultipartFile picFile) throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		ad.setAdCity(province + "-" + market);
		try {
			advertisementService.updateAdvertisement(u,ad,picFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/system_to_adList";
	}

	/**
	 * 进入案场客户管理页面
	 * @return
	 */
	@RequestMapping("to_system_project_customer_page")
	public String intoCustomerPage(){
		return "system/project_customer_info";
	}
		
	
}
