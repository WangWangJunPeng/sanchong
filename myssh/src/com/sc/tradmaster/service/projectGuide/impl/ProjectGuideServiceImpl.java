package com.sc.tradmaster.service.projectGuide.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectBenefits;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.projectGuide.ProjectGuideService;
import com.sc.tradmaster.service.projectGuide.impl.dto.ProjectHouse;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.SysContent;

@Service("projectGuideService")
public class ProjectGuideServiceImpl implements ProjectGuideService{
	
	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	private Session session;

	@Override
	public List findByProperty(String[] selectNames, String[] selectValue,Page page) {
		
		int start = page.getStart();
		int limit = page.getLimit();
		String[] projectSelectName = {selectNames[0],selectNames[1]};
		String[] projectSelectValue = {selectValue[0],selectValue[1]};
		List<Project> projectList = baseDao.findByPropertyLike("Project", projectSelectName, projectSelectValue, start, limit);
		String[] youhuiName = {selectNames[2]};
		String[] youhuiValue = {selectValue[2]};
		List<ProjectHouse> phList = new ArrayList<>();
		for (Project p : projectList) {
				String hql = "from ProjectGuide where  projectId = '" + p.getProjectId()+"'";
			if (selectValue[3].equals("1")) {
				hql = "from ProjectGuide where  projectId = '" + p.getProjectId()+"' and isDaiKan = '" + selectValue[3] + "'"; 
			}
			ProjectGuide pg = (ProjectGuide)baseDao.loadObject(hql);
			if(pg!=null){
				String youhui = "";
				String nowTime = DateTime.toString1(new Date());
				String youhuiHQL = "from ProjectBenefits where projectId = '" + p.getProjectId() + "'";
				youhuiHQL += " and startTime <= '" + nowTime +"' and endTime >= '" + nowTime + "' and caption is not null";
				List<ProjectBenefits> pbList = baseDao.findByHql(youhuiHQL);
				for (ProjectBenefits pb : pbList) {
					youhui += pb.getCaption() + ",";
				}
				if (selectValue[2].equals("1")){
					if (youhui != null && !"".equals(youhui)){
						ProjectHouse ph = new ProjectHouse();
						ph.setProjectId(p.getProjectId());
						ph.setProjectName(p.getProjectName());
						ph.setDeveloper(p.getDeveloper());
						ph.setAveragePrice(p.getAveragePrice());
						ph.setIsDaiKan(pg.getIsDaiKan());
						ph.setIsYiDi(pg.getIsYiDi());
						ph.setIsFast(pg.getIsFast());
						ph.setDaiKanMoney(pg.getDaiKanMoney());
						ph.setFenXiaoMoney(pg.getFenXiaoMoney());
						ph.setInformation(youhui);
						phList.add(ph);
					}
				} else {
					ProjectHouse ph = new ProjectHouse();
					ph.setProjectId(p.getProjectId());
					ph.setProjectName(p.getProjectName());
					ph.setDeveloper(p.getDeveloper());
					ph.setAveragePrice(p.getAveragePrice());
					ph.setIsDaiKan(pg.getIsDaiKan());
					ph.setIsYiDi(pg.getIsYiDi());
					ph.setIsFast(pg.getIsFast());
					ph.setDaiKanMoney(pg.getDaiKanMoney());
					ph.setFenXiaoMoney(pg.getFenXiaoMoney());
					ph.setInformation(youhui);
					phList.add(ph);
				}
			}
		}
		int total = phList.size();
		page.setTotal(total);
		page.setRoot(phList);
		return phList;
	}

	

	@Override
	public void addOrUpdate(ProjectGuide pg ,User user) {
		if(pg.getProjectId()==null || pg.getProjectId().equals("")){
			pg.setProjectId(user.getParentId());
		}
		pg.setProjectId(user.getParentId());
		baseDao.saveOrUpdate(pg);
	}

	@Override
	public ProjectGuide findById(String pgId) {
		if(pgId!=null && !pgId.equals("")){
			ProjectGuide pg = (ProjectGuide) baseDao.loadById(ProjectGuide.class, pgId);
			pg.setDaiKanMoneyStr(SysContent.get2Double(pg.getDaiKanMoney()));
			pg.setFenXiaoMoneyStr(SysContent.get2Double(pg.getFenXiaoMoney()));
			pg.setYiDiSalesCommissionStr(SysContent.get2Double(pg.getYiDiSalesCommission()));
			return pg;
		}else{
			return null;
		}
	}


	@Override
	public Map<String, Object> findShopApproveTime(User user) {
		Shops shops = (Shops) baseDao.loadObject("from Shops where shopId = '"+user.getParentId()+"' ");
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("approveTime", shops.getApproveTime());
		
		return map;
	}
	
	
}
