package com.sc.tradmaster.service.project.impl.dto;

import java.util.ArrayList;
import java.util.List;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.RealEnterBuyMan;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.ServiceHelper;

public class SeeBuyApplyDTO {
	//房源编号
	private Integer houseNum;
	// 房号
	private String houseNo;
	// 建筑面积
	private Double buildArea;
	// 使用面积
	private Double usefulArea;
	// 列表价
	private Double listPrice;
	// 底价
	private Double minimumPrice;
	// 中介供货价
	private Double shopPrice;
	// 房型名称
	private String homeTypeName;

	//订购号
	private Integer recordNo;
	// 来源
	private String source;
	// 申请人
	private String applyMan;
	// 购买价
	private Double buyPrice;
	// 申请时间
	private String applyTime;
	//申请数量
	private Integer applyThisHouseCounts;
	//审批人
	private String checkMan;
	//签约审核人
	private String signCheckMan;
	//剩余打款时间
	private String surplusPalyMoneyTime;
	//拒绝原因
	private String refuseReason;
	//打款超期时间
	private String palyMoneyOutTime;
	//定金到款审核时间
	private String remitConfirmTime;
	//签约确认时间
	private String signEnterTime;
	//汇款凭着
	private String credentialsPhoto;
	//已被认购
	private String hadConRe;
	
	/*2017-04-21 cdh*/
	//订单性质(0:自购,1:代购)
	private Integer orderProperty;
	
	
	//真实购买人名称
	private List<String> rName;
	
	//定金
	private Double dposit;
		
	//支付方式
	private Integer payStyle;
	
	//结算方式
	private Integer accountStyle;
	
	//选择的优惠
	private String benefitInfo;
	
	//认购人身份证
	private String customerIDCard;
	
	//认购人联系方式
	private String customerPhone;
	
	//客户姓名
	private String customerName;
	
	//撤单原因(单选框选择..........)
	private String killTheOrderReason;
	//撤单时间
	private String revokeTime;
	
	
	
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getHadConRe() {
		return hadConRe;
	}

	public void setHadConRe(String hadConRe) {
		this.hadConRe = hadConRe;
	}

	public String getSignCheckMan() {
		return signCheckMan;
	}

	public void setSignCheckMan(String signCheckMan) {
		this.signCheckMan = signCheckMan;
	}

	public String getSignEnterTime() {
		return signEnterTime;
	}

	public void setSignEnterTime(String signEnterTime) {
		this.signEnterTime = signEnterTime;
	}

	public String getRemitConfirmTime() {
		return remitConfirmTime;
	}

	public void setRemitConfirmTime(String remitConfirmTime) {
		this.remitConfirmTime = remitConfirmTime;
	}

	public String getCredentialsPhoto() {
		return credentialsPhoto;
	}

	public void setCredentialsPhoto(String credentialsPhoto) {
		this.credentialsPhoto = credentialsPhoto;
	}

