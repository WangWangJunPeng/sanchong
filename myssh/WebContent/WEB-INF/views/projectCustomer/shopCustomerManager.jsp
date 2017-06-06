<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
    <link  rel="icon" href="static/images/titleLogo.png"  />
    <title>门店管理后台</title>
    <link rel="stylesheet" href="static/layui/plugins/layui/css/layui.css" media="all" />
    <!-- <link rel="stylesheet" type="text/css" href="static/css/reset.css">
    <link rel="stylesheet" type="text/css" href="static/css/commend.css"> -->
    <!-- <link rel="stylesheet" href="static/css/jqpagination.css" /> -->
    <!-- <link rel="stylesheet" type="text/css" href="static/css/shopCustomerManager.css"> -->
    <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="static/layui/plugins/layui/layui.js"></script>
    <!-- <script type="text/javascript" src="static/js/jquery.jqpagination.js"></script> -->
   	<script type="text/javascript">
	   	$(document).ready(function(){
	   		  //ajax请求后台数据
		      getShopCustomerManagePageInfo();
		      //初始化翻页信息
		      /* initpage1(); */
	   		  //点击搜索时 搜索数据
	   		  $("#selectButton").click(function(){ 
	   			getShopCustomerManagePageInfo();
	   			currentPageAllAppoint = 1;
	   			toPage();
	   			//alert(1);
	   	      })
	   	   toPage();
	   	});
	   	
	  	//分页参数设置
	   	var startAllAppoint = 0;
	   	var limitAllAppoint = 10;
	   	var currentPageAllAppoint = 1;
	   	var totalPageAllAppoint = 0;
	   	//ajax请求后台数据
	   	function getShopCustomerManagePageInfo(){
	   		$.ajax({
	   			type:"post",
	   			async:false,
	   			url:"list_shop_customers_info",
	   			data:{start:startAllAppoint, limit:limitAllAppoint,selectValue:$("#selectValue").val()},
	   			success:function(data,status){
	   				data=eval("("+data+")");
	   				getShopCustomesInfo(data.root);
	   				startAllAppoint = data.currentResult;
	   				totalPageAllAppoint = data.totalPage;
	   				/* initpage1(); */
	   			}
	   		});
	   		
	   	}
	   	
	   /* 	function initpage1(){
	   		$("#page1").jqPagination({
	   			  link_string : '',
	   			  current_page: currentPageAllAppoint, //设置当前页 默认为1
	   			  max_page : totalPageAllAppoint, //设置最大页 默认为1
	   			  page_string : '当前第{current_page}页,共{max_page}页',
	   			  paged : function(page) {
	   			  	  startAllAppoint = (page-1)*limitAllAppoint;
	   			  	  getShopCustomerManagePageInfo();
	   			  }
	   		});
	   	} */
	   	
	   	function getShopCustomesInfo(data){
	   		var s = "<tr><th>姓名</th><th>性别</th><th>电话</th><th>备案楼盘</th><th>已成交</th><th>归属经纪人</th><th>添加时间</th></tr>";
	   		$.each(data,function(v,o){
	   				s+='<tr><td>'+o.cusName+'</td>';
	   				s+='<td>'+o.cusSex+'</td>';
	   				s+='<td>'+o.phone+'</td>';
	   				s+='<td>'+o.records+'</td>';
	   				s+='<td>'+o.alreadyDeal+'</td>';
	   				s+='<td>'+o.theMedi+'</td>';
	   				s+='<td>'+o.addTime+'</td></tr>';
	   		});

	   		if(data.length>0){
	   			$("#t_customerInfo").html(s);
	   		}else{
	   			$("#page1").hide();
	   			$("#t_customerInfo").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	   		}
	   		
	   		
	   	}
   		
	   	
	   	
	   	function toPage(){
	   		
	   		layui.use(['form', 'laypage', 'layedit','layer', 'laydate'], function() {
				var form = layui.form(),
					layer = layui.layer,
					layedit = layui.layedit,
					laydate = layui.laydate,
					laypage = layui.laypage;
				
				//调用分页
				  laypage({
				    cont: 'paged'
				    ,pages: totalPageAllAppoint //得到总页数
				    ,curr: currentPageAllAppoint
				    ,skip: true
				    ,jump: function(obj, first){
				    	
				    	currentPageAllAppoint = obj.curr;
				    	startAllAppoint = (obj.curr-1)*limitAllAppoint;
		   			  	 getShopCustomerManagePageInfo();
				      //document.getElementById('biuuu_city_list').innerHTML = render(obj, obj.curr);
				      if(!first){ //一定要加此判断，否则初始时会无限刷新
				          //location.href = '?page='+obj.curr;
				        }
				    }
				  });
				
				
			});
	   	};
	   	
   	</script>
</head>
<body>
	<div class="admin-main">
	
	
				<blockquote class="layui-elem-quote">
				<form class="layui-form" action="" >
				<div class="layui-form-item">
				<div class="layui-input-inline">
					<input type="text" id="selectValue" lay-verify="required" placeholder="客户姓名，电话" autocomplete="off" class="layui-input">
			    </div>
			    <button class="layui-btn" type="button" id="selectButton">搜索</button>
				</div>
				</form>
				<span><a href="shop_customer_manager_page_info">显示所有客户</a></span>
				</blockquote>
				<fieldset class="layui-elem-field">
					<legend>客户列表</legend>
					<div class="layui-field-box layui-form">
						<table class="layui-table admin-table" id="t_customerInfo">
							
						</table>
					</div>
				</fieldset>
				<div class="admin-table-page">
					<div id="paged" class="page">
					</div>
				</div>
			</div>
	
    <!-- <header id="" class="index-header">
        <nav>
            <ul>
                <li><a href="###" title="" class="index-logo">中介门店管理后台</a></li>
                <li><a  class="active" href="index.html" title="">首页</a></li>
                <li><a href="search.html" title="">房源搜索</a></li>
                <li><a href="###" title="">经纪人管理</a></li>
                <li><a href="###" title="">客户管理</a></li>
                <li><a href="###" title="">对账单</a></li>
                <li><a href="###" title="">业务</a></li>
                <li><a href="###" title="">欢迎您：<span>User</span></a></li>
            </ul>
        </nav>
    </header> -->
   <%--  <%@include file="../publicpage/shoppublicpage.jsp" %> --%>
    <!-- <div class="container">
        
            <p style="padding-top:10px;margin-left:10px;font-size:12px;color:#0c95db;"><a href="to_store_index" style="font-size:12px;color:#0c95db;">首页</a>&nbsp;-&nbsp;客户管理</p>
      
        <div class="box">
            <form action="">
                <input id="selectValue" type="text" placeholder="客户姓名，电话" />
                <input id="selectButton" type="button" value="搜索"/>
            </form>
            <span><a href="shop_customer_manager_page_info">显示所有客户</a></span>
            <div class="table-list">
				<table cellpadding="8" id="t_customerInfo">
				</table>
			</div>           
        </div>
        <div class="pagination" id="page1">
			<a href="#" class="first" data-action="first">&laquo;</a>
			<a href="#" class="previous" data-action="previous">&lsaquo;</a>
			<input type="text" readonly="readonly" data-max-page="40" />
			<a href="#" class="next" data-action="next">&rsaquo;</a>
			<a href="#" class="last" data-action="last">&raquo;</a>
		</div>
		<div style="height:100px;"></div>
    </div> -->
</body>
</html>