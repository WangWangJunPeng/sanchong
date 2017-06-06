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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
    </script>
     <title>成交客户</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">成交客户</h1>
	</header> 
	<div class="mui-content">
		<div class="mid">
			<c:forEach items="${data }" var="list">
			<div class="middle">
			<table  class="messageList" border="">
			<tbody>
				<tr>
					<td rowspan="2" >
						<div class="cusName">${list.customerName }</div>
						<c:if test="${list.customerPhone != null && list.customerPhone != ''}">
							<span class="cusPho">${list.customerPhone }</span>
						</c:if>
						<c:if test="${list.customerPhone == null || list.customerPhone == ''}">
							<span class="cusPho">暂无号码</span>
						</c:if>
					</td>
					<td class="midd">签约时间：<span>${list.contractConfirmTime }</span></td>
				</tr>
				<c:if test="${list.buyPrice != null && list.buyPrice != ''}">
							<tr><td class="midd">签约金额：<span>${list.buyPrice }元</span></td></tr>
				</c:if>
				<c:if test="${list.buyPrice == null || list.buyPrice == ''}">
							<tr><td class="midd">签约金额：<span>暂无签约金额</span></td></tr>
				</c:if>
			</tbody>
		</table>
		</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>