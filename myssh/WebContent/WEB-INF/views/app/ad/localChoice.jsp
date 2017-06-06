<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html  class="feedback">
<head>
    <meta charset="utf-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <link href="<%=request.getContextPath()%>/static/css/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/feedback.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/localChoice.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <title>本地精选</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>			
			<h1 class="mui-title">本地精选</h1>
	</header>
	<div class="mui-content">
		<div class="messageList">
		<c:forEach items="${data }" var="list">
			<div class="bg">
				<div class="message">
				<a href="to_midOneProject?projectId=${list.projectId }">
					
				<c:if test="${list.adPicture != null && list.adPicture !='' }">
					<img src="${list.adPicture }" alt="" />
				</c:if>
				<c:if test="${list.adPicture == null || list.adPicture =='' }">
					<img src="<%=request.getContextPath()%>/static/images/morentupian.png" alt="" />
				</c:if>
				
					<input type="hidden" name="projectId" value="${list.projectId }">
					<h4>${list.projectName }均价<span>${list.averagePrice }</span>元/平方米</h4>
				<div class="icons mui-inline floatLeft">
					<c:if test="${list.stars == '1.0' }">
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
						</c:if>
						<c:if test="${list.stars == '1.5' }">
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/halfStar.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>							
						</c:if>
						<c:if test="${list.stars == '2.0' }">
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
						</c:if>
						<c:if test="${list.stars == '2.5' }">
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/halfStar.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
						</c:if>
						<c:if test="${list.stars == '3.0' }">
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
						</c:if>
						<c:if test="${list.stars == '3.5' }">
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/halfStar.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
						</c:if>
						<c:if test="${list.stars == '4.0' }">
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/starLine.png" alt=""></i>
						</c:if>
						<c:if test="${list.stars == '4.5' }">
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/halfStar.png" alt=""></i>
						</c:if>
						<c:if test="${list.stars == '5.0' }">
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
							<i class="star"><img src="static/images/star.png" alt=""></i>
						</c:if>
				</div>
					<div class="floatLeft"><span>${list.isFast }</span><span>${list.isDaiKan }</span></div>
					<div class="floatRight">可售房源:<span>${list.unitCount }套</span></div>
				</a>
				</div>
			</div>
			
		</c:forEach>
		</div>
	</div>
	
	<script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
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
		 mui('.icons').on('tap','i',function(){
	  	var index = parseInt(this.getAttribute("data-index"));
	  	var parent = this.parentNode;
	  	var children = parent.children;
	  	if(this.classList.contains("mui-icon-star")){
	  		for(var i=0;i<index;i++){
  				children[i].classList.remove('mui-icon-star');
  				children[i].classList.add('mui-icon-star-filled');
	  		}
	  	}else{
	  		for (var i = index; i < 5; i++) {
	  			children[i].classList.add('mui-icon-star')
	  			children[i].classList.remove('mui-icon-star-filled')
	  		}
	  	}
	  	starIndex = index;
  });
	</script>
</body>
</html>
