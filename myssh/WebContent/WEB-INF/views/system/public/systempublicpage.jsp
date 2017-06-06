<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<header id="" class="index-header">
		<nav>
			<ul>
				<li><a href="<%=request.getContextPath()%>/system_index" title="" class="index-logo">平台运管管理中心</a></li>
				<li><a href="<%=request.getContextPath()%>/mapInfo" title="">加盟</a></li>
				<li><a class="active" href="<%=request.getContextPath()%>/system_index" title="">首页</a></li>
				<li><a href="<%=request.getContextPath()%>/system_to_adList" title="">广告管理</a></li>
				<li><a href="<%=request.getContextPath()%>/all_user_info_page" title="">账号管理</a></li>
				<li><a href="<%=request.getContextPath()%>/toPartnerList">合伙人管理</a></li>
				<li><a href="<%=request.getContextPath()%>/system_to_projectList" title="">案场管理</a></li>
				<li><a href="<%=request.getContextPath()%>/to_staement_page" title="">对账单</a></li>
				<li><a href="<%=request.getContextPath()%>/system_poroject_report" title="">报表</a></li>
				<li><a href="<%=request.getContextPath()%>/all_reviewd_page" title="">注册门店审核</a></li>
				<li><a href="<%=request.getContextPath()%>/showloadLog" title="">日志下载</a></li>
				<li><a href="<%=request.getContextPath()%>/to_system_project_customer_page" title="">项目客户管理</a></li>
				
				
				<li><a href="#" title="">欢迎您：<span>${sessionScope.userInfo.userCaption}</span></a></li>
			</ul>
		</nav>
	</header>
</body>
</html>