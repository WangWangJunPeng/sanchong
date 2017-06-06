package com.sc.tradmaster.controller.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.Tag;
import com.sc.tradmaster.bean.TagType;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.tagService.TagService;
import com.sc.tradmaster.service.tagService.impl.dto.MyChildTag;
import com.sc.tradmaster.service.tagService.impl.dto.MyParentTag;
import com.sc.tradmaster.service.tagService.impl.dto.TagLib;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller("tagController")
public class TagController extends BaseController{
	
	@Resource(name="tagService")
	private TagService tagService;
	
	@Resource(name="projectService")
	private ProjectService projectService;
	
	
	@RequestMapping("/payone")
	public void jiajiekou1(){
		
	}
	
	@RequestMapping("/payTwo")
	public void jiajiekou2(){
		
	}
	
	
	/**
	 * 2017-4-6 maoxy
	 * 展示某个项目下的标签
	 * @param parentTagTpyeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("showTagLib")
	public List<TagLib> show(Integer tagTpyeId,String projectId,Integer status){
		return tagService.showTagLib(null,"10000",status,null);
	}
	/**
	 * 复制标准标签到某个项目
	 * @param projectId
	 */
	@RequestMapping("copyTagLib")
	public void copyTagLib2Project(String projectId){
		tagService.add_copyTagLib(projectId);
	}
	/**
	 * 查询标签根目录
	 * @return
	 */
	@ResponseBody
	@RequestMapping("showRootTagType")
	public List<TagType> showRootTagType(String projectId){
		return tagService.showRootTagType(projectId,0);
	}

	/**
	 * 添加标签类目
	 * @param tt
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addTagType")
	public Map<String ,Object> addTagType(TagType tt){
		Map<String ,Object> map = new HashMap<>();
		try {
			tagService.addTagType(tt);
			map.put("status", 200);
			map.put("message", "添加成功");
			return map;
		} catch (Exception e) {
			map.put("status", 202);
			map.put("message", "添加失败");
			return map;
		}
	}
	/**
	 * 删除标签类目
	 * @param tagTypeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteTagType")
	public Map<String,Object> deleteTagType(Integer tagTypeId,String projectId){
		Map<String,Object> map = new HashMap<>();
		try {
			tagService.dropTagType(tagTypeId,projectId);
			map.put("status", 200);
			map.put("message", "删除成功");
			return map;
		} catch (Exception e) {
			map.put("status", 202);
			map.put("message", "删除失败");
			return map;
		}
	}
	/**
	 * 修改/启用/禁用标签类目
	 * @param tagTypeId
	 * @param tagTypeName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("alertTagType")
	public Map<String,Object> alterTagType(TagType tagType){
		Map<String,Object> map = new HashMap<>();
		try {
			tagService.updateTagType(tagType);
			map.put("status", 200);
			map.put("message", "修改成功");
			return map;
		} catch (Exception e) {
			map.put("status", 202);
			map.put("message", "修改失败");
			return map;
		}
	}
	

	/**
	 * 添加标签
	 * @param tag
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addTag")
	public Map<String,Object> addTag(Tag tag){
		Map<String,Object> map = new HashMap<>();
		try {
			tagService.addTag(tag);
			map.put("status", 200);
			map.put("message", "添加成功");
			return map;
		} catch (Exception e) {
			map.put("status", 202);
			map.put("message", "添加失败");
			return map;
		}
		
	}
	/**
	 * 删除标签
	 * @param tagId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteTag")
	public Map<String,Object> deleteTag(Integer tagId,String projectId){
		Map<String,Object> map = new HashMap<>();
		try {
			tagService.dropTag(tagId,projectId);
			map.put("status", 200);
			map.put("message", "删除成功");
			return map;
		} catch (Exception e) {
			map.put("status", 202);
			map.put("message", "删除失败");
			return map;
		}
	}
	/**
	 * 修改标签
	 * @param tagId
	 * @param tagName
	 * @param dic
	 * @param isMultiple
	 * @return
	 */
	@ResponseBody
	@RequestMapping("alertTag")
	public Map<String,Object> alterTag(Tag tag){
		Map<String,Object> map = new HashMap<>();
		try {
			tagService.updateTag(tag);
			map.put("status", 200);
			map.put("message", "修改成功");
			return map;
		} catch (Exception e) {
			map.put("status", 202);
			map.put("message", "修改失败");
			return map;
		}
	}
	
	
	/**
	 * 
	 * 进入项目标签管理页面
	 * @return
	 */
	@RequestMapping("/to_project_all_tag_page")
	public String toProjectAllTagPage(){
		return "tags/projectLabel";
	}
	
