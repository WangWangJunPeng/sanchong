package com.sc.tradmaster.service.house.impl.dto;

import com.sc.tradmaster.bean.House;

public class HouseExportDTO {
	// 房源编号
	private String houseId;
	// 案场编号
	private String projectId;
	// 房号
	private String houseNo;
	// 区位号
	private String district;
	// 楼栋号
	private String buildingNo;
	// (房源类型，0:公寓、1:排屋、2:独栋、3:商住两用、4:办公室、5:酒店式公寓、6:商铺、7:车位、8:车库、9:储藏室)
	private Integer houseKind;
	private String houseKindName;
	// 单元
	private String unit;
	// 楼层
	private Integer floor;
	// 朝向
	private String direct;
	// 建筑面积
	private Double buildArea;
	// 使用面积
	private Double usefulArea;
	// 列表价
	private Double listPrice;
	// 底价
	private Double minimumPrice;
	// 中介供货价
	private Double shopPrice;
	// 户型编码
	private String houseTypeId;
	// 户型名称
	private String houseTypeName;
	// (房源状态，0：初始；1：上架；2：删除；3：销控；4：订购；5：签约)
	private Integer houseStatus;
	private String houseStatusName;
	// 装修标准(0：毛坯；1：普通装修；2：精装修；3：家具全配；4：家电全配)
	private Integer decorationStandard;
	private String decorationStandardName;
	// 发布时间
	private String shelvTime;
	// (经纪人是否可见0:不可见;1:可见)
	private Integer isOpen;
	private String isOpenName;
	// 房子的唯一编码
	private Integer houseNum;
	// 预售证信息
	private String presalePermissionInfo;
	
	
	public String getIsOpenName() {
		return isOpenName;
	}
	public void setIsOpenName(String isOpenName) {
		this.isOpenName = isOpenName;
	}
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
	public String getHouseKindName() {
		return houseKindName;
	}
	public void setHouseKindName(String houseKindName) {
		this.houseKindName = houseKindName;
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
	public Double getListPrice() {
		return listPrice;
	}
	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}
	public Double getMinimumPrice() {
		return minimumPrice;
	}
	public void setMinimumPrice(Double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	public Double getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}
	public String getHouseTypeId() {
		return houseTypeId;
	}
	public void setHouseTypeId(String houseTypeId) {
		this.houseTypeId = houseTypeId;
	}
	public String getHouseTypeName() {
		return houseTypeName;
	}
	public void setHouseTypeName(String houseTypeName) {
		this.houseTypeName = houseTypeName;
	}
	public Integer getHouseStatus() {
		return houseStatus;
	}
	public void setHouseStatus(Integer houseStatus) {
		this.houseStatus = houseStatus;
	}
	public String getHouseStatusName() {
		return houseStatusName;
	}
	public void setHouseStatusName(String houseStatusName) {
		this.houseStatusName = houseStatusName;
	}
	public Integer getDecorationStandard() {
		return decorationStandard;
	}
	public void setDecorationStandard(Integer decorationStandard) {
		this.decorationStandard = decorationStandard;
	}
	public String getDecorationStandardName() {
		return decorationStandardName;
	}
	public void setDecorationStandardName(String decorationStandardName) {
		this.decorationStandardName = decorationStandardName;
	}
	public String getShelvTime() {
		return shelvTime;
	}
	public void setShelvTime(String shelvTime) {
		this.shelvTime = shelvTime;
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
	public String getPresalePermissionInfo() {
		return presalePermissionInfo;
	}
	public void setPresalePermissionInfo(String presalePermissionInfo) {
		this.presalePermissionInfo = presalePermissionInfo;
	}
	
	public HouseExportDTO createHouseExportDto(House h){
		//this.projectId = h.getProjectId();
		this.houseNo = h.getHouseNo();
		this.buildingNo = h.getBuildingNo();
		// (房源类型，0:公寓、1:排屋、2:独栋、3:商住两用、4:办公室、5:酒店式公寓、6:商铺、7:车位、8:车库、9:储藏室)
		if(h.getHouseKind().equals(0)){
			this.houseKindName = "公寓";
		}
		if(h.getHouseKind().equals(1)){
			this.houseKindName = "排屋";
		}
		if(h.getHouseKind().equals(2)){
			this.houseKindName = "独栋";
		}
		if(h.getHouseKind().equals(3)){
			this.houseKindName = "商住两用";
		}
		if(h.getHouseKind().equals(4)){
			this.houseKindName = "办公室";
		}
		if(h.getHouseKind().equals(5)){
			this.houseKindName = "酒店式公寓";
		}
		if(h.getHouseKind().equals(6)){
			this.houseKindName = "商铺";
		}
		if(h.getHouseKind().equals(7)){
			this.houseKindName = "车位";
		}
		if(h.getHouseKind().equals(8)){
			this.houseKindName = "车库";
		}
		if(h.getHouseKind().equals(9)){
			this.houseKindName = "储藏室";
		}
		this.unit = h.getUnit();
		this.floor = h.getFloor();
		this.direct = h.getDirect();
		this.district = h.getDistrict();
		this.buildArea = h.getBuildArea();
		this.usefulArea = h.getUsefulArea();
		this.listPrice = h.getListPrice();
		this.minimumPrice = h.getMinimumPrice();
		this.shopPrice = h.getShopPrice();
		this.houseTypeName = h.getHouseTypeName();
		// (房源状态，0：初始；1：上架；2：删除；3：销控；4：订购；5：签约)
		if(h.getHouseStatus().equals(0)){
			this.houseStatusName = "初始";
		}
		if(h.getHouseStatus().equals(1)){
			this.houseStatusName = "上架";
		}
		if(h.getHouseStatus().equals(2)){
			this.houseStatusName = "删除";
		}
		if(h.getHouseStatus().equals(3)){
			this.houseStatusName = "销控";
		}
		if(h.getHouseStatus().equals(4)){
			this.houseStatusName = "订购";
		}
		if(h.getHouseStatus().equals(5)){
			this.houseStatusName = "签约";
		}
		// 装修标准(0：毛坯；1：普通装修；2：精装修；3：家具全配；4：家电全配)
		if(h.getDecorationStandard().equals(0)){
			this.decorationStandardName = "毛坯";
		}
		if(h.getDecorationStandard().equals(1)){
			this.decorationStandardName = "普通装修";
		}
		if(h.getDecorationStandard().equals(2)){
			this.decorationStandardName = "精装修";
		}
		if(h.getDecorationStandard().equals(3)){
			this.decorationStandardName = "家具全配";
		}
		if(h.getDecorationStandard().equals(4)){
			this.decorationStandardName = "家电全配";
		}
		this.shelvTime = h.getShelvTime();
		// (经纪人是否可见0:不可见;1:可见)
		if(h.getIsOpen().equals(0)){
			this.isOpenName = " 不可见";
		}
		if(h.getIsOpen().equals(1)){
			this.isOpenName = "可见";
		}
		this.houseNum = h.getHouseNum();
		if(h.getPresalePermissionInfo()!=null && !h.getPresalePermissionInfo().equals("")){
			this.presalePermissionInfo = h.getPresalePermissionInfo();
		}else{
			this.presalePermissionInfo = "暂无数据";
		}
		
		return this;
	}

	
}
