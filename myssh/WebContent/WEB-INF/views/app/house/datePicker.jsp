<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <title> 日期</title>
    <script src="static/js/flexible.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/mui.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/datePicker.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/calendar.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/calendar.js"></script>

</head>

<body class="mui-ios mui-ios-9 mui-ios-9-1" style="padding-top:0;">
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			
			<h1 class="mui-title">日期</h1>
		</header>
		<div class="title"></div>
		<button type="button" class="confirmBtn">确定</button>
		<div class="mask"></div>
		<div class="boxbox" >
			<p>请选择日期</p>
			<button class="cancleBtn">确定</button>
		</div>
		
		<script type="text/javascript">
		mui.init();
		var dateStr ="";
		var cld = new Calendar({
		el: '#box',
		value: '', // 默认为new Date();
		fn: function(obj) {
			dateStr = obj.date;
		}
	})
		
		$('.confirmBtn').click(function () {
			if (dateStr!= "") {
				toIndex(dateStr);
			} else{
				showAlert();
			}
			
		}
		);
		
		function toIndex (date) {
			window.location.href = "add_date_to_agent_index_page?dateStr="+date;
		}
		
		function showAlert () {
			$('.mask').addClass('maskDiv');
			$('.boxbox').css("display","block");
		}
		
		$('.cancleBtn').click(function () {
			$('.mask').removeClass('maskDiv');
			$('.boxbox').css("display","none");
		}
		);
		
	</script>
	</body>
</html>