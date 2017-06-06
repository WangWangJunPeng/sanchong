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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/my.css" />	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			getDateInfo();
			getTwoDateInfo();
			getNotReadNum();
		});
		function getDateInfo(){
			$.ajax({
				type : "post",
				async : false,
				url : "to_midTopRecord",
				success : function(data) {
					data = eval("("+data+")");
					renderDateInfo(data.data);
				},
				error:function(){
					alert("信息获取失败");
				}
			});
		}
		
		function renderDateInfo(data){
			$("#userCaption").html(data.userCaption);
			if (data.photo != null && data.photo != "") {
				$("#photo").attr('src',data.photo);
				
			}
			$("#yijiesuan").html(data.yijiesuan);
			$("#weijiesuan").html(data.weijiesuan);
			$("#chengjiaoNum").html(data.chengjiaoNum);
		}
		
		function getTwoDateInfo(){
			$.ajax({
				type : "post",
				async : false,
				url : "to_myMidBusiness",
				success : function(data) {
					data = eval("("+data+")");
					renderTwoDateInfo(data.data);
				},
				error : function(){
					alert("信息获取失败");
				}
			});
		}
		function renderTwoDateInfo(data){
			$("#beianNum").html(data.beianNum);
			$("#daikanNum").html(data.daikanNum);
			$("#dealNum").html(data.dealNum);
		}
		function getNotReadNum(){
			$.ajax({
				type:"post",
				async : false,
				url:"to_getMydynamicNum",
				success : function(data) {
					data = eval("("+data+")");
					renderNotReadNum(data.data);
				}
			});
		}
		function renderNotReadNum(data){
			if(data.notReadNum >0){
				$('#notReadNum').html(data.notReadNum);
			}else {
				$('#notReadNum').removeClass("mui-badge");
			}
		}
	</script>
    <title>我的</title>
</head>
<body  class="mui-fullscreen">
	<div id="app" class="mui-views">
			<div class="mui-view">
				<div class="mui-pages">
					<!--页面主内容区开始-->
			<div class="mui-page-content">
				<div class="mui-scroll-wrapper">
					<div class="mui-scroll">
								<div>
			<div class="top">
				<a href="to_settingMid"><img class="setting" src="static/images/APPsetting.png" alt="" /></a>
				<div class="people">
					<img src="<%=request.getContextPath()%>/static/images/APPheadphoto.jpg" alt="" id="photo"/>
					<p id="userCaption"></p>
				</div>
			</div>
			<div class="mid">
			<ul>
				<li>
					<p id="yijiesuan"></p>
					<p>已结算佣金</p>
				</li>
				<li>
					<p id="weijiesuan"></p>
					<p>待结算佣金</p>
				</li>
				<li>
					<p id="chengjiaoNum"></p>
					<p>成交房源</p>
				</li>
			</ul>
			</div>
		</div>
						<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell">
								<img src="<%=request.getContextPath()%>/static/images/13.png">
								<a href="to_Mydynamic" class="mui-navigate-right">我的动态</a><span class="mui-badge mui-badge-danger" id="notReadNum"></span>
							</li>
						</ul>
						<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell">
								<img src="<%=request.getContextPath()%>/static/images/myorder.png">
								<a href="to_goToAllMyContractRecordsListPage" class="mui-navigate-right">我的订单</a>
							</li>
						</ul>
						<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell">
								<img src="<%=request.getContextPath()%>/static/images/7.png">	
								<a href="to_midUserInfo" class="mui-navigate-right">个人资料</a>
							</li>
						</ul>
						<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell">
								<img src="<%=request.getContextPath()%>/static/images/14.png">	
								<a href="###" class="">业务统计 </a>
							</li>
							<ul class="mui-table-view-cell list">
								<li><p>本月备案<span id="beianNum"></span></p></li>
								<li><p>本月带看<span id="daikanNum"></span></p></li>
								<li><p>本月成交<span id="dealNum"></span></p></li>
							</ul>
						</ul>
					</div>
				</div>
			</div>
			<!--页面主内容区结束-->
				</div>
			</div>
	</div>

	<footer>
		<nav class="mui-bar mui-bar-tab">
    	<a class="mui-tab-item" id="select">
        	<span class="mui-icon mui-icon-list"></span>
        	<span class="mui-tab-label">精选</span>
    	</a>
    	<a class="mui-tab-item" id="house">
        	<span class="mui-icon mui-icon-home"></span>
        	<span class="mui-tab-label">房源</span>
    	</a>
   		 <a class="mui-tab-item" id="service">
   	    	<span class="mui-icon mui-icon-chatboxes"></span>
        	<span class="mui-tab-label">业务</span>
    	</a>
    	<a class="mui-tab-item mui-active" id="my">
       		<span class="mui-icon mui-icon-person"></span>
        	<span class="mui-tab-label">我的</span>
    	</a>
    	</nav>
	</footer>
</body>
	<script src="<%=request.getContextPath()%>/static/js/mui.min.js "></script>
	<script src="<%=request.getContextPath()%>/static/js/mui.view.js "></script>

	<script>
		mui.init();
		
		//初始化单页的区域滚动
		mui('.mui-scroll-wrapper').scroll();
		//底部跳转
		document.getElementById('select').addEventListener('tap', function() {
			 //打开关于页面
			mui.openWindow({
				url: 'to_goToChoice', 
			id:'info'
				});
			});
			document.getElementById('house').addEventListener('tap', function() {
			 //打开关于页面
			mui.openWindow({
				url: 'to_goToMessageApp', 
			id:'info'
				});
			});
			document.getElementById('service').addEventListener('tap', function() {
			 //打开关于页面
			mui.openWindow({
				url: 'to_goToMidMyService', 
			id:'info'
				});
			});
			if (!$("#my").hasClass('mui-active')) {
				document.getElementById('my').addEventListener('tap', function() {
				 //打开关于页面
				mui.openWindow({
					url: 'to_goToMediationInfo', 
				id:'info'
					});
					
				});
			}

		
		/* (function($) {
			var view = viewApi.view;
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
		})(mui);		 */
	</script>

</html>
