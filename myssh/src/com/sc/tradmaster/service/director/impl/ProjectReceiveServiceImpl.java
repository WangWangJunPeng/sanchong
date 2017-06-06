package com.sc.tradmaster.service.director.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.SignRecords;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.director.ProjectReceiveService;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.SysContent;

@Service("projectReceiveService")
public class ProjectReceiveServiceImpl implements ProjectReceiveService {
	
	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	//获取今日接访数据
	@Override
	public Map findTodayReceiveFirstPageData(User user) {
		Map map = new HashMap<>();
		if(user!=null){
			String toDayStartTime = SysContent.getStartTime();
			String toDayEndTime = SysContent.getEndTime();
			//今日接访
			String toDayRecListHql = "from VisitRecords where 1 = 1 "
					+ " and projectId = '"+user.getParentId()+"' and receptTime > "
					+toDayStartTime+"' and ( leaveTime <= "+toDayEndTime+"' or leaveTime is null )" ;
			List<VisitRecords> list = baseDao.findByHql(toDayRecListHql);
			int counts = list.size();
 			//今日认购
			String overEnterBuyHql = "select count(*) from ContractRecords where 1=1 "
					+ " and applyTime >= '"+toDayStartTime+"' and applyTime <= ';"+toDayEndTime+"'";
			int enterBuyCounts = baseDao.countQuery(overEnterBuyHql);
			//今日签约
			String overSignHql = "select count(*) from ContractRecords where 1=1 "
					+ " and voucherUploadTime > '"+toDayStartTime+"' and voucherUploadTime <= ';"+toDayEndTime+"'";
			int toDaySignCount = baseDao.countQuery(overSignHql);
			//获取正在接访的
			List receivingList = new ArrayList<>();
			//获取正在等待的
			List waitRecList = new ArrayList<>();
			for(VisitRecords vr : list){
				if(vr.getLeaveTime()!=null){
					waitRecList.add(vr.getUserId());
				}else{
					receivingList.add(vr);
				}
			}
			//获取正在接访数
			int receivingCount = receivingList.size();
			
			//装配今日休息的置业顾问
			List todayRest = new ArrayList<>();
				//①获取所有的案场置业信息
			String userHql = "from User where parentId = '"+user.getParentId()+"'";
			List<User> userList = baseDao.findByHql(userHql);
			for(User u : userList){
				//查询置业在今日签到的状况
				String todaySignHql = "from SignRecords where userId = '"+u.getUserId()+"' "
						+ "and signInTime > " +toDayStartTime+ "' and ( signOutTime <= "+toDayEndTime+"' or signOutTime is null )";
				SignRecords signUser = (SignRecords) baseDao.loadObject(todaySignHql);
				if(signUser==null){
					todayRest.add(u.getUserId());
				}
			}
			
			//封装返回数据
			map.put("todayRecCount", counts);
			map.put("enterBuy", enterBuyCounts);
			map.put("signCount", toDaySignCount);
			map.put("recingCount", receivingCount);
			map.put("recing", receivingList);
			map.put("wait", waitRecList);
			map.put("rest", todayRest);
			
		}
		return map;
	}

