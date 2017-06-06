package com.sc.tradmaster.controller.directorApp;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sc.tradmaster.bean.ManagerOwnAnalyse;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.service.user.impl.dto.AllCheckedCustomerDTO;
import com.sc.tradmaster.service.user.impl.dto.GrDto;
import com.sc.tradmaster.service.user.impl.dto.RankingDto;
import com.sc.tradmaster.service.user.impl.dto.VisitRecordsDTO;

/**
 * 
 * @author wjp
 *
 */
@Controller
public class ManagerTaskController extends BaseController{

	@Resource(name = "userService")
	private UserService userService;
	
	
	/**
	 * 经理分析页面...................跳转控制器
	 * @return
	 */
	@RequestMapping("/to_getManagerAnalysePage")
	public String toGoToManagerAnalysePage(){
		return "经理分析________________________页面";
	}
	
	
	/**
	 * 经理我的页面...................跳转控制器
	 * @return
	 */
	@RequestMapping("/to_getManagerMyInfoPage")
	public String toGoToManagerMyInfoPage(){
		return "经理我的________________________页面";
	}
	
	
	/**
	 * 经理业务页面跳转控制器
	 * @return
	 */
	@RequestMapping("/to_goToManagerTask")
	public String toGoToManagerTask(){
		return "经理任务总页面................";
	}
	
	/**
	 * 经理任务页今日接访未登记跳转控制器
	 * 
	 */
	@RequestMapping("/to_getVisitNotRegisterList")
	public String toGoToVisitNotRegisterList(){
		return "经理我的_任务_接访未登记";
	}
	

	/**
	 * 经理任务页今日接访未登记
	 * @param todayTime
	 * @throws ParseException 
	 */
	@RequestMapping("/getVisitNotRegisterList")
	public void getVisitNotRegisterList(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		List<VisitRecordsDTO> vrdtoList = userService.findToadyVisitRecords(startTime, endTime, user);
		
		this.outListString(vrdtoList);
	}
	
	/**
	 * 案场今日已备案
	 * @param todayTime
	 * @throws ParseException 
	 */
	@RequestMapping("/getGuideRecordsByToday")
	public void getGuideRecordsByToday(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		Map<String, String> map = userService.findTodayGuideRecords(startTime, endTime, user);
		this.outObjectString(map, null);
	}
	
	/**
	 * 经理_任务_待完成_盘客数据显示
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	@RequestMapping("/getCheckCustomerByToday")
	public void getCheckCustomerByToday(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		Map<String, Integer> map = userService.findCheckCustomerNum(startTime, endTime, user);
		this.outObjectString(map, null);
	}
	
	/**
	 * 经理我的_任务_备案未到访跳转控制器
	 * @return
	 */
	@RequestMapping("/to_goToGuideRecordsHaveAndNotPage")
	public String toGoToGuideRecordsHaveAndNotPage(){
		return "经理我的_任务_备案未到访";
	}
	
	/**
	 * 案场备案详情.....每个中介门店的备案记录
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	@RequestMapping("/getGuideRecordsDetails")
	public void getGuideRecordsDetails(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		List<GrDto> grdtoList = userService.findGuideRecordsDetail(startTime, endTime, user);
		this.outListString(grdtoList);
	}
	
	/**
	 * 我的团队排名页面控制器
	 */
	@RequestMapping("/to_goToVisitRankingPage")
	public String toGoToVisitRankingPage(){
		return "经理我的_我的团队排名页面..........";
	}
	
	/**
	 * 接访排名
	 * @param startTime
	 * @param endTime
	 * @throws ParseException 
	 */
	@RequestMapping("/to_getVisitRanking")
	public void toGetVisitRanking(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		List<RankingDto> rkList = userService.findVisitRanking(startTime, endTime, user);
		this.outListString(rkList);
	}
	
	/**
	 * 认购排名
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	@RequestMapping("/to_getRengouRanking")
	public void toGetRengouRanking(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		List<RankingDto> rkList = userService.findRengouRanking(startTime, endTime, user);
		this.outListString(rkList);
	}
	
	/**
	 * 签约排名
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	@RequestMapping("/to_getContractRanking")
	public void toGetContractRanking(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		List<RankingDto> rkList = userService.findContractRanking(startTime, endTime, user);
		this.outListString(rkList);
	}

	/**成交转化率
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	@RequestMapping("/to_getSlewRateRanking")
	public void toGetSlewRateRanking(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		List<RankingDto> rkList = userService.findSlewRateRanking(startTime, endTime, user);
		this.outListString(rkList);
	}
	
	/**
	 * 业务报表页面跳转控制器
	 * @return
	 */
	@RequestMapping("/to_goToMyServiceStatement")
	public String toGoToMyServiceStatement(){
		return "经理_我的_业务报表_页面............";
	}
	
