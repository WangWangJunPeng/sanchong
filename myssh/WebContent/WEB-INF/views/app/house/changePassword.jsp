<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="utf-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <link href="<%=request.getContextPath()%>/static/css/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/changePwd.css" />
	
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/js/mui.min.js "></script>
	<script type="text/javascript">
		function getChange(){
			$.ajax({
				type:"post",
				url:"to_updatePassword",
				data:{password:$('#password').val(),
					truepassword : $('#truePassword').val()},
				success :function(data){
					data = eval("("+data+")");
					getToChange(data.data);
				}
			});
		}
		function getToChange(data){
			if (data.jump == "true"){
				window.location.href="app/tologin";
			} else {
				showAlert();
			}
		}
		
		function showAlert () {
			$('.mask').addClass('maskDiv');
			$('.boxbox').css("display","block");
		}
		
		function closeMask () {
			$('.mask').removeClass('maskDiv');
			$('.boxbox').css("display","none");
		}
		
	</script>
	
    <title>设置密码</title>
    
</head>
<body>
<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">设置密码</h1>
	</header> 
	<div class="mask"></div>
	<div class="boxbox" >
		<p>两次密码输入不一致</p>
		<button class="cancleBtn" onclick="closeMask()">确定</button>
	</div>
		
	<div class="mui-content">
		<form action="" >
			<ul class="mui-table-view mui-table-view-chevron">
				<li class="mui-table-view-cell">
					<label for="password">密码</label>
					<input type="password" name="password" id="password"  placeholder="请设置新密码"/>
				</li>
			</ul>
			<ul class="mui-table-view mui-table-view-chevron">
				<li class="mui-table-view-cell">
					<label for="truePassword">确认密码</label>
					<input type="password" name="truepassword" id="truePassword"  placeholder="请再次输入"/>
				</li>
			</ul>
			<button type="button" id="success" onclick="getChange()">完成</button>
		</form>	
	</div>
	
</body>
</html>
