<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心</title>
        
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="static/css/purchaseApply.css" />
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/purchaseApply.js"></script> 
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
        </div> -->
        <%@include file="publicHeader.jsp" %>
        <div class="content-list">
        	<input id="houseNum" type="hidden" value="${houseNum}"/>
            <div class="purchase-apply">
                <p><a href="to_pro_index" style="font-size:12px;color:#0c95db;">首页</a>&nbsp;-&nbsp;<a href="to_trade_business_page" style="font-size:12px;color:#0c95db;">成交业务</a>&nbsp;-&nbsp;查看购买申请</p>
            </div>
            <div class="house-msg" style="padding-bottom:20px;">
                <p>房源信息</p>
                <table cellpadding="5">
                    <tr>
                        <th>房号</th>
                        <th>户型</th>
                        <th>建筑面积</th>
                        <th>使用面积</th>
                        <th>列表价</th>
                        <th>底价</th>
                        <th>中介供货价</th>
                    </tr>
                    <tr>
                        <td id="houseNo"></td>
                        <td id="houseTypeName"></td>
                        <td id="buileArea"></td>
                        <td id="usefulArea"></td>
                        <td id="listPrice"></td>
                        <td id="miniPrice"></td>
                        <td id="mediPrice"></td>
                    </tr>
                </table>
                <p class="buy-list" id="applyList">申请购买列表</p>
               <!--  <table cellpadding="5" id="applyBuyList">
                </table> -->
            </div>
        </div>
        <div class="audit-pop" style="display:none">
        	<div class="delete" onclick="yin()"><img src="static/images/shan.png"></img></div>
            <div class="sure-audit">
                <h1>确认审核</h1>
                <input id="checkRecordNo" type="hidden"/>
                <input id="checkReson" type="hidden" value="同意"/>
            </div>
            <p>是否确认审核此购买申请？</p>
            <button onclick="checkBuyApply()">审核</button>
        </div>
        <div class="reject-pop" style="display:none">
        	<div class="delete" onclick="yin()"><img src="static/images/shan.png"></img></div>
            <div class="sure-reject">
                <h1>拒绝</h1> 
            </div>
            <p>请输入拒绝的理由</p>
            <form action="refuse_buy_apply_date" class="form-box" method="post">
            	<input id="reRecordNo" type="hidden" name="recordNo"/>
                <textarea name="reason" id="" cols="30" rows="10"></textarea>
                <div class="reject-btn">
                    <input type="submit" value="拒绝" />
                </div>                                       
            </form>       
        </div>
    </body>
</html>