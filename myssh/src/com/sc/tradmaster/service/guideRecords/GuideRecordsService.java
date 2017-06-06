package com.sc.tradmaster.service.guideRecords;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.User;

public interface GuideRecordsService {
	/**
	 * 增加备案记录
	 */	
	List addGuideRecords(User user, String customerName,String phone,String[] projectId)throws ParseException;
	
	
	/**
	 * 备案客户表(申请状态的)
	 */	
	List findGuideRecordsList(User user, String projectId,String startTime, String endTime)throws ParseException;
	
	/**
 	 * 将到期的客户
	 */	
	Map<String, Object> findNearOver(User user, String projectId,String phone)throws ParseException;
	
}
