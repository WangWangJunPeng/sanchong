package com.sc.tradmaster.controller.contractRecords;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.EnterBuyManAndRealEnterBuyManRelation;
import com.sc.tradmaster.bean.ProjectBenefits;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.RealEnterBuyMan;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.contractRecords.ContractRecordsService;
import com.sc.tradmaster.service.contractRecords.impl.dto.AllMyContractRecordDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.NewContractRecordStatusDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.NewContractRecordsDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.OrderDetailsDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.RealEnterBuyDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.ShopCustomerDTO;
import com.sc.tradmaster.service.house.impl.dto.HouseListDTO;
import com.sc.tradmaster.service.user.UserService;

import net.sf.json.JSONObject;

@Controller("contractRecordsController")
public class ContractRecordsController extends BaseController {

	@Resource(name = "contractRecordsService")
	private ContractRecordsService contractRecordsService;

	@Resource(name = "userService")
	private UserService userService;
	
	/**
	 * 中介购买申请页跳转
	 * 
	 * @return
	 */
	@RequestMapping("/to_getContractSize")
	public void tobuyApply(Integer houseNum) {

		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		int count = contractRecordsService.findContractRecordsNum(user);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contractNum", count);
		this.outObjectString(map, null);
	}

	/**
	 * 置业顾问app我的客户所有的
	 * 
	 * @return
	 */

