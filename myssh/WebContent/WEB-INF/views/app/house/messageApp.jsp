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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/messageAPP.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/messageAPP.js" type="text/javascript"></script>   
    <script type="text/javascript">
    	$(document).ready(function(){
    		getFooterTop();
    		getLocalCity();
    		//getLocalArea();
    		getProjectInfo();
    		getFavNumShow();
    		//getCityName();
    	});
    	function getFooterTop(){
    		$("#btn-search").focus(function(){
    			$("#footerbar").css("top",$(document.body).height()-$("#footerbar").height());
    		})
    	}
    	function getFavNumShow(){
    		$.ajax({
      			 type : "post",
      			 async : false,
      			 url:"to_favoriteListSize",
      			 success : function (data) {
      				 data = eval("(" +data+")");
      				 renderFavNum(data.data);
      			 }
      		 });
    	}
    	function renderFavNum(data){
    		$('#favNum').html('('+data.favSize+')');
    	}
    	
    	function getCityName(){
    		$.ajax({
   			 type : "post",
   			 async : false,
   			 url:"to_getCityNameById",
   			 data:{shengOrShi:$('#city').html()},
   			 success : function (data) {
   				 data = eval("(" +data+")");
   				 $('#cityAreaName').html(data.data.cityName)
   				
   			 }
   		 });
    	}
    	function getLocalArea(data){
    		$.ajax({
    			type:"post",
    			async : false,
    			url:"menu_list_city_area",
    			data:{shengOrShi:data.cityId},
    			success:function(data){
    				data=eval("("+data+")");
    				renderAreaInfo(data.root);
    			}
    		});
    	}
    	function renderAreaInfo(data){
    		$.each(data,function(v,o){
    			$('#areaNext').after('<li class="appHouseCity" data-value="'+o.cityId+'" onclick="getCityArea(this)" class="cityArea">'+o.cityName+'</li>');
    		});
    	}
    	function getCityArea(obj){
    		var aaa = $(obj).data("value");
    		 $("#hc").val(aaa);
    		 $(".navMessage").css("display","none").children().removeClass("show");
    		getProjectInfo();
    	}
    	
    	function getLocalCity(){
    		$.ajax({
    			type:"post",
    			async : false,
    			url:"to_getDefaultCity",
    			data:{shengOrShi:$('#hiddenCityId').val(),
    				citySigle:$('#hiddenCitySigle').val()},
    			success:function(data){
    				data=eval("("+data+")");
    				renderDateInfo(data.data);
    				getLocalArea(data.data);
    				//getProjectInfo(data.data);
    				//setCity(data.data);
    			}
    		});
    	}
    	function setCity(data){
    		$('#hiddenSetCity').val(data.cityId);
    	}
    	function renderDateInfo(data){
    		$('#setCityId').html('<a href="to_goToCityToAreaList" id="cityId" data-value="'+data.cityId+'"><span class="mui-icon mui-icon-arrowdown" id="cityName">'+data.cityName+'</span></a>');
    	
    	}
    	
    	function getHouseNo(obj){
	    		var aaa = $(obj).data("value");
	   			$("#searchHouseNo").val(aaa);
	   			$(".navMessage").css("display","none");
	   			getProjectInfo();
    	}
    	function getHouseArea(obj){
    		var aaa = $(obj).data("value");
   			$("#searchHouseArea").val(aaa);
   			$(".navMessage").css("display","none");
   			getProjectInfo();
    	}
    	function getMore(obj){
    		var aaa = $(obj).data("value");
   			$("#searchMore").val(aaa);
   			$(".navMessage").css("display","none");
   			getProjectInfo();
    	}
    	function getUpAndDown(obj){
    		var aaa = $(obj).data("value");
   			$("#searchUpAndDown").val(aaa);
   			$(".navMessage").css("display","none");
   			getProjectInfo();
    	}
    	function getProjectInfo(){
    		$.ajax({
      			type:"post",
      			async : false,
      			url:"to_zhongjieSelectProject",
     			data:{conditions : $('#btn-search').val(),
     				shi: $('#cityId').data("value"),
     				qu : $('#hc').val(),
     				housType : $('#searchHouseNo').val(),
     				area : $('#searchHouseArea').val(),
     				recommend : $('#searchUpAndDown').val(),
     				more : $('#searchMore').val()
     			},
      			success:function(data){
      				data=eval("("+data+")");
      				getEveryProjectInfo(data.root);
      			}
      		});
    	}
    	function getEveryProjectInfo(data){
    		var s = '';
    		$.each(data,function(v,o){
    			s += '<a href="to_midOneProject?projectId='+o.projectId+'"><section class="listStyle contain"><div class="leftbox">';
    			 if (o.adPicture != null && o.adPicture !=""){
	    			s += '<img src="'+o.adPicture+'" alt="" /></div>';
    			 }else {
    				 s += '<img src="<%=request.getContextPath()%>/static/images/morentupian.png" alt="" /></div>';
    			 }
				if (o.isCollection == '已收藏'){
					s += '<div class="rightbox"><h1 class="list1">'+o.projectName+'<span><input class="whiteInp" name="checkbox1" disabled="disabled" onchange="getChoiceToFav(this)" value="'+o.projectId+'" type="checkbox" ><img class="imgSi" src="static/images/heart.png" alt=""></span></h1>';					
    			}else{
    				s += '<div class="rightbox"><h1 class="list1">'+o.projectName+'<span><input class="whiteInp" name="checkbox1" onchange="getChoiceToFav(this)" value="'+o.projectId+'" type="checkbox" ><img class="imgSi" src="static/images/heart-line.png" alt=""></span></h1>';        			
    			}
				s += '<p class="list2">'+o.city+'-可售房源<span>'+o.unitCount+'套</span></p><div class="list3"><span>惠</span>';
    			s += '<div class="list-right"><p>'+o.youhuiInfo+'</p><p>共有'+o.youhuiListSize+'条优惠-请见详情页</p></div></div>';
    			s += '<p class="list4">';
    			
    			if (o.isFast!=null && o.isFast !=""){
    				s += '<span class="box-border">'+o.isFast+'</span>';
    			}
    			if (o.isYiDi != null && o.isYiDi != ""){
    				s += '<span class="box-border">'+o.isYiDi+'</span>';
    			}
    			if (o.isDaiKan != null && o.isDaiKan !=""){
    				s += '<span class="box-border">'+o.isDaiKan+'</span>';
    			}
    			
    			if (o.stars == '1.0'){
	    			s += '<span class="star"><img src="static/images/star.png" alt=""></span>';
    			}
    			if (o.stars == '1.5'){
	    			s += '<span class="star"><img src="static/images/halfStar.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>';
    			}
    			if (o.stars == '2.0'){
	    			s += '<span class="star"><img src="static/images/star.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>';
    			}
    			if (o.stars == '2.5'){
	    			s += '<span class="star"><img src="static/images/halfStar.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>';
    			}
    			if (o.stars == '3.0'){
	    			s += '<span class="star"><img src="static/images/star.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>';
    			}
    			if (o.stars == '3.5'){
	    			s += '<span class="star"><img src="static/images/halfStar.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>';
    			}
    			if (o.stars == '4.0'){
	    			s += '<span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>';
    			}
    			if (o.stars == '4.5'){
	    			s += '<span class="star"><img src="static/images/halfStar.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>';
    			}
    			if (o.stars == '5.0'){
	    			s += '<span class="star"><img src="static/images/star.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>'+'<span class="star"><img src="static/images/star.png" alt=""></span>';
    			}
    			
    			s += '</p></div></section></a>';
    		});
    		 if(data.length>0){
     			$('#selectOneProjectInfo').html(s);
 			 }else {
 				 $("#selectOneProjectInfo").html("<tr><td style='width:10%;height:30px;margin:0 auto;'>暂无数据</td></tr>");
    		}
    	}
    	
    	 function getChoiceToFav(obj){
    		 if(obj.checked){
    			 var aa = $(obj).val();
    			 console.log(aa)
    			 	$.ajax({
    				type:"post",
    				url:"to_addFavorite",
    				data : {projectId : aa},
    				success : function (msg){
    					getFavNumShow();
    				}
    			 
    		 });
    		}
    	 }
    	 
    	 function getSearchProject(){
    		 getProjectInfo();
    	 }
    </script>
    
    <script type="text/javascript" charset="utf-8">
      	mui.init();
