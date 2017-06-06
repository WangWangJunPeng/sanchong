package com.sc.tradmaster.service.projectBenefits.impl.dto;

public class ProjectBenefitsDTO {

	//优惠编码
	private String benefitId;
	//案场编号
	private String projectId;
	//优惠名称
	private String caption;
	//起始时间
	private String startTime;
	//结束时间
	private String endTime;
	//缴纳的金额
	private Double fee;
	//优惠金额
	private Double benefitMoney;
	//优惠比率
	private Double benefitPercent;
	//money:0,pencent:1
	private Integer type;
	//优惠名称
	private String benefitsName;
	//状态(未开始, 进行中, 已结束)
	private String benefitsStatus;
	
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
	public String getBenefitsStatus() {
		return benefitsStatus;
	}
	public void setBenefitsStatus(String benefitsStatus) {
		this.benefitsStatus = benefitsStatus;
	}
	@Override
	public String toString() {
		return "ProjectBenefitsDTO [benefitId=" + benefitId + ", projectId=" + projectId + ", caption=" + caption
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", fee=" + fee + ", benefitMoney="
				+ benefitMoney + ", benefitPercent=" + benefitPercent + ", type=" + type + ", benefitsName="
				+ benefitsName + ", benefitsStatus=" + benefitsStatus + "]";
	}
}
