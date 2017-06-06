package com.sc.tradmaster.service.house.impl.dto;

import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.utils.SysContent;

public class HouseListDTO {
	//房源编号
	private String houseId;
	//案场编号
	private String projectId;
	//案场名字
	private String projectName;
	//房号
	private String houseNo;
	//区位号
	private String district;
	//楼栋号
	private String buildingNo;
	//(房源类型，0:公寓、1:排屋、2:独栋、3:商住两用、4:办公室、5:酒店式公寓、6:商铺、7:车位、8:车库、9:储藏室)
	private Integer houseKind;
	//单元
	private String unit;
	//楼层
	private Integer floor;
	//朝向
	private String direct;
	//建筑面积
	private Double buildArea;
	//使用面积
	private Double usefulArea;
	//列表价
	private String listPrice;
	//底价
	private String minimumPrice;
	//中介供货价
	private String shopPrice;
	//户型
	private String caption;
	//房型
	private String houseStyle;
	//(房源状态，0：初始；1：上架；2：删除；3：销控；4：订购；5：签约)
	private Integer houseStatus;
	//装修标准(0：毛坯；1：普通装修；2：精装修；3：家具全配；4：家电全配)
	private Integer decorationStandard;
	//备用的说明性字段
	private String description;
	//标签字段
	private String tags;
	//效果图URL
	private String photos;
	//发布时间
	private String shelvTime;
	private double rewardMoney;	//数据库表里没有这个字段,,,,
	//(经纪人是否可见0:不可见;1:可见)
	private Integer isOpen;
	//houseNum
	private Integer houseNum;
	//佣金
	private Double brokerage;
	//是否支持认购0,不支持,1;支持
	private String isSupport;
	//发起成交数量
	private Integer dealNum;
	//是否可以发起认购(0:可以,1:不可以)
	private Integer isToBuyHouse;
	//房源列表第一张效果图显示
	private String resultPhotoUrl;
	//单价
	private String danjiaStr;
	
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
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
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public Integer getHouseKind() {
		return houseKind;
	}
	public void setHouseKind(Integer houseKind) {
		this.houseKind = houseKind;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public String getDirect() {
		return direct;
	}
	public void setDirect(String direct) {
		this.direct = direct;
	}
	public Double getBuildArea() {
		return buildArea;
	}
	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}
	public Double getUsefulArea() {
		return usefulArea;
	}
	public void setUsefulArea(Double usefulArea) {
		this.usefulArea = usefulArea;
	}
	public String getListPrice() {
		return listPrice;
	}
	public void setListPrice(String listPrice) {
		this.listPrice = listPrice;
	}
	public String getMinimumPrice() {
		return minimumPrice;
	}
	public void setMinimumPrice(String minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	public String getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(String shopPrice) {
		this.shopPrice = shopPrice;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getHouseStyle() {
		return houseStyle;
	}
	public void setHouseStyle(String houseStyle) {
		this.houseStyle = houseStyle;
	}
	public Integer getHouseStatus() {
		return houseStatus;
	}
	public void setHouseStatus(Integer houseStatus) {
		this.houseStatus = houseStatus;
	}
	public Integer getDecorationStandard() {
		return decorationStandard;
	}
	public void setDecorationStandard(Integer decorationStandard) {
		this.decorationStandard = decorationStandard;
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
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	public String getShelvTime() {
		return shelvTime;
	}
	public void setShelvTime(String shelvTime) {
		this.shelvTime = shelvTime;
	}
	public double getRewardMoney() {
		return rewardMoney;
	}
	public void setRewardMoney(double rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	public Integer getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	public Integer getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}
	public Double getBrokerage() {
		return brokerage;
	}
	public void setBrokerage(Double brokerage) {
		this.brokerage = brokerage;
	}
	public String getIsSupport() {
		return isSupport;
	}
	public void setIsSupport(String isSupport) {
		this.isSupport = isSupport;
	}
	public Integer getDealNum() {
		return dealNum;
	}
	public void setDealNum(Integer dealNum) {
		this.dealNum = dealNum;
	}
	public Integer getIsToBuyHouse() {
		return isToBuyHouse;
	}
	public void setIsToBuyHouse(Integer isToBuyHouse) {
		this.isToBuyHouse = isToBuyHouse;
	}
	
	public String getResultPhotoUrl() {
		return resultPhotoUrl;
	}
	public void setResultPhotoUrl(String resultPhotoUrl) {
		this.resultPhotoUrl = resultPhotoUrl;
	}
	
	public String getDanjiaStr() {
		return danjiaStr;
	}
	public void setDanjiaStr(String danjiaStr) {
		this.danjiaStr = danjiaStr;
	}
	/*
	 *装配DTO 
	 */
	public HouseListDTO getHouseListDTO(House h, Project pro,HouseType ht,String pic){
		if(ht!=null){
			this.houseStyle = ht.getHousType();
			this.caption = ht.getCaption();
		}
		if (pro != null && !"".equals(pro)){
			this.projectId = pro.getProjectId();
			this.projectName = pro.getProjectName();
		}
		this.houseId = h.getHouseId();
		this.houseNo = h.getHouseNo();
		this.district = h.getDistrict();
		this.buildingNo = h.getBuildingNo();
		this.houseKind = h.getHouseKind();
		this.unit = h.getUnit();
		this.floor = h.getFloor();
		this.direct = h.getDirect();
		this.buildArea = h.getBuildArea();
		this.usefulArea = h.getUsefulArea();
		this.listPrice = SysContent.get2Double(h.getListPrice());
		this.minimumPrice = SysContent.get2Double(h.getMinimumPrice());
		this.shopPrice = SysContent.get2Double(h.getShopPrice());
		this.danjiaStr = SysContent.get2Double(h.getListPrice()/h.getBuildArea());
		this.houseStatus = h.getHouseStatus();
		this.decorationStandard = h.getDecorationStandard();
		this.description = h.getDescription();
		this.tags = h.getTags();
		this.photos = h.getPhotos();
		this.shelvTime = h.getShelvTime();
		this.rewardMoney = h.getRewardMoney();
		this.isOpen = h.getIsOpen();
		this.houseNum = h.getHouseNum();
		//this.brokerage = h.getShopPrice()*pg.getDaiKanMoney()/100;
		if (pic != null && !"".equals(pic)){
			this.resultPhotoUrl = pic;
		}
		return this;
	}
}
