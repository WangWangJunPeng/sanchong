package com.sc.tradmaster.service.contractRecords.impl.dto;

public class ContractRecordsDTO {
	//认购表NO 唯一性
	private Integer recordNo;
	//项目id
	private String projectId;
	//项目名字
	private String projectName;
	//houseNum
	private Integer houseNum;
	//楼栋号
	private String buildingNo;
	//单元号
	private String unit;
	//房号
	private String houseNo;
	//客户电话
	private String customerPhone;
	//客户姓名
	private String customerName;
	//中介编码
	private String userId;
	//剩余时间
	private String residueHours;
	//projectCustomerId
	private String projectCustomerId;
	
	
	public Integer getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}
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
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getResidueHours() {
		return residueHours;
	}
	public void setResidueHours(String residueHours) {
		this.residueHours = residueHours;
	}
	public Integer getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
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
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getProjectCustomerId() {
		return projectCustomerId;
	}
	public void setProjectCustomerId(String projectCustomerId) {
		this.projectCustomerId = projectCustomerId;
	}
	@Override
	public String toString() {
		return "ContractRecordsDTO [recordNo=" + recordNo + ", projectId=" + projectId + ", projectName=" + projectName
				+ ", houseNum=" + houseNum + ", buildingNo=" + buildingNo + ", unit=" + unit + ", houseNo=" + houseNo
				+ ", customerPhone=" + customerPhone + ", customerName=" + customerName + ", userId=" + userId
				+ ", residueHours=" + residueHours + ", projectCustomerId=" + projectCustomerId + "]";
	}
}
