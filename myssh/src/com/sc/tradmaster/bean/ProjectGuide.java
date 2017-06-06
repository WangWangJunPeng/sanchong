package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
* 带看
* @author wjp 2017-01-11
*/
@Entity
@Table(name="t_projectguide")
public class ProjectGuide {
	//案场id
	private String projectId;
	//是否上货架
	private Integer isAvailable;
	//有效天数
	private Integer validDays;
	//0:先付,1:后付固定金额,2:后付百分比
	private Integer payType;
	//成交后奖励金额
	private Double rewardMoney;
	//成交后奖励成交金额的百分比
	private Double rewardpercent;
	//说明
	private String description;
	//是否支持带看,0:不支持,1:支持
	private Integer isDaiKan;
	//是否支持异地,0:不支持,1:支持
	private Integer isYiDi;
	//异地销售佣金
	private Double yiDiSalesCommission;
	private String yiDiSalesCommissionStr;
	//异地有效天数
	private String yiDiValidity;
	//是否支持快速结佣,0:不支持,1:支持
	private Integer isFast;
	//带看佣金
	private Double daiKanMoney;
	private String daiKanMoneyStr;
	//分销佣金
	private Double fenXiaoMoney;
	private String fenXiaoMoneyStr;
	//客户保护期
	private String custormerProtectDays;
	
	@Id
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Integer getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}
	public Integer getValidDays() {
		return validDays;
	}
	public void setValidDays(Integer validDays) {
		this.validDays = validDays;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Double getRewardMoney() {
		return rewardMoney;
	}
	public void setRewardMoney(Double rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	public Double getRewardpercent() {
		return rewardpercent;
	}
	public void setRewardpercent(Double rewardpercent) {
		this.rewardpercent = rewardpercent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIsDaiKan() {
		return isDaiKan;
	}
	public void setIsDaiKan(Integer isDaiKan) {
		this.isDaiKan = isDaiKan;
	}
	public Integer getIsYiDi() {
		return isYiDi;
	}
	public void setIsYiDi(Integer isYiDi) {
		this.isYiDi = isYiDi;
	}
	public Integer getIsFast() {
		return isFast;
	}
	public void setIsFast(Integer isFast) {
		this.isFast = isFast;
	}
	public Double getDaiKanMoney() {
		return daiKanMoney;
	}
	public void setDaiKanMoney(Double daiKanMoney) {
		this.daiKanMoney = daiKanMoney;
	}
	public Double getFenXiaoMoney() {
		return fenXiaoMoney;
	}
	public void setFenXiaoMoney(Double fenXiaoMoney) {
		this.fenXiaoMoney = fenXiaoMoney;
	}
	public Double getYiDiSalesCommission() {
		return yiDiSalesCommission;
	}
	public void setYiDiSalesCommission(Double yiDiSalesCommission) {
		this.yiDiSalesCommission = yiDiSalesCommission;
	}
	public String getYiDiValidity() {
		return yiDiValidity;
	}
	public void setYiDiValidity(String yiDiValidity) {
		this.yiDiValidity = yiDiValidity;
	}
	public String getCustormerProtectDays() {
		return custormerProtectDays;
	}
	public void setCustormerProtectDays(String custormerProtectDays) {
		this.custormerProtectDays = custormerProtectDays;
	}
	@Transient
	public String getYiDiSalesCommissionStr() {
		return yiDiSalesCommissionStr;
	}
	public void setYiDiSalesCommissionStr(String yiDiSalesCommissionStr) {
		this.yiDiSalesCommissionStr = yiDiSalesCommissionStr;
	}
	@Transient
	public String getDaiKanMoneyStr() {
		return daiKanMoneyStr;
	}
	public void setDaiKanMoneyStr(String daiKanMoneyStr) {
		this.daiKanMoneyStr = daiKanMoneyStr;
	}
	@Transient
	public String getFenXiaoMoneyStr() {
		return fenXiaoMoneyStr;
	}
	public void setFenXiaoMoneyStr(String fenXiaoMoneyStr) {
		this.fenXiaoMoneyStr = fenXiaoMoneyStr;
	}
	@Override
	public String toString() {
		return "{projectId:" + projectId + ", isAvailable:" + isAvailable + ", validDays:" + validDays
				+ ", payType:" + payType + ", rewardMoney:" + rewardMoney + ", rewardpercent:" + rewardpercent
				+ ", description:" + description + ", isDaiKan:" + isDaiKan + ", isYiDi:" + isYiDi
				+ ", yiDiSalesCommission:" + yiDiSalesCommission + ", yiDiValidity:" + yiDiValidity + ", isFast:"
				+ isFast + ", daiKanMoney:" + daiKanMoney + ", fenXiaoMoney:" + fenXiaoMoney + ", custormerProtectDays:"
				+ custormerProtectDays + "}";
	}
}
