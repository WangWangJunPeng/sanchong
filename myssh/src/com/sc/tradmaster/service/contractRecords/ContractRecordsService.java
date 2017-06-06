package com.sc.tradmaster.service.contractRecords;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.ProjectBenefits;
import com.sc.tradmaster.bean.RealEnterBuyMan;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.contractRecords.impl.dto.AllMyContractRecordDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.NewContractRecordStatusDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.NewContractRecordsDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.OrderDetailsDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.RealEnterBuyDTO;
import com.sc.tradmaster.service.house.impl.dto.HouseListDTO;

public interface ContractRecordsService {
	
	
	/**
	 * 中介提交申请页面
	 */	
	Map<String, Object> findContractApply(User user, Integer houseNum, String customerName, String customerPhone, Double buyPrice);
	
	/**
	 * 中介提交申请同意和拒绝
	 */	
	void addOrUpdateContractRecords(User user, Integer houseNum, String customerName, String customerPhone, Double buyPrice,String projectCustomerId)throws IOException;
	
	/**
	 * 中介app我的客户
	 */	
	List findMidCustomer(User user,Integer houseNum)throws ParseException ;
	
	/**
	 * 中介签约客户
	 */	
	List findMidDeal(User user, String projectId, String startTime, String endTime);
	
	/**
	 * 中介认购客户
	 */
	List findMidBuy(User user);
	
	/**
	 * 中介取消认购
	 */
	void updateCancelCR(User user, Integer houseNum);
	
	/**
	 * 中介确认下定
	 */
	Map<String, Object> findTrueBuy(User user, Integer recordNo);
	
	/**
	 * 置业顾问成交客户
	 */
	List findSaleDeal(User user);
	
	/**
	 * 置业顾问我的客户
	 */
	List findSaleCustomer(User user);
	
	/**
	 * 置业顾问app我的页面下所有的认购客户
	 */
	List findSaleAllBuy(User user,String customerNameOrPhone);
	
	/**
	 * 置业顾问app我的客户搜索
	 * 
	 */
	List findMyCustomer(User user,String customerNameOrPhone);
	
	/**
	 * 置业顾问app业务下认购客户(下定客户)(订购有效期内的)
	 * 
	 */
	List findSaleBuy(User user);
	
	
	
	/**
	 * 置业顾问app确认下定
	 * 
	 */
	Map<String, Object> findSaleReal(User user, Integer recordNo);
	
	/**
	 * 置业顾问app和中介端  确认打款按钮  更新订购记录信息
	 * @throws IOException 
	 * 
	 */
	void addOrUpdateContract(User user,String customerIDCard, Integer credentialsType, Integer recordNo,MultipartFile pic) throws Exception;

	/**
	 * 中介我的客户选中
	 * 
	 */
	Map<String, Object> findOneCustomer( User user ,Integer houseNum, String shopCustomerId);
	
	/**
	 * 置业顾问我的客户选中
	 * 
	 */
	Map<String, Object> findSaleOneCustomer( User user ,Integer houseNum, String projectCustomerId);
	/**
	 * 客户资料显示页面
	 */
	Map selectOneCustomer(User user, String projectCustomerId,String projectCustomerPhoneTwo);
	
	/**
	 * 客户资料修改,保存
	 */
	void updateProjectCustomer(User user, String projectCustomerId, String description,String projectCustomerPhone);
	
	/**
	 * 中介认购客户的数量
	 */
	int findContractRecordsNum(User user);
	
	/**
	 * 认购页面房源列表展示(改版后)
	 */
	List<HouseListDTO> findNowProjectHouses(User user,Integer houseNum);
	
	/**
	 * 认购页面房源优惠信息列表展示(改版后)
	 */
	List<ProjectBenefits> findNowProjectBenefits(User user, Integer houseNum)throws ParseException;
	
	/**
	 * 认购页面房源信息显示(改版后)
	 */
	NewContractRecordsDTO findOneHouseToContractRecord(User user,Integer houseNum);
	
	/**
	 * 认购页面价格优惠信息显示(改版后)
	 */
	Map<String, Object> findOneProjectBenefitsToContractRecord(User user,String allBenefitsId,Integer houseNum);
	
	/**
	 * 提交认购信息(改版后)
	 */
	void addNewContractRecord(User user, Integer orderProperty,Integer isAlreadyRead,Integer payStyle,
			Integer accountStyle,String benefitInfo,Integer houseNum,String buyPrice,String customerId,String rengourenIdCard,String realCustomerId,String dposit)throws IOException;

	
	/**
	 * 中介购买申请房源重新选中  (改版后)
	 * 
	 * @return
	 */
	NewContractRecordsDTO findNewProjectHouse(User user,NewContractRecordsDTO crdto,Integer houseNum,String allBenefitsId);
	
