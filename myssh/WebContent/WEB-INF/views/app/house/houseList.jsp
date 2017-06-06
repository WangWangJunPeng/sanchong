<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <link href="<%=request.getContextPath()%>/static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/houseList.css"/>
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script type="text/javascript">
    mui.init();
    	function getChooseHouse(){
    		window.location.href="to_goToChooseOneProjectHouse?houseNum="+$("input[name='checkbox1']:checked").val()+"&allBenefitsId="+$('#allBenefitsId').val();
    	}
    </script>
	<title>房源列表</title>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">房源列表</h1>
	</header>
	<div class="mui-content">
	<input type="hidden" value="${allBenefitsId }" id="allBenefitsId" name="allBenefitsId">
	<c:forEach items="${hdtoList }" var="list">
		<div class="message">
			<div class="container">
				<div class="leftL">
					<div class="mui-input-row mui-radio mui-left">
  						<label></label>
  						<input name="checkbox1" value="${list.houseNum }" type="radio">
					</div>
				</div>
				
				<div class="leftbox">
					<c:if test="${list.resultPhotoUrl != null && list.resultPhotoUrl !='' }">
						<img src="${list.resultPhotoUrl }" alt="" />
					</c:if>
					<c:if test="${list.resultPhotoUrl == null || list.resultPhotoUrl =='' }">
						<img src="<%=request.getContextPath()%>/static/images/morentupian.png" alt="" />
					</c:if>
				</div>
				<a href="to_jingjirenOneHouse?houseNum=${list.houseNum }">
				<div class="rightbox">
				<c:if test="${list.district != null && list.district !=''}">
					<h2>${list.projectName }<span>/${list.district }</span></h2>
				</c:if>
				<c:if test="${list.district == null || list.district ==''}">
					<h2>${list.projectName }</h2>
				</c:if>
					<p><span>${list.buildingNo }</span>栋<span>${list.unit }</span>单元<span>${list.floor }</span>楼<span>${list.houseNo }</span>号<span class="floatRight">${list.buildArea }㎡</span></p>
					<p>${list.houseStyle }<span class="redcolor floatRight">${list.listPrice }元</span></p>
					<p><span class="floatRight">${list.danjiaStr }元/㎡</span></p>
				</div>
				</a>
			</div>
		</div>
		
	</c:forEach>
		
	</div>
	<div class="btn-success mui-bar mui-bar-tab">
			<div class="midde">
				<button type="button" id="btn-succ" class="failedCss" disabled="disabled" onclick="getChooseHouse()">确认</button>
			</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$("input").click(function(){
				if($("input[name='checkbox1']:checked").length==0){
					$("#btn-succ").removeClass("success").addClass("failedCss").attr("disabled","disabled");
				}else {
					$("#btn-succ").removeClass("failedCss").addClass("success").removeAttr("disabled");
				}
			})
		})
	</script>
</body>
</html>