	//顾问状态数据
	@Override
	public Map findAgentStatusDataById(User user, String userId, String startDate, String endDate) {
		Map map = new HashMap<>();
		if(user!=null){
			//顾问信息
			User agent = (User) baseDao.loadById(User.class, userId);
			String status = "未知";
			//判断该顾问状态(先判断该置业是否休息，没有休息在判断是否正在接待)
			String toDayStartTime = SysContent.getStartTime();
			String toDayEndTime = SysContent.getEndTime();
			
			String todaySignHql = "from SignRecords where userId = '"+userId+"'"
					+ " and signInTime > " +toDayStartTime+ "' and ( signOutTime <= "+toDayEndTime+"' or signOutTime is null )";
			SignRecords signUser = (SignRecords) baseDao.loadObject(todaySignHql);
			
			if(signUser==null){
				status = "休息中";
			}else{
				String toDayRecListHql = "from VisitRecords where userId = '"+userId+"' "
						+ "and projectId = '"+user.getParentId()+"' and receptTime > "
						+toDayStartTime+"' and ( leaveTime <= "+toDayEndTime+"' or leaveTime is null )" ;
				VisitRecords rece = (VisitRecords) baseDao.loadObject(toDayRecListHql);
				if(rece!=null){
					status = "接待中";
				}else{
					status = "等待中";
				}
			}
			map.put("agent", agent);
			map.put("status",status);
			//到访信息,有效接访率
			String validHql = "select count(*) from VisitRecords where writeState = 1 and userId = '"+agent.getUserId()+"' and "
					+ "receptTime >= '"+startDate+"' and receptTime <= '"+endDate+"'";
			String totalHql = "select count(*) from VisitRecords where 1 = 1 and userId = '"+agent.getUserId()+"' and "
					+ "receptTime >= '"+startDate+"' and receptTime <= '"+endDate+"'";
			double valid = baseDao.countQuery(validHql);
			double total = baseDao.countQuery(totalHql);
			String validRate = "0.00";
			if(total>0){
				validRate = SysContent.get2Double((valid/total));
			}
			map.put("valiRate", validRate);
			map.put("valid",(int)valid);
			map.put("total",(int)total);
			// 客户回头率
			// 客户回头率
			// ①获取当前案场的客户
			int oldSecondCount = 0;
			int secondCount = 0;
			int secondRate = 0;
			String currentProCusHql = "from ProjectCustomers where projectId = '" + user.getParentId() + "'";
			List<ProjectCustomers> cusList = baseDao.findByHql(currentProCusHql);
			if (!cusList.isEmpty()) {
				String agentVisitHql = "";
				for (ProjectCustomers pc : cusList) {
					agentVisitHql = "from VisitRecords where 1=1 and customerId = '" + pc.getProjectCustomerId()
							+ "' and projectId = '" + user.getParentId() + "' " + " and receptTime >= '" + startDate
							+ "' and receptTime <= '" + endDate + "' writeState = 1";
					List<VisitRecords> list = baseDao.findByHql(agentVisitHql);
					if (list.size() == 2) {
						secondCount++;
						VisitRecords vr = list.get(list.size()-1);
						if(vr.getIsNew()==null && vr.getIsNew().equals("") && !vr.getIsNew()){
							oldSecondCount++;
						}
					}
				}
			}
			if(valid>0){
				secondRate = (int) (secondCount / valid * 100);
			}
			//回头率
			map.put("secondRate", secondRate+"%");
			//老客户占比
			map.put("oldInRate", oldSecondCount);
			//新增二次来访
			map.put("secondCount", secondCount);
			//认购信息
			String getMoneyHql = "select sum(haveToPayMoney) from ContractRecords where userId = '"+userId+"'";
			double money = baseDao.countQuery(getMoneyHql);
			String getMoney = SysContent.get2Double(money);
			map.put("getMoney", getMoney);
			//新老用户
			int newCus = 0;
			int oldCus = 0;
			String newOleUserHql = "from ContractRecords where userId = '"+userId+"'";
			List<ContractRecords> crList = baseDao.findByHql(newOleUserHql);
			for(ContractRecords cr : crList){
				if(cr.getProjectCustomerId()!=null){
					//查询是否是老客户
					String visitHql = "from VisitRecords where customerId = '"+cr.getProjectCustomerId()+"'";
					VisitRecords vrs = (VisitRecords) baseDao.loadObject(visitHql);
					if(vrs.getIsNew()){
						newCus++;
					}else{
						oldCus++;
					}
				}else{
					oldCus++;
				}
			}
			map.put("new", newCus);
			map.put("oldCus", oldCus);
			//已签约房源信息数据
			String buyMoneyHql = "select sum(buyPrice) from ContractRecords where recordStatus = 5 and userId = '"+userId+"'";
			double buyTotalMoney = baseDao.countQuery(buyMoneyHql);
			String totalMoney = SysContent.get2Double(buyTotalMoney);
			map.put("money",totalMoney);
			//已签约
			String orealdySign = "select count(*) from ContractRecords where recordStatus = 5 and userId = '"+userId+"'";
			int orealdySignCount = baseDao.countQuery(orealdySign);
			map.put("sign", orealdySignCount);
			//等待签约
			String waitSignHql = "select count(*) from ContractRecords where recordStatus = 4 and userId = '"+userId+"'";
			int waitSignCount = baseDao.countQuery(waitSignHql);
			map.put("waitSign", waitSignCount);
			//放弃签约
			String overSignHql = "select count(*) from ContractRecords where recordStatus = 7 and userId = '"+userId+"' "
					+ " and remitConfirmTime is not null";
			int overSignCount = baseDao.countQuery(overSignHql);
			map.put("overSign", overSignCount);
			//放弃认购 	
			String overEnterBuy = "select count(*) from ContractRecords where recordStatus = 7 and userId = '"+userId+"' "
					+ " and voucherUploadTime is null";
			int overEnterBuyCount = baseDao.countQuery(overEnterBuy);
			map.put("overEnterBuy",overEnterBuyCount);
		}
		return map;
	}