	/**
	 * 
	 * ajax异步获取当前项目标签和标签类目
	 * @return
	 * 
	 */
	@ResponseBody
	@RequestMapping("get_project_all_tag_info")
	public List<TagLib> getProjectAllTagInfo(){
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		Project pro = projectService.findProjectById(user.getParentId());
		List<TagLib> tagLibList = tagService.showTagLib(null, pro.getProjectId(), null, null);
		Integer tagTypeId = 0;
		for(TagLib t : tagLibList){
			if("项目标签".equals(t.getTagTypeName())){
				tagTypeId = t.getTagTypeId();
			}
		}
		List<TagLib> list = tagService.showTagLib(tagTypeId, pro.getProjectId(), 1, null);
		return list;
	}
	
	/**
	 * ajax异步获取该项目已经点选的标签
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get_project_use_tag_info")
	public List<TagLib> getProjectUseTagInfo(){
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		List<TagLib> tagLibList = tagService.showTagLib(null, pro.getProjectId(), null, null);
		
		Integer tagTypeId = 0;
		
		for(TagLib t : tagLibList) {
			if("项目标签".equals(t.getTagTypeName())){
				tagTypeId = t.getTagTypeId();
			}
		}
		
		List<TagLib> list = tagService.showTagLib(tagTypeId, pro.getProjectId(), 1, pro.getProjectId());
		
		return list;
	}
	
	/**
	 * cdh 2017/05/08
	 * 	标签选择-项目标签（启用或禁用） 项目标签的添加
	 * @param tagTypes	标签类目集合
	 * @param status	启用或禁用
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/project_start_use_tags")
	public Map<String, Object> projectStartUseTags(String tags, Integer status){
		
		String[] strs = tags.split(",");
		
		Integer[] myTags = new Integer[strs.length];
		
		for(int i=0; i<strs.length; i++){
			
			myTags[i] = Integer.parseInt(strs[i]);
			System.out.println(myTags[i]);
		}
				
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		//通过当前登录的用户查找所属的项目
		Project project = projectService.findProjectById(user.getParentId());
		
		boolean flag = tagService.updateTargetTagType(project.getProjectId(), myTags, status);
		if(flag){
			Map<String, Object> map = new HashMap<>();
			map.put("data", 200);
			map.put("message", "保存成功");
			return map;
		}else{
			Map<String, Object> map = new HashMap<>();
			map.put("data", 202);
			map.put("message", "保存失败");
			return map;
		}
	}
	
	/**
	 * 
	 * 进入产品标签管理页面
	 * @return
	 */
	@RequestMapping("/to_product_all_tag_page")
	public ModelAndView toProductAllTagPage(){
		ModelAndView mv = new ModelAndView();
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		
		List<TagLib> tagLibList = tagService.showTagLib(null, pro.getProjectId(), null, null);
		
		Integer tagTypeId = 0;
		for(TagLib t : tagLibList){
			if("房源标签".equals(t.getTagTypeName())){
				tagTypeId = t.getTagTypeId();
			}
		}
		
		mv.addObject("superTagType",tagTypeId);
		mv.setViewName("tags/produceLabel");
		return mv;
	}
	