//		点击爱心变换
$(document).ready(function(){
		var inputs=$(".whiteInp");
		var li=$('.navMessage li');
		inputs.click(function(){
			if($(this).is(":checked")){
				$(this).attr("disabled","disabled");
				$(this).next().attr("src","static/images/heart.png");				
			}else{
				$(this).next().attr("src","static/images/heart-line.png");
			}
		});
		li.click(function(){
			//$('.navMessage').css('display','none');
			$('.message').removeClass('mask');
		});
});
    </script>
     <title>信息页</title>
</head>
<body>
	<header class="header">
		<div class="headerMid">
	    	<div class="city" id="setCityId">
	    		<input type="hidden" id="hiddenCityId" value="${cityDataInfo.shengshiId }">
				<input type="hidden" id="hiddenCityName" value="${cityDataInfo.cityName }">
				<input type="hidden" id="hiddenCitySigle" value="${cityDataInfo.citySigle }">
	   	 	</div>
	   		<div class="search-group">
	   			<form action="">
	   				<input type="text" id="btn-search" name="conditions" value="" placeholder="输入项目名称">
	   				<button class="btn-topSearch" type="button" onclick="getSearchProject()">
	   					<span class="mui-icon mui-icon-search"></span>
	   				</button>	   				
	   			</form>
	   		</div>
	    	<img class="locationImg" src="static/images/dingwei.png" >
		</div>	
	</header>
		<input id="hc" type="hidden">
		<input id="searchHouseNo" type="hidden">
		<input id="searchHouseArea" type="hidden">
		<input id="searchUpAndDown" type="hidden">
		<input id="searchMore" type="hidden">
	<div id="tabbar" class="mui-content">
		<nav class="navbar">
	   		<ul id="navlist">
	   			<li>
	   				区域<span class="mui-icon mui-icon-arrowdown"></span>
	   			</li>
	   			<li>
	   				户型<span class="mui-icon mui-icon-arrowdown"></span>
	   			</li>
	   			<li>
	   				面积<span class="mui-icon mui-icon-arrowdown"></span>
	   			</li>
	   			<li>
	   				推荐<span class="mui-icon mui-icon-arrowdown"></span>
	   			</li>
	   			<li>
	   				更多<span class="mui-icon mui-icon-arrowdown"></span>
	   			</li>
	   		</ul>
	   </nav>	