	//获取详细接访数据
	@Override
	public Map findToDayDetailedReceiveDataByTime(User user, String startDate, String endDate) {
		Map map = new HashMap<>();
		if(user!=null){
			String toDayStartTime = SysContent.getStartTime();
			String toDayEndTime = SysContent.getEndTime();
			String vistHql = "from VisitRecords where projectId = '"+user.getParentId()+"' and receptTime >= '"+startDate+"' and receptTime <= '"+endDate+"'";
			List<VisitRecords> vrList = baseDao.findByHql(vistHql);
			//接访数量
			int recCount = vrList.size();
			map.put("recCount", recCount);
			long timeDiff = 0L;
			int totalNum = 0;
			double AverageLongTime = 0.00;
			String strAverageLongTime = "0.00";
			//有效到访数
			int validReceCount = 0;
			int unValidReceCount = 0;
			int validRate = 0;
			//老客户占比
			int oldCusCount = 0;
			int oldCusRate = 0;
			//总替接率
			int replaceCount = 0;
			int replaceRate = 0;
			for(VisitRecords vr : vrList){
				if(vr.getLeaveTime()!=null && !vr.getLeaveTime().equals("")){
					long leave = DateUtil.parse(vr.getLeaveTime()).getTime();;
					long recept = DateUtil.parse(vr.getReceptTime()).getTime();
					timeDiff += leave -recept;
					totalNum++;
				}
				if(vr.getWriteState()!=null && !vr.getWriteState().equals("") && vr.getWriteState()==1){
					validReceCount++;
				}else{
					unValidReceCount++;
				}
				if(!vr.getIsNew()){
					oldCusCount++;
				}
				if(!vr.getUserId().equals(vr.getAppointUserId()) && vr.getWriteState().equals(1)){
					replaceCount++;
				}
			}
			//平均接待时间
			if(totalNum>0){
				AverageLongTime = timeDiff/totalNum/1000/60;
				strAverageLongTime = SysContent.get2Double(AverageLongTime);
			}
			map.put("averrageTime", strAverageLongTime);
			map.put("valid", validReceCount);
			map.put("unValid", unValidReceCount);
			if(validReceCount+unValidReceCount>0){
				validRate = validReceCount/(validReceCount+unValidReceCount)*100;
			}
			map.put("validRate", validRate+"%");
			//报备到访率
			String grTotalHql = "select count(*) from GuideRecords where 1=1 and projectId = '"+user.getParentId()+"' and applyTime > '"+startDate+"' and applyTime <= '"+endDate+"'";
			String grVisitedHql = "select count(*) from GuideRecords where 1=1 and visitNo is not null and projectId = '"+user.getParentId()+"' and applyTime > '"+startDate+"' and applyTime <= '"+endDate+"'";
			int grVisited = baseDao.countQuery(grVisitedHql);
			int grTotal = baseDao.countQuery(grTotalHql);
			int grVisiteRate = 0;
			if(grTotal>0){
				grVisiteRate = grVisited/grTotal*100;
			}
			map.put("grVisitedRate", grVisiteRate+"%");
			if(recCount>0){
				//老客户占比
				oldCusRate = oldCusCount / recCount * 100;
				//总替接率
				replaceRate = replaceCount/recCount * 100;
			}
			map.put("oldRate", oldCusRate+"%");
			map.put("replaceRate", replaceRate+"%");
			//平台导客比
			map.put("systemRate", grVisiteRate+"%");
			//客户回头率
				//①获取当前案场的客户
			int secondCount = 0;
			int secondRate = 0;
			String currentProCusHql = "from ProjectCustomers where projectId = '"+user.getParentId()+"'";
			List<ProjectCustomers> cusList = baseDao.findByHql(currentProCusHql);
			if(!cusList.isEmpty()){
				String agentVisitHql="";
				for(ProjectCustomers pc : cusList){
					agentVisitHql = "from VisitRecords where 1=1 and customerId = '"+pc.getProjectCustomerId()+"' and projectId = '"+user.getParentId()+"' "
							+ " and receptTime >= '"+startDate+"' and receptTime <= '"+endDate+"' and writeState = 1";
					List list = baseDao.findByHql(agentVisitHql);
					if(list.size()==2){
						secondCount++;
					}
				}
			}
			if(recCount>0){
				secondRate = secondCount / recCount * 100;
			}
			map.put("secondRate", secondRate+"%");
		}
		return map;
	}

