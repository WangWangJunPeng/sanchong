package com.sc.tradmaster.service.logRecords.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sc.tradmaster.bean.LogRecords;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.logRecords.LogRecordsService;

/**
 * 日志记录
 * @author grl
 *
 */
@Service("logRecordsService")
public class LogRecordsServiceImpl implements LogRecordsService {
	@Resource(name = "baseDao")
	private BaseDao baseDao;

	@Override
	public void addNewLogRecords(LogRecords log) {
		baseDao.saveOrUpdate(log);
		
	}
	
	
}
