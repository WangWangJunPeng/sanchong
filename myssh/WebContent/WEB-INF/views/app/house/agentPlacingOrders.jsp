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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <style type="text/css">
.messageList {
	width: 100%;
}
.messageList th:first-child ,.messageList td:first-child {
	width: 20%;
}
.second {
	width: 45%;
}
.messageList th, .messageList td {
	height: .93rem;
	text-align: center;
}
.messageList th, td {
	font-size: .3466rem;
}
    </style>
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
    </script>
     <title>下定客户</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">下定客户</h1>
	</header> 
	<div class="mui-content">
		<table class="messageList" border="" cellspacing="" cellpadding="">
			<thead>
				<tr>
					<th>客户姓名</th>
					<th class="second">案场/房源</th>
					<th>过期时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${data }" var="list">
					<tr>
						<td>${list.customerName }</td>
						<td class="second">${list.projectName }${list.buildingNo }${list.unit }${list.houseNo }</td>
						<td>${list.residueHours }</td>
						<td>
						<c:if test="${list.residueHours != '已过期' && list.residueHours != '审核中' && list.residueHours !='已打款'}">
							<a href="to_saleToJumpUpload?recordNo=${list.recordNo }">确定</a>
						</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>