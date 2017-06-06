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
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <link href="<%=request.getContextPath()%>/static/css/mui.min.css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/MyInstall.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/my.css" />	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/myMaterial.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			getDateInfo();
		});
		function getDateInfo(){
			$.ajax({
				type:"post",
				url:"to_getCustomerInfo",
				data:{projectCustomerId : $('#projectCustomerId').val(),
					projectCustomerPhoneTwo : $('#projectCustomerPhoneTwo').val()},
				success : function(data) {
					data = eval("("+data+")");
					renderDateInfo(data.data);
				},
				/* error:function(){
					alert("信息获取失败");
				} */
			});
		}
		function renderDateInfo(data){
			$("#projectCustomerName").html(data.projectCustomerName);
			$("#projectCustomerPhone").html(data.projectCustomerPhone);
			$("#projectCustomerId").html(data.projectCustomerId);
			$("#sex").html(data.sex);
			$("#idCard").html(data.idCard);
			$('#description').html(data.description);
		}
		function getCustomerInfoSave(){
			$.ajax({
				type:"post",
				url:"to_updateCustomerInfo",
				data:{projectCustomerId : $('#projectCustomerId').val(),
					description : $('#description').val()},
				success : function(data) {
					/* data = eval("("+data+")");
					renderDateInfo(data.data); */
					window.location.href="to_saleMyAllCustomerAndSubscribe";
				},
				/* error:function(){
					alert("信息获取失败");
				} */
			});
		}
	</script>
	
    <title>个人资料</title>
</head>
<body  class="mui-fullscreen">
	<div id="app" class="mui-views">
			<div class="mui-view">
				<div class="mui-pages">
					
				</div>
			</div>
	</div>
	<div id="setting" class="mui-page">
		<header class="mui-bar mui-bar-nav mui-bar-transparent">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		</header>
			<!--页面主内容区开始-->
			<div class="mui-page-content">
				<div class="mui-scroll-wrapper">
					<div class="mui-scroll">
								<div>
			<div class="top">
				<div class="people">
					<img src="<%=request.getContextPath()%>/static/images/APPheadphoto.jpg" alt="" id="photo"/>
					<p id="projectCustomerName"></p>
				</div>
			</div>
			</div>
						<!--<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell">
								账户名称<span class="mui-right"></span>
							</li>
						</ul>-->
						<input type="hidden" value="${dataInfo }" name="projectCustomerId" id="projectCustomerId">
						<input type="hidden" value="${dataInfoTwo }" name="projectCustomerPhoneTwo" id="projectCustomerPhoneTwo">
						<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell">
								<img src="<%=request.getContextPath()%>/static/images/7.png" alt="" />
								手机<span id="projectCustomerPhone"></span>
							</li>
						</ul>
						<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell">
								<img src="<%=request.getContextPath()%>/static/images/8.png" alt="" />
								性别 <span id="sex"></span>
							</li>
						</ul>
						<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell">
								<img src="<%=request.getContextPath()%>/static/images/12.png" alt="" />
								身份证号码 <span id="idCard"></span>
							</li>
						</ul>
						<ul class="mui-table-view mui-table-view-chevron" style="height: 4.5rem;">
							<li class="mui-table-view-cell" style="height: 4.5rem;">
								<img src="<%=request.getContextPath()%>/static/images/12.png" alt="" />
								备注 
								<form action="" method="post">
									<!-- <input type="hidden" name="projectCustomerId" id="projectCustomerId"> -->
									<textarea name="description" rows="6" cols="8" id="description"></textarea>
									<button type="button" class="btn-save" onclick="getCustomerInfoSave()">保存</button>
								</form>								
							</li>
						</ul>
					</div>
				</div>
			</div>
			<!--页面主内容区结束-->
	</div>
	
</body>
	<script src="<%=request.getContextPath()%>/static/js/mui.min.js "></script>
	<script src="<%=request.getContextPath()%>/static/js/mui.view.js "></script>

	<script>
		mui.init();
		//初始化单页view
		var viewApi = mui('#app').view({
			defaultPage: '#setting'
		});
		//初始化单页的区域滚动
		mui('.mui-scroll-wrapper').scroll();


		var view = viewApi.view;
		(function($) {
			//处理view的后退与webview后退
			var oldBack = $.back;
			$.back = function() {
				if (viewApi.canBack()) { //如果view可以后退，则执行view的后退
					viewApi.back();
				} else { //执行webview后退
					oldBack();
				}
			};
			//监听页面切换事件方案1,通过view元素监听所有页面切换事件，目前提供pageBeforeShow|pageShow|pageBeforeBack|pageBack四种事件(before事件为动画开始前触发)
			//第一个参数为事件名称，第二个参数为事件回调，其中e.detail.page为当前页面的html对象
			view.addEventListener('pageBeforeShow', function(e) {
				//				console.log(e.detail.page.id + ' beforeShow');
			});
			view.addEventListener('pageShow', function(e) {
				//				console.log(e.detail.page.id + ' show');
			});
			view.addEventListener('pageBeforeBack', function(e) {
				//				console.log(e.detail.page.id + ' beforeBack');
			});
			view.addEventListener('pageBack', function(e) {
				//				console.log(e.detail.page.id + ' back');
			});
		})(mui);		
	</script>

</html>
