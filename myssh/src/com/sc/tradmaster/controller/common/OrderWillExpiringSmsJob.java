package com.sc.tradmaster.controller.common;

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
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.utils.JavaSmsApi;
import com.sc.tradmaster.utils.ServiceHelper;

public class OrderWillExpiringSmsJob implements Job {
	
	public OrderWillExpiringSmsJob() {
		
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		TriggerKey triggerKey = context.getTrigger().getKey();
		JobKey jobKey = context.getJobDetail().getKey();
		JobDataMap data = context.getJobDetail().getJobDataMap();
		try { 
			String phone = data.getString("phone");
			String content = data.getString("content");
			Integer id = (Integer) data.get("id");
			JavaSmsApi.sendSms(content, phone);
			updateTableInfo(id);
			System.out.println("------------------短信已发送-------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				removeJob(jobKey,triggerKey);
				System.out.println("------------------定时任务已结束------------------");
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
	
	public static void updateTableInfo(Integer id){
		ProjectService proSer = ServiceHelper.getProjectService();
		MiniMessageWaitSend mmws = proSer.findMessageById(id);
		mmws.setIsSendMiniMessage("yes");
		proSer.addOrupdateMessageSendStatus(mmws);
	}
	
}