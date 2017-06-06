<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <link href="<%=request.getContextPath()%>/static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/subscriptionApplication.css"/>
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/additional-methods.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/validateidCard.js" type="text/javascript"></script>
	<title>认购申请</title>
	<script type="text/javascript">
	 mui.init();
		$.validator.setDefaults({
    		submitHandler: function() {
      		alert("提交事件!");
    		}
		});
		$().ready(function() {
   			$("#myform").validate({
   				debug:true,
   			 	rules: {
   			 	rengourenIdCard: {
   			 			required: true,
   			 			isIdCardNo:true
   			 		}
   			 	},
   			 	message: {
   			 	rengourenIdCard: {
   			 			required: "请输入身份证号码",
   			 			isIdCardNo:"请输入正确的身份证号码"
   			 		}
   			 	}
   			 });
   	
		});
		function getAllAgentCustomer(){
			window.location.href="to_goToAgentMyCustomer?houseNum="+$('#houseNum').val();
		}
		
		function getChangeHouse(){
			window.location.href="to_goToSelectOneProjectHouseList?houseNum="+$('#houseNum').val()+"&allBenefitsId="+$('#allBenefitsId').val();
		}
		function getChooseRealMan(){
			window.location.href="to_goToRealEnterBuyCustomer?customerId="+$('#customerId').val();
		}
		function getChooseBenefits(){
			window.location.href="to_goToProjectBenefitsInfoList?houseNum="+$('#houseNum').val();
		}
		
		/* function getAddSession(){
			$.ajax({
   				type : "post",
   				async : false,
   				url : "to_goToAddSession",
   				data : {
   					orderProperty :  $("input[name='orderProperty']:checked").val(),
   					rengourenIdCard : $('#rengourenIdCard').val(),
   					buyPrice : $('#buyPrice').val(),
   					payStyle : $('#payStyle').val(),
   					accountStyle : $("input[name='accountStyle']:checked").val(),
   					},
			 	success: function(){
		       	   },
   			});
		} */
		function getAddSession(){
			$.ajax({
   				type : "post",
   				async : false,
   				url : "to_goToAddSession",
   				data : {
   					orderProperty :  $("input[name='orderProperty']:checked").val(),
   					rengourenIdCard : $('#rengourenIdCard').val(),
   					buyPrice : $('#buyPrice').val(),
   					payStyle : $("input[name='payStyle']:checked").val(),
   					accountStyle : $('#accountStyle').val()
   					},
			 	success: function(){
		       	   },
   			});
		}
		
		function getToSubmitContractRecord(){
			$.ajax({
   				type : "post",
   				async : false,
   				url : "to_goToSubmitContractRecord",
   				data : {
   					orderProperty :  $("input[name='orderProperty']:checked").val(),
   					rengourenIdCard : $('#rengourenIdCard').val(),
   					buyPrice : $('#buyPrice').val(),
   					payStyle : $("input[name='payStyle']:checked").val(),
   					accountStyle : $('#accountStyle').val(),
   					benefitInfo : $('#benefitInfo').val(),
   					houseNum : $('#houseNum').val(),
   					customerId : $('#customerId').val(),
   					realCustomerId : $('#realCustomerId').val(),
   					isAlreadyRead : $("input[name='isAlreadyRead']:checked").val(),
   					dposit : $('#dposit').val()
   					},
			 	success: function(){
			 		window.location.href="to_goToAllMyContractRecordsListPage";
		       	   },
   			});
		}
	</script>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-icon mui-icon-left-nav mui-pull-left" href="to_goToSaleHouseDetail"></a>
		<h1 class="mui-title">认购申请</h1>
	</header>
	<div class="mui-content">
	<form action="" method="post" id="myform" name="form">
		<div class="customerMess">
			<h2 class="headtop"><span class="shuxian"></span>客户信息</h2>
			<div class="mainMes">
				<div class="line1">
					<div class="leftBox">订单性质</div>
					<div class="rightBox">
							<c:if test="${sessionScope.newCrdto.orderProperty == 1 }">
						<div class="mui-input-row mui-radio">
							<label>自购</label>
								<input id="mybuy" name="orderProperty" type="radio" value="0" >
						</div>
						<div class="mui-input-row mui-radio">
							<label>代购</label>
								<input id="othersbuy" name="orderProperty" type="radio" value="1" checked="checked">
						</div>
						
							</c:if>
							<c:if test="${sessionScope.newCrdto.orderProperty != 1 }">
						<div class="mui-input-row mui-radio">
							<label>自购</label>
								<input id="mybuy" name="orderProperty" type="radio" value="0"  checked="checked">
						</div>
						<div class="mui-input-row mui-radio">
							<label>代购</label>
								<input id="othersbuy" name="orderProperty" type="radio" value="1" >
						</div>
							</c:if>
							
					</div>
				</div>
				<div class="line1">
					<div class="leftBox">认购客户</div>
					<div class="rightBox">
					<input type="hidden" value="${sessionScope.newCrdto.customerId}" id="customerId" name="customerId">
						<c:if test="${sessionScope.newCrdto.rName !=null && sessionScope.newCrdto.rName !=''}">
							<button type="button" class="selectCustomer" onclick="getAllAgentCustomer()" value="${sessionScope.newCrdto.customerId }" id="customerId" name="customerId">${sessionScope.newCrdto.rName }</button>
						</c:if>
						<c:if test="${sessionScope.newCrdto.rName ==null || sessionScope.newCrdto.rName ==''}">
							<button type="button" id="customerId" class="selectCustomer" onclick="getAllAgentCustomer()" value="选择认购客户">选择认购客户</button>
						</c:if>
					</div>
				</div>
				<div class="line1 idcard">
					<div class="leftBox">身份证号码</div>
					<div class="rightBox">
						<input id="rengourenIdCard" type="text" name="rengourenIdCard" placeholder="输入身份证号码" onblur="getAddSession()" value="${sessionScope.newCrdto.rengourenIdCard }"/>
					</div>
				</div>
				<div class="line1 tureCus">
					<div class="leftBox">实际购买人</div>
					<div class="rightBox">
					<input type="hidden" value="${sessionScope.newCrdto.realCustomerId }" id="realCustomerId" name="realCustomerId">
						<input id="realEnterBuyManInfo" style="width: 70%;" type="text" name="realEnterBuyManInfo" readonly="readonly" placeholder="请添加实际购买人" value="${sessionScope.newCrdto.realEnterBuyManInfo }"/>
						<button type="button" class="writeCustomer" onclick="getChooseRealMan()">选择</button>
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
							<input id="houseNum" type="text" name="houseNum" readonly="readonly" value="${sessionScope.newCrdto.houseNum}"/>
						</div>
					</div>
					<div class="line1">
						<div class="leftBox">房号</div>
						<div class="rightBox">
							<input id="houseNo" style="width: 70%;" type="text" name="houseNo" readonly="readonly" value="${sessionScope.newCrdto.houseInfo}"/>
							<button type="button" class="change" onclick="getChangeHouse()">更改</button>
						</div>
					</div>
					<div class="line1">
						<div class="leftBox">预售证号</div>
						<div class="rightBox">
							<input type="text" name="presalePermissionInfo" readonly="readonly" value="${sessionScope.newCrdto.presalePermissionInfo}"/>
						</div>
					</div>
					<div class="line1">
						<div class="leftBox">户型</div>
						<div class="rightBox">
							<input type="text" name="housType" readonly="readonly" value="${sessionScope.newCrdto.housType}"/>
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
							<input type="text" name="danjia" readonly="readonly" value="${sessionScope.newCrdto.danjia}元/㎡"/>
						</div>
					</div>
					<div class="line1">
						<div class="leftBox">总价</div>
						<div class="rightBox">
							<input type="text" name="listPriceStr" readonly="readonly" value="${sessionScope.newCrdto.listPriceStr}元"/>
						</div>
					</div>
					<div class="line1">
						<div class="leftBox">优惠条款</div>
						<div class="rightBox">
						<input type="hidden" value="${sessionScope.newCrdto.allBenefitsId}" id="allBenefitsId" name="allBenefitsId">
							<c:if test="${sessionScope.newCrdto.benefitInfo !=null && sessionScope.newCrdto.benefitInfo !=''}">
								<button type="button" class="selectCustomer" onclick="getChooseBenefits()" value="${sessionScope.newCrdto.benefitInfo }" id="benefitInfo" name="benefitInfo">${sessionScope.newCrdto.benefitInfo }</button>
							</c:if>
							<c:if test="${sessionScope.newCrdto.benefitInfo ==null || sessionScope.newCrdto.benefitInfo ==''}">
								<button type="button" class="selectCustomer" onclick="getChooseBenefits()">选择优惠条款</button>
							</c:if>
						</div>
					</div>
					<div class="line1">
						<div class="leftBox">认购价格</div>
						<div class="rightBox">
							<input type="text" name="buyPrice" onblur="getAddSession()" id="buyPrice" value="${sessionScope.newCrdto.buyPrice}"/>
						</div>
					</div>
					<div class="line1">
						<div class="leftBox">结算方式</div>
						<div class="rightBox">
							<c:if test="${sessionScope.newCrdto.accountStyle ==null || sessionScope.newCrdto.accountStyle ==''}">
								<select name="accountStyle" id="accountStyle" onchange="getAddSession()">
									<option value="">选择结算方式</option>
									<option value="5">一次性</option>
									<option value="1">公积金</option>
									<option value="2">商贷按揭</option>
									<option value="3">商贷按揭+公积金</option>
									<option value="4">其他</option>
								</select>
							</c:if>
							<c:if test="${sessionScope.newCrdto.accountStyle == 5 }">
								<select name="accountStyle" id="accountStyle" onchange="getAddSession()">
									<option value="5">一次性</option>
									<option value="1">公积金</option>
									<option value="2">商贷按揭</option>
									<option value="3">商贷按揭+公积金</option>
									<option value="4">其他</option>
								</select>
							</c:if>
							<c:if test="${sessionScope.newCrdto.accountStyle == 1 }">
								<select name="accountStyle" id="accountStyle" onchange="getAddSession()">
									<option value="1">公积金</option>
									<option value="5">一次性</option>
									<option value="2">商贷按揭</option>
									<option value="3">商贷按揭+公积金</option>
									<option value="4">其他</option>
								</select>
							</c:if>
							<c:if test="${sessionScope.newCrdto.accountStyle == 2 }">
								<select name="accountStyle" id="accountStyle" onchange="getAddSession()">
									<option value="2">商贷按揭</option>
									<option value="5">一次性</option>
									<option value="1">公积金</option>
									<option value="3">商贷按揭+公积金</option>
									<option value="4">其他</option>
								</select>
							</c:if>
							<c:if test="${sessionScope.newCrdto.accountStyle == 3 }">
								<select name="accountStyle" id="accountStyle" onchange="getAddSession()">
									<option value="3">商贷按揭+公积金</option>
									<option value="5">一次性</option>
									<option value="1">公积金</option>
									<option value="2">商贷按揭</option>
									<option value="4">其他</option>
								</select>
							</c:if>
							<c:if test="${sessionScope.newCrdto.accountStyle == 4 }">
								<select name="accountStyle" id="accountStyle" onchange="getAddSession()">
									<option value="4">其他</option>
									<option value="5">一次性</option>
									<option value="1">公积金</option>
									<option value="2">商贷按揭</option>
									<option value="3">商贷按揭+公积金</option>
								</select>
							</c:if>
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
							<input id="dposit" type="text" name="dposit" value="${sessionScope.newCrdto.dposit}" readonly="readonly"/>
						</div>
					</div>
					<div class="line1">
						<div class="leftBox">支付方式</div>
						<div class="rightBox">
							<c:if test="${sessionScope.newCrdto.payStyle == 1 }">
								<div class="mui-input-row mui-radio">
									<label>线下</label>
									<input name="payStyle" type="radio" onchange="getAddSession()" value="1" checked="checked" id="offline">
								</div>
								<div class="mui-input-row mui-radio">
									<label>线上</label>
									<input name="payStyle" type="radio" onchange="getAddSession()" value="0" id="online">
								</div>
							</c:if>
							<c:if test="${sessionScope.newCrdto.payStyle != 1 }">
								<div class="mui-input-row mui-radio">
									<label>线下</label>
									<input name="payStyle" type="radio" onchange="getAddSession()" value="1" id="offline" >
								</div>
								<div class="mui-input-row mui-radio">
									<label>线上</label>
									<input name="payStyle" type="radio" onchange="getAddSession()" value="0" id="online" checked="checked">
								</div>
							</c:if>
						</div>
					</div>
					<div class="line1">
						<div class="leftBox">查看条款</div>
						<div class="rightBox">
							<div class="mui-input-row mui-checkbox">
  								<a href="to_goToReadContractTerms?houseNum=${sessionScope.newCrdto.houseNum}">《查看相关条款》</a>
  								<input name="isAlreadyRead" value="1" type="checkbox"    id="isAlreadyRead" disabled="disabled">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="btn-success mui-bar mui-bar-tab">
			<div class="midde">
				<button id="succ-Btn" type="button" class="faileded" disabled="disabled">完成</button>
			</div>
		</div>
	</form>
	</div>
	
	<!--弹框区(确定)-->
	<div class="clickSuccess alertCss displaynone">
			<form action="" class="contain">
				<img class="imgB image-close2" src="<%=request.getContextPath()%>/static/images/close.png" alt="" />
				<div class="midd">
					<div class="pics">
						<img class="suqian" src="<%=request.getContextPath()%>/static/images/shuqianye.png" alt="" />
						<p>订单填写完成</p>
					</div>
				</div>
				<div class="btn-mid">
					<button type="button" class="submitAudit" onclick="getToSubmitContractRecord()">提交审核</button>
				</div>
			</form>
	</div>
	<!--弹出合同条款-->
	<div class="alertClause alertCss displaynone">
	<input type="hidden" value="${dataInfo }" id="dataInfo" name="dataInfo">
			<form action="" class="contain">
				<img class="imgB image-close3" src="<%=request.getContextPath()%>/static/images/close.png" alt="" />
				<div class="midde">
					<div class="mui-input-row mui-checkbox mui-left">
  						<label>我已阅读并已告知客户各条款内容</label>
  						<input name="checkbox22" value="Item 1" type="checkbox" id="ckexked22">
					</div>
				</div>
				<div class="btn-mid">
					<button type="button" id="sureTk" class="submitAuditOFF" disabled="disabled">确定</button>
				</div>
			</form>
	</div>
	<!--遮罩层-->
	<div class="popup-backdrop" style="display: none;"></div>
	<script type="text/javascript">
		$(document).ready(function(){
			changeB();
			beforewancheng();
			changgeBook();
			alertClause();
			clickSuccess();
			tiaokuan();
			sureTk();
			closeAlert();
			function changeB(){
				if($('input:radio[name="orderProperty"]:checked').val()=="1"){
					$(".tureCus").css("display","block");
				}else{
					$(".tureCus").css("display","none");
				}
				if($('#customerId').val()==""){
				    $(".writeCustomer").attr("disabled","disabled");
				}else {
					$(".writeCustomer").removeAttr("disabled");
				}
				if($("#dataInfo").val()==1){
					$("#isAlreadyRead").attr("checked","checked");
				}
			}
			function changgeBook(){
				$('input:radio[name="orderProperty"]').click(function(){
					if($('input:radio[name="orderProperty"]:checked').val()=="1"){
					$(".tureCus").css("display","block");
				}else{
					$(".tureCus").css("display","none");
				}
				if($('#customerId').val()==""){
					    $(".writeCustomer").attr("disabled","disabled");
					}else {
						$(".writeCustomer").removeAttr("disabled");
					}
					getAddSession();
				});
			}
			function getAddSession(){
				$.ajax({
	   				type : "post",
	   				async : false,
	   				url : "to_goToAddSession",
	   				data : {
	   					orderProperty :  $("input[name='orderProperty']:checked").val(),
	   					rengourenIdCard : $('#rengourenIdCard').val(),
	   					buyPrice : $('#buyPrice').val(),
	   					payStyle : $('#payStyle').val(),
	   					accountStyle : $("input[name='accountStyle']:checked").val(),
	   					},
				 	success: function(){
			       	   },
	   			});
			}
			/*点击完成弹出合同条款*/
			function alertClause(){
				$("#succ-Btn").click(function(){
					$(".alertClause").removeClass("displaynone");
					$(".popup-backdrop").css("display","block");
				})
			}
			/*关闭各弹窗*/
			
			function closeAlert(){
				$(".image-close1").click(function(){
					$(".AddtrueCus").addClass("displaynone");
					$(".popup-backdrop").css("display","none");
				});
				$(".image-close2").click(function(){
					$(".clickSuccess").addClass("displaynone");
					$(".popup-backdrop").css("display","none");
				});
				$(".image-close3").click(function(){
					$(".alertClause").addClass("displaynone");
					$(".popup-backdrop").css("display","none");
				});
			}
			/*点击完成弹框*/
			function clickSuccess(){
				$(".success").click(function(){
					$(".alertClause").removeClass("displaynone");
					$(".popup-backdrop").css("display","block");	
				});
			}
			/*条款勾选才能点确定*/
			function tiaokuan(){
				$("#ckexked22").click(function(){
					if($("input[name='checkbox22']:checked").length==0){
					$("#sureTk").removeClass("submitAudit").addClass("submitAuditOFF").attr("disabled","disabled");
					}else {
					$("#sureTk").removeClass("submitAuditOFF").addClass("submitAudit").removeAttr("disabled");
				}
				})
			}
			/*点击确定弹出提交审核*/
			function sureTk(){
				$("#sureTk").click(function(){
					$(".alertClause").addClass("displaynone");
					$(".clickSuccess").removeClass("displaynone");
				});
			}
			/*当页面所有输入框有值是才可点击完成按钮*/
			function beforewancheng(){
				mui('body').on('dragstart','.mui-content',function(){
					if($("#customerId").val()!=""&&$("#rengourenIdCard").val()!=""&&$("#buyPrice").val()!==""&&$("#dposit").val()!==""&&$('#accountStyle').val()!=""&&$("#isAlreadyRead").is(':checked')==true&&$("#rengourenIdCard").val().length=="18"){
						$("#succ-Btn").removeClass("faileded").addClass("success").removeAttr("disabled");
			  		}else {
			  			$("#succ-Btn").removeClass("success").addClass("faileded").attr("disabled","disabled");
			  		}
				})
				$("body").change(function(){
			  		if($("#customerId").val()!=""&&$("#rengourenIdCard").val()!=""&&$("#buyPrice").val()!==""&&$("#dposit").val()!==""&&$('#accountStyle').val()!=""&&$("#isAlreadyRead").is(':checked')==true&&$("#rengourenIdCard").val().length=="18"){
						$("#succ-Btn").removeClass("faileded").addClass("success").removeAttr("disabled");
			  		}else {
			  			$("#succ-Btn").removeClass("success").addClass("faileded").attr("disabled","disabled");
			  		}
				});
			}
		})
		
	</script>
</body>
</html>
