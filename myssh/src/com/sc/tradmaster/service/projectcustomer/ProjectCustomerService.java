package com.sc.tradmaster.service.projectcustomer;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.utils.Page;

public interface ProjectCustomerService {

	/**
	 * list当前职业顾问管理案场下的客户
	 * @param user
	 * @return
	 */
	void findProCustomersByUser(User user,String selectValue,Page page);

	/**
	 * 客户管理-设置归属置业顾问 动态菜单
	 * @param parentId
	 * @return
	 */
	List<Map<String, String>> findAgentsToMenu(String parentId);

	/**
	 * 更新案场客户所属置业顾问
	 * @param proCursId
	 * @param agentId
	 */
	void addOrUpdateProCustomerownerAgent(String[] proCursId, String agentId);

	/**
	 * 中介门店 客户管理list
	 * @param user
	 * @param selectValue
	 * @param page
	 */
	void findShopCustomersByUser(User user, String selectValue, Page page);
	
	/**
	 * 根据用户的phone获取用户的到访记录和认购记录
	 * @param phone
	 * @return
	 */
	public Map<String, Object> findCustomerCAndVInfo(String phone, String projectId);
	
	
	/**
	 * 根据用户的phone查找用户
	 * @param phone
	 * @return
	 */
	public ProjectCustomers findProjectCustomersByPhone(String phone);	
	
	
	/**
	 * 
	 * 根据用户id查找客户的动态
	 * @param customerId
	 * @param customerType 客户类型（value：projectCustomerId || shopCustomerId）
	 * @return
	 */
	public List<Mydynamic> findCustomerDynamicByCustomerId(String customerId, String customerType);
	
	
	/**
	 * 导入客户信息-excell
	 * @param file
	 * @param projectId
	 * @return
	 */
	public Map<String, Object> addCustomerExcell(MultipartFile file, String projectId);
}