	/**
	 * 业务报表_________接访
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	@RequestMapping("/to_getVisitStatement")
	public void getVisitStatement(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		Map<String, Object> map = userService.findVisitStatement(startTime, endTime, user);
		this.outObjectString(map, null);
	}
	
	
	/**
	 * 业务报表_________成交
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	@RequestMapping("/to_getDealStatement")
	public void getDealStatement(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		Map<String, Object> map = userService.findDealStatement(startTime, endTime, user);
		this.outObjectString(map, null);
	}
	
	/**
	 * 业务报表_________外场
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	@RequestMapping("/to_getOutFieldStatement")
	public void getOutFieldStatement(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		Map<String, Object> map = userService.findOutFieldStatement(startTime, endTime, user);
		this.outObjectString(map, null);
	}
	
	/**
	 * 经理_任务_带盘客____________
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	@RequestMapping("/to_getReadyCheckedCustomer")
	public void getReadyCheckedCustomer(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		List<AllCheckedCustomerDTO> accdtoList = userService.findAllCheckedCustomer(startTime, endTime, user);
		this.outListString(accdtoList);
		
	}
	/**
	 * 经理点评_________
	 * @param projectCustomerId
	 */
	@RequestMapping("/to_updateProjectCustomer")
	public void toUpdateProjectCustomer(String projectCustomerId,String evaluate){
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		userService.updateProjectCustomerByManager(projectCustomerId, evaluate, user);
		
	}
	
	/**
	 * 归属置业顾问___________
	 * @param projectCustomerId
	 */
	@RequestMapping("/to_getCustomerAffiliation")
	public void getCustomerAffiliation(String projectCustomerId){
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		Map<String, String> map = userService.findCustomerAffiliation(projectCustomerId, user);
		this.outObjectString(map, null);
	}

	
	/**
	 *经理分析收藏标签____________________________________________________________________________________________________ 
	 */
	@RequestMapping("/to_getManagerAnalyseList")
	public void getManagerAnalyseList(){
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		List<ManagerOwnAnalyse> moaList = userService.findManagerOwnAnalyse(user);
		this.outListString(moaList);
	}
	
	
	/**
	 * 经理分析新增收藏标签_____________ 
	 * @param categoryId
	 */
	@RequestMapping("/to_addManagerAnalyse")
	public void addManagerAnalyse(Integer categoryId){
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		userService.addManagerOwnAnalyse(user, categoryId);
	}

	/**
	 * 经理分析删除收藏标签_____________ 
	 * @param categoryId
	 */
	@RequestMapping("/to_dropManagerAnalyse")
	public void dropManagerAnalyse(Integer categoryId){
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		userService.updateManagerOwnAnalyse(user, categoryId);
	}
	
	/**
	 * 观察客户接待成效控制器______________________
	 * 
	 */
	@RequestMapping("/to_goToSeeCustomerReceptionPage")
	public String goToSeeCustomerReceptionPage(Model model){
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		
		return "观察客户接待成效页面____________________";
	}
	
	/**
	 * 观察客户接待成效数据显示______________________
	 * @throws ParseException 
	 * 
	 */
	@RequestMapping("/to_getSeeCustomerReceptionInfo")
	public void getSeeCustomerReceptionInfo(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		Map<String, Object> map = userService.findReceptionByManager(startTime, endTime, user);
		this.outObjectString(map, null);
	}
	
	/**
	 * 观察新老客户通道控制器______________________
	 * 
	 */
	@RequestMapping("/to_goToSeeNewAndOldCustomerPage")
	public String goToSeeNewAndOldCustomerPage(Model model){
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		
		return "观察新老客户通道控制器______________________";
	}
	
	/**
	 * 观察新老客户通道______________________
	 * @throws ParseException 
	 * 
	 */
	@RequestMapping("/to_getSeeNewAndOldCustomerPassagewayInfo")
	public void getSeeNewAndOldCustomerPassagewayInfo(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		Map<String, Object> map = userService.findNewAndOldCustomerPassagewayInfo(startTime, endTime, user);
		this.outObjectString(map, null);
	}
	
	/**
	 * 观察指定接访_________________________________________________
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	@RequestMapping("/to_getSeeAppointCustomerReceptionInfo")
	public void getSeeAppointCustomerReceptionInfo(String startTime,String endTime) throws ParseException{
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		Map<String, Object> map = userService.findSeeAppointCustomerReceptionInfo(startTime, endTime, user);
		this.outObjectString(map, null);
	}
}
