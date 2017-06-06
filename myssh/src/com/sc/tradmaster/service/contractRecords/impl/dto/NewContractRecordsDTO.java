package com.sc.tradmaster.service.contractRecords.impl.dto;

import java.util.List;

import com.sc.tradmaster.bean.RealEnterBuyMan;

public class NewContractRecordsDTO {
	
	//订单性质(0:自购,1:代购)
	private Integer orderProperty;
	//案场编号
    private String projectId;
    //房源编号,唯一
    private String houseNum;
    //房号
    private String houseNo;
    //楼栋号
    private String buildingNo;
    //房号整体信息
    private String houseInfo;
    //单元
    private String unit;
    //楼层
    private Integer floor;
    //预售证信息
    private String presalePermissionInfo;
    //房型
  	private String housType;
  	 //列表价
    private String listPriceStr;
    //单价
    private String danjia;
    //优惠条款信息
  	private String benefitInfo;
  	//购买价格
  	private String buyPrice;
  	//定金数额
  	private String dposit;
  	//支付方式
  	private Integer payStyle;
  	//结算方式
  	private Integer accountStyle;
  	//是否阅读全部条款(0:未读,1:已读)
  	private Integer isAlreadyRead;
  	//认购客户id
  	private String customerId;
  	//认购客户姓名
  	private String rName;
  	//认购客户电话
	private String rPhone;
	//认购客户身份证
	private String rengourenIdCard;
	//真实认购人信息
	private List<RealEnterBuyMan> rebList;
	//字符串形式显示真实认购人信息
	private String realEnterBuyManInfo;
	//真实购买人id字符串集合
	private String realCustomerId;
	//优惠条款id集合
	private String allBenefitsId;
	
	public Integer getOrderProperty() {
		return orderProperty;
	}
	public void setOrderProperty(Integer orderProperty) {
		this.orderProperty = orderProperty;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
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
	public String getHouseInfo() {
		return houseInfo;
	}
	public void setHouseInfo(String houseInfo) {
		this.houseInfo = houseInfo;
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
	public String getPresalePermissionInfo() {
		return presalePermissionInfo;
	}
	public void setPresalePermissionInfo(String presalePermissionInfo) {
		this.presalePermissionInfo = presalePermissionInfo;
	}
	public String getHousType() {
		return housType;
	}
	public void setHousType(String housType) {
		this.housType = housType;
	}
	public String getListPriceStr() {
		return listPriceStr;
	}
	public void setListPriceStr(String listPriceStr) {
		this.listPriceStr = listPriceStr;
	}
	public String getDanjia() {
		return danjia;
	}
	public void setDanjia(String danjia) {
		this.danjia = danjia;
	}
	public String getBenefitInfo() {
		return benefitInfo;
	}
	public void setBenefitInfo(String benefitInfo) {
		this.benefitInfo = benefitInfo;
	}
	public String getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getDposit() {
		return dposit;
	}
	public void setDposit(String dposit) {
		this.dposit = dposit;
	}
	public Integer getPayStyle() {
		return payStyle;
	}
	public void setPayStyle(Integer payStyle) {
		this.payStyle = payStyle;
	}
	public Integer getAccountStyle() {
		return accountStyle;
	}
	public void setAccountStyle(Integer accountStyle) {
		this.accountStyle = accountStyle;
	}
	public Integer getIsAlreadyRead() {
		return isAlreadyRead;
	}
	public void setIsAlreadyRead(Integer isAlreadyRead) {
		this.isAlreadyRead = isAlreadyRead;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getrPhone() {
		return rPhone;
	}
	public void setrPhone(String rPhone) {
		this.rPhone = rPhone;
	}
	
	public String getRengourenIdCard() {
		return rengourenIdCard;
	}
	public void setRengourenIdCard(String rengourenIdCard) {
		this.rengourenIdCard = rengourenIdCard;
	}
	public List<RealEnterBuyMan> getRebList() {
		return rebList;
	}
	public void setRebList(List<RealEnterBuyMan> rebList) {
		this.rebList = rebList;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getRealEnterBuyManInfo() {
		return realEnterBuyManInfo;
	}
	public void setRealEnterBuyManInfo(String realEnterBuyManInfo) {
		this.realEnterBuyManInfo = realEnterBuyManInfo;
	}
	
	
	public String getRealCustomerId() {
		return realCustomerId;
	}
	public void setRealCustomerId(String realCustomerId) {
		this.realCustomerId = realCustomerId;
	}
	
	public String getAllBenefitsId() {
		return allBenefitsId;
	}
	public void setAllBenefitsId(String allBenefitsId) {
		this.allBenefitsId = allBenefitsId;
	}
	@Override
	public String toString() {
		return "NewContractRecordsDTO [orderProperty=" + orderProperty + ", projectId=" + projectId + ", houseNum="
				+ houseNum + ", houseNo=" + houseNo + ", buildingNo=" + buildingNo + ", houseInfo=" + houseInfo
				+ ", unit=" + unit + ", floor=" + floor + ", presalePermissionInfo=" + presalePermissionInfo
				+ ", housType=" + housType + ", listPriceStr=" + listPriceStr + ", danjia=" + danjia + ", benefitInfo="
				+ benefitInfo + ", buyPrice=" + buyPrice + ", dposit=" + dposit + ", payStyle=" + payStyle
				+ ", accountStyle=" + accountStyle + ", isAlreadyRead=" + isAlreadyRead + ", customerId=" + customerId
				+ ", rName=" + rName + ", rPhone=" + rPhone + ", rengourenIdCard=" + rengourenIdCard + ", rebList="
				+ rebList + ", realEnterBuyManInfo=" + realEnterBuyManInfo + ", realCustomerId=" + realCustomerId
				+ ", allBenefitsId=" + allBenefitsId + "]";
	}
	
}


