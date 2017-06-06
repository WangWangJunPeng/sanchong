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
    <link href="static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" href="static/css/spareResult.css" />
    <link rel="stylesheet" href="static/css/My tidings.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="static/js/flexible.js" type="text/javascript"></script>
    <script src="static/js/mui.min.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		getMydynamicInfo();
    	});
    	function getMydynamicInfo(){
    		$.ajax({
	    		  type:"post",
	    		  async : false,
	    		  url:"to_getMydynamic",
	    	   	 success:function(data){
	    	   		 data = eval("("+data+")");
	    	   		 getToFindMydynamicInfo(data.root);
	    	   	 }
	    	});
    	}
    	function  getToFindMydynamicInfo(data){
    		var s = '';
    		
    		$.each(data,function(v,o){
    			s += ' <section><div class="mid"><div class="boxLeft"><h3>'+o.projectName+'</h3>';
				s += '<span>'+o.dynamicInfo+'</span></div><div class="boxRight">';
				var aa = o.creatTime;
				var bb = aa.split(" ")[0];
				
				var currentTime = new Date();
				var currentY = currentTime.getFullYear();
				var currentM = currentTime.getMonth()+1;
				var currentD = currentTime.getDate();
				if(currentM  >= 1 && currentM <= 9){
					currentM = "0" + currentM;
				}
				if(currentD  >= 1 && currentD <= 9){
					currentD = "0" + currentD;
				}
				var zong = currentY + "-" + currentM + "-" + currentD;
				if(bb == zong){
					var cc = aa.split(" ")[1];
					
					var dd = cc.substring(0,5);
					s += '<p>'+dd+'</p>';
				}else{
					var ee = aa.substring(5,10);
					
					s += '<p>'+ee+'</p>';
				}
				//s += '<p>'+o.creatTime+'</p>';
				if (o.isRead == 0){
					s += '<span>未读</span>';
				}else if (o.isRead == 1){
					s += '<span>已读</span>';
				}
				s += '</div></div></section>';
    		});
    		 if(data.length>0){
      			$('#selectMydynamicInfo').html(s);
  			 }else {
  				 $("#selectMydynamicInfo").html("<tr><td style='width:10%;height:30px;margin:0 auto;'>暂无数据</td></tr>");
     		}
    	}
    </script>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
    </script>
     <title>我的动态</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">我的动态</h1>
	</header> 
	<div class="mui-content" id="selectMydynamicInfo">
	</div>
</body>
</html>

