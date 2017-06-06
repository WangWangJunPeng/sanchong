<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link  rel="icon" href="static/images/titleLogo.png"  />
	<title>经纪人管理</title>
	<link rel="stylesheet" type="text/css" href="static/css/reset.css">
	<link rel="stylesheet" type="text/css" href="static/css/commend.css">
	<link rel="stylesheet" type="text/css" href="static/css/shopAddAgent.css">
	<link rel="stylesheet" type="text/css" href="static/css/accountManage.css" />
	<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		
		$(document).ready(function(){
			
		}); 
	</script>
</head>
<body>
	<%@include file="/WEB-INF/views/system/public/systempublicpage.jsp"%>
	<div class="bg">
	</div>
		<div class="contain">
                <p><a href="system_index">首页</a>&nbsp;-&nbsp;<a href="all_user_info_page">账号管理</a>&nbsp;-&nbsp;账号编辑</p>
            </div>
	<div class="contain">
		<div class="formMiddle">
				<form action="update_user_info" method="post" id="createForm">
			<section>
				<p>经纪人类型&nbsp;&nbsp;&nbsp;</p>
				<c:if test="${roleId == 1 || roleId == 2 }">
					<c:choose>
						<c:when test="${roleId==1}"><input type="radio" value="1" id="agent" name="role" checked="checked"><label for="kind1">中介经纪人</label></c:when>
						<c:otherwise><input type="radio" value="1" id="agent" name="role"><label for="kind1">中介经纪人</label></c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${roleId==2}"><input type="radio" value="2" id="shopManager" name="role" checked="checked"><label for="kind1">店长</label>	</c:when>
						<c:otherwise><input type="radio" value="2" id="shopManager" name="role"><label for="kind1">店长</label>	</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${roleId == 3 || roleId == 4 }">
					<c:choose>
						<c:when test="${roleId==3}"><input type="radio" value="3" id="counselor" name="role" checked="checked"><label for="kind1">置业顾问</label></c:when>
						<c:otherwise><input type="radio" value="3" id="counselor" name="role"><label for="kind1">置业顾问</label>	</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${roleId==4}"><input type="radio" value="4" id="assistant" name="role" checked="checked"><label for="kind1">案场助理</label></c:when>
						<c:otherwise><input type="radio" value="4" id="assistant" name="role"><label for="kind1">案场助理</label></c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${roleId==6}">
					<input type="radio" value="6" id="assistant" name="role" checked="checked"><label for="kind1">合伙人</label>
				</c:if>
			</section>
			<section>
				<label for="name">姓名<span>*</span></label>
				<input type="text" name="userCaption" id="name" value="${userCaption}">
			</section>
			<section>
				<input type="text" name="userId" id="name" value="${userId}" style="display:none;">
			</section>
			<section>
				<label for="phone">电话<span>*</span></label>
				<input type="text" name="phone" id="phone" value="${phone}">
			</section>
			<section>
				<label for="id_card">身份证号码<span>*</span></label>
				<input type="text" name="idCard" id="id_card" value ="${idCard}">
			</section>
			<input type="text" name="doSign" value="reset" style="display:none;">
			<input type="submit" id="forNext" name="returnSign" value="提交后返回账号管理页面">
		</form>
		</div>
	</div>
</body>
</html>