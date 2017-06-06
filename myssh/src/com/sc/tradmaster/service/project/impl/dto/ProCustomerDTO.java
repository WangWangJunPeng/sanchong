package com.sc.tradmaster.service.project.impl.dto;

import com.sc.tradmaster.bean.ProjectCustomers;

/**
 * 案场客户DTO
 * 
 * @author Administrator
 *
 */
public class ProCustomerDTO {

	// 案场编码
	private String projectId;
	// 客户编码
	private String projectCustomerId;
	// 客户名称
	private String projectCustomerName;
	// 客户电话,唯一性
	private String projectCustomerPhone;
	// 身份证号码
	private String idCard;
	// 性别,0:未知,1:male,2;famle
	private Integer sex;
	// 当前归属的职业顾问用户编码 由案场经理分配
	private String ownerUserId;
	// 客户最后到案场的时间
	private String lastTime;
	
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectCustomerId() {
		return projectCustomerId;
	}
	public void setProjectCustomerId(String projectCustomerId) {
		this.projectCustomerId = projectCustomerId;
	}
	public String getProjectCustomerName() {
		return projectCustomerName;
	}
	public void setProjectCustomerName(String projectCustomerName) {
		this.projectCustomerName = projectCustomerName;
	}
	public String getProjectCustomerPhone() {
		return projectCustomerPhone;
	}
	public void setProjectCustomerPhone(String projectCustomerPhone) {
		this.projectCustomerPhone = projectCustomerPhone;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getOwnerUserId() {
		return ownerUserId;
	}
	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	
	public ProCustomerDTO creatProCusDTO(ProjectCustomers proCus){
		this.projectId = proCus.getProjectId();
		this.projectCustomerId = proCus.getProjectCustomerId();
		this.projectCustomerName = proCus.getProjectCustomerName();
		this.projectCustomerPhone = proCus.getProjectCustomerPhone();
		this.idCard = proCus.getIdCard();
		this.sex = proCus.getSex();
		this.ownerUserId = proCus.getOwnerUserId();
		this.lastTime = proCus.getLastTime();
		
		return this;
	}
}
