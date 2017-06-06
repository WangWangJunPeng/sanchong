package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wjp 2017-03-01
 */
@Entity
@Table(name = "t_feedback")
public class Feedback {
	//记录编码
	private Integer feedbackId;
	//用户编码
	private String userId;
	//照片url
	private String photo;
	//问题反馈
	private String problem;
	//用户邮箱
	private String email;
	//预留的说明字段
	private String description;
	//预留的标签字段
	private String tags;
	//星级
	private Integer stars;
	//添加时间
	private String addTime;
	
	@Id
    @GeneratedValue
	public Integer getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Integer getStars() {
		return stars;
	}
	public void setStars(Integer stars) {
		this.stars = stars;
	}
	
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", userId=" + userId + ", photo=" + photo + ", problem=" + problem
				+ ", email=" + email + ", description=" + description + ", tags=" + tags + ", stars=" + stars
				+ ", addTime=" + addTime + "]";
	}
}
