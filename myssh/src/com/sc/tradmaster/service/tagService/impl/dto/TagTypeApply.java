package com.sc.tradmaster.service.tagService.impl.dto;

public class TagTypeApply {
	private Integer tagTypeId;
	private String tagTypeName;
	private Integer parentTagTpyeId;
	private String parentTagTypeName;
	private Integer hasTags;
	public Integer getTagTypeId() {
		return tagTypeId;
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
	public Integer getParentTagTpyeId() {
		return parentTagTpyeId;
	}
	public void setParentTagTpyeId(Integer parentTagTpyeId) {
		this.parentTagTpyeId = parentTagTpyeId;
	}
	public String getParentTagTypeName() {
		return parentTagTypeName;
	}
	public void setParentTagTypeName(String parentTagTypeName) {
		this.parentTagTypeName = parentTagTypeName;
	}
	public Integer getHasTags() {
		return hasTags;
	}
	public void setHasTags(Integer hasTags) {
		this.hasTags = hasTags;
	}
	
}