	public Integer getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public Double getBuildArea() {
		return buildArea;
	}

	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}

	public Double getUsefulArea() {
		return usefulArea;
	}

	public void setUsefulArea(Double usefulArea) {
		this.usefulArea = usefulArea;
	}

	public Double getListPrice() {
		return listPrice;
	}

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}

	public Double getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(Double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	public Double getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public String getHomeTypeName() {
		return homeTypeName;
	}

	public void setHomeTypeName(String homeTypeName) {
		this.homeTypeName = homeTypeName;
	}
	
	public Integer getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getApplyMan() {
		return applyMan;
	}

	public void setApplyMan(String applyMan) {
		this.applyMan = applyMan;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	
	public Integer getApplyThisHouseCounts() {
		return applyThisHouseCounts;
	}

	public void setApplyThisHouseCounts(Integer applyThisHouseCounts) {
		this.applyThisHouseCounts = applyThisHouseCounts;
	}
	
	public String getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}
	
	public String getSurplusPalyMoneyTime() {
		return surplusPalyMoneyTime;
	}

	public void setSurplusPalyMoneyTime(String surplusPalyMoneyTime) {
		this.surplusPalyMoneyTime = surplusPalyMoneyTime;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getPalyMoneyOutTime() {
		return palyMoneyOutTime;
	}

	public void setPalyMoneyOutTime(String palyMoneyOutTime) {
		this.palyMoneyOutTime = palyMoneyOutTime;
	}
	
	

	public Integer getOrderProperty() {
		return orderProperty;
	}

	public void setOrderProperty(Integer orderProperty) {
		this.orderProperty = orderProperty;
	}


	public List<String> getrName() {
		return rName;
	}

	public void setrName(List<String> rName) {
		this.rName = rName;
	}

	public Double getDposit() {
		return dposit;
	}

	public void setDposit(Double dposit) {
		this.dposit = dposit;
	}

	public Integer getPayStyle() {
		return payStyle;
	}

	public void setPayStyle(Integer payStyle) {
		this.payStyle = payStyle;
	}

	public Integer getAccountStyle() {
		return accountStyle;
	}

	public void setAccountStyle(Integer accountStyle) {
		this.accountStyle = accountStyle;
	}

	public String getBenefitInfo() {
		return benefitInfo;
	}

	public void setBenefitInfo(String benefitlds) {
		this.benefitInfo = benefitlds;
	}

	public String getCustomerIDCard() {
		return customerIDCard;
	}

	public void setCustomerIDCard(String customerIDCard) {
		this.customerIDCard = customerIDCard;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getKillTheOrderReason() {
		return killTheOrderReason;
	}

	public void setKillTheOrderReason(String killTheOrderReason) {
		this.killTheOrderReason = killTheOrderReason;
	}

	public String getRevokeTime() {
		return revokeTime;
	}

	public void setRevokeTime(String revokeTime) {
		this.revokeTime = revokeTime;
	}

	public SeeBuyApplyDTO creatHouseDTO(House h,HouseType ht) {
		this.houseNo = h.getBuildingNo()+"栋"+h.getUnit()+"单元"+h.getHouseNo()+"号";
		this.buildArea = h.getBuildArea();
		this.usefulArea = h.getUsefulArea();
		this.listPrice = h.getListPrice();
		this.minimumPrice = h.getMinimumPrice();
		this.shopPrice = h.getShopPrice();
		if(ht!=null){
			this.homeTypeName = ht.getHousType();
		}
		return this;
	}

	public SeeBuyApplyDTO creatBuyApplyDTO(ContractRecords crs,User u) {
		UserService userSer = ServiceHelper.getUserService();
		if(crs.getUserId()!=null){
			User user = userSer.findById(crs.getUserId());
			if(user!=null){
				String name = user.getUserCaption();
				this.applyMan = name.replace(name.charAt(1), '*');
				
				if(user.getParentId().equals(u.getParentId())){
					this.source = "置业顾问";
				}else{
					this.source = "中介";
				}
			}else{
				this.applyMan = "未知";
				this.source = "未知";
			}
		}else{
			this.applyMan = "未知";
			this.source = "未知";
		}
		
		this.buyPrice = crs.getBuyPrice();
		this.applyTime = crs.getApplyTime();
		this.recordNo = crs.getRecordNo();
		return this;
	}
	
	public SeeBuyApplyDTO createTradeBusinessBuyApplyDTO(House h,HouseType ht,ContractRecords crs,User u){
		//房源编号
		this.houseNum = crs.getHouseNum();
		//房号
		this.houseNo = h.getBuildingNo()+"栋"+h.getUnit()+"单元"+h.getHouseNo()+"号";
		//建筑面积
		this.buildArea = h.getBuildArea();
		//使用面积
		this.usefulArea = h.getUsefulArea();
		//列表价
		this.listPrice = h.getListPrice();
		//低价
		this.minimumPrice = h.getMinimumPrice();
		//中介供货价
		this.shopPrice = h.getShopPrice();
		//房型
		if(ht!=null && ht.getHousType()!=null && !ht.getHousType().equals("")){
			this.homeTypeName = ht.getHousType();
		}else{
			this.homeTypeName = "未知";
		}
		
		//拒绝原因
		this.refuseReason = crs.getAuditionReson();
		//申请人
		UserService userSer = ServiceHelper.getUserService();
		User user = userSer.findById(crs.getUserId());
		if(user!=null){
			String name = user.getUserCaption();
			this.applyMan = name.replace(name.charAt(1), '*');
		}
		//审核人
		if(crs.getAuditionUserId()!=null && !crs.getAuditionUserId().equals("")){
			User checkUser = userSer.findById(crs.getAuditionUserId());
			if(checkUser!=null){
				this.checkMan = checkUser.getUserCaption();
			}
		}
		//签约审核人
		if(crs.getContractconfirmUseerId()!=null && !crs.getContractconfirmUseerId().equals("")){
			User signCheckUser = userSer.findById(crs.getContractconfirmUseerId());
			if(signCheckUser!=null){
				this.signCheckMan = signCheckUser.getUserCaption();
			}
		}
		
		//定金到款审核时间
		this.remitConfirmTime = crs.getRemitConfirmTime();
		//签约确认时间
		this.signEnterTime = crs.getContractConfirmTime();
		//汇款凭证
		this.credentialsPhoto = crs.getCredentialsPhoto();
		this.buyPrice = crs.getBuyPrice();
		//申请时间
		this.applyTime = crs.getApplyTime();
		//来源
		if(user!=null && user.getParentId()!=null && user.getParentId().equals(u.getParentId())){
			this.source = "置业顾问";
		}else{
			this.source = "中介";
		}
		this.recordNo = crs.getRecordNo();
		
		this.killTheOrderReason = crs.getKillTheOrderReason(); //撤单原因
		
		this.revokeTime = crs.getRevokeTime(); //撤单时间
		
		return this;
	}
	
	
	/**
	 * 
	 * 查找认购人的所有信息包括支付方式和结算方式等等....
	 * @param project	认购属于的项目
	 * @param crs	认购记录
	 * @param rbm	真实购买人集合
	 * @param benefits	优惠条款
	 * @param enterBuy 认购规则
	 * @return
	 */
	public SeeBuyApplyDTO createContractMenInfo(ContractRecords crs, List<RealEnterBuyMan> rbm, EnterBuy enterBuy){
		
		if(crs == null)
			return null;
		
		//订单性质
		this.orderProperty = crs.getOrderProperty();
		//如果真实购买人存在说明是代购的
		if(rbm.size() > 0){
			List<String> list = new ArrayList<>();
			for(RealEnterBuyMan r : rbm){
				if(r != null)
					list.add(r.getrName());
			}
			this.rName = list;
		}
		
		//定金
		this.dposit = enterBuy.getDposit();
		//支付方式
		this.payStyle = crs.getPayStyle();
		//结算方式
		this.accountStyle = crs.getAccountStyle();
		//选择的优惠
		this.benefitInfo = crs.getBenefitInfo();
		//认购人身份证
		this.customerIDCard = crs.getCustomerIDCard();
		//认购人联系方式
		this.customerPhone = crs.getCustomerPhone();
		//认购人姓名
		this.customerName = crs.getCustomerName();
		
		return this;
	}

}
