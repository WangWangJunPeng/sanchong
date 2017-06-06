<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-首页</title>
        
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="static/css/home.css" />  
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/home.js"></script>
    </head>
    <body>
    	<%@include file="publicHeader.jsp" %>
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
        <div class="box">
        	<p style="padding-top:18px;margin-left:7px;font-size:14px;color:#0c95db;margin-bottom:4px;">首页</p>
            <div class="todo-taday">
                <h1>今日待办</h1>
                <div class="btn">
                  <div class="confirm same-border ">
                      <p class="num" id="enterBuy"></p>
                      <p class="sur">认购确认</p>
                  </div>
                  <div class="confirm ">
                      <p class="num" id="getMoney"></p>
                      <p class="sur">到款确认</p>
                  </div>
                  <div class="confirm ">
                      <p class="num" id="waitSign"></p>
                      <p class="sur">待签约中</p>
                  </div>
                  <div class="confirm ">
                      <p class="num" id="waitCash"></p>
                      <p class="sur">待结佣中</p>
                  </div>
                </div>
                <div class="table-list">
                  <table cellpadding="8" class="table-gather" id="t_enterBuy">
                  </table>  

                   <table  cellpadding="8"  style="display:none;" class="table-gather" id="t_getMoney">
                  </table>

                  <table cellpadding="8" style="display:none;" class="table-gather" id="t_waitSign">
                  </table> 

                   <table cellpadding="8" style="display:none;" class="table-gather" id="t_waitCash">
                  </table>       
                </div>             
            </div>
            <div class="field-taday">
              <h1>今日案场</h1>
              <div class="btn">
                  <div class="con">
                      <p class="num" id="todayVisit"></p>
                      <p class="sur">今日接访</p>
                  </div>
                  <div class="con">
                      <p class="num" id="todayGuide"></p>
                      <p class="sur">今日带看</p>
                  </div>
                  <div class="con">
                      <p class="num" id="todayRecord"></p>
                      <p class="sur">今日备案</p>
                  </div>
                  <div class="con">
                      <p class="num" id="todayEnterBuy"></p>
                      <p class="sur">今日认购</p>
                  </div>
                  <div class="con">
                      <p class="num" id="todaySign"></p>
                      <p class="sur">今日签约</p>
                  </div>
                </div>
            </div> 
            <div style="height:20px;"></div>
        </div>
            
    </body>
</html>