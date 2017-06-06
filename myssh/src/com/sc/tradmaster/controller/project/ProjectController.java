package com.sc.tradmaster.controller.project;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.SystemChargeDefinition;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.project.impl.dto.UserDTO;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.Page;

/**
 * 2017-01-08
 * 
 * @author grl
 *
 */
@Controller("projectController")
@Scope("prototype")
public class ProjectController extends BaseController {

	@Resource(name = "projectService")
	private ProjectService projectService;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "houseService")
	private HouseService houseService;

	/**
	 * 案场助理首页跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_pro_index")
	public String toProIndex() {
		return "project/projectIndex";
	}

	/**
	 * 门店店长首页跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_store_index")
	public String toShopStoreIndex() {
		return "publicpage/shopsPublicPage";
	}
	
	//得到店长首页页面
	@RequestMapping("/to_store_shop_index")
	public String toShopStoreHex(){
		
		return "login/store_index";
	}

	/**
	 * 案场信息维护页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_pro_baseInfo")
	public String toProBaseInfo() {
		return "project/basicInformation";
	}

	/**
	 * 预售证管理页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_idManage")
	public String toIdManage() {
		return "project/idManage";
	}

	/**
	 * 成交业务页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_trade_business_page")
	public String toTradeBusinessPage() {
		return "project/tradeBusiness";
	}

	/**
	 * 职业顾问对账单页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_agent_bill_page")
	public String toAgentSureBillPage() {
		return "project/sureBill";
	}

	/**
	 * 门店对账单页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("to_shoper_bill_page")
	public String toShoperSureBillPage() {
		return "project/shopSureBill";
	}

	/**
	 * 案场信息维护-获取案场信息控制器
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_msg_pro")
	public void toMessageProtect() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		// 判断该用户是否有管理的案场
		if (u.getParentId() != null && u.getParentId() != "") {
			Project project = projectService.findProjectById(u.getParentId());
			System.out.println(project);
			this.outObjectString(project, null);
		}
	}

	/**
	 * 案场信息维护-数据保存控制器
	 * 
	 * @param project
	 * @param province
	 * @param market
	 * @param area
	 * @param isSave
	 * @param pic
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/save_pro_info")
	public ModelAndView addOrUpdateBaseInfo(Project project, String province, String market, String area,
			String isSave) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 设置省、市、区
		project.setCity(province + "-" + market + "-" + area);
		projectService.addOrUpdateProject(project, user);
		ModelAndView mv = new ModelAndView();
		if (isSave.equals("保存")) {
			mv.setViewName("project/basicInformation");
		} else {
			mv.setViewName("project/effectPicture");
		}
		return mv;
	}

	/**
	 * 添加或更新预售证信息
	 * 
	 * @param idManageNum
	 * @param pic
	 * @param isSave
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/add_or_update_id_mange_for_pro")
	public String addOrUpdateIdManage(String idManageNum, MultipartFile pic, String isSave) throws Exception {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		// 判断该用户是否有管理的案场
		if (u.getParentId() != null && !u.equals("")) {
			projectService.addIdManageInfo(u, idManageNum, pic);
		}
		if (isSave.equals("保存")) {
			return "redirect:/to_idManage";
		} else {
			return "redirect:/to_buyRule";
		}
	}

	/**
	 * 修改当前预售证信息
	 * 
	 * @param idManageNum
	 * @param pic
	 * @param index
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/updata_curren_idmanage_info")
	public String updateIdManage(String idManageNum, MultipartFile pic, int index) throws Exception {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		// 判断该用户是否有管理的案场
		if (u.getParentId() != null && !u.equals("") && index >= 0) {
			projectService.updateIdManageInfo(u, idManageNum, pic, index);
		}
		return "redirect:/to_idManage";
	}

	/**
	 * 预售证信息列表
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/list_id_manage_infos")
	public void listIdManage() throws Exception {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		// 判断该用户是否有管理的案场
		if (u.getParentId() != null && !u.equals("")) {
			Project pro = projectService.findProjectById(u.getParentId());
			String urlStr = pro.getPresalePermissionURL();
			List<Map<String, String>> urlList = this.outStringToList(urlStr);
			List<Map> list = projectService.findCurrentIdManageBeenUsed(urlList);
			this.outListString(list);
		}
	}

	/**
	 * 通过项目id和预售证所在数组中的下标将其删除
	 * 
	 * @param pId
	 * @param index
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delete_id_manage_pro_pic_index")
	public void deleteIdManageByProPicIndex(int index) throws Exception {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		// 执行后台逻辑
		if (u.getParentId() != null && !u.getParentId().equals("")) {
			projectService.dropIdManageByProPicIndex(u.getParentId(), index);
			this.outString("");
		}
	}

	/**
	 * 通过下标获取当前预售证信息
	 * 
	 * @param index
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/curren_id_manage_info")
	public void currentIdManage(int index) throws Exception {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		// 判断该用户是否有管理的案场
		if (u.getParentId() != null && !u.equals("")) {
			Project pro = projectService.findProjectById(u.getParentId());
			String urlStr = pro.getPresalePermissionURL();
			List<Map<String, String>> urlList = this.outStringToList(urlStr);
			Map<String, String> currenIM = urlList.get(index);
			this.outObjectString(currenIM, null);
		}
	}

	/**
	 * 今日待办-counts
	 */
	@ResponseBody
	@RequestMapping("/agent_page_today_need_doing")
	public void agentFristPageForCounts() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if (user != null) {
			Map mapCount = projectService.findCountInfo(user);
			this.outObjectString(mapCount, null);
		}

	}

	/**
	 * 今日待办-list
	 * 
	 * @param title
	 */
	@ResponseBody
	@RequestMapping("/agent_page_list")
	public void agentFristPageForList(Integer num) {
		String title = null;
		if (num == 0) {
			title = "enterBuy";
		} else if (num == 1) {
			title = "getMoney";
		} else if (num == 2) {
			title = "waitSign";
		} else if (num == 3) {
			title = "waitCash";
		}
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if (user != null) {
			List listInfo = projectService.findInfoAboutProForAgent(user, title);
			this.outListString(listInfo);
		}
	}

	/**
	 * 今日待办 - list 查看 跳转控制器
	 */
	@RequestMapping("/to_see_buy_apply")
	public ModelAndView toseeBuyApplyPage(Integer houseNum) {
		ModelAndView mv = new ModelAndView("project/purchaseApply");
		mv.addObject("houseNum", houseNum);
		return mv;
	}

	/**
	 * 今日待办 - 查看认购申请
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/get_see_buy_apply_date")
	public void seeBuyApply(Integer houseNum) throws Exception {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if (user != null) {
			Map<String, Object> map = projectService.findCurrentHouseBuyApply(user, houseNum);
			this.outObjectString(map, null);
		}

	}

	/**
	 * 今日待办 - 认购申请 审核
	 */
	@ResponseBody
	@RequestMapping("agree_check_buy_apply_date")
	public void agreeCheckBuyApply(Integer recordNo, String checkReson) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		try {
			projectService.updateAgreeBuyApply(u, recordNo, checkReson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 今日待办 - 认购申请 拒绝
	 */
	@RequestMapping("refuse_buy_apply_date")
	public String refuseBuyApply(Integer recordNo, String reason) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		try {
			projectService.updateRefuseBuyApply(u, recordNo, reason);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/to_trade_business_page";
	}

	/**
	 * 今日案场 - counts
	 */
	@ResponseBody
	@RequestMapping("/agent_today_pro_counts")
	public void agentFristPageTodayProCounts() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if (user != null) {
			Map mapCount = projectService.findTodayProCounts(user);
			this.outObjectString(mapCount, null);
		}
	}

	/**
	 * 今日案场 - list
	 * 
	 * @param title
	 */
	@ResponseBody
	@RequestMapping("/agent_today_pro_list")
	public void agentFristPageTodayList(String title) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if (user != null) {
			List listInfo = projectService.findTodayProList(user, title);
			this.outListString(listInfo);
		}
	}

	/**
	 * 帐号管理页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_account_manager_page")
	public String toAccountsManagerPage() {
		return "accountsNumManage/accountManage";
	}

	/**
	 * 用户信息编辑页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_edit_account_page")
	public ModelAndView toEditAccountPage(String userId) {
		ModelAndView mv = new ModelAndView();
		if (userId != null && !userId.equals("")) {
			UserDTO user = projectService.findUserById(userId);
			mv.setViewName("accountsNumManage/editAccount");
			mv.addObject("user", user);
		}
		return mv;
	}

	/**
	 * 帐号管理 list
	 */
	@RequestMapping("/accountsNum_agent_info_list")
	public void accountsNumManageList(String selectStatus) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		Page page = new Page();
		if (user != null) {
			List listAllAgentInfo = projectService.findAllAgentByProId(user, selectStatus, page);
			this.outListString(listAllAgentInfo);
		}
	}

	/**
	 * 帐号管理 新增页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/goto_accountsnum_manage_page")
	public String toAddEnginerOrAgentPage() {
		return "accountsNumManage/addAccount";
	}

	/**
	 * 帐号管理 新增、更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/account_manager_to_add_or_update")
	public Object saveOrUpdateEnginerOrAgent(User user, String rightSign, String returnSign) {
		// 获取当前登录用户对象
		User loginuser = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(loginuser.getUserId());
		// 调用后台业务逻辑
		Map map = userService.addOrUpdateUser(u, user, rightSign);
		/*if (returnSign != null && returnSign.equals("提交后继续新增下一个")) {
			 map.put("url", "goto_accountsnum_manage_page");
		} else {
			map.put("url","to_account_manager_page");
		}*/
		return map;
	}

	/**
	 * 帐号管理 list 密码重置、用户启用、用户删除
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/account_manager_to_update_userInfo")
	public void setUpUsers(User user, String doSign) {
		userService.updateUserInfo(user, doSign);
	}

	/**
	 * 经纪人管理页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_medi_manager_page")
	public String toMediManagerPage() {
		return "projectCustomer/shopAgentManagent";
	}

	/**
	 * 经纪人管理 编辑页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_edit_Shop_manager_page")
	public ModelAndView toEditShopManagerPage(String userId) {
		ModelAndView mv = new ModelAndView();
		if (userId != null && !userId.equals("")) {
			UserDTO user = projectService.findUserById(userId);
			mv.setViewName("projectCustomer/shopAgentChange");
			mv.addObject("user", user);
			System.out.println(user);
		}
		return mv;
	}

	/**
	 * 经纪人管理 新增页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/goto_medi_manage_page")
	public String toAddOrMediPage() {
		return "projectCustomer/shopAddAgent";
	}

	/**
	 * 经纪人管理 list
	 */
	@RequestMapping("/medi_manage_info_list")
	public void mediManageList(String selectRole, String enOrDisable) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if (user != null) {
			List listAllAgentInfo = projectService.findAllMediByShopId(user, selectRole, enOrDisable);
			this.outListString(listAllAgentInfo);
		}
	}

	/**
	 * 经济人管理 新增、更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/medi_manager_to_add_or_update")
	public String saveOrUpdateMediOrAgent(User user, String rightSign, String returnSign) {
		// 获取当前登录用户对象
		User loginuser = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(loginuser.getUserId());
		// 调用后台业务逻辑
		userService.addOrUpdateMediUser(u, user, rightSign);
		// 根据请求跳转相应页面
		if ("提交后继续新增下一个".equals(returnSign)) {
			return "redirect:/goto_medi_manage_page";
		} else {
			return "redirect:/to_medi_manager_page";
		}

	}

	/**
	 * 今日门店 - counts
	 */
	@ResponseBody
	@RequestMapping("/shoper_today_shop_counts")
	public void shoperFristPageTodayProCounts() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if (user != null) {
			Map mapCount = projectService.findTodayShopCounts(user);
			this.outObjectString(mapCount, null);
		}
	}

	/**
	 * 今日门店 - list
	 */
	@ResponseBody
	@RequestMapping("/shoper_today_shop_list")
	public void shoperFristPageTodayList(Integer num) {
		String title = null;
		if (num == 0) {
			title = "enterbuy";
		} else if (num == 1) {
			title = "record";
		} else if (num == 2) {
			title = "guid";
		} else if (num == 3) {
			title = "willexpire";
		}
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if (user != null) {
			List list = projectService.findTodayShopList(user, title);
			this.outListString(list);
		}
	}

	/**
	 * 判断基本信息是否填写完整
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/basicInfoIsFull")
	public Object basecInfoIsFull() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map map = projectService.basicInfoIsFull(user);
		return map;
	}

	/**
	 * 成交业务 购买申请
	 * 
	 * @param doSign
	 */
	@ResponseBody
	@RequestMapping("/buy_apply_{doSign}_list")
	public void tradeBusinessForBuyApply(@PathVariable String doSign) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		try {
			List appointList = projectService.findBuyApplyAppointList(user, doSign);
			this.outListString(appointList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 成交业务 定金到款
	 * 
	 * @param doSign
	 */
	@ResponseBody
	@RequestMapping("/trade_business_for_get_bargain_list")
	public void tradeBusinessForGetBargain(String doSign) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		try {
			List appointList = projectService.findGetBargainAppointList(user, doSign);
			this.outListString(appointList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 成交业务 定金到款 未确认的到款 确认操作
	 * 
	 * @param recordNo
	 */
	@ResponseBody
	@RequestMapping("/trade_business_for_enter_bargain")
	public void tradeBusinessForEnterGetBargain(Integer recordNo) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		try {
			projectService.addOrUpdateContractRecords(user, recordNo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 成交业务 签约确认
	 * 
	 * @param doSign
	 */
	@ResponseBody
	@RequestMapping("/trade_business_for_sign_list")
	public void tradeBusinessForSign(String doSign) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		try {
			List appointList = projectService.findSignAppointList(user, doSign);
			this.outListString(appointList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 成交业务 签约确认 未确认的签约 确认操作
	 * 
	 * @param recordNo
	 */
	@ResponseBody
	@RequestMapping("/trade_business_for_enter_sign")
	public void tradeBusinessForSignCheck(Integer recordNo) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.addorUpdateContractRecordsForSign(user, recordNo);
	}

	/**
	 * 对账单 - list 案场助理端
	 * 
	 * @param startTime
	 * @param endTime
	 */
	@ResponseBody
	@RequestMapping("/agent_check_bill_list")
	public void agentCheckBill(String startTime, String endTime) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		List billList = projectService.findContracRecordsBill(user, startTime, endTime);
		this.outListString(billList);
	}

	/**
	 * 对账单 - list中结款操作
	 * 
	 * @param desc
	 *            确认到款描述
	 * @param enterSign
	 *            确认到款端标识（平台、中介）
	 */
	@RequestMapping("/agent_check_bill_list_enter")
	public String agentCheckBillEnter(String desc, Integer recordNo, String enterSign) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.addorUpdateContractRecordsForSignEnterPayMoney(user, recordNo, enterSign, desc);
		return "redirect:/to_agent_bill_page";
	}

	/**
	 * 对账单 - list中取消结款操作
	 * 
	 * @param desc
	 *            确认到款描述
	 * @param enterSign
	 *            确认到款端标识（平台、中介）
	 */
	@RequestMapping("/agent_check_bill_list_cancel")
	public String agentCheckBillCancelEnter(String desc, Integer recordNo, String cancelSign) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.addorUpdateContractRecordsForSignCancelPayMoney(user, recordNo, cancelSign, desc);
		return "redirect:/to_agent_bill_page";
	}

	/**
	 * 对账单 - list 中介端
	 * 
	 * @param startTime
	 * @param endTime
	 * @param start
	 * @param limit
	 * @param projectId
	 */
	@ResponseBody
	@RequestMapping("/shoper_check_bill_list")
	public void shoperCheckBill(String startTime, String endTime, Integer start, Integer limit, String projectId) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.findShoperContracRecordsBill(user, startTime, endTime, page, projectId);
		this.outPageString(page);
	}
	
	/**
	 * 对账单 - list 中介端 案场菜单
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_project_menu")
	public Object getProjectMenuData(){
		List<Map<String, String>> proMenu = projectService.findAllProjectForMenu();
		return proMenu;
	}

	/**
	 * 对账单 - list中到款操作
	 * 
	 * @param desc
	 *            确认到款描述
	 */
	@ResponseBody
	@RequestMapping("/shoper_check_bill_list_enter_or_cancel")
	public void shoperCheckBillEnterOrCancel(String desc, Integer recordNo, String doSingle) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.addorUpdateContractRecordsForShoperEnterReceiveMoney(user, recordNo, desc, doSingle);
	}

	/**
	 * 对账单 list 经纪人数据排行
	 * 
	 * @param startTime
	 * @param endTime
	 * @param start
	 * @param limit
	 * @param projectId
	 */
	@ResponseBody
	@RequestMapping("/shoper_medi_data_order_list")
	public void mediDataOrder(String startTime, String endTime, Integer start, Integer limit, String projectId) {
		
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.findAllMediRelativeDataInfo(user, startTime, endTime, page, projectId);
		this.outPageString(page);
	}

	/**
	 * 对账单 list 项目数据排行
	 * 
	 * @param startTime
	 * @param endTime
	 * @param start
	 * @param limit
	 * @param projectId
	 */
	@ResponseBody
	@RequestMapping("/shoper_pro_data_order_list")
	public void proDataOrderForMedi(String startTime, String endTime, Integer start, Integer limit, String projectId) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.findAllProRelativeDataForMediInfo(user, startTime, endTime, page, projectId);
		this.outPageString(page);
	}

	/**
	 * 2017-3-6 转向增加案场页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAddProject")
	public String toAddProject(Model model) {
		List<CountryProvinceInfo> allProvince = houseService.findAllProvince();
		model.addAttribute("provinces", allProvince);
		return "/system/addProjectManage";
	}

	/**
	 * 2017-3-6 maoxy 增加案场
	 * 
	 * @return
	 */
	@RequestMapping("/addProject")
	public String addProject(Project project, User u, String province, String market, String area, String isSave) {
		project.setCity(province + "-" + market + "-" + area);
		projectService.addOrUpdateFirstProject(project, u);
		if (!"提交并返回项目列表".equals(isSave)) {// 提交后继续新增
			return "redirect:/toAddProject";
		} else {// 提交后返回列表
			return "redirect:/system_to_projectList";
		}
	}

	/**
	 * 陈冬华 2017-03-06 通过ajax传过来的地址遍历项目相关数据并返回
	 * 
	 * @param projectId
	 */
	@RequestMapping("/find_projectId")
	public void selectProjectsByCity(String city) {
		List allProjects = projectService.findAllProjectsNameByCity(city);
		if (allProjects != null) {
			this.outListString(allProjects);
		}
	}

	/**
	 * 跳转到佣金定义页面 2017-03-06 cdh
	 * 
	 * @return
	 */
	@RequestMapping("/to_paltform")
	public String toPlatform(Model model) {
		List<CountryProvinceInfo> allProvince = houseService.findAllProvince();
		model.addAttribute("provinces", allProvince);
		return "project/platform_lis";
	}

	/**
	 * 添加佣金定义 2017-03-06
	 * 
	 * @param s
	 * @return
	 */
	@RequestMapping("/platform_definition")
	public ModelAndView addPlatform(SystemChargeDefinition s) {

		if (s != null) { // 如果表单为空

			ModelAndView mv = new ModelAndView();
			projectService.addPlatform(s);
			mv.addObject("data", s);
			mv.setViewName("project/platform_dengdai");
			return mv;
		} else {
			ModelAndView mv = new ModelAndView();
			mv.addObject("data", "表单信息填写错误，请重新填写");
			mv.setViewName("/platform_definition");// 可能要重新填写view的名字
			return mv;
		}
	}

	/**
	 * 陈冬华 2017-03-06 将所有已经定义了佣金的项目发往前端
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/all_project_list")
	public ModelAndView selectAllProject() {
		List<Map<String, Object>> mapList = projectService.selectAllPlatform();
		Map<String, Object> projectMap = null;
		for (Map<String, Object> map : mapList) {
			projectMap = new HashMap<String, Object>();
			String projectId = (String) map.get("projectId");
			if (projectId != null && !projectId.equals("")) {
				String projectName = projectService.findProjectById(projectId).getProjectName();

				System.out.println(projectId + " : " + projectName);
				map.put("projectName", projectName);
			} else {
				map.put("projectName", null);
			}
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("data", mapList);
		mv.addObject("data2", projectMap); // 此map为projectName
		mv.setViewName("/project/platform_lists");
		return mv;
	}

	/**
	 * 
	 * 进入所有状态的中介门店审核页面
	 * 
	 * @return
	 */
	@RequestMapping("/all_reviewd_page")
	public String shopReviewListPage() {
		return "/project/review_shop";
	}

	/**
	 * 进入审核中的中介门店审核页面
	 * 
	 * @return
	 */
	@RequestMapping("/apply_reviewd_page")
	public String shopApplyReviewdPage() {
		return "/project/apply_shop";
	}

	/**
	 * 进入已经通过审核的中介门店页面
	 * 
	 * @return
	 */
	@RequestMapping("/passed_reviewd_page")
	public String shopPassedReviewPage() {
		return "/project/passed_shop";
	}

	/**
	 * 进入已经拒绝的中介门店页面
	 * 
	 * @return
	 */
	@RequestMapping("/refuse_reviewd_page")
	public String shopRefuseReviewPage() {
		return "/project/refuse_shop";
	}

	/**
	 * 中介门店的审核列表 applyId是前端筛选条件
	 * 
	 * @param applyId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/all_shops_reviewd")
	public void shopReviewdList(Integer start, Integer limit, Integer applyId) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);

		projectService.findAllReviewedShops(page, applyId);
		this.outPageString(page);
	}

	/**
	 * 中介门店的审核界面信息
	 * 
	 * @param shopId
	 * @param tags
	 * @return
	 */
	@RequestMapping("/select_shop_reviewd")
	public ModelAndView selectShopReviewd(Integer shopId) {

		User u = (User) this.request.getSession().getAttribute("userInfo");
		if (u != null && !u.equals("")) {
			Shops shop = projectService.findReviewdShopById(shopId);
			if (shop != null) {
				// 将区号转换成城市名
				String cityNo = shop.getCity();
				String cityName = projectService.findCityNameByCityNum(cityNo);
				shop.setCity(cityName + shop.getAddress());
				ModelAndView mv = new ModelAndView();
				mv.addObject("data", shop);
				mv.setViewName("/project/shop_reviewed");
				return mv;
			} else {
				ModelAndView mv = new ModelAndView();
				mv.addObject("data", "未找到该门店信息");
				return mv;
			}
		} else {
			ModelAndView mv = new ModelAndView();
			mv.addObject("data", "您没有登录，请登录后在进行操作");
			mv.setViewName("/login/store_login");
			return mv;
		}
	}

	/**
	 * 
	 * 进行门店的审核 tag代表是否通过审核
	 * 
	 * @param shopId
	 * @param tag
	 * @return
	 */
	@ResponseBody
	@RequestMapping("add_shop_reviewd")
	public String doShopReviewd(Shops shop, Integer tag) {
		User u = (User) this.request.getSession().getAttribute("userInfo");
		if (shop.getAuditOpinion().trim() == null)
			shop.setAuditOpinion("");
		if (u != null && !u.equals("")) {
			String ret = projectService.addReviewShopById(u, shop, tag);
			if (ret.equals("success")) { // 表示通过申请
				return "success";
			} else if (ret.equals("failure")) { // 表示拒绝申请
				return "jujue";
			} else { // 表示shop为空
				return "not data";
			}
		} else {
			return "login/engin_login";
		}
	}

	/**
	 * 
	 * 进行门店的删除的操作
	 * 
	 * @param shopId
	 * @return
	 */
	@RequestMapping("/delete_shop")
	public String deleteShop(Integer shopId) {
		projectService.dropShopByShopId(shopId);
		return "redirect:/apply_reviewd_page";
	}

	/**
	 * 业务 门店 页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_shoper_business_page")
	public String toShoperBusinessPage() {
		return "project/shopBusiness";
	}

	/**
	 * 业务 门店 list
	 * 
	 * @param cusOrProName
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param start
	 * @param limit
	 */
	@RequestMapping("/list_shoper_business_info")
	public void shoperBusinessList(String cusOrProName, String startTime, String endTime, String status, Integer start, Integer limit) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.findCusForShopBusiness(user, cusOrProName, startTime, endTime, status, page);
		this.outPageString(page);
	}

	/*---------------------新功能-中介成就----------------------------*/

	/**
	 * 本月中介签约
	 */
	@RequestMapping("/signed_in_this_month")
	public void signedInThisMonth() {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		List<ContractRecords> list = projectService.findThisMonthSignedInfo(user);
		this.outListString(list);
	}

	/**
	 * 本月中介认购
	 */
	@RequestMapping("/contract_in_this_month")
	public void contractInThisMonth() {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		List<ContractRecords> list = projectService.findThisMonthApplyAndAgreeContract(user);
		this.outListString(list);
	}

	/**
	 * 本月新增客户
	 */
	@RequestMapping("/new_customer_in_this_month")
	public void newCustomerInThisMonth() {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		List<ProjectCustomers> list = projectService.findNewCustomerInThisMonth(user);
		this.outListString(list);
	}

	/**
	 * 本月中介备案
	 */
	@RequestMapping("/records_in_this_month")
	public void recordInThisMonth() {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		this.outListString(projectService.findGuideRecordInThieMonth(user));
	}
	/*--------------中介成就-end-----------------*/

	// 认筹客户 页面跳转控制器
	public String toConfessCustomerPage() {
		return "";
	}

	// 认筹客户 list
	public Object getConfessCustomerList() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		List confCusList = projectService.findConfessOfCurrentUser(user);
		return confCusList;
	}

}