	/**
	 * 置业顾问app我的认购客户所有的(搜索)
	 * 
	 * @return
	 */
	@RequestMapping("/to_saleAllReadyCustomer")
	public void getSaleAllReadyCustomer(String customerNameOrPhone) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		List list = contractRecordsService.findSaleAllBuy(user, customerNameOrPhone);
		this.outListString(list);
	}

	/**
	 * 置业顾问app我的客户搜索
	 * 
	 * @return
	 */
	@RequestMapping("/to_selectMyCustomer")
	public void getSelectMyCustomer(String customerNameOrPhone) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		List list = contractRecordsService.findMyCustomer(user, customerNameOrPhone);

		this.outListString(list);
	}

	/**
	 * 中介app我的客户选中按钮跳转页
	 * 
	 * @return
	 */
	@RequestMapping("/to_chooseMidCustomer")
	public ModelAndView getOneCustomer(Integer houseNum, String shopCustomerId) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		Map<String, Object> map = contractRecordsService.findOneCustomer(user, houseNum, shopCustomerId);
		ModelAndView model = new ModelAndView("app/house/apply");
		model.addObject("data", map);
		return model;
	}

	/**
	 * 置业顾问app我的客户选中按钮跳转页
	 * 
	 * @return
	 */
	@RequestMapping("/to_chooseSaleCustomer")
	public ModelAndView getSaleOneCustomer(Integer houseNum, String projectCustomerId) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		Map<String, Object> map = contractRecordsService.findSaleOneCustomer(user, houseNum, projectCustomerId);
		ModelAndView model = new ModelAndView("app/house/saleApplyToBuy");
		model.addObject("data", map);
		return model;
	}

	/**
	 * 中介app提交购买申请按钮
	 * 
	 * @return
	 */
	@RequestMapping("/to_submitMidApply")
	public ModelAndView getSubmitApply(Integer houseNum, String customerName, String customerPhone, Double buyPrice) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		Map<String, Object> map = contractRecordsService.findContractApply(user, houseNum, customerName, customerPhone,
				buyPrice);
		ModelAndView model = new ModelAndView("app/house/protocol");
		model.addObject("data", map);
		return model;
	}

	/**
	 * 置业顾问/中介app购买申请同意或拒绝
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/to_agreedOrRefuse")
	public String getAgreedOrRefuse(Integer houseNum, String customerName, String customerPhone, Double buyPrice,
			String projectCustomerId) throws IOException {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		contractRecordsService.addOrUpdateContractRecords(user, houseNum, customerName, customerPhone, buyPrice,
				projectCustomerId);
		Set<Role> set = user.getRoleId();
		for (Role role : set) {
			if (role.getRoleId() == 1 || role.getRoleId() == 2) {
				return "app/house/midMyService";
			}
			if (role.getRoleId() == 3) {
				return "app/house/agentMyService";
			}
		}
		return null;
	}

	/**
	 * 中介认购客户数量显示
	 * 
	 * @return
	 */
	@RequestMapping("/to_getMidCosntractSize")
	public void getCosntractSize() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		int count = contractRecordsService.findContractRecordsNum(user);
		Map<String, Object> map = new HashMap<>();
		map.put("contractNum", count);
		this.outObjectString(map, null);
	}

	/**
	 * 置业顾问认购客户数量显示
	 * 
	 * @return
	 */
	@RequestMapping("/to_getSaleCosntractSize")
	public void getSaleCosntractSize() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		int count = contractRecordsService.findContractRecordsNum(user);
		Map<String, Object> map = new HashMap<>();
		map.put("contractNum", count);
		this.outObjectString(map, null);
	}

	/**
	 * 中介认购客户页面
	 * 
	 * @return
	 */
	@RequestMapping("/to_midReadyBuy")
	public ModelAndView dealCustomer() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		List list = contractRecordsService.findMidBuy(user);
		ModelAndView model = new ModelAndView("app/house/placingOrders");
		model.addObject("data", list);
		return model;
	}

	/**
	 * 中介app签约客户
	 * 
	 * @return
	 */
	@RequestMapping("/to_midHaveDeal")
	public ModelAndView getDeal(String projectId, String startTime, String endTime) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		List list = contractRecordsService.findMidDeal(u, projectId, startTime, endTime);
		ModelAndView model = new ModelAndView("app/house/midBuyCustomer");
		model.addObject("data", list);
		return model;
	}

	/**
	 * 中介取消认购
	 * 
	 * @return
	 */
	@RequestMapping("/to_cancelCR")
	public String getCancelCR(Integer recordNo) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");

		contractRecordsService.updateCancelCR(u, recordNo);
		return "redirect:/to_midReadyBuy";
	}

	/**
	 * 中介认购客户上传凭证跳转页
	 * 
	 * @return
	 */
	@RequestMapping("/to_jumpUpload")
	public ModelAndView toJumpUpload(Integer recordNo) {

		ModelAndView model = new ModelAndView("app/house/confirm");
		model.addObject("data", recordNo);

		return model;
	}

	/**
	 * 中介确认下定客户
	 * 
	 * @return
	 */
	@RequestMapping("/to_midTureBuy")
	public void getTureBuy(Integer recordNo) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");

		Map<String, Object> map = contractRecordsService.findTrueBuy(u, recordNo);
		this.outObjectString(map, null);
	}

	/**
	 * 置业顾问app成交客户
	 * 
	 * @return
	 */
	@RequestMapping("/to_saleDeal")
	public ModelAndView getSaleDeal() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		List list = contractRecordsService.findSaleDeal(u);
		ModelAndView model = new ModelAndView("app/house/saleBuyCustomer");
		model.addObject("data", list);
		return model;
	}

	/**
	 * 置业顾问app业务下认购客户(下定客户)(订购有效期内的)
	 * 
	 * @return
	 */
	@RequestMapping("/to_saleHaveMakeUp")
	public ModelAndView getSaleBuy() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		List list = contractRecordsService.findSaleBuy(user);
		ModelAndView model = new ModelAndView("app/house/agentPlacingOrders");
		model.addObject("data", list);
		return model;
	}

	/**
	 * 置业顾问认购客户上传凭证跳转页
	 * 
	 * @return
	 */
	@RequestMapping("/to_saleToJumpUpload")
	public ModelAndView saleToJumpUpload(Integer recordNo) {

		ModelAndView model = new ModelAndView("app/house/saleTrueToBuy");
		model.addObject("data", recordNo);

		return model;
	}

	/**
	 * 置业顾问app确认下定
	 * 
	 * @return
	 */
	@RequestMapping("/to_saleTureBuy")
	public void getSaleTureBuy(Integer recordNo) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		Map<String, Object> map = contractRecordsService.findSaleReal(user, recordNo);
		this.outObjectString(map, null);
	}

	/**
	 * 中介app确认下定页面 确认大打款按钮 更新订购记录信息
	 * 
	 * @throws IOException
	 * 
	 */
	@ResponseBody
	@RequestMapping("/to_confirmMoney")
	public Object getConfirmMoney(String desc, Integer recordNo, MultipartFile pic) throws Exception {
		int credentialsType = 0;
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		contractRecordsService.addOrUpdateContract(user, desc, credentialsType, recordNo, pic);
		Map map = new HashMap<>();
		map.put("code", 200);
		map.put("msg", "上传成功...");
		return map;
	}

	/**
	 * 置业顾问app确认下定页面 确认大打款按钮 更新订购记录信息
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping("/to_confirmMoneyAgent")
	public String getConfirmMoneyAgent(String payWay, String customerIDCard, Integer recordNo, MultipartFile pic)
			throws Exception {
		int credentialsType = 0;
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		if ("wangyin".equals(payWay)) {
			credentialsType = 0;
		}
		if ("face".equals(payWay)) {
			credentialsType = 1;
		}
		if ("airplay".equals(payWay)) {
			credentialsType = 2;
		}

		contractRecordsService.addOrUpdateContract(user, customerIDCard, credentialsType, recordNo, pic);
		Set<Role> hs = user.getRoleId();
		for (Role role : hs) {
			if (role.getRoleId() == 1 || role.getRoleId() == 2) {
				return "redirect:/to_midReadyBuy";
			}
			if (role.getRoleId() == 3) {
				return "redirect:/to_saleHaveMakeUp";
			}
		}
		return null;
	}

	/**
	 * 置业顾问app客户资料跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_saleCustomerInfo")
	public ModelAndView toGetSaleCustomerInfo(String projectCustomerId, String projectCustomerPhoneTwo) {
		ModelAndView model = new ModelAndView("app/house/agentCustomerMessage");
		model.addObject("dataInfo", projectCustomerId);
		model.addObject("dataInfoTwo", projectCustomerPhoneTwo);

		return model;
	}

	/**
	 * 置业顾问app客户资料
	 * 
	 * @return
	 */
	@RequestMapping("/to_getCustomerInfo")
	public void getCustomerInfo(String projectCustomerId, String projectCustomerPhoneTwo) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		Map<String, Object> map = contractRecordsService.selectOneCustomer(user, projectCustomerId,
				projectCustomerPhoneTwo);

		this.outObjectString(map, null);
	}

	/**
	 * 客户资料修改
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/to_updateCustomerInfo")
	public void updateCustomerInfo(HttpServletRequest request, HttpServletResponse response, String projectCustomerId,
			String description, String projectCustomerPhone) throws IOException {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		contractRecordsService.updateProjectCustomer(user, projectCustomerId, description, projectCustomerPhone);
	}

	/*
	 * 认购改版分割线------------------------------------------------------------------
	 * ------------------------
	 */

	/**
	 * 中介购买申请跳转页 (改版后)
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToContractRecord")
	public String toGoToContractRecord(Integer houseNum) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		NewContractRecordsDTO crdto = contractRecordsService.findOneHouseToContractRecord(user, houseNum);

		this.request.getSession().setAttribute("newCrdto", crdto);

		Set<Role> set = user.getRoleId();
		for (Role role : set) {
			if (role.getRoleId() == 1 || role.getRoleId() == 2) {
				return "app/house/subscriptionApplication";
			}
			if (role.getRoleId() == 3) {
				return "app/house/agentSubscriptionApplication";
			}
		}
		return null;
	}


	/**
	 * 中介购买申请房源列表显示 (改版后)
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToSelectOneProjectHouseList")
	public String toGoToSelectOneProjectHouse(Model model, Integer houseNum, String allBenefitsId) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		List<HouseListDTO> hdtoList = contractRecordsService.findNowProjectHouses(user, houseNum);

		model.addAttribute("hdtoList", hdtoList);
		model.addAttribute("allBenefitsId", allBenefitsId);
		Set<Role> set = user.getRoleId();
		for (Role role : set) {
			if (role.getRoleId() == 1 || role.getRoleId() == 2) {
				return "app/house/houseList";
			}
			if (role.getRoleId() == 3) {
				return "app/house/agentHouseList";
			}
		}
		return null;
	}

	/**
	 * 中介购买申请房源重新选中 (改版后)
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToChooseOneProjectHouse")
	public String toGoToChooseOneProjectHouse(Integer houseNum, String allBenefitsId) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 获取NewContractRecordsDTO对象信息
		NewContractRecordsDTO crdto = (NewContractRecordsDTO) this.request.getSession().getAttribute("newCrdto");
		crdto = contractRecordsService.findNewProjectHouse(user, crdto, houseNum, allBenefitsId);

		this.request.getSession().setAttribute("newCrdto", crdto);
		Set<Role> set = user.getRoleId();
		for (Role role : set) {
			if (role.getRoleId() == 1 || role.getRoleId() == 2) {
				return "app/house/subscriptionApplication";
			}
			if (role.getRoleId() == 3) {
				return "app/house/agentSubscriptionApplication";
			}
		}
		return null;
	}

	/**
	 * 中介购买申请房源价格优惠信息列表展示 (改版后)
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToProjectBenefitsInfoList")
	public String toGoToProjectBenefitsInfoList(Model model, Integer houseNum) throws ParseException {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		List<ProjectBenefits> pbList = contractRecordsService.findNowProjectBenefits(user, houseNum);

		model.addAttribute("benefitsList", pbList);
		model.addAttribute("houseNum", houseNum);
		return "app/house/termList";
	}

	/**
	 * 中介购买申请房源价格优惠信息显示选中 (改版后)
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToGetProjectBenefitsInfo")
	public String toGoToGetProjectBenefitsInfo(String allBenefitsId, Integer houseNum) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 获取NewContractRecordsDTO对象信息
		NewContractRecordsDTO crdto = (NewContractRecordsDTO) this.request.getSession().getAttribute("newCrdto");

		crdto = contractRecordsService.findNewProjectBenefits(user, crdto, allBenefitsId, houseNum);

		this.request.getSession().setAttribute("newCrdto", crdto);
		Set<Role> set = user.getRoleId();
		for (Role role : set) {
			if (role.getRoleId() == 1 || role.getRoleId() == 2) {
				return "app/house/subscriptionApplication";
			}
			if (role.getRoleId() == 3) {
				return "app/house/agentSubscriptionApplication";
			}
		}
		return null;

	}

	/**
	 * 中介购买申请认购客户列表(改版后)
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToMidMyCustomer")
	public String toGetAllCustomers(Model model, Integer houseNum) throws ParseException {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		List<ShopCustomerDTO> list = contractRecordsService.findMidCustomer(user, houseNum);

		model.addAttribute("scList", list);
		return "app/house/myClient";
	}

	/**
	 * 置业顾问购买申请认购客户列表(改版后)
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToAgentMyCustomer")
	public String toGetAgentCustomer(Model model, Integer houseNum) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		List<ProjectCustomers> list = contractRecordsService.findSaleCustomer(u);

		model.addAttribute("pcList", list);
		return "app/house/agentMyClient";
	}

	/**
	 * 中介app我的客户选中按钮跳转页(改版后)
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToChooseMidCustomer")
	public String toGoToGetOneCustomer(Integer houseNum, String shopCustomerId) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 获取NewContractRecordsDTO对象信息
		NewContractRecordsDTO crdto = (NewContractRecordsDTO) this.request.getSession().getAttribute("newCrdto");

		crdto = contractRecordsService.findMidCustomerNew(user, crdto, houseNum, shopCustomerId);
		this.request.getSession().setAttribute("newCrdto", crdto);
		return "app/house/subscriptionApplication";
	}

	/**
	 * 置业顾问app我的客户选中按钮跳转页(改版后)
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToChooseSaleCustomer")
	public String toGetSaleOneCustomer(Integer houseNum, String projectCustomerId) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 获取NewContractRecordsDTO对象信息
		NewContractRecordsDTO crdto = (NewContractRecordsDTO) this.request.getSession().getAttribute("newCrdto");

		crdto = contractRecordsService.findAgentCustomerNew(user, crdto, houseNum, projectCustomerId);
		this.request.getSession().setAttribute("newCrdto", crdto);
		return "app/house/agentSubscriptionApplication";

	}

	/**
	 * 鼠标移开填写信息添加到session(改版后)
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToAddSession")
	public void toGoToAddSession(Integer orderProperty, String buyPrice, Integer accountStyle, Integer payStyle,
			Integer isAlreadyRead, String rengourenIdCard) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 获取NewContractRecordsDTO对象信息
		NewContractRecordsDTO crdto = (NewContractRecordsDTO) this.request.getSession().getAttribute("newCrdto");
		crdto = contractRecordsService.toAddSession(user, crdto, orderProperty, buyPrice, accountStyle, payStyle,
				isAlreadyRead, rengourenIdCard);

		this.request.getSession().setAttribute("newCrdto", crdto);
	}

	/**
	 * 真实认购人存入session(改版后)
	 * 
	 */
	@RequestMapping("/to_goToAddRealEnterBuySession")
	public void toGoToAddRealEnterBuySession(String rName, String rPhone, String rIdCard, String relationDesc) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		List<RealEnterBuyDTO> rebList = contractRecordsService.findRebList(rName, rPhone, rIdCard, relationDesc);

		this.request.getSession().setAttribute("newRebList", rebList);

		this.outListString(rebList);
	}

	/**
	 * 所有订单页面(包含不同订单状态)跳转控制器
	 * 
	 */
	@RequestMapping("/to_goToAllMyContractRecordsListPage")
	public String toGotoAllMyContractRecordsListPage() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		Set<Role> set = user.getRoleId();
		for (Role role : set) {
			if (role.getRoleId() == 1 || role.getRoleId() == 2) {
				return "app/house/myBooking";
			}
			if (role.getRoleId() == 3) {
				return "app/house/agentMyBooking";
			}
		}
		return null;
	}

	/**
	 * 所有订单页面(包含不同订单状态)
	 * 
	 */
	@RequestMapping("/to_goToAllMyContractRecordsList")
	public void toGoToAllMyContractRecords(Model model) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		List<AllMyContractRecordDTO> amcrdtoList = contractRecordsService.findAllContractRecords(user);

		this.outListString(amcrdtoList);
	}

	/**
	 * 待审核订单页面(
	 * 
	 */
	@RequestMapping("/to_goToContractRecordsByReadyCheck")
	public void toGoToContractRecordsByReadyCheck(Model model) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		List<AllMyContractRecordDTO> amcrdtoList = contractRecordsService.findContractRecordsByReadyCheck(user);

		this.outListString(amcrdtoList);
	}

	/**
	 * 待付款订单页面(
	 * 
	 */
	@RequestMapping("/to_goToContractRecordsByReadyPay")
	public void toGoToContractRecordsByReadyPay(Model model) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		List<AllMyContractRecordDTO> amcrdtoList = contractRecordsService.findContractRecordsByReadyPay(user);

		this.outListString(amcrdtoList);
	}

	/**
	 * 待确定付款订单页面(
	 * 
	 */
	@RequestMapping("/to_goToContractRecordsByEnterPay")
	public void toGoToContractRecordsByEnterPay(Model model) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		List<AllMyContractRecordDTO> amcrdtoList = contractRecordsService.findContractRecordsByEnterPay(user);

		this.outListString(amcrdtoList);
	}

	/**
	 * 待签约订单页面(
	 * 
	 */
	@RequestMapping("/to_goToContractRecordsByReadyContract")
	public void toGoToContractRecordsByReadyContract(Model model) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		List<AllMyContractRecordDTO> amcrdtoList = contractRecordsService.findContractRecordsByReadyContract(user);

		this.outListString(amcrdtoList);
	}

	/**
	 * 已签约订单页面(
	 * 
	 */
	@RequestMapping("/to_goToContractRecordsByHaveContract")
	public void toGoToContractRecordsByHaveContract(Model model) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		List<AllMyContractRecordDTO> amcrdtoList = contractRecordsService.findContractRecordsByHaveContract(user);

		this.outListString(amcrdtoList);
	}

	/**
	 * 已拒绝订单页面(
	 * 
	 */
	@RequestMapping("/to_goToContractRecordsByRefuse")
	public void toGoToContractRecordsByRefuse(Model model) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		List<AllMyContractRecordDTO> amcrdtoList = contractRecordsService.findContractRecordsByRefuse(user);

		this.outListString(amcrdtoList);
	}

	/**
	 * 已撤销订单页面(
	 * 
	 */
	@RequestMapping("/to_goToContractRecordsByRevoke")
	public void toGoToContractRecordsByRevoke(Model model) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		List<AllMyContractRecordDTO> amcrdtoList = contractRecordsService.findContractRecordsByRevoke(user);

		this.outListString(amcrdtoList);
	}

	/**
	 * 跳转到订单状态和详情页
	 * 
	 * @return
	 */
	@RequestMapping("/to_order_style_details_page")
	public String toOrderStyleAndDetailsPage(Model m, Integer recordNo) {
		m.addAttribute("recordNo", recordNo);
		return "app/house/orderStatus";
	}

	/**
	 * 查阅订单状态
	 * 
	 */
	@RequestMapping("/to_goToReadOneContractRecord")
	public String toGoToReadOneContractRecord(Model model, Integer recordNo, Integer picUpCode) throws Exception {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		NewContractRecordStatusDTO crdto = contractRecordsService.findOneContractRecord(recordNo);
		String str = JSONObject.fromObject(crdto).toString();
		System.out.println(str);
		model.addAttribute("crdtoStatus", crdto);
		Set<Role> set = user.getRoleId();
		for (Role role : set) {
			if (role.getRoleId() == 1 || role.getRoleId() == 2) {
				return "app/house/orderStatus";
			}
			if (role.getRoleId() == 3) {
				return "app/house/agentOrderStatus";
			}
		}
		return null;
	}

	/**
	 * 查看订单详情（grl add 2017-04-23）
	 * 
	 * @param recordNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/orderDetails")
	public Object orderDetails(Integer recordNo) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		Map m = new HashMap<>();
		OrderDetailsDTO odDto = contractRecordsService.findOrderDetailsByRecordNo(user, recordNo);
		System.out.println(odDto);
		System.out.println(JSONObject.fromObject(odDto));
		return odDto;
	}

	/**
	 * 跳到撤销订单页面控制器(
	 * 
	 */
	@RequestMapping("/to_goToRevokeOneContractRecord")
	public String toGoToRevokeOneContractRecord(Integer recordNo, Model model) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		model.addAttribute("oneRecordNo", recordNo);
		Set<Role> set = user.getRoleId();
		for (Role role : set) {
			if (role.getRoleId() == 1 || role.getRoleId() == 2) {
				return "app/house/cancelOrder";
			}
			if (role.getRoleId() == 3) {
				return "app/house/agentCancelOrder";
			}
		}
		return null;
	}

	/**
	 * 撤销订单操作(
	 * 
	 */
	@RequestMapping("/to_goToRealRevokeOneContractRecord")
	public void toRevokeContractRecord(Integer recordNo, String killTheOrderReason, String revokeOrderNotes) {

		contractRecordsService.updateContractRecordToRevoke(recordNo, killTheOrderReason, revokeOrderNotes);

	}

	/**
	 * 查阅合同条款
	 * 
	 */
	@RequestMapping("/to_goToReadContractTerms")
	public String toGoToReadContractTerms(Integer houseNum, Model model) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		EnterBuy eb = contractRecordsService.findProjectEnterBuy(houseNum);

		model.addAttribute("enterBuy", eb);
		return "app/house/hetong";
	}

	/**
	 * 合同条款看完 返回订购页面
	 * 
	 */
	@RequestMapping("/to_goToRenGouPage")
	public String togoToRenGouPage(Model model) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		model.addAttribute("dataInfo", "1");
		Set<Role> set = user.getRoleId();
		for (Role role : set) {
			if (role.getRoleId() == 1 || role.getRoleId() == 2) {
				return "app/house/subscriptionApplication";
			}
			if (role.getRoleId() == 3) {
				return "app/house/agentSubscriptionApplication";
			}
		}
		return null;
	}

	/**
	 * 跳转到真实认购人添加页面
	 * 
	 */
	@RequestMapping("/to_goToRealEnterBuyCustomer")
	public String toGoToRealEnterBuyCustomer(String customerId, Model model) {

		model.addAttribute("customerId", customerId);

		return "app/house/trueCustomerList";
	}

	/**
	 * 真实认购人list
	 * 
	 */
	@RequestMapping("/to_getRealEnterBuyCustomerList")
	public void toGetRealEnterBuyCustomerList(String customerId) {

		List<RealEnterBuyMan> rebmList = contractRecordsService.findAllRebmList(customerId);

		this.outListString(rebmList);
	}

	/**
	 * 真实认购人添加
	 * 
	 */
	@ResponseBody
	@RequestMapping("/to_goToAddRealEnterBuyCustomer")
	public Map<String, String> toAddRealEnterBuyCustomer(String customerId, String rName, String rPhone, String rIdCard,
			String relation) {

		Map<String, String> map = contractRecordsService.addRealBuyCustomer(customerId, rName, rPhone, rIdCard,
				relation);

		return map;
	}

	/**
	 * 真实认购人删除
	 * 
	 */
	@RequestMapping("/to_goToDeleteRealEnterBuyCustomer")
	public void toDeleteRealEnterBuyCustomer(String allRealEnterBuyId) {

		contractRecordsService.updateRealBuyCustomer(allRealEnterBuyId);

	}

	/**
	 * 选中真实认购人....
	 * 
	 */
	@RequestMapping("/to_goToChooseRealCustomer")
	public String tooGoToChooseRealCustomer(String allRealEnterBuyId) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 获取NewContractRecordsDTO对象信息
		NewContractRecordsDTO crdto = (NewContractRecordsDTO) this.request.getSession().getAttribute("newCrdto");
		crdto = contractRecordsService.findChooseRebmList(crdto, allRealEnterBuyId);
		Set<Role> set = user.getRoleId();
		for (Role role : set) {
			if (role.getRoleId() == 1 || role.getRoleId() == 2) {
				return "app/house/subscriptionApplication";
			}
			if (role.getRoleId() == 3) {
				return "app/house/agentSubscriptionApplication";
			}
		}
		return null;
	}

	/**
	 * 提交认购订单
	 * 
	 */
	@RequestMapping("/to_goToSubmitContractRecord")
	public void toGoToSubmitContracrRecord(Integer orderProperty, Integer isAlreadyRead, Integer payStyle,
			Integer accountStyle, String benefitInfo, Integer houseNum, String buyPrice, String customerId,
			String rengourenIdCard, String realCustomerId, String dposit) throws IOException {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		contractRecordsService.addNewContractRecord(user, orderProperty, isAlreadyRead, payStyle, accountStyle,
				benefitInfo, houseNum, buyPrice, customerId, rengourenIdCard, realCustomerId, dposit);

	}

	/**
	 * 催单
	 * @param orderNo
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/call_order")
	public Object callOrder(Integer orderNo) throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map map = new HashMap<>();
		Boolean flag = contractRecordsService.addOrUpdateCallOrderSms(user,orderNo);
		if(flag){
			map.put("msg", "催单成功");
		}else{
			map.put("msg", "请稍候重试...");
		}
		return map;
	}

}
