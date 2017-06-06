package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_waitsendmessage")
public class MiniMessageWaitSend {

	//id主键，自增
	private Integer id;
	//项目id
	private String projectId;
	//房源主键，id
	private Integer houseNum;
	//认购主键，id，短信发送的标识id
	private Integer recordNo;
	//短信发送时间
	private String date;
	//短信发送目标手机号
	private String phone;
	//短信发送内容
	private String text;
	//短信是否发送
	private String isSendMiniMessage;
	//订单号
	private String orderNum;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Integer getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}
	public Integer getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIsSendMiniMessage() {
		return isSendMiniMessage;
	}
	public void setIsSendMiniMessage(String isSendMiniMessage) {
		this.isSendMiniMessage = isSendMiniMessage;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
}