	//获取详细成交数据
	@Override
	public Map findDealDataByTime(User user, String startDate, String endDate) {
		Map map = new HashMap<>();
		if(user!=null){
			//成交数量
			String dealHql = "select count(*) from ContractRecords where 1=1 and recordStatus ！= 2 and recordStatus != 3 and recordStatus ！= 7 and projectId = '"+user.getParentId()+"'";
			//今日认购
			String enterBuyHql = "select count(*) from ContractRecords where 1=1 and voucherUploadTime is null and projectId = '"+user.getParentId()+"'";
			//今日签约
			String signHql = "select count(*) from ContractRecords where 1=1 and projectId = '"+user.getParentId()+"'";
			//已锁定房源货值
			String lockedHouseHql = "select sum(buyPrice) from ContractRecords where 1=1 and projectId = '"+user.getParentId()+"'";
			//已签约房源货值
			String signedHouseHql = "select sum(buyPrice) from ContractRecords where 1=1 and recordStatus = 5 and projectId = '"+user.getParentId()+"'";
			//平台客户认购占比(获取当前时间认购客户)
			String systemCusInOrderHql = "select count(*) from ContractRecords where 1=1 and voucherUploadTime is null and shopCustomerId is not null and projectId = '"+user.getParentId()+"'";
			//平台客户签约占比
			String systemCusInSignHql = "select count(*) from ContractRecords where 1=1 and shopCustomerId is not null and projectId = '"+user.getParentId()+"'";
			if(startDate!=null && !startDate.equals("")){
				dealHql += " and applyTime >= '"+startDate+"'";
				enterBuyHql += " and applyTime >= '"+startDate+"'";
				signHql += " and voucherUploadTime > '"+startDate+"'";
				lockedHouseHql += " and voucherUploadTime > '"+startDate+"'";
				signedHouseHql += " and contractConfirmTime > '"+startDate+"'";
				systemCusInOrderHql += " and applyTime >= '"+startDate+"'";
				systemCusInSignHql += " and voucherUploadTime > '"+startDate+"'";
			}
			if(endDate!=null && !endDate.equals("")){
				dealHql += " and applyTime <= '"+endDate+"'";
				enterBuyHql += " and applyTime <= ';"+endDate+"'";
				signHql += " and voucherUploadTime <= ';"+endDate+"'";
				lockedHouseHql += " and voucherUploadTime <= ';"+endDate+"'";
				signedHouseHql += " and contractConfirmTime <= '"+endDate+"'";
				systemCusInOrderHql += " and applyTime <= '"+endDate+"'";
				systemCusInSignHql += " and voucherUploadTime <= ';"+endDate+"'";
			}
			
			int dealCount = baseDao.countQuery(dealHql);
			map.put("dealCount", dealCount);
			//今日认购
			int enterBuyCount = baseDao.countQuery(enterBuyHql);
			map.put("enterCount", enterBuyCount);
			//今日签约
			int signCount = baseDao.countQuery(signHql);
			map.put("signCount",signCount);
			//已锁定房源货值
			int lockedHouseValue = baseDao.countQuery(lockedHouseHql);
			map.put("lockedValue", lockedHouseValue);
			//已签约房源货值
			int signedHouseValue = baseDao.countQuery(signedHouseHql);
			//平台客户认购占比
			int systemCusCount= baseDao.countQuery(systemCusInOrderHql);
			
			int systemCusEnterBuyRate = 0;
			if(enterBuyCount>0){
				systemCusEnterBuyRate = systemCusCount / enterBuyCount * 100;
			}
			map.put("systemCusEnterBuyRate", systemCusEnterBuyRate);
			//平台客户签约占比
			int systemCusSignRate = 0;
			int systemCusInSignCount = baseDao.countQuery(systemCusInSignHql);
			if(signCount>0){
				systemCusSignRate = systemCusInSignCount / signCount * 100;
			}
			map.put("systemCusSignRate", systemCusSignRate);
		}
		return map;
	}


