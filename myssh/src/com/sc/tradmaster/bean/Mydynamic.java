package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wjp 2017-04-06
 */
@Entity
@Table(name = "t_mydynamic")
public class Mydynamic {

	//动态Id(自增长)
	private Integer dynamicId;
	//置业顾问/中介经纪人id
	private String userId;
	//置业顾问/中介经纪人号码
	private String userPhone;
	//案场客户id
	private String projectCustomerId;
	//中介客户id
	private String shopCustomerId;
	//客户名字
	private String customerName;
	//客户电话
	private String customerPhone;
	//动态内容
	private String dynamicInfo;
	//是否被看(0:没看,1:已被看)
	private Integer isRead;
	//动态新建时间
	private String creatTime;
	//动态查阅时间
	private String readTime;
	//项目id
	private String projectId;
	//项目名字
	private String projectName;
	//houseNum
	private String hosueNum;
	//预留的说明字段
	private String description;
	//预留的标签字段
	private String tags;
	//动态状态(0,保留,1:删除)
	private String isDelete;
	//动态类型(备案成功,备案失败,确认到访,认购申请,认购同意,提交打款,确认到款,签约成功)
	private String dynamicStatus;
	
	@Id
    @GeneratedValue
	public Integer getDynamicId() {
		return dynamicId;
	}
	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getProjectCustomerId() {
		return projectCustomerId;
	}
	public void setProjectCustomerId(String projectCustomerId) {
		this.projectCustomerId = projectCustomerId;
	}
	public String getShopCustomerId() {
		return shopCustomerId;
	}
	public void setShopCustomerId(String shopCustomerId) {
		this.shopCustomerId = shopCustomerId;
	}
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
	public String getDynamicInfo() {
		return dynamicInfo;
	}
	public void setDynamicInfo(String dynamicInfo) {
		this.dynamicInfo = dynamicInfo;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	public String getReadTime() {
		return readTime;
	}
	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}
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
	public String getHosueNum() {
		return hosueNum;
	}
	public void setHosueNum(String hosueNum) {
		this.hosueNum = hosueNum;
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
	
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	public String getDynamicStatus() {
		return dynamicStatus;
	}
	public void setDynamicStatus(String dynamicStatus) {
		this.dynamicStatus = dynamicStatus;
	}
	@Override
	public String toString() {
		return "Mydynamic [dynamicId=" + dynamicId + ", userId=" + userId + ", userPhone=" + userPhone
				+ ", projectCustomerId=" + projectCustomerId + ", shopCustomerId=" + shopCustomerId + ", customerName="
				+ customerName + ", customerPhone=" + customerPhone + ", dynamicInfo=" + dynamicInfo + ", isRead="
				+ isRead + ", creatTime=" + creatTime + ", readTime=" + readTime + ", projectId=" + projectId
				+ ", projectName=" + projectName + ", hosueNum=" + hosueNum + ", description=" + description + ", tags="
				+ tags + ", isDelete=" + isDelete + ", dynamicStatus=" + dynamicStatus + "]";
	}
}
