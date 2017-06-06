package com.sc.tradmaster.service.tagService.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sc.tradmaster.bean.Tag;
import com.sc.tradmaster.bean.TagType;
import com.sc.tradmaster.bean.TagsRelation;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.tagService.TagService;
import com.sc.tradmaster.service.tagService.impl.dto.MyChildTag;
import com.sc.tradmaster.service.tagService.impl.dto.MyParentTag;
import com.sc.tradmaster.service.tagService.impl.dto.TagLib;


@Service("tagService")
public class TagServiceImpl implements TagService{
	
	@Resource(name = "baseDao")
	private BaseDao baseDao;


	@Override
	public List<TagLib> showTagLib(Integer id,String projectId,Integer status,String targetId) {
		if(id==null){
			id=0;
		}
		if(StringUtils.isEmpty(projectId)){
			projectId = "0000";
		}
		List<Integer> tagsList = null;
		if(targetId!=null){
			TagsRelation tr =(TagsRelation) baseDao.loadObject("from TagsRelation where targetId='"+targetId+"'");
			//目标所拥有的tagId
			if(tr!=null){
				String tags = tr.getTags();
				tagsList = new ArrayList<Integer>();
				String[] split = tags.split(",");
				for(String s : split){
					Integer valueOf = Integer.valueOf(s.trim());
					tagsList.add(valueOf); 
				}
			}
		}
		
		//查询所有的根标签类目
		List<TagType> tagTypes = new ArrayList<>();
		if(status==null){
			tagTypes = baseDao.findByHql("from TagType where parentTagTypeId="+id+" and projectId='"+projectId+"'");
		}else{
			tagTypes = baseDao.findByHql("from TagType where parentTagTypeId="+id+" and projectId='"+projectId+"' and tagTypeStatus="+status);
		}
		List<TagLib> tagLibs = new ArrayList<>();
		if(tagTypes.size()==0){
			//没有标签类目
			tagTypes = baseDao.findByHql("from TagType where tagTypeId="+id+" and projectId='"+projectId+"'");
		}
		//遍历所有的根标签类目
		for(TagType tagType:tagTypes){
			TagLib t1 = new TagLib();
			t1.setTagTypeId(tagType.getTagTypeId());
			t1.setTagTypeName(tagType.getTagTypeName());
			t1.setParentTagTpyeId(tagType.getParentTagTypeId());
			t1.setTagTypeStatus(tagType.getTagTypeStatus());
			t1.setIsMultiple(tagType.getIsMultiple());
			t1.setProjectId(tagType.getProjectId());
			t1.setSortNum(tagType.getSortNum());
			t1.setOriginalTagType(tagType.getOriginalTagType());//是否是原始标签
			//查询该根标签类目下的所有标签
			List<Tag> tags = baseDao.findByHql("from Tag where tagTypeId="+tagType.getTagTypeId()+" and projectId='"+projectId+"' and tagStatus=1");
			getAllChildTag(tags,projectId,tagsList);
			t1.setTags(tags);
			tagLibs.add(t1);
		}
		List<TagType> listAll = null;
		if(status==null){
			listAll = baseDao.findByHql("from TagType where projectId='"+projectId+"'");
		}else{
			listAll = baseDao.findByHql("from TagType where projectId='"+projectId+"' and tagTypeStatus="+status);
		}
		getAllTypeList(listAll,tagLibs,projectId,tagsList);
		return tagLibs;
	}
	//获取所有的标签子集
	private void getAllChildTag(List<Tag> tags,String projectId,List<Integer> tagsList){
		for(Tag tag:tags){
			List<Tag> child = baseDao.findByHql("from Tag where parentTagId="+tag.getTagId()+" and projectId='"+projectId+"' and tagStatus=1");
			tag.setSelected(0);
			if(tagsList!=null){
				boolean flag = false;
				for(Integer i:tagsList){
					if(i.equals(tag.getTagId())){
						flag = true;
						break;
					}
				}
				if(flag){
					tag.setSelected(1);
				}
			}
			tag.setChildren(child);
			if(child.size()!=0){
				getAllChildTag(child, projectId,tagsList);
			}
			
		}
		
	}
	//递归调用 设置所有的子标签目录
	private void getAllTypeList(List<TagType> tagTypes,List<TagLib> tagLibs,String projectId,List<Integer> tagList){
		for(TagLib t1 :tagLibs){
			List<TagLib> tagLib2 = new ArrayList<>();
			Integer tagTypeId = t1.getTagTypeId();
			//遍历所有的标签类目
			for(TagType tagType:tagTypes){
				Integer parentTagTpyeId = tagType.getParentTagTypeId();
				if(tagTypeId.equals(parentTagTpyeId)){
					TagLib t2 = new TagLib();
					t2.setTagTypeId(tagType.getTagTypeId());
					t2.setTagTypeName(tagType.getTagTypeName());
					t2.setParentTagTpyeId(tagType.getParentTagTypeId());
					t2.setTagTypeStatus(tagType.getTagTypeStatus());
					t2.setIsMultiple(tagType.getIsMultiple());
					t2.setProjectId(tagType.getProjectId());
					t2.setSortNum(tagType.getSortNum());
					t2.setOriginalTagType(tagType.getOriginalTagType());//是否是原始标签
					List<Tag> tags = baseDao.findByHql("from Tag where tagTypeId="+tagType.getTagTypeId()+" and projectId='"+projectId+"' and tagStatus=1");
					getAllChildTag(tags,projectId,tagList);
					t2.setTags(tags);
					tagLib2.add(t2);
				}
			}
			t1.setTagLibs(tagLib2);
			if(tagLib2.size()!=0){
				getAllTypeList(tagTypes, tagLib2,projectId,tagList);
			}
		}
	}
	
	
	@Override
	public void addTagType(TagType tt) {
		try {
			if(StringUtils.isEmpty(tt.getProjectId())){
				int a = 1/0;
			}
			tt.setTagTypeStatus(1);
			List<TagType> tts = baseDao.findByHql("from TagType where parentTagTypeId="+tt.getParentTagTypeId()+" and projectId='"+tt.getProjectId()+"'");
			if(tts.size()>0){
				int max = -1;
				for(TagType tt1:tts){
					if(max<tt1.getSortNum()){
						max = tt1.getSortNum();
					}
				}
				tt.setSortNum(max+1);
			}else{
				tt.setSortNum(1);
			}
			baseDao.save(tt);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	@Override
	public void dropTagType(Integer tagTypeId,String projectId) {
		TagType tagType = (TagType)baseDao.loadObject("from TagType where tagTypeId="+tagTypeId+" and projectId='"+projectId+"'");
		dropTagTypeChild(tagTypeId,projectId);
		List<Tag> tags = baseDao.findByHql("from Tag where tagTypeId="+tagTypeId+" and projectId='"+projectId+"'");
		if(tags.size()>0){
			for(Tag tag:tags){
				dropTagChild(tag.getTagId(), projectId);
				baseDao.delete(tag);
			}
		}
		baseDao.delete(tagType);
	}
	/**
	 * 删除该标签类目下的所有子标签类目及其子标签类目下的标签（不包含本标签）
	 * @param tagTypeId
	 * @param projectId
	 */
	private void dropTagTypeChild(Integer tagTypeId,String projectId){
		List<TagType> tagTypes = baseDao.findByHql("from TagType where parentTagTpyeId="+tagTypeId+" and projectId='"+projectId+"'");
		if(tagTypes.size()>0){
			for(TagType tt:tagTypes){
				Integer tagTypeId2 = tt.getTagTypeId();
				List<Tag> tags = baseDao.findByHql("from Tag where tagTypeId="+tagTypeId2+" and projectId='"+projectId+"'");
				if(tags.size()>0){
					for(Tag tag:tags){
						dropTagChild(tag.getTagId(), projectId);
						baseDao.delete(tag);
					}
				}
				dropTagTypeChild(tagTypeId2,projectId);
				baseDao.delete(tt);
			}
		}
	}
	@Override
	public void updateTagType(TagType tagType) {
		Integer tagTypeId = tagType.getTagTypeId();
		String projectId = tagType.getProjectId();
		TagType tagType1 = (TagType)baseDao.loadObject("from TagType where tagTypeId="+tagTypeId+" and projectId='"+projectId+"'");
		if(tagType1!=null){
			if(!StringUtils.isEmpty(tagType.getTagTypeName())){
				tagType1.setTagTypeName(tagType.getTagTypeName());
			}
			if(tagType.getTagTypeStatus()!=null){
				tagType1.setTagTypeStatus(tagType.getTagTypeStatus());
			}
			if(tagType.getIsMultiple()!=null){
				tagType1.setIsMultiple(tagType.getIsMultiple());
			}
			baseDao.saveOrUpdate(tagType1);
		}else{
			throw new RuntimeException();
		}
	}

	@Override
	public void addTag(Tag tag) {
		baseDao.saveOrUpdate(tag);
	}
	@Override
	public void dropTag(Integer tagId,String projectId) {
		Tag tag = (Tag)baseDao.loadObject("from Tag where tagId="+tagId+" and projectId='"+projectId+"'");
		if(tag!=null){
			dropTagChild(tagId,projectId);
			baseDao.delete(tag);
		}else{
			throw new RuntimeException();
		}
		
	}
	/**
	 * 删除该标签下的所有子标签（不包含本标签）
	 * @param tagId
	 * @param projectId
	 */
	private void dropTagChild(Integer tagId,String projectId){
		List<Tag> tags = baseDao.findByHql("from Tag where parentTagId="+tagId+" and projectId='"+projectId+"'");
		if(tags.size()>0){
			for(Tag tag:tags){
				dropTagChild(tag.getTagId(), projectId);
				baseDao.delete(tag);
			}
		}
	}
	@Override
	public void updateTag(Tag tag) {
		Tag tag1 = (Tag)baseDao.loadObject("from Tag where tagId="+tag.getTagId()+" and projectId='"+tag.getProjectId()+"'");
		if(tag1!=null){
			if(!StringUtils.isEmpty(tag.getDic())){
				tag1.setDic(tag.getDic());
			}
			if(!StringUtils.isEmpty(tag.getTagName())){
				tag1.setTagName(tag.getTagName());
			}
			baseDao.saveOrUpdate(tag1);
		}else{
			throw new RuntimeException();
		}
		
	}



	@Override
	public void add_copyTagLib(String projectId) {
		List<TagLib> standardTagLib = showTagLib(0,"0000",null,null);
		for(TagLib tl:standardTagLib){
			TagType root = new TagType();
			root.setIsMultiple(tl.getIsMultiple());
			root.setParentTagTypeId(tl.getParentTagTpyeId());
			root.setProjectId(projectId);
			root.setTagTypeName(tl.getTagTypeName());
			root.setTagTypeStatus(tl.getTagTypeStatus());
			root.setOriginalTagType(tl.getOriginalTagType());
			root.setSortNum(tl.getSortNum());
			baseDao.saveOrUpdate(root);
			Integer rootId = root.getTagTypeId();
			add_copyTagTypeChild(tl, projectId, rootId);
			List<Tag> tags = tl.getTags();
			add_copyTagChild(tags, projectId, rootId, null);
		}
		
	}
	//复制所有标签
	private void add_copyTagChild(List<Tag> tags,String projectId,Integer tagTypeId,Integer tagId){
		if(tags.size()!=0){
			for(Tag tag:tags){
				Tag t = new Tag();
				t.setDic(tag.getDic());
				t.setProjectId(projectId);
				t.setTagName(tag.getTagName());
				t.setTagTypeId(tagTypeId);
				t.setParentTagId(tagId);
				t.setOriginalTag(tag.getOriginalTag());
				t.setTagStatus(tag.getTagStatus());
				baseDao.saveOrUpdate(t);
				Integer tagId2 = t.getTagId();
				List<Tag> children = tag.getChildren();
				add_copyTagChild(children, projectId, null, tagId2);
			}
		}
	}
	
	//复制所有的标签类目
	private void add_copyTagTypeChild(TagLib tagLib,String projectId,Integer tagTypeId){
		List<TagLib> tagLibs = tagLib.getTagLibs();
		if(tagLibs.size()!=0){
			for(TagLib tl:tagLibs){
				TagType t2 = new TagType();
				t2.setIsMultiple(tl.getIsMultiple());
				t2.setParentTagTypeId(tagTypeId);
				t2.setProjectId(projectId);
				t2.setTagTypeName(tl.getTagTypeName());
				t2.setTagTypeStatus(tl.getTagTypeStatus());
				t2.setOriginalTagType(tl.getOriginalTagType());
				t2.setSortNum(tl.getSortNum());
				baseDao.saveOrUpdate(t2);
				Integer tagTypeId2 = t2.getTagTypeId();
				add_copyTagTypeChild(tl, projectId, tagTypeId2);
				List<Tag> tags = tl.getTags();
				add_copyTagChild(tags, projectId, tagTypeId2, null);
			}
			
		}
	}
	
	@Override
	public List<TagType> showRootTagType(String projectId,Integer tagTypeId) {
		List<TagType> tagTypes = baseDao.findByHql("from TagType where parentTagTypeId="+tagTypeId+" and projectId='"+projectId+"'");
		return tagTypes;
	}
	
	@Override
	public void addTagRelation(Integer[] tags, String targetId) {
		List<TagsRelation> tagsRelations = baseDao.findByHql("from TagsRelation where targetId='"+targetId+"'");
		if(tagsRelations.size()>0){
			baseDao.delete(tagsRelations.get(0));
		}
		//第一次添加
		if(tags.length==0){
			return;
		}
		TagsRelation tr = new TagsRelation();
		String tags1 = Arrays.toString(tags);
		String substring = tags1.substring(1, tags1.length()-1);
		tr.setTags(substring);
		tr.setTargetId(targetId);
		baseDao.saveOrUpdate(tr);
	}
	
	
	@Override
	public boolean updateTargetTagType(String targetId, Integer[] tags, Integer status) {
		
		if(status == null){
			return false;
		}
		
		if(StringUtils.isEmpty(targetId))
			return false;
		
		try {
			
				
				
				
				//保存项目选中的标签
				addTagRelation(tags, targetId);
				
				return true;
				
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 
	 * 根据项目id和tagType的名称获取TagType对象
	 * @param targetId	目标对象id
	 * @param tagType	标签类目
	 * @return
	 */
	public TagType findTagTypeByTagTypeId(String targetId, TagType tagType){
		
		if(StringUtils.isEmpty(targetId))
			return null;
		if(tagType == null)
			return null;
		String tagTypeHql = "from TagType where tagTypeId = '" + tagType.getTagTypeId() + "' and projectId = '" + targetId + "' and tagTypeStatus != 2";
		
		TagType tagt = (TagType) baseDao.loadObject(tagTypeHql);
		
		return tagt;
	}
	
	/**
	 * 根据tag的tagName查找tag
	 * @param tagName	需要查找的标签名
	 * @param tagTypeId	标签类目
	 * @param targetId	目标id
	 * @return
	 */
	public Tag findTagByTagName(String tagName, Integer tagTypeId, String targetId){
		if(StringUtils.isEmpty(tagName))
			return null;
		if(tagTypeId == null)
			return null;
		if(StringUtils.isEmpty(targetId))
			return null;
		
		String hql = "from Tag where tagName = '" + tagName + "' and projectId = '" + targetId + "' and tagTypeId = " + tagTypeId + " and tagStatus = 1";
		
		return (Tag) baseDao.loadObject(hql);
		
		
	}
	
	
	@Override
	public boolean addBatchTagsAndSetTagTypeStatus(String targetId, TagType tagType, List<MyParentTag> tags) {
		
		if(StringUtils.isEmpty(targetId))
			return false;
		if(tagType == null)
			return false;
		
		try {
			
			//1.保存产品类目下的标签类目
			tagType.setProjectId(targetId);
			if(tagType.getTagTypeId() == null) //如果是已经存在的只保存
				addTagTypeByTagType(tagType);
			else
				updateTagType(tagType);
			//2.查找刚才保存的标签类目
			TagType tagt = findTagTypeByTagTypeId(targetId, tagType);
			if(tagt == null)
				return false;
			
			//3.保存类目下的一级标签
			for(MyParentTag mpt : tags){
				Tag pTag = new Tag();
				if(!"".equals(mpt.getParentTagName()) && null != mpt.getParentTagName()){
					
					if(mpt.getParentId() == null){ //如果tagid为空说明新标签，需要保存
						
						pTag.setTagName(mpt.getParentTagName());
						pTag.setTagTypeId(tagt.getTagTypeId());
						pTag.setProjectId(targetId);
						pTag.setTagStatus(mpt.getParentTagStatus());
						pTag.setOriginalTag(mpt.getOriginalTag());
						addTag(pTag);
					}else{
						pTag.setTagId(mpt.getParentId());
						pTag.setTagName(mpt.getParentTagName());
						pTag.setTagTypeId(tagt.getTagTypeId());
						pTag.setProjectId(targetId);
						pTag.setTagStatus(mpt.getParentTagStatus());
						pTag.setOriginalTag(mpt.getOriginalTag());
						addTag(pTag);
					}
				}
				
				
				//4.查找刚才保存的一级标签
				Tag tag = findTagByTagName(pTag.getTagName(), tagt.getTagTypeId(), targetId);
				
				if(tag != null){
					
					List<MyChildTag> list =  mpt.getTags();
					
					
					//5.保存二级标签
					for(MyChildTag mct : list){
						Tag cTag = new Tag();
						//System.out.println(mct.get("childId"));
						if(mct.getChildId() == null){
							cTag.setParentTagId(tag.getTagId());
							cTag.setProjectId(targetId);
							cTag.setTagName(mct.getChildTagName());
							//cTag.setTagTypeId(tagt.getTagTypeId());
							cTag.setTagStatus(mct.getChildTagStatus());
							cTag.setOriginalTag(1);//子标签是能删除的
							addTag(cTag);
						}else{
							cTag.setTagId(mct.getChildId());
							cTag.setParentTagId(tag.getTagId());
							cTag.setProjectId(targetId);
							cTag.setTagName(mct.getChildTagName());
							//cTag.setTagTypeId(tagt.getTagTypeId());
							cTag.setTagStatus(mct.getChildTagStatus());
							cTag.setOriginalTag(1);//子标签是能删除的
							addTag(cTag);
						}
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		//return false;
	}
	
	
	@Override
	public List<TagLib> showTagLib_use(String targetId, Integer tagTypeId,String projectId) {
		TagsRelation tr =(TagsRelation) baseDao.loadObject("from TagsRelation where targetId='"+targetId+"'");
		//目标所拥有的Id
		List<Integer> tagsList = new ArrayList<Integer>();
		if(tr!=null){
			String tags = tr.getTags();
			String[] split = tags.split(",");
			for(String s : split){
				Integer valueOf = Integer.valueOf(s.trim());
				tagsList.add(valueOf);
			}
		}
		//所有的标签
		List<TagLib> tagLibs = this.showTagLib(tagTypeId, projectId, 1,null);
		getTagLib_user(tagLibs,tagsList);
		return tagLibs;
	}

	/**
	 * 得到目标使用中的标签及其子标签
	 */
	private void getTagLib_user(List<TagLib> tagLibs,List<Integer> tagsList){
		if(tagLibs.size()>0){
			for(TagLib tagLib:tagLibs){
				List<Tag> tags = tagLib.getTags();
				//含有子标签
				if(tags.size()>0){
					getTag_use(tags,tagsList);
				}
				//遍历该标签类目下的子标签类目
				if(tagLib.getTagLibs().size()>0){
					getTagLib_user(tagLib.getTagLibs(),tagsList);
				}
			}
		}
	}
	/**
	 * 获取目标使用中的标签
	 * @param tags
	 * @param tagsList
	 */
	private void getTag_use(List<Tag> tags,List<Integer> tagsList){
		if(tags.size()>0){
			Iterator<Tag> iterator = tags.iterator();
			while(iterator.hasNext()){
				Tag tag = iterator.next();
				List<Tag> children = tag.getChildren();
				//子标签内含有子标签
				if(children.size()>0){
					getTag_use(children,tagsList);
					//递归结束 标签的子标签没有一个被选中
					if(children.size()==0){
						iterator.remove();
					}
				}else{
					//子标签内不含有子标签
					//默认删除
					boolean flag = true;
					for(Integer tagId : tagsList){
						//如果存在,就不删除
						if(tag.getTagId().equals(tagId)){
							flag = false;
							break;
						}
					}
					if(flag){
						iterator.remove();
					}
				}
				
				
			}
			
		}
	}
	@Override
	public Tag findTagByTagIdAndProjectId(Integer tagId, String projectId) {
		Tag tag = (Tag)baseDao.loadObject("from Tag where tagId="+tagId+" and projectId='"+projectId+"'");
		return tag;
	}
	
	@Override
	public void addTagTypeByTagType(TagType tagType) {
		try {
			if(StringUtils.isEmpty(tagType.getProjectId())){
				int a = 1/0;
			}
			List<TagType> tts = baseDao.findByHql("from TagType where parentTagTypeId="+tagType.getParentTagTypeId()+" and projectId='"+tagType.getProjectId()+"'");
			if(tts.size()>0){
				int max = -1;
				for(TagType tt1:tts){
					if(max<tt1.getSortNum()){
						max = tt1.getSortNum();
					}
				}
				tagType.setSortNum(max+1);
			}else{
				tagType.setSortNum(1);
			}
			baseDao.save(tagType);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}
	@Override
	public Integer findTagIdByTagName(String tagName, String projectId) {
		Tag loadObject = (Tag)baseDao.loadObject("from Tag where tagName="+tagName+" and projectId='"+projectId+"'");
		if(loadObject!=null){
			return loadObject.getTagId();
		}
		return null;
	}
	
	@Override
	public boolean findTagTypeIsRepetition(String tagTypeName, String projectId) {
		
		String hql = "from TagType where tagTypeName ='" + tagTypeName + "' and projectId = '" + projectId + "' and tagTypeStatus != 2";
		List<TagType> list = baseDao.findByHql(hql);
		if(list.size() > 0)
			return false;
		else
			return true;
	}
	
	@Override
	public boolean findTagIsRepetition(Integer tagTypeId, Integer parentTagId, String tagName, String projectId) {
		String hql = "";
		if(parentTagId != null && parentTagId != 0){
			hql = "from Tag where tagName = '" + tagName + "' and parentTagId = " + parentTagId + " and projectId = '" + projectId + "'";
		}else{
			hql = "from Tag where tagName = '" + tagName + "' and tagTypeId = " + tagTypeId + " and projectId = '" + projectId + "'";
		}
		List<Tag> list = baseDao.findByHql(hql);
		if(list.size() > 0)
			return false;
		else
			return false;
	}
	
	@Override
	public boolean dropTagTypeAndTags(Integer tagTypeId, Integer[] tags){
		
		if(tagTypeId == null)
			return false;
		
		//更新标签类目为删除
		
		TagType tagType = (TagType) baseDao.loadById(TagType.class, tagTypeId);
		tagType.setTagTypeId(tagTypeId);
		tagType.setTagTypeStatus(2);
		baseDao.saveOrUpdate(tagType);
		
		
		//批量更新标签状态为删除
		if(tags != null){
			
			for(int i = 0; i < tags.length; i++){
				
				Tag tag = (Tag) baseDao.loadById(Tag.class, tags[i]);
				
				tag.setTagId(tags[i]);
				tag.setTagStatus(0);
				baseDao.saveOrUpdate(tag);
			}
		}
		
		return true;
	}
	@Override
	public List<Tag> findTargetTag(String targetId) {
		List<Tag> t  = new ArrayList<>();
		//查询目标含有的标签
		TagsRelation tr =(TagsRelation) baseDao.loadObject("from TagsRelation where targetId='"+targetId+"'");
		if(tr==null){
			return t;
		}
		String tagsString = tr.getTags();
		String[] split = tagsString.split(",");
		//i  含有标签集合
		List<Integer> i = new ArrayList<>();
		for(String s : split){
			if(!StringUtils.isEmpty(s)){
				Integer valueOf = Integer.valueOf(s.trim());
				i.add(valueOf);
			}
		}
		if(i.size()>0){
			for(Integer tagId : i){
				Tag tag = (Tag)baseDao.loadObject("from Tag where tagId="+tagId);
				if(tag!=null){
					t.add(tag);
				}
			}
		}
		return t;
	}

}
