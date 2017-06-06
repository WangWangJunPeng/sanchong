package com.sc.tradmaster.service.contractRecords.impl.dto;

import java.util.List;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.utils.SysContent;

public class OrderDetailsDTO {
	//认购编号
	private Integer recordNo;
	//认购状态
	private Integer recordStatus;
	//房源描述
	private String houseDesc;
	//订单属性
	private Integer orderProperty;
	//认购人姓名
	private String enterBuyMan;
	//身份证号
	private String idCardNum;
	//真实认购人
	private List realEnterBuyMan;
	//房源id
	private String houseNum;
	//房号
	private String houseNo;
	//预售证号
	private String presalePermissionNum;
	//凭证上传时间
	private String voucherUploadTime;
	//房型名称
	private String homeTypeName;
	//单价
	private String price1;
	//总价
	private String totalPrice;
	//优惠条款
	private String preferentialList;
	//购买价格
	private String buyPrice;
	//结算方式
	private String accountStyle;
	//定金
	private String haveToMoney;
	//支付方式
	private Integer payStyle;
	//拒绝原因
	private String refuseReson;
	
	public OrderDetailsDTO createThisDTO(ContractRecords cr, House h, HouseType ht, Project p) {
		this.recordNo = cr.getRecordNo();
		this.recordStatus = cr.getRecordStatus();
		this.houseDesc = p.getProjectName()+h.getBuildingNo()+"栋"+h.getUnit()+"单元"+h.getHouseNo()+"号";
		this.orderProperty = cr.getOrderProperty();
		this.houseNum = h.getHouseNum().toString();
		this.houseNo = h.getHouseNo();
		this.presalePermissionNum = h.getPresalePermissionInfo();
		this.homeTypeName = ht.getHousType();
		this.price1 = SysContent.get2Double(h.getListPrice()/h.getUsefulArea());
		this.totalPrice = SysContent.get2Double(h.getListPrice());
		this.preferentialList = cr.getBenefitInfo();
		this.buyPrice = SysContent.get2Double(cr.getBuyPrice());
		if (cr.getAccountStyle() != null && !"".equals(cr.getAccountStyle())){
			if(cr.getAccountStyle().equals(5)){
				this.accountStyle = "一次性";
			}else if(cr.getAccountStyle().equals(1)){
				this.accountStyle = "公积金";
			}else if(cr.getAccountStyle().equals(2)){
				this.accountStyle = "商贷按揭";
			}else if(cr.getAccountStyle().equals(3)){
				this.accountStyle = "商贷按揭+公积金";
			}else if (cr.getAccountStyle().equals(4)){
				this.accountStyle = "其他";
			}
		}else {
			this.accountStyle = "未选择结算方式";
		}
		this.haveToMoney = cr.getHaveToPayMoney();
		//if (cr.getPayStyle() != null && !"".equals(cr.getPayStyle())){
			this.payStyle = cr.getPayStyle();
		//}
		this.refuseReson = cr.getAuditionReson();
		this.voucherUploadTime = cr.getVoucherUploadTime();
		return this;
	}
	
	public Integer getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}
	public String getHouseDesc() {
		return houseDesc;
	}
	public void setHouseDesc(String houseDesc) {
		this.houseDesc = houseDesc;
	}
	public Integer getOrderProperty() {
		return orderProperty;
	}
	public void setOrderProperty(Integer orderProperty) {
		this.orderProperty = orderProperty;
	}
	public String getEnterBuyMan() {
		return enterBuyMan;
	}
	public void setEnterBuyMan(String enterBuyMan) {
		this.enterBuyMan = enterBuyMan;
	}
	public String getIdCardNum() {
		return idCardNum;
	}
	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}
	public List getRealEnterBuyMan() {
		return realEnterBuyMan;
	}
	public void setRealEnterBuyMan(List realEnterBuyMan) {
		this.realEnterBuyMan = realEnterBuyMan;
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
	public String getPresalePermissionNum() {
		return presalePermissionNum;
	}
	public void setPresalePermissionNum(String presalePermissionNum) {
		this.presalePermissionNum = presalePermissionNum;
	}
	public String getHomeTypeName() {
		return homeTypeName;
	}
	public void setHomeTypeName(String homeTypeName) {
		this.homeTypeName = homeTypeName;
	}
	public String getPrice1() {
		return price1;
	}
	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	public String getPreferentialList() {
		return preferentialList;
	}
	public void setPreferentialList(String preferentialList) {
		this.preferentialList = preferentialList;
	}
	public String getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getAccountStyle() {
		return accountStyle;
	}
	public void setAccountStyle(String accountStyle) {
		this.accountStyle = accountStyle;
	}
	public String getHaveToMoney() {
		return haveToMoney;
	}
	public void setHaveToMoney(String haveToMoney) {
		this.haveToMoney = haveToMoney;
	}
	public Integer getPayStyle() {
		return payStyle;
	}
	public void setPayStyle(Integer payStyle) {
		this.payStyle = payStyle;
	}
	public Integer getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getRefuseReson() {
		return refuseReson;
	}
	public void setRefuseReson(String refuseReson) {
		this.refuseReson = refuseReson;
	}

	public String getVoucherUploadTime() {
		return voucherUploadTime;
	}

	public void setVoucherUploadTime(String voucherUploadTime) {
		this.voucherUploadTime = voucherUploadTime;
	}
	
}
