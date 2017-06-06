package com.sc.tradmaster.service.projectGuide;

import java.util.List;
import java.util.Map;

import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.projectGuide.impl.dto.ProjectHouse;
import com.sc.tradmaster.utils.Page;

public interface ProjectGuideService {
	
	/**
	 * 添加或更新带看业务定义
	 * @param pg
	 * @param user
	 */
	void addOrUpdate(ProjectGuide pg, User user);
	
	/**
	 * 通过id获取该带看定义
	 * @param pgId
	 * @return
	 */
	ProjectGuide findById(String pgId);
	
	/**
	 * 通过条件查询(案场)房源信息(中介门店管理后台)
	 * @param project
	 * @return 
	 */	
	List findByProperty(String[] selectNames, String[] selectValue,Page page);
	
	/**
	 * 获取门店批准时间
	 * @param user
	 * @return
	 */
	Map<String, Object> findShopApproveTime(User user);
}
