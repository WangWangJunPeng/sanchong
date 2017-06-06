<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <meta charset="utf-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <link href="<%=request.getContextPath()%>/static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/spareResult.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/my customer.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
      	
      	function getDoubleValue(){
      		
      		var projectCustomerId = $(this).data("value");
      		$("#houseNum").val();
      		
      		$.ajax({
      			type : "post",
      			url : "to_chooseSaleCustomer",
      			data : {houseNum : $("#houseNum").val(),projectCustomerId : projectCustomerId }
      		});
      	}
      	
      	
    </script>
     <title>我的客户</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">我的客户</h1>
	</header> 
	<div class="mui-content">
	
	<input type="hidden" id="houseNum" value="${dataInfo }">
	<c:if test="${fn:length(data)>0}">
		<c:forEach items="${data }" var="list">
			<section class="messagelist">
				<img src="<%=request.getContextPath()%>/static/images/headliu.png" alt="" />
				<div class="middle">
					<p>
						<img src="<%=request.getContextPath()%>/static/images/7.png" alt="" />
						<span>联系人:</span><span >${list.projectCustomerName }</span>
						<span>
							
						</span>
					</p>
					<p>
						<img src="<%=request.getContextPath()%>/static/images/8.png" alt="" />
						<span>电话:</span><span >${list.projectCustomerPhone }</span>
					</p>
				</div>
				<a class="selected-btn" data-vlaue="${list.projectCustomerId }" href="to_chooseSaleCustomer?houseNum=${dataInfo }&projectCustomerId=${list.projectCustomerId }">选中</a>
			</section>
		</c:forEach>
	</c:if>
	<c:if test="${fn:length(data)==0}">
			<div style="text-align:center">暂无客户可选</div>
		</c:if>
	</div>
</body>
</html>

