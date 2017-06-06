package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *经理端分析类目对象
 * @author grl 2017-05-18
 *
 */
@Entity
@Table(name="t_analysisCategory")
public class AnalysisCategory {
	//类目id
	private Integer categoryId;
	//类目名称
	private String categoryName;
	//拥有的标签(数值数组格式)
	private String haveLabel;
	//预留字段
	private String reserveField;
	//项目id
	private String projectId;
	//优先级
	private String priority;
	//是否是定制(0:非定制、1:定制)
	private String isMade;
	
	@Id
	@GeneratedValue
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getHaveLabel() {
		return haveLabel;
	}
	public void setHaveLabel(String haveLabel) {
		this.haveLabel = haveLabel;
	}
	public String getReserveField() {
		return reserveField;
	}
	public void setReserveField(String reserveField) {
		this.reserveField = reserveField;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getIsMade() {
		return isMade;
	}
	public void setIsMade(String isMade) {
		this.isMade = isMade;
	}
	@Override
	public String toString() {
		return "AnalysisCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + ", haveLabel="
				+ haveLabel + ", reserveField=" + reserveField + ", projectId=" + projectId + ", priority=" + priority
				+ ", isMade=" + isMade + "]";
	}
}
