package com.sc.tradmaster.controller.feedback;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.feedback.FeedbackService;

/**
 * 2017-03-02
 * 
 * @author wjp
 *
 */
@Controller("feeedbakController")
public class FeeedbakController extends BaseController{
	
	@Resource(name = "feedbackService")
	private FeedbackService feedbackService;
	
	/**
	 * 添加或更新反馈信息
	 * @return
	 */	
	@RequestMapping("/to_addFeedback")
	public void getFeedback(String problem,@RequestParam(required=false)MultipartFile pic,String email,Integer stars)throws Exception {
		
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		feedbackService.addOrUpdate(user, problem, pic, email, stars);
	}
}
