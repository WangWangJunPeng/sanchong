<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

	<head>
		<meta charset="utf-8">
		<title>设置</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/MyInstall.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/feedback.css" />
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script>
		function setupWebViewJavascriptBridge(callback) {
	        if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
	        if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
	        window.WVJBCallbacks = [callback];
	        var WVJBIframe = document.createElement('iframe');
	        WVJBIframe.style.display = 'none';
	        WVJBIframe.src = 'https://__bridge_loaded__';
	        document.documentElement.appendChild(WVJBIframe);
	        setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0)
	    }

	    setupWebViewJavascriptBridge(function(bridge) {
			bridge.registerHandler('quitAccount', function(data, responseCallback) {
				responseCallback(responseData)
			})	
		})
	</script>		
	<script type="text/javascript">	
		function getconfirm(){
			$.ajax({
				type:"post",
				url:"to_validationOldPassword",
				data :{password : $('#oldPassword').val()},
				success :function(data){
					data = eval("("+data+")");
					getToChangeOrStay(data.data);
				}
			});
		}
		function getToChangeOrStay(data){
			if (data.num == 1 ){
				window.location.href="to_goToChangePassword";
			}else {
				$('.boxbox').addClass('mui-page');
				$('.boxbox2').removeClass('mui-page');
			}
		}
			
		</script>
	</head>

	<body class="mui-fullscreen">
		
		<!--页面主结构开始-->
		<div id="app" class="mui-views">
			<div class="mui-view">
				<div class="mui-navbar">
					
				</div>
				<div class="mui-pages">
					
				</div>
			</div>
			<div class="mask"></div>
			<div class="boxbox mui-page" >
					<img class="btn-close" src="<%=request.getContextPath()%>/static/images/close-btn.png" alt="" />
					<form class="mainbox" action="">
						<h3>验证原密码</h3>
						<p>为保障数据安全，请填原密码</p>						
						<input type="password" name="password" id="oldPassword"/>
						<button type="button" onclick="getconfirm()">确定</button>					
					</form>
			</div>
			<div class="boxbox2 mui-page" >
					<img class="btn-close2" src="<%=request.getContextPath()%>/static/images/close-btn.png" alt="" />
					<form class="mainbox" action="">
						<p>您输入的密码有误，请重新输入</p>
						<button type="button" class="btn-true">确定</button>					
					</form>
			</div>
		</div>
		<!--页面主结构结束-->
		<!--单页面开始-->
		<div id="setting" class="mui-page">
			<!--页面标题栏开始-->
			<div class="mui-navbar-inner mui-bar mui-bar-nav">
				<button type="button" class="mui-left mui-action-back mui-btn  mui-btn-link mui-btn-nav mui-pull-left btn-title">
					<span class="mui-icon mui-icon-left-nav" style="font-size:1.4rem;margin-top:.6rem;"></span>
				</button>
				<h1 class="mui-center mui-title">设置</h1>
			</div>
			<!--页面标题栏结束-->
			<!--页面主内容区开始-->
			<div class="mui-page-content">
				<div class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell mui-media">
								<a class="mui-navigate-right" href="###">
									<img class="mui-media-object mui-pull-left head-img" id="head-img" src="">
									<div class="mui-media-body">
										Hello 九邑Sales
										<p class='mui-ellipsis'>账号:${sessionScope.userInfo.userCaption}</p>
									</div>
								</a>
							</li>
						</ul>
						<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell"  id="exchangePass">
								<a href="#" class="mui-navigate-right" id="change">修改密码</a>
							</li>
						</ul>
						<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell">
								<a href="###" class="mui-navigate-right">关于交易大师 <i class="mui-pull-right update"></i></a>
							</li>
						</ul>
						<ul class="mui-table-view" class="logOff">
							<li class="mui-table-view-cell" id="" style="text-align: center;">
								<a id="logOut" href="javascript:;" onclick="logout()" >退出登录</a>
							</li>
							<div id="log"></div>
						</ul>
					</div>
				</div>
			</div>
			<!--页面主内容区结束-->
		</div>
		<!--单页面结束-->
	
		<div id="account" class="mui-page">
			<div class="mui-navbar-inner mui-bar mui-bar-nav">
				<button type="button" class="mui-left mui-action-back mui-btn  mui-btn-link mui-btn-nav mui-pull-left">
					<span class="mui-icon mui-icon-left-nav"></span>
				</button>
				<h1 class="mui-center mui-title">账号与安全</h1>
			</div>
			<div class="mui-page-content">
				<div class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<ul class="mui-table-view">
							<li class="mui-table-view-cell">
								<a id="head" class="mui-navigate-right">头像
								<span class="mui-pull-right head">
									<img class="head-img mui-action-preview" id="head-img1" src=""/>
								</span>
							</a>
							</li>
							<li class="mui-table-view-cell">
								<a>姓名<span class="mui-pull-right">Hbuilder</span></a>
							</li>
							<li class="mui-table-view-cell">
								<a>HBuilder账号<span class="mui-pull-right">hbuilder@dcloud.io</span></a>
							</li>
						</ul>
						<ul class="mui-table-view">
							<li class="mui-table-view-cell">
								<a>QQ号<span class="mui-pull-right">88888888</span></a>
							</li>
							<li class="mui-table-view-cell">
								<a>手机号<span class="mui-pull-right">18601234567</span></a>
							</li>
							<li class="mui-table-view-cell">
								<a>邮箱地址<span class="mui-pull-right">hbuilder@dcloud.io</span></a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="notifications" class="mui-page">
			<div class="mui-navbar-inner mui-bar mui-bar-nav">
				<button type="button" class="mui-left mui-action-back mui-btn  mui-btn-link mui-btn-nav mui-pull-left">
					<span class="mui-icon mui-icon-left-nav"></span>设置
				</button>
				<h1 class="mui-center mui-title">新消息通知</h1>
			</div>
			<div class="mui-page-content">
				<div class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<ul class="mui-table-view">
							<li class="mui-table-view-cell">
								<a>接收新消息通知<span class="mui-pull-right">已开启</span></a>
							</li>
						</ul>
						<ul class="mui-table-view">
							<li class="mui-table-view-cell">
								通知显示消息详情
								<div class="mui-switch mui-active mui-switch-mini">
									<div class="mui-switch-handle"></div>
								</div>
							</li>
						</ul>
						<div class="mui-content-padded">
							<p>若关闭，当收到新消息时，通知提示将不显示发信人和内容摘要</p>
						</div>

						<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell">
								<a href="#notifications_disturb" class="mui-navigate-right">功能消息免打扰</a>
							</li>
						</ul>
						<div class="mui-content-padded">
							<p>设置系统功能消息提示声音和震动的时段</p>
						</div>
						<ul class="mui-table-view">
							<li class="mui-table-view-cell">
								<a>接收新消息通知<span class="mui-pull-right">已开启</span></a>
							</li>
						</ul>
						<ul class="mui-table-view">
							<li class="mui-table-view-cell">
								声音
								<div class="mui-switch mui-active mui-switch-mini">
									<div class="mui-switch-handle"></div>
								</div>
							</li>
							<li class="mui-table-view-cell">
								震动
								<div class="mui-switch mui-active mui-switch-mini">
									<div class="mui-switch-handle"></div>
								</div>
							</li>
						</ul>
						<div class="mui-content-padded">
							<p>当HelloMUI在运行时，你可以设置是否需要声音或者震动</p>
						</div>
						<div></div>
					</div>
				</div>
			</div>
		</div>
		<div id="notifications_disturb" class="mui-page">
			<div class="mui-navbar-inner mui-bar mui-bar-nav">
				<button type="button" class="mui-left mui-action-back mui-btn  mui-btn-link mui-btn-nav mui-pull-left">
					<span class="mui-icon mui-icon-left-nav"></span>新消息通知
				</button>
				<h1 class="mui-center mui-title">功能消息免打扰</h1>
			</div>
			<div class="mui-page-content">
				<div class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<ul class="mui-table-view mui-table-view-radio">
							<li class="mui-table-view-cell">
								<a class="mui-navigate-right">开启</a>
							</li>
							<li class="mui-table-view-cell">
								<a class="mui-navigate-right">只在夜间开启</a>
							</li>
							<li class="mui-table-view-cell">
								<a class="mui-navigate-right">关闭</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="privacy" class="mui-page">
			<div class="mui-navbar-inner mui-bar mui-bar-nav">
				<button type="button" class="mui-left mui-action-back mui-btn  mui-btn-link mui-btn-nav mui-pull-left">
					<span class="mui-icon mui-icon-left-nav"></span>设置
				</button>
				<h1 class="mui-center mui-title">隐私</h1>
			</div>
			<div class="mui-page-content">
				<div class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<ul class="mui-table-view">
							<li class="mui-table-view-divider">通讯录</li>
							<li class="mui-table-view-cell">
								加我为朋友时需要验证
								<div class="mui-switch mui-active mui-switch-mini">
									<div class="mui-switch-handle"></div>
								</div>
							</li>
						</ul>
						<ul class="mui-table-view">
							<li class="mui-table-view-cell">
								向我推荐QQ好友
								<div class="mui-switch mui-switch-mini">
									<div class="mui-switch-handle"></div>
								</div>
							</li>
							<li class="mui-table-view-cell">
								通过QQ号搜索到我
								<div class="mui-switch mui-switch-mini">
									<div class="mui-switch-handle"></div>
								</div>
							</li>
						</ul>
						<ul class="mui-table-view">
							<li class="mui-table-view-cell">
								可通过手机号搜索到我
								<div class="mui-switch mui-active mui-switch-mini">
									<div class="mui-switch-handle"></div>
								</div>
							</li>
							<li class="mui-table-view-cell">
								向我推荐通讯录朋友
								<div class="mui-switch mui-switch-mini">
									<div class="mui-switch-handle"></div>
								</div>
							</li>
							<li class="mui-table-view-divider">开启后，为你推荐已经开通HBuilder的手机联系人</li>
						</ul>
						<ul class="mui-table-view">
							<li class="mui-table-view-cell">
								通过HBuilder账号搜索到我
								<div class="mui-switch mui-active mui-switch-mini">
									<div class="mui-switch-handle"></div>
								</div>
							</li>
							<li class="mui-table-view-divider">关闭后，其他用户将不能通过HBuilder号搜索到你</li>
						</ul>

					</div>
				</div>
			</div>
		</div>
		<div id="general" class="mui-page">
			<div class="mui-navbar-inner mui-bar mui-bar-nav">
				<button type="button" class="mui-left mui-action-back mui-btn  mui-btn-link mui-btn-nav mui-pull-left">
					<span class="mui-icon mui-icon-left-nav"></span>设置
				</button>
				<h1 class="mui-center mui-title">通用</h1>
			</div>
			<div class="mui-page-content">
				<div class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<ul class="mui-table-view">
							<li class="mui-table-view-cell">
								多语言
							</li>
						</ul>

						<ul class="mui-table-view">
							<li class="mui-table-view-cell">
								听筒模式
								<div class="mui-switch mui-switch-mini">
									<div class="mui-switch-handle"></div>
								</div>
							</li>
						</ul>
						<ul class="mui-table-view">
							<li class="mui-table-view-cell">
								功能
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div id="about" class="mui-page">
			<div class="mui-navbar-inner mui-bar mui-bar-nav">
				<button type="button" class="mui-left mui-action-back mui-btn  mui-btn-link mui-btn-nav mui-pull-left">
					<span class="mui-icon mui-icon-left-nav"></span>设置
				</button>
				<h1 class="mui-center mui-title">关于案场大师</h1>
			</div>
			<div class="mui-page-content">
				<div class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<ul class="mui-table-view">
							<li class="mui-table-view-cell mui-plus-visible">
								<a id="rate" class="mui-navigate-right">评分鼓励</a>
							</li>
							<li class="mui-table-view-cell mui-plus-visible">
								<a id="welcome" class="mui-navigate-right">欢迎页</a>
							</li>
							<li class="mui-table-view-cell mui-plus-visible">
								<a id="share" class="mui-navigate-right">分享推荐</a>
							</li>
							<li class="mui-table-view-cell mui-plus-visible">
								<a id="tel" class="mui-navigate-right">客服电话</a>
							</li>
							<li class="mui-table-view-cell">
								<a id="aboutus" href="#feedback" class="mui-navigate-right">关于我们</a>
							</li>
							<li class="mui-table-view-cell">
								<a id="feedback-btn" href="#feedback" class="mui-navigate-right">问题反馈</a>
							</li>
							<!-- <li id="check_update" class="mui-table-view-cell">
								<a id="update" class="mui-navigate-right">检查更新</a>
							</li> -->
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div id="feedback" class="mui-page feedback">
			<div class="mui-navbar-inner mui-bar mui-bar-nav">
				<button type="button" class="mui-left mui-action-back mui-btn  mui-btn-link mui-btn-nav mui-pull-left">
					<span class="mui-icon mui-icon-left-nav"></span>关于MUI
				</button>
				<button id="submit" class="mui-btn mui-btn-blue mui-btn-link mui-pull-right" type="button" onclick="getSend()">发送</button>
				<h1 class="mui-center mui-title">问题反馈</h1>
			</div>
			<div class="mui-page-content">
				<div class="mui-content-padded">
					<div class="mui-inline">问题和意见</div>
					<a class="mui-pull-right mui-inline" href="#popover">
						快捷输入
						<span class="mui-icon mui-icon-arrowdown"></span>
					</a>
					<!--快捷输入具体内容，开发者可自己替换常用语-->
					<div id="popover" class="mui-popover">
						<div class="mui-popover-arrow"></div>
						<div class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<ul class="mui-table-view">
									<!--仅流应用环境下显示-->
									<li class="mui-table-view-cell stream">
										<a href="#">桌面快捷方式创建失败</a>
									</li>
									<li class="mui-table-view-cell"><a href="#">界面显示错乱</a></li>
									<li class="mui-table-view-cell"><a href="#">启动缓慢，卡出翔了</a></li>
									<li class="mui-table-view-cell"><a href="#">偶发性崩溃</a></li>
									<li class="mui-table-view-cell"><a href="#">UI无法直视，丑哭了</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="row mui-input-row">
					<textarea id='question' class="mui-input-clear question" placeholder="请详细描述你的问题和意见..." name="problem"></textarea>
				</div>
				<p>图片(选填,提供问题截图,总大小10M以下)</p>
				<div id='image-list' class="row image-list"></div>
				<p>QQ/邮箱</p>
				<div class="mui-input-row">
					<input id='contact' type="text" class="mui-input-clear contact" placeholder="(选填,方便我们联系你 )" name="email"/>
				</div>
				<div class="mui-content-padded">
					<div class="mui-inline">应用评分</div>
					<div class="icons mui-inline" style="margin-left: 6px;">
						<i data-index="1" class="mui-icon mui-icon-star"></i>
						<i data-index="2" class="mui-icon mui-icon-star"></i>
						<i data-index="3" class="mui-icon mui-icon-star"></i>
						<i data-index="4" class="mui-icon mui-icon-star"></i>
						<i data-index="5" class="mui-icon mui-icon-star"></i>
					</div>
				</div><br />
			</div>
		</div>

	</body>
	<script src="<%=request.getContextPath()%>/static/js/mui.min.js "></script>
	<script src="<%=request.getContextPath()%>/static/js/mui.view.js "></script>
	<script src='<%=request.getContextPath()%>/static/js/feedback.js'></script>

	<script>
		mui.init();
		//初始化单页view
		var viewApi = mui('#app').view({
			defaultPage: '#setting'
		});
		//初始化单页的区域滚动
		mui('.mui-scroll-wrapper').scroll();
		//退出登录返回首页
		function logout(){
			$.ajax({
				type:"post",
				url:"user_logout",
				success :function(data){
					getOut(data);
				}
			});
		}
		function getOut(data){
			var isIos = /iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream;
			if (isIos){
				 WebViewJavascriptBridge.callHandler('quitAccount',null,function(response) {
		                 alert(response);
		      	});
			}else{
				console.log(data);
				window.location.href=data.url;
			}
		}
		
		
		//分享操作
		var shares = {};
		
		mui.plusReady(function() {
			plus.share.getServices(function(s) {
				if (s && s.length > 0) {
					for (var i = 0; i < s.length; i++) {
						var t = s[i];
						shares[t.id] = t;
					}
				}
			}, function() {
				console.log("获取分享服务列表失败");
			});
		});
		
		setTimeout(function () {
			defaultImg();
			setTimeout(function() {
				initImgPreview();
			}, 300);
		},500);
		
		//分享链接点击事件
		document.getElementById("share").addEventListener('tap', function() {
			var ids = [{
					id: "weixin",
					ex: "WXSceneSession"
				}, {
					id: "weixin",
					ex: "WXSceneTimeline"
				}, {
					id: "sinaweibo"
				}, {
					id: "tencentweibo"
				}, {
					id: "qq"
				}],
				bts = [{
					title: "发送给微信好友"
				}, {
					title: "分享到微信朋友圈"
				}, {
					title: "分享到新浪微博"
				}, {
					title: "分享到腾讯微博"
				}, {
					title: "分享到QQ"
				}];
			plus.nativeUI.actionSheet({
				cancel: "取消",
				buttons: bts
			}, function(e) {
				var i = e.index;
				if (i > 0) {
					var s_id = ids[i - 1].id;
					var share = shares[s_id];
					if (share) {
						if (share.authenticated) {
							shareMessage(share, ids[i - 1].ex);
						} else {
							share.authorize(function() {
								shareMessage(share, ids[i - 1].ex);
							}, function(e) {
								console.log("认证授权失败：" + e.code + " - " + e.message);
							});
						}
					} else {
						mui.toast("无法获取分享服务，请检查manifest.json中分享插件参数配置，并重新打包")
					}
				}
			});
		});

		function shareMessage(share, ex) {
			var msg = {
				extra: {
					scene: ex
				}
			};
			msg.href = "http://www.dcloud.io/hellomui/";
			msg.title = "最接近原生APP体验的高性能前端框架";
			msg.content = "我正在体验HelloMUI，果然很流畅，基本看不出和原生App的差距";
			if (~share.id.indexOf('weibo')) {
				msg.content += "；体验地址：http://www.dcloud.io/hellomui/";
			}
			msg.thumbs = ["_www/images/logo.png"];
			share.send(msg, function() {
				console.log("分享到\"" + share.description + "\"成功！ ");
			}, function(e) {
				console.log("分享到\"" + share.description + "\"失败: " + e.code + " - " + e.message);
			});
		}
		//去评分
		document.getElementById("rate").addEventListener('tap', function() {
			if (mui.os.ios) {
				location.href = 'https://itunes.apple.com/WebObjects/MZStore.woa/wa/viewContentsUserReviews?id=682211190&pageNumber=0&sortOrdering=2&type=&mt=8';
			} else if (mui.os.android) {
				plus.runtime.openURL("market://details?id=io.dcloud.HelloMUI", function(e) {
					plus.runtime.openURL("market://details?id=io.dcloud.HelloMUI", function(e) {
						mui.alert("360手机助手和应用宝，你一个都没装，暂时无法评分，感谢支持");
					}, "com.qihoo.appstore");
				}, "com.tencent.android.qqdownloader");
			}
		});
		//客服电话
		document.getElementById("tel").addEventListener('tap', function() {
			if(mui.os.plus){
				plus.device.dial("114");
			}else{
				location.href = 'tel:114';
			}
			
		});
		//		//意见反馈
		//		document.getElementById("quest").addEventListener('tap', function() {
		//			
		//		});
		//		
		//检查更新
		/* document.getElementById("update").addEventListener('tap', function() {
			var server = "http://www.dcloud.io/check/update"; //获取升级描述文件服务器地址
			mui.getJSON(server, {
				"appid": plus.runtime.appid,
				"version": plus.runtime.version,
				"imei": plus.device.imei
			}, function(data) {
				if (data.status) {
					plus.ui.confirm(data.note, function(i) {
						if (0 == i) {
							plus.runtime.openURL(data.url);
						}
					}, data.title, ["立即更新", "取　　消"]);
				} else {
					mui.toast('Hello 案场大师 已是最新版本~')
				}
			});
		}); */
		var view = viewApi.view;
		(function($) {
			//处理view的后退与webview后退
			var oldBack = $.back;
			$.back = function() {
				if (viewApi.canBack()) { //如果view可以后退，则执行view的后退
					viewApi.back();
				} else { //执行webview后退
					oldBack();
				}
			};
			//监听页面切换事件方案1,通过view元素监听所有页面切换事件，目前提供pageBeforeShow|pageShow|pageBeforeBack|pageBack四种事件(before事件为动画开始前触发)
			//第一个参数为事件名称，第二个参数为事件回调，其中e.detail.page为当前页面的html对象
			view.addEventListener('pageBeforeShow', function(e) {
				//				console.log(e.detail.page.id + ' beforeShow');
			});
			view.addEventListener('pageShow', function(e) {
				//				console.log(e.detail.page.id + ' show');
			});
			view.addEventListener('pageBeforeBack', function(e) {
				//				console.log(e.detail.page.id + ' beforeBack');
			});
			view.addEventListener('pageBack', function(e) {
				//				console.log(e.detail.page.id + ' back');
			});
		})(mui);
		//更换头像
		mui(".mui-table-view-cell").on("tap", "#head", function(e) {
			if(mui.os.plus){
				var a = [{
					title: "拍照"
				}, {
					title: "从手机相册选择"
				}];
				plus.nativeUI.actionSheet({
					title: "修改头像",
					cancel: "取消",
					buttons: a
				}, function(b) {
					switch (b.index) {
						case 0:
							break;
						case 1:
							getImage();
							break;
						case 2:
							galleryImg();
							break;
						default:
							break
					}
				})	
			}
			
		});

		
		function getImage() {
			var c = plus.camera.getCamera();
			c.captureImage(function(e) {
				plus.io.resolveLocalFileSystemURL(e, function(entry) {
					var s = entry.toLocalURL() + "?version=" + new Date().getTime();
					console.log(s);
					document.getElementById("head-img").src = s;
					document.getElementById("head-img1").src = s;
					//变更大图预览的src
					//目前仅有一张图片，暂时如此处理，后续需要通过标准组件实现
					document.querySelector("#__mui-imageview__group .mui-slider-item img").src = s + "?version=" + new Date().getTime();;;
				}, function(e) {
					console.log("读取拍照文件错误：" + e.message);
				});
			}, function(s) {
				console.log("error" + s);
			}, {
				filename: "_doc/head.jpg"
			})
		}

		function galleryImg() {
			plus.gallery.pick(function(a) {
				plus.io.resolveLocalFileSystemURL(a, function(entry) {
					plus.io.resolveLocalFileSystemURL("_doc/", function(root) {
						root.getFile("head.jpg", {}, function(file) {
							//文件已存在
							file.remove(function() {
								console.log("file remove success");
								entry.copyTo(root, 'head.jpg', function(e) {
										var e = e.fullPath + "?version=" + new Date().getTime();
										document.getElementById("head-img").src = e;
										document.getElementById("head-img1").src = e;
										//变更大图预览的src
										//目前仅有一张图片，暂时如此处理，后续需要通过标准组件实现
										document.querySelector("#__mui-imageview__group .mui-slider-item img").src = e + "?version=" + new Date().getTime();;
									},
									function(e) {
										console.log('copy image fail:' + e.message);
									});
							}, function() {
								console.log("delete image fail:" + e.message);
							});
						}, function() {
							//文件不存在
							entry.copyTo(root, 'head.jpg', function(e) {
									var path = e.fullPath + "?version=" + new Date().getTime();
									document.getElementById("head-img").src = path;
									document.getElementById("head-img1").src = path;
									//变更大图预览的src
									//目前仅有一张图片，暂时如此处理，后续需要通过标准组件实现
									document.querySelector("#__mui-imageview__group .mui-slider-item img").src = path;
								},
								function(e) {
									console.log('copy image fail:' + e.message);
								});
						});
					}, function(e) {
						console.log("get _www folder fail");
					})
				}, function(e) {
					console.log("读取拍照文件错误：" + e.message);
				});
			}, function(a) {}, {
				filter: "image"
			})
		};

		function defaultImg() {
			if(mui.os.plus){
				plus.io.resolveLocalFileSystemURL("_doc/head.jpg", function(entry) {
					var s = entry.fullPath + "?version=" + new Date().getTime();;
					document.getElementById("head-img").src = s;
					document.getElementById("head-img1").src = s;
				}, function(e) {
					document.getElementById("head-img").src = 'static/images/logo.png';
					document.getElementById("head-img1").src = 'static/images/logo.png';
				})
			}else{
				document.getElementById("head-img").src = 'static/images/logo.png';
				document.getElementById("head-img1").src = 'static/images/logo.png';
			}
			
		}
		document.getElementById("head-img1").addEventListener('tap', function(e) {
			e.stopPropagation();
		});
		document.getElementById("welcome").addEventListener('tap', function(e) {
			//显示启动导航
			mui.openWindow({
				id: 'guide',
				url: 'guide.html',
				show: {
					aniShow: 'fade-in',
					duration: 300
				},
				waiting: {
					autoShow: false
				}
			});
		});

		function initImgPreview() {
			var imgs = document.querySelectorAll("img.mui-action-preview");
			imgs = mui.slice.call(imgs);
			if (imgs && imgs.length > 0) {
				var slider = document.createElement("div");
				slider.setAttribute("id", "__mui-imageview__");
				slider.classList.add("mui-slider");
				slider.classList.add("mui-fullscreen");
				slider.style.display = "none";
				slider.addEventListener("tap", function() {
					slider.style.display = "none";
				});
				slider.addEventListener("touchmove", function(event) {
					event.preventDefault();
				})
				var slider_group = document.createElement("div");
				slider_group.setAttribute("id", "__mui-imageview__group");
				slider_group.classList.add("mui-slider-group");
				imgs.forEach(function(value, index, array) {
					//给图片添加点击事件，触发预览显示；
					value.addEventListener('tap', function() {
						slider.style.display = "block";
						_slider.refresh();
						_slider.gotoItem(index, 0);
					})
					var item = document.createElement("div");
					item.classList.add("mui-slider-item");
					var a = document.createElement("a");
					var img = document.createElement("img");
					img.setAttribute("src", value.src);
					a.appendChild(img)
					item.appendChild(a);
					slider_group.appendChild(item);
				});
				slider.appendChild(slider_group);
				document.body.appendChild(slider);
				var _slider = mui(slider).slider();
			}
		}
		
		if(mui.os.stream){
			document.getElementById("check_update").display = "none";
		}

		
	</script>
	<script type="text/javascript">
		$('#change').click(function(){
			$('.mask').addClass('maskDiv');
			$('.boxbox').removeClass('mui-page');
		});
		$('.btn-close').click(function(){
			$('.boxbox').addClass('mui-page');
			$('.mask').removeClass('maskDiv');
		});
		$(".btn-true").click(function(){
			$(".boxbox2").addClass('mui-page');
			$('.mask').removeClass('maskDiv');
		});
		$(".btn-close2").click(function(){
			$(".boxbox2").addClass('mui-page');
			$('.mask').removeClass('maskDiv');
		});
	</script>
</html>

