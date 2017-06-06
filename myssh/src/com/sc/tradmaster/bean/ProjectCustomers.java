package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*
* @author wjp 2017-01-22
*/
@Entity
@Table(name="t_projectcustomers")
public class ProjectCustomers {
	//案场编码
	private String projectId;
	//客户编码
	private String projectCustomerId;
	//客户名称
	private String projectCustomerName;
	//客户电话,唯一性
	private String projectCustomerPhone;
	//身份证号码
	private String idCard;
	//最初建立的用户编码(最初归属id)
	private String createUserId;
	//建立时间
	private String createTime;
	//性别,0:未知,1:male,2;famle
	private Integer sex;
	//当前归属的职业顾问用户编码   由案场经理分配
	private String ownerUserId;
	//归属当前置业顾问名下的起始时间()
	private String ownerStartTime;
	//预留的描述字段
	private String description;
	//预留的标签字段
	private String tags;
	//客户最后到案场的时间
	private String lastTime;
	//经理点评
	private String evaluate;
	
	
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Id
	public String getProjectCustomerId() {
		return projectCustomerId;
	}
	public void setProjectCustomerId(String projectCustomerId) {
		this.projectCustomerId = projectCustomerId;
	}
	public String getProjectCustomerName() {
		return projectCustomerName;
	}
	public void setProjectCustomerName(String projectCustomerName) {
		this.projectCustomerName = projectCustomerName;
	}
	public String getProjectCustomerPhone() {
		return projectCustomerPhone;
	}
	public void setProjectCustomerPhone(String projectCustomerPhone) {
		this.projectCustomerPhone = projectCustomerPhone;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getOwnerUserId() {
		return ownerUserId;
	}
	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}
	public String getOwnerStartTime() {
		return ownerStartTime;
	}
	public void setOwnerStartTime(String ownerStartTime) {
		this.ownerStartTime = ownerStartTime;
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
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	@Override
	public String toString() {
		return "ProjectCustomers [projectId=" + projectId + ", projectCustomerId=" + projectCustomerId
				+ ", projectCustomerName=" + projectCustomerName + ", projectCustomerPhone=" + projectCustomerPhone
				+ ", idCard=" + idCard + ", createUserId=" + createUserId + ", createTime=" + createTime + ", sex="
				+ sex + ", ownerUserId=" + ownerUserId + ", ownerStartTime=" + ownerStartTime + ", description="
				+ description + ", tags=" + tags + ", lastTime=" + lastTime + "]";
	}
}
