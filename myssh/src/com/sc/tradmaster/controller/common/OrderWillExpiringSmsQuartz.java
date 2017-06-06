package com.sc.tradmaster.controller.common;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.SysContent;

import net.sf.json.JSONObject;

public class OrderWillExpiringSmsQuartz {
	
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	
	
	//date执行节点时间  recordNo订购id phone短信发送的手机号 content发送的短信内容
	public static void addJob(String date, String recordNo,String phone,String content,Integer id,HashMap map)throws Exception {
		removeJob(recordNo,id);
		Scheduler scheduler = schedulerFactory.getScheduler();
		//可以通过SchedulerFactory创建一个Scheduler实例	
		//设置工作详情	
		JobDetail job = newJob(OrderWillExpiringSmsJob.class).withIdentity("recordNoSmsTask_"+recordNo, "recordNoSmsTaskGroup_"+recordNo).requestRecovery().build(); 	
		job.getJobDataMap().put("activityid", recordNo);
		job.getJobDataMap().put("phone", phone);
		job.getJobDataMap().put("content", content);
		job.getJobDataMap().put("id", id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(date);
		//Date转String	//设置触发器
		SimpleTrigger trigger = (SimpleTrigger)newTrigger().withIdentity("recordNoSmsTask_"+recordNo, "recordNoSmsTaskGroup_"+recordNo).startAt(startDate).build();
		scheduler.scheduleJob(job, trigger);
		scheduler.start();
		
		if(map!=null){
			Scheduler schedulerUpTable = schedulerFactory.getScheduler();
			JobDetail jobUpTable = newJob(OrderWillExpiringUpdateWaitSendMessage.class).withIdentity("recordNoSmsTask_"+id, "recordNoSmsTaskGroup_"+id).requestRecovery().build();
			jobUpTable.getJobDataMap().put("myMap", map);
			Date outdate = sdf.parse(SysContent.addSameMinute(date, 30));
			//Date outdate = sdf.parse(SysContent.addSameMinute(DateTime.toString1(new Date()), 1));
			SimpleTrigger triggerUpTable = (SimpleTrigger)newTrigger().withIdentity("recordNoSmsTask_"+id, "recordNoSmsTaskGroup_"+id).startAt(outdate).build();
			schedulerUpTable.scheduleJob(jobUpTable, triggerUpTable);
			schedulerUpTable.start();
		}
			
	}
	
	public static void removeJob(String recordNo,Integer id) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		JobKey jobKey = JobKey.jobKey("recordNoSmsTask_"+recordNo, "recordNoSmsTaskGroup_"+recordNo);
		TriggerKey triggerKey = TriggerKey.triggerKey("recordNoSmsTask_"+recordNo, "recordNoSmsTaskGroup_"+recordNo);
		if(sched.checkExists(triggerKey)){
			sched.pauseTrigger(triggerKey);// 停止触发器  
	        sched.unscheduleJob(triggerKey);// 移除触发器  
		}
		if(sched.checkExists(jobKey)){
	        sched.deleteJob(jobKey);// 删除任务  
		}
		
		//移除信息更新定时
		SchedulerFactory sfUp = new StdSchedulerFactory();
		Scheduler schedUp = sfUp.getScheduler();
		JobKey jobKeyUp = JobKey.jobKey("recordNoSmsTask_"+id, "recordNoSmsTaskGroup_"+id);
		TriggerKey triggerKeyUp = TriggerKey.triggerKey("recordNoSmsTask_"+id, "recordNoSmsTaskGroup_"+id);
		if(schedUp.checkExists(triggerKeyUp)){
			schedUp.pauseTrigger(triggerKeyUp);// 停止触发器  
			schedUp.unscheduleJob(triggerKeyUp);// 移除触发器  
		}
		if(schedUp.checkExists(jobKeyUp)){
			schedUp.deleteJob(jobKeyUp);// 删除任务  
		}
	}
	
		
}