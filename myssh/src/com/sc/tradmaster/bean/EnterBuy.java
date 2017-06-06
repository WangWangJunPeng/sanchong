package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 2017-01-16
 * @author grl
 *
 */
@Entity
@Table(name="t_enterbuy")
public class EnterBuy {
	private String enterBuyId;
	private String projectId;
	private String isSupportBuy;
	private String outOfTime;
	private Double dposit;
	private String dPositStr;
	private String enterBuyRule;
	private String dscription;
	//合同条款
	private String contractTerms;
	//退定条款
	private String countermand;
	
	@Id
	public String getEnterBuyId() {
		return enterBuyId;
	}
	public void setEnterBuyId(String enterBuyId) {
		this.enterBuyId = enterBuyId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getIsSupportBuy() {
		return isSupportBuy;
	}
	public void setIsSupportBuy(String isSupportBuy) {
		this.isSupportBuy = isSupportBuy;
	}
	public String getOutOfTime() {
		return outOfTime;
	}
	public void setOutOfTime(String outOfTime) {
		this.outOfTime = outOfTime;
	}
	public Double getDposit() {
		return dposit;
	}
	public void setDposit(Double dposit) {
		this.dposit = dposit;
	}
	public String getEnterBuyRule() {
		return enterBuyRule;
	}
	public void setEnterBuyRule(String enterBuyRule) {
		this.enterBuyRule = enterBuyRule;
	}
	public String getDscription() {
		return dscription;
	}
	public void setDscription(String dscription) {
		this.dscription = dscription;
	}
	public String getContractTerms() {
		return contractTerms;
	}
	public void setContractTerms(String contractTerms) {
		this.contractTerms = contractTerms;
	}
	public String getCountermand() {
		return countermand;
	}
	public void setCountermand(String countermand) {
		this.countermand = countermand;
	}
	@Transient
	public String getdPositStr() {
		return dPositStr;
	}
	public void setdPositStr(String dPositStr) {
		this.dPositStr = dPositStr;
	}
	@Override
	public String toString() {
		return "EnterBuy [enterBuyId=" + enterBuyId + ", projectId=" + projectId + ", isSupportBuy=" + isSupportBuy
				+ ", outOfTime=" + outOfTime + ", dposit=" + dposit + ", enterBuyRule=" + enterBuyRule + ", dscription="
				+ dscription + ", contractTerms=" + contractTerms + ", countermand=" + countermand + "]";
	}

}
