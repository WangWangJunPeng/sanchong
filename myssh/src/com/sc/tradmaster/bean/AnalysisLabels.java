package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 经理端分析详细标签对象
 * @author grl 2017-05-18
 *
 */
@Entity
@Table(name="t_analysisLabels")
public class AnalysisLabels {
	//分析标签id
	private Integer labelId;
	//标签名字
	private String labelName;
	//标签描述
	private String labelDesc;
	//标签关联关系（数组字符串）
	private String labelRelation;
	//图标类型
	private String tableType;
	//后台方法名
	private String systemFunctionName;
	//是否是定制(0:非定制、1:定制)
	private String isMade;
	
	@Id
	@GeneratedValue
	public Integer getLabelId() {
		return labelId;
	}
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getLabelDesc() {
		return labelDesc;
	}
	public void setLabelDesc(String labelDesc) {
		this.labelDesc = labelDesc;
	}
	public String getLabelRelation() {
		return labelRelation;
	}
	public void setLabelRelation(String labelRelation) {
		this.labelRelation = labelRelation;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public String getSystemFunctionName() {
		return systemFunctionName;
	}
	public void setSystemFunctionName(String systemFunctionName) {
		this.systemFunctionName = systemFunctionName;
	}
	public String getIsMade() {
		return isMade;
	}
	public void setIsMade(String isMade) {
		this.isMade = isMade;
	}
}
