package com.sc.tradmaster.service.projectBenefits;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.sc.tradmaster.bean.ProjectBenefits;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.utils.Page;

public interface ProjectBenefitsService {
	
	
	/**
	 * 根据benefitId获得ProjectBenefits对象
	 * 
	 */	
	ProjectBenefits findProjectBenefitsById(String benefitId);

	/**
	 * 案场助理优惠条款首页
	 * 
	 */	
	void findAllHouseAndBenefits(User user,Page page)throws ParseException;
	
	/**
	 * 一个案场下所有价格优惠条款显示
	 * 
	 */	
	List findAllBenefits(User user)throws ParseException;
	
	/**
	 * 单条删除优惠条款
	 * 
	 */	
	void dropOneBenefits(User user,String benefitId);
	
	/**
	 * 批量删除优惠条款
	 * 
	 */	
	void dropSomeBenefits(User user, String[] benefitId);
	
	/**
	 * 新增优惠条款
	 * 
	 */	
	void addBenefits(User user,String benefitsName,String startTime,String endTime,Double fee,
			Integer type,Double benefitMoney,String caption);
	
	/**
	 * 修改优惠条款
	 * 
	 */	
	void updateBenefits(User user,String benefitId, String benefitsName,String startTime,String endTime,Double fee,
			Integer type,Double benefitMoney,String caption);
	
	/**
	 * 房源优惠组合页面
	 * 
	 */	
	Map findUseBenefits(User user,String[] houseNums)throws ParseException;
	
	/**
	 * 优惠条款排序计算最高优惠
	 * 
	 */	
	void updateHighMoney(User user,String[] benefits,String[] houseNums) ;
}
