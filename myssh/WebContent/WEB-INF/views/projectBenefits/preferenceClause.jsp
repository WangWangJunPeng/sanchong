<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-价格优惠条款</title>       
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="static/css/preferenceClause.css" />  
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/layer.js"></script>
        <script type="text/javascript" src="static/js/preferenceClause.js"></script>
   		
   		<script type="text/javascript">
   		$(document).ready(function(){
   			$(".set-group").click(function(){
   	   			setSort();
   	   		});
   	   		getHouseBenefitsInfo();
   	   		//初始化翻页信息
   	      	initpage1();
   		})
   		// 全选全不选
	       function allChecked(){
	       	$("#allChoose").click(function(){ 
	       	    $(".chooseSingle").prop("checked",this.checked);
	       	})
	       	$(".chooseSingle").click(function(){
	       	    var flag=true;
	       	    $(".chooseSingle").each(function() {
	       	        if (!this.checked) {
	       	            flag=false;
	       	        };
	       	    });
	       	     $("#allChoose").prop("checked",flag);
	       	  })
	       }
	   		function setSort(){
		        var aa="";
		        $("input[name='checkBox']:checked").each(function()
		        	{
		        	aa += $(this).val() + ",";
		       	 })
		       	 if(aa != ""){
		       		 
			       	$.ajax({
			       	   type: "POST",
			       	   url: "to_benefitsGroup",
			       		//async: false,
			       	   data:{houseNumber:aa},
			       	   success: function(data){
			       		data = eval("("+data+")");
			       		window.location.href="toBenefitsPage?houseNums="+data.root; 
			       	   },
			       	   error:function(){
			       		 //alert(11111)
			       	   }
			       	});
		       	 }else{
		       		 layer.alert("请选中房源")
		       	 }
	        }
   		//分页参数设置
        var startAllAppoint = 0;
        var limitAllAppoint = 15;
        var currentPageAllAppoint = 1;
        var totalPageAllAppoint = 0;
        function getHouseBenefitsInfo(){
        	$.ajax({
   				type:"post",
   				async : false,
   				url:"to_selectBenefitsManage",
   			 	data:{start:startAllAppoint,
   			 		limit:limitAllAppoint}, //$('#myForm').serialize(),
   				success:function(data){
   					data=eval("("+data+")");
   					fillHousesBenefitsInfo(data.root);
   					allChecked();
   					startAllAppoint = data.currentResult;
   					totalPageAllAppoint = data.totalPage;
   					//initpage1();
   					
   				}
   			});
        }
        function initpage1(){
        	$("#page1").jqPagination({
        		  link_string : '',
        		  current_page: currentPageAllAppoint, //设置当前页 默认为1
        		  max_page : totalPageAllAppoint, //设置最大页 默认为1
        		  page_string : '当前第{current_page}页,共{max_page}页',
        		  paged : function(page) {
        		  	  startAllAppoint = (page-1)*limitAllAppoint;
        		  	  getHouseBenefitsInfo();
        		  }
        	});
        }
        function fillHousesBenefitsInfo(data){
        	var s = ' <tr><th><input type="checkbox" id="allChoose" /></th><th>房号</th>';
                s += '<th>列表价(元)</th><th>进货价(元)</th><th>底价(元)</th><th>发布时间</th>';
                s += '<th>最高优惠(元)</th><th>最佳优惠组合</th></tr>';
            $.each(data,function(v,o){
            	s +='<tr><td><input type="checkbox" class="chooseSingle" name="checkBox" value="'+o.houseNum+'"/></td>';
                s +='<td>'+o.houseNo+'</td>';
                s +='<td>'+o.listPrice+'</td>';
                s +='<td>'+o.shopPrice+'</td>';
                s +='<td>'+o.minimumPrice+'</td>';
                s +='<td>'+o.shelvTime+'</td>';
                s +='<td>'+o.highBenefit+'</td>';
                s +='<td>'+o.bestBenefits+'</td></tr>';
            });
            if(data.length>0){
				$("#houseeBenefitsInfo").html(s);
			}else{
				$("#houseeBenefitsInfo").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
			}
        }
   		</script>
    </head>
    <body>
        <!-- <div class="header">
            <a href="###" class="self-center">案场助理个人中心</a>
            <ul>
                <li><a href="">首页</a></li>
               <li><a href="">案场信息维护</a></li>
               <li><a href="">价格优惠条款</a></li>
               <li><a href="">房源管理</a></li>
               <li><a href="">上下架管理</a></li>
               <li><a href="">客户管理</a></li>
               <li><a href="">成交业务</a></li>
               <li><a href="">账号管理</a></li>
            </ul>
            <a href="###" class="welcome">欢迎您，某某某</a>
        </div>      -->
        <%@include file="../publicpage/publicHeader.jsp" %>
        <div class="box">
             <div class="discount-clause">
                <p style="font-size:14px;color:#0c95db;"><a href="to_pro_index" style="font-size:14px;color:#0c95db;">首页</a>&nbsp;-&nbsp;<a href="###" style="font-size:14px;color:#0c95db;">价格优惠条款</a></p>
            </div>
            <div class="clause-content">
                <div class="clause-set">
                    <a href="to_allBenefits" class="manage-clause">管理价格优惠条款</a>
                    <a href="###" class="set-group" >设置选中房源的优惠组合</a>
                </div>
                <div class="clause-list">
                    <table cellpadding="8" id="houseeBenefitsInfo">
                        
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
            <div style="height:50px;"></div>
        </div>
    </body>
</html>