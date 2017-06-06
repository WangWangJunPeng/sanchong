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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/MyInstall.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/my.css" />	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/myMaterial.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
   	<script type="text/javascript">
   	$(document).ready(function() {
		getDateInfo();
	});
	function getDateInfo(){
		$.ajax({
			type : "post",
			url : "to_getUserInfo",
			success : function(data) {
				data = eval("("+data+")");
				renderDateInfo(data.data);
			},
			/* error:function(){
				alert("信息获取失败");
			} */
		});
	}
	function renderDateInfo(data){
		$(".userName").html(data.userName);
		if (data.photo != null && data.photo != "") {
			$("#photo").attr('src',data.photo);
		}
		$("#userPhone").html(data.userPhone);
		$("#idCard").html(data.idCard);
	}
   	</script>
   	
    <title>个人资料</title>
</head>
<body>
		<header class="mui-bar mui-bar-transparent">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" style="color:#333;"></a>
		</header>
			<!--页面主内容区开始-->
			<div class="mui-content">

			<div class="top">
				<div class="people">
					<img src="<%=request.getContextPath()%>/static/images/APPheadphoto.jpg" alt="" id="photo"/>
					<p class="userName"></p>
				</div>
			</div>
					<div class="line">
							<p>
								<img src="<%=request.getContextPath()%>/static/images/7.png" alt="" />
								姓名<span class="userName"></span>
							</p>
					</div>
					<div class="line">
							<p>
								<img src="<%=request.getContextPath()%>/static/images/8.png" alt="" />
								电话 <span id="userPhone"></span>
							</p>
					</div>
					<div class="line">
							<p>
								<img src="<%=request.getContextPath()%>/static/images/12.png" alt="" />
								身份证号码 <span id="idCard"></span>
							</p>
					</div>
			</div>
	
</body>
	<script src="<%=request.getContextPath()%>/static/js/mui.min.js "></script>
</html>
