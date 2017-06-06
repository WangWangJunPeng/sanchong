package com.sc.tradmaster.controller.ad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.Advertisement;
import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.advertisement.AdvertisementService;
import com.sc.tradmaster.service.project.ProjectService;

import com.sc.tradmaster.service.advertisement.impl.dto.AdvertisementDTO;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
import com.sc.tradmaster.service.house.HouseService;
/**
 * 2017-02-06
 * @author grl 
 *
 */
import com.sc.tradmaster.service.user.UserService;

@Controller("advertisementController")
public class AdvertisementController extends BaseController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "advertisementService")
	private AdvertisementService advertisementService;

	@Resource(name = "projectService")
	private ProjectService projectService;

	@Resource(name = "houseService")
	private HouseService houseService;

	/**
	 * 4个住按钮跳转到中介精选首页
	 */
	@RequestMapping(value = "/to_goToChoice")
	public String toGoToChoice() {

		return "app/ad/choice";
	}

	/**
	 * 4个住按钮跳转到中介房源页面
	 */
	@RequestMapping(value = "/to_goToMessageApp")
	public String toGoToMessageApp() {

		return "app/house/messageApp";
	}

	/**
	 * 4个住按钮跳转到中介业务按钮
	 */
	@RequestMapping(value = "/to_goToMidMyService")
	public String toGoToMidMyService() {

		return "app/house/midMyService";
	}

	/**
	 * 4个住按钮跳转到中介我的页面
	 */
	@RequestMapping(value = "/to_goToMediationInfo")
	public String toGoToMediationInfo() {

		return "app/house/mediationInfo";
	}

	/**
	 * 为你推荐 和 本地精选 都走这个方法
	 * 
	 * @param shi
	 * @param adPosition
	 */
	@RequestMapping(value = "/list_current_city_ads")
	public ModelAndView listCurrentCityAds(String shi, String adPosition) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		List<Advertisement> listAds = advertisementService.getAdsByUserInfo(user, shi, adPosition);

		if ("foryou".equals(adPosition)) {
			ModelAndView model = new ModelAndView("app/ad/forYou");
			model.addObject("data", listAds);
			return model;
		} else {
			ModelAndView model = new ModelAndView("app/ad/localChoice");
			model.addObject("data", listAds);
			return model;
		}
	}

	/**
	 * 精选列表（根据前台传的城市和显示类别显示精选、本地、普通的的广告，app页面精选、本地、普通模块均调用该方法）
	 * 
	 * @param shi
	 */
	@RequestMapping("/to_listAdsForYou")
	public void getAdsForYou(String shi, String adPosition) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		//获取session中的城市
		CitySessionDTO  csdto = (CitySessionDTO) this.request.getSession().getAttribute("csdto");
		
		List List = advertisementService.getAdsForYou(user, shi,csdto);
		this.outListString(List);
	}

	/**
	 * 精选本地精选首页
	 * 
	 * @param shi
	 */
	@RequestMapping("/to_listAdsLocation")
	public void getAdsLocation(String shi, String adPosition) {

	}

	

	@ResponseBody
	@RequestMapping("/query_sorting")
	public String selectSorting(Advertisement ad, String province, String market) {
		ad.setAdCity(province + "-" + market);
		int result = advertisementService.findSorting(ad);
		String s = result + "";
		return s;
	}

	/**
	 * 精选其他推荐首页
	 * 
	 * @param shi
	 */
	@RequestMapping("/to_listAdsForOthers")
	public void getAdsForOthers(String shi) {

	}

	/**
	 * 广告页返回当前city
	 * 
	 * @return
	 */
	@RequestMapping("/to_returnLocationCity")
	public void getReturnLocationCity() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");

		String shi = advertisementService.findCityLocation(u);
		Map<String, String> map = new HashMap<String, String>();
		map.put("shi", shi);
		this.outObjectString(map, null);
	}

	
}
