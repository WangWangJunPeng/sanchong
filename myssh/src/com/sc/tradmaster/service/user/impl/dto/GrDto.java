package com.sc.tradmaster.service.user.impl.dto;

public class GrDto {
	//备案客户数
	private String grAllNum;
	//备案已到访数
	private String haveVisitNum;
	//备案未到访
	private String notVisitNum;
	//中介门店id
	private Integer shopId;
	//中介门店名称
	private String shopName;
	//中介公司名称
	private String companyName;
	//地址
	private String address;
	//省,市,区
	private String city;
	//联系人电话
	private String phone;
	//报备到访率
	private String visitPercent;
	
	
	public String getGrAllNum() {
		return grAllNum;
	}
	public void setGrAllNum(String grAllNum) {
		this.grAllNum = grAllNum;
	}
	public String getHaveVisitNum() {
		return haveVisitNum;
	}
	public void setHaveVisitNum(String haveVisitNum) {
		this.haveVisitNum = haveVisitNum;
	}
	public String getNotVisitNum() {
		return notVisitNum;
	}
	public void setNotVisitNum(String notVisitNum) {
		this.notVisitNum = notVisitNum;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getVisitPercent() {
		return visitPercent;
	}
	public void setVisitPercent(String visitPercent) {
		this.visitPercent = visitPercent;
	}
	@Override
	public String toString() {
		return "GrDto [grAllNum=" + grAllNum + ", haveVisitNum=" + haveVisitNum + ", notVisitNum=" + notVisitNum
				+ ", shopId=" + shopId + ", shopName=" + shopName + ", companyName=" + companyName + ", address="
				+ address + ", city=" + city + ", phone=" + phone + ", visitPercent=" + visitPercent + "]";
	}
}
