package com.sc.tradmaster.service.projectcustomer.impl.dto;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.ShopCustomers;
import com.sc.tradmaster.bean.User;

public class CustomerManager {
	//案场客户id
	private String proCusId;
	//案场客户姓名
	private String cusName;
	//案场客户归属置业顾问
	private String cusOwnerAgent;
	//是否成交num标识
	private Integer alreadyDeal;
	//是否已成交
	private String isSign;
	//门店客户id
	private String shopCusId;
	//客户性别
	private String cusSex;
	//客户电话
	private String phone;
	//备案楼盘
	private Integer records;
	//所属中介
	private String theMedi;
	//添加时间
	private String addTime;
	//备注信息
	private String description;
	
	public String getShopCusId() {
		return shopCusId;
	}
	public void setShopCusId(String shopCusId) {
		this.shopCusId = shopCusId;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusSex() {
		return cusSex;
	}
	public void setCusSex(String cusSex) {
		this.cusSex = cusSex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	public Integer getAlreadyDeal() {
		return alreadyDeal;
	}
	public void setAlreadyDeal(Integer alreadyDeal) {
		this.alreadyDeal = alreadyDeal;
	}
	public String getTheMedi() {
		return theMedi;
	}
	public void setTheMedi(String theMedi) {
		this.theMedi = theMedi;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getProCusId() {
		return proCusId;
	}
	public void setProCusId(String proCusId) {
		this.proCusId = proCusId;
	}
	public String getCusOwnerAgent() {
		return cusOwnerAgent;
	}
	public void setCusOwnerAgent(String cusOwnerAgent) {
		this.cusOwnerAgent = cusOwnerAgent;
	}
	public String getIsSign() {
		return isSign;
	}
	public void setIsSign(String isSign) {
		this.isSign = isSign;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public CustomerManager createCusManObj(ShopCustomers scs,User u){
		this.shopCusId = scs.getShopCustomerId();
		this.cusName = scs.getShopCustomerName();
		if(scs.getSex()!=null && scs.getSex().equals(1)){
			this.cusSex = "男";
		}else if(scs.getSex()!=null && scs.getSex().equals(2)){
			this.cusSex = "女";
		}else{
			this.cusSex = "未知";
		}
		this.phone = scs.getShopCustomerPhone();
		this.theMedi = u.getUserCaption();
		this.addTime = scs.getCreateTime();
		return this;
	}
	
	public CustomerManager createCusMan(ProjectCustomers pc,User u,ContractRecords crs){
		this.proCusId = pc.getProjectCustomerId();
		this.cusName = pc.getProjectCustomerName();
		if(pc.getSex()!=null && pc.getSex().equals(1)){
			this.cusSex = "男";
		}else if(pc.getSex()!=null && pc.getSex().equals(2)){
			this.cusSex = "女";
		}else{
			this.cusSex = "未知";
		}
		this.phone = pc.getProjectCustomerPhone();
		if(u!=null){
			this.cusOwnerAgent = u.getUserCaption();
		}else{
			this.cusOwnerAgent = "未知";
		}
		this.addTime = pc.getCreateTime();
		this.description = pc.getDescription();
		if(crs!=null){
			this.isSign = "是";
		}else{
			this.isSign = "否";
		}
		return this;
	}
	
	
	
	
}
