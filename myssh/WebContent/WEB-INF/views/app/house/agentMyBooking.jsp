<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <link href="<%=request.getContextPath()%>/static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/myBooking.css"/>
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<title>我的订单</title>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class=" mui-icon mui-icon-left-nav mui-pull-left" href="to_goToAgentMyService"></a>
		<h1 class="mui-title">我的订单</h1>
	</header>
	<div class="mui-content">
		<div id="slider" class="mui-slider mui-fullscreen">
				<div id="sliderSegmentedControl" class="mui-scroll-wrapper mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
					<div class="mui-scroll">
						<a class="mui-control-item mui-active" data-value="all" href="#item1mobile">
							<img src="<%=request.getContextPath()%>/static/images/appicon1.png" alt="" />
							全部订单
						</a>
						<a class="mui-control-item" href="#item2mobile">
							<img src="<%=request.getContextPath()%>/static/images/appicon2.png" alt="" />
							待审核
						</a>
						<a class="mui-control-item" href="#item3mobile">
							<img src="<%=request.getContextPath()%>/static/images/appicon3.png" alt="" />
							待付款
						</a>
						<a class="mui-control-item" href="#item4mobile">
							<img src="<%=request.getContextPath()%>/static/images/appicon4.png" alt="" />
							待签约
						</a>
						<a class="mui-control-item" href="#item5mobile">
							<img src="<%=request.getContextPath()%>/static/images/appicon5.png" alt="" />
							已签约
						</a>
						<a class="mui-control-item" href="#item6mobile">
							<img src="<%=request.getContextPath()%>/static/images/appicon6.png" alt="" />
							已拒绝
						</a>
						<a class="mui-control-item" href="#item7mobile">
							<img src="<%=request.getContextPath()%>/static/images/appicon7.png" alt="" />
							已撤销
						</a>
						<a class="mui-control-item" href="#item8mobile">
							<img src="<%=request.getContextPath()%>/static/images/appicon7.png" alt="" />
							付款待确认
						</a>
					</div>
				</div>
				<div class="mui-slider-group">
					<div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<ul class="mui-table-view" id="allOrder">
								</ul>
							</div>
						</div>
					</div>
					<div id="item2mobile" class="mui-slider-item mui-control-content">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="warpp"><h2 class="titleh2">最近订单</h2></div>
							<div class="mui-scroll">
								<ul class="mui-table-view" id="waitCheck">
								</ul>
							</div>
						</div>
					</div>
					<div id="item3mobile" class="mui-slider-item mui-control-content">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="warpp"><h2 class="titleh2">最近订单</h2></div>
							<div class="mui-scroll">
								<ul class="mui-table-view" id="waitPay">
								</ul>
							</div>
						</div>
					</div>
					<div id="item4mobile" class="mui-slider-item mui-control-content">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="warpp"><h2 class="titleh2">最近订单</h2></div>
							<div class="mui-scroll">
								<ul class="mui-table-view" id="waitSign">
								</ul>
							</div>
						</div>
					</div>
					<div id="item5mobile" class="mui-slider-item mui-control-content">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="warpp"><h2 class="titleh2">最近订单</h2></div>
							<div class="mui-scroll">
								<ul class="mui-table-view" id="hadSign">
								</ul>
							</div>
						</div>
					</div>
					<div id="item6mobile" class="mui-slider-item mui-control-content">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="warpp"><h2 class="titleh2">最近订单</h2></div>
							<div class="mui-scroll">
								<ul class="mui-table-view" id="hadRefuse">
								</ul>
							</div>
						</div>
					</div>
					<div id="item7mobile" class="mui-slider-item mui-control-content">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="warpp"><h2 class="titleh2">最近订单</h2></div>
							<div class="mui-scroll">
								<ul class="mui-table-view" id="hadRevoke">
								</ul>
							</div>
						</div>
					</div>
					<div id="item8mobile" class="mui-slider-item mui-control-content">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="warpp"><h2 class="titleh2">最近订单</h2></div>
							<div class="mui-scroll">
								<ul class="mui-table-view" id="waitPayEnter">
								</ul>
							</div>
						</div>
					</div>
				</div>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/js/mui.pullToRefresh.js"></script>
	<script src="<%=request.getContextPath()%>/static/js/mui.pullToRefresh.material.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			getCurrentMenuData(0);
			mui.init({
				gestureConfig:{
					release:true
				}
			});
			(function($) {
				//阻尼系数
				var deceleration = mui.os.ios?0.003:0.0009;
				$('.mui-scroll-wrapper').scroll({
					bounce: false,
					indicators: true, //是否显示滚动条
					deceleration:deceleration
				});
				$.ready(function() {
					var item1 = document.getElementById('item1mobile');
					var item2 = document.getElementById('item2mobile');
					var item3 = document.getElementById('item3mobile');
					var item5 = document.getElementById('item5mobile');
					var item6 = document.getElementById('item6mobile');
					var item7 = document.getElementById('item7mobile');
					var item8 = document.getElementById('item8mobile');
					document.getElementById('slider').addEventListener('slide',function(e){
						if (e.detail.slideNumber === 0){
							//alert(1);
							getCurrentMenuData(0);
						}
						if(e.detail.slideNumber === 1){
							//alert(2);
							getCurrentMenuData(1);
						}
						if(e.detail.slideNumber === 2){
							getCurrentMenuData(2);
						}
						if(e.detail.slideNumber === 3){
							getCurrentMenuData(3);
						}
						if(e.detail.slideNumber === 4){
							getCurrentMenuData(4);
						}
						if(e.detail.slideNumber === 5){
							getCurrentMenuData(5);
						}
						if(e.detail.slideNumber === 6){
							getCurrentMenuData(6);
						}
						if(e.detail.slideNumber === 7){
							getCurrentMenuData(7);
						}
					},1000)
					//循环初始化所有下拉刷新，上拉加载。
					$.ready(function() {
						//循环初始化所有下拉刷新，上拉加载。
						 $.each(document.querySelectorAll('.mui-slider-group .mui-scroll'), function(index, pullRefreshEl) {
							$(pullRefreshEl).pullToRefresh({
								down: {
									callback: function() {
										var self = this;
										setTimeout(function() {
											var ul = self.element.querySelector('.mui-table-view');
											ul.insertBefore(createFragment(ul, index, 5, true), ul.firstChild);
											self.endPullDownToRefresh();
										}, 1500);
									}
								},
								up: {
									callback: function() {
										var self = this;
										setTimeout(function() {
											var ul = self.element.querySelector('.mui-table-view');
												ul.appendChild(createFragment(ul, index, 2));
												self.endPullUpToRefresh();
										}, 1500);
									}
								}
							});
							getId();
						});
						var createFragment = function(ul, index, count, reverse) {
							var id = null;
							if(index==0){
								//全部
								id = "allOrder";
							}else if(index==1){
								//待审核
								id = "waitCheck";
							}else if(index==2){
								//待付款
								id = "waitPay";
							}else if(index==3){
								//待签约
								id = "waitSign";
							}else if(index==4){
								//已签约
								id = "hadSign";
							}else if(index==5){
								//已决绝
								id = "hadRefuse";
							}else if(index==6){
								//已撤销
								id = "hadRevoke";
							}else if(index==7){
								//付款待确认
								id = "waitPayEnter";
							}
							var fragment = document.createDocumentFragment();
							section = document.createElement('section');
							/* for (var i = 0; i < count; i++) {
								li = document.createElement('li');
								li.className = 'mui-table-view-cell';
								li.innerHTML = '第' + (index + 1) + '个选项卡子项-' + (length + (reverse ? (count - i) : (i + 1)));
								fragment.appendChild(li);
							} */
							var div = getCurrentMenuData(index);
							if(div!=undefined){
								dropHtml(id);
								section.innerHTML=div;
								fragment.appendChild(section);
								return fragment;
							}else{
								return fragment;
							}
						};
					});
					
				});
			})(mui);
		})
		function dropHtml(id){
			$("#"+id).children().remove();
		}
		//获取当前菜单的数据
		function getCurrentMenuData(menu){
			var url = null;
			var str = null;
			if(menu==0){
				//全部
				url = "to_goToAllMyContractRecordsList";
				
			}else if(menu==1){
				//待审核
				url = "to_goToContractRecordsByReadyCheck";
			}else if(menu==2){
				//待付款
				url = "to_goToContractRecordsByReadyPay";
			}else if(menu==3){
				//待签约
				url = "to_goToContractRecordsByReadyContract";
			}else if(menu==4){
				//已签约
				url = "to_goToContractRecordsByHaveContract";
			}else if(menu==5){
				//已决绝
				url = "to_goToContractRecordsByRefuse";
			}else if(menu==6){
				//已撤销
				url = "to_goToContractRecordsByRevoke";
			}else if(menu==7){
				//付款待确认
				url = "to_goToContractRecordsByEnterPay";
			}
			//判断url,执行后台方法，获取数据
			if(url!=null){
				$.ajax({
	    		  type:"post",
	    		  async : false,
	    		  url:url,
	    	   	  success:function(data){
	    	   		data = eval("("+data+")");
	    	   		str=getToAllContractRecords(data.root,menu);
	    	   		getId();
	    	   	  }
		    	});
			}else{
				
			}
			
		}
		//添加数据
		function getToAllContractRecords(data,menu){
			 var div='';
			$.each(data,function(v,o){
				 div +='<a><div class="message" data-value='+o.recordNo+'><div class="container"><div class="leftbox">';
				 div +='<img src="'+o.photoUrl+'" alt="" /></div><div class="rightbox">';
				 div +='<h2><span>'+o.objectName+'</span></h2>';
				 div +='<h2><span>'+o.buildingNo+'</span><span>栋</span><span>'+o.unit+'</span><span>单元</span><span>'+o.floor+'</span><span>楼</span><span>'+o.houseNo+'</span><span>号</span></h2><p>下单时间：<span>'+o.applyTime+'</span></p>';
				 div +='<p>认购客户：<span>'+o.customerName+'</span><span class="redcolor floatRight"><span>总价:</span>'+o.buyPrice+'元</span></p>';
				 if (o.recordStatus == 0 ){
					 div +='<p><span class="redcolor">待审核</span><span class="floatRight"><span>单价:</span>'+o.danjia+'元/㎡</span></p>';
				 }
				 if (o.recordStatus == 1 && (o.voucherUploadTime==null || o.voucherUploadTime=='')){
					 div +='<p><span class="redcolor">待付款</span><span class="floatRight"><span>单价:</span>'+o.danjia+'元/㎡</span></p>';
				 }
				 if (o.recordStatus == 1 && o.voucherUploadTime!=null && o.voucherUploadTime!=''){
					 div +='<p><span class="redcolor">付款待确认</span><span class="floatRight"><span>单价:</span>'+o.danjia+'元/㎡</span></p>';
				 }
				 if (o.recordStatus == 3 ){
					 div +='<p><span class="redcolor">已拒绝</span><span class="floatRight"><span>单价:</span>'+o.danjia+'元/㎡</span></p>';
				 }
				 if (o.recordStatus == 4 ){
					 div +='<p><span class="redcolor">待签约</span><span class="floatRight"><span>单价:</span>'+o.danjia+'元/㎡</span></p>';
				 }
				 if (o.recordStatus == 5 ){
					 div +='<p><span class="redcolor">已签约</span><span class="floatRight"><span>单价:</span>'+o.danjia+'元/㎡</span></p>';
				 }
				 if (o.recordStatus == 7 ){
					 div +='<p><span class="redcolor">已撤销</span><span class="floatRight"><span>单价:</span>'+o.danjia+'元/㎡</span></p>';
				 }
				 div +='</div></div></div></a>';
			 })
			 //全部
			 if(data.length>0 && menu==0){
				 $("#allOrder").html(div);
				 return div;
			 }else{
				 $("#picInfos").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
			//待审核
			if(data.length>0 && menu==1){
				 $("#waitCheck").html(div);
				 return div;
			 }else{
				 $("#waitCheck").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
			//待付款
			if(data.length>0 && menu==2){
				 $("#waitPay").html(div);
				 return div;
			 }else{
				 $("#waitPay").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
			//待签约
			if(data.length>0 && menu==3){
				 $("#waitSign").html(div);
				 return div;
			 }else{
				 $("#waitSign").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
			//已签约
			if(data.length>0 && menu==4){
				 $("#hadSign").html(div);
				 return div;
			 }else{
				 $("#hadSign").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
			//已决绝
			if(data.length>0 && menu==5){
				 $("#hadRefuse").html(div);
				 return div;
			 }else{
				 $("#hadRefuse").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
			//已撤销
			if(data.length>0 && menu==6){
				 $("#hadRevoke").html(div);
				 return div;
			 }else{
				 $("#hadRevoke").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
			//付款待确认
			if(data.length>0 && menu==7){
				 $("#waitPayEnter").html(div);
				 return div;
			 }else{
				 $("#waitPayEnter").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
		}
		//订单列表点击跳转
		function getId(){
			$(".message").on('tap', function() { 
				window.location.href = "to_goToReadOneContractRecord?recordNo="+$(this).data("value");
			})
		}
	</script>
</body>
</html>

