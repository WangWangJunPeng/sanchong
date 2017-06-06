package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 2017-02-06
 * @author grl
 *
 */
@Entity
@Table(name="t_advertisement")
public class Advertisement {
	//主键自动生成
	private Integer adId;
	//城市
	private String adCity;
	//广告位(本地、精选,其他)
	private String adPosition;
	//导图Url
	private String adUrl;
	//广告标题
	private String adTitle;
	//项目id
	private String projectId;
	//开始时间
	private String startTime;
	//结束时间
	private String endTime;
	//排序(数值越大越靠前)
	private Integer sorting;
	//启用状态(0禁用；1启用)
	private Integer state;
	
	@Id
	@GeneratedValue
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public String getAdCity() {
		return adCity;
	}
	public void setAdCity(String adCity) {
		this.adCity = adCity;
	}
	public String getAdPosition() {
		return adPosition;
	}
	public void setAdPosition(String adPosition) {
		this.adPosition = adPosition;
	}
	public String getAdUrl() {
		return adUrl;
	}
	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}
	public String getAdTitle() {
		return adTitle;
	}
	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getSorting() {
		return sorting;
	}
	public void setSorting(Integer sorting) {
		this.sorting = sorting;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	public String toString() {
		return "adId"+" "+"adCity"+" "+"adPosition"+" "+"+adUrl"+" "+"adTitle"+" "+"projectIdstartTime"+" "+"endTime"+" "+"sorting"+" "+"state";
	}
	
}
