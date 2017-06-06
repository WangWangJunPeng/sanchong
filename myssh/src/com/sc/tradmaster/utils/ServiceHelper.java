package com.sc.tradmaster.utils;

import com.sc.tradmaster.service.enterbuy.EnterBuyService;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.logRecords.LogRecordsService;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.user.UserService;

/**
 * @author grl
 * 获取Spring容器中的service bean
 */
public final class ServiceHelper {
	
	public static Object getService(String serviceName){
		return SysContent.WEB_APP_CONTEXT.getBean(serviceName);
	}
	
	public static ProjectService getProjectService(){
		return (ProjectService) getService("projectService");
	}
	
	public static UserService getUserService(){
		return (UserService) getService("userService");
	}
	
	public static HouseService getHouseService(){
		return (HouseService) getService("houseService");
	}
	
	public static EnterBuyService getEnterBuyService(){
		return (EnterBuyService) getService("enterBuyService");
	}
	
	public static ProjectService getConRecService(){
		return (ProjectService) getService("projectService");
	}
	
	public static LogRecordsService getLogRecordsService(){
		return (LogRecordsService) getService("logRecordsService");
	}
	
	
}
