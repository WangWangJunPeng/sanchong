package com.sc.tradmaster.utils;

public class SmsContext {
	//订购申请 提醒案场助理及时审核
	public static String BUY_APPLY = "您收到一笔来自#userName#的购买申请，购买的房源名称为#houseName#，价格为#money#元，请及时审核！";
	//订购申请 同意订购 提醒申请人通知客户
	public static String AGREE_BUY_APPLY = "您申请#projectName#项目，房源名称为#8#，客户姓名：#customerName#，已经被#state#，请及时处理！";
	//订购申请 拒绝订购 提醒申请人通知客户
	public static String REFUSE_BUY_APPLY = "您申请#projectName#项目，房源名称为#8#，客户姓名：#customerName#，已经被#state#，原因为：#reason#";
	//打款确认 提醒案场助理及时确认(ok)
	public static String PLAY_MONEY_ENTER = "您好！来自#userName#的购买申请，房源名称为#houseName#，已经打款，请及时确认！";
	//打款即将到期提醒  提醒申请人通知客户及时大款
	public static String PLAY_MONEY_WILL_OUT_TIME = "您的申请#projectName#项目，房源名称为#houseName#，客户姓名：#customerName#，已经被#state#，打款即将超时，请及时处理！";
	//催单
	public static String CALL_ORDER = "您的项目关于#housName#房源的订单，订单号为：#No#，#userRole##userName#请求您审核，请尽快处理！";
	
	//文件上传，路径来源设置
	public static String AD_PIC = "adPic";
	public static String CR_PIC = "recordContractPic";
	public static String HT_PIC = "houseTypePic";
	public static String EF_PIC = "effectPic";
	public static String SHOP_PIC = "shopsPic";
	public static String USERS_PIC = "usersPic";
	public static String WSC_PIC = "willSaleCardPic";
	public static String FB_PIC = "feedBackPic";
	public static String VERSION = "product";
}
