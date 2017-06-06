package com.sc.tradmaster.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 真实认购人
 * @author grl
 *
 */
@Entity
@Table(name="t_realEnterBuyMan")
public class RealEnterBuyMan {
	private Integer realEnterBuyId;
	private String rName;
	private String rPhone;
	private String rIdCard;
	//private EnterBuyManAndRealEnterBuyManRelation realId;
	//认购客户id
	private String customerId;
	//认购关系
	private String relation;
	//0:启用,1:禁用
	private String isUserful;
	
	
	@Id
	@GeneratedValue
	public Integer getRealEnterBuyId() {
		return realEnterBuyId;
	}
	public void setRealEnterBuyId(Integer realEnterBuyId) {
		this.realEnterBuyId = realEnterBuyId;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getrPhone() {
		return rPhone;
	}
	public void setrPhone(String rPhone) {
		this.rPhone = rPhone;
	}
	public String getrIdCard() {
		return rIdCard;
	}
	public void setrIdCard(String rIdCard) {
		this.rIdCard = rIdCard;
	}
	
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	public String getIsUserful() {
		return isUserful;
	}
	public void setIsUserful(String isUserful) {
		this.isUserful = isUserful;
	}
	/*@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="realId")
	public EnterBuyManAndRealEnterBuyManRelation getRealId() {
		return realId;
	}
	public void setRealId(EnterBuyManAndRealEnterBuyManRelation realId) {
		this.realId = realId;
	}*/
	@Override
	public String toString() {
		return "RealEnterBuyMan [realEnterBuyId=" + realEnterBuyId + ", rName=" + rName + ", rPhone=" + rPhone
				+ ", rIdCard=" + rIdCard + ", customerId=" + customerId + ", relation="
				+ relation + ", isUserful=" + isUserful + "]";
	}
	
}

