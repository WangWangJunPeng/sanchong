<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-案场信息维护</title>
        <link rel="stylesheet" type="text/css" href="static/css/messageProtect.css" />
         <link rel="stylesheet" type="text/css" href="static/css/validation.css" />
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="static/lib/laydate/need/laydate.css" />
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/jquery.validate.js"></script>
         <script type="text/javascript" src="static/js/validation.js"></script>
        <script type="text/javascript" src="static/lib/laydate/laydate.js"></script>
        <script src="static/js/city2.js"></script>
        <script type="text/javascript" src="static/js/citySelect2.js"></script>
        <script type="text/javascript" src="static/js/messageProtect.js"></script>
        <script type="text/javascript">
	        $(document).ready(function() {
				getCurrentEnterBuyInfo();
			});
	        function getCurrentEnterBuyInfo(){
	        	$.ajax({
	       			type:"post",
	       			async:false,
	       			url:"get_current_enter_buy_info",
	       			success:function(data){
	       				data = eval("("+data+")");
	       				fillEnterBuyInfo(data.data);
	       			}
	       		});
	        }
	        function fillEnterBuyInfo(data){
	        	$("#enterBuyId").val(data.enterBuyId);
	        	if(data.isSupportBuy=="1"){
	        		$("#checkIsSupportBuy").prop("checked",true);
	        	}else{
	        		$("#checkIsSupportBuy").prop("checked",false);
	        	}
				$("#dposit").val(data.dPositStr);
				$("#enterBuyRule").val(data.enterBuyRule);
	        }
        </script>
    </head>
    <body>
        <!-- <div class="header">
            <a href="###" class="self-center">案场助理个人中心</a>
            <ul>
                <li><a href="###">首页</a></li>
               <li><a href="###">案场信息维护</a></li>
               <li><a href="###">价格优惠条款</a></li>
               <li><a href="###">房源管理</a></li>
               <li><a href="###">上下架管理</a></li>
               <li><a href="###">客户管理</a></li>
               <li><a href="###">成交业务</a></li>
               <li><a href="###">账号管理</a></li>
            </ul>
            <a href="###" class="welcome">欢迎您，某某某</a>
        </div> -->
        <%@include file="publicHeader.jsp" %>
        <div class="container">
            <div class="home-messageProtect">
                <p style="font-size:14px;color:#0c95db;"><a href="to_pro_index" style="font-size:12px;color:#0c95db;">首页</a>&nbsp;-&nbsp;案场信息维护</p>
            </div>
            <%@include file="basicSide.jsp" %>
             <!-- <div class="side-bar">
                <ul>
                    <li><a href="">基本信息</a></li>
                    <li><a href="">效果图</a></li>
                    <li><a href="">户型</a></li>
                    <li><a href="">银行账号</a></li>
                    <li><a href="">带看业务定义</a></li>
                    <li><a href="">预售证管理</a></li>
                    <li><a href="" style="color:#199ed8">认购规则</a></li>
                </ul>
            </div> --> 
            <div class="main-content">
                  <div class="buy-rule" >
                        <form action="add_or_update_enter_buy" method="post" class="form-box" id="buyVerify">
                            <div class="form-div">
                            	<input type="hidden" id="enterBuyId" name="enterBuyId"/>
                                <label class="form-label">是否支持认购</label>
                                <input type="checkbox" class="form-input "  name="checkIsSupportBuy" id="checkIsSupportBuy" />
                                <span>支持认购</span>
                            </div>
                            <div class="form-div">
                                <label class="form-label">超时时间</label>
                                <input type="text" class="form-input over-time" readonly="ture" name="outOfTime"  value="4" />
                                <span>小时</span>
                            </div>  
                            <div class="form-div">
                                <label class="form-label">定金<b>*</b></label>
                                <input type="text" class="form-input " name="dposit" id="dposit" />
                                <span>元</span>
                            </div> 
                             <div class="form-div"> 
                                <label  class="form-label">认购规则</label>
                                <textarea id="enterBuyRule" name="enterBuyRule"  cols="50" rows="5"></textarea>
                            </div>     
                            <div class="btn">
                                <input type="submit" class="btn1" value="保存" />
                            </div>                     
                        </form>
                    </div>        
            </div>
            </div> 
           
        </body>
    </html>