	/************************************Web端Service***************************************/
	//获取项目任务完成进度
	@Override
	public Map<String, String> findProjectTaskFinishedExtent(User user, String anyDate) {
		Map map = new HashMap<>();
		if(user!=null){
			if(anyDate==null || anyDate.equals("")){
				anyDate = DateTime.toString1(new Date());
			}
			String start = SysContent.getStartTime2(DateUtil.parse(anyDate));
			String end = SysContent.getEndTime2(DateUtil.parse(anyDate));
			int maxNum = 100 ;
			//接访量完成度
			String visitHql = "select count(*) from VisitRecords where projectId = '"+user.getParentId()+"' and receptTime => '"+start+"' and receptTime <= '"+end+"' and writeState = 1";
			int visitCount = baseDao.countQuery(visitHql);
			int visitRate = visitCount / maxNum * 100;
			map.put("visitRate", visitRate+"%");
			//认购量完成度
			String enterBuyHql = "select count(*) from ContractRecords where projectId = '"+user.getParentId()+"' and and voucherUploadTime is null and applyTime >= '"+start+"' and "
					+ " applyTime <= ';"+end+"'";
			int enterBuyCount = baseDao.countQuery(enterBuyHql);
			int enterBuyRate = enterBuyCount / maxNum * 100;
			map.put("enterBuyRate", enterBuyRate+"%");
			//签约量完成度
			String singHql = "select count(*) from ContractRecords where projectId = '"+user.getParentId()+"' and and voucherUploadTime > '"+start+"' and voucherUploadTime <= '"+end+"'";
			int singCount = baseDao.countQuery(singHql);
			int singRate = singCount / maxNum * 100;
			map.put("signRate",singRate+"%");
			//售出房源货值完成度
			//新增储客完成度
			
		}
		return map;
	}
	
	
}
