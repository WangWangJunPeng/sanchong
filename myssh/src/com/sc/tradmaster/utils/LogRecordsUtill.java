package com.sc.tradmaster.utils;

import com.sc.tradmaster.bean.LogRecords;
import com.sc.tradmaster.service.logRecords.LogRecordsService;
/**
 * 日志记录  通用方法
 * @author grl
 *
 */
public class LogRecordsUtill {
	public static void creatLogRecords(LogRecords log){
		LogRecordsService logSer = ServiceHelper.getLogRecordsService();
		logSer.addNewLogRecords(log);
	}
}
