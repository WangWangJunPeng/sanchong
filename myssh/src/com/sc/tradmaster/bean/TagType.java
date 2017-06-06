package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_tagtype")
public class TagType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tagTypeId;//自增主键
	private String tagTypeName;//类目名称
	private Integer parentTagTypeId;//父类目Id
	private Integer tagTypeStatus;//标签类目状态，0-禁用，1-启用,2-删除
	private Integer isMultiple;//是否单选0-单选，1-多选
	private String projectId;//项目ID，标准模板id为0000
	private Integer sortNum;//排序号
	private Integer originalTagType; //0-原始，1-自建
	public Integer getOriginalTagType() {
		return originalTagType;
	}
	public void setOriginalTagType(Integer originalTagType) {
		this.originalTagType = originalTagType;
	}
	public Integer getTagTypeId() {
		return tagTypeId;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public void setTagTypeId(Integer tagTypeId) {
		this.tagTypeId = tagTypeId;
	}
	public String getTagTypeName() {
		return tagTypeName;
	}
	public void setTagTypeName(String tagTypeName) {
		this.tagTypeName = tagTypeName;
	}

	public Integer getParentTagTypeId() {
		return parentTagTypeId;
	}
	public void setParentTagTypeId(Integer parentTagTypeId) {
		this.parentTagTypeId = parentTagTypeId;
	}
	public Integer getTagTypeStatus() {
		return tagTypeStatus;
	}
	public void setTagTypeStatus(Integer tagTypeStatus) {
		this.tagTypeStatus = tagTypeStatus;
	}
	public Integer getIsMultiple() {
		return isMultiple;
	}
	public void setIsMultiple(Integer isMultiple) {
		this.isMultiple = isMultiple;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	
	
	

}
