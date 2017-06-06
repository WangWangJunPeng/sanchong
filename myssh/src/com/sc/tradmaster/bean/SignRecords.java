package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*
* @author wjp 2017-02-20
*/
@Entity
@Table(name="t_signrecords")
public class SignRecords {
	
	//签到记录号,自增长
	private Integer recordId;
	//签到用户编码
	private String userId;
	//所属单位编码
	private String parentId;
	//签到时间(时分秒)
	private String signInTime;
	//签退时间(时分秒)
	private String signOutTime;
	//签到状态,1:签到,2:签退
//	private Integer type;
	
	@Id
	@GeneratedValue
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getSignInTime() {
		return signInTime;
	}
	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}
	public String getSignOutTime() {
		return signOutTime;
	}
	public void setSignOutTime(String signOutTime) {
		this.signOutTime = signOutTime;
	}
//	public Integer getType() {
//		return type;
//	}
//	public void setType(Integer type) {
//		this.type = type;
//	}
	
	@Override
	public String toString() {
		return "SignRecords [recordId=" + recordId + ", userId=" + userId + ", parentId=" + parentId + ", signInTime="
				+ signInTime + ", signOutTime=" + signOutTime + "]";
	}
	
}
