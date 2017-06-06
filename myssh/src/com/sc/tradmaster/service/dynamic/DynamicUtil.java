package com.sc.tradmaster.service.dynamic;

import java.util.Date;

import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.utils.DateTime;

public class DynamicUtil {
	public static Mydynamic createOneDynamic(String userId,String phone,String mdcStr,Integer isRead,
			String creatTime,String proId,String hosueNum,String status,String isDelete,String proCusId,String pcName,String cusPhone,String proName){
		Mydynamic mdc = new Mydynamic();
		mdc.setUserId(userId);
		mdc.setUserPhone(phone);
		mdc.setDynamicInfo(mdcStr);
		mdc.setIsRead(isRead);
		mdc.setCreatTime(creatTime);
		mdc.setProjectId(proId);
		if(hosueNum!=null && !hosueNum.equals("")){
			mdc.setHosueNum(hosueNum);
		}
		mdc.setDynamicStatus(status);
		mdc.setIsDelete(isDelete);
		mdc.setProjectCustomerId(proCusId);
		mdc.setProjectName(pcName);
		mdc.setCustomerPhone(cusPhone);
		mdc.setProjectName(proName);
		return mdc;
	}
}
