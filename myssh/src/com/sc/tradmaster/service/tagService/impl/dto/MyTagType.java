package com.sc.tradmaster.service.tagService.impl.dto;

import java.util.List;


public class MyTagType {
	
	private List<MyParentTag> myparentTags;
	
	
	
	public List<MyParentTag> getMyparentTags() {
		return myparentTags;
	}
	public void setMyparentTags(List<MyParentTag> myparentTags) {
		this.myparentTags = myparentTags;
	}
	
	
	
	@Override
	public String toString() {
		return "MyTagType [myparentTags=" + myparentTags + "]";
	}
	

}
