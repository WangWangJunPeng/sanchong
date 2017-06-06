package com.sc.tradmaster.service.tagService.impl.dto;

import java.util.List;

public class MyTagLib {

	
	private List<Integer> tagTypeIds;
	
	private List<Integer> tags;
	
	private Integer status;

	public List<Integer> getTagTypeIds() {
		return tagTypeIds;
	}

	public void setTagTypeIds(List<Integer> tagTypeIds) {
		this.tagTypeIds = tagTypeIds;
	}

	public List<Integer> getTags() {
		return tags;
	}

	public void setTags(List<Integer> tags) {
		this.tags = tags;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MyTagLib [tagTypeIds=" + tagTypeIds + ", tags=" + tags + ", status=" + status + "]";
	}
	
	
	
}
