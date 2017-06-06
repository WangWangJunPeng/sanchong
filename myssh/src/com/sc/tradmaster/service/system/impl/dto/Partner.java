package com.sc.tradmaster.service.system.impl.dto;

import java.util.List;


/**
 * 该类用于封装合伙人 与 案场 门店的关系
 * @author Administrator
 *
 */
public class Partner {
	private String partnerName; //合伙人姓名
	private String phone;//合伙人登入账号
	private String partnerId; //合伙人Id
	private List<ProjectOfPartner> projects;
	private List<ShopOfPartner> shops;
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public List<ProjectOfPartner> getProjects() {
		return projects;
	}
	public void setProjects(List<ProjectOfPartner> projects) {
		this.projects = projects;
	}
	public List<ShopOfPartner> getShops() {
		return shops;
	}
	public void setShops(List<ShopOfPartner> shops) {
		this.shops = shops;
	}
	
	
	
	
	
	
}
