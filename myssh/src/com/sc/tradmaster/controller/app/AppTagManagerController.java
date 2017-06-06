package com.sc.tradmaster.controller.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.Tag;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.agent.AgentVisitRecordService;
import com.sc.tradmaster.service.tagService.AppTagManagerService;
import com.sc.tradmaster.service.tagService.TagService;
import com.sc.tradmaster.service.tagService.impl.dto.PCustomerInformation;
import com.sc.tradmaster.service.tagService.impl.dto.TagLib;

@Controller
public class AppTagManagerController extends BaseController{
	
	@Resource(name="tagService")
	private TagService tagService;
	
	@Resource(name="appTagManagerService")
	private AppTagManagerService appTagManagerService;
	
	@Resource(name="agentVisitRecordService")
	private AgentVisitRecordService agentVisitRecordService;
	
	
	/**
	 * 跳转客户页面
	 */
	
	@RequestMapping("/findCustomersInformation")
	public String toShowCustomersInformation(String projectCustomerId,String index,Model model){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String userId = user.getUserId();
		PCustomerInformation pc = appTagManagerService.findCustomersInformation(projectCustomerId,null,null);
		if(userId.equals(pc.getOwn())){
			pc.setOwn("1");
		}
		model.addAttribute("customer", pc);
		model.addAttribute("index",index);
		return "app/taglib/customerMessage";
	}
	
	@RequestMapping("/findCustomersInformation_phone")//TODO第二次点击添加
	public String toShowCustomersInfoByPhone(String index,String phone,Integer visitNo,RedirectAttributes ra){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		PCustomerInformation pc = appTagManagerService.findCustomersInformation(null,phone,projectId);
		ra.addAttribute("customerId",pc.getCustomerId());
		ra.addAttribute("visitNo", visitNo);
		ra.addAttribute("projectId", projectId);
		ra.addAttribute("index",index);
		return "redirect:/toCustomerEdit";
	}
	
	/**
	 * 展示客户已经拥有的标签
	 * @param targetId
	 * @param tagTypeId
	 * @param projectId
	 */
	@ResponseBody
	@RequestMapping("/findCustomersTags")
	public List<TagLib> showUserTagLib(String customerId,Integer tagTypeId,String projectId){
		List<TagLib> showTagLib_use = tagService.showTagLib_use(customerId,tagTypeId,projectId);
		return showTagLib_use;
	}
	/**
	 * 查询经理点评
	 * @param customerId
	 * @param projectId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findCustomerEvaluate")
	public Map<String,String> showManagerEvaluate(String customerId,String projectId){	
		Map<String,String> result = new HashMap<String, String>();
		ProjectCustomers pc = appTagManagerService.findCustomers(customerId,projectId);
		result.put("evaluate", pc.getEvaluate());
		return result;
	}
	/**
	 * 编辑页面
	 * @param customerId
	 * @param projectId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toCustomerEdit")
	public String toeditCustmer(String index,String customerId,String projectId,Integer visitNo, Model model){
		ProjectCustomers pc = appTagManagerService.findCustomers(customerId,projectId);
		if(pc==null){
			pc = new ProjectCustomers();
			VisitRecords findVisit = appTagManagerService.findVisit(visitNo);
			pc.setProjectCustomerPhone(findVisit.getPhone());
			pc.setProjectId(projectId);
		}
		model.addAttribute("customer", pc);
		model.addAttribute("visitNo", visitNo);
		model.addAttribute("index", index);
		return "app/taglib/aditCustomer";
	}
	/**
	 * 查询客户标签以及客户拥有的标签
	 * @param customerId
	 * @param projectId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findCustomerTag")
	public List<TagLib> findCustomerTag(String customerId,String projectId){
		List<TagLib> customerTags = appTagManagerService.findAllTagAndCustomerInfo(customerId,projectId);
		return customerTags;
	}
	
	@RequestMapping("/addCustomerTag")
	public String saveCustomerTag(String costomerId,Integer[] tags){
		tagService.addTagRelation(tags, costomerId);
		return "app/taglib/";
	}
	
	/**
	 * 转向客户分组列表
	 * @param ownerUserId 用户ID
	 * @param model
	 * @return
	 */
	@RequestMapping("/toCustomerList")
	public String toCoutomerList(Model model){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String userId = user.getUserId();
		Map<Tag, List<String>> customersList = appTagManagerService.findCustomersList(userId);
		model.addAttribute("cmap", customersList);
		return "app/house/agentMyCustomerList";
	}
	
	/**
	 * 跳转客户列表
	 * @param customerIds
	 * @param tagId
	 * @param model
	 * @return
	 */
	@RequestMapping("/realCustomerList")
	public String customerList(String index,String[] customerIds,Integer tagId,Model model){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		String userId = user.getUserId();
		if(customerIds==null){
			String tagName = "";
			if(index.equals("5")){
				tagName = "高";
			}
			if(index.equals("6")){
				tagName = "中";
			}
			if(index.equals("7")){
				tagName = "低";
			}
			if(index.equals("8")){
				tagName = "未知";
			}
			//Integer tagId1 = tagService.findTagIdByTagName(tagName,projectId);
			List<ProjectCustomers> pcs = appTagManagerService.findCustomerByTagId(tagName,userId,projectId);
			model.addAttribute("cList", pcs);
			model.addAttribute("tName", tagName);
			model.addAttribute("index", index);
		}else{
			Tag tag= tagService.findTagByTagIdAndProjectId(tagId,projectId);
			String tagName = tag.getTagName();
			List<ProjectCustomers> pcs = appTagManagerService.findCustomerByIds(customerIds);
			model.addAttribute("cList", pcs);
			model.addAttribute("tName", tagName);
			model.addAttribute("index", index);
		}
		return "app/house/customerList";
	}
	
