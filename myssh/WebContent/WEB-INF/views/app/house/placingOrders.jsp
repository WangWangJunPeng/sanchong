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
     <title>下定客户</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">下定客户</h1>
	</header> 
	<div class="mui-content" style="padding-top:1.6rem;">
		<table class="messageList" border="" cellspacing="" cellpadding="">
			<thead>
				<tr>
					<th>项目</th>
					<th>客户姓名</th>
					<th>剩余</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${data }" var="list">
					<tr>
						<td>${list.projectName }</td>
						<td>${list.customerName }</td>
						<td>${list.residueHours }</td>
						<td>
						<c:if test="${list.residueHours !='已过期' &&  list.residueHours !='审核中' && list.residueHours !='已打款'}">
							<a href="to_jumpUpload?recordNo=${list.recordNo }">上传凭证</a>
							<br>
							<a href="to_cancelCR?recordNo=${list.recordNo }">取消</a>
						</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>