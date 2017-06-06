<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <script src="static/js/flexible.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.js"></script>
    <link href="static/css/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/subscriptionApplication.css"/>
    <link rel="stylesheet" type="text/css" href="static/css/agentIndexApp.css"/>
    <link rel="stylesheet" href="static/css/suitable.css" />
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/additional-methods.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/validateidCard.js" type="text/javascript"></script>
    <script type="text/javascript">
	    $(document).ready(function(){
			getAllMytask();
			getDateNow();
			getFooterTop();
			confirm();
			$("#myform").validate({
   				debug:true,
   			 	rules: {
   			 	phone: {
   			 			phone_number:true
   			 		}
   			 	},
   			 	message: {
   			 	phone: {
   			 			phone_number:"手机号码格式错误"
   			 		}
   			 	}
   			 });
		});
	    
	    function confirm(){
	    	$(".btn-confirm").click(function(){
	    		var phpneNub = $(".phoneinp").val();
	    		var visitNo = $(this).attr("data-value");
	    		console.log(visitNo);
	    		if($(".phoneinp").val().length=="11"){
	    			window.location.href="newCustomerEdit?phone="+phpneNub+"&visitNo="+visitNo+"&index="+1;
	    		}else{
	    			return false;
	    		}
	    	})
	    }
	    function alertbox(){
	    	$(".btn-add").click(function(){
	    		//console.log($(this).attr("data-value"))
	    		$(".alertbox").css("display","block");
	    		$(".popup-backdrop").css("display","block");
	    		$(".btn-confirm").attr("data-value",$(this).attr("data-value"));
	    	})
	    	
	    	$(".btn-addTwo").click(function(){
	    		var phone = $(this).attr("data-phone");
	    		var visitNo = $(this).attr("data-value");
	    		window.location.href="findCustomersInformation_phone?phone="+phone+"&visitNo="+visitNo+"&index="+1;
	    	})
	    	btnclose();
	    	
	    	
	    }
	    function btnclose(){
	    	$(".btn-close").click(function(){
	    		$(".alertbox").css("display","none");
	    		$(".popup-backdrop").css("display","none");
	    		$(".phoneinp").val("");
	    		$("#phone-error").remove();
	    	})
	    }
	    function getFooterTop(){
    		$("#btn-search").focus(function(){
    			$("#footerbar").css("top",$(document.body).height()-$("#footerbar").height());
    		})
    	}
	    function getDateNow(){
	    	var d = $("#selectData").val();
	    	if(d==""){
	    		var data = new Date();
		    	date1 = data.getDate();
		    	$(".dateTime").html(date1);
	    	}else{
	    		console.log(d);
	    		date1 = d.substring(d.lastIndexOf("-")+1);
	    		$(".dateTime").html(date1);
	    	}
	    	
	    }
	    function getAllMytask(){
	    	var d = $("#selectData").val();
	    	 if(d==""){
	    	    var date = new Date();
	    	    var month = date.getMonth() + 1;
	    	    var strDate = date.getDate();
	    	    if (month <= 9) {
	    	        month = "0" + month;
	    	    }
	    	    if (strDate <= 9) {
	    	        strDate = "0" + strDate;
	    	    }
	    		d = date.getFullYear() + "-" + month + "-" + strDate;
	    	}
	    	
	    	$.ajax({ 
   				type:"post",
   				url:"all_my_task_list",
   				data : {dataStr:d},
   				success:function(data){
   					data=eval("("+data+")");
   					console.log(data);
   					initTaskCount(data.total);
   					initCustomerInfo(data.root);
   					/* success(); */
   					alertbox();
   				}
   			});
	    }
	    //任务数
	    function initTaskCount(data){
	    	$("#taskCount").text(data);
	    }
	    
	    //待填写客户信息
	    function initCustomerInfo(data){
	    	var s="<thead><tr><th>姓名</th><th>到访时间</th><th>人数</th><th>操作</th></tr></thead>";
	    	$.each(data,function(v,o){
	    		if(o.customerName!=""){
	    			s+='<tbody><tr><td>'+o.customerName+'</td>';
	    		}else{
	    			s+='<tbody><tr><td>未知</td>';
	    		}
       			s+='<td>'+o.arriveTime.substring(5,16)+'</td>';
       			s+= '<td>'+o.customerCount+'</td>';
       			if(o.phone==null || o.phone==''){
       				s+='<td><button id="promptBtn'+v+'" class="btn-add" type="button" data-value='+o.visitNo+'>添加</button></td></tr></tbody >';	
       			}else{
       				s+='<td><button id="hadPhoneBtn'+v+'" class="btn-addTwo" type="button" data-value='+o.visitNo+' data-phone='+o.phone+'>添加</button></td></tr></tbody >';
       				
       			}
       			
       		});
       		
       		if(data.length>0){
				$("#visitCustomerInfo").html(s);
			} else{
				$(".nomessage").css("display","block");
			} 
	    }
	    
    </script>
    <title>置业顾问首页</title>
