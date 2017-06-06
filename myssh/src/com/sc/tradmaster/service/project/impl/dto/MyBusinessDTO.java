package com.sc.tradmaster.service.project.impl.dto;

import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ShopCustomers;

public class MyBusinessDTO {
	//项目id
	private String projectId;
	//项目名称
	private String projectName;
	//客户名称
	private String cusName;
	//当前状态
	private String currentStatus;
	//备案时间
	private String keepRecordTime;
	//将过期
	private Integer nearOverdue;
	//已备案
	private Integer guideRecords;
	//已到访
	private Integer visitRecords;
	//已成交
	private Integer deal;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getNearOverdue() {
		return nearOverdue;
	}
	public void setNearOverdue(Integer nearOverdue) {
		this.nearOverdue = nearOverdue;
	}
	public Integer getGuideRecords() {
		return guideRecords;
	}
	public void setGuideRecords(Integer guideRecords) {
		this.guideRecords = guideRecords;
	}
	public Integer getVisitRecords() {
		return visitRecords;
	}
	public void setVisitRecords(Integer visitRecords) {
		this.visitRecords = visitRecords;
	}
	public Integer getDeal() {
		return deal;
	}
	public void setDeal(Integer deal) {
		this.deal = deal;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getKeepRecordTime() {
		return keepRecordTime;
	}
	public void setKeepRecordTime(String keepRecordTime) {
		this.keepRecordTime = keepRecordTime;
	}
	@Override
	public String toString() {
		return "MyBusinessDTO [projectId=" + projectId + ", projectName=" + projectName + ", nearOverdue=" + nearOverdue
				+ ", guideRecords=" + guideRecords + ", visitRecords=" + visitRecords + ", deal=" + deal + "]";
	}
	
	public MyBusinessDTO creatShoperBusinessDto(ShopCustomers sc,GuideRecords gr,Project p){
		this.cusName = sc.getShopCustomerName();
		this.projectName = p.getProjectName();
		if(gr.getApplyStatus().equals(0)){
			this.currentStatus = "已备案";
		}else{
			this.currentStatus = "已到访";
		}
		this.keepRecordTime = gr.getApplyTime();
		return this;
	}
}
