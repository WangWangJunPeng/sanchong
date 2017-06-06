package com.sc.tradmaster.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 短信http接口的java代码调用示例
* 基于Apache HttpClient 4.3
*
* @author songchao
* @since 2015-04-03
*/

public class JavaSmsApi {

    //查账户信息的http地址
    private static String URI_GET_USER_INFO = "https://sms.yunpian.com/v2/user/get.json";

    //智能匹配模板发送接口的http地址
    private static String URI_SEND_SMS = "https://sms.yunpian.com/v2/sms/single_send.json";

    //模板发送接口的http地址
    private static String URI_TPL_SEND_SMS = "https://sms.yunpian.com/v2/sms/tpl_single_send.json";

    //发送语音验证码接口的http地址
    private static String URI_SEND_VOICE = "https://voice.yunpian.com/v2/voice/send.json";

    //绑定主叫、被叫关系的接口http地址
    private static String URI_SEND_BIND = "https://call.yunpian.com/v2/call/bind.json";

    //解绑主叫、被叫关系的接口http地址
    private static String URI_SEND_UNBIND = "https://call.yunpian.com/v2/call/unbind.json";

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";
    
    private static final String apikey = "7d6d0b0044f74fa2eb59b37104689c39";

    public static void main(String[] args) throws IOException, URISyntaxException {

        //修改为您的apikey.apikey可在官网（http://www.yunpian.com)登录后获取
        String apikey = "7d6d0b0044f74fa2eb59b37104689c39";

        //修改为您要发送的手机号
        String mobile = "15639196571";

        /**************** 查账户信息调用示例 *****************/
        System.out.println(JavaSmsApi.getUserInfo(apikey));

        /**************** 使用智能匹配模板接口发短信(推荐) *****************/
        /**
         //订购申请 提醒案场助理及时审核(ok)
			public static String BUY_APPLY = "您收到一笔来自#userName#的购买申请，购买的房源名称为#houseName#，价格为#money#元，请及时审核！";
			//订购申请 同意订购 提醒申请人通知客户(ok)
			public static String AGREE_BUY_APPLY = "您申请#projectName#项目，房源名称为#housName#，客户姓名：#8#，已经被#state#，请及时处理！";
			//订购申请 拒绝订购 提醒申请人通知客户(ok)
			public static String REFUSE_BUY_APPLY = "您申请#projectName#项目，房源名称为#8#，客户姓名：#customerName#，已经被#state#，原因为：#reason#";
			//打款确认 提醒案场助理及时确认(ok)
			public static String PLAY_MONEY_ENTER = "您好！来自#userName#的购买申请，房源名称为#houseName#，已经打款，请及时确认！";
			//打款即将到期提醒  提醒申请人通知客户及时大款(ok)
			public static String PLAY_MONEY_WILL_OUT_TIME = "您的申请#projectName#项目，房源名称为#houseName#，客户姓名：# customerName #，已经被#state#，打款即将超时，请及时处理！";
         */
        //设置您要发送的内容(内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）
        //String text = SmsContext.BUY_APPLY.replace("#userName#", "grl").replace("#houseName#", "金屋").replace("#money#", "10万");
        //String text = SmsContext.AGREE_BUY_APPLY.replace("#projectName#", "sc").replace("#8#", "和田碧海三栋2单元302").replace("#customerName#", "suz").
        //		replace("#state#", "同意");
        //String text = SmsContext.REFUSE_BUY_APPLY.replace("#projectName#", "sc").replace("#8#", "123456").replace("#customerName#", "grl").replace("#state#", "拒绝").replace("#reason#", "qianshao");
        //String text = SmsContext.PLAY_MONEY_ENTER.replace("#userName#", "grl").replace("#houseName#", "123456");
        String text = SmsContext.PLAY_MONEY_WILL_OUT_TIME.replace("#projectName#", "1231328564").replace("#houseName#", "5555").
        		replace("#customerName#", "grl").replace("#state#", "同意");
        System.out.println(text);
        //发短信调用示例
        System.out.println(JavaSmsApi.sendSms(text, mobile));
    }

    /**
    * 取账户信息
    *
    * @return json格式字符串
    * @throws java.io.IOException
    */

    public static String getUserInfo(String apikey) throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        return post(URI_GET_USER_INFO, params);
    }

    /**
    * 智能匹配模板接口发短信
    *
    * @param apikey apikey
    * @param text   　短信内容
    * @param mobile 　接受的手机号
    * @return json格式字符串
    * @throws IOException
    */

    public static String sendSms(String text, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post(URI_SEND_SMS, params);
    }

    /**
    * 通过模板发送短信(不推荐)
    *
    * @param apikey    apikey
    * @param tpl_id    　模板id
    * @param tpl_value 　模板变量值
    * @param mobile    　接受的手机号
    * @return json格式字符串
    * @throws IOException
    */

    public static String tplSendSms(String apikey, long tpl_id, String tpl_value, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("tpl_id", String.valueOf(tpl_id));
        params.put("tpl_value", tpl_value);
        params.put("mobile", mobile);
        return post(URI_TPL_SEND_SMS, params);
    }

    /**
    * 通过接口发送语音验证码
    * @param apikey apikey
    * @param mobile 接收的手机号
    * @param code   验证码
    * @return
    */

    public static String sendVoice(String apikey, String mobile, String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("mobile", mobile);
        params.put("code", code);
        return post(URI_SEND_VOICE, params);
    }

    /**
    * 通过接口绑定主被叫号码
    * @param apikey apikey
    * @param from 主叫
    * @param to   被叫
    * @param duration 有效时长，单位：秒
    * @return
    */

    public static String bindCall(String apikey, String from, String to , Integer duration ) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("from", from);
        params.put("to", to);
        params.put("duration", String.valueOf(duration));
        return post(URI_SEND_BIND, params);
    }

    /**
    * 通过接口解绑绑定主被叫号码
    * @param apikey apikey
    * @param from 主叫
    * @param to   被叫
    * @return
    */
    public static String unbindCall(String apikey, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("from", from);
        params.put("to", to);
        return post(URI_SEND_UNBIND, params);
    }

    /**
    * 基于HttpClient 4.3的通用POST方法
    *
    * @param url       提交的URL
    * @param paramsMap 提交<参数，值>Map
    * @return 提交响应
    */

    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
            try {
                HttpPost method = new HttpPost(url);
                if (paramsMap != null) {
                    List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                    for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                        NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                        paramList.add(pair);
                    }
                    method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
                }
                response = client.execute(method);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseText = EntityUtils.toString(entity, ENCODING);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return responseText;
        }
    
}
