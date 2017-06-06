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
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/houseManage.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/lib/laydate/need/laydate.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/accountManage.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
</head>
<body>
<%@include file="public/systempublicpage.jsp"%>
<!-- 
<a href="<%=request.getContextPath()%>/toAddProject">添加案场</a> -->
<div class="account-list">
	 <div class="add-account">
	 	<a href="<%=request.getContextPath()%>/toAddProject" class="add-tag">新增</a>
	 	 <form class="form-div">
	                <select id="status">
	                    <option value="">所有状态</option>
	                    <option value="1">启用</option>
	                    <option value="2">禁用</option>
	                </select>
	                <input type="button" id="search" value="搜索"/>
                </form>
		<table>
		<tr>
			<td>案场</td>
			<td>案场助理姓名</td>
			<td>案场助理电话</td>
			<td>所属合伙人</td>
		</tr>
		<c:forEach items="${dataList}" var="data">
		<tr>
			<td>${data.projectName }</td>
			<td>${data.assistantProject.userCaption}</td>
			<td>${data.assistantProject.phone }</td>
			<td>${data.partners.userCaption}</td>
		</tr>
		</c:forEach>
		
		</table>
	 
	 </div>
</div>

</body>
</html>