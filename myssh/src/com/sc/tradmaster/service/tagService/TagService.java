package com.sc.tradmaster.service.tagService;

import java.util.List;

import com.sc.tradmaster.bean.Tag;
import com.sc.tradmaster.bean.TagType;
import com.sc.tradmaster.service.tagService.impl.dto.MyParentTag;
import com.sc.tradmaster.service.tagService.impl.dto.TagLib;

public interface TagService {
	
	

	/**
	 * 展示标签
	 * @param id 标签类目Id（当为null或者0的时候查询整套标签）
	 * @param projectId 项目Id（当为null或者0000的时候标签查询标准标签）
	 * @param status 标签状态（当为null的时候表示查询所有标签，0-禁用，1-启用）
	 * @param targetId 目标ID 
	 * @return
	 */
	public List<TagLib> showTagLib(Integer id, String projectId,Integer status,String targetId);
	/**
	 * 复制标准标签库至某个案场
	 * @param projectId
	 */
	public void add_copyTagLib(String projectId);

	/**
	 * 2017-4-7 maoxy
	 * 添加标签类目
	 * @param tt
	 * @return
	 */
	public void addTagType(TagType tt);

	/**
	 * 2017-4-7 maoxy
	 * 删除某个案场下的标签类目
	 * @param tagTypeId
	 */
	public void dropTagType(Integer tagTypeId,String porojectId);
	/**
	 * 修改，启用，禁用标签类目
	 * @param tagType
	 * @param projectId
	 */
	public void updateTagType(TagType tagType);

	/**
	 * 添加标签
	 * @param tag
	 */
	public void addTag(Tag tag);
	/**
	 * 删除标签
	 * @param tagId
	 */
	public void dropTag(Integer tagId,String projectId);
	/**
	 * 修改标签
	 * @param tagId
	 * @param tagName
	 * @param dic
	 * @param isMultiple 
	 */
	public void updateTag(Tag tag);


	/**
	 * 查询指定标签类目下的子标签类目
	 * @param projectId 项目ID
	 * @param tagTypeId 标签类目ID
	 * @return
	 */
	public List<TagType> showRootTagType(String projectId,Integer tagTypeId);
	
	/**
	 * 给目标对象添加标签
	 * @param tags 标签ID集合
	 * @param tagerId 目标Id
	 * @return
	 */
	public void addTagRelation(Integer[] tags,String tagerId);
	/**
	 * 展示客户/案场/房源已经拥有的标签
	 * @param targetId 目标Id
	 * @param tagTypeId 标签类目Id
	 * @param projectId 项目Id
	 * @return
	 */
	public List<TagLib> showTagLib_use(String targetId,Integer tagTypeId,String projectId);
	/**
	 * 根据标签id和项目查询指定标签
	 * @param tagId
	 * @param projectId
	 * @return
	 */
	public Tag findTagByTagIdAndProjectId(Integer tagId, String projectId);
	
	/**
	 * 根据目标Id查询所有的标签
	 * @param targetId
	 * @return
	 */
	public List<Tag> findTargetTag(String targetId);
	

	
	
	/**
	 * cdh 2017-05-08
	 * 启用或禁用目标对象的标签类别
	 * @param targetId	目标的id
	 * @param status 启用或删除
	 */
	public boolean updateTargetTagType(String targetId, Integer[] tags, Integer status);
	
	
	/**
	 *  cdh 2017-05-09
	 * 批量添加/更新标签 并设置标签类目的状态
	 * @param targetId 标签所属的目标对象
	 * @param tagType	标签类目
	 * @param tags 标签的集合
	 * @return
	 */
	boolean addBatchTagsAndSetTagTypeStatus(String targetId, TagType tagType, List<MyParentTag> tags);
	

	/**
	 *  cdh 2017-05-04
	 * 添加tagType
	 * @param tagType
	 */
	void addTagTypeByTagType(TagType tagType);
	
	/**
	 * 通过标签名查找标签
	 * @param tagName
	 * @param projectId
	 * @return
	 */
	public Integer findTagIdByTagName(String tagName, String projectId);
	
	
	
	/**
	 * 
	 * 根据标签类目名称查找是否重复
	 * @param tagTypeName
	 * @param projectId
	 * @return
	 */
	boolean findTagTypeIsRepetition(String tagTypeName, String projectId);
	
	/**
	 * 
	 * 根据标签名称查找是否重复
	 * @param tagTypeId 
	 * @param parentTagId
	 * @param tagName
	 * @param projectId
	 * @return
	 */
	boolean findTagIsRepetition(Integer tagTypeId, Integer parentTagId, String tagName, String projectId);
	
	/**
	 * 删除标签类目和标签
	 * @param tagTypeId
	 * @param tags
	 * @return
	 */
	public boolean dropTagTypeAndTags(Integer tagTypeId, Integer[] tags);
}
