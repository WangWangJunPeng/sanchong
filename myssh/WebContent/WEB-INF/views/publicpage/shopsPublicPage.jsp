<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="utf-8">
		<title>门店管理后台</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		
		<link rel="stylesheet" href="static/css/bootstrap/css/bootstrap.css"/>
		<link rel="stylesheet" href="static/layui/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="static/layui/css/global.css" media="all">
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>

	<body>
		<div class="layui-layout layui-layout-admin" style="border-bottom: solid 5px #1aa094;">
			<div class="layui-header header header-demo">
				<div class="layui-main">
					<div class="admin-login-box">
						<a class="logo" style="left: 0;" href="###" >
							<span style="font-size: 22px;">中介门店管理后台</span>
						</a>
						<div class="admin-side-toggle">
							<i class="fa fa-bars" aria-hidden="true"></i>
						</div>
						<div class="admin-side-full">
							<i class="fa fa-life-bouy" aria-hidden="true"></i>
						</div>
					</div>
					<ul class="layui-nav admin-header-item">
						
						<li class="layui-nav-item">
							<a href="javascript:;" class="admin-header-user">
								<img src="static/layui/images/0.jpg" />
								<span>${sessionScope.userInfo.userCaption}</span>
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="#" id="changePSW"><i class="fa fa-gear" aria-hidden="true"></i>修改密码</a>
								</dd>
								<!-- <dd id="lock">
									<a href="javascript:;">
										<i class="fa fa-lock" aria-hidden="true" style="padding-right: 3px;padding-left: 1px;"></i> 锁屏 (Alt+L)
									</a>
								</dd>  -->
								<dd>
									<a href="web_logout"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
								</dd>
							</dl>
						</li>
					</ul>
					
				</div>
			</div>
			<div class="layui-side layui-bg-black" id="admin-side">
				<div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
			</div>
			<div class="layui-body" style="bottom: 0;border-left: solid 2px #1AA094;" id="admin-body" >
				<div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab" >
					<ul class="layui-tab-title">
						<li class="layui-this">
							<cite>首页</cite>
						</li>
					</ul>
					<div class="layui-tab-content" style="min-height: 150px;  padding: 5px 0 0 0;">
						<div class="layui-tab-item layui-show"  >
							<iframe src="to_store_shop_index" style="height:1000px;"></iframe>
						</div>
					</div>
				</div>
			</div>
			
			<div class="site-tree-mobile layui-hide">
				<i class="layui-icon">&#xe602;</i>
			</div>
			<div class="site-mobile-shade"></div>
			
			<!--锁屏模板 start-->
			<script type="text/template" id="lock-temp">
				<div class="admin-header-lock" id="lock-box">
					<div class="admin-header-lock-img">
						<img src="images/0.jpg"/>
					</div>
					<div class="admin-header-lock-name" id="lockUserName">beginner</div>
					<input type="text" class="admin-header-lock-input" value="输入密码解锁.." name="lockPwd" id="lockPwd" />
					<button class="layui-btn layui-btn-small" id="unlock">解锁</button>
				</div>
			</script>
			<!--锁屏模板 end -->
			
			<script type="text/javascript" src="static/layui/plugins/layui/layui.js"></script>
			<script type="text/javascript" src="static/layui/js/navbar.js"></script>
			<script type="text/javascript" src="static/layui/datas/nav-shop.js"></script>
			<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
			<script src="static/layui/js/index.js"></script>
			<script type="text/javascript">
        	
        
				layui.use('layer', function() {
					var $ = layui.jquery,
						layer = layui.layer;

					$('#video1').on('click', function() {
						layer.open({
							title: 'YouTube',
							maxmin: true,
							type: 2,
							content: 'video.html',
							area: ['800px', '500px']
						});
					});
					$('#changePSW').on("click",function(){
						layer.open({
							  type: 1,
							  //加上边框
							  title:"修改密码",
							  scrollbar: false,
							  area: ['450px', '350px'], //宽高
							  content: '<form  id="psd"><div class="layui-inline" style="margin-left:50px;margin-top:30px;"><label class="layui-form-label">旧密码*</label><div class="layui-input-inline"><input type="password" name="oldPsw" id="oldPsw" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input"></div></div><div class="layui-inline"  style="margin-left:50px;"><label class="layui-form-label">新密码*</label><div class="layui-input-inline"><input type="password" name="newPsw" id="newPPP" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input"></div></div><div class="layui-inline" style="margin-left:50px;"><label class="layui-form-label" style="padding-right:0px;margin-left:-14px;">确认密码*</label><div class="layui-input-inline" style="margin-left:15px;"><input type="password" name="surePsd" id="surePsd" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input"></div></div><div class="layui-inline"><span id="oldPswMsg" style="font-size:12px;color:#ff6161;margin-left:40px;"></span></div><div class="layui-form-item" style="margin-left:180px;margin-top:40px;"><button id="btnSbm" type="button" onclick="updatePsd()" class="layui-btn layui-btn-primary">提交</button></div></form>'
							});
					});
					
					/*  $('#btnSbm').on("click",function(){
						 alert("coming");
						updatePsd();
					});  */

				});
				
				/* $(document).ready(function (){
	        		$("#btnSbm").click(function (){
	        			updatePsd();
	        		});
	        	}); */ 
	        	
	        	function updatePsd(){
	        		var old = $('#oldPsw').val();
	        		var new1 = $('#newPPP').val();
	        		var new2 = $('#surePsd').val();
	        		
	        	if(old != "" && new1 != "" && new2 != ""){
	        		
					if (new1 == new2) {
	
							if (old != new2) {
	
								$.ajax({
									type : "post",
									url : "update_passworld",
									data : $("#psd").serialize(),
									success : function(data) {
										console.log(data);
										if (data.returnCode == "200") {
											window.location = data.url;
										} else if (data.returnCode == "201") {
											$("#oldPswMsg").text(data.msg);
										}
									}
								})
							}else{
								$("#oldPswMsg").text("新密码与原始密码不能相同");
							}
						}else{
							$("#oldPswMsg").text("两次输入的新密码不匹配，请重试");
						}
	
						
					}else{
						$("#oldPswMsg").text("不能为空");
					}
	        	}
			</script>
		</div>
	</body>

</html>