package com.sc.tradmaster.service.agent.impl.visitDTO;

public class Customer {
	
	//置业顾问id
	private String userId;
	//客户姓名
	private String customerName;
	//首次到访时间
	private String firstVisitTime;
	//末次到访时间
	private String lastVisitTime;
	//到访次数
	private Integer visitCount;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getFirstVisitTime() {
		return firstVisitTime;
	}
	public void setFirstVisitTime(String firstVisitTime) {
		this.firstVisitTime = firstVisitTime;
	}
	public String getLastVisitTime() {
		return lastVisitTime;
	}
	public void setLastVisitTime(String lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	@Override
	public String toString() {
		return "Customer [userId=" + userId + ", customerName=" + customerName + ", firstVisitTime=" + firstVisitTime
				+ ", lastVisitTime=" + lastVisitTime + ", visitCount=" + visitCount + "]";
	}
}
