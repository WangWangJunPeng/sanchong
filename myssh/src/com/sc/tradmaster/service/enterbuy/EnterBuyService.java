package com.sc.tradmaster.service.enterbuy;

import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.User;
/**
 * 
 * @author grl 2017-01-16
 *
 */
public interface EnterBuyService {
	/**
	 * 添加或更新认购规则
	 * @param proId
	 */
	void addOrupdateEnterBuy(EnterBuy eb, User user);
	/**
	 * 通过通过用户管理的项目id获取该项目的认购规则信息
	 * @param user
	 * @return
	 */
	EnterBuy findById(User user);
	/**
	 * 通过项目id获取认购规则信息
	 * @param user
	 * @return
	 */
	EnterBuy findByProId(String proId);
}
