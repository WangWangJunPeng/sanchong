package com.sc.tradmaster.service.agent;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.SignRecords;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.service.agent.impl.visitDTO.Customer;
import com.sc.tradmaster.service.agent.impl.visitDTO.ProjectCustomerDTO;

/**
 * 2017-02-03
 * @author grl
 *
 */
public interface AgentVisitRecordService {

	/**
	 * 通过用户信息获取到访客户信息
	 * @param user
	 * @return
	 */
	List<VisitRecords> findVisitInfoByUser(User user,String data);

	/**
	 * 通过手机号获取客户的信息
	 * @param phone
	 * @return
	 */
	ProjectCustomerDTO findCustomerInfoByPhone(User u ,String phone);

	/**
	 * 职业顾问添加客户信息
	 * @param user
	 * @param phone
	 * @throws ParseException 
	 */
	ProjectCustomers addOrUpdateAgentInsertCustomerInfo(User user,String cName, String phone,String desc ,Integer visitNo) throws ParseException;
	
	/**
	 * 通过projectId获得当前案场的所有置业顾问列表
	 * @return
	 */
	List findAgents(String projectId);
	/**
	 * 获取签到签退记录
	 * @param user
	 * @param projectId
	 * @param checkTime
	 * @return 
	 */
	List<SignRecords> findAllSignAndOutRecordList(User user, String projectId, String checkTime);

	/**
	 * 上传到访记录，更新到访记录表
	 * @param excel
	 * @throws IOException 
	 * @throws Exception 
	 */
	Integer addOrUpdateVisitReocrdFromExcel(VisitRecords vrs);
	
	/**
	 * 获取客户到访记录
	 */
	Customer findCustomerVisitRecord(String projectId, String phone);

	/**
	 * 签到签退
	 */
	void addSignInAndUpdateSignOut(User user,Integer type,String projectId,String userId,String time);

	/**
	 * 更新到访信息
	 * @param user
	 * @param visitNo
	 * @param phone
	 */
	void addorUpdataVistInfo(User user, Integer visitNo, String phone);
}
