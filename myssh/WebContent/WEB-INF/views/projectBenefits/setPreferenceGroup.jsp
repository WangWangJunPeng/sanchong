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
        <link rel="stylesheet" type="text/css" href="static/css/setPreferenceGroup.css" />  
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/jquery.tablednd_0_5.js"></script>
        <script type="text/javascript" src="static/js/setPreferenceGroup.js"></script>
        
        <script type="text/javascript">
        $(document).ready(function(){
        	$(".btn2").click(function(){
        		close();
        	})
        })
        function close(){
        	
        	window.location.href="to_benefitsManage";
        }
        function setBest(){
	        var aa="";
	        //console.log($("#houseNumbers").val());
	        $("input[name='checkBox']:checked").each(function()
	        	{
	        	aa += $(this).val() + ",";
	       	 })
	       	 if(aa == ""){
	       		alert("请选择优惠条款");
	       		/* $(".btn1").attr("disabled","disabled");
	       		$(".btn1").css({ "color": "#fff", "background": "gray" }) */
	       		  return false;
	       	 }else{
	       		/* $(".btn1").attr("disabled","false");
	       		$(".btn1").css({ "color": "#fff", "background": "#169bd5" }) */
	       		
	       		 $.ajax({
	 	       	   type: "POST",
	 	       	   url: "to_sortBenefits",
	 	       		//async: false,
	 	       	   data:{benefitsNumber:aa,houseNum:$("#houseNumbers").val()},
	 	       	   success: function(msg){
	 	       		window.location.href="to_benefitsManage";
	 	       	   },
	 	       	   error:function(){
	 	       		 //alert(11111)
	 	       	   }
	 	       	});
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
                <p><a href="to_pro_index">首页</a>&nbsp;-&nbsp;<a href="to_benefitsManage">价格优惠条款</a>&nbsp;-&nbsp;<a href="###">设置优惠组合</a></p>
            </div>
            <div class="clause-content"> 
                <div class="clause-list">
                <input id="houseNumbers" type="hidden" value="${data.houseNumbers }">
                    <table cellpadding="8" id="table-drag">
                    	
                        <tr class='nodrop nodrag'>
                            <th><input type="checkbox" id="allChoose" /></th>
                            <th>优惠名称</th>
                            <th>起始时间</th>
                            <th>结束时间</th>                       
                            <th>优惠内容</th>
                        </tr>
						
                        <c:forEach items="${data.youhuiList }" var="list">
                        	 <tr>
	                            <td><input type="checkbox" class="chooseSingle" name="checkBox" value="${list.benefitId }"/></td>
	                            <td>${list.benefitsName }</td>
	                            <td>${list.startTime }</td>
	                            <td>${list.endTime }</td>
	                            <td>${list.caption }</td>   
	                        </tr>
                        </c:forEach>
                        
                        <!-- <tr>
                            <td><input type="checkbox" class="chooseSingle" /></td>
                            <td>端午特价优惠选择</td>
                            <td>2016/1/1</td>
                            <td>2017/1/1</td>
                            <td>缴纳10000元,优惠4%</td>   
                        </tr> -->
                    </table>
                    <div class="drag"><span>拖动可排序</span></div>
                    
                </div>
                <div class="preference-result">
                        <!-- <p class="preference-money">依据当前设定，最高可优惠<span>99999</span>元</p> -->
                        <!-- <p class="best-group">最佳优惠组合：<span></span></p> -->
                </div>
            </div>
            <div class="btn">
                <input type="button" value="确定" class="btn1"  onclick="setBest()"/>
                <input type="button" value="关闭" class="btn2" />
            </div>
        </div>    
    </body>
   
   </html>