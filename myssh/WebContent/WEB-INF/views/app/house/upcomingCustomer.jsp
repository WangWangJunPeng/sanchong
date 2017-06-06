<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="utf-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <link href="<%=request.getContextPath()%>/static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/spareResult.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/Record customer.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/Upcoming customer.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
    </script>
    <script type="text/javascript">
	    $(document).ready(function(){
			getDataInfo();
		});
    	function getDataInfo(){
    		$.ajax({
    			type:"post",
    			async : false,
    			url:"to_nearOverdue",
    			data:{projectId : $('#projectId').val(),
    				phone : $('#phone').val()},
    			success:function(data){
    				data=eval("("+data+")");
    				renderDateInfo(data.data);
    			}
    		});
    	}
    	function renderDateInfo(data){
    		$('#projectName').html(data.projectName);
    		$('#validDays').html(data.validDays);
    		var s = '';
    		$.each(data.grList,function(v,o){
    			s += '<div class="middle"><table  class="messageList" border=""><tbody>';
    			s += '<tr><td rowspan="2" ><div class="cusName">'+o.customerName+'</div>';
    			s += '<span class="cusPho">'+o.customerPhone+'</span></td>';
    			s += '<td class="midd">备案时间：<span>'+o.applyTime+'</span></td>';
    			s += '</tr><tr><td class="midd">剩余天数：<span>1天</span></td></tr>';
    			s += '</tbody></table></div>';
    		});
    		if(data.grList.length>0){
    			$('#nearCustomerInfo').html(s);
    		}else {
				 $("#nearCustomerInfo").html("<tr><td style='width:10%;height:30px;margin:0 auto;'>暂无数据</td></tr>");
			 }
    	}
    	function getSearchCustomer(){
    		getDataInfo();
    	}
    </script>
     <title>即将到期的客户</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">即将到期的客户</h1>
	</header> 
	<div class="mui-content">
		<div class="mess">
	 		<section class="messageHead">
			<div class="leftNum">
			
				<h3 id="projectName"></h3>
				<p>备案有效期：<span id="validDays"></span><span>天</span></p>
			</div>
			<div class="rightNum">
				<form>
					<input type="hidden" value="${dataInfo }" name="projectId" id="projectId">
					<button type="button" id="btn-search" onclick="getSearchCustomer()">搜索</button>
					<input type="text" placeholder="请输入手机号搜索" id="phone" name="phone">
				</form>
			</div>
			</section>
		</div>
		<div class="mid" id="nearCustomerInfo">
			
		</div>
	</div>

</body>
</html>
