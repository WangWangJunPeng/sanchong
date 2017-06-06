<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-价格优惠条款</title>       
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
            <div class="sure-bill">
                <p>首页&nbsp;-&nbsp;平台佣金列表</p>
            </div>
            <div class="house-list">
                   <table cellpadding="8" cellspacing="0" id="housesInfo">
                   	<thead>
                      <tr>
                        <th>项目名</th>
                        <th>佣金结算方式</th>
                        <th>佣金金额/比率</th>
                        <th>结算最低成交金额</th>
                        <th>结算最高成交金额</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${data }" var="list">
                    	<tr>
	                        <td>${list.projectName }</td>
	                        <c:if test="${list.rewardType == 0}"><td>固定方式</td></c:if>
	                        <c:if test="${list.rewardType == 1}"><td>比率方式</td></c:if>
	                        <td><c:if test="${list.rewardType == 0}">${list.reward }&nbsp;元</c:if>
	                        <c:if test="${list.rewardType == 1}">${list.reward }&nbsp;%</c:if></td>
	                        <td>${list.minRewarMoney }元</td>
	                        <td>${list.MaxRewarMoney }元</td>
                      </tr>
                    </c:forEach>
                    </tbody>
                   </table>
                </div>
    </body>  
</html>