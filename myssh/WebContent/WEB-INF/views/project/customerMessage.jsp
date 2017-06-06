<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <link href="<%=request.getContextPath()%>/static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/customerMessage.css" />
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<title>顾客信息</title>
	<script type="text/javascript">
	$(function(){
		status();
		var data = $("#oneDiv").attr("value");
		qingqiu(data,0);
		$("#oneDiv").click();
		$("#aditCustomerBtn").click(function(){
			var customerId = $("#customerId").val();
			var projectId = $("#projectId").val();
			var own = $("#own").val();
			if(own==1){
				mui.openWindow({
				    url: "toCustomerEdit?customerId="+customerId+"&projectId="+projectId, 
				  });
			}else{
				//不是自己的客户
				alert("该客户不属于您");
			}
		});
	})
	function status(){
		var status = $("#status").val();
		console.log(status);
		/* 签约 */
		if(status=="5"){
			$(".listfour").css("background","#fd6767");
			$(".listes4").css("color","#fd6767");
		}
		/* 付款 */
		if(status=="4"){
			$(".listthree").css("background","#fd6767");
			$(".listes3").css("color","#fd6767");
		}
		if(status=="1"){
			$(".listtwo").css("background","#fd6767");
			$(".listes2").css("color","#fd6767");
		}
		/* 付款 */
		if(status=="0"){
			$(".listone").css("background","#fd6767");
			$(".listes1").css("color","#fd6767");
		}
	}
	function showOneDiv(){
		var data = $("#oneDiv").attr("value");
		qingqiu(data,0);
	}
	
	function showTwoDiv(){
		var data = $("#twoDiv").attr("value");
		qingqiu(data,1);
	}
	function qingqiu(data,num){
		$(".contain").empty();
		var customerId = $("#customerId").val();
		var projectId = $("#projectId").val();
		//alert(data+","+customerId+","+projectId)
		$.ajax({
  			 url: 'findCustomersTags',
               type: 'post',
               dataType: 'json',
               data: {
            	   "customerId":customerId,
            	   "tagTypeId":data,
            	   "projectId":projectId
               },
               success: function (data) {
            	   //alert(data.length)
                    showTagType(data,num);
                    
               },
               error:function(){
            	   
               }
  			});
	}
	
	function showTagType(data,num){
		$.each(data,function(v, o) {
			if(o.tagLibs.length>0){
				showTagType(o.tagLibs);
			}else{
				//不存在标签类目
				var tagTypeName = o.tagTypeName;
				var sortNum = o.sortNum;
				var tagTypeId = o.tagTypeId;
				var tagTypeDiv = '<div class="list"><div class="leftbox"><span></span><div class="title">'+tagTypeName+'</div></div>'+
					'<div class="rightbox" >'+
						'<ul class="messList" >';
						
						//'</ul>'+
					//'</div>'+
				//'</div>';
				
				var tags = o.tags;
				var lilist = '';
				if(tags.length>0){
					lilist = showTags(tags);
					tagTypeDiv += lilist;
				}else{
					//没有选中的标签
					tagTypeDiv+='<li>暂无</li>';
				}
				tagTypeDiv += '</ul>'+
				'</div>'+
				'</div>';
				//alert(tagTypeDiv);
				if(num==0){
					$(tagTypeDiv).appendTo($("#item1mobile .contain"));
				}
				if(num==1){
					$(tagTypeDiv).appendTo($("#item2mobile .contain"));
				}
			}
			
		})
		
	}
	function showTags(data){
		var text = '';
		$.each(data,function(v,o){
			var tag = o.children;
			if(tag.length>0){
				//alert("asd");
				text += showTags(tag);
			}else{
				//展示选中的标签
				text += '<li>'+o.tagName+'</li>';
				//alert(text);
			}

		})
		return text;
	}
	//处理经理点评
	function managerEvl(){
		$(".contain").empty();
		var customerId = $("#customerId").val();
		var projectId = $("#projectId").val();
		//alert(data+","+customerId+","+projectId)
		$.ajax({
  			 url: 'findCustomerEvaluate',
               type: 'post',
               dataType: 'json',
               data: {
            	   "customerId":customerId,
            	   "projectId":projectId
               },
               success: function (data) {
            	   if(data.evaluate!=null){
	            	   var textarea = '<textarea name="" rows="" cols="" placeholder="经理点评" readonly="readonly">'+data.evaluate+'</textarea>';
            	   }else{
            		   var textarea = '<textarea name="" rows="" cols="" placeholder="经理点评" readonly="readonly"></textarea>';
            	   }
            	   
				   $(textarea).appendTo($("#item3mobile .contain"));
               },
               error:function(){
            	   
               }
  			});
	}
	</script>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">${customer.customerName}</h1>
	</header>
	<div class="mui-content">
	<input id="customerId" type="hidden" value="${customer.customerId}">
	<input id="projectId" type="hidden" value="${customer.projectId}">
	<input id="status" type="hidden" value="${customer.status}">
	<input id="own" type="hidden" value="${customer.own}">
		<!--title-->
		<div class="titlebar">
			<div class="container">
				<div class="lefttitle">
					<p>到访时间:<span class="leftwidth">${customer.firstTime}</span></p>
				</div>
				<div class="righttltle">
					<button id="aditCustomerBtn" type="button">
						<img src="static/images/bianji.png" alt="" />
					编辑
					</button>
				</div>
			</div>
		</div>
		<!--zhong-->
		<div class="people">
			<div class="container">
			<div class="left">
				<img src="static/images/people1.png" alt="" />
			</div>
			<div class="right">
				<p><img src="static/images/7.png"/><span>${customer.customerName}</span></p>
				<p><img src="static/images/8.png"/><span>${customer.customerPhone}</span></p>
				<p class="top1"><span class="yuanyuan">已到访${customer.comeNum}次</span>
			
						<span class="yuanyuan">${customer.yixiang}</span>
				</p>
			</div>
			</div>
		</div>
		<!--状态指示-->
		<div class="state">
			<ul class="statelist">
				<li class="list1"><p class="pt"><span class="dian listone"></span></p><p class="listes1">已到访</p></li>
				<li class="list1"><p class="pt"><span class="dian listtwo"></span></p><p class="listes2">已认购</p></li>
				<li class="list1"><p class="pt"><span class="dian listthree"></span></p><p class="listes3">已付款</p></li>
				<li class="list1"><p class="pt"><span class="dian  listfour"></span></p><p class="listes4">已签约</p></li>
			</ul>
		</div>
		<!--信息展示-->
		<div id="slider" class="mui-slider">
			<div id="sliderSegmentedControl" class="mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
				<a id="oneDiv" class="mui-control-item mui-active" href="#item1mobile" value="${customer.rootTagTypes[0].tagTypeId}">${customer.rootTagTypes[0].tagTypeName}</a>
				<a id="twoDiv" class="mui-control-item" href="#item2mobile" value="${customer.rootTagTypes[1].tagTypeId}" >${customer.rootTagTypes[1].tagTypeName}</a>
				<a id="threeDiv" class="mui-control-item" href="#item3mobile">经理点评</a>
			</div>
			<div id="sliderProgressBar" class="mui-slider-progress-bar mui-col-xs-4"></div>
			<div class="mui-slider-group">
				<div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
					<div id="scroll1" class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<div class="contain">
							<!--  
								<div class="list">
									<div class="leftbox"><span></span><div class="title">性格123</div></div>
									<div class="rightbox">
										<ul class="messList">
										<li>路过</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										</ul>
									</div>
								</div>
								
						
								<div class="list">
									<div class="leftbox"><span></span><div class="title">性格</div></div>
									<div class="rightbox">
										<ul class="messList">
										<li>路过</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										</ul>
									</div>
								</div>
								<div class="list">
									<div class="leftbox"><span></span><div class="title">性格</div></div>
									<div class="rightbox">
										<ul class="messList">
										<li>路过</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										</ul>
									</div>
								</div>
								<div class="list">
									<div class="leftbox"><span></span><div class="title">性格</div></div>
									<div class="rightbox">
										<ul class="messList">
										<li>路过</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										</ul>
									</div>
								</div>
								-->
							</div>
						</div>
					</div>
				</div>
				<div id="item2mobile" class="mui-slider-item mui-control-content">
					<div id="scroll2" class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<div class="contain">
								<div class="list">
									<div class="leftbox"><span></span><div class="title">性格</div></div>
									<div class="rightbox">
										<ul class="messList">
										<li>路过</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										</ul>
									</div>
								</div>
								<div class="list">
									<div class="leftbox"><span></span><div class="title">性格</div></div>
									<div class="rightbox">
										<ul class="messList">
										<li>路过</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="item3mobile" class="mui-slider-item mui-control-content">
					<div id="scroll3" class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<div class="contain">
								<!--  <textarea name="" rows="" cols="" placeholder="经理点评" readonly="readonly"></textarea>-->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--底部按钮-->
	<div class="btn-success mui-bar mui-bar-tab">
			<div class="midde">
				<button type="button" id="firetBtn" class="failedCss" ><img src="static/images/dianhua.png" alt="" /><span>电话</span></button>
				<button type="button" id="btn-succ" class="failedCss" ><img src="static/images/xinxi.png" alt="" /><span>短信</span></button>
			</div>
	</div>
	
	<!--遮罩层-->
	<div class="popup-backdrop" style="display: none;"></div>
	<script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			mui.init({
				swipeBack:true //启用右滑关闭功能
			});
			var item1 = document.getElementById('item1mobile');
			var item2 = document.getElementById('item2mobile');
			var item3 = document.getElementById('item3mobile');
			document.getElementById('slider').addEventListener('slide',function(e){
				if (e.detail.slideNumber === 0){
					showOneDiv();
				}
				if(e.detail.slideNumber === 1){
					showTwoDiv();
				}
				if(e.detail.slideNumber === 2){
					managerEvl();
				}
			},1000)
		})
	</script>
</body>
</html>

