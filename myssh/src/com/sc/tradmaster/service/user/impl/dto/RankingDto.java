package com.sc.tradmaster.service.user.impl.dto;

public class RankingDto {
	private String userId;
	private String userName;
	private String phone;
	private Integer visitNum;
	private Integer rengouNum;
	private Integer contractNum;
	private Integer slewRate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}
	public Integer getRengouNum() {
		return rengouNum;
	}
	public void setRengouNum(Integer rengouNum) {
		this.rengouNum = rengouNum;
	}
	public Integer getContractNum() {
		return contractNum;
	}
	public void setContractNum(Integer contractNum) {
		this.contractNum = contractNum;
	}
	public Integer getSlewRate() {
		return slewRate;
	}
	public void setSlewRate(Integer slewRate) {
		this.slewRate = slewRate;
	}
	@Override
	public String toString() {
		return "RankingDto [userId=" + userId + ", userName=" + userName + ", phone=" + phone + ", visitNum=" + visitNum
				+ ", rengouNum=" + rengouNum + ", contractNum=" + contractNum + ", slewRate=" + slewRate + "]";
	}
	
}
