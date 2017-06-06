package com.sc.tradmaster.service.guideRecords.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.sl.draw.geom.Guide;
import org.springframework.stereotype.Service;

import com.sc.tradmaster.bean.Favorites;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.ShopCustomers;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.guideRecords.GuideRecordsService;
import com.sc.tradmaster.service.guideRecords.impl.dto.GuideRecordsDTO;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.SysContent;

import net.sf.json.JSONObject;
@Service("guideRecordsService")
public class GuideRecordsServiceImpl implements GuideRecordsService{

	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	/**
	 * 增加备案记录
	 * @throws ParseException 
	 */	
	@Override
	public List addGuideRecords(User user,String customerName, String phone,String[] projectId) throws ParseException {
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String userId = user.getUserId();
		
		String scHQL = "from ShopCustomers where shopCustomerPhone = '"+phone+"'";
		ShopCustomers sc = (ShopCustomers) baseDao.loadObject(scHQL);
		List<GuideRecords> grList2 = new ArrayList<>();
		//存在
		if (sc!= null && !"".equals(sc)){
			//new list
			List<Favorites> fList = new ArrayList<>();
			if (projectId.length > 0) {
				for (int i = 0; i < projectId.length; i++) {
					String fHQL = "from Favorites f where f.projectId = '" + projectId[i] + "'";
					Favorites f = (Favorites) baseDao.loadObject(fHQL);
					fList.add(f);
				}
			}
			
			for (Favorites f : fList) {
				//根据收藏夹一条记录查到一个对应的project对象
				String pId = f.getProjectId();
				Project p = (Project) baseDao.loadById(Project.class, pId);
				
				//new 一个带看备忘记录
				GuideRecords gr = new GuideRecords();
				//根据项目id查带看表
				String pgHQL = "from ProjectGuide pg where pg.projectId = '" + pId + "'";
				ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);
				//new  动态
				Mydynamic md = new Mydynamic();
				md.setUserId(user.getUserId());
				md.setUserPhone(user.getPhone());
				md.setCustomerPhone(phone);
				md.setCustomerName(customerName);
				md.setIsRead(0);
				md.setCreatTime(DateTime.toString1(new Date()));
				md.setProjectId(p.getProjectId());
				md.setProjectName(p.getProjectName());
				md.setIsDelete("0");
				
				if (pg != null && !"".equals(pg)){
					//判断该案场是否支持带看
					if (pg.getIsDaiKan()!=1) {
						gr.setShopCustomerId(SysContent.uuid());
						//不支持带看,备案状态为否决
						gr.setApplyStatus(3);
						//备案失败
						gr.setFail("该案场不支持备案");
						
						md.setDynamicInfo("客户"+customerName+"备案失败!");
						md.setDynamicStatus("备案失败");
						baseDao.save(md);
					} else {
						//该案场支持带看,,,查该案场是否存在该客户
						String pcHQL = "from ProjectCustomers pc where pc.projectCustomerPhone = '" +phone+"' and pc.projectId = '"+ pId+"'";
						ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(pcHQL);
						//案场存在该客户
						if (pc != null && !"".equals(pc)) {
							//客户最后到达该案场的时间
							String lastTime = pc.getLastTime();
							Date date = sdf.parse(lastTime);
							Date daoqiTime = DateUtil.rollMonth(date, Integer.parseInt(pg.getCustormerProtectDays()));
							
							//现在时间-到期时间
							long i = d.getTime() - daoqiTime.getTime();
							
							if (i<=0) {
								gr.setShopCustomerId(sc.getShopCustomerId());
								gr.setApplyStatus(3);
								//判断时间,在保护期内,备案失败
								gr.setFail("该客户为该案场客户且在保护期内");
								
								md.setDynamicInfo("客户"+customerName+"备案失败!");
								md.setDynamicStatus("备案失败");
								baseDao.save(md);
							}else {
								List<GuideRecords> grList = baseDao.findByHql("from GuideRecords where projectId = '"+p.getProjectId()+"' and userId = '"+user.getUserId()+"' and customerPhone = '"+phone+"'  and (applyStatus = 1 or applyStatus = 0) order by applyTime DESC");
								if (grList.size()>0){
									for (int k = 0; k < grList.size(); k++) {
										if (k == 0 ){
											String applyTime = grList.get(k).getApplyTime();
											Date applyTimeDate = sdf.parse(applyTime);
											long j = d.getTime() - applyTimeDate.getTime();
											int youxiaoDays = (int) (j/1000/60/60/24);
											Integer validDays = pg.getValidDays();
											if (youxiaoDays <= validDays) {
												gr.setShopCustomerId(sc.getShopCustomerId());
												gr.setApplyStatus(3);
												if (sc.getUserId().equals(user.getUserId())){
													gr.setFail("您之前备案过该客户");
													
													md.setDynamicInfo("客户"+customerName+"备案失败!");
													md.setDynamicStatus("备案失败");
													baseDao.save(md);
													
												}else {
													//该客户已被其他中介备案且在备案有效天数内
													gr.setFail("该客户已被其他中介备案");
													
													md.setDynamicInfo("客户"+customerName+"备案失败!");
													md.setDynamicStatus("备案失败");
													baseDao.save(md);
												}
											}else {
												gr.setShopCustomerId(sc.getShopCustomerId());
												//备案成功....(为申请)
												gr.setApplyStatus(0);
												
												md.setDynamicInfo("客户"+customerName+"备案成功!");
												md.setDynamicStatus("备案成功");
												baseDao.save(md);
											}
										}
									}
								}else {
									gr.setShopCustomerId(sc.getShopCustomerId());
									//备案成功....(为申请)
									gr.setApplyStatus(0);
									
									md.setDynamicInfo("客户"+customerName+"备案成功!");
									md.setDynamicStatus("备案成功");
									baseDao.save(md);
								}
							}
						}else {
							List<GuideRecords> grList = baseDao.findByHql("from GuideRecords where projectId = '"+p.getProjectId()+"' and customerPhone = '"+phone+"' and (applyStatus = 1 or applyStatus = 0)  order by applyTime DESC");
							if (grList.size()>0){
								for (int k = 0; k < grList.size(); k++) {
									if (k == 0 ){
										String applyTime = grList.get(k).getApplyTime();
										Date applyTimeDate = sdf.parse(applyTime);
										long j = d.getTime() - applyTimeDate.getTime();
										int youxiaoDays = (int) (j/1000/60/60/24);
										Integer validDays = pg.getValidDays();
										if (youxiaoDays <= validDays) {
											gr.setShopCustomerId(sc.getShopCustomerId());
											gr.setApplyStatus(3);
											if (sc.getUserId().equals(user.getUserId())){
												gr.setFail("您之前备案过该客户");
												
												md.setDynamicInfo("客户"+customerName+"备案失败!");
												md.setDynamicStatus("备案失败");
												baseDao.save(md);
												
											}else {
												//该客户已被其他中介备案且在备案有效天数内
												gr.setFail("该客户已被其他中介备案");
												
												md.setDynamicInfo("客户"+customerName+"备案失败!");
												md.setDynamicStatus("备案失败");
												baseDao.save(md);
											}
										}else {
											gr.setShopCustomerId(sc.getShopCustomerId());
											//备案成功....(为申请)
											gr.setApplyStatus(0);
											
											md.setDynamicInfo("客户"+customerName+"备案成功!");
											md.setDynamicStatus("备案成功");
											baseDao.save(md);
										}
									}
								}
							}else {
								gr.setShopCustomerId(sc.getShopCustomerId());
								//备案成功....(为申请)
								gr.setApplyStatus(0);
								
								md.setDynamicInfo("客户"+customerName+"备案成功!");
								md.setDynamicStatus("备案成功");
								baseDao.save(md);
							}
						}
					}
				}
				gr.setApplyTime(DateTime.toString1(new Date()));
				gr.setUserId(userId);
				gr.setProjectId(pId);
				gr.setCustomerName(customerName);
				gr.setCustomerPhone(phone);
				//带看业务定义表加到rules
				JSONObject json = JSONObject.fromObject(pg);
				gr.setRules(json.toString());
				//保存更新备案记录表
				baseDao.saveOrUpdate(gr);
				
				grList2.add(gr);
			}
			
		//不存在,新new一个中介客户
		}else {
			ShopCustomers shopCustomers = new ShopCustomers();
			shopCustomers.setCreateTime(DateTime.toString1(new Date()));
			shopCustomers.setShopCustomerId(SysContent.uuid());
			shopCustomers.setShopCustomerName(customerName);
			shopCustomers.setShopCustomerPhone(phone);
			shopCustomers.setShopId(Integer.parseInt(user.getParentId()));
			shopCustomers.setUserId(user.getUserId());
			baseDao.saveOrUpdate(shopCustomers);
			
			
			//new list
			List<Favorites> fList = new ArrayList<>();
			
			if (projectId.length > 0) {
				for (int i = 0; i < projectId.length; i++) {
					String fHQL = "from Favorites f where f.projectId = '" + projectId[i] + "'";
					Favorites f = (Favorites) baseDao.loadObject(fHQL);
					fList.add(f);
				}
			}
			if (fList.size()>0 && fList != null ){
				for (Favorites f : fList) {
					//new 一个带看备忘记录
					GuideRecords gr = new GuideRecords();
					//根据收藏夹一条记录查到一个对应的project对象
					String pId = f.getProjectId();
					Project p = (Project) baseDao.loadById(Project.class, pId);
					//根据项目id查带看表
					String pgHQL = "from ProjectGuide pg where pg.projectId = '" + pId + "'";
					ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);
					//new  动态
					Mydynamic md = new Mydynamic();
					md.setUserId(user.getUserId());
					md.setUserPhone(user.getPhone());
					md.setCustomerPhone(phone);
					md.setCustomerName(customerName);
					md.setIsRead(0);
					md.setCreatTime(DateTime.toString1(new Date()));
					md.setProjectId(p.getProjectId());
					md.setProjectName(p.getProjectName());
					md.setIsDelete("0");
					
					if (pg != null && !"".equals(pg)){
						//判断该案场是否支持带看
						if (pg.getIsDaiKan()!=1) {
							gr.setShopCustomerId(SysContent.uuid());
							//不支持带看,备案状态为否决
							gr.setApplyStatus(3);
							//备案失败
							gr.setFail("该案场不支持备案");
							
							md.setDynamicInfo("客户"+customerName+"备案失败!");
							md.setDynamicStatus("备案失败");
							baseDao.save(md);
						} else {
							//该案场支持带看,,,查该案场是否存在该客户
							String pcHQL = "from ProjectCustomers pc where pc.projectCustomerPhone = '" +phone+"' and pc.projectId = '"+ pId+"'";
							ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(pcHQL);
							//案场存在该客户
							if (pc != null && !"".equals(pc)) {
								//客户最后到达该案场的时间
								String lastTime = pc.getLastTime();
								Date date = sdf.parse(lastTime);
								Date daoqiTime = DateUtil.rollMonth(date, Integer.parseInt(pg.getCustormerProtectDays()));
								
								//现在时间-到期时间
								long i = d.getTime() - daoqiTime.getTime();
								if (i<=0) {
									gr.setShopCustomerId(shopCustomers.getShopCustomerId());
									gr.setApplyStatus(3);
									//判断时间,在保护期内,备案失败
									gr.setFail("该客户为该案场客户且在保护期内");
									
									md.setDynamicInfo("客户"+customerName+"备案失败!");
									md.setDynamicStatus("备案失败");
									baseDao.save(md);
								}else {
									//超出保护器,备案成功
									gr.setShopCustomerId(shopCustomers.getShopCustomerId());
									//备案成功
									gr.setApplyStatus(0);
									
									md.setDynamicInfo("客户 "+customerName+"备案成功!");
									md.setDynamicStatus("备案成功");
									baseDao.save(md);
								}
							}else {
								//该案场不存在该客户,备案成功
								gr.setShopCustomerId(shopCustomers.getShopCustomerId());
								//备案成功
								gr.setApplyStatus(0);
								
								md.setDynamicInfo("客户"+customerName+"备案成功!");
								md.setDynamicStatus("备案成功");
								baseDao.save(md);
							}
						}
					}
					gr.setApplyTime(DateTime.toString1(new Date()));
					gr.setUserId(userId);
					gr.setProjectId(pId);
					gr.setCustomerName(customerName);
					gr.setCustomerPhone(phone);
					//带看业务定义表加到rules
					JSONObject json = JSONObject.fromObject(pg);
					gr.setRules(json.toString());
					//保存更新备案记录表
					baseDao.saveOrUpdate(gr);
					
					grList2.add(gr);
				}
			}
		}

		List<GuideRecordsDTO> grdtoList = new ArrayList<>();
		if (grList2.size()>0 && grList2 != null){
			for (GuideRecords guideRecords : grList2) {
				GuideRecordsDTO grdto = new GuideRecordsDTO();
				String pHQL = "from Project p where p.projectId = '" + guideRecords.getProjectId() + "'";
				Project p = (Project) baseDao.loadObject(pHQL);
				String pgHQL = "from ProjectGuide pg where pg.projectId = '" + guideRecords.getProjectId() + "'";
				ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);
				
				grdto.setProjectName(p.getProjectName());
				if (guideRecords.getApplyStatus() == 0){
					if (guideRecords.getApplyTime() != null && !"".equals(guideRecords.getApplyTime())){
						Date date2 = sdf.parse(guideRecords.getApplyTime());
						Integer validDays = pg.getValidDays();
						Date jiezhiTime = DateUtil.rollDay(date2, validDays);
						String time = sdf.format(jiezhiTime);
						grdto.setApplyStatus(guideRecords.getApplyStatus());
						grdto.setValidity(time);
					}
				}else if (guideRecords.getApplyStatus() == 3){
					
					if (guideRecords.getFail() != null && !"".equals(guideRecords.getFail())){
						grdto.setFail(guideRecords.getFail());
						grdto.setApplyStatus(guideRecords.getApplyStatus());
					}
				}
				grdtoList.add(grdto);
			}
		}
		return grdtoList;
	}

	/**
	 * 已备案客户表(申请状态的)
	 * @throws ParseException 
	 */	
	@Override
	public List findGuideRecordsList(User user, String projectId,String startTime, String endTime) throws ParseException {
		String userId = user.getUserId();
		String hql = "from GuideRecords gr where gr.applyStatus = 0 and gr.userId = '" + userId + "' and gr.projectId = '" + projectId + "' order by applyTime DESC";
		if (startTime != null && !"".equals(startTime)) {
			hql += " and gr.applyTime >= '" + startTime + "'";
		}
		if (endTime != null && !"".equals(endTime)) {
			hql += " and gr.applyTime <= '" + endTime + "'";
		}
		List<GuideRecords> grList = baseDao.findByHql(hql);
		List<GuideRecordsDTO> grdtoList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		for (GuideRecords gr : grList) {
			GuideRecordsDTO grdto = new GuideRecordsDTO();
			grdto.setApplyStatus(gr.getApplyStatus());
			grdto.setApplyTime(gr.getApplyTime());
//			String customerName = gr.getCustomerName().substring(0, 1);
			grdto.setCustomerName(gr.getCustomerName());
			grdto.setCustomerPhone(gr.getCustomerPhone());
			String pgHQL = "from ProjectGuide where projectId = '" + projectId+"' ";
			ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);
			//有效天数
			Integer validDays = pg.getValidDays();
			String applyTime = gr.getApplyTime();
			//到期时间
			String daoqiTime = DateTime.getNewDay(gr.getApplyTime(), pg.getValidDays());
			String nowTime = DateTime.toString1(new Date());
			int remainingDays = DateUtil.getOffsetDays(sdf.parse(nowTime),sdf.parse(daoqiTime));
			if(remainingDays >0){
				grdto.setDays(remainingDays);
			}else {
				grdto.setDays(0);
			}
			grdtoList.add(grdto);
		}
		return grdtoList;
	}

	/**
 	 * 将到期的客户
	 * @throws ParseException 
	 */	
	@Override
	public Map<String, Object> findNearOver(User user, String projectId,String phone) throws ParseException {
		String userId = user.getUserId();
		Map<String, Object> map = new HashMap<>();
		Project project = (Project) baseDao.loadById(Project.class, projectId);
		//案场名称
		map.put("projectName", project.getProjectName());
		String pgHQL = "from ProjectGuide pg where pg.projectId = '" + projectId + "'";
		ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);
		int validDays = pg.getValidDays();
		//备案有效天数
		map.put("validDays", validDays);
		//该案场的我的备案记录
		String hql = "from GuideRecords gr where gr.userId = '" + userId + "' and gr.projectId = '" + projectId + "' and gr.applyStatus = 0 order by applyTime DESC";
		if (phone != null && !"".equals(phone)){
			hql += " and customerPhone = '"+ phone + "' ";
		}
		List<GuideRecords> grList = baseDao.findByHql(hql);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<GuideRecords> grList2 = new ArrayList<>();
		for (GuideRecords gr : grList) {
			//当前时间
			Date date = new Date();
			//备案申请时间
			String applyTime = gr.getApplyTime();
			Date d = sdf.parse(applyTime);
			//申请时间+有效天数=到期时间
			String time = sdf.format(new Date(d.getTime() + validDays*24*60*60*1000));
			Date date2 = sdf.parse(time);
			long j = date.getTime() - date2.getTime();
			int day = (int) (j/1000/60/60/24);
			if (day <= 1 && day > 0) {
				//剩余天数都是1天
				grList2.add(gr);
			}
		}
		map.put("grList", grList2);
		return map;
	}

}
