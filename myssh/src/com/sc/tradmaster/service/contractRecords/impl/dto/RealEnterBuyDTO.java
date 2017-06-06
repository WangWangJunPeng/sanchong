package com.sc.tradmaster.service.contractRecords.impl.dto;

public class RealEnterBuyDTO {
	private String rName;
	private String rPhone;
	private String rIdCard;
	private String relationDesc;
	
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
	public String getRelationDesc() {
		return relationDesc;
	}
	public void setRelationDesc(String relationDesc) {
		this.relationDesc = relationDesc;
	}
	@Override
	public String toString() {
		return "RealEnterBuyDTO [rName=" + rName + ", rPhone=" + rPhone + ", rIdCard=" + rIdCard + ", relationDesc="
				+ relationDesc + "]";
	}
	
	
}
