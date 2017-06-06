<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link  rel="icon" href="static/images/titleLogo.png"  />
	<title>搜索结果-一房一档</title>
	<!-- <link rel="stylesheet" type="text/css" href="static/css/reset.css"> -->
	<!-- <link rel="stylesheet" type="text/css" href="static/css/commend.css"> -->
	<link rel="stylesheet" type="text/css" href="static/css/search_result.css">
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="static/js/city2.js"></script>
	<script type="text/javascript" src="static/js/citySelect2.js"></script>
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
	  <a><cite>房源详情</cite></a>
	</span>
	
	
		<fieldset class="layui-elem-field">
			<legend>可售房源</legend>
			<div class="layui-field-box layui-form">
				<table class="layui-table admin-table">
					<caption>
						<p>房子ID:<span>${data.houseNum}</span></p>
						<h1>案场名称:<span>${data.projectName }</span></h1>
					</caption>
					<thead>
						<tr>
							<th colspan="4" class="title">${data.houseNo }</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>房源类型</td>
							<td>${data.houseKind }</td>
							<td>预售证号</td>
							<td>${data.presalePermissionURL }</td>
						</tr>
						<tr>
							<td>区位号</td>
							<td>${data.district }</td>
							<td>朝向</td>
							<td>${data.direct }</td>
						</tr>
						<tr>
							<td>建筑面积</td>
							<td>${data.buildArea }平方米</td>
							<td>使用面积</td>
							<td>${data.usefulArea }平方米</td>
						</tr>
						<tr>
							<td>户型</td>
							<td>${data.houseType }</td>
							<td>装修标准</td>
							<td>${data.decorationStandard }</td>
						</tr>
						<tr>
							<td colspan="6" class="title">价格信息</td>
						</tr>
						<tr>
							<td>单价</td>
							<td>${data.danjia }元/平方米</td>
							<td>总价</td>
							<td>${data.listPrice }元</td>
							<td>供货价</td>
							<td>${data.shopPrice }元</td>
						</tr>
						<%int i=0; %>
						<c:forEach items="${data.youhuiList }" var="list">
							<tr>
								<td colspan=1>优惠 <%i=i+1; out.print(i);%></td>
								<td colspan=5>${list.caption }</td>
							</tr>
						</c:forEach>
						<tr>
								<td colspan="6" class="title">最低成交价:${data.zuidiPrice }元,优惠力度:${data.lidu}%最低成交优惠组合:${data.youhui }</td>
						</tr>
						<tr>
							<td colspan="1">交付标准</td>
							<td colspan="5" class="pay-standard">${data.deliverStandard }</td>
						</tr>
						<tr>
							<td colspan="6" class="title">项目信息</td>
						</tr>
						<tr>
							<td>项目名称</td>
							<td colspan="2">${data.projectName}</td>
							<td>所在地</td>
							<td colspan="2" style="width:40%;">${data.propertyAddress }</td>
						</tr>
						<tr>
							<td>用地面积</td>
							<td colspan="2">${data.landArea }</td>
							<td>地上面积</td>
							<td colspan="2">${data.groundArea }</td>
						</tr>
						<tr>
							<td>地下面积</td>
							<td colspan="2">${data.underGroundArea }</td>
							<td>总户数</td>
							<td colspan="2">${data.unitCount }</td>
						</tr>
						<tr>
							<td>容积率</td>
							<td colspan="2">${data.volumeRatio }</td>
							<td>密度</td>
							<td colspan="2">${data.density }</td>
						</tr>
						<tr>
							<td>绿化率</td>
							<td colspan="2">${data.afforestationRatio }</td>
							<td>开发商</td>
							<td colspan="2">${data.developer }</td>
						</tr>
						<tr>
							<td>投资商</td>
							<td colspan="2">${data.investor }</td>
							<td>物业管理</td>
							<td colspan="2">${data.manager }</td>
						</tr>
						<tr>
							<td>物业费</td>
							<td colspan="2">${data.propertyCost }元</td>
							<td>产权年限</td>
							<td colspan="2">${data.rightsYears }年</td>
						</tr>
						<tr>
							<td>开工时间</td>
							<td colspan="2">${data.startTime }</td>
							<td>交付时间</td>
							<td colspan="2">${data.deliverTime }</td>
						</tr>
					</tbody>
				</table>
			</div>
	<div id="myCarousel" class="carousel">
		<!-- 轮播（Carousel）指标 -->
			<!-- <ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>   --> 
	<!-- 轮播（Carousel）项目 -->
			<div class="carousel-inner" style="top:-23px;right:-8px;width:600px;height:288px;overflow:hidden;">
			<c:if test="${fn:length(data.photo)>0}">
							<div class="item active" style="width:600px;height:288px;overflow:hidden;">
								<img src="${data.photo[fn:length(data.photo)-1]}" style="width:100%;height:100%;">
							</div>
				<c:forEach begin="0" end="${fn:length(data.photo)-1}" var="p">
							<div class="item active" style="width:600px;height:288px;overflow:hidden;">
								<img src="${data.photo[p]}" style="width:100%;height:100%;">
							</div>
				</c:forEach>
							<div class="item" style="width:600px;height:288px;overflow:hidden;">
								<img src="${data.photo[0]}" style="width:100%;height:100%;">
							</div>
			</c:if>
			</div>
	<!-- 轮播（Carousel）导航 -->
			<a class="carousel-control left" href="#myCarousel" 
	  	 	data-slide="prev" style="width:50px;height:70px;left:20px;top:80px;font-size:50px;">&lsaquo;</a>
			<a class="carousel-control right" href="#myCarousel" 
	  	 	data-slide="next" style="width:50px;height:70px;right:20px;top:80px;font-size:50px;">&rsaquo;</a>
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
				<a href="###" title="" style="font-size:12px;color:#0c95db;">房源搜索</a>
			</p>
		<div class="btn-group" style="width:99%;margin:0 auto;background-color: #ffffff;margin-top: 10px;padding-top:5px;padding-bottom:10px;margin-left:5px;">
		
		
		<table class="search-result" style="width:98%;border:1px solid #a6a6a6;margin:0 auto;margin-top:5px;">
			<caption>
				<p>房子ID:<span>${data.houseNum}</span></p>
				<h1 class="result-title">案场名称:<span>${data.projectName }</span></h1>
			</caption>
			<thead>
				<tr>
					<th colspan="4" class="title">${data.houseNo }</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>房源类型</td>
					<td>${data.houseKind }</td>
					<td>预售证号</td>
					<td>${data.presalePermissionURL }</td>
				</tr>
				<tr>
					<td>区位号</td>
					<td>${data.district }</td>
					<td>朝向</td>
					<td>${data.direct }</td>
				</tr>
				<tr>
					<td>建筑面积</td>
					<td>${data.buildArea }平方米</td>
					<td>使用面积</td>
					<td>${data.usefulArea }平方米</td>
				</tr>
				<tr>
					<td>户型</td>
					<td>${data.houseType }</td>
					<td>装修标准</td>
					<td>${data.decorationStandard }</td>
				</tr>
				<tr>
					<td colspan="6" class="title">价格信息</td>
				</tr>
				<tr>
					<td>单价</td>
					<td>${data.danjia }元/平方米</td>
					<td>总价</td>
					<td>${data.listPrice }元</td>
					<td>供货价</td>
					<td>${data.shopPrice }元</td>
				</tr>
				<%int i=0; %>
				<c:forEach items="${data.youhuiList }" var="list">
					<tr>
						<td colspan=1>优惠 <%i=i+1; out.print(i);%></td>
						<td colspan=5>${list.caption }</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="6" class="title">最低成交价:${data.zuidiPrice }元,优惠力度:${data.lidu}%
