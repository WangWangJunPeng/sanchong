package com.sc.tradmaster.service.enterbuy.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.enterbuy.EnterBuyService;
import com.sc.tradmaster.utils.SysContent;

/**
 * 
 * @author grl 2017-01-16
 *
 */
@Service("enterBuyService")
public class EnterBuyServiceImpl implements EnterBuyService {

	@Resource(name="baseDao")
	private BaseDao baseDao;
	@Override
	public void addOrupdateEnterBuy(EnterBuy eb,User proId) {
		if(proId.getParentId()!=null && !proId.equals("")){
			if(eb.getEnterBuyId()==null || eb.getEnterBuyId().equals("")){
				eb.setEnterBuyId(SysContent.uuid());
			}
			eb.setProjectId(proId.getParentId());
			baseDao.saveOrUpdate(eb);
		}
	}
	@Override
	public EnterBuy findById(User user) {
		if(user.getParentId()!=null && !user.getParentId().equals("")){
			String hql = "from EnterBuy where projectId = '" + user.getParentId() +"'";
			EnterBuy eb = (EnterBuy) baseDao.loadObject(hql);
			eb.setdPositStr(SysContent.get2Double(eb.getDposit()));
			return eb;
		}else{
			return null;
		}
		
	}
	@Override
	public EnterBuy findByProId(String proId) {
		if(proId!=null && !proId.equals("")){
			String hql = "from EnterBuy where projectId = '" +proId+"'";
			return (EnterBuy) baseDao.loadObject(hql);
		}else{
			return null;
		}
	}

}
