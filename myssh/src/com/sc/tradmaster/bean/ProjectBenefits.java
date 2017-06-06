package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
*
* @author wjp 2017-01-11
*/
@Entity
@Table(name="t_projectbenefits")
public class ProjectBenefits {
	//优惠编码
	private String benefitId;
	//案场编号
	private String projectId;
	//优惠说明
	private String caption;
	//起始时间
	private String startTime;
	//结束时间
	private String endTime;
	//缴纳的金额
	private Double fee;
	//保留两位小数,缴纳的金额
	private String feeStr;
	//优惠金额
	private Double benefitMoney;
	//优惠金额 保留两位小数
	private String benefitMoneyStr;
	//优惠比率
	private Double benefitPercent;
	//优惠比率 保留两位小数
	private String benefitPercentStr;
	//money:0,pencent:1
	private Integer type;
	//优惠名称
	private String benefitsName;
	//是否可以多选(0:可以,1:不可以)
	private Integer isChooseMore;
	
	@Id
	public String getBenefitId() {
		return benefitId;
	}

	public void setBenefitId(String benefitId) {
		this.benefitId = benefitId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}
	@Transient
	public String getFeeStr() {
		return feeStr;
	}

	public void setFeeStr(String feeStr) {
		this.feeStr = feeStr;
	}

	public Double getBenefitMoney() {
		return benefitMoney;
	}

	public void setBenefitMoney(Double benefitMoney) {
		this.benefitMoney = benefitMoney;
	}

	public Double getBenefitPercent() {
		return benefitPercent;
	}

	public void setBenefitPercent(Double benefitPercent) {
		this.benefitPercent = benefitPercent;
	}
	
	public String getBenefitMoneyStr() {
		return benefitMoneyStr;
	}

	public void setBenefitMoneyStr(String benefitMoneyStr) {
		this.benefitMoneyStr = benefitMoneyStr;
	}

	public String getBenefitPercentStr() {
		return benefitPercentStr;
	}

	public void setBenefitPercentStr(String benefitPercentStr) {
		this.benefitPercentStr = benefitPercentStr;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	 
	public String getBenefitsName() {
		return benefitsName;
	}

	public void setBenefitsName(String benefitsName) {
		this.benefitsName = benefitsName;
	}

	public Integer getIsChooseMore() {
		return isChooseMore;
	}

	public void setIsChooseMore(Integer isChooseMore) {
		this.isChooseMore = isChooseMore;
	}

	@Override
	public String toString() {
		return "ProjectBenefits [benefitId=" + benefitId + ", projectId=" + projectId + ", caption=" + caption
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", fee=" + fee + ", feeStr=" + feeStr
				+ ", benefitMoney=" + benefitMoney + ", benefitMoneyStr=" + benefitMoneyStr + ", benefitPercent="
				+ benefitPercent + ", benefitPercentStr=" + benefitPercentStr + ", type=" + type + ", benefitsName="
				+ benefitsName + ", isChooseMore=" + isChooseMore + "]";
	}
}
