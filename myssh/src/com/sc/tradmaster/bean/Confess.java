package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 认筹表
 * @author cdh
 *
 */
@Entity
@Table(name="t_confess")
public class Confess {

	//认筹id
	private Integer confessId;
	//项目id
	private String projectId;
	//认筹客户id
	private String proCustomerId;
	//认筹时间
	private String confessTime;
	/*//认筹状态(0认筹申请；1取消认筹；2同意认筹；3不同意认筹；4签约；5确认签约；)
	private String confState;*/
	//认筹金
	private String confessMoney;
	//录入人id
	private String proUserId;
	//详细信息(预留字段)
	private String description;
	//预留字段
	private String tags;
	
	@Id
	@GeneratedValue
	public Integer getConfessId() {
		return confessId;
	}
	public void setConfessId(Integer confessId) {
		this.confessId = confessId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProCustomerId() {
		return proCustomerId;
	}
	public void setProCustomerId(String proCustomerId) {
		this.proCustomerId = proCustomerId;
	}
	public String getConfessTime() {
		return confessTime;
	}
	public void setConfessTime(String confessTime) {
		this.confessTime = confessTime;
	}
	public String getConfessMoney() {
		return confessMoney;
	}
	public void setConfessMoney(String confessMoney) {
		this.confessMoney = confessMoney;
	}
	public String getProUserId() {
		return proUserId;
	}
	public void setProUserId(String proUserId) {
		this.proUserId = proUserId;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "Confess [confessId=" + confessId + ", projectId=" + projectId + ", proCustomerId=" + proCustomerId
				+ ", confessTime=" + confessTime + ", confessMoney=" + confessMoney + ", proUserId=" + proUserId
				+ ", description=" + description + ", tags=" + tags + "]";
	}
	
}
