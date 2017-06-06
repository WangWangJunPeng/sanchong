<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-案场信息维护</title>
        <link rel="stylesheet" type="text/css" href="static/css/messageProtect.css" />
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/lib/laydate/laydate.js"></script>
        <script type="text/javascript" src="static/js/jquery.validate.js"></script>
    </head>
    <body>
	<div class="side-bar">
		<ul id="allLi">
			<li class="sideLi"><a href="to_pro_baseInfo" >基本信息</a></li>
			<li class="sideLi"><a href="to_effectPic">效果图</a></li>
			<li class="sideLi"><a href="to_houseType">户型</a></li>
			<li class="sideLi"><a href="to_bankCount">银行账号</a></li>
			<li class="sideLi"><a href="to_serviceDef">带看业务定义</a></li>
			<li class="sideLi"><a href="to_idManage">预售证管理</a></li>
			<li class="sideLi"><a href="to_buyRule">认购规则</a></li>
			<li class="sideLi"><a href="to_customer_info_tag_page">标签管理</a></li>
		</ul>
	</div>
</body>
<script>
     $(document).ready(function(){
    	changeColor();
     })
     function changeColor(){
    	var url = window.location.href;
     	var index =  url.lastIndexOf("/");
	   	var lastUrl = url.substring(index+1);
    	
    	$("#allLi a").each(function(){
    		//console.log($(this).attr("href"));
    		if($(this).attr("href") == lastUrl){
    			$(this).css("color","#0c95db");
    		}
    	})
     }
     </script>
</html>