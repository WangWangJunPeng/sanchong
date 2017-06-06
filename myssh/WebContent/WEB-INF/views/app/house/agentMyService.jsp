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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/agentMyService.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			getDataInfo();
			getSaleRenGouNum();
		});
		function getSaleRenGouNum(){
			$.ajax({
    			type:"post",
    			async : false,
    			url:"to_getSaleCosntractSize",
    			success:function(data){
    				data=eval("("+data+")");
    				renderDateInfo(data.data);
    			}
    		});
		}
		function renderDateInfo(data){
    		$('#contractNum').html(data.contractNum);
    	}
		function getDataInfo(){
			$.ajax({
				type:"post",
				url:"to_saleBusiness",
				success:function(data){
					data=eval("("+data+")");
					getSaleServiceInfo(data.data);
				}
			});
		}
		function getSaleServiceInfo(data){
			$('#projectName').html(data.projectName);
			$('#yidaofangNum').html(data.yidaofangNum);
			$('#yichengjiaoNum').html(data.yichengjiaoNum);
			
		}
	
	</script>
    <title>业务</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
			<h1 class="mui-title">业务</h1>
	</header>
	<div class="mui-content">
		<div class="meaaageList">
			<div class="message">
				<div class="middle">
					<h3 id="projectName"></h3>
				<ul>				
					<li>
						<a href="to_saleVisitList">
							<p>已到访</p>
							<p id="yidaofangNum"></p>
						</a>
					</li>
					<li>
						<a href="to_saleDeal">
							<p>已成交</p>
							<p id="yichengjiaoNum"></p>
						</a>
					</li>
				</ul>			
				</div>		
			</div>
			
		</div>
		<div class="btn-confirm">
			<form action="to_goToAllMyContractRecordsListPage" method="post">
				<button class="btn-customer" type="submit">我的订单（<span id="contractNum"></span>）</button>
			</form>
		</div>
	</div>	
	<footer>
		<nav class="mui-bar mui-bar-tab" id="footerbar">
    	<a class="mui-tab-item" id="select">
        	<span class="mui-icon mui-icon-list"><img src="" alt=""></span>
        	<span class="mui-tab-label">任务</span>
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
							/*
							 * rs.value 拼合后的 value
							 * rs.text 拼合后的 text
							 * rs.y 年，可以通过 rs.y.vaue 和 rs.y.text 获取值和文本
							 * rs.m 月，用法同年
							 * rs.d 日，用法同年
							 * rs.h 时，用法同年
							 * rs.i 分（minutes 的第二个字母），用法同年
							 */
							result1.innerText =  rs.text+' 00:00:00';
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
				btn2.each(function(i, btn) {
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
							/*
							 * rs.value 拼合后的 value
							 * rs.text 拼合后的 text
							 * rs.y 年，可以通过 rs.y.vaue 和 rs.y.text 获取值和文本
							 * rs.m 月，用法同年
							 * rs.d 日，用法同年
							 * rs.h 时，用法同年
							 * rs.i 分（minutes 的第二个字母），用法同年
							 */
							result2.innerText =  rs.text+' 23:59:59';
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
			
			document.getElementById('select').addEventListener('tap', function() {
				 //打开关于页面
				mui.openWindow({
					url: 'to_goToAgentIndex', 
					show: {
						autoShow:false
					},
				id:'info'
					});
				});
		
			
			document.getElementById('house').addEventListener('tap', function() {
			 //打开关于页面
			mui.openWindow({
				url: 'to_goToSaleHouseDetail', 
			id:'info'
				});
			});
		if (!$("#service").hasClass('mui-active')) {
			document.getElementById('service').addEventListener('tap', function() {
			 //打开关于页面
			mui.openWindow({
				url: 'to_goToAgentMyService', 
			id:'info'
				});
			});
			
		}
			document.getElementById('my').addEventListener('tap', function() {
			 //打开关于页面
			mui.openWindow({
				url: 'to_goToAgentMyPerson', 
			id:'info'
				});
		});
		</script>
</body>
</html>