最低成交优惠组合:${data.youhui }</td>
				</tr>
				<tr>
					<td colspan="1">交付标准</td>
					<td colspan="5" class="pay-standard">${data.deliverStandard }</td>
				</tr>
				<tr>
					<td colspan="6" class="title">项目信息</td>
				</tr>
				<tr>
					<td>项目名称</td>
					<td colspan="2">${data.projectName}</td>
					<td>所在地</td>
					<td colspan="2" style="width:40%;">${data.propertyAddress }</td>
				</tr>
				<tr>
					<td>用地面积</td>
					<td colspan="2">${data.landArea }</td>
					<td>地上面积</td>
					<td colspan="2">${data.groundArea }</td>
				</tr>
				<tr>
					<td>地下面积</td>
					<td colspan="2">${data.underGroundArea }</td>
					<td>总户数</td>
					<td colspan="2">${data.unitCount }</td>
				</tr>
				<tr>
					<td>容积率</td>
					<td colspan="2">${data.volumeRatio }</td>
					<td>密度</td>
					<td colspan="2">${data.density }</td>
				</tr>
				<tr>
					<td>绿化率</td>
					<td colspan="2">${data.afforestationRatio }</td>
					<td>开发商</td>
					<td colspan="2">${data.developer }</td>
				</tr>
				<tr>
					<td>投资商</td>
					<td colspan="2">${data.investor }</td>
					<td>物业管理</td>
					<td colspan="2">${data.manager }</td>
				</tr>
				<tr>
					<td>物业费</td>
					<td colspan="2">${data.propertyCost }元</td>
					<td>产权年限</td>
					<td colspan="2">${data.rightsYears }年</td>
				</tr>
				<tr>
					<td>开工时间</td>
					<td colspan="2">${data.startTime }</td>
					<td>交付时间</td>
					<td colspan="2">${data.deliverTime }</td>
				</tr>
			</tbody>
		</table>
		
		</div>
		<div style="height:20px;"></div>
		<div id="myCarousel" class="carousel slide"> --%>
		<!-- 轮播（Carousel）指标 -->
			<!-- <ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>   --> 
	<!-- 轮播（Carousel）项目 -->
			<%-- <div class="carousel-inner" style="top:-23px;right:-8px;width:370px;height:176px;overflow:hidden;">
			<c:if test="${fn:length(data.photo)>0}">
							<div class="item active" style="width:370px;height:176px;overflow:hidden;">
								<img src="${data.photo[fn:length(data.photo)-1]}" style="width:100%;height:100%;">
							</div>
				<c:forEach begin="0" end="${fn:length(data.photo)-1}" var="p">
							<div class="item active" style="width:370px;height:176px;overflow:hidden;">
								<img src="${data.photo[p]}" style="width:100%;height:100%;">
							</div>
				</c:forEach>
							<div class="item" style="width:370px;height:176px;overflow:hidden;">
								<img src="${data.photo[0]}" style="width:100%;height:100%;">
							</div>
			</c:if>
			</div> --%>
	<!-- 轮播（Carousel）导航 -->
		<!-- 	<a class="carousel-control left" href="#myCarousel" 
	  	 	data-slide="prev" style="width:50px;height:70px;left:20px;top:30px;font-size:50px;">&lsaquo;</a>
			<a class="carousel-control right" href="#myCarousel" 
	  	 	data-slide="next" style="width:50px;height:70px;right:20px;top:30px;font-size:50px;">&rsaquo;</a>
		</div> 

	</div>

	<script>
			// 市区二级联动
			var selectVal = new CitySelect({
				data   : data,
				provId : "#prov4",
				cityId : '#city4',
				areaId : '#area4'
			});
			
			    

			
	</script> -->
</body>
</html>