package com.sc.tradmaster.service.contractRecords.impl.dto;

public class ShopCustomerDTO {

	//客户编码
	private String shopCustomerId;
	//客户名称
	private String shopCustomerName;
	//客户电话,唯一性
	private String shopCustomerPhone;
	//中介经纪人的用户编码
	private String userId;
	//建立时间
	private String createTime;
	//性别,0:未知,1:male;2:female
	private Integer sex;
	//预留的描述字段
	private String description;
	//预留的标签字段
	private String tags;
	//对应门店id
	private Integer shopId;
	
	//是否可以被选中(0,不让选,1:可以选)
	private Integer isChoose;
	//身份证号
	private String idCard;
	
	public String getShopCustomerId() {
		return shopCustomerId;
	}


	public void setShopCustomerId(String shopCustomerId) {
		this.shopCustomerId = shopCustomerId;
	}


	public String getShopCustomerName() {
		return shopCustomerName;
	}


	public void setShopCustomerName(String shopCustomerName) {
		this.shopCustomerName = shopCustomerName;
	}


	public String getShopCustomerPhone() {
		return shopCustomerPhone;
	}


	public void setShopCustomerPhone(String shopCustomerPhone) {
		this.shopCustomerPhone = shopCustomerPhone;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public Integer getSex() {
		return sex;
	}


	public void setSex(Integer sex) {
		this.sex = sex;
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


	public Integer getShopId() {
		return shopId;
	}


	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}


	public Integer getIsChoose() {
		return isChoose;
	}


	public void setIsChoose(Integer isChoose) {
		this.isChoose = isChoose;
	}


	public String getIdCard() {
		return idCard;
	}


	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}


	@Override
	public String toString() {
		return "ShopCustomerDTO [shopCustomerId=" + shopCustomerId + ", shopCustomerName=" + shopCustomerName
				+ ", shopCustomerPhone=" + shopCustomerPhone + ", userId=" + userId + ", createTime=" + createTime
				+ ", sex=" + sex + ", description=" + description + ", tags=" + tags + ", shopId=" + shopId
				+ ", isChoose=" + isChoose + ", idCard=" + idCard + "]";
	}

	
}
