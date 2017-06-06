package com.sc.tradmaster.service.guideRecords.impl.dto;

import com.sc.tradmaster.bean.GuideRecords;

//备案结果页
public class GuideRecordsDTO {
	//项目名字
	private String projectName;
	//案场电话
	private String projectPhone;
	//备案有效期
	private String validity;
	//备案失败原因
	private String fail;
	//备案时间
	private String applyTime;
	//剩余天数
	private Integer days;
	//客户名字
	private String customerName;
	//客户电话
	private String customerPhone;
	//备案状态,0:申请(备案成功);1:确认(到访,到达案场);2:删除;3:否决(备案失败);4：备案逾期
	private Integer applyStatus;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectPhone() {
		return projectPhone;
	}
	public void setProjectPhone(String projectPhone) {
		this.projectPhone = projectPhone;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getFail() {
		return fail;
	}
	public void setFail(String fail) {
		this.fail = fail;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
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
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	
	public Integer getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	@Override
	public String toString() {
		return "GuideRecordsDTO [projectName=" + projectName + ", projectPhone=" + projectPhone + ", validity="
				+ validity + ", fail=" + fail + ", applyTime=" + applyTime + ", days=" + days + ", customerName="
				+ customerName + ", customerPhone=" + customerPhone + ", applyStatus=" + applyStatus + "]";
	}
}
