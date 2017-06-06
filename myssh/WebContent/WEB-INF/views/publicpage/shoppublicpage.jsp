<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>门店管理后台</title>
<link rel="stylesheet" type="text/css" href="static/css/reset.css">
<link rel="stylesheet" type="text/css" href="static/css/commend.css">
</head>
<style>
	.nave:after{content:"."; height:0; visibility:hidden; display:block; clear:both;}

</style>
<body>
	<header id="" class="index-header" >
		<nav class="nave" style="position:relative;">
			<ul>
				<li><a href="###" title="" class="index-logo">中介门店管理后台</a></li>
				<li><a class="active" href="to_store_index" title="">首页</a></li>
				<li><a href="to_allProjects" title="">房源搜索</a></li>
				<li><a href="to_medi_manager_page" title="">经纪人管理</a></li>
				<li><a href="shop_customer_manager_page_info" title="">客户管理</a></li>
				<li><a href="to_shoper_bill_page" title="">对账单</a></li>
				<li><a href="to_shoper_business_page" title="">业务</a></li>
				<li><a href="#" title="" class="welcome">欢迎您：<span>${sessionScope.userInfo.userCaption}</span></a></li>
			</ul>
			 <ol style="position:absolute;right:-5px;top:40px;z-index:100;width:80px;border:1px solid #999;height:50px;background:#fff;display:none;" class="olDiv">
	           <li style="position:relative;display:block;height:25px;width:50px;line-height: 25px;margin:auto;left:15px;"><a href="to_shop_edit_passworld" style="display:inline-block;color:#999;text-align:center;width:50px;font-size:12px;">修改密码</a></li>
	           <li style="position:relative;display:block;height:25px;width:50px;line-height: 25px;margin:auto;right:15px;"><a href="web_logout" style="display:inline-block;color:#999;text-align:center;font-size:12px;width:50px;">退出登录</a></li>
	      	</ol>
		</nav>
		
	</header>
	 <script>
     $(document).ready(function(){
    	
    	$(".welcome").click(function(){
    		$(".olDiv").slideToggle("slow");
    	});
     })
     </script>
</body>
</html>