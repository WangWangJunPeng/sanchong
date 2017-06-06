<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <link href="<%=request.getContextPath()%>/static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/termList.css"/>
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    
    <script type="text/javascript">
    	function getHaveRead(){
    		window.location.href="to_goToRenGouPage";
    	}
    </script>
	<title>条款</title>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<h1 class="mui-title">条款</h1>
	</header>
	<div class="mui-content">
		<div class="list">
			<h2>认购规则</h2>
			<p>${enterBuy.enterBuyRule }</p>
		</div>
	</div>
	<div class="btn-success mui-bar mui-bar-tab">
			<div class="midde">
				<button type="button" class="success" onclick="getHaveRead()">我已阅读</button>
			</div>
	</div>
</body>
</html>

