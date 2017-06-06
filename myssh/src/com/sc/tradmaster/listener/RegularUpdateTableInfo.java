package com.sc.tradmaster.listener;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.service.enterbuy.EnterBuyService;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.ServiceHelper;
import com.sc.tradmaster.utils.SysContent;

public class RegularUpdateTableInfo {
	
	Runnable runnable = new Runnable() {  
        public void run() {  
        	try {
				updateOutTimeContractRecordHouseInfo();
			} catch (ParseException e) {
				e.printStackTrace();
			} 
        }  
    };  
    
	public RegularUpdateTableInfo() {
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间 ,时间单位均为秒
		service.scheduleAtFixedRate(runnable,60, 300, TimeUnit.SECONDS);
	}
	
	private void updateOutTimeContractRecordHouseInfo() throws ParseException{
		ProjectService proSer = ServiceHelper.getConRecService();
		EnterBuyService enterBuySer = ServiceHelper.getEnterBuyService();
		HouseService houseSer = ServiceHelper.getHouseService();
		//同意认购的，所有未打款的用户
		String conrecHql = "from ContractRecords where recordStatus = 1 and auditingTime is not null"
				+ " and isOut is null and (remitNo is null or voucherUploadTime is null)";
		List<ContractRecords> crList = proSer.findConRecListByHql(conrecHql);
		if(crList!=null){
			for(ContractRecords cr : crList){
				cr.setIsOut("yes");
				proSer.updateConRec(cr);
				EnterBuy eb = (EnterBuy) enterBuySer.findByProId(cr.getProjectId());
				int outTime = Integer.parseInt(eb.getOutOfTime());
				long time = DateUtil.parse(SysContent.addSameHours(cr.getAuditingTime(), outTime)).getTime();
				if(new Date().getTime()>time){
					House h = houseSer.findHouseByNum(cr.getHouseNum());
					h.setHouseStatus(0);
					houseSer.addOrUpdate(h);
				}
			}
		}
		
	}
 }
