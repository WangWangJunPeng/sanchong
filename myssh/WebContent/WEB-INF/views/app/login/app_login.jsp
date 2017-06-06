<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <title>登录</title>
      <meta charset="utf-8">
      <meta content="yes" name="apple-mobile-web-app-capable">
	  <meta content="yes" name="apple-touch-fullscreen">
	  <meta content="telephone=no,email=no" name="format-detection">
	  <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
      <link rel="stylesheet" type="text/css" href="../static/css/loginAPP.css">
   </head>
   <body style="height:96.28% !important;">
   <!-- 内容区 -->
   <div class="contain">
   <!-- logo -->
      <div class="logo">
      	<div class="logoMid"><img src="../static/images/logoAPP.png" alt=""></div>
      </div>
   <!-- form -->
   <div class="login-box">
   		 <input id="sessionOutTime" type="hidden" value="${noSession}">
   		 <form id="uesrMsg">
      	 <input style="background:transparent;" type="hidden" name="sign" value="app"  autocomplete="off"/>
         <i class="icon1"><img src="../static/images/userCap.png" alt=""></i><input type="text" class="userCaption" name="phone"  placeholder="手机号码登录">
         <i class="icon2"><img src="../static/images/passWordIcon.png" alt=""></i><input type="password" class="password" name="password"  placeholder="密码">
         <p id="msgInfo"></p>
         <input id="appLogin" type="button" class="btn-login" value="登录"  autocomplete="off"/>
     	 </form>
     	 <p class="forgotPas"><img alt="" src="../static/images/questuinIcon.png">忘记密码</p>
   </div>      
   </div>
<script src="../static/js/mui.min.js"></script>
<script type="text/javascript" src="../static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../static/js/jquery.cookie.js"></script>
<script>
		function setupWebViewJavascriptBridge(callback) {
	        if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
	        if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
	        window.WVJBCallbacks = [callback];
	        var WVJBIframe = document.createElement('iframe');
	        WVJBIframe.style.display = 'none';
	        WVJBIframe.src = 'https://__bridge_loaded__';
	        document.documentElement.appendChild(WVJBIframe);
	        setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0)
	    }

	    setupWebViewJavascriptBridge(function(bridge) {
			bridge.registerHandler('quitAccount', function(data, responseCallback) {
				responseCallback(responseData)
			})	
		})
</script>
<script  type="text/javascript">
$(document).ready(function() {
	//session超时时判断是ios系统还是android系统，跳转响应的登录页面
	toH5PageOrIOSPage();
	if($.cookie("userName")!=""&&$.cookie("password")!=""){
		
		var user =$.cookie("userName")
		var pass =$.cookie("password")
		console.log($.cookie("userName"))
		$(".userCaption").val(user);
		$(".password").val(pass);
	}
	$('body').height($('body')[0].clientHeight+50);
	$("#appLogin").click(function(){
		$.ajax({
            //cache: true,
            type: "POST",
            url:"../applogin",
            data:$('#uesrMsg').serialize(),// 你的formid
            async: false,
            error: function(request) {
            	
            },
            success: function(data) {
            	//成功
            	if(data.returenCode==200){
            		setpas();
            		window.location.href = "../"+data.skipURL;
            	}
            	//没有权限
            	if(data.returenCode==401){
            		$("#msgInfo").text(data.msg);
            	}
            	//用户密码错误
            	if(data.returenCode==402){
            		//alert(data.msg);
            		$("#msgInfo").text(data.msg);
            	}
            }
        });

	});
	//判断浏览是否支持H5缓存
	function setpas(){
		if(window.localStorage){
			// 获取缓存里面的数据
			var userCaptionVal = JSON.stringify($(".userCaption").val())
			var passwordVal = JSON.stringify($(".password").val())
			localStorage.setItem("userName", userCaptionVal);
			localStorage.setItem("password", passwordVal);
			var user = JSON.parse(localStorage.getItem("userName"));
	 		var pwd = JSON.parse(localStorage.getItem("password"));
			$(".userCaption").val(user);
			$(".password").val(pwd);
		}else{
			$.cookie("userName",$(".userCaption").val(),{
    			expires:7,
    			path: "/"
    		})
    		$.cookie("password",$(".password").val(),{
    			expires:7,
    			path: "/"
    		})
		};
	};
	
	
});

function toH5PageOrIOSPage(){
	console.log($("#sessionOutTime").val());
	if($("#sessionOutTime").val()=="201"){
		var isIos = /iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream;
		if (isIos){
			 WebViewJavascriptBridge.callHandler('quitAccount',null,function(response) {
	                 alert(response);
	      	});
		}
	}
}
</script>
</body>

</html>