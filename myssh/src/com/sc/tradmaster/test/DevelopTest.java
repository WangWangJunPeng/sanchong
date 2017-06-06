package com.sc.tradmaster.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.LogRecords;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.ProjectPics;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.controller.common.OrderWillExpiringSmsQuartz;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.dao.impl.BaseDaoImpl;
import com.sc.tradmaster.service.project.impl.dto.SeeBuyApplyDTO;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.JavaSmsApi;
import com.sc.tradmaster.utils.LogRecordsUtill;
import com.sc.tradmaster.utils.PropertiesUtil;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;
import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DevelopTest {

	@Resource(name = "baseDao")
	private static BaseDao baseDao;

	@Test
	public void test() {

	}

	public static void main(String[] args) throws Exception {
		/*
		 * Calendar cal = Calendar.getInstance(); Date time1 = cal.getTime();
		 * //下面的就是把当前日期加12个月 cal.add(Calendar.MONTH, 12); Date time2 =
		 * cal.getTime(); int v = time2.compareTo(time1);//time2-time1
		 * System.out.println(v);
		 */

		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * String s = "2017-02-03 05:55:02"; Date d = sdf.parse(s);
		 * System.out.println(d); Calendar cal = Calendar.getInstance();
		 * cal.setTime(d); System.out.println(cal);
		 * System.out.println(cal.getTime());
		 */

		/*
		 * DecimalFormat df = new DecimalFormat("#.##"); Double d = 2.33889;
		 * System.out.println(df.format(d));
		 */

		/*
		 * BaseController bc = new BaseController(); List list = new
		 * ArrayList<>(); for(int i = 0;i<3;i++){ list.add(new
		 * SeeBuyApplyDTO()); } Map<String,Object> map = new HashMap<>();
		 * map.put("house", new House()); map.put("list",list );
		 * System.out.println(map); bc.outObjectString(map, null);
		 */

		/*
		 * Double v = (double) DateUtil.getOffsetMinute(new
		 * Date(),DateUtil.parse("2017-02-16 23:30:00")); Double d = (double)
		 * (v/1000/3600); DecimalFormat df = new DecimalFormat("#.##");
		 * System.out.println(d); System.out.println(df.format(d));
		 */

		/*
		 * Map<String,Object> map = new HashMap<>(); int houseNum = 123;
		 * map.put("houseKey", houseNum); map.put("'"+houseNum+"'", new
		 * ArrayList<>()); System.out.println(map.get(map.get("houseKey")));
		 */

		/*
		 * System.out.println("--------------------开始定时任务---------------------")
		 * ; String date = DateTime.toString1(DateUtil.parse(
		 * "2017-02-17 12:54:00")); String recordNo = "1122356"; String phone =
		 * "15639196571"; //您好！来自#userName#的购买申请，房源名称为#houseName#，已经打款，请及时确认！
		 * String content = SmsContext.PLAY_MONEY_ENTER.replace("#userName#",
		 * "testSms").replace("#houseName#", "1栋2单元201");
		 * OrderWillExpiringSmsQuartz.addJob(date, recordNo, phone, content);
		 */

		/*
		 * String maxPalyMoneyTimeStr = SysContent.addSameHours(
		 * "2017-02-17 21:30:00", 4);//最晚打款时间 String date =
		 * SysContent.addSameMinute(maxPalyMoneyTimeStr, -30);//短信发送时间
		 * System.out.println(date);
		 */

		/*
		 * Boolean v = SysContent.doubleDateComper("2017-02-17 20:00:00",
		 * "2017-02-17 21:00:00"); System.out.println(v);
		 */
		/*
		 * String s = SysContent.getEndTime2(DateUtil.parse(
		 * "2017-03-12 20:02:30")); System.out.println(s);
		 */

		/*
		 * BaseDao baseDao = new BaseDaoImpl();
		 * 
		 * VisitRecords vr = new VisitRecords();//实例化对象，属性值为null
		 * 
		 * if(vr!=null){ System.out.println("持久化前"+vr.getVisitNo());//结果为null }
		 * 
		 * baseDao.saveOrUpdate(vr);//调用数据库保存方法，id为自增，这时的vr在数据库中id是存在
		 * 
		 * if(vr!=null && !vr.getVisitNo().equals(0) && vr.getVisitNo()!=null){
		 * System.out.println("持久化后："+vr.getVisitNo());
		 * //这里的vr是持久前的对象还是持久后的对象，自增的id可以取到吗？ }
		 */
		// String str = DateTime.getAddSameWeeksNewDay("2017-011-05
		// 13:30:00",2);
		// System.out.println(str);
		/*
		 * System.out.println(SysContent.getEndTime2(new Date()));
		 * System.out.println(SysContent.getStartTime2(new Date()));
		 */

		/*
		 * TreeSet set = new TreeSet<>(); set.add("一室 一厅"); set.add("一室 一厅");
		 * set.add("一室 一厅"); set.add("一室 一厅"); set.add("两室 一厅"); BaseController
		 * bs = new BaseController(); bs.outSetString(set);
		 */

		/*
		 * String token =
		 * "123456789wqw-20170302182907886-10f0ad37efdb4c27bf677a9b11906878";
		 * String userId = token.substring(0, token.indexOf('-'));
		 * System.out.println(userId);
		 */
		//
		// String mdStr = encryptToMD5("1234564");
		// System.out.println(mdStr);

		// System.out.println(Integer.TYPE);
		//
		//
		// String mdStr = encryptToMD5("1234564");
		// System.out.println(mdStr);
		// System.out.println(Integer.TYPE);
		/*
		 * System.out.println(Integer.TYPE);
		 * 
		 * String mdStr = encryptToMD5("1234564"); System.out.println(mdStr);
		 * 
		 * String mdStr = encryptToMD5("1234564"); System.out.println(mdStr);
		 */

		/*
		 * String proPicsId = "[aaa]"; List<String> urlList = new ArrayList<>();
		 * if(proPicsId!=null && !proPicsId.equals("")){ //ProjectPics pps =
		 * (ProjectPics) baseDao.loadById(ProjectPics.class, proPicsId);
		 * //if(pps!=null){ String picUrl = proPicsId; picUrl =
		 * picUrl.substring(1, picUrl.length()-1); if(picUrl.indexOf(",")!=-1){
		 * String[] picUrlStr = picUrl.split(","); urlList =
		 * Arrays.asList(picUrlStr); }else{ urlList.add(picUrl); } //}
		 * for(String s : urlList){ System.out.println(s); } }
		 */
		// String str = " dfadf ";
		// System.out.println(str.trim());

		/*
		 * ProjectGuide pg = new ProjectGuide(); pg.setIsDaiKan(0);
		 * pg.setCustormerProtectDays("20"); //pg.setDaiKanMoney(0.3);
		 * pg.setDaiKanMoney(0.5); pg.setDescription("qq"); GuideRecords grs =
		 * new GuideRecords(); grs.setRecordNo(1); grs.setRules(pg.toString());
		 * //System.out.println(grs); //System.out.println(grs.getRules());
		 * HashMap grMap = JSON.parseObject(grs.getRules(), HashMap.class);
		 * System.out.println(grMap);
		 * System.out.println(grMap.get("daiKanMoney"));
		 */
		// {projectId:10000, isAvailable:null, validDays:10, payType:null,
		// rewardMoney:null,
		// rewardpercent:null, description:null, isDaiKan:1, isYiDi:1,
		// yiDiSalesCommission:0.0,
		// yiDiValidity:3, isFast:1, daiKanMoney:0.01, fenXiaoMoney:0.02,
		// custormerProtectDays:5}

		// 对象json转换
		/*
		 * ProjectGuide pg = new ProjectGuide(); pg.setProjectId("10000");
		 * pg.setValidDays(10); pg.setIsDaiKan(1); pg.setIsYiDi(1);
		 * pg.setYiDiSalesCommission(0.0); pg.setYiDiValidity("3");
		 * pg.setIsFast(1); pg.setDaiKanMoney(0.01); pg.setFenXiaoMoney(0.02);
		 * pg.setCustormerProtectDays("5"); JSONObject json =
		 * JSONObject.fromObject(pg); System.out.println(json.toString());
		 * HashMap mapStr = JSON.parseObject(json.toString(), HashMap.class);
		 * System.out.println(mapStr);
		 */

		// 短信接口，模版测试
		/*
		 * //订购申请 提醒案场助理及时审核 public static String BUY_APPLY =
		 * "您收到一笔来自#userName#的购买申请，购买的房源名称为#houseName#，价格为#money#元，请及时审核！";
		 * //订购申请 同意订购 提醒申请人通知客户 public static String AGREE_BUY_APPLY =
		 * "您申请#projectName#项目，房源名称为#8#，客户姓名：#customerName#，已经被#state#，请及时处理！";
		 * //订购申请 拒绝订购 提醒申请人通知客户 public static String REFUSE_BUY_APPLY =
		 * "您申请#projectName#项目，房源名称为#8#，客户姓名：#customerName#，已经被#state#，原因为：#reason#";
		 * //打款确认 提醒案场助理及时确认 public static String PLAY_MONEY_ENTER =
		 * "您好！来自#userName#的购买申请，房源名称为#houseName#，已经打款，请及时确认！"; //打款即将到期提醒
		 * 提醒申请人通知客户及时大款 public static String PLAY_MONEY_WILL_OUT_TIME =
		 * "您的申请# projectName #项目，房源名称为#houseName#，客户姓名：# customerName #，已经被#state#，打款即将超时，请及时处理！"
		 * ;
		 */
		/*
		 * String str1 =
		 * "【九邑房源在线】您收到一笔来自#置业顾问/中介经纪人#的购买申请，购买的房源名称为中信，价格为10万元，请及时审核！";//#置业顾问/
		 * 中介经纪人#内容无法替换
		 * 
		 * String str2 =
		 * "【九邑房源在线】您申请#projectName#项目，房源名称为#8#，客户姓名：#customerName#，已经被#state#，请及时处理！";
		 * String text = SmsContext.AGREE_BUY_APPLY; JavaSmsApi.sendSms(str2,
		 * "15639196571"); //JavaSmsApi.sendSms(str, "15558011520");
		 * System.out.println(text);
		 */

		/*
		 * String a = DateTime.toString1(new Date()); String v =
		 * DateTime.toString1(new Date()); int d = 10; Boolean b
		 * =SysContent.addSameDaysComperWithToday(a,v,d); System.out.println(b);
		 */

		// Map map = new HashMap<>();
		// map.put("key1", 1234);
		// map.put("key2", 5558);
		// map.put("key3", 8454);

		/*
		 * JSONObject json = JSONObject.fromObject(map);
		 * System.out.println(json); String k = json.get("key2").toString();
		 */
		// System.out.println(map);

		// List list = new ArrayList<>();
		// boolean isValue = list.isEmpty();
		// System.out.println(list);
		// System.out.println(!isValue);
		//
		// List list = new ArrayList<>();
		// boolean isValue = list.isEmpty();
		// System.out.println(list);
		// System.out.println(!isValue);
		/*
		 * List list = new ArrayList<>(); boolean isValue = list.isEmpty();
		 * System.out.println(list); System.out.println(!isValue);
		 */

		// String pw = "2059";
		// String password = SysContent.md5(pw);
		// System.out.println(password);

		/*
		 * //加密字符串 String str = SysContent.md5("123456"); String pww =
		 * SysContent.md5(str); System.out.println(pww);
		 */

		/*
		 * Double dou = 3.00; String d = SysContent.get2Double(dou);
		 * System.out.println(d);
		 */
		/*
		 * String realEnterBuyManId = "[1,2,3,4,5]"; List list =
		 * JSON.parseArray(realEnterBuyManId); System.out.println(list); for(int
		 * i=0;i<list.size();i++){ System.out.println(list.get(i)); }
		 */

		/*
		 * Boolean b = SysContent.doubleDateComper("2017-05-01 08:00:00",
		 * "2017-05-01 14:00:00"); System.out.println(b);
		 */
	}

	// 加密算法
	public static String encryptToMD5(String info) {
		byte[] digesta = null;
		try {
			// 得到一个md5的消息摘要
			MessageDigest alga = MessageDigest.getInstance("MD5");
			// 添加要进行计算摘要的信息
			alga.update(info.getBytes());
			// 得到该摘要
			digesta = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 将摘要转为字符串
		String rs = byte2hex(digesta);
		return rs;
	}

	public static String byte2hex(byte[] md5Bytes) {
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

}
