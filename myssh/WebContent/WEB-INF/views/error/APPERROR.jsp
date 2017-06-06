<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
   	 	<meta content="yes" name="apple-mobile-web-app-capable">
		<meta content="yes" name="apple-touch-fullscreen">
		<meta content="telephone=no,email=no" name="format-detection">
		<script src="static/js/flexible.js" type="text/javascript"></script>
		<link type="text/css" rel="stylesheet" href="static/css/errorPage.css" />
		<title>错误页</title>
		<script type="text/javascript">
			function gotoLogin(){
				window.location.href = "errorPageToLoginPage";
			}
		</script>
	</head>
	<body>
		<div id="content">
			<div class="qqimage">
				<img src="static/images/errorIcon.png" />
			</div>
			<h1>与服务器连接发生异常</h1>
			<button class="backBtn" onclick="gotoLogin()">返回登录</button>
		</div>
	</body>
</html>