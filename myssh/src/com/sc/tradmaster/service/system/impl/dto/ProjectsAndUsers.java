package com.sc.tradmaster.service.system.impl.dto;

import java.util.List;

import com.sc.tradmaster.bean.User;

/**
 * 2017-3-16 xaoxy
 * 该类用于封装案场与案场助理，合伙人的信息
 * @author Administrator
 *
 */
public class ProjectsAndUsers {
	
	private String projectId;//案场编号
	private String projectName;//案场名称
	private User assistantProject; //案场助理
	private User partners; //案场所属合伙人
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
	public User getAssistantProject() {
		return assistantProject;
	}
	public void setAssistantProject(User assistantProject) {
		this.assistantProject = assistantProject;
	}
	public User getPartners() {
		return partners;
	}
	public void setPartners(User partners) {
		this.partners = partners;
	}
	
}
