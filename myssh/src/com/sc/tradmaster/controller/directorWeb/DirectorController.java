package com.sc.tradmaster.controller.directorWeb;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.director.ProjectReceiveService;
import com.sc.tradmaster.service.user.UserService;

/**
 * 案场经理端控制器
 * 
 * @author grl 2017-06-01
 *
 */
@Controller("directorController")
public class DirectorController extends BaseController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "projectReceiveService")
	private ProjectReceiveService projectReceiveService;

	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param anyDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/director_frist_page_letf_data")
	public Object getFristPageOnLeftOfData(String anyDate) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		//获取项目任务完成进度
		Map<String, String> map = projectReceiveService.findProjectTaskFinishedExtent(user,anyDate);
		
		return map;
	}

}
