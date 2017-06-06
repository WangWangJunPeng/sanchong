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
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <link href="<%=request.getContextPath()%>/static/css/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/choice.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    
    <script type="text/javascript">
    	$(document).ready(function(){
    		//adLoad();
    		getLocalCity();
    	})
    	
    	
    	function getLocalCity(){
    		$.ajax({
    			type:"post",
    			url:"to_getDefaultCity",
    			data:{shengOrShi:$('#hiddenCityId').val(),
    				citySigle:$('#hiddenCitySigle').val()},
    			success:function(data){
    				data=eval("("+data+")");
    				renderDateInfo(data.data);
    				adLoad(data.data);
    			}
    		});
    	}
    	function renderDateInfo(data){
    		$('#cityName').html(data.cityName);
    		$('#cityId').html(data.cityId);
    		//$('.hrefTwo').attr('href','list_current_city_ads?adPosition=foryou&shi='+data.cityId);
    		//$('.hrefOne').attr('href','list_current_city_ads?adPosition=location&shi='+data.cityId);
    		$('#setCityId').html('<a href="to_goToCityList" id="cityId" data-value="'+data.cityId+'"><span id="cityName" >'+data.cityName+'</span><i class="mui-icon mui-icon-arrowdown"></i></a>');
    	}
    	function adLoad(data){
    		$.ajax({
      			type:"post",
      			url:"to_listAdsForYou",
     			data:{shi:data.cityId},
      			success:function(data){
      				data=eval("("+data+")");
      				$.each(data.root,function(v,o){
      					if(o.adPosition == 'foryou' ){
      						getForYou(o);
      					}else if(o.adPosition == 'location' ){
      						getLocation(o);
      					}else if(o.adPosition == 'others'){
      						
      						getForOthers(o);
      					}
      					
      				});
      			}
      		});
    	}
    	var ary=new Array();
    	var aryTwo = new Array();
		function getForYou(o){
			if (o.adUrl != null && o.adUrl !=""){
				ary.push(o.adUrl);
				
				$('.foryou').attr("src",ary[0]);
			}
			
		}
		
		function getLocation(o){
			if (o.adUrl != null && o.adUrl !=""){
				aryTwo.push(o.adUrl);
				$('.location').attr("src",aryTwo[0]);
			}
			
		}
		 function getForOthers(o){
			 if (o.adUrl != null && o.adUrl !=""){
				$(".messageList") .append('<div class="bg"><div class="message"><a href="to_midOneProject?projectId='+o.projectId+'"><img src="'+o.adUrl+'" alt="" /><h4>'+o.projectName+'</h4></a></div></div>');
			 }else {
				 $(".messageList") .append('<div class="bg"><div class="message"><a href="to_midOneProject?projectId='+o.projectId+'"><img src="<%=request.getContextPath()%>/static/images/morentupian.png" alt="" /><h4>'+o.projectName+'</h4></a></div></div>');
			 }
			 
		} 
		
    </script>
    <title>精选首页</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav">
		
			<h1 class="mui-title" id="setCityId">
				<input type="hidden" id="hiddenCityId" value="${cityDataInfo.shengshiId }">
				<input type="hidden" id="hiddenCityName" value="${cityDataInfo.cityName }">
				<input type="hidden" id="hiddenCitySigle" value="${cityDataInfo.citySigle }">
			</h1>
		
	</header>
	<div class="mui-content">
		<div class="mui-slider">
  			<div class="mui-slider-group mui-slider-loop">
    <!--支持循环，需要重复图片节点-->
   			<div class="mui-slider-item mui-slider-item-duplicate"><a href="list_current_city_ads?adPosition=location&shi=${sessionScope.csdto.cityId}" class="hrefOne"><img src="<%=request.getContextPath()%>/static/images/morentupian.png" class="location"/><p class="mui-slider-title"><span class="redBar"></span><span class="leftq">本地精选</span></p></a></div>
    		<div class="mui-slider-item"><a href="list_current_city_ads?adPosition=foryou&shi=${sessionScope.csdto.cityId}" class="hrefTwo"><img src="<%=request.getContextPath()%>/static/images/morentupian.png" class="foryou"/><p class="mui-slider-title"><span class="redBar"></span><span class="leftq">为你推荐</span></p></a></div>
    		
    		<div class="mui-slider-item"><a href="list_current_city_ads?adPosition=location&shi=${sessionScope.csdto.cityId}" class="hrefOne"><img src="<%=request.getContextPath()%>/static/images/morentupian.png" class="location"/><p class="mui-slider-title"><span class="redBar"></span><span class="leftq">本地精选</span></p></a></div>
    		
    <!--支持循环，需要重复图片节点-->
    		<div class="mui-slider-item mui-slider-item-duplicate"><a href="list_current_city_ads?adPosition=foryou&shi=${sessionScope.csdto.cityId}" class="hrefTwo"><img src="<%=request.getContextPath()%>/static/images/morentupian.png" class="foryou"/><p class="mui-slider-title"><span class="redBar"></span><span class="leftq">为你推荐</span></p></a></div>
  			</div>
		</div>
		<div class="messageList">
			
			
		</div>
	</div>
	<footer>
		<nav class="mui-bar mui-bar-tab">
    	<a class="mui-tab-item mui-active" id="select" style="padding:0;">
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
    	<a class="mui-tab-item" id="my">
       		<span class="mui-icon mui-icon-person"></span>
        	<span class="mui-tab-label">我的</span>
    	</a>
    	</nav>
	</footer>

	<script type="text/javascript">
	//初始化
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
			//获得slider插件对象
		var gallery = mui('.mui-slider');
			gallery.slider({
  			interval:4000//自动轮播周期，若为0则不自动播放，默认为0；
		});
			
			//初始化单页的区域滚动
			mui('.mui-scroll-wrapper').scroll();

			//底部跳转
				if (!$("#select").hasClass('mui-active')) {
					document.getElementById('select').addEventListener('tap', function() {
		 			 //打开关于页面
		  			mui.openWindow({
		   				url: 'to_goToChoice', 
		    			id:'info'
		  				});
		  			});
				}
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
