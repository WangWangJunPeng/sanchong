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
    <link href="<%=request.getContextPath()%>/static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/spareResult.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/protocol.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
    </script>
     <title>购买协议</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">购买协议</h1>
	</header> 
	<div class="mui-content">
		<form action="to_agreedOrRefuse" method="post">
			<input name="houseNum" type="hidden" value="${data.houseNum }">
			<input name="customerName" type="hidden" value="${data.customerName }">
			<input name="customerPhone" type="hidden" value="${data.customerPhone }">
			<input name="buyPrice" type="hidden" value="${data.buyPrice }">
			<input name="projectCustomerId" type="hidden" value="${data.projectCustomerId }">
			<h3>购买申请审批后,需要缴纳的定金</h3>
			<p>金额为:<span>${data.dposit }</span></p>
			<h4>购买说明</h4>
			<p>${data.description }</p>
			<h4>在购买申请前请仔细阅读并同意下面的条款</h4>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar tempor. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus sapien nunc eget.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar tempor. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus sapien nunc egettellus mollis orci, sed rhoncus sapien nunc egettellus mollis orci, sed rhoncus sapien nunc egettellus mollis orci, sed rhoncus sapien nunc eget.egettellus mollis orci, sed rhoncus sapien nunc egettellus mollis orci, sed rhoncus sapien nunc egettellus mollis orci, sed rhoncus sapien nunc eget.</p>
			<button class="mui-btn mui-btn-primary" type="submit">同意</button>
			<button class="mui-btn mui-action-back">拒绝</button>
		</form>
	</div>
</body>
</html>

