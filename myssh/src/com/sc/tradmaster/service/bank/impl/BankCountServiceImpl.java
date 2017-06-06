package com.sc.tradmaster.service.bank.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sc.tradmaster.bean.BankCount;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.bank.BankCountService;
import com.sc.tradmaster.utils.SysContent;

@Service("bankCountService")
public class BankCountServiceImpl implements BankCountService {

	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	@Override
	public void addBankCount(BankCount p,User u) {
		p.setBankId(SysContent.uuid());
		p.setProjectId(u.getParentId());
		baseDao.saveOrUpdate(p);
	}

	@Override
	public void dropBankCountById(String id) {
		baseDao.deleteById(BankCount.class, id);
	}

	@Override
	public BankCount findBankCountById(String id) {
		return null;
	}

	@Override
	public List<BankCount> findAllBankCounts(String pId) {
		String hql = "from BankCount where projectId = '"  + pId + "'";
		return baseDao.findByHql(hql);
	}

}
