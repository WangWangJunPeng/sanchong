<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

	<head>
		<meta charset="utf-8">
		<title>我的客户</title>
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/myClient.css"/>
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
		<script type="text/javascript">
   		 mui.init();
   		</script>
	</head>

	<body class="mui-ios mui-ios-9 mui-ios-9-1" style="padding-top:0;">
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">我的客户</h1>
		</header>
	
		<div class="mui-content">
			<div id="tabbar">
			<c:forEach items="${scList }" var="list">
				<section class="clientMsg">
					<div class="leftBox">
						<div class ="cusName">${list.shopCustomerName}</div>
					</div>
					
					<div class="rightBox">
						<p>
							<img src="<%=request.getContextPath()%>/static/images/1.png" />
							<span>${list.shopCustomerName }</span>
						</p>
										
						<p>
							<img src="<%=request.getContextPath()%>/static/images/12.png" />
							<span>${list.shopCustomerPhone }</span>
						</p>
										
						<p>
							<img src="<%=request.getContextPath()%>/static/images/12.png" />
							<span>${list.idCard }</span>
						</p>									
										
					</div>
					<c:if test="${list.isChoose == 1 }">
						<a class="chooseLink" href="to_goToChooseMidCustomer?shopCustomerId=${list.shopCustomerId }">选中</a>
					</c:if>
					<c:if test="${list.isChoose != 1 }">
						<a class="chooseLink" href="###" style="background:#999;">选中</a>
					</c:if>
					<!-- <a class="chooseLink">选中</a> -->
				</section>
			</c:forEach>
			</div>
		</div>
		

	</body>
	
	


</html>

