package com.sc.tradmaster.controller.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.sc.tradmaster.bean.MiniMessageWaitSend;
import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.service.dynamic.DynamicUtil;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.JavaSmsApi;
import com.sc.tradmaster.utils.LogRecordsUtill;
import com.sc.tradmaster.utils.ServiceHelper;

public class OrderWillExpiringUpdateWaitSendMessage implements Job {

	public OrderWillExpiringUpdateWaitSendMessage() {
		
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		TriggerKey triggerKey = context.getTrigger().getKey();
		JobKey jobKey = context.getJobDetail().getKey();
		JobDataMap data = context.getJobDetail().getJobDataMap();
		try { 
			Object map = data.get("myMap");
			if(map!=null){
				updateTableInfo(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				removeJob(jobKey,triggerKey);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void removeJob(JobKey jobKey, TriggerKey tiKey) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.pauseTrigger(tiKey);
		// 停止触发器
		sched.unscheduleJob(tiKey);
		// 删除任务
		sched.deleteJob(jobKey);
	}
	
	public static void updateTableInfo(Object m){
		Map map = (Map) m;
		ProjectService proSer = ServiceHelper.getProjectService();
		String userId = map.get("uId").toString();
		String phone = map.get("uPhone").toString();
		String mdcStr = map.get("msg").toString();
		String proId = map.get("pId").toString();
		String hosueNum = map.get("hNum").toString();
		String proCusId = null;
		if(map.get("pcId")!=null){
			proCusId = map.get("pcId").toString();
		}
		String pcName = map.get("pcName").toString();
		String cusPhone = map.get("cPhone").toString();
		String proName = map.get("pName").toString();
		Mydynamic outTimeDynamic = DynamicUtil.createOneDynamic(userId, phone, mdcStr, 0, DateTime.toString1(new Date()), proId, 
				hosueNum, "打款超时", "0", proCusId,pcName,cusPhone, proName);
		proSer.addorUpdateMyDynamic(outTimeDynamic);
	}

}
