<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<title>订单状态</title>
	<link rel="stylesheet" href="static/css/mui.min.css">
	<link rel="stylesheet" type="text/css" href="static/css/feedback.css" />
	<link rel="stylesheet" href="static/css/suitable.css" />
	<link rel="stylesheet" type="text/css" href="static/css/subscriptionApplication.css"/>
	<link rel="stylesheet" type="text/css" href="static/css/orderStatus.css"/>
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script src="static/js/flexible.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		getOrderDetailsInfo();
		judgeLast ();
	});
	
	function judgeLast () {
		var final = $(".middbox").length - 1;
		$(".middbox").eq(final).children().attr("src","static/images/redDecorate.png");
	}
		//撤单
		function getRevokeContractRecord(){
			window.location.href="to_goToRevokeOneContractRecord?recordNo="+$('#recordNo').val();
		}
		//重新下单
		function againContractRecord(){
			window.location.href="to_goToContractRecord?houseNum="+$("#houseNum").val();
		}
		function getOrderDetailsInfo(){
			$.ajax({
				url:"orderDetails",
		  		type:"post",
		  		async : false,
		  		data:{recordNo:$('#recordNo').val()},
		  		dataType:"json",//服务器返回的数据类型
		  	   	success:function(data){
		  	   		var status = data.recordStatus;
		  	   		//否决状态，显示否决原因模块,隐藏撤单按钮，显示重新下单按钮
		  	   		if(status==3){
		  	   			$(".jujue").css("display","block");//显示
		  	   			$("#again111").removeClass("displaynone");
		  	   			$("#revoke11").addClass("displaynone");
		  	   		}else{
		  	   			$(".jujue").css("display","none");//不显示
		  	   		}
		  	   		//已撤单状态
			  	   	if(status==7){
		  	   			$("#again111").removeClass("displaynone");//显示重新下单
		  	   			$("#revoke11").addClass("displaynone");//隐藏撤单
		  	   			$(".jujue").css("display","none");//不显示
		  	   		}
		  	   		//订单审核通过，待打款状态，显示凭证上传模块
		  	   		if(status==1 && data.voucherUploadTime==null){
		  	   			$(".pingzhen").css("display","block");//显示
		  	   		}else{
		  	   			$(".pingzhen").css("display","none");//不显示
		  	   		}
		  	   		setOrderDetailsInfo(data);
		  	   	}
	  	   });
		}
		
		function setOrderDetailsInfo(data){
			//自购0；代购1
			if(data.orderProperty==0){
				$("input[name='radio1']").eq(1).attr("checked","checked");
			}else if(data.orderProperty==1){
				$("input[name='radio1']").eq(0).attr("checked","checked");
				$(".tureCus").css("display","block");//显示
				$.each(data.realEnterBuyMan,function(v, o) {
					$("#trueCust").after('<input type="text" name="" readonly="readonly" value='+o+' />');
				});
			}
			if(data.payStyle==0){
				$("input[name='radio2']").eq(1).attr("checked","checked");
			}else if(data.payStyle==1){
				$("input[name='radio2']").eq(0).attr("checked","checked");
			}
			$("#enterBuyMan").val(data.enterBuyMan);
			$("#idCard").val(data.idCardNum);
			$("#idCard").val(data.idCardNum);
			$("#houseNum").val(data.houseNum);
			$("#houseNo").val(data.houseNo);
			$("#presalePermissionNum").val(data.presalePermissionNum);
			$("#homeTypeName").val(data.homeTypeName);
			$("#price1").val(data.price1);
			$("#totalPrice").val(data.totalPrice);
			$("#preferentialList").val(data.preferentialList);
			$("#buyPrice").val(data.buyPrice);
			$("#accountStyle").val(data.accountStyle);
			$("#cost").val(data.haveToMoney);
			$("#refuseReson").val(data.refuseReson);
		}
		function uploadFile(){
			var form = new FormData();
			form.append("desc",$("#desc").val());
			form.append("pic",$("#payProve").get(0).files[0]);
			form.append("recordNo",$("#recordNo").val());
			$.ajax({
				  url: 'to_confirmMoney',
				  type: 'post',
				  dataType:"json",
				  data: form,
				  contentType: false,
				  processData: false,
				  success:function(data){
						if(data.code==200){
							alert(data.msg);
							window.location.href = "to_goToReadOneContractRecord?recordNo="+$("#recordNo").val();
						}
					},
					error:function(){
						alert("图片上传失败...");
					}
				 });
		}
		function callOrder(){
			$.ajax({
				  url: 'call_order',
				  type: 'post',
				  dataType:"json",
				  data: {orderNo:$("#recordNo").val()},
				  success:function(data){
						alert(data.msg);
					}
			});
		}
	</script>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-icon mui-icon-left-nav mui-pull-left" href="to_goToAllMyContractRecordsListPage" ></a>
		<h1 class="mui-title">${crdtoStatus.houseName}</h1>
	</header>
	<div class="mui-content">		
		<!-- 切换 -->
		<div id="slider" class="mui-slider">
			<div id="sliderSegmentedControl" class="mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
				<a class="mui-control-item mui-active" href="#item1mobile">订单状态</a>
				<a class="mui-control-item" href="#item2mobile">订单详情</a>
			</div>
			<div id="sliderProgressBar" class="mui-slider-progress-bar mui-col-xs-6"></div>
			<div class="mui-slider-group">
				<div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
					<div id="scroll1" class="mui-scroll-wrapper">
						<input type="hidden" value="${crdtoStatus.recordNo }" id="recordNo">
						<div class="mui-scroll">
							<c:if test="${crdtoStatus.applyTime != null && crdtoStatus.applyTime != '' }">
								<div class="page page1">
									<div class="message">
										<div class="leftbox">${crdtoStatus.applyTime }</div>
										<div class="middbox">
											<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
										</div>
										<div class="rightbox">
											<p>已提交订单</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${crdtoStatus.auditingTime != null && crdtoStatus.auditingTime != '' }">
								<c:if test="${crdtoStatus.recordStatus == 1  }">
									<div class="page page1">
										<div class="message">
											<div class="leftbox">${crdtoStatus.auditingTime }</div>
											<div class="middbox">
												<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
											</div>
											<div class="rightbox">
												<p>审核通过</p>
											</div>
										</div>
									</div>
									<div class="page page1">
										<div class="message">
											<div class="leftbox">${crdtoStatus.outTime }</div>
											<div class="middbox">
												<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
											</div>
											<div class="rightbox">
												<p><span>即将超时</span></p>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${crdtoStatus.recordStatus == 3  }">
									<div class="page page1">
										<div class="message">
											<div class="leftbox">${crdtoStatus.auditingTime }</div>
											<div class="middbox">
												<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
											</div>
											
											<div class="rightbox">
												<p>审核拒绝</p>
											</div>
										</div>
									</div>
								</c:if>	
							</c:if>
							<c:if test="${crdtoStatus.recordStatus ==1 && crdtoStatus.voucherUploadTime != null && crdtoStatus.voucherUploadTime != '' }">
								<div class="page page1">
										<div class="message">
										<div class="leftbox">${crdtoStatus.voucherUploadTime }</div>
										<div class="middbox">
											<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
										</div>
										<div class="rightbox">
											<p>凭证已上传</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${crdtoStatus.recordStatus ==4}">
								<div class="page page1">
									<div class="message">
										<div class="leftbox">${crdtoStatus.auditingTime }</div>
										<div class="middbox">
											<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
										</div>
										<div class="rightbox">
											<p>审核通过</p>
										</div>
									</div>
								</div>
								<div class="page page1">
									<div class="message">
										<div class="leftbox">${crdtoStatus.outTime }</div>
										<div class="middbox">
											<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
										</div>
										<div class="rightbox">
											<p><span>即将超时</span></p>
										</div>
									</div>
								</div>
								<div class="page page1">
										<div class="message">
										<div class="leftbox">${crdtoStatus.voucherUploadTime }</div>
										<div class="middbox">
											<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
										</div>
										<div class="rightbox">
											<p>凭证已上传</p>
										</div>
									</div>
								</div>
								<div class="page page1">
										<div class="message">
										<div class="leftbox">${crdtoStatus.remitConfirmTime }</div>
										<div class="middbox">
											<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
										</div>
										<div class="rightbox">
											<p>定金已到款</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${crdtoStatus.recordStatus ==5}">
								<div class="page page1">
									<div class="message">
										<div class="leftbox">${crdtoStatus.auditingTime }</div>
										<div class="middbox">
											<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
										</div>
										<div class="rightbox">
											<p>审核通过</p>
										</div>
									</div>
								</div>
								<div class="page page1">
									<div class="message">
										<div class="leftbox">${crdtoStatus.outTime }</div>
										<div class="middbox">
											<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
										</div>
										<div class="rightbox">
											<p><span>即将超时</span></p>
										</div>
									</div>
								</div>
								<div class="page page1">
										<div class="message">
										<div class="leftbox">${crdtoStatus.voucherUploadTime }</div>
										<div class="middbox">
											<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
										</div>
										<div class="rightbox">
											<p>凭证已上传</p>
										</div>
									</div>
								</div>
								<div class="page page1">
										<div class="message">
										<div class="leftbox">${crdtoStatus.remitConfirmTime }</div>
										<div class="middbox">
											<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
										</div>
										<div class="rightbox">
											<p>定金已到款</p>
										</div>
									</div>
								</div>
								<div class="page page1">
										<div class="message">
										<div class="leftbox">${crdtoStatus.contractConfirmTime }</div>
										<div class="middbox">
											<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
										</div>
										<div class="rightbox">
											<p>已签约</p>
										</div>
									</div>
								</div>
							</c:if>	
							<c:if test="${crdtoStatus.recordStatus == 7  }">
								<div class="page page1">
									<div class="message">
										<div class="leftbox">${crdtoStatus.revokeTime }</div>
										<div class="middbox">
											<img src="<%=request.getContextPath()%>/static/images/decorate.png" />
										</div>
										<div class="rightbox">
											<p>订单已撤销</p>
										</div>
									</div>
								</div>
							</c:if>
							<div class = "bootomDiv"></div>
						</div>
						<div class="btn-success mui-bar mui-bar-tab">
						<div class="midde">
							<c:if test="${crdtoStatus.recordStatus == 3 or crdtoStatus.recordStatus ==7}">
								<button  type="button" id="again11" class="successed btn-1" onclick="againContractRecord()">重新下单</button>
								<button  type="button" class="successed" disabled="disabled">分享给客户</button>
							</c:if>
							<c:if test="${crdtoStatus.recordStatus != 3 && crdtoStatus.recordStatus !=7}">
								<button  type="button" class="successed btn-1" onclick="getRevokeContractRecord()">撤单</button>
								<c:if test="${crdtoStatus.recordStatus == 0}">
									<button  type="button" class="successeded" onclick="callOrder()">催单</button>
								</c:if>
								<c:if test="${crdtoStatus.recordStatus != 0}">
									<button  type="button" class="successeded" disabled="disabled">分享给客户</button>
								</c:if>
							</c:if>
						</div>
					</div>
					</div>
					
				</div>
				<div id="item2mobile" class="mui-slider-item mui-control-content">
					<div id="scroll2" class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<div class="page2">
								<div class="customerMess">
									<h2 class="headtop"><span class="shuxian"></span>客户信息</h2>
									<div class="mainMes">
										<div class="line1">
											<div class="leftBox">订单性质</div>
											<div class="rightBox">
												<div class="mui-input-row mui-radio">
													<label>代购</label>
													<input id="othersbuy" name="radio1" type="radio" value="代购" disabled="disabled">
												</div>
												<div class="mui-input-row mui-radio">
													<label>自购</label>
													<input id="mybuy" name="radio1" type="radio" value="自购" disabled="disabled">
												</div>
											</div>
										</div>
										<div class="line1">
											<div class="leftBox">认购客户</div>
											<div class="rightBox">
												<input id="enterBuyMan" type="text" class="selectCustomer" readonly="readonly"></input>
											</div>
										</div>
										<div class="line1 idcard">
											<div class="leftBox">身份证号码</div>
											<div class="rightBox">
												<input id="idCard" type="text" name="idCard" placeholder="" readonly="readonly"/>
											</div>
										</div>
										<div id="realMan" class="line1 tureCus">
											<div class="leftBox">实际购买人</div>
											<div class="rightBox">
												<input id="trueCust"type="hidden"/>
											</div>
										</div>
									</div>
								</div>
								<div class="houseMess">
									<div class="customerMess">
										<h2 class="headtop"><span class="shuxian"></span>房源信息</h2>
										<div class="mainMes">
											<div class="line1">
												<div class="leftBox">房源ID</div>
												<div class="rightBox">
													<input id="houseNum" type="text" name="" readonly="readonly"/>
												</div>
											</div>
											<div class="line1">
												<div class="leftBox">房号</div>
												<div class="rightBox">
													<input id="houseNo" type="text" readonly="readonly"/>
												</div>
											</div>
											<div class="line1">
												<div class="leftBox">预售证号</div>
												<div class="rightBox">
													<input id="presalePermissionNum" type="text" name="" readonly="readonly"/>
												</div>
											</div>
											<div class="line1">
												<div class="leftBox">户型</div>
												<div class="rightBox">
													<input id="homeTypeName" type="text" name="" readonly="readonly"/>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="contPayway">
									<div class="customerMess">
										<h2 class="headtop"><span class="shuxian"></span>价格优惠与结算方式</h2>
										<div class="mainMes">
											<div class="line1">
												<div class="leftBox">单价</div>
												<div class="rightBox">
													<input id="price1" type="text" name="" readonly="readonly"/>
												</div>
											</div>
											<div class="line1">
												<div class="leftBox">总价</div>
												<div class="rightBox">
													<input id="totalPrice" type="text" name="" readonly="readonly"/>
												</div>
											</div>
											<div class="line1">
												<div class="leftBox">优惠条款</div>
												<div class="rightBox">
													<input id="preferentialList" type="text" class="selectCustomer" readonly="readonly"></input>
												</div>
											</div>
											<div class="line1">
												<div class="leftBox">认购价格</div>
												<div class="rightBox">
													<input id="buyPrice" type="text" name=""/>
												</div>
											</div>
											<div class="line1">
												<div class="leftBox">结算方式</div>
												<div class="rightBox">
													<input id="accountStyle" type="text" readonly="readonly"></input>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="clause">
									<div class="customerMess">
										<h2 class="headtop"><span class="shuxian"></span>定金与条款</h2>
										<div class="mainMes">
											<div class="line1">
												<div class="leftBox">定金数额</div>
												<div class="rightBox">
													<input id="cost" type="text" name="" readonly="readonly"/>
												</div>
											</div>
											<div class="line1">
												<div class="leftBox">支付方式</div>
												<div class="rightBox">
													<div class="mui-input-row mui-radio">
														<label>线下</label>
														<input name="radio2" type="radio" disabled="disabled">
													</div>
													<div class="mui-input-row mui-radio">
														<label>线上</label>
														<input name="radio2" type="radio" disabled="disabled">
													</div>
												</div>
											</div>
											<div class="line1">
												<div class="leftBox">查看条款</div>
												<div class="rightBox">
													<div class="mui-input-row mui-checkbox">
						  								<label><a href="###">《查看相关条款》</a></label>
						  								<input name="checkbox1" value="Item 1" type="checkbox" checked disabled="disabled">
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!--拒绝原因-->
								<div class="jujue">
									<div class="customerMess">
										<h2 class="headtop"><span class="shuxian"></span>拒绝原因</h2>
										<div class="mainMes">
											<div class="zhong">
												<textarea id="refuseReson" name="" rows="" cols="" placeholder="价格太低，不予批准。"></textarea>
											</div>
										</div>
									</div>
								</div>
								<!-- 分享 -->
								<div class="share alertCss displaynone">
									<img class="imgB image-close2" src="<%=request.getContextPath()%>/static/images/close.png" alt="" />
									<div class="midd">
										123
									</div>
								</div>
								<!--遮罩层-->
								<div class="popup-backdrop" style="display: none;"></div>
								<!--付款凭证-->
								<div class="pingzhen">
									<div class="customerMess">
										<h2 class="headtop"><span class="shuxian"></span>付款凭证</h2>
										<div class="mainMes">
											<form action="" method="post" enctype="multipart/form-data" >
												<div class="zhong">
													<div class="left">
														<input type="hidden" name="recordNo" value="${crdtoStatus.recordNo}">
														<img src="static/images/takephoto.png" />
														<p class="p1">上传凭证</p>
														<p class="p2">（仅限1张）</p>
														<input id="payProve" type="file" name="pic"/>
													</div>
													<div class="right">
														<textarea id="desc" name="desc" rows="" cols="" placeholder="添加说明"></textarea>
													</div>
													<div class="righted" style="text-align: center;">
															<input type="button" value="上传" onclick="uploadFile()">
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
								<div class="btn-detail mui-bar mui-bar-tab">
									<div class="midde">
										<button  type="button" id="again111" class="successed btn-1 displaynone" onclick="againContractRecord()">重新下单</button>
										<button  type="button" id="revoke11" class="successed btn-1" onclick="getRevokeContractRecord()">撤单</button>
										<button  type="button" class="successeded" disabled="disabled">分享给客户</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script src="static/js/mui.min.js"></script>
<script type="text/javascript">
   changgeBook();
	function changgeBook(){
		$('input:radio[name="radio1"]').click(function(){
			if($('input:radio[name="radio1"]:checked').val()=="代购"){
			$(".tureCus").css("display","block");
		}else{
			$(".tureCus").css("display","none");
		}
		});
		$('#payProve').change(function(){
			if($('#payProve').val()!=""){
			$(".p2").html("已选择1张图片");
		}else{
		}
		});
	}
	// 轮播
	mui.init({
		swipeBack:true //启用右滑关闭功能
	});
</script>
</body>
</html>