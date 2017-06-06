package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_logRecords")
public class LogRecords {
	//id 主键
	private Integer logRecId;
	//项目id
	private String proId;
	//日期
	private String recordDate;
	//用户id
	private String userId;
	//内容
	private String content;
	//等级
	private Integer level;
	//分类
	private String sorts;
	//状态
	private String status;
	
	public LogRecords(String proId, String recordDate, String userId, String content, Integer level, String sorts,String status) {
		super();
		this.proId = proId;
		this.recordDate = recordDate;
		this.userId = userId;
		this.content = content;
		this.level = level;
		this.sorts = sorts;
		this.status = status;
	}
	
	@Id
	@GeneratedValue
	public Integer getLogRecId() {
		return logRecId;
	}
	public void setLogRecId(Integer logRecId) {
		this.logRecId = logRecId;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getSorts() {
		return sorts;
	}
	public void setSorts(String sorts) {
		this.sorts = sorts;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
