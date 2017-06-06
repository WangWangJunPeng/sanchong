<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>平台运管管理中心-广告管理</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/commend.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/index.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/reset.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
</head>
<body>
<%@include file="public/systempublicpage.jsp"%>
<hr>
<a href="<%=request.getContextPath()%>/toAddAd" style="border:0px solid; width:20px; height:16px; background:#33CCCC; font-size:20px; color:#ECF0F1; margin-left:452px;">添加广告</a>
<div align="center" style="width:1200px;margin:0 auto;">
<table align="center" style="width:1000px;margin:0 auto; background:#EBE9F6">
	<tr>
		<td  align=center>导图</td>
		<td  align=center>广告标题</td>
		<td  align=center>所属项目</td>
		<td  align=center>广告位</td>
		<td  align=center>开始时间</td>
		<td  align=center>结束时间</td>
		<td  align=center>排序</td>
		<td  align=center>城市</td>
		<td  align=center>操作</td>
	</tr>
	
	<c:forEach items="${adList }" var="ad">
	<tr>
		<td><img alt="导图" height="106px" width="185px" src="${ad.adUrl}"></td>
		<td  align=center>${ad.adTitle}</td>
		<td  align=center>${ad.projectName}</td>
		<td  align=center>${ad.adPosition}</td>
		<td  align=center>${ad.startTime}</td>
		<td  align=center>${ad.endTime}</td> 
		<td  align=center>${ad.sorting}</td>
		<td  align=center>${ad.province}-${ad.city}</td>
		<td  align=center><a href="<%=request.getContextPath()%>/system_to_updataAd?adId=${ad.adId}">修改</a></td>
	</tr>
	</c:forEach>
</table>
</div>
</body>
</html>