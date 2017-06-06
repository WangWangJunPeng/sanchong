package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author wjp 2017-01-09
 */
@Entity
@Table(name = "t_projecthouses")
public class House {
    //房源编号
    private String houseId;
    //案场编号
    private String projectId;
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
    private String buildAreaStr;
    //使用面积
    private Double usefulArea;
    private String usefulAreaStr;
    //列表价
    private Double listPrice;
    private String listPriceStr;
    //底价
    private Double minimumPrice;
    private String minimumPriceStr;
    //中介供货价
    private Double shopPrice;
    private String shopPriceStr;
    //户型编码
    private String houseTypeId;
    //户型名称
    private String houseTypeName;
    //(房源状态-0：下架；1：上架；2：删除；3：撤销;)
    private Integer houseStatus;
    //装修标准(0：毛坯；1：普通装修；2：精装修；3：家具全配；4：家电全配)
    private Integer decorationStandard;
    //备用的说明性字段
    private String description;
    //标签字段
    private String tags;
    //户型图片URL
    private String photos;
    //发布时间
    private String shelvTime;

    private double rewardMoney;   //数据库表里没有这个字段,,,,
    //(经纪人是否可见0:不可见;1:可见)
    private Integer isOpen;
    //房子的唯一编码
    private Integer houseNum;
    //预售证信息
    private String presalePermissionInfo;
    //最佳优惠组合
    private String bestBenefitsId;

    
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

    @Id
    @GeneratedValue
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

    public String getBestBenefitsId() {
        return bestBenefitsId;
    }

    public void setBestBenefitsId(String bestBenefitsId) {
        this.bestBenefitsId = bestBenefitsId;
    }

    @Transient
    public String getHouseTypeName() {
		return houseTypeName;
	}

	public void setHouseTypeName(String houseTypeName) {
		this.houseTypeName = houseTypeName;
	}

	@Transient
	public String getBuildAreaStr() {
		return buildAreaStr;
	}

	public void setBuildAreaStr(String buildAreaStr) {
		this.buildAreaStr = buildAreaStr;
	}

	@Transient
	public String getUsefulAreaStr() {
		return usefulAreaStr;
	}

	public void setUsefulAreaStr(String usefulAreaStr) {
		this.usefulAreaStr = usefulAreaStr;
	}

	@Transient
	public String getListPriceStr() {
		return listPriceStr;
	}

	public void setListPriceStr(String listPriceStr) {
		this.listPriceStr = listPriceStr;
	}

	@Transient
	public String getMinimumPriceStr() {
		return minimumPriceStr;
	}

	public void setMinimumPriceStr(String minimumPriceStr) {
		this.minimumPriceStr = minimumPriceStr;
	}

	@Transient
	public String getShopPriceStr() {
		return shopPriceStr;
	}

	public void setShopPriceStr(String shopPriceStr) {
		this.shopPriceStr = shopPriceStr;
	}

	@Override
    public String toString() {
        return "House [houseId=" + houseId + ", projectId=" + projectId + ", houseNo=" + houseNo + ", district="
                + district + ", buildingNo=" + buildingNo + ", houseKind=" + houseKind + ", unit=" + unit + ", floor="
                + floor + ", direct=" + direct + ", buildArea=" + buildArea + ", usefulArea=" + usefulArea
                + ", listPrice=" + listPrice + ", minimumPrice=" + minimumPrice + ", shopPrice=" + shopPrice
                + ", houseTypeId=" + houseTypeId + ", houseStatus=" + houseStatus + ", decorationStandard="
                + decorationStandard + ", description=" + description + ", tags=" + tags + ", photos=" + photos
                + ", shelvTime=" + shelvTime + ", rewardMoney=" + rewardMoney + ", isOpen=" + isOpen + ", houseNum="
                + houseNum + ", presalePermissionInfo=" + presalePermissionInfo + ", bestBenefitsId=" + bestBenefitsId
                + "]";
    }

}