	/**
	 * 新建客户
	 * @param phone
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/newCustomerEdit")
	public String toeditNewCustmer(String index,String phone,Integer visitNo,RedirectAttributes ra) throws Exception{
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		ProjectCustomers pc = appTagManagerService.findCustomerByPhone(phone,projectId);
		if(pc!=null){
			//客户存在
			//agentVisitRecordService.addorUpdataVistInfo(user,visitNo,phone);
//			跳转至客户详情页面
			//model.addAttribute("projectCustomerId", pc.getProjectCustomerId());
			//this.request.setAttribute("projectCustomerId", pc.getProjectCustomerId());
//			agentVisitRecordService.addOrUpdateAgentInsertCustomerInfo(user, pc.getProjectCustomerName(), pc.getProjectCustomerPhone(), pc.getDescription(), visitNo);
//			ra.addAttribute("projectCustomerId", pc.getProjectCustomerId());
//			ra.addAttribute("index", index);
//			return "redirect:/findCustomersInformation";
			if(pc.getOwnerUserId().equals(user.getUserId())){
				//自己的客户跳转编辑页
				appTagManagerService.updateVistInfo(pc,visitNo);
			//	agentVisitRecordService.addorUpdataVistInfo(user,visitNo,phone);
				ra.addAttribute("visitNo", visitNo);
				ra.addAttribute("projectId", projectId);
				ra.addAttribute("index", index);
				ra.addAttribute("customerId", pc.getProjectCustomerId());
				return "redirect:/toCustomerEdit";
			}else{
				//别人的客户跳转详情页面
				agentVisitRecordService.addOrUpdateAgentInsertCustomerInfo(user, pc.getProjectCustomerName(), pc.getProjectCustomerPhone(), pc.getDescription(), visitNo);
				//更新到访表tags
				appTagManagerService.addVisitTag(pc.getProjectCustomerId(),visitNo);
				ra.addAttribute("projectCustomerId", pc.getProjectCustomerId());
				ra.addAttribute("index", index);
				return "redirect:/findCustomersInformation";
			}
			
			
		}else{
			//不存在的用户
			agentVisitRecordService.addorUpdataVistInfo(user,visitNo,phone);
			ra.addAttribute("visitNo", visitNo);
			ra.addAttribute("projectId", projectId);
			ra.addAttribute("index", index);
			return "redirect:/toCustomerEdit";
		}
	}
	
	@ResponseBody
	@RequestMapping("/saveCustomerAndTag")
	public Map<String,Object> saveCustomerAndTag(String index,ProjectCustomers pc,Integer visitNo,Integer[] tagss) throws Exception{
		Map<String,Object> res = new HashMap<>();
		User user = (User) this.request.getSession().getAttribute("userInfo");
		List<Integer> ii = new ArrayList<>();
		for(Integer i:tagss){
			if(i!=null){
				ii.add(i);
			}
		}
		System.out.println(ii);
		Integer[] array =ii.toArray(new Integer[ii.size()]);
		ProjectCustomers pc1 = new ProjectCustomers();
		if(visitNo==null){
			//单纯保存
			pc1 = appTagManagerService.updateCustomerAndTags(pc,array);
			res.put("data", pc.getProjectCustomerId());
		}else{
			pc1 = agentVisitRecordService.addOrUpdateAgentInsertCustomerInfo(user, pc.getProjectCustomerName(),pc.getProjectCustomerPhone(), pc.getDescription(), visitNo);
			if(!StringUtils.isEmpty(pc.getProjectCustomerId())){
				//保存已经存在的客户
				tagService.addTagRelation(array, pc.getProjectCustomerId());
				res.put("data", pc.getProjectCustomerId());
			}else{
				//保存不存在的客户
				tagService.addTagRelation(array, pc1.getProjectCustomerId());
				res.put("data", pc1.getProjectCustomerId());
			}
			appTagManagerService.addVisitTag(visitNo, array);
		}
		res.put("status", 200);
		res.put("index", index);
		return	res;
	}
	
	@ResponseBody
	@RequestMapping("/findProjectOrHousesTags")
	public List<Tag> showProjectOrHouses(String targetId){
		List<Tag> findTargetTag = tagService.findTargetTag(targetId);
		return findTargetTag;
	}
	
	@ResponseBody
	@RequestMapping("/findProjectOrHousesTagsByUser")
	public List<Tag> showProjectOrHousesByUser(){
		//职业顾问
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String parentId = user.getParentId();
		List<Tag> findTargetTag = tagService.findTargetTag(parentId);
		return findTargetTag;
	}
	
}
