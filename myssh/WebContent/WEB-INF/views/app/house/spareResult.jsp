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
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
	
    </script>
    
     <title>备案结果</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">备案结果</h1>
	</header> 
	<div class="mui-content">
		<div class="titlebar">
			<div class="line">
				<div class="successTitle">备案成功项目：<span id="successLength">${dataSuccess }</span></div>
				<div class="failedTitle">备案失败项目：<span id="failLength">${dataFail }</span></div>
			</div>
		</div>
		<div class="mesSuccess">
		<div class="line">
			<h3>备案成功项目</h3>
			<table border="1" cellspacing="" cellpadding="">
				<tbody id="selectSuccess">
				<tr>
					<td>项目名称</td>
					<td>有效期截止</td>
				</tr>
				<c:forEach items="${data }" var="grdtoList">
					<c:if test="${grdtoList.applyStatus == 0 }">
						<tr>
							<td>${grdtoList.projectName }</td>
							<td>${grdtoList.validity }</td>
						</tr> 
						
					</c:if>
				</c:forEach>
				</tbody>
			</table>
		</div>
		</div>
		
		<div>
			<input type="hidden" value="${dateInfoOne }" id="allProjectId" name="allProjectId">
			<input type="hidden" value="${dataInfo.dateInfoTwo }" id="customerName" name="customerName">
			<input type="hidden" value="${requestScope.dateInfoThree }" id="phone" name="phone">
		</div>
		<div class="mesFailed">
		<div class="line">
			<h3>备案失败项目</h3>
			<table border="" cellspacing="" cellpadding="">
				<tbody id="selectFail">
				<tr>
					<td>项目名称</td>
					<td>失败原因</td>
				</tr>
				<c:forEach items="${data }" var="grdtoList">
					<c:if test="${grdtoList.applyStatus == 3 }">
						<tr>
							<td>${grdtoList.projectName }</td>
							<td>${grdtoList.fail }</td>
						</tr> 
					</c:if>
				</c:forEach>
				</tbody>
			</table>
		</div>	
		</div>
	</div>
</body>
</html>