package com.sc.tradmaster.service.projectBenefits.impl.dto;

import java.util.ArrayList;
import java.util.List;

public class HouseBenefitsDTO {

	//houseNum
	private Integer houseNum;
	//楼栋号
	private String buildingNo;
	//单元号
	private String unit;
	//房号
	private String houseNo;
	//列表价
	private String listPrice;
	//供货价
	private String shopPrice;
	//底价
	private String minimumPrice;
	//发布时间
	private String shelvTime;
	//最高优惠
	private String highBenefit;
	//所有优惠规则
	private String allBenefits;
	//最佳优惠组合
	private String bestBenefits;
	
	public Integer getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}
	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getListPrice() {
		return listPrice;
	}
	public void setListPrice(String listPrice) {
		this.listPrice = listPrice;
	}
	public String getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(String shopPrice) {
		this.shopPrice = shopPrice;
	}
	public String getMinimumPrice() {
		return minimumPrice;
	}
	public void setMinimumPrice(String minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	public String getShelvTime() {
		return shelvTime;
	}
	public void setShelvTime(String shelvTime) {
		this.shelvTime = shelvTime;
	}
	public String getHighBenefit() {
		return highBenefit;
	}
	public void setHighBenefit(String highBenefit) {
		this.highBenefit = highBenefit;
	}
	public String getAllBenefits() {
		return allBenefits;
	}
	public void setAllBenefits(String allBenefits) {
		this.allBenefits = allBenefits;
	}
	
	public String getBestBenefits() {
		return bestBenefits;
	}
	public void setBestBenefits(String bestBenefits) {
		this.bestBenefits = bestBenefits;
	}
	@Override
	public String toString() {
		return "HouseBenefitsDTO [houseNum=" + houseNum + ", buildingNo=" + buildingNo + ", unit=" + unit + ", houseNo="
				+ houseNo + ", listPrice=" + listPrice + ", shopPrice=" + shopPrice + ", minimumPrice=" + minimumPrice
				+ ", shelvTime=" + shelvTime + ", highBenefit=" + highBenefit + ", allBenefits=" + allBenefits
				+ ", bestBenefits=" + bestBenefits + "]";
	}
	public List getPageList(List li, int start, int limit) {
		int pageSize = start+limit;
		List list = new ArrayList<>();
		if (pageSize>=li.size()){
			pageSize = li.size();
		}
		for(int i=start;i<pageSize;i++){
			list.add(li.get(i));
		}
		return list;
	}
}
