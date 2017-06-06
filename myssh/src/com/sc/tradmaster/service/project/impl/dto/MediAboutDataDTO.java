package com.sc.tradmaster.service.project.impl.dto;

import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.User;

public class MediAboutDataDTO {
	//经纪人
	private String mediName;
	//项目名称
	private String proName;
	//备案数
	private int guideCount;
	//确客数
	private int enterCount;
	//备案未到访
	private int ontVisitCount;
	//备案已逾期=到访逾期客户+未到访逾期客户
	private int outValidTimeCount;
	//认购申请数
	private int enterBuyApplyCount;
	//认购完成数
	private int enterBuyEnterApplyCount;
	//签约完成数
	private int signFinishCount;
	//结佣数
	private int payMoneyCount;
	//到款数
	private int receiveMoneyCount;
	//结佣平均时间
	private Integer averageDates;
	//认购审核通过率
	private Double checkProgressRate;
	
	
	
	public String getMediName() {
		return mediName;
	}
	public void setMediName(String mediName) {
		this.mediName = mediName;
	}
	public int getGuideCount() {
		return guideCount;
	}
	public void setGuideCount(int guideCount) {
		this.guideCount = guideCount;
	}
	public int getEnterCount() {
		return enterCount;
	}
	public void setEnterCount(int enterCount) {
		this.enterCount = enterCount;
	}
	public int getOntVisitCount() {
		return ontVisitCount;
	}
	public void setOntVisitCount(int ontVisitCount) {
		this.ontVisitCount = ontVisitCount;
	}
	public int getOutValidTimeCount() {
		return outValidTimeCount;
	}
	public void setOutValidTimeCount(int outValidTimeCount) {
		this.outValidTimeCount = outValidTimeCount;
	}
	public int getEnterBuyApplyCount() {
		return enterBuyApplyCount;
	}
	public void setEnterBuyApplyCount(int enterBuyApplyCount) {
		this.enterBuyApplyCount = enterBuyApplyCount;
	}
	public int getEnterBuyEnterApplyCount() {
		return enterBuyEnterApplyCount;
	}
	public void setEnterBuyEnterApplyCount(int enterBuyEnterApplyCount) {
		this.enterBuyEnterApplyCount = enterBuyEnterApplyCount;
	}
	public int getSignFinishCount() {
		return signFinishCount;
	}
	public void setSignFinishCount(int signFinishCount) {
		this.signFinishCount = signFinishCount;
	}
	public int getPayMoneyCount() {
		return payMoneyCount;
	}
	public void setPayMoneyCount(int payMoneyCount) {
		this.payMoneyCount = payMoneyCount;
	}
	public int getReceiveMoneyCount() {
		return receiveMoneyCount;
	}
	public void setReceiveMoneyCount(int receiveMoneyCount) {
		this.receiveMoneyCount = receiveMoneyCount;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public Integer getAverageDates() {
		return averageDates;
	}
	public void setAverageDates(Integer averageDates) {
		this.averageDates = averageDates;
	}
	public Double getCheckProgressRate() {
		return checkProgressRate;
	}
	public void setCheckProgressRate(Double checkProgressRate) {
		this.checkProgressRate = checkProgressRate;
	}
	
	
	public MediAboutDataDTO createMediAboutDataDTO(User u){
		this.mediName = u.getUserCaption();
		
		return this;
	}
	
	public MediAboutDataDTO createProAboutMediDataDTO(Project p){
		this.proName = p.getProjectName();
		return this;
	}
}
