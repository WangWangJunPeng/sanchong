package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 2017-02-24
 * @author grl
 *
 */
@Entity
@Table(name="t_platformDefinition")
public class SystemChargeDefinition {
	private Integer sysCharDefId;
	private String projectId;
	private Integer rewardType;
	private Double reward;
	private Double minRewarMoney;
	private Double MaxRewarMoney;
	private String dscription;
	private String tags;
	
	@Id
	@GeneratedValue
	public Integer getSysCharDefId() {
		return sysCharDefId;
	}
	public void setSysCharDefId(Integer sysCharDefId) {
		this.sysCharDefId = sysCharDefId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Integer getRewardType() {
		return rewardType;
	}
	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}
	public Double getReward() {
		return reward;
	}
	public void setReward(Double reward) {
		this.reward = reward;
	}
	public Double getMinRewarMoney() {
		return minRewarMoney;
	}
	public void setMinRewarMoney(Double minRewarMoney) {
		this.minRewarMoney = minRewarMoney;
	}
	public Double getMaxRewarMoney() {
		return MaxRewarMoney;
	}
	public void setMaxRewarMoney(Double maxRewarMoney) {
		MaxRewarMoney = maxRewarMoney;
	}
	public String getDscription() {
		return dscription;
	}
	public void setDscription(String dscription) {
		this.dscription = dscription;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
}
