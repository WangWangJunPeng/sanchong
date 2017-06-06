package com.sc.tradmaster.bean;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 客户认购和真实认购人的关系
 * @author grl
 *
 */
@Entity
@Table(name="t_enterBuyRelation")
public class EnterBuyManAndRealEnterBuyManRelation {
	private Integer relationId;
	//案场客户id/中介客户id
	private String customerId;
	private ContractRecords conrecId;
	private Set<RealEnterBuyMan> realEnterBuyManId;
	private Integer relationDesc;
	
	public EnterBuyManAndRealEnterBuyManRelation(){}
	
	public EnterBuyManAndRealEnterBuyManRelation(String customerId, ContractRecords conrecId, Integer relationDesc) {
		super();
		this.customerId = customerId;
		this.conrecId = conrecId;
		this.relationDesc = relationDesc;
	}
	@Id
	@GeneratedValue
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	@OneToOne
	@JoinColumn(name="conrecId")
	public ContractRecords getConrecId() {
		return conrecId;
	}
	public void setConrecId(ContractRecords conrecId) {
		this.conrecId = conrecId;
	}
	@OneToMany
	@JoinColumn(name="realEnterBuyManId")
	public Set<RealEnterBuyMan> getRealEnterBuyManId() {
		return realEnterBuyManId;
	}
	public void setRealEnterBuyManId(Set<RealEnterBuyMan> realEnterBuyManId) {
		this.realEnterBuyManId = realEnterBuyManId;
	}
	public Integer getRelationDesc() {
		return relationDesc;
	}
	public void setRelationDesc(Integer relationDesc) {
		this.relationDesc = relationDesc;
	}
}
