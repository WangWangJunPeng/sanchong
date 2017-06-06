package com.sc.tradmaster.bean;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
*
* @author wjp 2017-02-03
*/
@Entity
@Table(name="t_contractrecords")
public class ContractRecords {
	//记录号,自动增长
	private Integer recordNo;
	//申请人用户编码
	private String userId;
	//案场编码
	private String projectId;
	//房源编码,唯一性
	private Integer houseNum;
	//客户姓名
	private String customerName;
	//客户身份证号码
	private String customerIDCard;
	//客户电话
	private String customerPhone;
	//优惠条款列表(集合),按顺序计算
	private String benefitlds;
	//成交价格
	private Double price;
	//状态,0:申请,1:同意,2:删除,3:否决,4:到款,5:签约,6:退款,7:撤销
	private Integer recordStatus;
	//申请时间
	private String applyTime;
	//审核时间
	private String auditingTime;
	//审核用户编码
	private String auditionUserId;
	//审核说明
	private String auditionReson;
	//汇款单号
	private String remitNo;
	//订购金到款确认时间
	private String remitConfirmTime;
	//订购金到款确认用户编码
	private String remitConfirmUserId;
	//签约确认时间
	private String contractConfirmTime;
	//签约确认用户编码
	private String contractconfirmUseerId;
	//是否中介结款(0：未结款；1已结款;2已到款)
	private Integer isShopPayConfirm;
	//中介结账确认时间
	private String shopPayConfirmTime;
	//中介结账确认用户编码
	private String shopPayConfirmUserId;
	// 中介结款说明
	private String shopPayConfirmDesc;
	// 取消中介结款说明
	private String cancelShopPayConfirmDesc;
	//是否平台结账(0：未结款；1已结款；2已到款)
	private Integer isSystemPayConfirm;
	//平台结账确认时间
	private String systemPayConfirmTime;
	//平台结账确认用户编码
	private String systemPayConfirmUserId;
	//平台结款说明
	private String systemPayConfirmDesc;
	//取消平台结款说明
	private String cancelSystemPayConfirmDesc;
	//中介到款确认时间
	private String shopReceiveConfirmTime;
	//中介到款确认用户编码
	private String shopReceiveConfirmUserId;
	//中介取消到款说明
	private String shopCancelReceiveConfirmDesc;
	//平台到款确认时间
	private String systemReceiveConfirmTime;
	//平台到款确认用户编码
	private String systemReceiveConfirmUserId;
	//预留的标签字段
	private String tags;
	//预留的说明字段
	private String description;
	//凭证类型,0:网银汇款,1:现场交款,2:支付宝
	private Integer credentialsType;
	private String credentialsNum;
	//凭证照片url
	private String credentialsPhoto;
	//购买价格
	private Double buyPrice;
	//凭证上传时间
	private String voucherUploadTime;
	//到款审核说明
	private String getMoneyDesc;
	//案场客户id
	private String projectCustomerId;
	//是否打款超期
	private String isOut;
	//支付方式 0线上 1线下
	private Integer payStyle;
	//订单性质(0:自购,1:代购)
	private Integer orderProperty;
	//是否阅读全部条款(0:未读,1:已读)
	private Integer isAlreadyRead;
	//认购关系0,1,2.....
	private Integer enterBuyRelation;
	//结算方式 0一次性1公积金2商贷按揭3其他
	private Integer accountStyle;
	//撤单原因(单选框选择..........)
	private String killTheOrderReason;
	//撤单备注
	private String revokeOrderNotes;
	//撤单时间
	private String revokeTime;
	//优惠条款信息
	private String benefitInfo;
	//订单号
	private String orderNum;
	//认购认购人id
	private String shopCustomerId;
	//真实认购人id信息
	private String realCustomerId;
	//定金
	private String haveToPayMoney;
	
	
	@Id
	@GeneratedValue
	public Integer getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
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
	public Integer getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerIDCard() {
		return customerIDCard;
	}
	public void setCustomerIDCard(String customerIDCard) {
		this.customerIDCard = customerIDCard;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getBenefitlds() {
		return benefitlds;
	}
	public void setBenefitlds(String benefitlds) {
		this.benefitlds = benefitlds;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getAuditingTime() {
		return auditingTime;
	}
	public void setAuditingTime(String auditingTime) {
		this.auditingTime = auditingTime;
	}
	public String getAuditionUserId() {
		return auditionUserId;
	}
	public void setAuditionUserId(String auditionUserId) {
		this.auditionUserId = auditionUserId;
	}
	public String getAuditionReson() {
		return auditionReson;
	}
	public void setAuditionReson(String auditionReson) {
		this.auditionReson = auditionReson;
	}
	public String getRemitNo() {
		return remitNo;
	}
	public void setRemitNo(String remitNo) {
		this.remitNo = remitNo;
	}
	public String getRemitConfirmTime() {
		return remitConfirmTime;
	}
	public void setRemitConfirmTime(String remitConfirmTime) {
		this.remitConfirmTime = remitConfirmTime;
	}
	public String getRemitConfirmUserId() {
		return remitConfirmUserId;
	}
	public void setRemitConfirmUserId(String remitConfirmUserId) {
		this.remitConfirmUserId = remitConfirmUserId;
	}
	public String getContractConfirmTime() {
		return contractConfirmTime;
	}
	public void setContractConfirmTime(String contractConfirmTime) {
		this.contractConfirmTime = contractConfirmTime;
	}
	public String getContractconfirmUseerId() {
		return contractconfirmUseerId;
	}
	public void setContractconfirmUseerId(String contractconfirmUseerId) {
		this.contractconfirmUseerId = contractconfirmUseerId;
	}
	public String getShopPayConfirmTime() {
		return shopPayConfirmTime;
	}
	public void setShopPayConfirmTime(String shopPayConfirmTime) {
		this.shopPayConfirmTime = shopPayConfirmTime;
	}
	public String getShopPayConfirmUserId() {
		return shopPayConfirmUserId;
	}
	public void setShopPayConfirmUserId(String shopPayConfirmUserId) {
		this.shopPayConfirmUserId = shopPayConfirmUserId;
	}
	public String getSystemPayConfirmTime() {
		return systemPayConfirmTime;
	}
	public void setSystemPayConfirmTime(String systemPayConfirmTime) {
		this.systemPayConfirmTime = systemPayConfirmTime;
	}
	public String getSystemPayConfirmUserId() {
		return systemPayConfirmUserId;
	}
	public void setSystemPayConfirmUserId(String systemPayConfirmUserId) {
		this.systemPayConfirmUserId = systemPayConfirmUserId;
	}
	public String getShopReceiveConfirmTime() {
		return shopReceiveConfirmTime;
	}
	public void setShopReceiveConfirmTime(String shopReceiveConfirmTime) {
		this.shopReceiveConfirmTime = shopReceiveConfirmTime;
	}
	public String getShopReceiveConfirmUserId() {
		return shopReceiveConfirmUserId;
	}
	public void setShopReceiveConfirmUserId(String shopReceiveConfirmUserId) {
		this.shopReceiveConfirmUserId = shopReceiveConfirmUserId;
	}
	public String getSystemReceiveConfirmTime() {
		return systemReceiveConfirmTime;
	}
	public void setSystemReceiveConfirmTime(String systemReceiveConfirmTime) {
		this.systemReceiveConfirmTime = systemReceiveConfirmTime;
	}
	public String getSystemReceiveConfirmUserId() {
		return systemReceiveConfirmUserId;
	}
	public void setSystemReceiveConfirmUserId(String systemReceiveConfirmUserId) {
		this.systemReceiveConfirmUserId = systemReceiveConfirmUserId;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCredentialsType() {
		return credentialsType;
	}
	public void setCredentialsType(Integer credentialsType) {
		this.credentialsType = credentialsType;
	}
	public String getCredentialsPhoto() {
		return credentialsPhoto;
	}
	public void setCredentialsPhoto(String credentialsPhoto) {
		this.credentialsPhoto = credentialsPhoto;
	}
	public Double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getVoucherUploadTime() {
		return voucherUploadTime;
	}
	public void setVoucherUploadTime(String voucherUploadTime) {
		this.voucherUploadTime = voucherUploadTime;
	}
	public String getGetMoneyDesc() {
		return getMoneyDesc;
	}
	public void setGetMoneyDesc(String getMoneyDesc) {
		this.getMoneyDesc = getMoneyDesc;
	}
	
	public String getProjectCustomerId() {
		return projectCustomerId;
	}
	public void setProjectCustomerId(String projectCustomerId) {
		this.projectCustomerId = projectCustomerId;
	}
	public Integer getIsShopPayConfirm() {
		return isShopPayConfirm;
	}
	public void setIsShopPayConfirm(Integer isShopPayConfirm) {
		this.isShopPayConfirm = isShopPayConfirm;
	}
	public String getShopPayConfirmDesc() {
		return shopPayConfirmDesc;
	}
	public void setShopPayConfirmDesc(String shopPayConfirmDesc) {
		this.shopPayConfirmDesc = shopPayConfirmDesc;
	}
	public String getCancelShopPayConfirmDesc() {
		return cancelShopPayConfirmDesc;
	}
	public void setCancelShopPayConfirmDesc(String cancelShopPayConfirmDesc) {
		this.cancelShopPayConfirmDesc = cancelShopPayConfirmDesc;
	}
	public Integer getIsSystemPayConfirm() {
		return isSystemPayConfirm;
	}
	public void setIsSystemPayConfirm(Integer isSystemPayConfirm) {
		this.isSystemPayConfirm = isSystemPayConfirm;
	}
	public String getSystemPayConfirmDesc() {
		return systemPayConfirmDesc;
	}
	public void setSystemPayConfirmDesc(String systemPayConfirmDesc) {
		this.systemPayConfirmDesc = systemPayConfirmDesc;
	}
	public String getCancelSystemPayConfirmDesc() {
		return cancelSystemPayConfirmDesc;
	}
	public void setCancelSystemPayConfirmDesc(String cancelSystemPayConfirmDesc) {
		this.cancelSystemPayConfirmDesc = cancelSystemPayConfirmDesc;
	}
	public String getShopCancelReceiveConfirmDesc() {
		return shopCancelReceiveConfirmDesc;
	}
	public void setShopCancelReceiveConfirmDesc(String shopCancelReceiveConfirmDesc) {
		this.shopCancelReceiveConfirmDesc = shopCancelReceiveConfirmDesc;
	}
	public String getIsOut() {
		return isOut;
	}
	public void setIsOut(String isOut) {
		this.isOut = isOut;
	}
	public Integer getPayStyle() {
		return payStyle;
	}
	public void setPayStyle(Integer payStyle) {
		this.payStyle = payStyle;
	}
	public Integer getOrderProperty() {
		return orderProperty;
	}
	public void setOrderProperty(Integer orderProperty) {
		this.orderProperty = orderProperty;
	}
	public Integer getIsAlreadyRead() {
		return isAlreadyRead;
	}
	public void setIsAlreadyRead(Integer isAlreadyRead) {
		this.isAlreadyRead = isAlreadyRead;
	}
	public Integer getEnterBuyRelation() {
		return enterBuyRelation;
	}
	public void setEnterBuyRelation(Integer enterBuyRelation) {
		this.enterBuyRelation = enterBuyRelation;
	}
	public Integer getAccountStyle() {
		return accountStyle;
	}
	public void setAccountStyle(Integer accountStyle) {
		this.accountStyle = accountStyle;
	}
	public String getKillTheOrderReason() {
		return killTheOrderReason;
	}
	public void setKillTheOrderReason(String killTheOrderReason) {
		this.killTheOrderReason = killTheOrderReason;
	}
	public String getRevokeOrderNotes() {
		return revokeOrderNotes;
	}
	public void setRevokeOrderNotes(String revokeOrderNotes) {
		this.revokeOrderNotes = revokeOrderNotes;
	}
	public String getBenefitInfo() {
		return benefitInfo;
	}
	public void setBenefitInfo(String benefitInfo) {
		this.benefitInfo = benefitInfo;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getRevokeTime() {
		return revokeTime;
	}
	public void setRevokeTime(String revokeTime) {
		this.revokeTime = revokeTime;
	}
	public String getShopCustomerId() {
		return shopCustomerId;
	}
	public void setShopCustomerId(String shopCustomerId) {
		this.shopCustomerId = shopCustomerId;
	}
	public String getRealCustomerId() {
		return realCustomerId;
	}
	public void setRealCustomerId(String realCustomerId) {
		this.realCustomerId = realCustomerId;
	}
	public String getHaveToPayMoney() {
		return haveToPayMoney;
	}
	public void setHaveToPayMoney(String haveToPayMoney) {
		this.haveToPayMoney = haveToPayMoney;
	}
	public String getCredentialsNum() {
		return credentialsNum;
	}
	public void setCredentialsNum(String credentialsNum) {
		this.credentialsNum = credentialsNum;
	}
	@Override
	public String toString() {
		return "ContractRecords [recordNo=" + recordNo + ", userId=" + userId + ", projectId=" + projectId
				+ ", houseNum=" + houseNum + ", customerName=" + customerName + ", customerIDCard=" + customerIDCard
				+ ", customerPhone=" + customerPhone + ", benefitlds=" + benefitlds + ", price=" + price
				+ ", recordStatus=" + recordStatus + ", applyTime=" + applyTime + ", auditingTime=" + auditingTime
				+ ", auditionUserId=" + auditionUserId + ", auditionReson=" + auditionReson + ", remitNo=" + remitNo
				+ ", remitConfirmTime=" + remitConfirmTime + ", remitConfirmUserId=" + remitConfirmUserId
				+ ", contractConfirmTime=" + contractConfirmTime + ", contractconfirmUseerId=" + contractconfirmUseerId
				+ ", isShopPayConfirm=" + isShopPayConfirm + ", shopPayConfirmTime=" + shopPayConfirmTime
				+ ", shopPayConfirmUserId=" + shopPayConfirmUserId + ", shopPayConfirmDesc=" + shopPayConfirmDesc
				+ ", cancelShopPayConfirmDesc=" + cancelShopPayConfirmDesc + ", isSystemPayConfirm="
				+ isSystemPayConfirm + ", systemPayConfirmTime=" + systemPayConfirmTime + ", systemPayConfirmUserId="
				+ systemPayConfirmUserId + ", systemPayConfirmDesc=" + systemPayConfirmDesc
				+ ", cancelSystemPayConfirmDesc=" + cancelSystemPayConfirmDesc + ", shopReceiveConfirmTime="
				+ shopReceiveConfirmTime + ", shopReceiveConfirmUserId=" + shopReceiveConfirmUserId
				+ ", shopCancelReceiveConfirmDesc=" + shopCancelReceiveConfirmDesc + ", systemReceiveConfirmTime="
				+ systemReceiveConfirmTime + ", systemReceiveConfirmUserId=" + systemReceiveConfirmUserId + ", tags="
				+ tags + ", description=" + description + ", credentialsType=" + credentialsType + ", credentialsPhoto="
				+ credentialsPhoto + ", buyPrice=" + buyPrice + ", voucherUploadTime=" + voucherUploadTime
				+ ", getMoneyDesc=" + getMoneyDesc + ", projectCustomerId=" + projectCustomerId + ", isOut=" + isOut
				+ ", payStyle=" + payStyle + ", orderProperty=" + orderProperty + ", isAlreadyRead=" + isAlreadyRead
				+ ", enterBuyRelation=" + enterBuyRelation + ", accountStyle=" + accountStyle + ", killTheOrderReason="
				+ killTheOrderReason + ", revokeOrderNotes=" + revokeOrderNotes + ", revokeTime=" + revokeTime
				+ ", benefitInfo=" + benefitInfo + ", shopCustomerId=" + shopCustomerId
				+ ", realCustomerId=" + realCustomerId + "]";
	}
	//分页
	public Map getPageMap(Map m,Integer start,Integer limit){
		int pageSize = start+limit;
		Map<ContractRecords, House> map = new HashMap<>();
		if (pageSize >= m.size()){
			pageSize = m.size();
		}
		for(int i=start;i<pageSize;i++){
//			map.p
		}
		return null;
	}
}
