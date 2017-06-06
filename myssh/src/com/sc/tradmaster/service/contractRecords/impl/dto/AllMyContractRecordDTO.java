package com.sc.tradmaster.service.contractRecords.impl.dto;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.EnterBuyManAndRealEnterBuyManRelation;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.ProjectPics;
import com.sc.tradmaster.utils.SysContent;

public class AllMyContractRecordDTO {
	// 记录号,自动增长
	private Integer recordNo;
	// 申请人用户编码
	private String userId;
	// 案场编码
	private String projectId;
	//案场名称
	private String objectName;
	// 房源编码,唯一性
	private Integer houseNum;
	// 客户姓名
	private String customerName;
	// 客户身份证号码
	private String customerIDCard;
	// 客户电话
	private String customerPhone;
	// 优惠条款列表(集合),按顺序计算
	private String benefitlds;
	// 成交价格
	private Double price;
	// 状态,0:申请,1:同意,2:删除,3:否决,4:到款,5:签约,6:退款,7:撤销
	private Integer recordStatus;
	// 申请时间
	private String applyTime;
	// 审核时间
	private String auditingTime;
	// 审核用户编码
	private String auditionUserId;
	// 审核说明
	private String auditionReson;
	// 汇款单号
	private String remitNo;
	// 订购金到款确认时间
	private String remitConfirmTime;
	// 订购金到款确认用户编码
	private String remitConfirmUserId;
	// 签约确认时间
	private String contractConfirmTime;
	// 签约确认用户编码
	private String contractconfirmUseerId;
	// 是否中介结款(0：未结款；1已结款;2已到款)
	private Integer isShopPayConfirm;
	// 中介结账确认时间
	private String shopPayConfirmTime;
	// 中介结账确认用户编码
	private String shopPayConfirmUserId;
	// 中介结款说明
	private String shopPayConfirmDesc;
	// 取消中介结款说明
	private String cancelShopPayConfirmDesc;
	// 是否平台结账(0：未结款；1已结款；2已到款)
	private Integer isSystemPayConfirm;
	// 平台结账确认时间
	private String systemPayConfirmTime;
	// 平台结账确认用户编码
	private String systemPayConfirmUserId;
	// 平台结款说明
	private String systemPayConfirmDesc;
	// 取消平台结款说明
	private String cancelSystemPayConfirmDesc;
	// 中介到款确认时间
	private String shopReceiveConfirmTime;
	// 中介到款确认用户编码
	private String shopReceiveConfirmUserId;
	// 中介取消到款说明
	private String shopCancelReceiveConfirmDesc;
	// 平台到款确认时间
	private String systemReceiveConfirmTime;
	// 平台到款确认用户编码
	private String systemReceiveConfirmUserId;
	// 预留的标签字段
	private String tags;
	// 预留的说明字段
	private String description;
	// 凭证类型,0:网银汇款,1:现场交款,2:支付宝
	private Integer credentialsType;
	// 凭证照片url
	private String credentialsPhoto;
	// 购买价格
	private Double buyPrice;
	// 凭证上传时间
	private String voucherUploadTime;
	// 到款审核说明
	private String getMoneyDesc;
	// 案场客户id
	private String projectCustomerId;
	// 是否打款超期
	private String isOut;
	// 支付方式
	private Integer payStyle;
	// 订单性质(0:自购,1:代购)
	private Integer orderProperty;
	// 是否阅读全部条款(0:未读,1:已读)
	private Integer isAlreadyRead;
	// 认购关系0,1,2.....
	private Integer enterBuyRelation;
	// 结算方式
	private Integer accountStyle;
	// 撤单原因(单选框选择..........)
	private String killTheOrderReason;
	// 撤单备注
	private String revokeOrderNotes;
	// 撤单时间
	private String revokeTime;
	// 优惠条款信息
	private String benefitInfo;
	// 认购人和真实认购人关系
	private EnterBuyManAndRealEnterBuyManRelation relationId;
	  //房号
    private String houseNo;
    //楼栋号
    private String buildingNo;
    //单元
    private String unit;
    //楼层
    private Integer floor;
    //建筑面积
    private String buildAreaStr;
    //使用面积
    private String usefulAreaStr;
    //列表价
    private String listPriceStr;
    //照片
    private String photoUrl;
    //单价
    private String danjia;
    
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
	public Integer getIsShopPayConfirm() {
		return isShopPayConfirm;
	}
	public void setIsShopPayConfirm(Integer isShopPayConfirm) {
		this.isShopPayConfirm = isShopPayConfirm;
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
	public String getShopCancelReceiveConfirmDesc() {
		return shopCancelReceiveConfirmDesc;
	}
	public void setShopCancelReceiveConfirmDesc(String shopCancelReceiveConfirmDesc) {
		this.shopCancelReceiveConfirmDesc = shopCancelReceiveConfirmDesc;
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
	public String getRevokeTime() {
		return revokeTime;
	}
	public void setRevokeTime(String revokeTime) {
		this.revokeTime = revokeTime;
	}
	public String getBenefitInfo() {
		return benefitInfo;
	}
	public void setBenefitInfo(String benefitInfo) {
		this.benefitInfo = benefitInfo;
	}
	public EnterBuyManAndRealEnterBuyManRelation getRelationId() {
		return relationId;
	}
	public void setRelationId(EnterBuyManAndRealEnterBuyManRelation relationId) {
		this.relationId = relationId;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public String getBuildAreaStr() {
		return buildAreaStr;
	}
	public void setBuildAreaStr(String buildAreaStr) {
		this.buildAreaStr = buildAreaStr;
	}
	public String getUsefulAreaStr() {
		return usefulAreaStr;
	}
	public void setUsefulAreaStr(String usefulAreaStr) {
		this.usefulAreaStr = usefulAreaStr;
	}
	public String getListPriceStr() {
		return listPriceStr;
	}
	public void setListPriceStr(String listPriceStr) {
		this.listPriceStr = listPriceStr;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getDanjia() {
		return danjia;
	}
	public void setDanjia(String danjia) {
		this.danjia = danjia;
	}
	
    
	@Override
	public String toString() {
		return "AllMyContractRecordDTO [recordNo=" + recordNo + ", userId=" + userId + ", projectId=" + projectId
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
				+ ", benefitInfo=" + benefitInfo + ", relationId=" + relationId + ", houseNo=" + houseNo
				+ ", buildingNo=" + buildingNo + ", unit=" + unit + ", floor=" + floor + ", buildAreaStr="
				+ buildAreaStr + ", usefulAreaStr=" + usefulAreaStr + ", listPriceStr=" + listPriceStr + ", photoUrl="
				+ photoUrl + ", danjia=" + danjia + "]";
	}
	public AllMyContractRecordDTO getAllMyContractRecordDTO(ContractRecords cr, House h, String pic){
		if (cr != null &&!"".equals(cr)){
			this.recordNo = cr.getRecordNo();
			this.userId = cr.getUserId();
			this.projectId = cr.getProjectId();
			this.houseNum = cr.getHouseNum();
			this.customerName = cr.getCustomerName();
			this.customerIDCard = cr.getCustomerIDCard();
			this.customerPhone = cr.getCustomerPhone();
			this.benefitlds = cr.getBenefitlds();
			this.price = cr.getPrice();
			this.recordStatus = cr.getRecordStatus();
			this.applyTime = cr.getApplyTime();
			this.auditingTime = cr.getAuditingTime();
			this.auditionUserId = cr.getAuditionUserId();
			this.auditionReson = cr.getAuditionReson();
			this.remitNo = cr.getRemitNo();
			this.remitConfirmTime = cr.getRemitConfirmTime();
			this.remitConfirmUserId = cr.getRemitConfirmUserId();
			this.contractConfirmTime = cr.getContractConfirmTime();
			this.contractconfirmUseerId = cr.getContractconfirmUseerId();
			this.isShopPayConfirm = cr.getIsShopPayConfirm();
			this.shopPayConfirmTime = cr.getShopPayConfirmTime();
			this.shopPayConfirmUserId = cr.getShopPayConfirmUserId();
			this.shopPayConfirmDesc = cr.getShopPayConfirmDesc();
			this.cancelShopPayConfirmDesc = cr.getCancelShopPayConfirmDesc();
			this.isSystemPayConfirm = cr.getIsSystemPayConfirm();
			this.systemPayConfirmTime = cr.getSystemPayConfirmTime();
			this.systemPayConfirmUserId = cr.getSystemPayConfirmUserId();
			this.systemPayConfirmDesc = cr.getSystemPayConfirmDesc();
			this.cancelSystemPayConfirmDesc = cr.getCancelSystemPayConfirmDesc();
			this.shopReceiveConfirmTime = cr.getShopReceiveConfirmTime();
			this.shopReceiveConfirmUserId = cr.getShopReceiveConfirmUserId();
			this.shopCancelReceiveConfirmDesc = cr.getShopCancelReceiveConfirmDesc();
			this.systemReceiveConfirmTime = cr.getSystemReceiveConfirmTime();
			this.systemReceiveConfirmUserId = cr.getSystemReceiveConfirmUserId();
			this.tags = cr.getTags();
			this.description = cr.getDescription();
			this.credentialsType = cr.getCredentialsType();
			this.credentialsPhoto = cr.getCredentialsPhoto();
			this.buyPrice = cr.getBuyPrice();
			this.voucherUploadTime = cr.getVoucherUploadTime();
			this.getMoneyDesc = cr.getGetMoneyDesc();
			this.projectCustomerId = cr.getProjectCustomerId();
			this.isOut = cr.getIsOut();
			this.payStyle = cr.getPayStyle();
			this.orderProperty = cr.getOrderProperty();
			this.isAlreadyRead = cr.getIsAlreadyRead();
			this.enterBuyRelation = cr.getEnterBuyRelation();
			this.accountStyle = cr.getAccountStyle();
			this.killTheOrderReason = cr.getKillTheOrderReason();
			this.revokeOrderNotes = cr.getRevokeOrderNotes();
			this.revokeTime = cr.getRevokeTime();
			this.benefitInfo = cr.getBenefitInfo();
			//this.relationId = cr.getRelationId();	
		}
		if (h != null && !"".equals(h)){
			this.houseNo = h.getHouseNo();
			this.buildingNo = h.getBuildingNo();
			this.unit = h.getUnit();
			this.floor = h.getFloor();
			this.buildAreaStr = SysContent.get2Double(h.getBuildArea());
			this.usefulAreaStr = SysContent.get2Double(h.getUsefulArea());
			this.listPriceStr = SysContent.get2Double(h.getListPrice());
			this.danjia = SysContent.get2Double(h.getListPrice()/h.getBuildArea());
		}
		if (pic != null && !"".equals(pic)){
			this.photoUrl = pic;
		}
		return this;
	}
	
}
