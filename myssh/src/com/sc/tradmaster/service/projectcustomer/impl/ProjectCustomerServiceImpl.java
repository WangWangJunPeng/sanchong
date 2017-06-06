package com.sc.tradmaster.service.projectcustomer.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.ShopCustomers;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.projectcustomer.ProjectCustomerService;
import com.sc.tradmaster.service.projectcustomer.impl.dto.CustomerManager;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.ExcelHelper;
import com.sc.tradmaster.utils.JxlExcelHelper;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.StringUtil;
import com.sc.tradmaster.utils.SysContent;

@Service("projectCustomerService")
public class ProjectCustomerServiceImpl implements ProjectCustomerService {

	@Resource(name = "baseDao")
	private BaseDao baseDao;
	
	@Override
	public void findProCustomersByUser(User user,String selectValue,Page page) {
		List list = new ArrayList<>();
		String hql = "from ProjectCustomers as model where model.projectId = '"+user.getParentId()+"' ";
		if(selectValue!=null && !selectValue.equals("")){
			hql+="and model.projectCustomerName like '%" +selectValue+"%' or model.projectCustomerPhone like '%" +selectValue+"%'";
		}
		List<ProjectCustomers> pcList = baseDao.findByHql(hql,page.getStart(),page.getLimit());
		for(ProjectCustomers  pc :pcList ){
			CustomerManager cm = new CustomerManager();
			User u = null;
			if(pc.getOwnerUserId()!=null && !pc.getOwnerUserId().equals("")){
				u = (User) baseDao.loadById(User.class, pc.getOwnerUserId());
			}
			String crHql = "from ContractRecords where projectId = '"+user.getParentId()+"' and projectCustomerId = '"+pc.getProjectCustomerId()+"'";
			ContractRecords crs = (ContractRecords) baseDao.loadObject(crHql);
			CustomerManager newCm = cm.createCusMan(pc, u,crs);
			list.add(newCm);
		}
		String cHql = "select count(*) "+hql;
		int total = baseDao.countQuery(cHql);
		page.setRoot(list);
		page.setTotal(total);
	}

	@Override
	public List<Map<String, String>> findAgentsToMenu(String parentId) {
		String hql = "from User where parentId = '"+parentId+"'";
		List<User> agList = baseDao.findByHql(hql);
		List<Map<String,String>> listMap = new ArrayList<>();
		for(User ag : agList){
			Map<String,String> agMenu = new HashMap<>();
			agMenu.put("agId", ag.getUserId());
			agMenu.put("agName",ag.getUserCaption());
			listMap.add(agMenu);
		}
		return listMap;
	}

	@Override
	public void addOrUpdateProCustomerownerAgent(String[] proCursId, String agentId) {
		for(String cId : proCursId){
			ProjectCustomers pc =  (ProjectCustomers) baseDao.loadById(ProjectCustomers.class, cId);
			pc.setOwnerUserId(agentId);
			pc.setOwnerStartTime(DateTime.toString1(new Date()));
			baseDao.saveOrUpdate(pc);
		}
	}

	@Override
	public void findShopCustomersByUser(User user, String selectValue, Page page) {
		List cmList = new ArrayList<>();
		int total = 0;
		if(user!=null && user.getParentId()!=null && !user.getParentId().equals("")){
			String hql = "from ShopCustomers as model where model.shopId = " + Integer.parseInt(user.getParentId());
			if(selectValue!=null && !selectValue.equals("")){
				hql+="and model.shopCustomerName like '%" +selectValue+"%' or model.shopCustomerPhone like '%" +selectValue+"%'";
			}
			List<ShopCustomers> list = baseDao.findByHql(hql,page.getStart(),page.getLimit());
			for(ShopCustomers sc : list){
				User u = (User) baseDao.loadById(User.class, sc.getUserId());
				String cGRSHql = "select count(*) from GuideRecords where shopCustomerId = '"+sc.getShopCustomerId()+"'";
				String cDealHql = "select count(*) from GuideRecords where shopCustomerId = '"+sc.getShopCustomerId()+"' and isDeal = 1";
				int floorCounts = baseDao.countQuery(cGRSHql);//备案楼盘数
				int dealCounts = baseDao.countQuery(cDealHql);//已成交数
				CustomerManager cm = new CustomerManager();
				CustomerManager cmObj = cm.createCusManObj(sc,u);
				cmObj.setRecords(floorCounts);
				cmObj.setAlreadyDeal(dealCounts);
				cmList.add(cmObj);
			}
			String cHql = "select count(*) "+hql;
			total = baseDao.countQuery(cHql);
		}
		page.setRoot(cmList);
		page.setTotal(total);
	}
	
