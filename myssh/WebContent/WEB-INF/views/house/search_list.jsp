<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link  rel="icon" href="static/images/titleLogo.png"  />
	<title>门店管理后台</title>
	<!-- <link rel="stylesheet" type="text/css" href="static/css/reset.css">
	<link rel="stylesheet" type="text/css" href="static/css/commend.css">
	<link rel="stylesheet" type="text/css" href="static/css/search_list.css"> -->
	<link rel="stylesheet" href="static/layui/plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="static/layui/css/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	<link rel="stylesheet" href="static/layui/css/table.css" />
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript" src="static/layui/plugins/layui/layui.js"></script>
</head>
<body>
	<div class="admin-main">
	<span class="layui-breadcrumb">
	  <a href="to_allProjects">房源搜索</a>
	  <a><cite>可售房源列表</cite></a>
	</span>
	
	
		<fieldset class="layui-elem-field">
			<legend>可售房源</legend>
			<div class="layui-field-box layui-form">
				<table class="layui-table admin-table">
					<thead>
						<tr>
							<th class="houseNo">房号</th>
							<!-- <th class="houseKind">类型</th> -->
							<th class="buildingNo">楼栋号</th>
							<th class="houseStyle">户型</th>
							<th class="buildArea">建筑面积</th>
							<th class="usefulArea">使用面积</th>
							<th class="listPrice">列表价(元)</th>
							<th class="shopPrice">供货价(元)</th>

							<th class="shelvTime">发布时间</th>
							<th class="houseStatus">房源状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="content">
						<c:forEach items="${data}" var="list">
							<tr>
								<td class="houseNo">${list.houseNo }</td>

								<td class="buildingNo">${list.buildingNo }栋</td>

								<td class="houseStyle">${list.houseStyle }</td>
								<td class="buildArea">${list.buildArea }㎡</td>
								<td class="usefulArea">${list.usefulArea }㎡</td>
								<td class="listPrice">${list.listPrice }</td>
								<td class="shopPrice">${list.shopPrice }</td>

								<td class="shelvTime">${list.shelvTime }</td>
								<c:if test="${list.houseStatus == '0'}">
									<td class="houseStatus">初始</td>
								</c:if>
								<c:if test="${list.houseStatus == '1'}">
									<td class="houseStatus">上架</td>
								</c:if>
								<c:if test="${list.houseStatus == '2'}">
									<td class="houseStatus">删除</td>
								</c:if>
								<c:if test="${list.houseStatus == '3'}">
									<td class="houseStatus">销控</td>
								</c:if>
								<c:if test="${list.houseStatus == '4'}">
									<td class="houseStatus">订购</td>
								</c:if>
								<c:if test="${list.houseStatus == '5'}">
									<td class="houseStatus">签约</td>
								</c:if>
								<td><a href="oneHouse?houseNum=${list.houseNum }"
									style="color: #0c95db;">查看</a></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</fieldset>
		<div class="admin-table-page">
			<div id="paged" class="page"></div>
		</div>
	</div>
	<script>
	
		layui.use('element', function() {
			var element = layui.element(); //导航的hover效果、二级菜单等功能，需要依赖element模块

			//监听导航点击
			element.on('nav(demo)', function(elem) {
				//console.log(elem)
				layer.msg(elem.text());
			});
		});
		
		
	</script>
	<!-- <header id="" class="index-header">
		<nav>
			<ul>
				<li><a href="index.html" title="" class="index-logo">中介门店管理后台</a></li>
				<li><a  href="index.html" title="">首页</a></li>
				<li><a  class="active" href="" title="">房源搜索</a></li>
				<li><a href="" title="">经纪人管理</a></li>
				<li><a href="" title="">客户管理</a></li>
				<li><a href="" title="">对账单</a></li>
				<li><a href="" title="">业务</a></li>
				<li><a href="" title="">欢迎您：<span>User</span></a></li>
			</ul>
		</nav>
	</header>/header -->
	<%-- <%@include file="../publicpage/shoppublicpage.jsp" %> --%>
	
	<%-- <div class="contain" style="width:960px;margin:0 auto;background-color: #eaeaea;">
			<p style="font-size:12px;color:#0c95db;padding-top:10px;margin-left:10px;">
				<a href="to_store_index" title="" style="font-size:12px;color:#0c95db;">首页</a>
				<span>-</span>
				<a href="to_allProjects" title="" style="font-size:12px;color:#0c95db;">房源搜索</a>
				<span>-</span>
				<a href="###" title="" style="font-size:12px;color:#0c95db;">可售房源列表</a>
			</p>
	
		<div style="width:99%;margin:0 auto;background-color: #ffffff;margin-top: 10px;padding-top:5px;padding-bottom:10px;">
		<table class="search-list" cellpadding="8">
			<thead>
				<tr>
					<th class="houseNo">房号</th>
					<!-- <th class="houseKind">类型</th> -->
					<th class="buildingNo">楼栋号</th>
					<th class="houseStyle">户型</th>
					<th class="buildArea">建筑面积</th>
                    <th class="usefulArea">使用面积</th>
					<th class="listPrice">列表价(元)</th>
					<th class="shopPrice">供货价(元)</th>
					
					<th class="shelvTime">发布时间</th>
					<th class="houseStatus">房源状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${data}" var="list">
				<tr>
					<td class="houseNo">${list.houseNo }</td>
					
					<td class="buildingNo">${list.buildingNo }栋</td>
					
					<td class="houseStyle">${list.houseStyle }</td>
					<td class="buildArea">${list.buildArea }㎡</td>
					<td class="usefulArea">${list.usefulArea }㎡</td>
					<td class="listPrice">${list.listPrice }</td>
					<td class="shopPrice">${list.shopPrice }</td>
					
					<td class="shelvTime">${list.shelvTime }</td>
					<c:if test="${list.houseStatus == '0'}">
						<td class="houseStatus">初始</td>
					</c:if>
					<c:if test="${list.houseStatus == '1'}">
						<td class="houseStatus">上架</td>
					</c:if>
					<c:if test="${list.houseStatus == '2'}">
						<td class="houseStatus">删除</td>
					</c:if>
					<c:if test="${list.houseStatus == '3'}">
						<td class="houseStatus">销控</td>
					</c:if>
					<c:if test="${list.houseStatus == '4'}">
						<td class="houseStatus">订购</td>
					</c:if>
					<c:if test="${list.houseStatus == '5'}">
						<td class="houseStatus">签约</td>
					</c:if>
					<td><a href="oneHouse?houseNum=${list.houseNum }"  style="color:#0c95db;">查看</a></td>
				</tr>
			
			</c:forEach>
			</tbody>
		</table>
		</div>
		<div style="height:20px;"></div>
	</div> --%>
</body>
</html>