package com.sc.tradmaster.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sc.tradmaster.bean.Tag;
import com.sc.tradmaster.bean.TagType;
import com.sc.tradmaster.service.tagService.TagService;
import com.sc.tradmaster.service.tagService.impl.dto.TagLib;
import com.sc.tradmaster.utils.SysContent;

public class TagServiceTest {
	private ClassPathXmlApplicationContext ac = null;
	private TagService ts = null;
	
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("spring*.xml");
		ts = ac.getBean("tagService",TagService.class);
	}
	
	//展示所有标签
	@Test
	public void showTagLib(){
		List<TagLib> showTagLib = ts.showTagLib(null, "10000", 1,null);
		System.out.println(showTagLib);
	}
	//给目标添加标签
	@Test
	public void addTagRelationTest(){
		Integer[] tags = {104,124};
		ts.addTagRelation(tags,"ssss");
	}
	
	@Test
	//添加标签
	public void test1(){
		Tag tag = new Tag();
		tag.setParentTagId(87);
		tag.setProjectId("1111");
		tag.setTagName("测试所用");
		ts.addTag(tag);
	}
	
	@Test
	//添加标签类目
	public void test2(){
		TagType tt = new TagType();
		tt.setParentTagTypeId(97);
		tt.setProjectId("1111");
		tt.setTagTypeName("测试阿萨德");
		tt.setTagTypeStatus(1);
		tt.setIsMultiple(1);
		ts.addTagType(tt);
	}
	
	@Test
	public void test3(){
		ts.dropTag(113, "1111");
	}
	
	@Test
	public void test4(){
		ts.dropTagType(106, "1111");
	}
	
	
	@Test
	public void test5(){
		List<TagType> showRootTagType = ts.showRootTagType("1111",97);
		System.out.println(showRootTagType);
	}
	
	@Test
	public void test6(){
		Tag tag = new Tag();
		tag.setProjectId("1111");
		tag.setTagId(107);
		tag.setTagName("老城区");
		tag.setDic("我是老城区");
		ts.updateTag(tag);
	}
	
	@Test
	public void test7(){
		TagType tt = new TagType();
		tt.setIsMultiple(0);
		tt.setParentTagTypeId(11);
		tt.setProjectId("1111");
		ts.updateTagType(tt);
	}
	@Test
	public void test8(){
		List<TagLib> showTagLib_use = ts.showTagLib_use("asdwd", 97, "1111");
		System.out.println(showTagLib_use);
	}
	
	@Test
	public void test9(){
		ts.add_copyTagLib("10000");
//		ts.add_copyTagLib("2c1ce8b2432d489a89ba28143d6b99e2");
//		ts.add_copyTagLib("48a91ac3c316400c9aab7efa83478dbc");
//		ts.add_copyTagLib("527fa0a4d3094458abf72d0179d53060");
//		ts.add_copyTagLib("7015dda84cd046d19c2d09644a90a484");
//		ts.add_copyTagLib("731cdd5779784f6eaa4aa8d22a4cce91");
//		ts.add_copyTagLib("8a4f9ee6f4914564b1ef339f7c90f414");
//		ts.add_copyTagLib("8a5f00c11a5744a1b18463f2161f542e");
//		ts.add_copyTagLib("8bfea1c5416d4dd8bff604ec472f4128");
//		ts.add_copyTagLib("933c5d2e9cf2491292efaf8107981394");
//		ts.add_copyTagLib("9e77a0281b1e460dbeb7180c0e17972b");
//		ts.add_copyTagLib("b0d5436210ba4a73b99b5a18baa747b9");
//		ts.add_copyTagLib("b446a2f6360f42e3ba922852df6c6e43");
//		ts.add_copyTagLib("bb41ade7a1d74b24807ed1ca48f1c8bc");
//		ts.add_copyTagLib("ce89517a0e164230b2f9b9699a7b9b86");
//		ts.add_copyTagLib("f1331c2cd24841f1a5256c92b1a379e4");
	}
	
	@Test
	public void test10(){
		List<Tag> findTargetTag = ts.findTargetTag("0862548c775941e6bba0632b967975c5");
		System.out.println(findTargetTag);
	}
	
	@Test
	public void Test11(){
		String paw = SysContent.md5("2059");
		System.out.println(paw);
	}
	

}
