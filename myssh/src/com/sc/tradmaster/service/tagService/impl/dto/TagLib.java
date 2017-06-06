package com.sc.tradmaster.service.tagService.impl.dto;

import java.io.Serializable;
import java.util.List;

import com.sc.tradmaster.bean.Tag;

/**
 * 该类封装了所有的标签
 * @author Administrator
 *
 */
public class TagLib implements Serializable{
	@Override
	public String toString() {
		return "TagLib [tagTypeId=" + tagTypeId + ", tagTypeName=" + tagTypeName + ", parentTagTpyeId="
				+ parentTagTpyeId + ", tagTypeStatus=" + tagTypeStatus + ", isMultiple=" + isMultiple + ", projectId="
				+ projectId + ", sortNum=" + sortNum + ", tagLibs=" + tagLibs + ", tags=" + tags + "]";
	}
	private Integer tagTypeId;
	private String tagTypeName;
	private Integer parentTagTpyeId;
	private Integer tagTypeStatus;
	private Integer isMultiple;
	private String projectId;
	private Integer sortNum;
	private List<TagLib> tagLibs;
	private List<Tag> tags;
	private Integer originalTagType;
	public Integer getOriginalTagType() {
		return originalTagType;
	}
	public void setOriginalTagType(Integer originalTagType) {
		this.originalTagType = originalTagType;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
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
	public List<TagLib> getTagLibs() {
		return tagLibs;
	}
	public void setTagLibs(List<TagLib> tagLibs) {
		this.tagLibs = tagLibs;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	
	
	
	
}
