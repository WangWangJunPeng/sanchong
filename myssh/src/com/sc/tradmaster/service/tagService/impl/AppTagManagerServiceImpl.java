package com.sc.tradmaster.service.tagService.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.Tag;
import com.sc.tradmaster.bean.TagType;
import com.sc.tradmaster.bean.TagsRelation;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.agent.AgentVisitRecordService;
import com.sc.tradmaster.service.tagService.AppTagManagerService;
import com.sc.tradmaster.service.tagService.TagService;
import com.sc.tradmaster.service.tagService.impl.dto.PCustomerInformation;
import com.sc.tradmaster.service.tagService.impl.dto.TagLib;


@Service("appTagManagerService")
public class AppTagManagerServiceImpl implements AppTagManagerService{
	
	
	@Resource(name = "baseDao")
	private BaseDao baseDao;
	
	@Resource(name="tagService")
	private TagService tagService;
	
	@Resource(name="agentVisitRecordService")
	private AgentVisitRecordService agentVisitRecordService;
	
	/**
	 * 查询客户意向
	 * @param projectCustomerId
	 * @param projectId
	 * @return
	 */
	public String findCustomerYiXiang(String projectCustomerId,String projectId){
		TagsRelation relation =(TagsRelation) baseDao.loadObject("from TagsRelation where targetId = '"+projectCustomerId+"'");
		String yixiang = "未知";
		if(relation!=null){
			String tags = relation.getTags();
			String[] split = tags.split(",");
			if(split.length>0){
				List<Integer> i = new ArrayList<Integer>();
				for(String s : split){
					i.add(Integer.valueOf(s.trim()));
				}
				TagType loadObject =(TagType) baseDao.loadObject("from TagType where tagTypeName='客户意向' and projectId='"+projectId+"'");
				if(loadObject!=null){
					Integer tagTypeId2 = loadObject.getTagTypeId();
					List<Tag> findByHql = baseDao.findByHql("from Tag where tagTypeId="+tagTypeId2+" and projectId='"+projectId+"' and tagStatus=1");
					if(findByHql.size()>0){
						for(Tag tt:findByHql){
							for(Integer ii : i){
								if(ii.equals(tt.getTagId())){
									yixiang = tt.getTagName();
									break;
								}
							}
								
						}
					}
				}
			
			}
		}
		return yixiang;
	}

	@Override
	public PCustomerInformation findCustomersInformation(String projectCustomerId,String phone,String projectId) {
		ProjectCustomers customer = null;
		if(projectCustomerId!=null){
			customer = (ProjectCustomers) baseDao.loadObject("from ProjectCustomers where projectCustomerId='"+projectCustomerId+"'");
		}
		if(phone!=null){
			customer = (ProjectCustomers) baseDao.loadObject("from ProjectCustomers where projectCustomerPhone='"+phone+"' and projectId='"+projectId+"'");
		}
		if(customer==null){
			customer = new ProjectCustomers();
		}
		if(projectId==null){
			projectId = customer.getProjectId();
		}
		PCustomerInformation pc = new PCustomerInformation();
		//客户名字
		pc.setCustomerName(customer.getProjectCustomerName());
		//案场编码
		pc.setProjectId(projectId);
		//客户编码
		pc.setCustomerId(customer.getProjectCustomerId());
		//客户电话
		pc.setCustomerPhone(customer.getProjectCustomerPhone());
		List<TagType> showRootTagType = tagService.showRootTagType(projectId,0);
		Integer tagTypeId = null;
		for(TagType tagType : showRootTagType){
			if("客户标签".equals(tagType.getTagTypeName())){
				tagTypeId = tagType.getTagTypeId();
			}
		}
		if(tagTypeId!=null){
			//查询客户标签类目下的所有子标签
			List<TagType> showRootTagType2 = tagService.showRootTagType(projectId, tagTypeId);
			//刪除 客戶意向标签
			Iterator<TagType> iterator = showRootTagType2.iterator();
			while(iterator.hasNext()){
				TagType next = iterator.next();
				if("客户意向".equals(next.getTagTypeName())){
					iterator.remove();
				}
			}
			pc.setRootTagTypes(showRootTagType2);
		}
		List<VisitRecords> vrs = baseDao.findByHql("from VisitRecords where customerId='"+projectCustomerId+"' and projectId='"+projectId+"' and visitStatus!=2 order by arriveTime");
		//到访次数
		pc.setComeNum(vrs.size());
		if(vrs.size()>0){
			//第一次到访时间
			pc.setFirstTime(vrs.get(0).getArriveTime());
		}
		//认购状态
		List<ContractRecords> crs = baseDao.findByHql("from ContractRecords where projectCustomerId='"+projectCustomerId+"' and projectId='"+projectId+"' order by applyTime DESC");
		if(crs.size()>0){
			ContractRecords contractRecords = crs.get(0);
			pc.setStatus(contractRecords.getRecordStatus());
		}
		//客户意向
//		TagsRelation relation =(TagsRelation) baseDao.loadObject("from TagsRelation where targetId = '"+projectCustomerId+"'");
//		pc.setYixiang("未知");
//		if(relation!=null){
//			String tags = relation.getTags();
//			String[] split = tags.split(",");
//			if(split.length>0){
//				List<Integer> i = new ArrayList<Integer>();
//				for(String s : split){
//					i.add(Integer.valueOf(s.trim()));
//				}
//				TagType loadObject =(TagType) baseDao.loadObject("from TagType where tagTypeName='客户意向' and projectId='"+projectId+"'");
//				if(loadObject!=null){
//					Integer tagTypeId2 = loadObject.getTagTypeId();
//					List<Tag> findByHql = baseDao.findByHql("from Tag where tagTypeId="+tagTypeId2+" and projectId='"+projectId+"'");
//					if(findByHql.size()>0){
//						for(Tag tt:findByHql){
//							for(Integer ii : i){
//								if(ii.equals(tt.getTagId())){
//									pc.setYixiang(tt.getTagName());
//									break;
//								}
//							}
//								
//						}
//					}
//				}
//			
//			}
//		}else{
//			pc.setYixiang("未知");
//		}
		String yixiang = findCustomerYiXiang(projectCustomerId,projectId);
		pc.setYixiang(yixiang);
		pc.setOwn(customer.getOwnerUserId());
		return pc;
	}