<div class="navMessage">
		<ul class="list earalist">
	   					<li  class="eara listbar">	   						
							<ul class="listbar-right showed" id="areaNext">
								<li data-value="" onclick="getCityArea(this)">不限</li>
								<!-- <a href=""><li>上城区</li></a>
								<a href=""><li>下城区</li></a>
								<a href=""><li>西湖区</li></a>
								<a href=""><li>拱墅区</li></a>
								<a href=""><li>江干区</li></a>
								<a href=""><li>滨江区</li></a> -->
							</ul>
	   					</li>
	   	</ul>
	   	<ul class="list earalist">
	   					<li  class="eara listbar">
							<ul class="listbar-right showed">
								<li data-value="" onclick="getHouseNo(this)">不限</li>
								<li data-value="一房" onclick="getHouseNo(this)">一房</li>
								<li data-value="两房" onclick="getHouseNo(this)">两房</li>
								<li data-value="三房" onclick="getHouseNo(this)">三房</li>
								<li data-value="四房" onclick="getHouseNo(this)">四房</li>
								<li data-value="五房 " onclick="getHouseNo(this)">五房</li>
								<li data-value="六房 " onclick="getHouseNo(this)">六房</li>
							</ul>
	   					</li>
	   	</ul>
	   	<ul class="list earalist">
	   					<li  class="eara listbar">
							<ul class="listbar-right showed">
								<li data-value="" onclick="getHouseArea(this)">不限</li></a>
								<li data-value="50" onclick="getHouseArea(this)">50平米以下</li>
								<li data-value="50-70" onclick="getHouseArea(this)">50-70平米</li>
								<li data-value="70-90" onclick="getHouseArea(this)">70-90平米</li>
								<li data-value="90-110" onclick="getHouseArea(this)">90-110平米</li>
								<li data-value="110-130" onclick="getHouseArea(this)">110-130平米</li>
								<li data-value="130-150" onclick="getHouseArea(this)">130-150平米</li>
								<li data-value="150" onclick="getHouseArea(this)">150平米以上</li>
							</ul>
	   					</li>
	   	</ul>
	   	<ul class="list earalist">
	   					<li  class="eara listbar">
							<ul class="listbar-right showed">
								<li data-value="" onclick="getUpAndDown(this)">不限</li></a>
								<li data-value="daikanDown" onclick="getUpAndDown(this)">带看佣金从高到低</li>
								<li data-value="daikanUp" onclick="getUpAndDown(this)">带看佣金从低到高</li>
								<li data-value="fenxiaoDown" onclick="getUpAndDown(this)">分销佣金从高到低</li>
								<li data-value="fenxiaoUp" onclick="getUpAndDown(this)">分销佣金从低到高</li>
							</ul>
	   					</li>
	   	</ul>
	   	<ul class="list earalist">
	   					<li  class="eara listbar">
							<ul class="listbar-right showed">
								<li data-value="" onclick="getMore(this)">不限</li>
								<li data-value="支持带看" onclick="getMore(this)">支持带看</li>
								<li data-value="快速结佣" onclick="getMore(this)">快速结佣</li>
								<li data-value="异地备案" onclick="getMore(this)">异地备案</li>
							</ul>
	   					</li>
	   	</ul>
	</div>   	
	    <div class="message" id="selectOneProjectInfo">
	    	
	    </div>	    	    
 </div>
	    <!--进入收藏按钮-->
	    <div class="btn-faverite">
	    	<form action="to_favoriteList" method="post">
		    	<button type="submit" class="btn-shoucang">
		    		<span>收藏夹</span>
		    		<span id="favNum"></span>
		    	</button>
	    	</form>	    	
	    </div>
	<footer>
		<nav class="mui-bar mui-bar-tab" id="footerbar">
    	<a class="mui-tab-item" id="select">
        	<span class="mui-icon mui-icon-list"><img src="" alt=""></span>
        	<span class="mui-tab-label">精选</span>
    	</a>
    	<a class="mui-tab-item mui-active" id="house">
        	<span class="mui-icon mui-icon-home"><img src="" alt=""></span>
        	<span class="mui-tab-label">房源</span>
    	</a>
   		 <a class="mui-tab-item" id="service">
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
	<script type="text/javascript">
	//初始化
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
			
			//底部跳转
				document.getElementById('select').addEventListener('tap', function() {
	 			 //打开关于页面
	  			mui.openWindow({
	   				url: 'to_goToChoice', 
	    			id:'info'
	  				});
	  			});
			if (!$("#house").hasClass('mui-active')) {
	  			document.getElementById('house').addEventListener('tap', function() {
	 			 //打开关于页面
	  			mui.openWindow({
	   				url: 'to_goToMessageApp', 
	    			id:'info'
	  				});
	  			});
			}
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