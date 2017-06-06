package com.sc.tradmaster.service.projectBenefits.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.ProjectBenefits;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.projectBenefits.ProjectBenefitsService;
import com.sc.tradmaster.service.projectBenefits.impl.dto.HouseBenefitsDTO;
import com.sc.tradmaster.service.projectBenefits.impl.dto.ProjectBenefitsDTO;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.SysContent;

@Service("projectBenefitsService")
public class ProjectBenefitsServiceImpl implements ProjectBenefitsService{

	@Resource(name = "baseDao")
	private BaseDao baseDao;

	/**
	 * 价格优惠条款管理页面
	 * @throws ParseException 
	 * 
	 */	
	@Override
	public void findAllHouseAndBenefits(User user,Page page) throws ParseException {
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		int start = 0;
		int limit = 1000;
		String hql = "from House where projectId = '" + user.getParentId() + "' and houseStatus != 2 and houseStatus != 4";
		List<House> hList = baseDao.findByHql(hql);
		List pbdtoList = new ArrayList<>();
		if (hList!=null && hList.size()>0) {
			
			String pbHQL = "from ProjectBenefits pb where pb.projectId = '" + user.getParentId() + "'";
			List<ProjectBenefits> pbList = baseDao.findByHql(pbHQL);
			//进行中的优惠条款
			List<ProjectBenefits> pbList2 = new ArrayList<>();
			for (ProjectBenefits pb : pbList) {
				String nowTime = DateTime.toString1(new Date());
				double i = sdf.parse(nowTime).getTime() - sdf.parse(pb.getStartTime()).getTime();
				double j = sdf.parse(nowTime).getTime() - sdf.parse(pb.getEndTime()).getTime();
				if (i >= 0 && j < 0) {
					pbList2.add(pb);
				}
			}
			List<House> hList2 = new ArrayList<>();
			for (House house : hList) {
				String htHQL = "from HouseType where houseTypeId = '"+house.getHouseTypeId()+"' ";
				HouseType ht = (HouseType) baseDao.loadObject(htHQL);
				if (ht != null && !"".equals(ht)){
					if (ht.getProjectId().equals(house.getProjectId())){
						hList2.add(house);
					}
				}
			}
			/*Set<House> hSet = new HashSet<>();
			for (House house : hList2) {
				List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where houseNum = '"+house.getHouseNum()+"' ");
				if (crList.size()>0){
					for (ContractRecords cr : crList) {
						if (cr.getRecordStatus() != 5){
							hSet.add(house);
						}
					}
				}else {
					hSet.add(house);
				}
			}
		
			List<House> hList3 = new ArrayList<>();
			for (House house : hSet) {
				hList3.add(house);
			}*/
			
			for (House house : hList2) {
				HouseBenefitsDTO hbdto = new HouseBenefitsDTO();
				hbdto.setHouseNum(house.getHouseNum());
				hbdto.setBuildingNo(house.getBuildingNo());
				hbdto.setUnit(house.getUnit());
				hbdto.setHouseNo(house.getHouseNo());
				
				hbdto.setListPrice(SysContent.get2Double(house.getListPrice()));
				hbdto.setShopPrice(SysContent.get2Double(house.getShopPrice()));
				hbdto.setMinimumPrice(SysContent.get2Double(house.getMinimumPrice()));
				hbdto.setShelvTime(house.getShelvTime());
				
				String bestYouHuiId = house.getBestBenefitsId();
				if (bestYouHuiId != null) {
					double nowPrice = house.getListPrice();
					String youhui = "";
					String[] bestBenefits = bestYouHuiId.split(",");
					for (int i = 0; i < bestBenefits.length; i++) {
						String pbHQL2 = "from ProjectBenefits pb where pb.benefitId = '" + bestBenefits[i] + "'";
						ProjectBenefits pb = (ProjectBenefits) baseDao.loadObject(pbHQL2);
						if (pb != null) {
							String nowTime = DateTime.toString1(new Date());
							double x = sdf.parse(nowTime).getTime() - sdf.parse(pb.getStartTime()).getTime();
							double y = sdf.parse(nowTime).getTime() - sdf.parse(pb.getEndTime()).getTime();
							if (x >= 0 && y < 0) {
								if (pb.getType() == 0) {
									nowPrice = nowPrice - pb.getBenefitMoney();
								} else if (pb.getType() == 1) {
									nowPrice = nowPrice *(1-pb.getBenefitPercent()/100);
								}
								youhui += pb.getCaption()+ ",";
							}
						}
					}
					hbdto.setBestBenefits(youhui);
					DecimalFormat df = new DecimalFormat("#.##");
					double zuigaoYouhui = house.getListPrice() -nowPrice;
					String zuigaoYouhuiStr = df.format(zuigaoYouhui);
					double highestBenefits = Double.parseDouble(zuigaoYouhuiStr);
					
					hbdto.setHighBenefit(SysContent.get2Double(highestBenefits));
				}
				
				pbdtoList.add(hbdto);
			}
		}
		page.setTotal(pbdtoList.size());
		HouseBenefitsDTO hbdto = new HouseBenefitsDTO();
		List pageList = hbdto.getPageList(pbdtoList, page.getStart(), page.getLimit());
		page.setRoot(pageList);
	}

