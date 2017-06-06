package com.sc.tradmaster.service.tagService.impl.dto;

public class MyChildTag {
	
	private static final long serialVersionUID = 1L;
	
	private Integer childId;

	//子标签名称
	private String childTagName;
	
	//子标签的状态：0删除 1正常
	private Integer childTagStatus;
	
	
	
	

	public MyChildTag() {
		super();
	}

	public MyChildTag(Integer childId, String childTagName, Integer childTagStatus) {
		super();
		this.childId = childId;
		this.childTagName = childTagName;
		this.childTagStatus = childTagStatus;
	}

	public String getChildTagName() {
		return childTagName;
	}

	public void setChildTagName(String childTagName) {
		this.childTagName = childTagName;
	}

	public Integer getChildTagStatus() {
		return childTagStatus;
	}

	public void setChildTagStatus(Integer childTagStatus) {
		this.childTagStatus = childTagStatus;
	}

	public Integer getChildId() {
		return childId;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}


	@Override
	public String toString() {
		return "MyChildTag [childId=" + childId + ", childTagName=" + childTagName + ", childTagStatus="
				+ childTagStatus + "]";
	}
	
	
}
