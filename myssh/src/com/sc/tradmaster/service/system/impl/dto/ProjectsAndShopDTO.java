package com.sc.tradmaster.service.system.impl.dto;

public class ProjectsAndShopDTO {

	//项目id
	private String projectId;
	//项目名称
	private String projectName;
	//佣金来源
	private String houseInfo;
	//房号
	private String houseNo;
	//房子的唯一编码
    private Integer houseNum;
	//楼栋号
	private String buildingNo;
	//单元号
	private String unit;
	//分销还是带看   0:分销  1：带看
	private Integer MoneyType;
	//带看佣金
	private Double daiKanMoney;
	//分销佣金
	private Double fenXiaoMoney;
	//合计分销金额
	private Double totalMoney;
	//带看申请备案时间
	private String applyTime;
	//经纪人id
	private String agentUserId;
	//门店编号
	private Integer shopId;
	//门店名称
	private String shopName;
	//认购状态 0:申请,1:同意,2:删除,3:否决,4:到款,5:签约,6:退款
	private Integer recordStatus;
	//签约确认时间
	private String contractConfirmTime;
	//是否平台结账(0：未结款；1已结款；2已到款)
	private Integer isSystemPayConfirm;
	//平台结账确认时间
	private String systemPayConfirmTime;
	//平台到款确认时间
	private String systemReceiveConfirmTime;
	
	
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
	public Integer getMoneyType() {
		return MoneyType;
	}
	public void setMoneyType(Integer moneyType) {
		MoneyType = moneyType;
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
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getAgentUserId() {
		return agentUserId;
	}
	public void setAgentUserId(String agentUserId) {
		this.agentUserId = agentUserId;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getContractConfirmTime() {
		return contractConfirmTime;
	}
	public void setContractConfirmTime(String contractConfirmTime) {
		this.contractConfirmTime = contractConfirmTime;
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
	public String getSystemReceiveConfirmTime() {
		return systemReceiveConfirmTime;
	}
	public void setSystemReceiveConfirmTime(String systemReceiveConfirmTime) {
		this.systemReceiveConfirmTime = systemReceiveConfirmTime;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	public Integer getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}
	public String getHouseInfo() {
		return this.houseInfo = this.buildingNo + "栋" + this.unit + "单元" + this.houseNo + "室";
	}
	public void setHouseInfo(String houseInfo) {
		this.houseInfo = this.buildingNo + "栋" + this.unit + "单元" + this.houseNo + "室";
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	@Override
	public String toString() {
		return "ProjectsAndShopDTO [projectId=" + projectId + ", projectName=" + projectName + ", houseInfo="
				+ houseInfo + ", houseNo=" + houseNo + ", houseNum=" + houseNum
				+ ", buildingNo=" + buildingNo + ", unit=" + unit + ", MoneyType=" + MoneyType + ", daiKanMoney="
				+ daiKanMoney + ", fenXiaoMoney=" + fenXiaoMoney + ", totalMoney=" + totalMoney + ", applyTime="
				+ applyTime + ", agentUserId=" + agentUserId + ", shopId=" + shopId + ", shopName=" + shopName
				+ ", recordStatus=" + recordStatus + ", contractConfirmTime=" + contractConfirmTime
				+ ", isSystemPayConfirm=" + isSystemPayConfirm + ", systemPayConfirmTime=" + systemPayConfirmTime
				+ ", systemReceiveConfirmTime=" + systemReceiveConfirmTime + "]";
	}
	
}
