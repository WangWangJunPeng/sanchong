<%@page import="java.net.URLEncoder"%>
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/collection.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/messageAPP.js" type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
      
      	
      	function getDelete(){
      		var aa="";
      		$("input[name='checkbox1']:checked").each(function(){
      			
      			aa +=$(this).val() + ",";
      		})
      		//console.log(aa);
      		$.ajax({
      			type:"post",
      			url:"to_deleteFav",
      			data:{allProjectId:aa},
      			success:function(msg){
     				window.location.href="to_favoriteList";
      			},
      		});
      	}
      	function getChecked(){
      		var aa="";
      		$("input[name='checkbox1']:checked").each(function(){
      			aa +=$(this).val() + ",";
      		})
      		if(aa == ""){
      			
      			return false;
      		}else if(aa != ""){
      			 $("#allProjectId").val(aa);
      		}
      		
      	}
      	
    </script>
     <title>收藏夹项目</title>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">收藏夹项目</h1>
		<button type="button" class="btn-finished">编辑</button>
		<button type="button" class="btn-ok" style="display: none;">完成</button>
	</header> 
	<div class="mui-content">
	    <div  id="tabbar"  class="message">
	    <c:forEach items="${data }" var="list">
		    <a href="to_midOneProject?projectId=${list.projectId }">
		    	<section class="listStyle">
		    		<div class="leftbox">
			    		<c:if test="${list.adPicture != null && list.adPicture !='' }">
							<img src="${list.adPicture }" alt="" />
						</c:if>
						<c:if test="${list.adPicture == null || list.adPicture =='' }">
							<img src="<%=request.getContextPath()%>/static/images/morentupian.png" alt="" />
						</c:if>
		    		</div>
		    		<div class="rightbox">
		    			<h1 class="list1">
		    				${list.projectName }
							<span class="mui-input-row mui-checkbox">
								<label></label>
								<input name="checkbox1" type="checkbox" value="${list.projectId }">
							</span>
		    			</h1>
		    			<p class="list2">${list.city }-可售房源<span>${list.unitCount }套</span></p>
		    			<div class="list3">
		    				<span>惠</span>
		    				<div class="list-right">
		    					<p>${list.youhuiInfo }</p>
		    					<p>共有${list.youhuiListSize }条优惠-请见详情页</p>
		    				</div>	    				
		    			</div>
		    			<p class="list4">
		    			<c:if test="${list.isFast == '快'}">
			    			<span class="box-border isFast">${list.isFast }</span>
		    			</c:if>
	    				<c:if test="${list.isYiDi == '异'}">
			    			<span class="box-border isYiDi">${list.isYiDi }</span>
		    			</c:if>
		    			<c:if test="${list.isDaiKan == '看'}">
			    			<span class="box-border isDaiKan">${list.isDaiKan }</span>
		    			</c:if>
		    			
		    			<c:if test="${list.stars == '1.0' }">
							<span class="star"><img src="static/images/star.png" alt=""></span></p>
						</c:if>
						<c:if test="${list.stars == '1.5' }">
							<span class="star"><img src="static/images/halfStar.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span></p>
						</c:if>
						<c:if test="${list.stars == '2.0' }">
							<span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span></p>
						</c:if>
						<c:if test="${list.stars == '2.5' }">
							<span class="star"><img src="static/images/halfStar.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span></p>
						</c:if>
						<c:if test="${list.stars == '3.0' }">
							<span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span></p>
						</c:if>
						<c:if test="${list.stars == '3.5' }">
							<span class="star"><img src="static/images/halfStar.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span></p>
						</c:if>
						<c:if test="${list.stars == '4.0' }">
							<span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span></p>
						</c:if>
						<c:if test="${list.stars == '4.5' }">
							<span class="star"><img src="static/images/halfStar.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span></p>
						</c:if>
						<c:if test="${list.stars == '5.0' }">
							<span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span><span class="star"><img src="static/images/star.png" alt=""></span></p>
						</c:if>
		    		</div>	    		
		    	</section>
		    </a>
	    </c:forEach>
	    	
	    	
	    </div>
	    </div>
	    <div class="btnGroup">
	    	<form action="" class="form1">
	    		<button type="button" class="btn-look">发起备案</button>
	    		<input type="checkbox" name="" id="allCheck" value=""/>	
	    		<label for="allCheck">全部选择</label>
	    	</form>	 
	    	<form action="" class="form2" style="display: none;">
	    		<button type="button" class="btn-remove" id="confirmBtn" onclick="getDelete()">删除</button>
	    		<input type="checkbox" name="" id="allCheck2" value=""/>	
	    		<label for="allCheck2">全部选择</label>
	    	</form>
	    </div>
	    <div class="referring">
	    	<form id="main" action="addGuideRecords" onsubmit="return getChecked()" method="post">
	    		<input type="text" placeholder="客户姓名" id="customerName" name="customerName"/>
	    		<input type="text" placeholder="联系电话" pattern="^1[345678][0-9]{9}$" required id="phone" name="phone" title="请输入正确的手机格式"/>
	    		<input type="hidden" value="" id="allProjectId" name="allProjectId"/>
	    		<button id="btn-conf" type="submit" >提交备案</button>
	    	</form>
	    		<button class="btn-close">取消</button>
	    </div>

	<script type="text/javascript">
	$(document).ready(function(){
  		
  	});
	</script>
</body>
</html>