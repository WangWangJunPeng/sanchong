package com.sc.tradmaster.controller.projectpics;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.ProjectPics;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.projectPics.ProjectPicsService;
import com.sc.tradmaster.service.user.UserService;

/**
 * 
 * @author grl 2017-01-15
 *
 */
@Controller("projectPicsController")
public class ProjectPicsController extends BaseController {

	@Resource(name = "projectPicsService")
	private ProjectPicsService projectPicsService;

	@Resource(name = "userService")
	private UserService userService;

	/**
	 * 效果图跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_effectPic")
	public String toEffectPic() {
		return "project/effectPicture";
	}

	/**
	 * 添加或更新案场照片集（效果图）
	 * 
	 * @param proPics
	 * @param pics
	 * @param isSave
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/add_updata_pro_pics")
	public Object addOrUpdataProPics(ProjectPics proPics,String[] urlArray, @RequestParam(value="pic") MultipartFile[] pic, String isSave) throws Exception {
		// 获取登录用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		// 执行添加户型业务逻辑
		Map map = new HashMap<>();
		for(MultipartFile p : pic){
			System.out.println(p.getOriginalFilename());
		}
		
		projectPicsService.addOrUpdataProPics(proPics,urlArray, user, pic);
		// 页面跳转
		if (isSave.equals("保存")) {
			map.put("code", 200);
			map.put("url", "to_effectPic");
		} else {
			map.put("code", 201);
			map.put("url", "to_houseType");
		}
		return map;
	}
	
	/**
	 * 案场照片集列表
	 */
	@ResponseBody
	@RequestMapping("/list_pro_pics")
	public void listAllProPics() {
		// 获取登录用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		// 执行添加户型业务逻辑
		List<ProjectPics> proPicsList = projectPicsService.findAllProPics(user);
		if(proPicsList!=null){
			this.outListString(proPicsList);
		}
	}
	
	/**
	 * 通过id获取该案场照片的信息
	 * @param proPicsId
	 */
	@ResponseBody
	@RequestMapping("/get_current_proPic_info")
	public void getProPicsInfo(String proPicsId){
		if(proPicsId!=null && !proPicsId.equals("")){
			ProjectPics proPic = projectPicsService.findById(proPicsId);
			this.outObjectString(proPic, null);
		}
	}
	
	/**
	 * 通过id获取url集合
	 * @param proPicsId
	 */
	@ResponseBody
	@RequestMapping("/get_current_proPic_url_list")
	public void getProPicsUrlList(String proPicsId){
		if(proPicsId!=null && !proPicsId.equals("")){
			List proPic = projectPicsService.findPicsUrlList(proPicsId);
			this.outListString(proPic);
		}
	}
	
	/**
	 * 通过id删除当前效果图信息
	 * @param proPicsId
	 */
	@ResponseBody
	@RequestMapping("/delete_curren_pro_pic_info")
	public void deleteProPics(String proPicsId){
		if(proPicsId!=null && !proPicsId.equals("")){
			projectPicsService.dropProPics(proPicsId);
			this.outString("{success:true,msg:'删除成功!'}");
		}
	}

}
