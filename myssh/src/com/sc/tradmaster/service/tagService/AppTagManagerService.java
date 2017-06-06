package com.sc.tradmaster.service.tagService;

import java.util.List;
import java.util.Map;

import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.Tag;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.service.tagService.impl.dto.PCustomerInformation;
import com.sc.tradmaster.service.tagService.impl.dto.TagLib;

public interface AppTagManagerService {
	/**
	 * 根据号码或者id查询客户
	 * @param customerId
	 * @return
	 */
	PCustomerInformation findCustomersInformation(String customerId,String phone,String projectId);


	ProjectCustomers findCustomers(String customerId, String projectId);

	/**
	 * 编辑客户信息、给客户添加标签
	 * @param customerId
	 * @param projectId
	 */
	List<TagLib> findAllTagAndCustomerInfo(String customerId, String projectId);

	/**
	 * 获取客户列表
	 * @param ownerUserId
	 * @return
	 */
	Map<Tag,List<String>> findCustomersList(String ownerUserId);

	/**
	 * 分组列表
	 * @param customerIds
	 * @return
	 */
	List<ProjectCustomers> findCustomerByIds(String[] customerIds);
	/**
	 * 查询意向
	 * @param projectCustomerId
	 * @param projectId
	 * @return
	 */
	String findCustomerYiXiang(String projectCustomerId,String projectId);


	ProjectCustomers updateCustomerAndTags(ProjectCustomers pc, Integer[] tagss);

	/**
	 * 根据号码查询案场客户
	 * @param phone
	 * @return
	 */
	ProjectCustomers findCustomerByPhone(String phone,String projectId);

	/**
	 * 查询到访记录
	 * @param visitNo
	 * @return
	 */
	VisitRecords findVisit(Integer visitNo);


	List<ProjectCustomers> findCustomerByTagId(String tagName, String userId,String projectId);
	

	/**
	 * 给到访记录表添加Tags
	 * @param projectCustomerId
	 * @param visitNo
	 */
	void addVisitTag(String projectCustomerId, Integer visitNo);
	/**
	 * 新用户保存时给记录表添加Tgas
	 * @param visitNo
	 * @param tags
	 */
	void addVisitTag(Integer visitNo,Integer[]tags);
	/**
	 * 更新到访记录表的号码，名字
	 * @param pc
	 * @param visitNo
	 */
	void updateVistInfo(ProjectCustomers pc, Integer visitNo);
	

}
