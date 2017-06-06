<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link  rel="icon" href="static/images/titleLogo.png"  />
<title>案场助理个人中心-价格优惠条款</title>
<link rel="stylesheet" type="text/css" href="static/css/reset.css" />
<link rel="stylesheet" type="text/css" href="static/css/sureBill.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/commend.css">
<link rel="stylesheet" type="text/css"
	href="static/lib/laydate/need/laydate.css" />
<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="static/lib/laydate/laydate.js"></script>
<script type="text/javascript" src="static/js/sureBill.js"></script>
<script type="text/javascript">
<!-- 当点击通过申请的反馈-->
	$(document).ready(function() {
		$("#tongguo").click(function() {

			var name = $('#companyName').html() + " " + $('#shopName').html();
			if (confirm("确认将" + name + "通过审核吗?")) {
				toSuccess();
			}
		});

		$("#jujue").click(function() {
			var name = $('#companyName').html() + " " + $('#shopName').html();
			var info = $("#yijian").val();
			if (confirm("确认拒绝" + name + "的申请吗?")) {
				if ($("#yijian").val() != '') {
					noSuccess();
				} else {
					alert("请输入审核意见");
				}
			}
		});
		
		$("#delete").click(function(){
			var name = $('#companyName').html() + " "+ $('#shopName').html();
			if(confirm("确认删除"+ name +"这家门店吗？")){
				deleteShop();
			}
		});
	});
	
	
	function deleteShop(){
		$.ajax({
			type:"post",
			url:"delete_shop",
			async : false,
			data : {shopId : $('#shopId').html()},
			success : function(data){
				window.location.href = "all_reviewd_page";
			}
		});
	}

	function toSuccess() {

		$.ajax({
			type : "post",
			url : "add_shop_reviewd",
			async : false,
			data : {
				auditOpinion : $('#yijian').val(),
				shopId : $('#shopId').html(),
				tag : 1
			},
			success : function(data) {
				if (data != null) {
					window.location.href = "all_reviewd_page";
				}
			},
		});
	}

	function noSuccess() {
		$.ajax({
			type : "POST",
			url : "add_shop_reviewd",
			async : false,
			data : {
				auditOpinion : $('#yijian').val(),
				shopId : $('#shopId').html(),
				tag : 0
			},
			success : function(data) {
				console.log(123456);
				if (data != null) {
					window.location.href = "all_reviewd_page";
				}
			},
		});
	}
</script>

</head>
<body>
	<%@include file="/WEB-INF/views/system/public/systempublicpage.jsp" %>
	<div class="box">
		<div class="sure-bill">
			<p>
				<a href="system_index">首页</a>&nbsp;-&nbsp;<a href="all_reviewd_page">注册门店审核</a>&nbsp;-&nbsp;查看审核门店申请
			</p>
		</div>
		<div class="house-list">
			<table cellpadding="14" cellspacing="4" id="housesInfo" border="1">
				<thead>
					<tr>
						<td id="shopId" style="display: none">${data.shopId }</td>
						<td>公司名称</td>
						<td id="companyName">${data.companyName }</td>
					</tr>
					<tr>
						<td>门店名称</td>
						<td id="shopName">${data.shopName }</td>
					</tr>
					<tr>
						<td>门店地址</td>
						<td>${data.city }</td>
					</tr>
					<tr>
						<td>门头照片</td>
						<td><img alt="门头照片" src="${data.photo}" style="width:450px;height:250px"></td>
					</tr>
					<tr>
						<td>营业执照复印件</td>
						<td><img alt="营业执照复印件" src="${data.licensePhoto}" style="width:450px;height:250px"></td>
					</tr>
					<tr>
						<td>门店营业执照号</td>
						<td>${data.licenseNo }</td>
					</tr>
					<tr>
						<td>负责人姓名</td>
						<td>${data.contactPerson }</td>
					</tr>
					<tr>
						<td>联系电话</td>
						<td>${data.phone }</td>
					<tr>
						<td>Email</td>
						<td>${data.email }</td>
					</tr>
					<tr>
						<td>银行卡号</td>
						<td>${data.bank_cards }</td>
					</tr>
					<tr>
						<td>银行卡开户行</td>
						<td>${data.bankName }</td>
					</tr>
					<tr>
						<td>银行卡开户名</td>
						<td>${data.representative }</td>
					</tr>
					<tr>
						<td>推荐合伙人</td>
						<td>暂无</td>
					</tr>
					<tr>
						<td>审核意见</td>
						<c:if test="${data.auditOpinion != null}">
							<td><label>${data.auditOpinion}</label></td>
						</c:if>
						<c:if test="${data.auditOpinion == null && data.shopStatus != 3}">
							<td><input size="60" style="width: 300px; height: 100px;"
								type="text" class="yijian" name="yijian" id="yijian"></td>
						</c:if>
					</tr>
					<c:if test="${data.shopStatus == 0}">
						<tr>
							<td></td>
							<td><input type="button" class="tongguo" id="tongguo"
								value="通过审核">&nbsp;&nbsp;&nbsp;<input type="button"
								class="jujue" id="jujue" value="拒绝申请"></td>
						</tr>
					</c:if>
								
				</thead>
			</table>
		</div>
	</div>
</body>
</html>
