<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <link href="static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/app.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/spareResult.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/customerList.css"/>
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$("#suibian").click(function(){
    			//alert("sss");
    			window.location.href="toCustomerList";
    		})
    	})
    </script>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
			<a  id="suibian" class="mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">${tName}</h1>
		</header>
	
		<div class="mui-content">
			<div id="tabbar">
			<c:forEach items="${cList}" var="c">
				<a  href="findCustomersInformation?projectCustomerId=${c.projectCustomerId}&index=${index}">
				<section class="clientMsg" id="${c.projectCustomerId}">
					<div class="leftBox">
						<div class ="cusName">${c.projectCustomerName}</div>
					</div>
					<div class="rightBox">
						<p>
							<img src="<%=request.getContextPath()%>/static/images/name.png" />
							姓名：${c.projectCustomerName}
						</p>
										
						<p>
							<img src="<%=request.getContextPath()%>/static/images/phone.png" />
							电话：${c.projectCustomerPhone}
						</p>					
										
					</div>
				</section>	
				</a>
			
			</c:forEach>
				
				
			</div>
		</div>
</body>
</html>