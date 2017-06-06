package com.sc.tradmaster.service.visitRecords.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.tagService.AppTagManagerService;
import com.sc.tradmaster.service.visitRecords.VisitRecordsService;
import com.sc.tradmaster.service.visitRecords.impl.visitRecordDTO.ProjectCustomersDTO;
import com.sc.tradmaster.service.visitRecords.impl.visitRecordDTO.VisitCustomerDTO;
import com.sc.tradmaster.service.visitRecords.impl.visitRecordDTO.VisitDTO;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.SysContent;

@Service("visitRecordsService")
public class VisitRecordsServiceImpl implements VisitRecordsService{

	@Resource(name = "baseDao")
	private BaseDao baseDao;
	
	@Resource(name = "appTagManagerService")
	private AppTagManagerService appTagManagerService;
	/**
	 * 到访客户表
	 * @throws ParseException 
	 */	
	@Override
	public List<VisitCustomerDTO> findVisitCustomer(User user, String projectId, String startTime, String endTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//当前中介的user信息
		String userId = user.getUserId();
		//当前获得的project
		//当前中介的该案场的备案记录表
		String grHQL = "from GuideRecords gr where gr.userId = '" + userId + "'" + "and gr.projectId = '" + projectId + "'"
				 + " and gr.applyStatus = 1";
		
		if (startTime != null && !"".equals(startTime)) {
			grHQL += " and gr.applyTime >= '" + startTime + "'";
		}
		if (endTime != null && !"".equals(endTime)) {
			grHQL += " and gr.applyTime <= '" + endTime + "'";
		}
		
		List<GuideRecords> grList = baseDao.findByHql(grHQL);
		//new map
		List<VisitCustomerDTO> vcdtoList = new ArrayList<>();
		if (grList != null) {
			//遍历当前中介的该案场的备案记录表
			for (GuideRecords guideRecords : grList) {
				VisitCustomerDTO vcdto = new VisitCustomerDTO();
				vcdto.setCustomerName(guideRecords.getCustomerName());
				vcdto.setCustomerPhone(guideRecords.getCustomerPhone());
				vcdto.setApplyTime(guideRecords.getApplyTime());
				//该条备案记录的客户对应的到访记录
				String hql = "from VisitRecords vr where vr.visitNo = '" + guideRecords.getVisitNo() + "'";
				VisitRecords vr = (VisitRecords) baseDao.loadObject(hql);
				if (vr != null && !"".equals(vr)){
					String userHQL = "from User u where u.userId = '" + vr.getUserId() + "'";
					User u = (User) baseDao.loadObject(userHQL);
					//一条map的到访客户信息 + 置业顾问的信息
					vcdto.setArriveTime(vr.getArriveTime());
					if (u != null && !"".equals(u)){
						vcdto.setUserCaption(u.getUserCaption());
						vcdto.setUserId(u.getUserId());
					}
				}
				vcdtoList.add(vcdto);
			}
		}
		for (int i = 0; i < vcdtoList.size(); i++) {
			for (int j = 0; j < vcdtoList.size()-1-i; j++) {
				if (sdf.parse(vcdtoList.get(j).getArriveTime()).getTime() < sdf.parse(vcdtoList.get(j+1).getArriveTime()).getTime()){
					VisitCustomerDTO vc = vcdtoList.get(j);
					vcdtoList.set(j, vcdtoList.get(j+1));
					vcdtoList.set(j+1, vc);
				}
			}
		}
		
		
		return vcdtoList;
	}

	/**
	 * 下载到访客户表
	 * @throws ParseException 
	 */	
	@Override
	public void findVisit(String projectId, String arriveTime,Page p) throws ParseException {
		Date date = new Date();
		String hql = "from VisitRecords vr where vr.projectId = '" + projectId + "'";
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (arriveTime != null && !"".equals(arriveTime)) {
			date = sdf.parse(arriveTime);
			//new List
			String startTime = SysContent.getStartTime2(date);
			String endTime = SysContent.getEndTime2(date);
			hql += " and vr.arriveTime >= '" + startTime + "' and vr.arriveTime <= '" + endTime + "'";
		}
		
		
		List<VisitRecords> vList = baseDao.findByHql(hql, p.getStart(), p.getLimit());
		List<VisitDTO> vdtoList = new ArrayList<>();
		for (VisitRecords vr : vList) {
			VisitDTO vdto = new VisitDTO();
			vdto.setVisitNo(vr.getVisitNo().toString());
			vdto.setUserId(vr.getUserId());
			vdto.setProjectId(vr.getProjectId());
			vdto.setVisitStatus(vr.getVisitStatus());
			vdto.setCustomerCount(vr.getCustomerCount());
			vdto.setCustomerName(vr.getCustomerName());
			vdto.setPhone(vr.getPhone());
			vdto.setRecordNo(vr.getRecordNo());
			vdto.setArriveTime(vr.getArriveTime());
			vdto.setReceptTime(vr.getReceptTime());
			vdto.setLeaveTime(vr.getLeaveTime());
			vdto.setAppointUserId(vr.getAppointUserId());
			vdto.setCustomerId(vr.getCustomerId());
			vdto.setDescription(vr.getDescription());
			vdto.setTags(vr.getTags());
			vdto.setWriteState(vr.getWriteState());
			vdtoList.add(vdto);
		}
		
		p.setTotal(vdtoList.size());
		p.setRoot(vdtoList);
		//返回当天的到访记录
	}

	/**
	 * 置业顾问到访客户表
	 */
	@Override
	public List<ProjectCustomersDTO> findSaleVisitList(User user) {
		String pcHQL = "from ProjectCustomers pc where pc.projectId = '" + user.getParentId()
		+ "' and pc.ownerUserId = '" + user.getUserId() + "' and pc.lastTime is not null  order by lastTime DESC";
		List<ProjectCustomers> pcList = baseDao.findByHql(pcHQL);
		
		List<ProjectCustomersDTO> pcdtoList = new ArrayList<>();
		
		for (ProjectCustomers pc : pcList) {
			ProjectCustomersDTO pcdto = new ProjectCustomersDTO();
			pcdto.setProjectCustomerName(pc.getProjectCustomerName());
			pcdto.setProjectCustomerPhone(pc.getProjectCustomerPhone());
			pcdto.setLastTime(pc.getLastTime());
			pcdto.setProjectCustomerId(pc.getProjectCustomerId());
			pcdto.setYixiang(appTagManagerService.findCustomerYiXiang(pc.getProjectCustomerId(), pc.getProjectId()));
		
			pcdtoList.add(pcdto);
		}
		
		return pcdtoList;
	}

}
