<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
	<meta charset="UTF-8" >
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no,user-scalable=no" name="format-detection">
    <link href="<%=request.getContextPath()%>/static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/customerMessage.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/aditCustomer.css"/>
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript">
    	$(function(){
    		var customerId = $("#customerId").val();
    		var projectId = $("#projectId").val();
    		$.ajax({
     			 url: 'findCustomerTag',
                  type: 'post',
                  dataType: 'json',
                  data: {
               	   "customerId":customerId,
               	   "projectId":projectId
                  },
                  success: function (data) {
                	  $.each(data,function(v,o){
                		  if(o.tagTypeName=='身份信息'){
                			  if(o.tagLibs.length>0){
	                			  showTagType(o.tagLibs,0);
                			  }else{
                				  //二级类目下含有标签----》不存在的情况
                			  }
                		  }
                		  if(o.tagTypeName=='客户需求'){
                			  if(o.tagLibs.length>0){
	                			  showTagType(o.tagLibs,1);
                			  }else{
                				  //二级类目下含有标签----》不存在的情况
                			  }
                		  }
                		  if(o.tagTypeName=='客户意向'){
                			  var text = '';
                			  $.each(o.tags,function(v,oo){
                				  if(oo.selected==1){
      		    					text += 
      		    						'<div class="check">'+
											'<input type="radio" name="yixiang"  checked="checked" value="'+oo.tagId+'" />'+
											'<label for="yixiang1">'+oo.tagName+'</label>'+
										'</div>';
          						}else{
          							text += 
          								'<div class="check">'+
										'<input type="radio" name="yixiang"  value="'+oo.tagId+'" />'+
										'<label for="yixiang1">'+oo.tagName+'</label>'+
									'</div>';
          						}
                			  })
                			  $(text).appendTo($("#khyx"));
                			  
                		  }
                		  
                	  });
                	 // showTagType(data);
                       
                  },
                  error:function(){
               	   
                  }
     			});
    		
    		
    		function showTagType(data,flag){
    			$.each(data,function(v, o) {
    				if(o.tagLibs.length>0){
    					showTagType(o.tagLibs,flag);
    				}else{
    					//不存在标签类目
    					var tagTypeName = o.tagTypeName;
    					var sortNum = o.sortNum;
    					var tagTypeId = o.tagTypeId;
    					var tagTypeDiv = 
    					'<div class="listes">'+
    						'<div class="mes">'+
    							'<div class="tip leftbox">'+
    								'<span></span><div class="title">'+tagTypeName+'</div>'+
    							'</div>'+
    						'</div>'+
    						'<div class="all">'+
    		    			'<div class="contain">';
    					//'</div>';	
    					var isMultiple = o.isMultiple;
    					var tags = o.tags;
    					var lilist = '';
    					if(tags.length>0){
    						lilist = showTags(tags,isMultiple,tagTypeId);
    						tagTypeDiv += lilist;
    					}else{
    						//无可用标签
    						tagTypeDiv+='<li>暂无</li>';
    					}
    					tagTypeDiv += '</div>'+
    					'</div>'+
    					'</div>';
    					//alert(tagTypeDiv);
    					if(flag==0){
	    					$(tagTypeDiv).appendTo($("#customerInfo"));
    					}
    					if(flag==1){
    						$(tagTypeDiv).appendTo($("#customerDemand"));
    					}
    				}
    				
    			})
    		}
    		
    		function showTags(data,isMultiple,tagTypeId){
    			var text = '';
    			$.each(data,function(v,o){
    				var tag = o.children;
    				if(tag.length>0){
    					//alert("asd");
    					text += showTags(tag,isMultiple,tagTypeId);
    				}else{
    					//展示选中的标签
    					if(isMultiple==1){
    						if(o.selected==1){
		    					text += 
		    						'<div class="check">'+
		    							'<input type="checkbox" name="'+tagTypeId+'"  value="'+o.tagId+'" checked="checked" />'+
		    							'<label for="1">'+o.tagName+'</label>'+
		    						'</div>';
    						}else{
    							text += 
		    						'<div class="check">'+
		    							'<input type="checkbox" name="'+tagTypeId+'"  value="'+o.tagId+'" />'+
		    							'<label for="1">'+o.tagName+'</label>'+
		    						'</div>';
    						}
    					}else{
    						if(o.selected==1){
	    						text += 
	    	    						'<div class="check">'+
	    	    							'<input type="radio" name="'+tagTypeId+'"  value="'+o.tagId+'" checked="checked" />'+
	    	    							'<label for="1">'+o.tagName+'</label>'+
	    	    						'</div>';
    						}else{
	    						text += 
	    	    						'<div class="check">'+
	    	    							'<input type="radio" name="'+tagTypeId+'"  value="'+o.tagId+'" />'+
	    	    							'<label for="1">'+o.tagName+'</label>'+
	    	    						'</div>';
    							
    						}
    					}
    				}

    			})
    			return text;
    		}
    		
    		
    		
    		//点击保存
    		$("#btn-succ").click(function(){
    			var customerId = $("#customerId").val();
    			var projectId = $("#projectId").val();
    			var name = $("#name").val();
    			var visitNo = $("#visitNo").val();
    			var sex = '';
    			var index = $("#index").val();
    			$('input[name="sex"]:checked').each(function(){ 
    				sex = $(this).val(); 
    			})
    			var phone = $("#phone").val();
    			var yixiang = '';
    			$('input[name="yixiang"]:checked').each(function(){ 
    				yixiang = $(this).val(); 
    			})
    			//alert(yixiang);
    			var chk_value = [];
				$('input[type="checkbox"]:checked').each(function(){ 
					chk_value.push($(this).val()); 
    			})
    			//console.log(chk_value);
    			var rd_value = [];
				$('input[type="radio"]:checked').each(function(){ 
					rd_value.push($(this).val()); 
    			})
    			console.log(rd_value);
				//rd_value.splice($.inArray(sex,rd_value),1);
				$.each(rd_value,function(index,item){
					if(sex){
						if(item==sex){
							rd_value.splice(index,1);
						}
					}
				})
				$.merge(chk_value,rd_value);
				console.log(chk_value);
				var tagss='';
				$.each(chk_value,function(v,o){
					tagss += o+",";
				})
				$.post("saveCustomerAndTag",{
					"projectId":projectId,
					"projectCustomerId":customerId,
					"projectCustomerPhone":phone,
					"sex":sex,
					"visitNo":visitNo,
					//身份号
					"projectCustomerName":name,
					"tagss":tagss,
					"index":index
				},function(data){
					if(data.status==200){

						 mui.plusReady(function() {
							 plus.webview.currentWebview().opener().hide();
					            // 关闭当前页
					         plus.webview.currentWebview().close();
						 });
						window.location.href="findCustomersInformation?projectCustomerId="+data.data+"&index="+data.index;
					}
				});
    		});
    	})
    	
    </script>
	<title>编辑客户信息</title>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">编辑客户信息</h1>
	</header>
	<form action="">
	<div class="mui-content">
		<!--信息展示-->
		<div id="slider" class="mui-slider">
			<div id="sliderSegmentedControl" class="mui-slider-indicator mui-segmented-control mui-segmented-control-inverted mui-bar mui-bar-nav" style="position:fixed;top:1.1733rem;">
				<a class="mui-control-item mui-active" href="#item1mobile" id="item1">身份信息</a>
				<a class="mui-control-item" href="#item2mobile" id="item2">客户需求</a>
			</div>
			<div id="sliderProgressBar" class="mui-slider-progress-bar mui-col-xs-6"></div>
			<div class="mui-slider-group">
				<div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
					<div id="scroll1" class="mui-scroll-wrapper">
						<div id="customerInfo" class="mui-scroll">
							<div class="listes">
									<div class="mes">
										<div class="tip leftbox">
											<span></span><div class="title">客户基本信息</div>
										</div>
									</div>
									<div class="all1">
										<div class="contain">
											<div class="list2">
												<label for="name" class="left1">姓名</label>
												<input type="text" name="name" id="name" placeholder="输入姓名" value="${customer.projectCustomerName}"/>
												<input type="hidden" id="customerId" value="${customer.projectCustomerId}">
												<input type="hidden" id="projectId" value="${customer.projectId}">
												<input type="hidden" id="visitNo" value="${visitNo}">
												<input type="hidden" id="index" value="${index}">
											</div>
											<div class="list2">
											<div class="left2">性别</div>
												<div class="right1 right11">
												<c:if test="${customer.sex==0||customer.sex==null}">
													<div class="check">
													<input type="radio" name="sex" id="male" value="1" />
													<label for="male">男</label>
													</div>
													<div class="check check1">
														<input type="radio" name="sex" id="formale" value="2" />
														<label for="formale">女</label>
													</div>
												</c:if>
												<c:if test="${customer.sex==1}">
													<div class="check">
													<input type="radio" name="sex" id="male" value="1" checked="checked"/>
													<label for="male">男</label>
													</div>
													<div class="check check1">
														<input type="radio" name="sex" id="formale" value="2" />
														<label for="formale">女</label>
													</div>
												</c:if>
												<c:if test="${customer.sex==2}">
													<div class="check">
													<input type="radio" name="sex" id="male" value="1" />
													<label for="male">男</label>
													</div>
													<div class="check check1">
														<input type="radio" name="sex" id="formale" value="2" checked="checked"/>
														<label for="formale">女</label>
													</div>
												</c:if>
												</div>
											</div>
											<div class="list2">
												<label for="phone" class="left1">联系方式</label>
												<input type="text" name="phone" id="phone" readonly="readonly" placeholder="输入联系方式" value="${customer.projectCustomerPhone}"/>
											</div>
											<div class="list2">
												<div class="left1">客户意向</div>
												<div id="khyx" class="right1 right11">
												<!--  
												<c:choose>
													<c:when test="${yixiang=='高意向'}">
													<div class="check">
														<input type="radio" name="yixiang" id="yixiang1" checked="checked" value="高" />
														<label for="yixiang1">高</label>
													</div>
												</c:when>
												<c:otherwise>
													<div class="check">
														<input type="radio" name="yixiang" id="yixiang1"  value="高" />
														<label for="yixiang1">高</label>
													</div>
												</c:otherwise>
												</c:choose>
												
												<c:choose>
												<c:when test="${yixiang=='中意向'}">
													<div class="check">
														<input type="radio" name="yixiang" id="yixiang2" checked="checked" value="中" />
														<label for="yixiang2">中</label>
													</div>
												</c:when>
												<c:otherwise>
													<div class="check">
														<input type="radio" name="yixiang" id="yixiang2" value="中" />
														<label for="yixiang2">中</label>
													</div>
												</c:otherwise>
												</c:choose>
												<c:choose>
												<c:when test="${yixiang=='低意向'}">
													<div class="check">
														<input type="radio" name="yixiang" id="yixiang3" checked="checked" value="低" />
														<label for="yixiang3">低</label>
													</div>
												</c:when>
												<c:otherwise>
													<div class="check">
														<input type="radio" name="yixiang" id="yixiang3" value="低" />
														<label for="yixiang3">低</label>
													</div>
												</c:otherwise>
												</c:choose>
												<c:choose>
												<c:when test="${yixiang=='未知'}">
													<div class="check">
														<input type="radio" name="yixiang" id="yixiang4" checked="checked" value="未知" />
														<label for="yixiang4">未知</label>
													</div>
												</c:when>
												<c:otherwise>
													<div class="check">
														<input type="radio" name="yixiang" id="yixiang4" value="未知" />
														<label for="yixiang4">未知</label>
													</div>
												</c:otherwise>
												</c:choose>
												-->
												</div>
											</div>
										</div>
									</div>
								</div>
			<!-- top ------------------------------------------------------------------------------
								<div class="listes">
									<div class="mes">
										<div class="tip leftbox">
											<span></span><div class="title">知晓途径</div>
										</div>
									</div>
									<div class="all">
										<div class="contain">
											<div class="check">
												<input type="checkbox" name="tujin" id="1" value="路过" />
												<label for="1">路过</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="2" value="朋友介绍" />
												<label for="2">朋友介绍</label>
											</div>
											
										</div>
									</div>
								</div>
								-->
								<!--  
								<div class="listes">
									<div class="mes">
										<div class="tip leftbox">
											<span></span><div class="title">知晓途径</div>
										</div>
									</div>
									<div class="all">
										<div class="contain">
											<div class="check">
												<input type="checkbox" name="tujin" id="1" value="路过" />
												<label for="1">路过</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="2" value="朋友介绍" />
												<label for="2">朋友介绍</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="3" value="老带新" />
												<label for="3">老带新</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="4" value="客带客" />
												<label for="4">客带客</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="5" value="中介" />
												<label for="5">中介</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="6" value="只是随便来了解行" />
												<label for="6">只是随便来了解行</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="7" value="朋友圈分享的" />
												<label for="7">朋友圈分享的</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="8" value="网络分享的消息" />
												<label for="8">网络分享的消息</label>
											</div>
										</div>
									</div>
								</div>
								-->
						</div>
					</div>
				</div>
				<div id="item2mobile" class="mui-slider-item mui-control-content">
					<div id="scroll2" class="mui-scroll-wrapper">
						<div id="customerDemand" class="mui-scroll">
						<!-- ---------------------------------------------------------------------- 
							<div class="listes">
									<div class="mes">
										<div class="tip leftbox">
											<span></span><div class="title">知晓途径</div>
										</div>
									</div>
									<div class="all">
										<div class="contain">
											<div class="check">
												<input type="checkbox" name="tujin" id="1" value="路过" />
												<label for="1">路过</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="2" value="朋友介绍" />
												<label for="2">朋友介绍</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="3" value="老带新" />
												<label for="3">老带新</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="4" value="客带客" />
												<label for="4">客带客</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="5" value="中介" />
												<label for="5">中介</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="6" value="只是随便来了解行" />
												<label for="6">只是随便来了解行</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="7" value="朋友圈分享的" />
												<label for="7">朋友圈分享的</label>
											</div>
											<div class="check">
												<input type="checkbox" name="tujin" id="8" value="网络分享的消息" />
												<label for="8">网络分享的消息</label>
											</div>
										</div>
									</div>
								</div>
								-->
								<!--  
								<div class="listes">
									<div class="mes">
										<div class="tip leftbox">
											<span></span><div class="title">购买能力</div>
										</div>
									</div>
									<div class="all">
										<div class="contain">
											<div class="check">
												<input type="checkbox" name="goumai" id="9" value="路过" />
												<label for="9">路过</label>
											</div>
											<div class="check">
												<input type="checkbox" name="goumai" id="10" value="朋友介绍" />
												<label for="10">朋友介绍</label>
											</div>
											<div class="check">
												<input type="checkbox" name="goumai" id="11" value="老带新" />
												<label for="11">老带新</label>
											</div>
											<div class="check">
												<input type="checkbox" name="goumai" id="12" value="客带客" />
												<label for="12">客带客</label>
											</div>
											<div class="check">
												<input type="checkbox" name="goumai" id="13" value="中介" />
												<label for="13">中介</label>
											</div>
											<div class="check">
												<input type="checkbox" name="goumai" id="14" value="只是随便来了解行" />
												<label for="14">只是随便来了解行</label>
											</div>
											<div class="check">
												<input type="checkbox" name="goumai" id="15" value="朋友圈分享的" />
												<label for="15">朋友圈分享的</label>
											</div>
											<div class="check">
												<input type="checkbox" name="goumai" id="16" value="网络分享的消息" />
												<label for="16">网络分享的消息</label>
											</div>
										</div>
									</div>
								</div>
								<div class="listes">
									<div class="mes">
										<div class="tip leftbox">
											<span></span><div class="title">购买能力</div>
										</div>
									</div>
									<div class="all">
										<div class="contain">
											<div class="check">
												<input type="checkbox" name="goumai" id="17" value="路过" />
												<label for="17">路过</label>
											</div>
											<div class="check">
												<input type="checkbox" name="goumai" id="18" value="朋友介绍" />
												<label for="18">朋友介绍</label>
											</div>
											<div class="check">
												<input type="checkbox" name="goumai" id="19" value="老带新" />
												<label for="19">老带新</label>
											</div>
											<div class="check">
												<input type="checkbox" name="goumai" id="20" value="客带客" />
												<label for="20">客带客</label>
											</div>
											<div class="check">
												<input type="checkbox" name="goumai" id="21" value="中介" />
												<label for="21">中介</label>
											</div>
											<div class="check">
												<input type="checkbox" name="goumai" id="22" value="只是随便来了解行" />
												<label for="22">只是随便来了解行</label>
											</div>
											<div class="check">
												<input type="checkbox" name="goumai" id="23" value="朋友圈分享的" />
												<label for="23">朋友圈分享的</label>
											</div>
											<div class="check">
												<input type="checkbox" name="goumai" id="24" value="网络分享的消息" />
												<label for="24">网络分享的消息</label>
											</div>
										</div>
									</div>
								</div>
								-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--底部按钮-->
	<div class="btn-success mui-bar mui-bar-tab">
			<div class="midde">
				<button type="button" id="btn-next" class="failedCss" >下一步</button>
				<button type="button" id="btn-succ" class="failedCss null" >保存</button>
			</div>
	</div>
	</form>
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
			document.getElementById('slider').addEventListener('slide',function(e){
				if (e.detail.slideNumber === 0){
					$("#btn-next").removeClass("null");
					$("#btn-succ").addClass("null");
				}
				if(e.detail.slideNumber === 1){
					$("#btn-next").addClass("null");
					$("#btn-succ").removeClass("null");
				}
			},1000)
			$("#btn-next").click(function(){
				mui('#slider').slider().gotoItem(1);
				$('body,html').animate({  
	                scrollTop: 0  
	            },100);  
	            return false;
			})
		})
	</script>
</body>
</html>

