package com.sc.tradmaster.service.project.impl.dto;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.dao.impl.BaseDaoImpl;
/**
 * 2017-02-07
 * @author grl
 *
 */
public class TodayNeedToDoDTO {
	//项目id
	private String proId;
	private String proName;
	//房号
	private Integer houseNum;
	private String proCity;
	//置业顾问或中介Id；
	private String aOrmId;
	//中介姓名
	private String mediName;
	//客户id
	private String cusId;
	private String cusName;
	//申请时间
	private String appTime;
	private Double appPrice;
	//打款时间
	private String palyTime;
	//签约时间
	private String contractConfirmTime;
	//带看备案记录id
	private Integer GuideRecordId;
	//带看/分销佣金比
	private Double commPerc;
	//购买价格
	private Double palyPrice;
	//成交价格
	private Double dealPrice;
	//定金
	private Double earnest;
	//提成金额
	private Double amount;
	
	/**
	 *today shop 今日门店 
	 */
	//当前状态
	private String currStatus;
	
	
	
	public String getCurrStatus() {
		return currStatus;
	}
	public void setCurrStatus(String currStatus) {
		this.currStatus = currStatus;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public Integer getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}
	public String getProCity() {
		return proCity;
	}
	public void setProCity(String proCity) {
		this.proCity = proCity;
	}
	public String getaOrmId() {
		return aOrmId;
	}
	public void setaOrmId(String aOrmId) {
		this.aOrmId = aOrmId;
	}
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getAppTime() {
		return appTime;
	}
	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}
	public Double getAppPrice() {
		return appPrice;
	}
	public void setAppPrice(Double appPrice) {
		this.appPrice = appPrice;
	}
	public String getPalyTime() {
		return palyTime;
	}
	public void setPalyTime(String palyTime) {
		this.palyTime = palyTime;
	}
	public String getContractConfirmTime() {
		return contractConfirmTime;
	}
	public void setContractConfirmTime(String contractConfirmTime) {
		this.contractConfirmTime = contractConfirmTime;
	}
	public Double getCommPerc() {
		return commPerc;
	}
	public void setCommPerc(Double commPerc) {
		this.commPerc = commPerc;
	}
	public Double getPalyPrice() {
		return palyPrice;
	}
	public void setPalyPrice(Double palyPrice) {
		this.palyPrice = palyPrice;
	}
	public Double getDealPrice() {
		return dealPrice;
	}
	public void setDealPrice(Double dealPrice) {
		this.dealPrice = dealPrice;
	}
	public Double getEarnest() {
		return earnest;
	}
	public void setEarnest(Double earnest) {
		this.earnest = earnest;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getGuideRecordId() {
		return GuideRecordId;
	}
	public void setGuideRecordId(Integer guideRecordId) {
		GuideRecordId = guideRecordId;
	}
	public String getMediName() {
		return mediName;
	}
	public void setMediName(String mediName) {
		this.mediName = mediName;
	}
	public TodayNeedToDoDTO getOneTNT(Project pro, ContractRecords crs,EnterBuy eb,GuideRecords gr,Boolean isDaiKan) {
		this.proId = pro.getProjectId();
		this.proName = pro.getProjectName();
		this.houseNum = crs.getHouseNum();
		this.aOrmId = crs.getUserId();
		this.cusId = crs.getProjectCustomerId();
		this.cusName = crs.getCustomerName();
		this.appTime = crs.getApplyTime();
		this.appPrice = crs.getBuyPrice();
		this.palyTime = crs.getVoucherUploadTime();
		this.palyPrice=crs.getPrice();
		this.earnest = eb.getDposit();
		this.getContractConfirmTime();
		this.dealPrice = crs.getPrice();
		if(gr!=null && isDaiKan!=null){
			BaseDao baseDao = new BaseDaoImpl();
			User u = (User) baseDao.loadById(User.class, aOrmId);
			this.mediName = u.getUserCaption();
			this.GuideRecordId = gr.getRecordNo();
			HashMap grMap = JSON.parseObject(gr.getRules(), HashMap.class);
			if(isDaiKan){
				this.commPerc = Double.parseDouble(grMap.get("daiKanMoney").toString());
				this.amount = dealPrice * commPerc / 100;
			}else{
				this.commPerc = Double.parseDouble(grMap.get("fenXiaoMoney").toString());
				this.amount = dealPrice * commPerc / 100;
			}
		}
		return this;
	}
	
	public TodayNeedToDoDTO createTodayShopObj(Project pro, ContractRecords crs,GuideRecords gr,User u,ProjectCustomers pc) {
		this.proId = pro.getProjectId();
		this.proName = pro.getProjectName();
		if(crs!=null){
			this.cusName = crs.getCustomerName();
			this.appTime = crs.getApplyTime();
			//0等待价格审批,1等待下定,2:删除,3:否决,4:到款,5:签约,6:退款
			if(crs.getRecordStatus().equals(0)){
				this.currStatus = "等待价格审批";
			}else if(crs.getRecordStatus().equals(1)){
				this.currStatus = "等待下定";
			}else if(crs.getRecordStatus().equals(2)){
				this.currStatus = "删除";
			}else if(crs.getRecordStatus().equals(3)){
				this.currStatus = "否决";
			}else if(crs.getRecordStatus().equals(4)){
				this.currStatus = "到款";
			}else if(crs.getRecordStatus().equals(5)){
				this.currStatus = "签约";
			}else if(crs.getRecordStatus().equals(6)){
				this.currStatus = "退款";
			}
		}
		if(gr!=null){
			this.mediName = u.getUserCaption();
			this.appTime = gr.getApplyTime();
			this.cusName = gr.getCustomerName();
		}
		if(pc!=null){
			this.mediName = u.getUserCaption();
			this.appTime = pc.getLastTime();
			this.cusName = pc.getProjectCustomerName();
		}
		return this;
	}
	
	
	
	
	
	
	
	
	
}