	/**
	 * 
	 * ajax异步获取产品所有标签
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get_product_all_tag")
	public Map<String, Object> getProductAllTagInfo(){
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		Map<String, Object> map = new HashMap<>();
		
		List<TagLib> tagLibList = tagService.showTagLib(null, pro.getProjectId(), null, null);
		
		Integer tagTypeId = 0;
		for(TagLib t : tagLibList){
			if("房源标签".equals(t.getTagTypeName())){
				tagTypeId = t.getTagTypeId();
			}
		}
		
		//产品已经启用的标签
		List<TagLib> useList = tagService.showTagLib(tagTypeId, pro.getProjectId(), 1, null);
		map.put("useList", useList);
		//产品未使用的标签
		List<TagLib> unUselist =  tagService.showTagLib(tagTypeId, pro.getProjectId(), 0, null);
		map.put("unUselist", unUselist);
		
		return map;
	}
	
	/**
	 * 
	 * 根据房源的tagType获取tag
	 * @param tagTypeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_produce_current_tag")
	public  Map<String, Object> getProduceCurrentTagInfo(Integer tagTypeId){
		
		Map<String, Object> map = new HashMap<>();
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		List<TagLib> list = tagService.showTagLib(tagTypeId, pro.getProjectId(), 1, null);
		
		map.put("list", list);
		
		return map;
	}
	
	/**
	 * 
	 * ajax异步添加标签和标签类目 (产品标签和用户标签都可以使用)
	 * @param obj	json对象
	 * @param tagType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add_tagtype_and_tags")
	public Map<String, Object> addProductOrCustomerTagTypeAndTags(String totalArray, Integer tagTypeId, Integer tagTypeStatus, Integer isMultiple, String tagTypeName, Integer superTagType, Integer originalTagType){
		
		System.out.println(totalArray);
		
		String jsonStr = totalArray.replace("\\", "");
		String jsonStr1 = jsonStr.replace("[", "{");
		String jsonStr2 = jsonStr1.replace("]", "}");
		String jsonStr3 = jsonStr2.replace("<\",", "[");
		String jsonStr4 = jsonStr3.replace(",\">\"", "]");
		 jsonStr4 = jsonStr3.replace("\">\"", "]");
		 jsonStr4 = jsonStr4.replace(",]", "]");
		 jsonStr4 = jsonStr4.replace("\t", "");
		String jsonStr5 = jsonStr4.substring(1, jsonStr4.length()-1);
		//jsonStr5 = jsonStr5.substring(, jsonStr5.length());
		//jsonStr5 = "["+jsonStr5+"]";
		//System.out.println(JSON.parse("["+jsonStr5+"]"));
		System.out.println(jsonStr5);
		Map<String, Object> map = new HashMap<>();
		
		JSONArray obj = new JSONArray().fromObject("["+jsonStr5+"]");
		
		
		List<MyParentTag> list = new ArrayList<>();
		for(int i =0; i<obj.size(); i++){
			JSONObject jObj =  obj.getJSONObject(i);
			Map classMap = new HashMap();
			classMap.put("tags", MyChildTag.class);
			MyParentTag my = (MyParentTag) jObj.toBean(jObj, MyParentTag.class, classMap);
			System.out.println(my);
			list.add(my);
		}
		/*List<MyParentTag> list = JSONArray.toList(obj, new MyParentTag(), new JsonConfig());
		
			System.out.println(totalArray);
			System.out.println();
		 */
			TagType tagType = new TagType();
			tagType.setTagTypeId(tagTypeId);
			tagType.setIsMultiple(isMultiple);
			tagType.setTagTypeStatus(tagTypeStatus);
			tagType.setTagTypeName(tagTypeName);
			tagType.setParentTagTypeId(superTagType);
			tagType.setOriginalTagType(originalTagType);
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		boolean flag = tagService.addBatchTagsAndSetTagTypeStatus(pro.getProjectId(), tagType, list);
		
		if(flag){
			map.put("data", 200);
			map.put("message", "添加成功");
		}else{
			map.put("data", 202);
			map.put("message", "保存失败");
		}
			