	/**
	 * 认购页面价格优惠信息重新选中(改版后)
	 */
	NewContractRecordsDTO findNewProjectBenefits(User user,NewContractRecordsDTO crdto,String allBenefitsId,Integer houseNum);
	
	/**
	 * 中介我的客户选中 (改版后)
	 * 
	 */
	NewContractRecordsDTO findMidCustomerNew(User user ,NewContractRecordsDTO crdto,Integer houseNum, String shopCustomerId);
	
	/**
	 * 置业顾问app我的客户选中按钮跳转页(改版后)
	 * 
	 */
	NewContractRecordsDTO findAgentCustomerNew(User user ,NewContractRecordsDTO crdto,Integer houseNum, String projectCustomerId);
	
	/**
	 * 鼠标移开填写信息添加到session(改版后)
	 * 
	 */
	NewContractRecordsDTO toAddSession(User user ,NewContractRecordsDTO crdto,Integer orderProperty,String buyPrice,Integer accountStyle,Integer payStyle,Integer isAlreadyRead,String rengourenIdCard);
	
	/**
	 * 真实认购人存入session(改版后)
	 * 
	 */
	List<RealEnterBuyDTO> findRebList(String rName,String rPhone,String rIdCard,String relationDesc);
	
	/**
	 * 所有订单页面(包含不同订单状态)
	 * 
	 */
	List<AllMyContractRecordDTO> findAllContractRecords(User user);
	
	/**
	 * 待审核订单页面(
	 * 
	 */
	List<AllMyContractRecordDTO> findContractRecordsByReadyCheck(User user);
	
	/**
	 * 待付款订单页面(
	 * 
	 */
	List<AllMyContractRecordDTO> findContractRecordsByReadyPay(User user);
	
	/**
	 * 待签约订单页面(
	 * 
	 */
	List<AllMyContractRecordDTO> findContractRecordsByReadyContract(User user);
	
	/**
	 * 已签约订单页面(
	 * 
	 */
	List<AllMyContractRecordDTO> findContractRecordsByHaveContract(User user);
	
	/**
	 * 已拒绝订单页面(
	 * 
	 */
	List<AllMyContractRecordDTO> findContractRecordsByRefuse(User user);
	
	/**
	 * 已撤销订单页面(
	 * 
	 */
	List<AllMyContractRecordDTO> findContractRecordsByRevoke(User user);
	
	/**
	 * 单条订单的所有状态显示
	 * 
	 */
	NewContractRecordStatusDTO findOneContractRecord(Integer recordNo)throws Exception;
	
	/**
	 * 撤销订单操作
	 * 
	 */
	void updateContractRecordToRevoke(Integer recordNo,String killTheOrderReason,String revokeOrderNotes);
	
	/**
	 * 查阅合同条款
	 * 
	 */
	EnterBuy findProjectEnterBuy(Integer houseNum);
	
	/**
	 * 真实认购人添加
	 * 
	 */
	Map<String,String> addRealBuyCustomer(String customerId,String rName,String rPhone,String rIdCard,String relation);

	/**
	 * 真实认购人删除
	 * 
	 */
	void updateRealBuyCustomer(String allRealEnterBuyId);

	/**
	 * 真实认购人列表
	 * 
	 */
	List<RealEnterBuyMan> findAllRebmList(String customerId);
	
	/**
	 * 真实认购人选中////////////////
	 * 
	 */
	NewContractRecordsDTO findChooseRebmList(NewContractRecordsDTO crdto,String allRealEnterBuyId);
	
	/**
	 * 我的客户新增...............
	 * 
	 */
	void addMyCustomerNew(User user,String rName,String rPhone,String rIdCard);

	/**
	 * 订单详情
	 * @param user
	 * @param recordNo
	 * @return 
	 */
	OrderDetailsDTO findOrderDetailsByRecordNo(User user, Integer recordNo);
	/**
	 * 付款待确认
	 * @param user
	 * @return
	 */
	List<AllMyContractRecordDTO> findContractRecordsByEnterPay(User user);

	/**
	 * 催单
	 * @param user
	 * @param orderNo
	 * @return
	 * @throws ParseException 
	 */
	Boolean addOrUpdateCallOrderSms(User user, Integer orderNo) throws Exception;
}
