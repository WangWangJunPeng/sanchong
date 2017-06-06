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
        <link rel="stylesheet" type="text/css" href="static/css/manageClause.css" />  
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
         <script type="text/javascript" src="static/js/layer.js"></script>
        <script type="text/javascript" src="static/js/manageClause.js"></script>
   		
   		<script type="text/javascript">
   			function getDelete(){
   				var aa="";
		        $("input[name='checkBox']:checked").each(function()
		        	{
		        	aa += $(this).val() + ",";
		       	 })
		        	//console.log(aa)
		       if(aa != "" ){ 	
		    	   layer.confirm('确认要删除选中的优惠条款吗？', {
		               title:['操作确认'],
		               btn: ['确定','取消'],
		               btnAlign:'c'
		           }, function(){
		                layer.msg('删除成功!',{icon:1,time:1000},function(){
		               	
		                	$.ajax({
		     		       	   type: "POST",
		     		       	   url: "to-deleteSomeBenefits",
		     		       		//async: false,
		     		       	   data:{someBenefitsId:aa},
		     		       	   success: function(msg){
		     		       		window.location.href="to_allBenefits";
		     		       	   },
		     		       	   error:function(){
		     		       		 //alert(11111)
		     		       	   }
		     		       	});
		                }); 
		               
		           });
		       }else{
		    	 layer.alert("请选择要删除的条款")  
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
                <p style="font-size:14px;color:#0c95db;"><a href="to_pro_index" style="font-size:14px;color:#0c95db;">首页</a>&nbsp;-&nbsp;<a href="to_benefitsManage" style="font-size:14px;color:#0c95db;">价格优惠条款</a>&nbsp;-&nbsp;<a href="###" style="font-size:14px;color:#0c95db;">管理价格优惠条款</a></p>
            </div>
            <div class="clause-content">
                <div class="clause-set">
                    <a href="to_addBenefits" class="manage-clause">新增价格优惠条款</a>
                    <button onclick="getDelete()">批量删除</button>
                </div>
                <div class="clause-list">
                    <table cellpadding="8">
                        <tr>
                            <th><input type="checkbox" id="allChoose" /></th>
                            <th>优惠名称</th>
                            <th>起始时间</th>
                            <th>结束时间</th>
                            <th>状态</th>
                            <th>优惠内容</th>
                            <th>操作</th>
                        </tr>
					
						<c:forEach items="${data }" var="list">
                    		<tr>
	                            <td><input type="checkbox" class="chooseSingle" name="checkBox" value="${list.benefitId }"/></td>
	                            <td>${list.benefitsName }</td>
	                            <td>${list.startTime }</td>
	                            <td>${list.endTime }</td>
	                            <td>${list.benefitsStatus }</td>
	                            <td>${list.caption }</td>
	                            <td>
	                                <a href="to_updateBenefits?benefitId=${list.benefitId }">修改</a>
	                                <a href="to_deleteOneBenefits?benefitId=${list.benefitId }">删除</a>
	                            </td>
	                            
	                        </tr>
                    	</c:forEach>	
                        
                        <!--  <tr>
                            <td><input type="checkbox" class="chooseSingle" /></td>
                            <td>中秋特价优惠选择</td>
                            <td>2016/1/1</td>
                            <td>2017/1/1</td>
                            <td>进行中</td>
                            <td>缴纳10000元,优惠10%</td>
                            <td>
                                <a href="alterClause.html">修改</a>
                                 <a href="###">删除</a>
                            </td>
                            
                        </tr> -->
                    </table>
                </div>
            </div>
            <div style="height:10px;"></div>
        </div>    
    </body>
</html>