	/**
	 * 一个案场下所有价格优惠条款显示
	 * @throws ParseException 
	 * 
	 */	
	@Override
	public List findAllBenefits(User user) throws ParseException  {
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String hql = "from ProjectBenefits pb where pb.projectId = '" + user.getParentId() + "'";
		List<ProjectBenefits> pbList = baseDao.findByHql(hql);
		
		List<ProjectBenefitsDTO> pbdtoList = new ArrayList<>();
		for (ProjectBenefits pb : pbList) {
			ProjectBenefitsDTO pbdto = new ProjectBenefitsDTO();
			pbdto.setBenefitId(pb.getBenefitId());
			pbdto.setBenefitsName(pb.getBenefitsName());
			pbdto.setStartTime(pb.getStartTime());
			pbdto.setEndTime(pb.getEndTime());
			
			Date nowTime = new Date();
			String start = pb.getStartTime();
			String end = pb.getEndTime();
			if (start != null && end != null) {
				Date startTime = sdf.parse(pb.getStartTime());
				Date endTime = sdf.parse(pb.getEndTime());
				
				double i = nowTime.getTime() - startTime.getTime();
				double j = nowTime.getTime() - endTime.getTime();
				if (i >= 0 && j <= 0) {
					pbdto.setBenefitsStatus("进行中");
				} else if (i < 0) {
					pbdto.setBenefitsStatus("未开始");
				} else if (j >= 0) {
					pbdto.setBenefitsStatus("已结束");
				}
			}
			pbdto.setCaption(pb.getCaption());
			
			pbdtoList.add(pbdto);
		}
		return pbdtoList;
	}

	/**
	 * 单条删除优惠条款
	 * 
	 */	
	@Override
	public void dropOneBenefits(User user,String benefitId) {
		
		ProjectBenefits pb = (ProjectBenefits) baseDao.loadById(ProjectBenefits.class, benefitId);
		
		baseDao.delete(pb);
		
	}

	/**
	 * 批量删除优惠条款
	 * 
	 */	
	@Override
	public void dropSomeBenefits(User user, String[] benefitId) {
		for (int i = 0; i < benefitId.length; i++) {
			String hql = "from ProjectBenefits pb where pb.benefitId = '" + benefitId[i] + "'";
			ProjectBenefits pb = (ProjectBenefits) baseDao.loadObject(hql);
			
			baseDao.delete(pb);
		}
	}

	/**
	 * 新增优惠条款
	 * 
	 */	
	@Override
	public void addBenefits(User user,String benefitsName,String startTime,String endTime,Double fee,
			Integer type,Double benefitMoney,String caption) {
		
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		String date = "2099-02-14 00:00:00";
		
		ProjectBenefits pb = new ProjectBenefits();
		pb.setBenefitId(SysContent.uuid());
		pb.setProjectId(user.getParentId());
		pb.setBenefitsName(benefitsName);
		
		if (startTime == null || "".equals(startTime)) {
			pb.setStartTime(DateTime.toString1(new Date()));
		} else {
			pb.setStartTime(startTime);
		}
		if (endTime == null || "".equals(endTime)) {
			pb.setEndTime(DateTime.toString1(DateUtil.parse("2099-02-14 00:00:00","yyyy-MM-dd HH:mm:ss" )));
		} else {
			pb.setEndTime(endTime);
		}
		pb.setFee(fee);
		pb.setType(type);
		if (type == 0) {
			if (benefitMoney != null) {
				pb.setBenefitMoney(benefitMoney);
			}
		} else if (type == 1) {
			if (benefitMoney != null) {
				pb.setBenefitPercent(benefitMoney);
			}
		}
		pb.setCaption(caption);
		
		baseDao.saveOrUpdate(pb);
	}

