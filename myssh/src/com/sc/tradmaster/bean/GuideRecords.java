package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* 2017-01-22
* 带看记录
* @author wjp 
*/

@Entity
@Table(name="t_guiderecords")
public class GuideRecords {
	//记录号,自动增长
	private Integer recordNo;
	//案场客户编码
	private String projectCustomerId;
	//中介客户编码
	private String shopCustomerId;
	//客户名字
	private String customerName;
	//客户电话
	private String customerPhone;
	//经纪人用户编码
	private String userId;
	//案场编码
	private String projectId;
	//备案状态,0:申请(备案成功);1:确认(到访,到达案场);2:删除;3:否决(备案失败);4：备案逾期
	private Integer applyStatus;
	//申请时间
	private String applyTime;
	//到访记录编码
	private Integer visitNo;
	//客户最终是否成交,0:未成交;1:成交
	private Integer isDeal;
	//预留的说明字段
	private String description;
	//预留的标签字段
	private String tags;
	//备案失败说明字段,1:不支持带看,2:该客户为该案场客户且在保护期内,3:已被其他中介备案
	private String fail;
	//备案时 业务定义 字段 (带看业务定义表打包放进去)
	private String rules;
	
	@Id
	@GeneratedValue
	public Integer getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
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
	public Integer getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public Integer getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(Integer visitNo) {
		this.visitNo = visitNo;
	}
	public Integer getIsDeal() {
		return isDeal;
	}
	public void setIsDeal(Integer isDeal) {
		this.isDeal = isDeal;
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
	public String getFail() {
		return fail;
	}
	public void setFail(String fail) {
		this.fail = fail;
	}
	public String getRules() {
		return rules;
	}
	public void setRules(String rules) {
		this.rules = rules;
	}
	@Override
	public String toString() {
		return "GuideRecords [recordNo=" + recordNo + ", projectCustomerId=" + projectCustomerId + ", shopCustomerId="
				+ shopCustomerId + ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", userId="
				+ userId + ", projectId=" + projectId + ", applyStatus=" + applyStatus + ", applyTime=" + applyTime
				+ ", visitNo=" + visitNo + ", isDeal=" + isDeal + ", description=" + description + ", tags=" + tags
				+ ", fail=" + fail + ", rules=" + rules + "]";
	}
}
