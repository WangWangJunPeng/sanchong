<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

	<head>
		<meta charset="utf-8">
		<title>撤单原因</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/cancleOrder.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/feedback.css" />
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script type="text/javascript">
			function getRealRevoke(){
				$.ajax({
		    		  type:"post",
		    		  async : false,
		    		  url:"to_goToRealRevokeOneContractRecord",
		    		  data:{recordNo:$('#oneRecordNo').val(),
		    			  killTheOrderReason : $('input[name="radio1"]:checked').val(),
		    			  revokeOrderNotes : $('#moreReason').val()},
		    	   	 success:function(){
		    	   		window.location.href="to_goToAllMyContractRecordsListPage";
		    	   	 }
		    	   });
			}
		</script>
	</head>

	<body class="mui-ios mui-ios-9 mui-ios-9-1" style="padding-top:0;">
		<header class="mui-bar mui-bar-nav mui-bar-transparent">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">我的客户</h1>
		</header>
	
		<div class="mui-content">
			<div id="tabbar">
				<section class="cancleReason">
					<p>订单已取消，请告诉我们原因</p>
					<h5>我们会努力做的更好</h5>
				</section>
				<section id="firstOption" class="optionSection">
					<p class="reasonOption">
						不想购买了
									
						<span class="mui-input-row mui-radio">
							<label></label>
							<input name="radio1" type="radio" value="不想购买了" >
						</span>
					</p>
				</section>
				<section class="optionSection">
					<p class="reasonOption">
						服务问题导致无法购买
									
						<span class="mui-input-row mui-radio">
							<label></label>
							<input name="radio1" type="radio" value="服务问题导致无法购买" >
						</span>
					</p>
				</section>
				<section class="optionSection">
					<p class="reasonOption">
						优惠规则不清晰
									
						<span class="mui-input-row mui-radio">
							<label></label>
							<input name="radio1" type="radio" value="优惠规则不清晰" >
						</span>
					</p>
				</section>
				<section class="optionSection">
					<p class="reasonOption">
						认购规则不清晰
									
						<span class="mui-input-row mui-radio">
							<label></label>
							<input name="radio1" type="radio" value="认购规则不清晰" >
						</span>
					</p>
				</section>
				<section class="optionSection">
					<p class="reasonOption">
						订单审核等待过长未审核
									
						<span class="mui-input-row mui-radio">
							<label></label>
							<input name="radio1" type="radio" value="订单审核等待过长未审核" >
						</span>
					</p>
				</section>
				<section class="optionSection">
					<p class="reasonOption">
						想更换房源
									
						<span class="mui-input-row mui-radio">
							<label></label>
							<input name="radio1" type="radio" value="想更换房源" >
						</span>
					</p>
				</section>
				<section class="optionSection">
					<p class="reasonOption">
						其他
									
						<span class="mui-input-row mui-radio">
							<label></label>
							<input name="radio1" type="radio" value="其他" >
						</span>
					</p>
				</section>
				
				<section class="inputSection">
					<textarea class="reasonInput" placeholder="如果你因其他原因取消，也请告诉我们原因" id="moreReason"></textarea>
				</section>
				
				<section class="btnSection">
					<input type="hidden" value="${oneRecordNo }" id="oneRecordNo" name="recordNo">
						<button class="confirmBtn" onclick="getRealRevoke()"> 确认提交</button>
				</section>

			</div>
		</div>
		
		<script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
	</body>


</html>