	@Override
	public ProjectCustomers findCustomers(String customerId,String projectId) {
		ProjectCustomers loadObject = (ProjectCustomers)baseDao.loadObject("from ProjectCustomers where projectCustomerId='"+customerId+"' and projectId='"+projectId+"'");
		return loadObject;
	}

	@Override
	public List<TagLib> findAllTagAndCustomerInfo(String customerId, String projectId) {
		List<TagType> showRootTagType = tagService.showRootTagType(projectId,0);
		Integer tagTypeId = null;
		for(TagType tagType : showRootTagType){
			if("客户标签".equals(tagType.getTagTypeName())){
				tagTypeId = tagType.getTagTypeId();
			}
		}
		if(tagTypeId!=null){
			List<TagLib> tagLib = tagService.showTagLib(tagTypeId, projectId, 1, customerId);
			return tagLib;
		}
		return null;
		
	}

	@Override
	public Map<Tag,List<String>> findCustomersList(String ownerUserId) {
		Map<Tag,List<String>> result = new HashMap<>();
		User user = (User)baseDao.loadObject("from User where userId='"+ownerUserId+"'");
		List<ProjectCustomers> projectCustomers = baseDao.findByHql("from ProjectCustomers where ownerUserId='"+ownerUserId+"'");
		String projectId = user.getParentId();
		//根标签
		List<TagType> showRootTagType = tagService.showRootTagType(projectId,0);
		Integer tagTypeId = null;
		for(TagType tagType : showRootTagType){
			if("客户标签".equals(tagType.getTagTypeName())){
				tagTypeId = tagType.getTagTypeId();
			}
		}
		if(tagTypeId!=null){
			List<TagType> showRootTagType2 = tagService.showRootTagType(projectId, tagTypeId);
			for(TagType tagType:showRootTagType2){
				if("客户意向".equals(tagType.getTagTypeName())){
					tagTypeId = tagType.getTagTypeId();
				}
			}
			//获取客户意向下的4个子标签
			List<Tag> tags = baseDao.findByHql("from Tag where tagTypeId="+tagTypeId+" and projectId='"+projectId+"' and tagStatus=1");
			//遍历每个客户
			Tag t1 = null;
			List<String> unknow = new ArrayList<>();
			for(Tag t:tags){
				if("未知".equals(t.getTagName())){
					t1 = t;
				}
				List<String> customerList = new ArrayList<>();
				Iterator<ProjectCustomers> iterator = projectCustomers.iterator();
				while(iterator.hasNext()){
					ProjectCustomers pc = iterator.next();
					List<Integer> ownTags = new ArrayList<>();
					TagsRelation relation =(TagsRelation) baseDao.loadObject("from TagsRelation where targetId = '"+pc.getProjectCustomerId()+"'");
					if(relation!=null){
						String s = relation.getTags();
						if(s!=null){
							String[] split = s.split(",");
							for(String ss:split){
								ownTags.add(Integer.valueOf(ss.trim()));
							}
						}
					}
					if(ownTags.size()>0){
						for(Integer in : ownTags){
							if(in.equals(t.getTagId())){
								customerList.add(pc.getProjectCustomerId());
								break;
							}
						}
					}else{
						iterator.remove();
						unknow.add(pc.getProjectCustomerId());
						
					}
				}
				result.put(t, customerList);
			}
			
			List<String> list = result.get(t1);
			list.addAll(unknow);
			return result;
			
		}
		return null;
		
	}

