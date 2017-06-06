package com.sc.tradmaster.service.bank;

import java.util.List;

import com.sc.tradmaster.bean.BankCount;
import com.sc.tradmaster.bean.User;

public interface BankCountService {
	//添加项目
	public void addBankCount(BankCount p,User u);
	//通过id删除该项目
	public void dropBankCountById(String id);
	//通过id查询该项目
	public BankCount findBankCountById(String id);
	//查询所有项目
	public List<BankCount> findAllBankCounts(String pId);
}
