package com.sc.tradmaster.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * properties文件读取工具
 * @author grl 2017-01-01
 *
 */
public class PropertiesUtil {
	private static Properties pros = new Properties();
	static{
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("dbconfig.properties");
		try{
			pros.load(in);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		String val = pros.getProperty(key);
		if(val == null){
			return "";
		}
		return val;
	}
	
}
