<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<link  rel="icon" href="static/images/titleLogo.png"  />
	<title>官网Web_平台登入页面</title>
	<link rel="stylesheet" type="text/css" href="static/css/reset.css">
	<link rel="stylesheet" type="text/css" href="static/css/login.css">
</head>
<body>
	<header id="" class="header">
		<nav>
			<ul>
				<li class="logo"><a href="" title="">
					<img src="static/images/logo.png" alt="logo">
				</a></li>
				<li><span>平台管理后台</span></li>
			</ul>
			<button type="submit"  class="btn-apply">注册平台申请</button>
		</nav>
	</header><!-- /header -->

	<div class="contain">
		<div class="bg">
			<img src="static/images/bg.jpg" alt="background">
		</div>
		<div class="middle">
			<div class="context">
				<div class="midtext">
					<section>
					<h1>平台登入文案V1.17.0420.2</h1>
					<p>平台登入文案平台登入文案平台登入文案平台登入文案平台登入文案平台登入文案平台登入文案平台登入文案平台登入文案</p>
					</section>
				</div>
				<div class="loginbox">
						<form action="system.login" method="post">
							<input type="hidden" name="x_token" value="" />
							<div class="top">平台账号登陆</div>
							<ul>
								<li><input type="text" name="phone" class="number" id="" value="" placeholder="请输入平台帐号"/></li>
								<li><input type="password" name="password" class="password" id="" value="" placeholder="请输入登录密码" /></li>
								<li><input type="submit" class="logoinbnt" value="登录管理后台"/></li>
							</ul>
							<font color="red">${data}</font>
							<a href="###"><span>忘记密码？</span></a>
							<input type="hidden" name="sign" value="web"/>
							<input type="hidden" name="r_sign" value="shopowner"/>
						</form>
				</div>
			</div>
		</div>

	</div>

	<footer>
		<p><span>版权说明</span><span>ICP证书</span><span>联系方式</span></p>
		<p>
			<span><img src="static/images/erweima1.png"/></span><span>微信公众号<br />交易大师</span>
			<span><img src="static/images/erweima2.png"/></span><span>App下载<br />iPhone/Android</span>
		</p>
	</footer>

</body>
	
</html>

