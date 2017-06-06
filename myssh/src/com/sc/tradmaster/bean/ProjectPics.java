package com.sc.tradmaster.bean;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_projectphotos")
public class ProjectPics {
	private String photoId;
	private String projectId;
	private String url;
	private String caption;
	private String description;
	
	@Id
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
