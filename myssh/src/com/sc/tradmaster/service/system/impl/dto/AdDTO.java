package com.sc.tradmaster.service.system.impl.dto;


/**
 * 2017-3-22 maoxy
 * 封装广告所有的数据
 * @author Administrator
 *
 */
public class AdDTO {
	
	private Integer adId;
	private String provinceAndCity;
	//省
	private String province;
	//市
	private String city;
	//广告位(本地、精选,其他)
	private String adPosition;
	//导图Url
	private String adUrl;
	//广告标题
	private String adTitle;
	////////////////////////////
	////////项目名称//////////////
	////////////////////////////
	private String projectId;
	private String projectName;
	//开始时间
	private String startTime;
	//结束时间
	private String endTime;
	//排序(数值越大越靠前)
	private Integer sorting;
	//启用状态(0禁用；1启用)
	private Integer state;
	
	
	public String getProvinceAndCity() {
		return provinceAndCity;
	}
	public void setProvinceAndCity(String provinceAndCity) {
		this.provinceAndCity = provinceAndCity;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	
	
	

}
