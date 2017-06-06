package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*
* @author wjp 2017-01-21
*/
@Entity
@Table(name="t_favorites")
public class Favorites {
	//收藏id
	private Integer favoritesId;
	//中介id
	private String userId;
	//项目id
	private String projectId;
	//添加时间
	private String addTime;
	//预留的说明字段
	private String description;
	//预留的标签字段
	private String tags;
	
	@Id
	@GeneratedValue
	public Integer getFavoritesId() {
		return favoritesId;
	}
	public void setFavoritesId(Integer favoritesId) {
		this.favoritesId = favoritesId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
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
		return "Favorites [favoritesId=" + favoritesId + ", userId=" + userId + ", projectId=" + projectId
				+ ", addTime=" + addTime + ", description=" + description + ", tags=" + tags + "]";
	}
	
}
