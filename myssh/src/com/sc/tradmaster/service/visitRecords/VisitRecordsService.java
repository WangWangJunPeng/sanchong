package com.sc.tradmaster.service.visitRecords;

import java.text.ParseException;
import java.util.List;

import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.visitRecords.impl.visitRecordDTO.ProjectCustomersDTO;
import com.sc.tradmaster.service.visitRecords.impl.visitRecordDTO.VisitCustomerDTO;
import com.sc.tradmaster.utils.Page;

public interface VisitRecordsService {

	/**
	 * 到访客户表
	 */	
	List<VisitCustomerDTO> findVisitCustomer(User user, String projectId, String startTime, String endTime)throws ParseException;
	
	/**
	 * 下载到访客户表
	 */	
	void findVisit(String projectId,String arriveTime,Page p)throws ParseException;
	

	/**
	 * 置业顾问到访客户表
	 */
	List<ProjectCustomersDTO> findSaleVisitList(User user);
	
}
