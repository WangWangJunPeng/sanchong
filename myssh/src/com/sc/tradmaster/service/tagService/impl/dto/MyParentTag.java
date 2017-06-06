package com.sc.tradmaster.service.tagService.impl.dto;

import java.util.List;
import java.util.Map;

public class MyParentTag {
	
	private static final long serialVersionUID = 1L;
	
	public Integer parentId;
	
	//父标签的名称
	private String parentTagName;
	
	//父标签的状态:0删除 1正常
	private Integer parentTagStatus;
	
	private Integer originalTag;
	
	private List<MyChildTag> tags;
	
	
	
	

	public MyParentTag() {
		super();
	}

	public MyParentTag(Integer parentId, String parentTagName, Integer parentTagStatus, Integer originalTag,
			List<MyChildTag> tags) {
		super();
		this.parentId = parentId;
		this.parentTagName = parentTagName;
		this.parentTagStatus = parentTagStatus;
		this.originalTag = originalTag;
		this.tags = tags;
	}

	public String getParentTagName() {
		return parentTagName;
	}

	public void setParentTagName(String parentTagName) {
		this.parentTagName = parentTagName;
	}

	

	public Integer getParentTagStatus() {
		return parentTagStatus;
	}

	public void setParentTagStatus(Integer parentTagStatus) {
		this.parentTagStatus = parentTagStatus;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	


	

	

	

	public List<MyChildTag> getTags() {
		return tags;
	}

	public void setTags(List<MyChildTag> tags) {
		this.tags = tags;
	}

	public Integer getOriginalTag() {
		return originalTag;
	}

	public void setOriginalTag(Integer originalTag) {
		this.originalTag = originalTag;
	}

	@Override
	public String toString() {
		return "MyParentTag [parentId=" + parentId + ", parentTagName=" + parentTagName + ", parentTagStatus="
				+ parentTagStatus + ", originalTag=" + originalTag + ", tags=" + tags + "]";
	}

	

	
	
}