</head>
<body>
	<div class="mui-content">
		<div class="calendar">
			<a href="to_calendar_page">
				<img src="static/images/calendar.png" />
			</a>
		</div>
		<div class="messages">
			<input id="selectData" type="hidden" value="${date}">
			<div class="messageNub">
				<span id="taskCount" class="Nub"></span>
			</div>
			<div class="messageHeader">
				<img src="static/images/messageHeader.png"/>
			</div>
			<div class="meaaageList">
				<table id="visitCustomerInfo"> 
				</table>
				<!--任务完成-->
				<div class="nomessage" style="display: none;">
					<img src="static/images/noMessage.png" alt="" />
					<span class="dateTime"></span>
					<p>
						<img src="static/images/icon-yes.png" alt="" />
						恭喜，您已完成任务啦
					</p>
				</div>
			</div>			
		</div>
	</div>
		<nav class="mui-bar mui-bar-tab">
    	<a class="mui-tab-item mui-active" id="select">
        	<span class="mui-icon mui-icon-list"></span>
        	<span class="mui-tab-label">任务</span>
    	</a>
    	<a class="mui-tab-item" id="house">
        	<span class="mui-icon mui-icon-home"></span>
        	<span class="mui-tab-label">房源</span>
    	</a>
   		 <a class="mui-tab-item" id="service">
   	    	<span class="mui-icon mui-icon-chatboxes"></span>
        	<span class="mui-tab-label">业务</span>
    	</a>
    	<a class="mui-tab-item" id="my">
       		<span class="mui-icon mui-icon-person"></span>
        	<span class="mui-tab-label">我的</span>
    	</a>
    	</nav>
    <!-- 输入电话框 -->
    <div class="alertbox" style="display: none;">
    	<form id="myform">
    		<div class="contain">
    			<p>请输入手机号</p>
    		<input class="phoneinp" name="phone" type="text" placeholder="请输入手机号">
    		<div class="btngroup">
    			<button type="button" class="btn-close">取消</button>
    			<button type="button" class="btn-confirm">确认</button>
    		</div>
    		</div>
    	</form>
    </div>
    <!--遮罩层-->
	<div class="popup-backdrop" style="display: none;"></div>
	<script src="static/js/mui.min.js"></script>
	<script type="text/javascript">
	//初始化
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
			if (!$("#select").hasClass('mui-active')) {
				document.getElementById('select').addEventListener('tap', function() {
	 			 //打开关于页面
	  			mui.openWindow({
	   				url: 'to_goToAgentIndex', 
	   				show: {
	   					autoShow:false
	   				},
	    			id:'info'
	  				});
	  			});
			}
  			document.getElementById('house').addEventListener('tap', function() {
 			 //打开关于页面
  			mui.openWindow({
   				url: 'to_goToSaleHouseDetail', 
    			id:'info'
  				});
  			});
  			document.getElementById('service').addEventListener('tap', function() {
 			 //打开关于页面
  			mui.openWindow({
   				url: 'to_goToAgentMyService', 
    			id:'info'
  				});
  			});
  			document.getElementById('my').addEventListener('tap', function() {
 			 //打开关于页面
  			mui.openWindow({
   				url: 'to_goToAgentMyPerson', 
    			id:'info'
  				});
			});
	</script>
</body>
</html>