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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/Record customer.css"/>
    <%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/Upcoming customer.css" /> --%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
      	
      	function getToInfo(data){
			window.location.href="findCustomersInformation?projectCustomerId="+data+"&index=10"
      	}
    </script>
     <title>到访客户</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class=" mui-icon mui-icon-left-nav mui-pull-left" href="to_goToAgentMyService"></a>
		<h1 class="mui-title">到访客户</h1>
	</header> 
	<div class="mui-content">
		<div class="mid">
			<c:forEach items="${data }" var="list">
			<div class="middle" onclick="getToInfo('${list.projectCustomerId}')">
			<table  class="messageList" border="">
			<tbody>
				<tr>
					<td rowspan="2" >
						<div class="cusName">${list.projectCustomerName }</div>
						<c:if test="${list.projectCustomerPhone != null && list.projectCustomerPhone != ''}">
							<span class="cusPho">${list.projectCustomerPhone }</span>
						</c:if>
						<c:if test="${list.projectCustomerPhone == null || list.projectCustomerPhone == ''}">
							<span class="cusPho">暂无号码</span>
						</c:if>
							
					</td>
					<td class="midd">到访时间：<span>${list.lastTime }</span></td>
				</tr>
					<tr><td class="midd">客户意向：<span>${list.yixiang }</span></td></tr>
					
			</tbody>
			</table>
			</div>
			</c:forEach>
		</div>
		
	</div>
</body>
</html>
