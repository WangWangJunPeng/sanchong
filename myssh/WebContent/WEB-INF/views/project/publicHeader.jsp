<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-案场信息维护</title>
        <link rel="stylesheet" type="text/css" href="static/css/messageProtect.css" />
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <!-- <link rel="stylesheet" type="text/css" href="static/lib/laydate/need/laydate.css" /> -->
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/lib/laydate/laydate.js"></script>
        <script type="text/javascript" src="static/js/jquery.validate.js"></script>
        <!-- <script src="static/js/citsy2.js"></script>
        <script type="text/javascript" src="static/js/citySelect2.js"></script> -->
        <script type="text/javascript" src="static/js/messageProtect.js"></script>
        
    </head>
    <body>
        <div class="header" style="position:relative;">
            <a href="to_pro_index" class="self-center">案场助理个人中心</a>
            <ul id="allLi">
               <li class="singleLi"><a href="to_pro_index" >首页</a></li>
               <li class="singleLi"><a href="to_pro_baseInfo">案场信息维护</a></li>
               <li class="singleLi"><a href="to_benefitsManage">价格优惠条款</a></li>
               <li class="singleLi"><a href="to_house_manage_page">房源管理</a></li>
               <li class="singleLi"><a href="to_selectHouseUpDown">上下架管理</a></li>
               <li class="singleLi"><a href="pro_customer_manager_page_info">客户管理</a></li>
               <li class="singleLi"><a href="###" id="business">成交业务</a></li>
               <li class="singleLi"><a href="to_agent_bill_page">对账单</a></li>
               <li class="singleLi"><a href="to_account_manager_page">账号管理</a></li>
            </ul>
           
	            <a href="#" class="welcome" >欢迎您：${sessionScope.userInfo.userCaption}</a>
	            <ol style="position:absolute;right:5px;top:40px;z-index:100;width:80px;border:1px solid #999;height:50px;background:#fff;display:none;" class="olDiv">
	            	<li style="display:block;height:15px;width:80px;text-align:center;line-height: 30px;"><a href="to_edit_passworld" style="color:#666;font-size:12px;display:block;height:20px;width:80px;">修改密码</a></li>
	            	<li style="display:block;height:15px;width:80px;text-align:center;line-height: 50px;"><a href="web_logout" style="color:#666;font-size:12px;display:block;height:20px;width:80px;">退出登录</a></li>
	            </ol>
         
        </div>
     </body>
     <script>
     $(document).ready(function(){
    	changeColor();
    	$(".welcome").click(function(){
    		$(".olDiv").slideToggle("slow");
    	});
    	$("#business").click(function(){
    		getBusinessUrl();
    	});
     })
     function changeColor(){
    	var url = window.location.href;
     	var index =  url.lastIndexOf("/");
	   	var lastUrl = url.substring(index+1);
    	
    	$("#allLi a").each(function(){
    		
    		if($(this).attr("href") == lastUrl){
    			$(this).css("color","#0c95db");
    		}
    	})
     }
     
     function getBusinessUrl(){
    	 $.ajax({
 			type : "post",
 			url : "basicInfoIsFull",
 			//data : {userId : data,doSign : "reset"},
 			success : function(data) {
 				if(data.returnCode=="200"){
 					window.location.href = data.url;
 				}else{
 					alert("请先完善基本信息...");
 					window.location.href = data.url;
 				}
 			}
 		});
     }
     </script>
     </html>