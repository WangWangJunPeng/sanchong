package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 2017-02-06
 * @author grl 
 *
 */
@Entity
@Table(name="t_medishop")
public class MideShop {
	//中介门店主键，自动生长
	private Integer shopId;
	//门店状态，0：申请、1：正常、2：删除、3：删除
	private Integer shopStatus;
	//门店申请时间
	private String shopApplyTime;
	//门店批准时间
	private String shopApproveTime;
	//批准的用户编号
	private String approveUserId;
	//门店名称
	private String shopName;
	//门店地址
	private String shopDress;
	//所在城市（省市区）
	private String city;
	//经纬度（备用）
	private String location;
	//联系人姓名
	private String contactPerson;
	//联系人电话
	private String contactPhone;
	//联系人邮箱
	private String contactEmail;
	//关兴趣的区域（集合）
	private String interestAreas;
	//感兴趣的案场（集合，存储收集的案场Id）
	private String interestProjects;
	
	@Id
	@GeneratedValue
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getShopStatus() {
		return shopStatus;
	}
	public void setShopStatus(Integer shopStatus) {
		this.shopStatus = shopStatus;
	}
	public String getShopApplyTime() {
		return shopApplyTime;
	}
	public void setShopApplyTime(String shopApplyTime) {
		this.shopApplyTime = shopApplyTime;
	}
	public String getShopApproveTime() {
		return shopApproveTime;
	}
	public void setShopApproveTime(String shopApproveTime) {
		this.shopApproveTime = shopApproveTime;
	}
	public String getApproveUserId() {
		return approveUserId;
	}
	public void setApproveUserId(String approveUserId) {
		this.approveUserId = approveUserId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopDress() {
		return shopDress;
	}
	public void setShopDress(String shopDress) {
		this.shopDress = shopDress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getInterestAreas() {
		return interestAreas;
	}
	public void setInterestAreas(String interestAreas) {
		this.interestAreas = interestAreas;
	}
	public String getInterestProjects() {
		return interestProjects;
	}
	public void setInterestProjects(String interestProjects) {
		this.interestProjects = interestProjects;
	}
	
}
