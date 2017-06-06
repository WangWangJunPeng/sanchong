package com.sc.tradmaster.service.user.impl.dto;

import java.util.List;

import com.sc.tradmaster.bean.ProjectCustomers;

public class AllCheckedCustomerDTO {
	
	private String agentId;
	private String agentName;
	private String phone;
	private String photo;
	private Integer notCheckedNum;
	private Integer haveCheckedNum;
	
	private List<ProjectCustomers> notCheckedList;
	private List<ProjectCustomers> haveCheckedList;

	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getPhoto() {
		return photo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getNotCheckedNum() {
		return notCheckedNum;
	}
	public void setNotCheckedNum(Integer notCheckedNum) {
		this.notCheckedNum = notCheckedNum;
	}
	public Integer getHaveCheckedNum() {
		return haveCheckedNum;
	}
	public void setHaveCheckedNum(Integer haveCheckedNum) {
		this.haveCheckedNum = haveCheckedNum;
	}
	public List<ProjectCustomers> getNotCheckedList() {
		return notCheckedList;
	}
	public void setNotCheckedList(List<ProjectCustomers> notCheckedList) {
		this.notCheckedList = notCheckedList;
	}
	public List<ProjectCustomers> getHaveCheckedList() {
		return haveCheckedList;
	}
	public void setHaveCheckedList(List<ProjectCustomers> haveCheckedList) {
		this.haveCheckedList = haveCheckedList;
	}
	@Override
	public String toString() {
		return "AllCheckedCustomerDTO [agentId=" + agentId + ", agentName=" + agentName + ", phone=" + phone
				+ ", photo=" + photo + ", notCheckedNum=" + notCheckedNum + ", haveCheckedNum=" + haveCheckedNum
				+ ", notCheckedList=" + notCheckedList + ", haveCheckedList=" + haveCheckedList + "]";
	}
	
}