		return map;
	}
	
	/*public Map<String, Object> addCustomerTags(MyTagType tg){
		
		Map<String, Object>
	}*/
	
	/**
	 * 
	 * 进入客户标签管理页面
	 * @return
	 */
	/*@RequestMapping("/to_customer_tag_page")
	public ModelAndView toCustomerTagPage(){
		
		ModelAndView mv = new ModelAndView();
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		
		List<TagLib> tagLibList = tagService.showTagLib(null, pro.getProjectId(), null, null);
		
		Integer tagTypeId = 0;
		for(TagLib t : tagLibList){
			if("客户标签".equals(t.getTagTypeName())){
				tagTypeId = t.getTagTypeId();
			}
		}
		
		mv.addObject("superTagType",tagTypeId);
		mv.setViewName("tags/idInfor");
		return mv;
	}*/
	/**
	 * 
	 * 进入客户标签-基本信息管理页面
	 * @return
	 */
	@RequestMapping("/to_customer_info_tag_page")
	public ModelAndView toCustomerInfoTagPage(){
		
		ModelAndView mv = new ModelAndView();
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		
		List<TagLib> tagLibList = tagService.showTagLib(null, pro.getProjectId(), null, null);
		
		Integer tagTypeId = 0;
		for(TagLib t : tagLibList){
			for(TagLib tl : t.getTagLibs())
			if("身份信息".equals(tl.getTagTypeName())){
				tagTypeId = tl.getTagTypeId();
			}
		}
		
		mv.addObject("superTagType",tagTypeId);
		mv.setViewName("tags/idInfor");
		return mv;
	}
	
	/**
	 * 
	 * 进入客户标签-[客户意向]管理页面
	 * @return
	 */
	@RequestMapping("/to_customer_need_info_tag_page")
	public ModelAndView toCustomerNeedInfoTagPage(){
		
		ModelAndView mv = new ModelAndView();
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		
		List<TagLib> tagLibList = tagService.showTagLib(null, pro.getProjectId(), null, null);
		
		Integer tagTypeId = 0;
		for(TagLib tl : tagLibList){
			
			if("客户标签".equals(tl.getTagTypeName())){
				tagTypeId = tl.getTagTypeId();
			}
		}
		
		mv.addObject("superTagType",tagTypeId);
		mv.setViewName("tags/customerDemand");
		return mv;
	}
	
	/**
	 * 
	 * 获取[客户意向]的客户标签（已启用和未启用）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get_customer_need_all_tag_info")
	public Map<String, Object> getCustomerAllTagInfo(){
		
		Map<String,Object> map = new HashMap<>();
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		List<TagLib> tagLibList = tagService.showTagLib(null, pro.getProjectId(), null, null);
		
		Integer tagTypeId = 0;
		for(TagLib t : tagLibList){
			
			if("客户标签".equals(t.getTagTypeName())){
				tagTypeId = t.getTagTypeId();
			}
			
		}
		
		//查找已经启用用户标签
		List<TagLib> useTagList = tagService.showTagLib(tagTypeId, pro.getProjectId(), 1, null);
		map.put("useTagList", useTagList);
		//查找未启用的用户标签
		List<TagLib> unUseTagList = tagService.showTagLib(tagTypeId, pro.getProjectId(), 0, null);
		map.put("unUseTagList", unUseTagList);
		
		
		return map;
	}
	/**
	 * 
	 * 进入客户标签-[客户需求]管理页面
	 * @return
	 */
	@RequestMapping("/to_customer_desired_info_tag_page")
	public ModelAndView toCustomerDesiredInfoTagPage(){
		
		ModelAndView mv = new ModelAndView();
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		
		List<TagLib> tagLibList = tagService.showTagLib(null, pro.getProjectId(), null, null);
		
		Integer tagTypeId = 0;
		for(TagLib t : tagLibList){
			
			for(TagLib tl : t.getTagLibs()){
				
				if("客户需求".equals(tl.getTagTypeName())){
					tagTypeId = tl.getTagTypeId();
				}
			}
		}
		
		mv.addObject("superTagType",tagTypeId);
		mv.setViewName("tags/customerDesired");
		return mv;
	}
	
	/**
	 * 
	 * 获取[客户需求]的客户标签（已启用和未启用）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get_customer_desired_all_tag_info")
	public Map<String, Object> getCustomerDesiredAllTagInfo(){
		
		Map<String,Object> map = new HashMap<>();
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		List<TagLib> tagLibList = tagService.showTagLib(null, pro.getProjectId(), null, null);
		
		Integer tagTypeId = 0;
		for(TagLib t : tagLibList){
			for(TagLib tl : t.getTagLibs()){
				
				if("客户需求".equals(tl.getTagTypeName())){
					tagTypeId = tl.getTagTypeId();
				}
			}
			
		}
		
		//查找已经启用用户标签
		List<TagLib> useTagList = tagService.showTagLib(tagTypeId, pro.getProjectId(), 1, null);
		map.put("useTagList", useTagList);
		//查找未启用的用户标签
		List<TagLib> unUseTagList = tagService.showTagLib(tagTypeId, pro.getProjectId(), 0, null);
		map.put("unUseTagList", unUseTagList);
		
		
		return map;
	}
	
	
	/**
	 * 
	 * 获取[客户信息]的客户标签（已启用和未启用）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get_customer_info_tag_info")
	public Map<String, Object> getCustomerInfoTagInfo(){
		
		Map<String,Object> map = new HashMap<>();
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		List<TagLib> tagLibList = tagService.showTagLib(null, pro.getProjectId(), null, null);
		
		Integer tagTypeId = 0;
			
			for(TagLib t : tagLibList){
				for(TagLib tl : t.getTagLibs())
				if("身份信息".equals(tl.getTagTypeName())){
					tagTypeId = tl.getTagTypeId();
				}
			}
			
		
		
		//查找已经启用用户标签
		List<TagLib> useTagList = tagService.showTagLib(tagTypeId, pro.getProjectId(), 1, null);
		map.put("useTagList", useTagList);
		//查找未启用的用户标签
		List<TagLib> unUseTagList = tagService.showTagLib(tagTypeId, pro.getProjectId(), 0, null);
		map.put("unUseTagList", unUseTagList);
		
		
		return map;
	}
	
	/**
	 * 对标签类目的名称是否重复进行校验
	 * @param tagTypeName
	 * @param projectId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/tagtype_is_repetition")
	public Map<String, Object> findTagTypeIsRepetition(String tagTypeName){
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		Map<String, Object> map = new HashMap<>();
		boolean flag = tagService.findTagTypeIsRepetition(tagTypeName, pro.getProjectId());
		if(flag){
			map.put("flag", 200);
		}else{
			map.put("flag", 202);
			map.put("message", "标签类目：["+tagTypeName+"] 重复了，请换个名称再试");
		}
		return map;
	}
	
	/**
	 * 
	 * 对标签的名称是否重复进行校验
	 * @param tagTypeId
	 * @param parentTagId
	 * @param tagName
	 * @param projectId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/tag_is_repetition")
	public Map<String, Object> findTagIsRepetition(Integer tagTypeId, Integer parentTagId, String tagName) {
		
		Map<String, Object> map = new HashMap<>();
		
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		boolean flag = tagService.findTagIsRepetition(tagTypeId, parentTagId, tagName, pro.getProjectId());
		
		if(flag){
			map.put("flag", 200);
		}else{
			map.put("flag", 202);
			map.put("message", "标签名：[" + tagName + "] 出现重复，请换个名称重试");
		}
		return map;
	}
	
	
	/**
	 * 
	 * 删除标签类目和标签
	 * @param tagType
	 * @param tags
	 * @return
	 */
	@RequestMapping("/delete_tagtype_and_tags")
	public Map<String, Object> deleteTagTypeAndTags(Integer tagTypeId, Integer[] tags){
		Map<String, Object> map = new HashMap<>();
		
		boolean flag = tagService.dropTagTypeAndTags(tagTypeId, tags);
		if(flag){
			map.put("fg", 200);
		}else{
			map.put("fg", 202);
		}
		return map;
	}
	
}
