package com.sc.tradmaster.service.visitRecords.impl.visitRecordDTO;

public class VisitCustomerDTO {
	
	private String customerName;
	private String customerPhone;
	private String applyTime;
	private String arriveTime;
	private String userCaption;
	private String userId;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getUserCaption() {
		return userCaption;
	}
	public void setUserCaption(String userCaption) {
		this.userCaption = userCaption;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "VisitCustomerDTO [customerName=" + customerName + ", customerPhone=" + customerPhone + ", applyTime="
				+ applyTime + ", arriveTime=" + arriveTime + ", userCaption=" + userCaption + ", userId=" + userId
				+ "]";
	}
	
}
