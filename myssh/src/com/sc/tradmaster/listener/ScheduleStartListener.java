package com.sc.tradmaster.listener;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.MiniMessageWaitSend;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.common.OrderWillExpiringSmsQuartz;
import com.sc.tradmaster.controller.common.OrderWillExpiringUpdateWaitSendMessage;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.dao.impl.BaseDaoImpl;
import com.sc.tradmaster.service.enterbuy.EnterBuyService;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.JavaSmsApi;
import com.sc.tradmaster.utils.ServiceHelper;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;


public class ScheduleStartListener implements ServletContextListener{	
	
	public void contextDestroyed(ServletContextEvent sce){
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			sched.shutdown();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void contextInitialized(ServletContextEvent sce){		
		try {
			recovery();
		}catch (Exception e){
		}
	}
	
	
	public void recovery() throws Exception{
		try {
			monitoringSMS();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 服务器运行时，监控短信发送任务
	 * @throws Exception
	 */
	public void monitoringSMS() throws Exception{
		System.out.println("--------------------开始定时任务---------------------");
		//查询待发短信表,检索未发送短信的所有数据
		ProjectService proSer = ServiceHelper.getProjectService();
		List<MiniMessageWaitSend> wmList = proSer.findMiniMessageWaitSendByHQL();
		if(!wmList.isEmpty()){
			for(MiniMessageWaitSend mw : wmList){
				Integer id = mw.getId();
				String date = mw.getDate();
				String text = mw.getText();
				String phone = mw.getPhone();
				String recordNo = mw.getRecordNo().toString();
				/*if(SysContent.doubleDateComper(date, DateTime.toString1(new Date()))){
					//调用短信发送接口，发送信息给申请人
					JavaSmsApi.sendSms(text, phone);
					OrderWillExpiringUpdateWaitSendMessage.updateTableInfo(id);
				}else{
					OrderWillExpiringSmsQuartz.addJob(date, recordNo, phone, text,id);//加入定时短信发送池
				}*/
				OrderWillExpiringSmsQuartz.addJob(date, recordNo, phone, text,id,null);//加入定时短信发送池
			}
		}
	}
}

