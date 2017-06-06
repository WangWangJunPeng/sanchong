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
    <link href="<%=request.getContextPath()%>/static/css/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/app.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/mui.picker.min.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/myService.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
	<script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>    
    <script type="text/javascript">
    	$(document).ready(function(){
    		getFooterTop();
    		getDataInfo();
    		getDataService();
    	});
    	function getDataInfo(){
    		$.ajax({
    			type:"post",
    			async : false,
    			url:"to_getMidCosntractSize",
    			success:function(data){
    				data=eval("("+data+")");
    				renderDateInfo(data.data);
    			}
    		});
    	}
    	function renderDateInfo(data){
    		$('#contractNum').html(data.contractNum);
    	}
    	function getFooterTop(){
    		$("#projectNameTwo").focus(function(){
    			$("#footerbar").css("top",$(document.body).height()-$("#footerbar").height());
    		})
    	}
    	function getDataService(){
    		$.ajax({
    			type:"post",
    			async : false,
    			url:"to_midBusiness",
    			data:{projectName:$('#projectNameTwo').val(),startTime:$('#st').val(),endTime:$('#en').val()},
    			success:function(data){
    				data=eval("("+data+")");
    				inService(data.root);
    				
    			}
    		});
    	}
    	function inService(data){
    		var s = '';
    		$.each(data,function(v,o){
    			s += '<div class="middle" ><h3 id="projectName">'+o.projectName +'</h3><ul>';
				s += '<a href="to_goToNearOverdue?projectId='+o.projectId+'&startTime='+$('#st').val()+'&endTime='+$('#en').val()+'">';
				s += '<li><p>将过期</p><p id="nearOverdue">'+o.nearOverdue +'</p></li></a>';
				s += '<a href="GuideRecordsList?projectId='+o.projectId+'&startTime='+$('#st').val()+'&endTime='+$('#en').val()+'">';
				s += '<li><p>已备案</p><p id="guideRecords">'+o.guideRecords +'</p></li></a>';
				s += '<a href="to_visitList?projectId='+o.projectId+'&startTime='+$('#st').val()+'&endTime='+$('#en').val()+'">';
				s += '<li><p>已到访</p><p id="visitRecords">'+o.visitRecords +'</p></li></a>';
				s += '<a href="to_midHaveDeal?projectId='+o.projectId+'&startTime='+$('#st').val()+'&endTime='+$('#en').val()+'">';
				s += '<li><p>已成交</p><p id="deal">'+o.deal +'</p></li></a></div>';
    		});
    		if(data.length>0){
    			$('#selectServiceInfo').html(s);
    		}else {
				 $("#selectServiceInfo").html("<tr><td style='width:10%;height:30px;margin:0 auto;'>暂无数据</td></tr>");
			 }
    	}
    </script>
    <title>业务</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
			<h1 class="mui-title">业务</h1>
	</header>
	<div class="mui-content">
		<div class="datePicker">
			<form action="" method="post">
				<button id='demo1' data-options='{"type":"date"}' class="btn mui-btn mui-btn-block btn1" name="startTime" >选择日期</button>
				<span>_</span>
				<input type="hidden" value="" id="st">
				<input type="hidden" value="" id="en">
				<button id='demo2' data-options='{"type":"date"}' class="btn mui-btn mui-btn-block btn2" name="endTime" >选择日期 </button>
				<input class="" type="" name="projectName" id="projectNameTwo" value="" placeholder="项目名称"/>
				<!-- <span class="mui-icon mui-icon-search"></span> -->
				<button type="button" id="btn-search" onclick="getDataService()">搜索</button>
			</form>			
		</div>
		<div class="meaaageList">
			<div class="message" id="selectServiceInfo">
			</div>
			
		</div>
		<div class="btn-confirm">
			<form action="to_goToAllMyContractRecordsListPage" method="post"><button class="btn-customer" type="submit">我的订单(<span id="contractNum"></span>)</button></form>			
		</div>
	</div>	
	<footer>
		<nav class="mui-bar mui-bar-tab" id="footerbar">
    	<a class="mui-tab-item" id="select">
        	<span class="mui-icon mui-icon-list"><img src="" alt=""></span>
        	<span class="mui-tab-label">精选</span>
    	</a>
    	<a class="mui-tab-item" id="house">
        	<span class="mui-icon mui-icon-home"><img src="" alt=""></span>
        	<span class="mui-tab-label">房源</span>
    	</a>
   		 <a class="mui-tab-item mui-active" id="service">
   	    	<span class="mui-icon mui-icon-chatboxes"><img src="" alt=""></span>
        	<span class="mui-tab-label">业务</span>
    	</a>
    	<a class="mui-tab-item" id="my">
       		<span class="mui-icon mui-icon-person"><img src="" alt=""></span>
        	<span class="mui-tab-label">我的</span>
    	</a>
    	</nav>
	</footer>
	<script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/js/mui.picker.min.js"></script>
	<script>
			(function($) {
				$.init();
				var result1 = $('#demo1')[0];
				
				var result2 = $('#demo2')[0];
				var btn1 = $('.btn1');
				var btn2 = $('.btn2');
				btn1.each(function(i, btn) {
					btn.addEventListener('tap', function() {
						var optionsJson = this.getAttribute('data-options') || '{}';
						var options = JSON.parse(optionsJson);
						var id = this.getAttribute('id');
						/*
						 * 首次显示时实例化组件
						 * 示例为了简洁，将 options 放在了按钮的 dom 上
						 * 也可以直接通过代码声明 optinos 用于实例化 DtPicker
						 */
						var picker = new $.DtPicker(options);
						picker.show(function(rs) {
							
							result1.innerText =  rs.text;
							document.getElementById("st").value = result1.innerText+' 00:00:00';
							//console.log(document.getElementById("st").value);
							
							picker.dispose();
							console.log()
						});
					}, false);
				});
				btn2.each(function(i, btn) {
					btn.addEventListener('tap', function() {
						var optionsJson = this.getAttribute('data-options') || '{}';
						var options = JSON.parse(optionsJson);
						var id = this.getAttribute('id');
						
						var picker = new $.DtPicker(options);
						picker.show(function(rs) {
							
							result2.innerText =  rs.text;
							document.getElementById("en").value = result2.innerText+' 23:59:59';
							/* 
							 * 返回 false 可以阻止选择框的关闭
							 * return false;
							 */
							/*
							 * 释放组件资源，释放后将将不能再操作组件
							 * 通常情况下，不需要示放组件，new DtPicker(options) 后，可以一直使用。
							 * 当前示例，因为内容较多，如不进行资原释放，在某些设备上会较慢。
							 * 所以每次用完便立即调用 dispose 进行释放，下次用时再创建新实例。
							 */
							picker.dispose();
						});
					}, false);
				});
				
			})(mui);
			
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
	  			if (!$("#service").hasClass('mui-active')) {
		  			document.getElementById('service').addEventListener('tap', function() {
		 			 //打开关于页面
		  			mui.openWindow({
		   				url: 'to_goToMidMyService', 
		    			id:'info'
		  				});
		  			});
	  			}
	  			document.getElementById('my').addEventListener('tap', function() {
	 			 //打开关于页面
	  			mui.openWindow({
	   				url: 'to_goToMediationInfo', 
	    			id:'info'
	  				});
				});
		</script>
</body>
</html>
