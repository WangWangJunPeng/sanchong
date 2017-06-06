package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_partnershops")
public class PartnerShops {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer countId;
	private String userId; //用户编号
	private String shopId; //门店编号
	private String createTime;//关系建立的时间
	private String removeTime;//关系结束的时间
	private Integer relationStatus; //关系状态，0：初始，无关联；1：管辖关系；2：解除关系
	private Integer validity; //有效时间   单位：年
	public Integer getCountId() {
		return countId;
	}
	public void setCountId(Integer countId) {
		this.countId = countId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRemoveTime() {
		return removeTime;
	}
	public void setRemoveTime(String removeTime) {
		this.removeTime = removeTime;
	}
	public Integer getRelationStatus() {
		return relationStatus;
	}
	public void setRelationStatus(Integer relationStatus) {
		this.relationStatus = relationStatus;
	}
	public Integer getValidity() {
		return validity;
	}
	public void setValidity(Integer validity) {
		this.validity = validity;
	}
	
	
	

}