	/**
	 * 修改优惠条款
	 * 
	 */	
	@Override
	public void updateBenefits(User user,String benefitId, String benefitsName,String startTime,String endTime,Double fee,
			Integer type,Double benefitMoney,String caption) {
		String hql = "from ProjectBenefits pb where pb.benefitId = '" + benefitId + "'";
		ProjectBenefits pb = (ProjectBenefits) baseDao.loadObject(hql);
		
		pb.setBenefitsName(benefitsName);
		if (startTime == null || "".equals(startTime)) {
			pb.setStartTime(DateTime.toString1(new Date()));
		} else {
			pb.setStartTime(startTime);
		}
		if (endTime == null || "".equals(endTime)) {
			pb.setEndTime(DateTime.toString1(DateUtil.parse("2099-02-14 00:00:00","yyyy-MM-dd HH:mm:ss" )));
		} else {
			pb.setEndTime(endTime);
		}
		pb.setFee(fee);
		pb.setType(type);
		if (type == 0) {
			if (benefitMoney != null) {
				pb.setBenefitMoney(benefitMoney);
			}
		} else if (type == 1) {
			if (benefitMoney != null) {
				pb.setBenefitPercent(benefitMoney);
			}
		}
		pb.setCaption(caption);
		baseDao .saveOrUpdate(pb);
	}

	/**
	 * 房源优惠组合页面
	 * @throws ParseException 
	 * 
	 */	
	@Override
	public Map findUseBenefits(User user,String[] houseNums) throws ParseException {
		
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String hql = "from ProjectBenefits pb where pb.projectId = '" + user.getParentId() + "'";
		List<ProjectBenefits> pbList = baseDao.findByHql(hql);
		
		Map<String, Object> map = new HashMap<>();
		//进行中的优惠条款
		List<ProjectBenefits> pbList2 = new ArrayList<>();
		for (ProjectBenefits pb : pbList) {
			String nowTime = DateTime.toString1(new Date());
			double i = sdf.parse(nowTime).getTime() - sdf.parse(pb.getStartTime()).getTime();
			double j = sdf.parse(nowTime).getTime() - sdf.parse(pb.getEndTime()).getTime();
			if (i >= 0 && j < 0) {
				pbList2.add(pb);
			}
		}
		map.put("houseNumbers", Arrays.asList(houseNums));
		map.put("youhuiList", pbList2);
		return map;
	}
	/**
	 * 优惠条款排序计算最高优惠
	 * 
	 */	
	@Override
	public void updateHighMoney(User user, String[] benefits,String[] houseNums) {
		
		double guding = 0;
		double baifenbi = 0;
		String bestYouHuiId = "";
		if (benefits.length > 0) {
			for (int i = 0; i < benefits.length; i++) {
				String hql = "from ProjectBenefits pb where pb.benefitId = '" + benefits[i] + "'";
				ProjectBenefits pb = (ProjectBenefits) baseDao.loadObject(hql);
				if ( pb != null ) {
					if (pb.getType() == 0) {
						guding += pb.getBenefitMoney();
					} else if (pb.getType() == 1) {
						baifenbi += pb.getBenefitPercent();
					}
					bestYouHuiId += pb.getBenefitId() + ",";
				}
			}
		}
		for (int i = 0; i < houseNums.length; i++) {
			String houseHQL = "from House h where h.houseNum = '" + houseNums[i] + "'";
			House h = (House) baseDao.loadObject(houseHQL);
			h.setBestBenefitsId(bestYouHuiId);
			baseDao.saveOrUpdate(h);
		}
	}

	/**
	 * 根据benefitId获得ProjectBenefits对象
	 * 
	 */	
	@Override
	public ProjectBenefits findProjectBenefitsById(String benefitId) {
		
		return (ProjectBenefits) baseDao.loadById(ProjectBenefits.class, benefitId);
	}
}
