<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link  rel="icon" href="static/images/titleLogo.png"  />
	<title>经纪人管理</title>
	<link rel="stylesheet" type="text/css" href="static/css/reset.css">
	<link rel="stylesheet" type="text/css" href="static/css/commend.css">
	<link rel="stylesheet" type="text/css" href="static/css/shopAddAgent.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/validation.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/shopAddAgent.js"></script>
	<style type="text/css">
	.error{color:#ff0000;font-size: 10px;}
	</style>
</head>
<body>
	<%@include file="../publicpage/shoppublicpage.jsp" %>

	<div class="contain">
		<div class="formMiddle">
				<form action="medi_manager_to_add_or_update" method="post" id="createForm">
			<section>
				<p>经纪人类型</p>
				<input type="radio" value="medi" id="kind1" name="rightSign" checked="checked">	
				<label for="kind1">经纪人</label>								
				<input type="radio" value="shopowner" id="kind2" name="rightSign">
				<label for="kind2">店长</label>
			</section>
			<section>
				<label for="name" style="margin-left: 45px;">姓名<span>*</span></label>
				<input type="text" name="userCaption" id="name">
			</section>
			<section>
				<label for="phone" style="margin-left: 45px;">电话<span>*</span></label>
				<input type="text" name="phone" id="phone"><a href="#" style="color:#2772DB;font-size: 10px;" id="phone_ok"></a><a href="#" style="color:#ff0000;font-size: 10px;" id="phone_exist"></a>
			</section>
			<section>
				<label for="id_card" style="margin-left: -10px;">身份证号码<span>*</span></label>
				<input type="text" name="idCard" id="id_card">
			</section>
			<input type="submit" id="forNext" name="returnSign" value="提交后继续新增下一个">
			<input type="submit" id="forReturn" name="returnSign" value="提交后返回列表">
			<!-- <button name="returnSign" type="submit" id="forNext">提交后继续新增下一个经纪人</button>
			<button name="returnSign" type="submit" id="forReturn">提交后返回经纪人列表</button> -->
		</form>
		</div>
	</div>
</body>
</html>