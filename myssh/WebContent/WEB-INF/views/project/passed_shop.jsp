<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-注册门店审核</title>       
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/sureBill.css" /> 
         <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/jqpagination.css" />
         <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/commend.css">
         <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/index.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/lib/laydate/need/laydate.css" /> 
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/laydate/laydate.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.jqpagination.js"></script>
        <script type="text/javascript">
        
        var startAllAppoint = 0;
        var limitAllAppoint = 10;
        var currentPageAllAppoint = 1;
        var totalPageAllAppoint = 0;
        
        
      //分页参数设置
      var applyId = 1;		//1表示已经通过的中介门店	
        $(document).ready(function(){
        	getShopAuditInfo();
        	initpage1();
        	bindClick(); 
        });
        
        
        function bindClick(){
        	$("#b1").click(function(){
        		window.location.href = "all_reviewd_page";	//全部状态的门店	
        	});
        	$("#b2").click(function(){
        		window.location.href = "apply_reviewd_page"; //只显示申请中的门店	
        	});
        	$("#b3").click(function(){
        		window.location.href = "passed_reviewd_page";	//只显示审核通过的门店			
        	});
        	$("#b4").click(function(){
        		window.location.href = "refuse_reviewd_page";	//只显示拒绝的门店 
        	});
        }
        
        
        
        function getShopAuditInfo(){
        	$.ajax({
        		type : "post",
        		async : false,
        		url : "<%=request.getContextPath()%>/all_shops_reviewd",
        		data : {start:startAllAppoint, limit:limitAllAppoint, applyId:applyId},
        		success : function(data,status){
        			data = eval("(" + data + ")");
        			fillShopAuditInfo(data.root);
        			startAllAppoint = data.currentResult;
        			totalPageAllAppoint = data.totalPage;
        			
        		}
        	});
        }
    

        function initpage1(){
        		
        	$("#page1").jqPagination({
        		  current_page: currentPageAllAppoint, //设置当前页 默认为1
        		  max_page : totalPageAllAppoint, //设置最大页 默认为1
        		  page_string : '当前第{current_page}页,共{max_page}页',
        		  paged : function(page) {
        		  startAllAppoint = (page-1)*limitAllAppoint;
        		  	getShopAuditInfo();
        		  }
        	});
        }
      
        
        function fillShopAuditInfo(data){
        	var s = "<tr><th>公司名称</th><th>门店名称</th><th>所在地</th><th>负责人</th><th>合伙人</th><th>电话</th><th>申请时间</th><th>当前状态</th><th>审核人</th><th>审核时间</th></tr>";
        	$.each(data, function(v, o){
        		s += '<tr><td><a href="select_shop_reviewd?shopId='+ o.shopId +'">'+ o.companyName +'</a></td>';	
        		s += '<td>' + o.shopName + '</td>';	
        		s += '<td>' + o.address + '</td>';	
        		s += '<td>' + o.contactPerson + '</td>';
        		s += '<td>暂无</td>'
        		s += '<td>' + o.phone + '</td>';	
        		s += '<td>' + o.applyTime + '</td>';
        		if(o.shopStatus == '0'){
        		s += '<td>审核中</td>';	
        		}else if(o.shopStatus == '1'){
        		s += '<td>已通过</td>';	
        		}else if(o.shopStatus == '3'){
        		s += '<td>已拒绝</td>';	
        		}else{
        		s += '<td></td>';	
        		}
        		s += '<td>' + o.userCaption + '</td>';	
        		s += '<td>' + o.approveTime + '</td></tr>';	
        	});
        	if(data.length > 0){
        		$("#page1").show();
        		$("#housesInfo").html(s);
        	}else{
        		$("#page1").hide();
        		$("#housesInfo").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
        	}
        }
        
        
        
        
        </script>
        
    </head>
    <body>
    <%@include file="/WEB-INF/views/system/public/systempublicpage.jsp" %>
        <div class="box">
            <div class="sure-bill">
                <p><a href="system_index">首页</a>&nbsp;-&nbsp;注册门店审核</p>
            </div>
            <div class="form-label">
            	<button style="ext-decoration: none;display:inline-block;width:120px;height:30px;line-height: 30px;font-size: 15px;color:#666666;background-color:#e0e0e0;border-radius: 6px;margin-left: 20px;" id="b1"  value="">显示全部的状态</button>
            	<button style="ext-decoration: none;display:inline-block;width:150px;height:30px;line-height: 30px;font-size: 15px;color:#666666;background-color:#e0e0e0;border-radius: 6px;margin-left: 20px;" id="b2" value="0">只显示审核中的申请</button>
            	<button style="ext-decoration: none;display:inline-block;width:165px;height:30px;line-height: 30px;font-size: 15px;color:#EEEEEE;background-color:#169bd5;border-radius: 6px;margin-left: 20px;" id="b3"  value="1">只显示审核通过的申请</button>
            	<button style="ext-decoration: none;display:inline-block;width:135px;height:30px;line-height: 30px;font-size: 15px;color:#666666;background-color:#e0e0e0;border-radius: 6px;margin-left: 20px;" id="b4"  value="3">只显示拒绝的申请</button>
            </div>
            <div class="house-list">
                   <table cellpadding="8" cellspacing="0" id="housesInfo" style="background-color:#FFFFFF;">
                </table>
                    <div class="pagination" id="page1">
					<a href="#" class="first" data-action="first">&laquo;</a>
					<a href="#" class="previous" data-action="previous">&lsaquo;</a>
					<input type="text" readonly="readonly" data-max-page="40" />
					<a href="#" class="next" data-action="next">&rsaquo;</a>
					<a href="#" class="last" data-action="last">&raquo;</a>
					</div>
                </div>
                
                </div>
    </body>  
</html>