package com.sc.tradmaster.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.sc.tradmaster.utils.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;


public class BaseController{

	private int limit; // 每页显示多少行
	private int start; // 开始行

	
	protected HttpServletRequest request;  
    protected HttpServletResponse response;  
    protected HttpSession session;
      
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;
        this.session = request.getSession();
    }
	
	
	
	public void outJsonString(String str) {
		response.setContentType("text/text;charset=UTF-8");
		outString(str);
	}

	public void outString(String str) {
		if(response!=null){
			response.setContentType("text/text;charset=UTF-8");
		}
		try {
			PrintWriter out = response.getWriter();
			System.out.println(str);
			out.print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void outListString(List list) {
		String jsonString = null;
		try {
			JSONArray jsonArray = new JSONArray();
			if (list.size() > 0) {
				jsonArray = JSONArray.fromObject(list);
			}
			jsonString = "{total:" + list.size() + ",root:"
					+ jsonArray.toString() + "}";

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("=============================="+jsonString);
		outString(jsonString);
	}
	
	public void outSetString(Set set) {
		String jsonString = null;
		try {
			JSONArray jsonArray = new JSONArray();
			if (set.size() > 0) {
				jsonArray = JSONArray.fromObject(set);
			}
			jsonString = "{total:" + set.size() + ",root:"
					+ jsonArray.toString() + "}";

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("=============================="+jsonString);
		outString(jsonString);
	}

	public void outObjectString(Object obj,JsonConfig jsonConfig) {
		JSONObject josnobj = new JSONObject();
		if (obj != null) {
			try{
				if(null==jsonConfig){
					josnobj = JSONObject.fromObject(obj);
				}else{
					josnobj = JSONObject.fromObject(obj,jsonConfig);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		String jsonString = "{success:true,data:" + josnobj.toString() + "}";
		System.out.println("==================================================================="+jsonString);
		outString(jsonString);
	}

	public void outObjString(Object obj) {
		JSONArray jsonArray = new JSONArray();
		if (obj != null) {
			jsonArray = JSONArray.fromObject(obj);
		}
		String jsonString = "{success:true,data:" + jsonArray.toString() + "}";
		System.out.println(jsonString);
		outString(jsonString);
	}

	public void outPageString(Page page) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
	    jsonConfig.registerJsonValueProcessor(Date.class,  
	            new JsonValueProcessor() {  
	                @Override  
	                public Object processObjectValue(String paramString,  
	                        Object paramObject, JsonConfig paramJsonConfig) {  
	                    if (paramObject == null) {  
	                        return null;  
	                    }
	                    String ret = null;  
	                    try {
	                        SimpleDateFormat format = new SimpleDateFormat(  
	                                "yyyy-MM-dd HH:mm:ss");  
	                        ret = format.format((Date) paramObject);  
	                    } catch (Exception e) {  
	                        SimpleDateFormat format = new SimpleDateFormat(  
	                                "yyyy-MM-dd");  
	                        ret = format.format((Date) paramObject);
	                    }  
	                    return ret;  
	                }  
	  
	                @Override  
	                public Object processArrayValue(Object paramObject,  
	                        JsonConfig paramJsonConfig) {  
	                    return null;  
	                }  
	            });  
		JSONArray jsonArray = new JSONArray();
		if (page.getRoot().size() > 0) {
			jsonArray = JSONArray.fromObject(page.getRoot(),jsonConfig);
		}
		String jsonString = "{total:" + page.getTotal()+ ",currentPage:"
		+ page.getCurrentPage()+ ",currentResult:"
		+ page.getCurrentResult()+ ",totalPage:"
		+ page.getTotalPage() + ",root:"
				+ jsonArray.toString() + "}";
		//outString(jsonString);
		//System.out.println(jsonString);
		outString(jsonString);
	}
	
	/**
	 * list数据忽略查询
	 * @param list
	 * @param ingore
	 */
	public String outList1(List list){
		 JsonConfig jsonConfig = new JsonConfig();
	      jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		  jsonConfig.setExcludes(new String[]{ "students","instructor","headMaster"});

		JSONArray josnobj = new JSONArray();
		if (list != null) {
			try{
				if(null==jsonConfig){
					josnobj = JSONArray.fromObject(list);
				}else{
					josnobj = JSONArray.fromObject(list, jsonConfig);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	   String jsonString =  "{total:" + list.size() + ",root:"
			+ josnobj.toString() + "}";
		return jsonString;
	}
	
	
	
	public String outPageString(Page page,String[] ingore) {
		JsonConfig jsonConfig = new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(Date.class,  
	            new JsonValueProcessor() {  
	                @Override  
	                public Object processObjectValue(String paramString,  
	                        Object paramObject, JsonConfig paramJsonConfig) {  
	                    if (paramObject == null) {  
	                        return null;  
	                    }  
	                    String ret = null;  
	                    try {  
	                        SimpleDateFormat format = new SimpleDateFormat(  
	                                "yyyy-MM-dd HH:mm:ss");  
	                        ret = format.format((Date) paramObject);  
	                    } catch (Exception e) {  
	                        SimpleDateFormat format = new SimpleDateFormat(  
	                                "yyyy-MM-dd");  
	                        ret = format.format((Date) paramObject);  
	                    }  
	                    return ret;  
	                }  
	  
	                @Override  
	                public Object processArrayValue(Object paramObject,  
	                        JsonConfig paramJsonConfig) {  
	                    return null;  
	                }  
	            });  
		JSONArray jsonArray = new JSONArray();
		if (page.getRoot().size() > 0) {
			jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
			jsonConfig.setExcludes(ingore);
			jsonArray = JSONArray.fromObject(page.getRoot(),jsonConfig);
		}
		String jsonString = "{total:" + page.getTotal()+ ",currentPage:"
		+ page.getCurrentPage()+ ",currentResult:"
		+ page.getCurrentResult()+ ",totalPage:"
		+ page.getTotalPage() + ",root:"
				+ jsonArray.toString() + "}";
		return jsonString;
	}
	
	@SuppressWarnings("unchecked")
	public String outTreeJsonList(List list){
		JSONArray jsonArray = new JSONArray();
		if (list.size() > 0) {
			jsonArray = JSONArray.fromObject(list);
		}
		return jsonArray.toString();
	}

	public String outXMLString(String xmlStr) {
		response.setContentType("application/xml;charset=UTF-8");
		return xmlStr;
	}
	
	public String outError() {
		return "{success:false,errors:'操作失败！'}";
	}
	
	/**
	 * 字符串转换list<Map>值
	 * @param rsContent
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> outStringToList(String rsContent) throws Exception{
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray arry = new JSONArray();
		if(rsContent!=null && !rsContent.equals("")){
			arry = JSONArray.fromObject(rsContent,jsonConfig);
		}
        List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < arry.size(); i++)
        {
            JSONObject jsonObject = arry.getJSONObject(i);
            Map<String, String> map = new HashMap<String, String>();
            for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();)
            {
                String key = (String) iter.next();
                String value = jsonObject.get(key).toString();
                map.put(key, value);
            }
            rsList.add(map);
        }
        return rsList;
    }
	
	/**machine返回数据结构方法**/
	public void outMachineObjectString(Object obj,JsonConfig jsonConfig) {
		JSONObject josnobj = new JSONObject();
		if (obj != null) {
			try{
				if(null==jsonConfig){
					josnobj = JSONObject.fromObject(obj);
				}else{
					josnobj = JSONObject.fromObject(obj,jsonConfig);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		String jsonString = josnobj.toString();
		outString(jsonString);
	}
	
	public String outMachineListString(List list) {
		String jsonString = null;
		try {
			JSONArray jsonArray = new JSONArray();
			if (list.size() > 0) {
				jsonArray = JSONArray.fromObject(list);
			}
			jsonString =  jsonArray.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("=============================="+jsonString);
		return jsonString;
	}
	
	public void outMachinePageString(Page page) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
	    jsonConfig.registerJsonValueProcessor(Date.class,  
	            new JsonValueProcessor() {  
	                @Override  
	                public Object processObjectValue(String paramString,  
	                        Object paramObject, JsonConfig paramJsonConfig) {  
	                    if (paramObject == null) {  
	                        return null;  
	                    }
	                    String ret = null;  
	                    try {
	                        SimpleDateFormat format = new SimpleDateFormat(  
	                                "yyyy-MM-dd HH:mm:ss");  
	                        ret = format.format((Date) paramObject);  
	                    } catch (Exception e) {  
	                        SimpleDateFormat format = new SimpleDateFormat(  
	                                "yyyy-MM-dd");  
	                        ret = format.format((Date) paramObject);
	                    }  
	                    return ret;  
	                }  
	  
	                @Override  
	                public Object processArrayValue(Object paramObject,  
	                        JsonConfig paramJsonConfig) {  
	                    return null;  
	                }  
	            });  
		JSONArray jsonArray = new JSONArray();
		if (page.getRoot().size() > 0) {
			jsonArray = JSONArray.fromObject(page.getRoot(),jsonConfig);
		}
		String jsonString = "{returnCode : 200, total:" + page.getTotal()+ ",currentPage:"
		+ page.getCurrentPage()+ ",currentResult:"
		+ page.getCurrentResult()+ ",totalPage:"
		+ page.getTotalPage() + ",root:"
				+ jsonArray.toString() + "}";
		//outString(jsonString);
		//System.out.println(jsonString);
		outString(jsonString);
	}
	/*******end************/
	
	public ServletContext getServletContext() {
		if (request != null) {
			return request.getSession().getServletContext();
		}
		return null;
	}

	public String getRealyPath(String path) {
		return getServletContext().getRealPath(path);
	}
	
	public int getLimit() {
		if(0==limit){
			limit = 20;
		}
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

}
