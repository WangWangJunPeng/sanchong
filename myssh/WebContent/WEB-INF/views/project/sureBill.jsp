<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-对账单</title>       
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="static/css/sureBill.css" /> 
        <link rel="stylesheet" type="text/css" href="static/lib/laydate/need/laydate.css" /> 
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/lib/laydate/laydate.js"></script>
        <script type="text/javascript" src="static/js/sureBill.js"></script>
        
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
               <li><a href="">对账单</a></li>
               <li><a href="">账号管理</a></li>
            </ul>
            <a href="###" class="welcome">欢迎您，某某某</a>
        </div>  -->
        <%@include file="publicHeader.jsp" %>
        <div class="box">
            <div class="sure-bill" style="padding-bottom:4px;">
                <p style="font-size:14px;color:#0c95db;"><a href="to_pro_index" style="font-size:14px;color:#0c95db;">首页</a>&nbsp;-&nbsp;对账单</p>
            </div>
            <div class="bill-content">
                <form>
                    <input type="text" class="laydate-icon icon-li" placeholder="起始时间" name="startTime" id="start" //>
                    <span class="">&nbsp;-&nbsp;</span>
                    <input type="text" class="laydate-icon icon-li" placeholder="结束时间" name="deliveryTime" id="end"/>
                    <input type="button" value="搜索" onclick="searchBills()"/>
                </form>
                <div class="start-end">
                    <p>时间：<span class="start-time"></span>至<span class="end-time"></span></p>
                </div>
                <div class="table-list">
                    <table cellpadding="8" id="billListInfo">
                    </table>
                </div>
            </div>
            <div style="width:10px;height:5px;"></div>
        </div>
        <div class="sure-pop" style="display:none;">
                <div class="sure-commission">
                    <h1>确定佣金结款</h1>
                </div>
                <p>是否确认支付佣金？</p>
                 <form action="agent_check_bill_list_enter" method="post">
                 	<input id="sureHidrecordNo" name="recordNo" type="hidden"/>
                 	<input id="enterSign" name="enterSign" type="hidden"/>
                    <textarea name="desc" id="reason" cols="30" rows="8"></textarea>
                    <div class="btn">
                        <input type="submit" value="确定" class="sub-popOne" />
                        <input type="button" value="关闭" class="close-popOne" onclick="payClosePop()"/>
                    </div>
                </form>
        </div>
        <div class="cancel-pop" style="display:none;">
                <div class="cancel-commission">
                    <h1>取消佣金结款</h1>
                </div>
                <p>请输入取消原因<span class="error-warning"></span></p>
                <form action="agent_check_bill_list_cancel" method="post">
                    <input id="cancelHidrecordNo" name="recordNo" type="hidden"/>
                 	<input id="cancelSign" name="cancelSign" type="hidden"/>
                    <textarea name="desc" id="reasonOne" cols="30" rows="8"></textarea>
                    <div class="btn">
                        <input type="submit" value="确定" class="sub-pop" />
                        <input type="button" value="关闭" class="close-pop" onclick="cancelPayClosePop()"/>
                    </div>
                </form>
            
        </div>
        <!--  <div class="angency-pop" style="display:none;">
                <div class="cancel-commission">
                <h1>取消佣金结款</h1>
                </div>
                <p>请输入取消原因<span class="error-warning"></span></p>
                <form action="">
                    <textarea name="" id="reasonTwo" cols="30" rows="8"></textarea>
                    <div class="btn">
                        <input type="submit" value="确定" class="sub-pop" />
                        <input type="button" value="关闭" class="close-pop" />
                    </div>
                </form>
            
        </div>         -->
    </body>  
    <script type="text/javascript">
        var start = {
              elem: '#start',
              format: 'YYYY-MM-DD hh:mm:ss',
              // min: laydate.now(), //设定最小日期为当前日期
              max: '2099-06-16', //最大日期
              istime: true,
              istoday: false,
              choose: function(datas){
                 end.min = datas; //开始日选好后，重置结束日的最小日期
                 end.start = datas //将结束日的初始值设定为开始日
                 
                $(".start-time").html($("#start").val());
              }
            };
        var end = {
          elem: '#end',
          format: 'YYYY-MM-DD hh:mm:ss',
          // min: laydate.now(),
          max: '2099-06-16',
          istime: true,
          istoday: false,
          choose: function(datas){
            start.max = datas; //结束日选好后，重置开始日的最大日期
            $(".end-time").html($("#end").val());
          }
        };
        laydate(start);
        laydate(end);
    </script> 
</html>