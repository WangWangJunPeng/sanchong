<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit"> 
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"> 
  <meta name="apple-mobile-web-app-status-bar-style" content="black"> 
  <meta name="apple-mobile-web-app-capable" content="yes">  
  <meta name="format-detection" content="telephone=no"> 
	<link  rel="icon" href="static/images/titleLogo.png"  />
	<title>门店管理后台</title>
	 <!-- <link rel="stylesheet" type="text/css" href="static/css/reset.css">
	<link rel="stylesheet" type="text/css" href="static/css/commend.css">
	<link rel="stylesheet" type="text/css" href="static/css/index.css"> --> 
	<link rel="stylesheet" href="static/layui/plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" type="text/css" href="static/layui/common/global.css">
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="static/layui/css/main2.css" media="all">
	<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="static/js/index-shop.js"></script>
</head>
<body>
<section class="larry-wrapper">
    <!-- overview -->
	<div class="row state-overview switch default-border">
		<div class="col-lg-3 col-sm-6 switch1"  id="todayBuy">
			<section class="panel">
				<div class="symbol userblue"> <i class="layui-icon">&#xe600;</i>
				</div>
				<div class="value">
					<a href="#">
						<h1 id="count1"><span id="enterBuy"></span></h1>
					</a>
					<p>今日认购</p>
				</div>
			</section>
		</div>
		<div class="col-lg-3 col-sm-6 switch1" id="todayRecord">
			<section class="panel">
				<div class="symbol commred"> <i class="layui-icon">&#xe60a;</i>
				</div>
				<div class="value">
					<a href="#" >
						<h1 id="count2"><span id="record"></span></h1>
					</a>
					<p>今日备案</p>
				</div>
			</section>
		</div>
		<div class="col-lg-3 col-sm-6 switch1" id="todaySee">
			<section class="panel">
				<div class="symbol articlegreen">
					<i class="layui-icon">&#xe613;</i>
				</div>
				<div class="value">
					<a href="#" >
						<h1 id="count3"><span id="guide"></span></h1>
					</a>
					<p>今日带看</p>
				</div>
			</section>
		</div>
		<div class="col-lg-3 col-sm-6 switch1"  id="todayOut">
			<section class="panel">
				<div class="symbol rsswet">
					<i class="layui-icon">&#xe628;</i>
				</div>
				<div class="value">
					<a href="#" >
						<h1 id="count4"><span id="willExpired"></span></h1>
					</a>
					<p>即将过期</p>
				</div>
			</section>
		</div>
	</div>
	<!-- overview end -->
	</section>
	<!-- /section end -->
	<div class="row" style="padding: 10px 15px;">
		<div class="col-lg-6">
			<section class="panel">
				<header class="panel-heading bm0">
					<span class='span-title' id="spanTitle">今日认购</span>
					<span class="tools pull-right"><a href="javascript:;" class="iconpx-chevron-down"></a></span>
				</header>
				<div class="panel-body" >
					<table class="table table-hover personal-task tab-pane" id="t_enterBuy">
                         
					</table>
					<table class="table table-hover personal-task tab-pane" style="display:none;" id="t_record">
                         
					</table>
					<table class="table table-hover personal-task tab-pane" style="display:none;" id="t_guid">
                         
					</table>
					<table class="table table-hover personal-task tab-pane" style="display:none;" id="t_willexpire">
                         
					</table>
				</div>
			</section>
		</div>
	</div>
		<!--  row end -->
	<%-- <%@include file="../publicpage/shoppublicpage.jsp" %> --%>
	<!-- <div class="contain" style="width:960px;margin:0 auto;background:#eaeaea;">
	
	<p style="font-size:12px;color:#0c95db;padding-top:10px;margin-left:10px;">首页</p>
	<div style="width:99%;background:#fff;margin:10px auto;padding-bottom:10px;">
		<nav class="message" >
			<ul>
				<li class="switch default-border"><span id="enterBuy"></span><p>今日认购</p></li>
				<li class="switch"><span id="record"></span><p>今日备案</p></li>
				<li class="switch"><span id="guide"></span><p>今日带看</p></li>
				<li class="switch"><span id="willExpired"></span><p>即将过期</p></li>
			</ul>
		</nav>
		<div class="tables">
			<table class="tab-pane" id="t_enterBuy">
			</table>

			<table class="tab-pane" style="display:none;" id="t_record">
			</table>

			<table class="tab-pane" style="display:none;" id="t_guid">
			</table>

			<table class="tab-pane" style="display:none;" id="t_willexpire">
			</table>
		</div>
 </div>
 <div style="height:10px;"></div>
	</div> -->
	<script type="text/javascript">
	
	$(function(){
		
		$('#todayBuy').click(function(){
			$('#spanTitle').html("今日认购");
		});
		$('#todayRecord').click(function(){
			
			$('#spanTitle').html("今日备案");
		});
		$('#todaySee').click(function(){
			
			$('#spanTitle').html("今日带看");
		});
		$('#todayOut').click(function(){
			
			$('#spanTitle').html("即将超期");
		});
		
		
		
	});
	</script>
</body>
</html>