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
    <link href="static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/app.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/spareResult.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/my customer.css"/>
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" charset="utf-8">
    $(function(){
    	$("#suibian").click(function(){
    		window.location.href="to_goToAgentMyPerson";
    	})
    })
      	mui.init();
    	/* 数据读取 */
      	function qingqiu(data){
    		var $li = $("#"+data).children("input");
    		var inx = $("#"+data).children("a").data("value");
    		//console.log(index);
    		var index ;
    		if("未知"==inx){
    			index = 8;
    		}
    		if("高"==inx){
    			index = 5;
    		}
    		if("中"==inx){
    			index = 6;
    		}
    		if("低"==inx){
    			index = 7;
    		}
    		var coustomer = "";
    		//console.log($li);
    		for(var i = 0;i<$li.length;i++){
    			var text = $li.get(i);
    			coustomer += $(text).val()+",";
    			
    		}
    		console.log(coustomer);
    		window.location.href="realCustomerList?customerIds="+coustomer+"&tagId="+data+"&index="+index;
    	/* 	$.each($li,function(v,o){
    			var ids = o.val();
    			alert(ids);
    		}) */
    	}
    </script>
     <title>我的客户列表</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
			<a id="suibian" class=" mui-icon mui-icon-left-nav mui-pull-left"></a>
			<!-- <a class="mui-icon mui-icon mui-icon-plusempty mui-pull-right"></a> -->
			<h1 class="mui-title">客户列表</h1>
		</header>
	
		<div class="mui-content">
			<div class="title">
				<h2>客户意向</h2>
			</div>
			<ul class="mui-table-view">
			
				<c:forEach items="${cmap}" var="c">
					<li class="mui-table-view-cell" id="${c.key.tagId}" onclick="qingqiu(${c.key.tagId})">
						<a class="mui-navigate-right" data-value="${c.key.tagName}">
							${c.key.tagName}
							<span class="mui-badge mui-badge-danger" >${c.value.size()}</span>
						</a>
						<c:forEach items="${c.value }" var="cl">
							<input type="hidden" value="${cl}">
						</c:forEach>
						</li>
				</c:forEach>
			</ul>

			<!-- <div class="title">
				<h2>认购客户</h2>
			</div>
			<ul class="mui-table-view">
				<li class="mui-table-view-cell">
					<a class="mui-navigate-right">
						已认购
						<span class="mui-badge mui-badge-danger" id="groupNum">15</span>
					</a>
				</li>
			</ul>

			<div class="title">
				<h2>自定义分组</h2>
			</div>
			<ul class="mui-table-view">
				<li class="mui-table-view-cell">
					<p>请点击右上角+号添加自定义分组</p>
				</li>
			</ul> -->

		</div>
</body>
</html>


