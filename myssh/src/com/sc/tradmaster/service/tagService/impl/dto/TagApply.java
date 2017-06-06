package com.sc.tradmaster.service.tagService.impl.dto;

public class TagApply {
	private Integer tagId;
	private String tagName;
	private String tagTypeName;
	private Integer tagTypeId;
	private String dic;
	private Integer isMultiple;
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagTypeName() {
		return tagTypeName;
	}
	public void setTagTypeName(String tagTypeName) {
		this.tagTypeName = tagTypeName;
	}
	public Integer getTagTypeId() {
		return tagTypeId;
	}
	public void setTagTypeId(Integer tagTypeId) {
		this.tagTypeId = tagTypeId;
	}
	public String getDic() {
		return dic;
	}
	public void setDic(String dic) {
		this.dic = dic;
	}
	public Integer getIsMultiple() {
		return isMultiple;
	}
	public void setIsMultiple(Integer isMultiple) {
		this.isMultiple = isMultiple;
	}
	
	
}
