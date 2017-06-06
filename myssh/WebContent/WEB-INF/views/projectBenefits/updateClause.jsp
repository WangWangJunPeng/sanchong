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
        <link rel="stylesheet" type="text/css" href="static/css/addClause.css" />
          <link rel="stylesheet" type="text/css" href="static/css/validation.css" />   
         
        <link rel="stylesheet" type="text/css" href="static/lib/laydate/need/laydate.css" />
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/validation.js"></script>
        <script type="text/javascript" src="static/lib/laydate/laydate.js"></script>
        <script type="text/javascript" src="static/js/addClause.js"></script>
   		<script type="text/javascript">
   			$(document).ready(function() {
   				getDateInfo();
   			});
   			function getDateInfo(){
   				$.ajax({
   					type : "post",
   					async : false,
   					url : "to_getOneBenefits",
   					data : {benefitId:$("#benefitId").val()},
   					success : function(data) {
   						data = eval("(" +data+")");
   						renderDateInfo(data.data);
   					},
   					error:function(){
   						alert("信息获取失败");
   					}
   				});
   			}
   			
   			function renderDateInfo(data){
   				if(data.hasOwnProperty("benefitId")){
   					$("#benefitsName").val(data.benefitsName);
   					$("#start").val(data.startTime);
   					$("#end").val(data.endTime);
   					$("#fee").val(data.feeStr);
   					
   					if (data.type == 0) {
   						$("#guding").prop("checked",true);
   						$("#benefitMoney").val(data.benefitMoneyStr);
   						$(".change-money").html("优惠金额");
   						$(".change-unit").html("元");
   					}else {
   						$("#baifenbi").prop("checked",true);
   						$("#benefitMoney").val(data.benefitPercentStr);
   						$(".change-money").html("优惠百分比");
   						$(".change-unit").html("%");
   					}
   					
   					$("#caption").val(data.caption);
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
            <div class="discount-clause" style="padding-bottom:4px;">
                <p style="font-size:14px;color:#0c95db;"><a href="to_pro_index" style="font-size:14px;color:#0c95db;">首页</a>&nbsp;-&nbsp;<a href="to_benefitsManage" style="font-size:14px;color:#0c95db;">价格优惠条款</a>&nbsp;-&nbsp;<a href="to_allBenefits" style="font-size:14px;color:#0c95db;">管理价格优惠条款</a>&nbsp;-&nbsp;<a href="###" style="font-size:14px;color:#0c95db;">修改价格优惠条款</a></p>
            </div>
            <div class="clause-content">
                <form action="to_changeBenefits" class="form-box" method="post" id="changeClauseVerify">
                    <div class="form-div">
                    	<input type="hidden" name="benefitId" value="${data }" id="benefitId">
                        <label  class="form-label" >优惠名称<b>*</b></label>
                        <input type="text" name="benefitsName" class="form-input" id="benefitsName"/>
                    </div>
                    <div class="form-div">
                        <label  class="form-label">起始时间</label>
                        <input class="laydate-icon icon-li form-input start-time" name="startTime" id="start" />
                    </div>
                    <div class="form-div">
                         <label  class="form-label">结束时间</label>
                         <input class="laydate-icon icon-li form-input end-time" name="endTime" id="end" />
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >缴纳的金额<b>*</b></label>
                        <input type="text" name="fee" class="form-input" id="fee"/>
                        <span>元</span>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >优惠方式<b>*</b></label>
                        <input type="radio" value="0" name="type" class="form-input fixed-amount" checked="checked" id="guding"/><span>固定金额</span>
                        <input type="radio" value="1" name="type" class="form-input percentage" id="baifenbi"/><span></span><span>百分比</span>
                    </div>
                    <div class="form-div">
                        <label  class="form-label change-money" >优惠金额<b>*</b></label>
                        <input type="text" name="benefitMoney" class="form-input" id="benefitMoney"/>
                        <span class="change-unit">元</span>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >说明</label>
                        <textarea name="caption" id="caption" cols="30" rows="5"></textarea>
                    </div>
                    <div class="btn">
                        <input type="submit" value="保存" id="update"/>
                    </div>
                </form>
            </div>
            <div style="height:10px;"></div>
        </div>    
    </body>
    <script type="text/javascript">
   
    //日期
    var start = {
          elem: '#start',
          format: 'YYYY-MM-DD hh:mm:ss',
          //min: laydate.now(), //设定最小日期为当前日期
          max: '2099-06-16 23:59:59', //最大日期
          istime: true,
          istoday: false,
          choose: function(datas){
             end.min = datas; //开始日选好后，重置结束日的最小日期
             end.start = datas //将结束日的初始值设定为开始日
          }
        };
    var end = {
      elem: '#end',
      format: 'YYYY-MM-DD hh:mm:ss',
      min: laydate.now(),
      max: '2099-06-16 23:59:59',
      istime: true,
      istoday: false,
      choose: function(datas){
        start.max = datas; //结束日选好后，重置开始日的最大日期
      }
    };
    laydate(start);
    laydate(end);

   
   </script>
</html>