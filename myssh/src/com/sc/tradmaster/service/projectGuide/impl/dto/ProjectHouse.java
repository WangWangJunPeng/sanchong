package com.sc.tradmaster.service.projectGuide.impl.dto;

/**
 * 中介管理员案场搜索
 */	

public class ProjectHouse {
	//案场id
	private String projectId;
	//案场名称
	private String projectName;
	//城市
	private String city;
	//开发商
	private String developer;
	//均价
	private Double averagePrice;
	//是否支持带看
	private Integer isDaiKan;
	//是否支持异地
	private Integer isYiDi;
	//是否支持快速结佣
	private Integer isFast;
	//带看佣金
	private Double daiKanMoney;
	//分销佣金
	private Double fenXiaoMoney;
	//是否有优惠
	private Integer isYouHui;
	//优惠信息
	private String information;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public Double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	public Integer getIsDaiKan() {
		return isDaiKan;
	}
	public void setIsDaiKan(Integer isDaiKan) {
		this.isDaiKan = isDaiKan;
	}
	public Integer getIsYiDi() {
		return isYiDi;
	}
	public void setIsYiDi(Integer isYiDi) {
		this.isYiDi = isYiDi;
	}
	public Integer getIsFast() {
		return isFast;
	}
	public void setIsFast(Integer isFast) {
		this.isFast = isFast;
	}
	public Double getDaiKanMoney() {
		return daiKanMoney;
	}
	public void setDaiKanMoney(Double daiKanMoney) {
		this.daiKanMoney = daiKanMoney;
	}
	public Double getFenXiaoMoney() {
		return fenXiaoMoney;
	}
	public void setFenXiaoMoney(Double fenXiaoMoney) {
		this.fenXiaoMoney = fenXiaoMoney;
	}
	public Integer getIsYouHui() {
		return isYouHui;
	}
	public void setIsYouHui(Integer isYouHui) {
		this.isYouHui = isYouHui;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	@Override
	public String toString() {
		return "ProjectHouse [projectId=" + projectId + ", projectName=" + projectName + ", city=" + city
				+ ", developer=" + developer + ", averagePrice=" + averagePrice + ", isDaiKan=" + isDaiKan + ", isYiDi="
				+ isYiDi + ", isFast=" + isFast + ", daiKanMoney=" + daiKanMoney + ", fenXiaoMoney=" + fenXiaoMoney
				+ ", isYouHui=" + isYouHui + ", information=" + information + "]";
	}
	
}
