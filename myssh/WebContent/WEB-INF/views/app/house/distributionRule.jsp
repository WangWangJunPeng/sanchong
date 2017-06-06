<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>分销规则</title>
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/distributionRule.css"/>
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
		<script type="text/javascript">
   		 mui.init();
   		 $(document).ready(function(){
   			console.log($('#pjt').val())
   		 })
   		</script>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">分销规则</h1>
		</header>
	
		<div class="mui-content">
		<input type="hidden" value="${projectGuideRule }" id="pjt">
			<div class="support">
				<span class="first">支持：</span>
				<span class="mainType">支持带看</span>
				<span class="mainType">异地备案</span>
				<span class="mainType">快速结佣</span>
				<span class="mainType">网络分享的消息</span>
			</div>
			<ul class="mui-table-view">
			<c:if test="${projectGuideRule.isDaiKan == 1}">
				<li class="mui-table-view-cell">
					<p class="supportType">
						是否支持带看
						<span class="floatRight">支持</span>
					</p>
				</li>
			</c:if>
			<c:if test="${projectGuideRule.isDaiKan != 1}">
				<li class="mui-table-view-cell">
					<p class="supportType">
						是否支持带看
						<span class="floatRight">不支持</span>
					</p>
				</li>
			</c:if>
				<li class="mui-table-view-cell">
					<p class="supportType">
						带看佣金
						<span class="floatRight">${projectGuideRule.daiKanMoney}%</span>
					</p>
				</li>
				<li class="mui-table-view-cell">
					<p class="supportType">
						分销佣金
						<span class="floatRight">${projectGuideRule.fenXiaoMoney}%</span>
					</p>
				</li>
				<li class="mui-table-view-cell">
					<p class="supportType">
						有效天数
						<span class="floatRight">${projectGuideRule.validDays}天</span>
					</p>
				</li>
				<li class="mui-table-view-cell">
					<p class="supportType">
						客户保护期
						<span class="floatRight">${projectGuideRule.custormerProtectDays}个月</span>
					</p>
				</li>
				<c:if test="${projectGuideRule.isYiDi == 1}">
				<li class="mui-table-view-cell">
					<p class="supportType">
						是否支持异地分销
						<span class="floatRight">是</span>
					</p>
				</li>
				</c:if>
				<c:if test="${projectGuideRule.isYiDi != 1}">
				<li class="mui-table-view-cell">
					<p class="supportType">
						是否支持异地分销
						<span class="floatRight">否</span>
					</p>
				</li>
				</c:if>
				<li class="mui-table-view-cell">
					<p class="supportType">
						异地分销佣金
						<span class="floatRight">${projectGuideRule.yiDiSalesCommission}%</span>
					</p>
				</li>
				<li class="mui-table-view-cell">
					<p class="supportType">
						异地报备有效期
						<span class="floatRight">${projectGuideRule.yiDiValidity}天</span>
					</p>
				</li>
				<c:if test="${projectGuideRule.isFast == 1}">
				<li class="mui-table-view-cell">
					<p class="supportType">
						是否支持快速结佣
						<span class="floatRight">是</span>
					</p>
				</li>
				</c:if>
				<c:if test="${projectGuideRule.isFast != 1}">
				<li class="mui-table-view-cell">
					<p class="supportType">
						是否支持快速结佣
						<span class="floatRight">否</span>
					</p>
				</li>
				</c:if>
				<li class="mui-table-view-cell">
					<p class="supportType">
						说明:
						<span>${projectGuideRule.description}</span>
					</p>
				</li>
			</ul>

		</div>
</body>
</html>