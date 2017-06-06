package com.sc.tradmaster.controller.guideRecords;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.guideRecords.GuideRecordsService;
import com.sc.tradmaster.service.guideRecords.impl.dto.GuideRecordsDTO;

@Controller("guideRecordsController")
public class GuideRecordsController extends BaseController{
	
	@Resource(name = "guideRecordsService")
	private GuideRecordsService guideRecordsService;
	
	/**
	 * 增加备案记录表
	 * @throws IOException 
	 */	
	@RequestMapping("/addGuideRecords")
	public ModelAndView addGuideRecords(String allProjectId,String customerName,String phone) throws Exception {
		// 获取session中的用户信息
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		String[] projectId = allProjectId.split(",");
		List<GuideRecordsDTO> grdtoList = guideRecordsService.addGuideRecords(user, customerName, phone, projectId);
		int i = 0;
		int j = 0;
		for (GuideRecordsDTO guideRecordsDTO : grdtoList) {
			if (guideRecordsDTO.getApplyStatus()==0){
				i++;
			}
			if (guideRecordsDTO.getApplyStatus()==3){
				j++;
			}
		}
		ModelAndView model = new ModelAndView("app/house/spareResult");
		model.addObject("data", grdtoList);
		model.addObject("dataSuccess", i);
		model.addObject("dataFail", j);
		
		return model;
	}
	
	
	/**
	 * 备案客户表(申请状态的)
	 */	
	@RequestMapping("/GuideRecordsList")
	public ModelAndView selectGuideRecords(String startTime, String endTime, String projectId)throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		List list = guideRecordsService.findGuideRecordsList(u, projectId, startTime, endTime);
		ModelAndView model = new ModelAndView("app/house/recordCustomer");
		model.addObject("data", list);
		return model;
	}

	/**
	 * 将过期的客户表页面跳转控制器
	 */	
	@RequestMapping("/to_goToNearOverdue")
	public ModelAndView toGoToNearOverdue(String projectId){
		ModelAndView model = new ModelAndView("app/house/upcomingCustomer");
		model.addObject("dataInfo", projectId);
		return model;
	}
	
	/**
	 * 将过期的客户表
	 */	
	@RequestMapping("/to_nearOverdue")
	public void selectnerOver(String projectId,String phone)throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		Map<String, Object> map = guideRecordsService.findNearOver(u, projectId,phone);
		this.outObjectString(map, null);
	}
}
