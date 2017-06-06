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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/houseList.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/subscriptionApplication.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/trueCustomerList.css"/>
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/additional-methods.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/validateidCard.js" type="text/javascript"></script>
    <script type="text/javascript">
    mui.init();
    	$(document).ready(function(){
    		getRealCustomerList();
    /* 		$("#suibian").click(function(){
    			alert(111);
    			window.location.herf="toCustomerList";
    		}) */
    	});
    		
    	 function getRealCustomerList(){
 			$.ajax({
 				type : "post",
 				async : false,
 				url : "to_getRealEnterBuyCustomerList",
 				data : {customerId:$("#customerId").val()},
 				success : function(data) {
 					data = eval("(" +data+")");
 					renderDateInfo(data.root);
 					
 				},
 				/* error:function(){
 					alert("信息获取失败");
 				} */
 			});
 		}
    	 function renderDateInfo(data){
    		 var s = '';
			 $.each(data,function(v,o){
				s +='<div class="message"><div class="container" ><div class="leftL"><div class="mui-input-row mui-checkbox mui-left">';
				s +='<label></label><input name="checkbox1" value="'+o.realEnterBuyId+'" type="checkbox">';
				s +='</div></div><div class="con" >';
		 		s +='';
		 		s +='<p class="navM">'+o.rName+'</p>';
		 		s +='<p><span>'+o.rPhone+'</span><span>'+o.rIdCard+'</span><span>'+o.relation+'</span></p>';
				s +='</div></div></div>';
			 });
			 if(data.length>0){
	    			$('#realCustomerList').html(s);
				 }else {
					 $("#realCustomerList").html("<tr><td style='width:10%;height:30px;margin:0 auto;'>暂无数据</td></tr>");
			 }
    	 }
    	 
    	 function getAddRealCustomer(){
    		 if($("#rPhone").val().length=="11"&&$("#rIdCard").val().length=="18"){
    			 $(".AddtrueCus").addClass("displaynone");
        		 $(".popup-backdrop").css("display","none");
        		 $.ajax({
      				type : "post",
      				async : false,
      				url : "to_goToAddRealEnterBuyCustomer",
      				data : {customerId:$("#customerId").val(),
      					rName : $('#rName').val(),
      					rPhone : $('#rPhone').val(),
      					rIdCard : $('#rIdCard').val(),
      					relation : $('#relation').val()
      					},
      					success: function(data){
      						if (data.status == "200"){
    	  						getRealCustomerList();
      						}else if (data.status == "400"){
      							$("#mes").removeClass("displaynone");
      							$(".popup-backdrop").css("display","block");
      						}else if (data.status == "600"){
      							$("#messagep").html("实际认购人最大可添加4人");
      						}
      			       	},
      			});
        		 $("#rName").val("");
        		 $("#rPhone").val("");
        		 $("#rIdCard").val("");
        		 $("#relation").val("");
    		 }else{
    			 return false;
    		 }
    		 }
    		
    	 function getDeleteRealCustomer(){
    		 var aa ="";
    		 $("input[name='checkbox1']:checked").each(function(){
    			 aa += $(this).val() + ",";
    		 })
    		 
    		 $.ajax({
   				type : "post",
   				async : false,
   				url : "to_goToDeleteRealEnterBuyCustomer",
   				data : {
   					allRealEnterBuyId :  aa
   					},
			 	success: function(){
			 		getRealCustomerList();
		       	   },
   			});
    	 }
    	 function getChooseTrueCustomer(){
    			 var bb ="";
        		 $("input[name='checkbox1']:checked").each(function(){
        			 bb += $(this).val() + ",";
        		 })
        		 window.location.href="to_goToChooseRealCustomer?allRealEnterBuyId="+bb;
    	 }
    </script>
	<title>实际认购人</title>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a  class="mui-icon mui-action-back mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">实际认购人</h1>
	</header>
	<div class="mui-content">
		<div class="titlebar">
			<p id="addP">添加实际购买人<span class="mui-icon mui-icon-plus"></span></p>
		</div>
		<p id="messagep"></p>
		<div class="customerList" id="realCustomerList">
			
		</div>
	</div>
	<div class="btn-success mui-bar mui-bar-tab">
			<div class="midde">
				<button type="button" id="firetBtn" class="failedCss" disabled="disabled" onclick="getDeleteRealCustomer()">删除</button>
				<button type="button" id="btn-succ" class="failedCss" disabled="disabled" onclick="getChooseTrueCustomer()">确定</button>
			</div>
	</div>
	<!--弹框区(选择认购客户)-->
	<div class="AddtrueCus alertCss displaynone">
			<form action="" class="contain" id="myform" name="myform">
				<img class="imgB image-close1" src="<%=request.getContextPath()%>/static/images/close.png" alt="" />
				<div class="midd">
				<input type="hidden" value="${customerId }" id="customerId" name="customerId">
					<div class="middle">
						<label class="leftBox">姓名</label>
						<div class="rightBox">
							<input name="rName" type="text" placeholder="填写姓名" id="rName"/>
						</div>
					</div>
					<div class="middle">
						<label class="leftBox">联系方式</label>
						<div class="rightBox">
							<input name="rPhone" type="text" placeholder="填写联系方式" id="rPhone"/>
						</div>
					</div>
					<div class="middle">
						<label class="leftBox">填写身份证号</label>
						<div class="rightBox">
							<input name="rIdCard" type="text" placeholder="填写身份证号" id="rIdCard"/>
						</div>
					</div>
					<div class="middle">
						<label class="leftBox">与认购人关系</label>
						<div class="rightBox">
							<input name="relation" type="text" placeholder="填写认购人关系" id="relation"/>
						</div>
					</div>
				</div>
				<div class="btn-mid">
					<button type="button" id="btn-wanc" class="faileded" onclick="getAddRealCustomer()" disabled="disabled">确定</button>
				</div>
			</form>
	</div>
	<div id="mes" class="alertCss displaynone">
		<img class="imgB image-close1" src="<%=request.getContextPath()%>/static/images/close.png" alt="" />
		<div class="midd">
			<div class="middle">
				<p>手机号输入重复，请检查</p>
			</div>
		</div>
	</div>
	<!--遮罩层-->
	<div class="popup-backdrop" style="display: none;"></div>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#addP").click(function(){
				$(".AddtrueCus").removeClass("displaynone");
				$(".popup-backdrop").css("display","block");
			});
			$(".imgB").click(function(){
				 	$("#rName").val("");
	    		 	$("#rPhone").val("");
	    		 	$("#rIdCard").val("");
	    		 	$("#relation").val("");
					$(".AddtrueCus").addClass("displaynone");
					$("#mes").addClass("displaynone");
					$(".popup-backdrop").css("display","none");
				});
			$("body").change(function(){
				if($("input[name='checkbox1']:checked").length==0){
					$("#btn-succ").removeClass("success").addClass("failedCss").attr("disabled","disabled");
					$("#firetBtn").removeClass("success").addClass("failedCss").attr("disabled","disabled");
				}else {
					$("#btn-succ").removeClass("failedCss").addClass("success").removeAttr("disabled");
					$("#firetBtn").removeClass("failedCss").addClass("success").removeAttr("disabled");
				}
			})
			$("#myform input").keyup(function(){
				if($("#rName").val()!=""&&$("#rPhone").val()!=""&&$("#rIdCard").val()!=""&&$("#relation").val()!=""){ 
					$("#btn-wanc").removeClass("faileded").addClass("successed").removeAttr("disabled");
				}else {
					$("#btn-wanc").removeClass("successed").addClass("faileded").attr("disabled","disabled");
				}
			});
			$("#myform").validate({
   			 	rules: {
   			 	rPhone: {
			 			phone_number:true
			 		},
			 	rIdCard: {
   			 			isIdCardNo:true
   			 		}
   			 	},
   			 	message: {
			 	rPhone: {
		 				phone_number:"手机号码格式错误"
		 		},
		 		rIdCard: {
			 			isIdCardNo:"请输入正确的身份证号码"
			 		}
   			 	}
   			 });
		})
	</script>
</body>
</html>

