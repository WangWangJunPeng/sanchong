package com.sc.tradmaster.service.project.impl.dto;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.SystemChargeDefinition;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.SysContent;

public class ContractRecordsBillDTO {
	
	//订购id
	private Integer recordNo;
	//中介佣金金额
	private String mediMoney;
	//平台佣金金额
	private double systemMoney;
	//项目名称
	private String proName;
	//佣金来源
	private String moneyComeFrom;
	//佣金类型
	private String MoneyType;
	//经纪人
	private String mediName;
	//客户姓名
	private String customeName;
	//客户电话
	private String customePhone;
	//结款时间
	private String payMoneyTime;
	//确认到款时间
	private String receiveMoneyTime;
	//带看时间
	private String daiKanTime;
	//签约时间
	private String signTime;
	//中介结款状态
	private String mediPayMoneyStatus;
	private Integer mediPayMoneytype;
	//平台结款状态
	private String systemPayMoneyStatus;
	private Integer systemPayMoneytype;
	//结款截至日期
	private String payMoneyEndTime;
	
	
	public Integer getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}
	public String getMediMoney() {
		return mediMoney;
	}
	public void setMediMoney(String mediMoney) {
		this.mediMoney = mediMoney;
	}
	public double getSystemMoney() {
		return systemMoney;
	}
	public void setSystemMoney(double systemMoney) {
		this.systemMoney = systemMoney;
	}
	public String getMoneyComeFrom() {
		return moneyComeFrom;
	}
	public void setMoneyComeFrom(String moneyComeFrom) {
		this.moneyComeFrom = moneyComeFrom;
	}
	public String getDaiKanTime() {
		return daiKanTime;
	}
	public void setDaiKanTime(String daiKanTime) {
		this.daiKanTime = daiKanTime;
	}
	public String getSignTime() {
		return signTime;
	}
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	public String getMediPayMoneyStatus() {
		return mediPayMoneyStatus;
	}
	public void setMediPayMoneyStatus(String mediPayMoneyStatus) {
		this.mediPayMoneyStatus = mediPayMoneyStatus;
	}
	public String getSystemPayMoneyStatus() {
		return systemPayMoneyStatus;
	}
	public void setSystemPayMoneyStatus(String systemPayMoneyStatus) {
		this.systemPayMoneyStatus = systemPayMoneyStatus;
	}
	public String getPayMoneyEndTime() {
		return payMoneyEndTime;
	}
	public void setPayMoneyEndTime(String payMoneyEndTime) {
		this.payMoneyEndTime = payMoneyEndTime;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getMoneyType() {
		return MoneyType;
	}
	public void setMoneyType(String moneyType) {
		MoneyType = moneyType;
	}
	public String getMediName() {
		return mediName;
	}
	public void setMediName(String mediName) {
		this.mediName = mediName;
	}
	public String getCustomeName() {
		return customeName;
	}
	public void setCustomeName(String customeName) {
		this.customeName = customeName;
	}
	public String getCustomePhone() {
		return customePhone;
	}
	public void setCustomePhone(String customePhone) {
		this.customePhone = customePhone;
	}
	public String getPayMoneyTime() {
		return payMoneyTime;
	}
	public void setPayMoneyTime(String payMoneyTime) {
		this.payMoneyTime = payMoneyTime;
	}
	public String getReceiveMoneyTime() {
		return receiveMoneyTime;
	}
	public void setReceiveMoneyTime(String receiveMoneyTime) {
		this.receiveMoneyTime = receiveMoneyTime;
	}
	public Integer getMediPayMoneytype() {
		return mediPayMoneytype;
	}
	public void setMediPayMoneytype(Integer mediPayMoneytype) {
		this.mediPayMoneytype = mediPayMoneytype;
	}
	public Integer getSystemPayMoneytype() {
		return systemPayMoneytype;
	}
	public void setSystemPayMoneytype(Integer systemPayMoneytype) {
		this.systemPayMoneytype = systemPayMoneytype;
	}
	/**
	 * 返回当前对象
	 * @return
	 */
	public ContractRecordsBillDTO createBillDTO(GuideRecords gr, Project pro,House h,ContractRecords cr,Boolean isDaiKan,SystemChargeDefinition sys) {
		this.recordNo = cr.getRecordNo();
		if(sys!=null && sys.getRewardType().equals(0)){
			this.systemMoney = sys.getReward();
		}else if(sys!=null && sys.getRewardType().equals(1)){
			this.systemMoney = sys.getReward() * cr.getPrice();
		}
		HashMap grMap = JSON.parseObject(gr.getRules(), HashMap.class);
		if(isDaiKan){
			double commPerc = Double.parseDouble(grMap.get("daiKanMoney").toString());
			double dmediMoney = cr.getBuyPrice() * commPerc / 100;
			this.mediMoney = SysContent.get2Double(dmediMoney);
		}else{
			double commPerc = Double.parseDouble(grMap.get("fenXiaoMoney").toString());
			double DouDmediMoney = cr.getBuyPrice() * commPerc / 100;
			this.mediMoney = SysContent.get2Double(DouDmediMoney);
		}
		this.moneyComeFrom = pro.getProjectName()+","+h.getBuildingNo()+"栋"+h.getUnit()+"单元"+h.getHouseNo()+"号";
		
		this.signTime = cr.getContractConfirmTime();
		this.mediPayMoneytype = cr.getIsShopPayConfirm();
		this.systemPayMoneytype = cr.getIsSystemPayConfirm();
		if(cr.getIsShopPayConfirm()==null || cr.getIsShopPayConfirm().equals("") || cr.getIsShopPayConfirm()==0){//0：未结款；1已结款；2已到款
			this.mediPayMoneyStatus = "待结款";
		}else if(cr.getIsShopPayConfirm()==1){
			this.mediPayMoneyStatus = "已结款";
		}else if(cr.getIsShopPayConfirm()==2){
			this.mediPayMoneyStatus = "已到款";
		}
		if(cr.getIsSystemPayConfirm()==null || cr.getIsSystemPayConfirm().equals("") || cr.getIsSystemPayConfirm()==0){
			this.systemPayMoneyStatus = "待结款";
		}else if(cr.getIsSystemPayConfirm()==1){
			this.systemPayMoneyStatus="已结款";
		}else if(cr.getIsSystemPayConfirm()==2){
			this.systemPayMoneyStatus="已到款";
		}
		if(grMap.get("isFast").equals(0)){//0不支持快速结佣,结款时间=确认签约时间+2个月;否则加俩周（14天）
			this.payMoneyEndTime = DateTime.getAddSameMonthNewDay(cr.getContractConfirmTime(), 2);
		}else{
			this.payMoneyEndTime = DateTime.getNewDay(cr.getContractConfirmTime(), 14);
		}
		return this;
	}
	/**
	 * 门店对账单list的dto对象
	 * @param gr
	 * @param pro
	 * @param h
	 * @param cr
	 * @param isDaiKan
	 * @return
	 */
	public ContractRecordsBillDTO createShoperBillDTO(GuideRecords gr, Project pro, House h, ContractRecords cr,Boolean isDaiKan) {
		this.recordNo = cr.getRecordNo();
		this.proName = pro.getProjectName();
		HashMap grMap = JSON.parseObject(gr.getRules(), HashMap.class);
		if(isDaiKan){
			double commPerc = Double.parseDouble(grMap.get("daiKanMoney").toString());
			double dmediMoney = cr.getBuyPrice() * commPerc / 100;
			this.mediMoney = SysContent.get2Double(dmediMoney);
			this.MoneyType = "带看佣金";
		}else{
			double commPerc = Double.parseDouble(grMap.get("fenXiaoMoney").toString());
			double dmediMoney = cr.getBuyPrice() * commPerc / 100;
			this.mediMoney = SysContent.get2Double(dmediMoney);
			this.MoneyType = "分销佣金";
		}
		this.moneyComeFrom = h.getBuildingNo()+"栋"+h.getUnit()+"单元"+h.getHouseNo()+"号";
		this.customePhone = cr.getCustomerPhone();
		this.signTime = cr.getContractConfirmTime();
		this.mediPayMoneytype = cr.getIsShopPayConfirm();
		this.systemPayMoneytype = cr.getIsSystemPayConfirm();
		if(cr.getIsShopPayConfirm()==0){//0：未结款；1已结款；2已到款
			this.mediPayMoneyStatus = "待结款";
		}else if(cr.getIsShopPayConfirm()==1){
			this.mediPayMoneyStatus = "已结款";
		}else if(cr.getIsShopPayConfirm()==2){
			this.mediPayMoneyStatus = "已到款";
		}
		if(cr.getIsSystemPayConfirm()==0){
			this.systemPayMoneyStatus = "待结款";
		}else if(cr.getIsSystemPayConfirm()==1){
			this.systemPayMoneyStatus="已结款";
		}else if(cr.getIsSystemPayConfirm()==2){
			this.systemPayMoneyStatus="已到款";
		}
		this.payMoneyTime = cr.getShopPayConfirmTime();
		this.receiveMoneyTime = cr.getShopPayConfirmTime();
		if(grMap.get("isFast").equals(0)){//0不支持快速结佣,结款时间=确认签约时间+2个月;否则加俩周（14天）
			this.payMoneyEndTime = DateTime.getAddSameMonthNewDay(cr.getContractConfirmTime(), 2);
		}else{
			this.payMoneyEndTime = DateTime.getNewDay(cr.getContractConfirmTime(), 14);
		}
		return this;
	}

}
