package com.sc.tradmaster.service.director;

import java.util.Map;

import com.sc.tradmaster.bean.User;

public interface ProjectReceiveService {
	/**
	 * 获取今日接访数据
	 * @param user
	 * @return
	 */
	Map findTodayReceiveFirstPageData(User user);

	/**
	 * 顾问状态数据
	 * @param user
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map findAgentStatusDataById(User user, String userId, String startDate, String endDate);

	/**
	 * 获取详细接访数据
	 * @param user
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map findToDayDetailedReceiveDataByTime(User user, String startDate, String endDate);

	/**
	 * 获取详细成交数据
	 * @param user
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map findDealDataByTime(User user, String startDate, String endDate);

	/************************************Web端Service***************************************/
	/**
	 * 获取项目任务完成进度
	 * @param user
	 * @param startTime
	 * @return
	 */
	Map<String, String> findProjectTaskFinishedExtent(User user, String anyDate);
	
}
