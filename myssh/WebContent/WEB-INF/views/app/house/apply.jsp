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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/apply.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
      	function getChecked(){
      		
      		if($("#customerName").val() == "" || $("#customerPhone").val() == "" || $("#buyPrice").val() == ""){
      			
      			return false;
      		}
      	}
      	
    </script>
     <title>购买申请</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">购买申请</h1>
	</header> 
	<div class="mui-content">
		<div class="mid">
		<form id="" class="listMess" action="to_submitMidApply" method="post" onsubmit="return getChecked()">
		<!-- <input type="hidden" name="houseNum" value="" id="houseNum"> -->
			<p>
				<img src="<%=request.getContextPath()%>/static/images/1.png" alt="" />
				<span class="leftName">ID</span>
				<input type="text" class="rightVal" readonly="readonly" id="houseNum" name="houseNum" value="${data.houseNum }"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/2.png" alt="" />
				<span class="leftName">房号</span>
				<input class="rightVal" readonly="readonly" id="houseNo" name="houseNo" value="${data.houseNo }"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/3.png" alt="" />
				<span class="leftName">预售证号</span>
				<input class="rightVal" readonly="readonly" id="presalePermissionInfo" name="presalePermissionInfo" value="${data.presalePermissionInfo }"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/4.png" alt="" />
				<span class="leftName">楼栋号</span>
				<input class="rightVal" readonly="readonly" id="buildingNo" name="buildingNo" value="${data.buildingNo }"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/5.png" alt="" />
				<span class="leftName">房型</span>
				<input class="rightVal" readonly="readonly" id="caption" name="caption" value="${data.housType }"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/6.png" alt="" />
				<span class="leftName">价格</span>
				<input class="rightVal" readonly="readonly" id="listPrice" name="listPrice" value="${data.listPrice }"/>
			</p>
			<p>
				<!-- <input type="hidden" name="resultMap" value="" id="resultMap"> -->
				<img src="<%=request.getContextPath()%>/static/images/7.png" alt="" />
				<lable class="leftName">客户姓名</lable>
				<a href="to_midMyCustomer?houseNum=${data.houseNum }"><img class="rightVal  btn-phone" src="<%=request.getContextPath()%>/static/images/phoneList.png" alt="" /></a>
				<input readonly="readonly" id="customerName" name="customerName" value="${data.customerName }"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/8.png" alt="" />
				<lable class="leftName">联系电话</lable>
				<input class="rightVal" readonly="readonly" id="customerPhone" name="customerPhone" value="${data.customerPhone }"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/9.png" alt="" />
				<lable class="leftName">购买价格</lable>
				<input type="text" placeholder="请填写" name="buyPrice" id="buyPrice"/>
			</p>
			<button class="apply-btn" type="submit">提交购买申请</button>
		</form>
		</div>
	</div>
</body>
</html>
