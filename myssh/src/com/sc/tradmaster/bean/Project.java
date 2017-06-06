package com.sc.tradmaster.bean;

import java.util.ArrayList;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="t_projects")
public class Project {

	//项目id
	private String projectId;
	//项目名称
	private String projectName;
	//省、市、区
	private String city;
	//项目用地面积
	private Double landArea;
	//项目总建筑面积
	private Double buildArea;
	//地上建筑面积
	private Double groundArea;
	//地下建筑面积
	private Double underGroundArea;
	//总户数
	private Integer unitCount;
	//容积率
	private Double volumeRatio;
	//.00显示处理字段
	private String strVolumeRatio;
	//密度
	private Double density;
	//.00显示处理
	private String strDensity;
	//绿化率
	private Double afforestationRatio;
	private String strAfforestationRatio;
	//物业类型
	private String propertyType;
	//售楼地址
	private String saleAddress;
	//物业地址
	private String propertyAddress;
	//区位
	private String district;
	//均价
	private Double averagePrice;
	//.00显示处理字段
	private String strAveragePrice;
	//开发商
	private String developer;
	//投资商
	private String investor;
	//物业管理归属
	private String manager;
	//物业费
	private Double propertyCost;
	//。00显示处理
	private String strPropertyCost;
	//装修标准(0：毛坯；1：普通装修；2：精装修；3：家具全配；4：家电全配)
	private Integer decorationStandard;
	//交付标准
	private String deliverStandard;
	//开工时间
	private String startTime;
	//交付时间
	private String deliverTime;
	//产权年限
	private Integer rightsYears;
	//预售许可证图片
	private String presalePermissionURL;
	//预设的各种标签
	private String tags;
	//预留说明字段
	private String description;
	//售楼处位置经纬度
	private String saleAddressGis;
	//物业位置经纬度
	private String propertyAddressGis;
	//效果图
	private String effectPic;
	//是否入住平台
	private Integer proInSystemStutas;
	
	@Id
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
	public Double getLandArea() {
		return landArea;
	}
	public void setLandArea(Double landArea) {
		this.landArea = landArea;
	}
	public Double getBuildArea() {
		return buildArea;
	}
	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}
	public Double getGroundArea() {
		return groundArea;
	}
	public void setGroundArea(Double groundArea) {
		this.groundArea = groundArea;
	}
	public Double getUnderGroundArea() {
		return underGroundArea;
	}
	public void setUnderGroundArea(Double underGroundArea) {
		this.underGroundArea = underGroundArea;
	}
	public Integer getUnitCount() {
		return unitCount;
	}
	public void setUnitCount(Integer unitCount) {
		this.unitCount = unitCount;
	}
	public Double getVolumeRatio() {
		return volumeRatio;
	}
	public void setVolumeRatio(Double volumeRatio) {
		this.volumeRatio = volumeRatio;
	}
	public Double getDensity() {
		return density;
	}
	public void setDensity(Double density) {
		this.density = density;
	}
	public Double getAfforestationRatio() {
		return afforestationRatio;
	}
	public void setAfforestationRatio(Double afforestationRatio) {
		this.afforestationRatio = afforestationRatio;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getSaleAddress() {
		return saleAddress;
	}
	public void setSaleAddress(String saleAddress) {
		this.saleAddress = saleAddress;
	}
	public String getPropertyAddress() {
		return propertyAddress;
	}
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	@Transient
	public String getStrAveragePrice() {
		return strAveragePrice;
	}
	public void setStrAveragePrice(String strAveragePrice) {
		this.strAveragePrice = strAveragePrice;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getInvestor() {
		return investor;
	}
	public void setInvestor(String investor) {
		this.investor = investor;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public Double getPropertyCost() {
		return propertyCost;
	}
	public void setPropertyCost(Double propertyCost) {
		this.propertyCost = propertyCost;
	}
	public Integer getDecorationStandard() {
		return decorationStandard;
	}
	public void setDecorationStandard(Integer decorationStandard) {
		this.decorationStandard = decorationStandard;
	}
	public String getDeliverStandard() {
		return deliverStandard;
	}
	public void setDeliverStandard(String deliverStandard) {
		this.deliverStandard = deliverStandard;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDeliverTime() {
		return deliverTime;
	}
	public void setDeliverTime(String deliverTime) {
		this.deliverTime = deliverTime;
	}
	public Integer getRightsYears() {
		return rightsYears;
	}
	public void setRightsYears(Integer rightsYears) {
		this.rightsYears = rightsYears;
	}
	public String getPresalePermissionURL() {
		return presalePermissionURL;
	}
	public void setPresalePermissionURL(String presalePermissionURL) {
		this.presalePermissionURL = presalePermissionURL;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSaleAddressGis() {
		return saleAddressGis;
	}
	public void setSaleAddressGis(String saleAddressGis) {
		this.saleAddressGis = saleAddressGis;
	}
	public String getPropertyAddressGis() {
		return propertyAddressGis;
	}
	public void setPropertyAddressGis(String propertyAddressGis) {
		this.propertyAddressGis = propertyAddressGis;
	}
	public String getEffectPic() {
		return effectPic;
	}
	public void setEffectPic(String effectPic) {
		this.effectPic = effectPic;
	}
	
	@Transient
	public String getStrVolumeRatio() {
		return strVolumeRatio;
	}
	public void setStrVolumeRatio(String strVolumeRatio) {
		this.strVolumeRatio = strVolumeRatio;
	}
	@Transient
	public String getStrDensity() {
		return strDensity;
	}
	public void setStrDensity(String strDensity) {
		this.strDensity = strDensity;
	}
	@Transient
	public String getStrAfforestationRatio() {
		return strAfforestationRatio;
	}
	public void setStrAfforestationRatio(String strAfforestationRatio) {
		this.strAfforestationRatio = strAfforestationRatio;
	}
	@Transient
	public String getStrPropertyCost() {
		return strPropertyCost;
	}
	public void setStrPropertyCost(String strPropertyCost) {
		this.strPropertyCost = strPropertyCost;
	}
	public Integer getProInSystemStutas() {
		return proInSystemStutas;
	}
	public void setProInSystemStutas(Integer proInSystemStutas) {
		this.proInSystemStutas = proInSystemStutas;
	}
}
