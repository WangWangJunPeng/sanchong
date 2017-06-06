package com.sc.tradmaster.service.user.impl.dto;

import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;

/**
 * 
 * @author wjp
 *
 */
public class VisitRecordsDTO {
	
	//记录号,自动增长
	private Integer visitNo;
	//接访置业顾问编码
	private String userId;
	//案场编码
	private String projectId;
	//到访状态,0:到访,1:接待,2:删除,3:送别
	private Integer visitStatus;
	//到访人数
	private Integer customerCount;
	//客户姓名
	private String customerName;
	//客户电话
	private String phone;
	//备案号
	private Integer recordNo;
	//到访时间
	private String arriveTime;
	//接待时间
	private String receptTime;
	//送别时间
	private String leaveTime;
	//指定接待的置业顾问的编码
	private String appointUserId;
	//客户编码
	private String customerId;
	//预留的说明字段
	private String description;
	//预留的标签字段
	private String tags;
	//该记录填写状态,0:未填写,1:已填写
	private Integer writeState;
	//默认为true
	private Boolean isNew;
	
	//置业顾问名字
	private String agentName;

	public Integer getVisitNo() {
		return visitNo;
	}

	public void setVisitNo(Integer visitNo) {
		this.visitNo = visitNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getVisitStatus() {
		return visitStatus;
	}

	public void setVisitStatus(Integer visitStatus) {
		this.visitStatus = visitStatus;
	}

	public Integer getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(Integer customerCount) {
		this.customerCount = customerCount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getReceptTime() {
		return receptTime;
	}

	public void setReceptTime(String receptTime) {
		this.receptTime = receptTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getAppointUserId() {
		return appointUserId;
	}

	public void setAppointUserId(String appointUserId) {
		this.appointUserId = appointUserId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer getWriteState() {
		return writeState;
	}

	public void setWriteState(Integer writeState) {
		this.writeState = writeState;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	@Override
	public String toString() {
		return "VisitRecordsDTO [visitNo=" + visitNo + ", userId=" + userId + ", projectId=" + projectId
				+ ", visitStatus=" + visitStatus + ", customerCount=" + customerCount + ", customerName=" + customerName
				+ ", phone=" + phone + ", recordNo=" + recordNo + ", arriveTime=" + arriveTime + ", receptTime="
				+ receptTime + ", leaveTime=" + leaveTime + ", appointUserId=" + appointUserId + ", customerId="
				+ customerId + ", description=" + description + ", tags=" + tags + ", writeState=" + writeState
				+ ", isNew=" + isNew + ", agentName=" + agentName + "]";
	}
	
	public VisitRecordsDTO getVisitRecordsDTO(VisitRecords vr,User user){
		
		this.visitNo = vr.getVisitNo();
		this.userId = vr.getUserId();
		this.projectId = vr.getProjectId();
		this.visitStatus = vr.getVisitStatus();
		this.customerCount = vr.getCustomerCount();
		this.customerName =vr.getCustomerName();
		this.phone = vr.getPhone();
		this.recordNo = vr.getRecordNo();
		this.arriveTime = vr.getArriveTime();
		this.receptTime = vr.getReceptTime();
		this.leaveTime = vr.getLeaveTime();
		this.appointUserId = vr.getAppointUserId();
		this.customerId = vr.getCustomerId();
		this.description = vr.getDescription();
		this.tags = vr.getTags();
		this.writeState = vr.getWriteState();
		this.isNew = vr.getIsNew();
		
		this.agentName = user.getUserCaption();
		
		return this;
	}
}