	@Override
	public List<ProjectCustomers> findCustomerByIds(String[] customerIds) {
		List<ProjectCustomers> clist = new ArrayList<>();
		for(String customerId : customerIds){
			ProjectCustomers pc = (ProjectCustomers)baseDao.loadObject("from ProjectCustomers where projectCustomerId='"+customerId+"' ");
			if(pc!=null){
				clist.add(pc);
			}
		}
		return clist;
	}

	@Override
	public ProjectCustomers updateCustomerAndTags(ProjectCustomers pc, Integer[] tagss) {
		ProjectCustomers newpc = (ProjectCustomers)baseDao.loadObject("from ProjectCustomers where projectCustomerId='"+pc.getProjectCustomerId()+"' ");
		newpc.setProjectCustomerName(pc.getProjectCustomerName());
		newpc.setProjectCustomerPhone(pc.getProjectCustomerPhone());
		newpc.setSex(pc.getSex());
		//newpc.setIdCard(idCard);身份证
		baseDao.saveOrUpdate(newpc);
		tagService.addTagRelation(tagss, pc.getProjectCustomerId());
		return newpc;
	}

	@Override
	public ProjectCustomers findCustomerByPhone(String phone,String projectId) {
		ProjectCustomers pc = (ProjectCustomers)baseDao.loadObject("from ProjectCustomers where projectCustomerPhone='"+phone+"' and projectId='"+projectId+"'");
		return pc;
	}

	@Override
	public VisitRecords findVisit(Integer visitNo) {
		VisitRecords vr = (VisitRecords) baseDao.loadById(VisitRecords.class, visitNo);
		return vr;
	}

	@Override
	public List<ProjectCustomers> findCustomerByTagId(String tagName, String userId,String projectId) {
		List<ProjectCustomers> pcList = baseDao.findByHql("from ProjectCustomers where ownerUserId='"+userId+"' ");
		List<ProjectCustomers> result = new ArrayList<>();
		if(pcList.size()>0){
			for(ProjectCustomers pc : pcList){
				String yixiang = findCustomerYiXiang(pc.getProjectCustomerId(), projectId);
				if(yixiang.equals(tagName)){
					result.add(pc);
				}
				
			}
		}
		return result;
	}

	@Override
	public void addVisitTag(String projectCustomerId, Integer visitNo) {
		// TODO Auto-generated method stub
		//拉取客户最新的标签添加到到访表
		TagsRelation ts = (TagsRelation)baseDao.loadObject("from TagsRelation where targetId='"+projectCustomerId+"'");
		if(ts!=null){
			VisitRecords vr = (VisitRecords) baseDao.loadById(VisitRecords.class, visitNo);
			vr.setTags(ts.getTags());
		}
		
	}
	
	public void addVisitTag(Integer visitNo,Integer[] tags){
		//保存新客户给到访表添加标签
		if(tags!=null&&tags.length!=0){
			VisitRecords vr = (VisitRecords) baseDao.loadById(VisitRecords.class, visitNo);
			String tags1 = Arrays.toString(tags);
			String substring = tags1.substring(1, tags1.length()-1);
			vr.setTags(substring);
			baseDao.saveOrUpdate(vr);
		}
	}

	@Override
	public void updateVistInfo(ProjectCustomers pc, Integer visitNo) {
		VisitRecords vr = (VisitRecords) baseDao.loadById(VisitRecords.class, visitNo);
		vr.setPhone(pc.getProjectCustomerPhone());
		vr.setCustomerName(pc.getProjectCustomerName());
		baseDao.saveOrUpdate(vr);
	}



}