	@Override
	public Map<String, Object> findCustomerCAndVInfo(String phone, String projectId) {
		
		Map<String, Object> map = new HashMap<>();
		
		//获取认购记录
		String CHql = "from ContractRecords where customerPhone = '" + phone + "' and projectId ='" + projectId + "'";
		List<ContractRecords> conList = baseDao.findByHql(CHql);
		map.put("contractRecords", conList);
		
		//获取到访记录
		String VHql = "select count(*) from VisitRecords where phone ='" + phone + "'";
		Integer visitCount = baseDao.countQuery(VHql);
		map.put("visitCount", visitCount);
		
		return map;
	}
	
	@Override
	public ProjectCustomers findProjectCustomersByPhone(String phone) {
		
		String hql = "select * from ProjectCustomers where phone projectCustomerPhone = '" + phone + "'";
		ProjectCustomers customer = (ProjectCustomers) baseDao.loadObject(hql);
		return customer;
	}
	
	@Override
	public List<Mydynamic> findCustomerDynamicByCustomerId(String customerId, String customerType) {
		String dynamicHql = "from Mydynamic where " + customerType + " = '" + customerId + "' order by asc";
		List<Mydynamic> list = baseDao.findByHql(dynamicHql);
		return list;
	}
	
	@Override
	public Map<String, Object> addCustomerExcell(MultipartFile file, String projectId) {
		
		Map<String, Object> map = new HashMap<>();
		
		//更新的用户
		List<ProjectCustomers> updateList = new ArrayList<>();
		//新添的用户
		List<ProjectCustomers> newList = new ArrayList<>();
		Integer addFlag = 0;
		Integer updateFlag = 0;
		try {
			
			String sepa = File.separator;
			String realPath = SysContent.getSession().getServletContext().getRealPath("/static/upload");
			String fileRename = SysContent.getFileRename(file.getOriginalFilename());
			String fileRpn = realPath + sepa + fileRename;
			File importFile = new File(fileRpn);
			FileUtils.copyInputStreamToFile(file.getInputStream(), importFile);
			ExcelHelper exh = JxlExcelHelper.getInstance(importFile);
			String[] fieldNames = new String[]{"lastTime", "projectCustomerName", "projectCustomerPhone", "createUserId"};
			List<ProjectCustomers> list = exh.readExcel(ProjectCustomers.class, fieldNames, true);
			
			
			/*如果导入的客户是同一个人那么就不重复导入 而是更新*/
			for(ProjectCustomers cus : list){
				if(!StringUtils.isEmpty(cus.getProjectCustomerName()) && !StringUtils.isEmpty(cus.getProjectCustomerPhone())){
					
					//首先查找数据库是否已经存在这个客户，并且最后到达案场的时间必须大于之前到达的时间
					String customerHql = "from ProjectCustomers where projectCustomerName = '" + cus.getProjectCustomerName() + "' and projectCustomerPhone = '" + cus.getProjectCustomerPhone() + "' and projectId = '" + projectId + "'";
					ProjectCustomers customer = (ProjectCustomers) baseDao.loadObject(customerHql);
					if(customer != null){ // is not null prove this customer is exist
						
						//查找当前客户的创建人
						User user = (User) baseDao.loadObject("from User where parentId = '" + projectId + "' and userCaption = '" + cus.getCreateUserId() + "'");
						String lastTime = DateUtil.format(DateUtil.parse(cus.getLastTime(),DateUtil.PATTERN_GRACE_SIMPLE));
						Date lastDate = DateUtil.parse(lastTime);
						Date oldLastTime = DateUtil.parse(customer.getLastTime());
						if(oldLastTime.getTime() < lastDate.getTime()){
							
							customer.setLastTime(DateUtil.format(DateUtil.parse(cus.getLastTime(),DateUtil.PATTERN_GRACE_SIMPLE)));
							baseDao.saveOrUpdate(customer);
							updateFlag += 1;
							
							updateList.add(customer);
						}
						
						
					}else{// is null prove this customer is new customer
						
						String name = cus.getCreateUserId();
						
						User user = (User) baseDao.loadObject("from User where parentId = '" + projectId + "' and userCaption = '" + cus.getCreateUserId() + "'");
						cus.setProjectCustomerId(SysContent.uuid());
						cus.setCreateUserId(user.getUserId());
						cus.setOwnerUserId(user.getUserId());
						cus.setCreateTime(DateUtil.format(new Date()));
						cus.setProjectId(projectId);
						cus.setLastTime(DateUtil.format(DateUtil.parse(cus.getLastTime(),DateUtil.PATTERN_GRACE_SIMPLE)));
						cus.setOwnerStartTime(DateUtil.format(new Date()));
						baseDao.save(cus);
						addFlag += 1;
						
						newList.add(cus);
					}
				}
				
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("updateCustomer", updateList);
		map.put("newCustomer", newList);
			return map;
	}


}
