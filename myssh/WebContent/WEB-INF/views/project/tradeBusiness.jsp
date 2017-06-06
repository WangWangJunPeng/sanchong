<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-成交业务</title>
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="static/css/tradeBusiness.css" />
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/tradeBusiness.js"></script> 
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
        </div> -->
        <%@include file="publicHeader.jsp" %>
        <div class="business-list">
            <div class="btn-all">
                <button>购买申请</button>
                <button>定金到款</button>
                <button>签约确定</button>
            </div>
            <div class="btn-single" >
                <span class="every-span span-one">待审核的申请</span>
                <span class="every-span span-one">已审核的申请</span>
                <span class="every-span span-one">已拒绝的申请</span>
                <span class="every-span span-one">超期未打款的申请</span>
                <span class="every-span span-one">已撤销的申请</span>
                <div class="table-list">
                    <table id="t_waitCheck" cellpadding="8" class="table-one">
                    </table>

                    <table id="t_alreadyCheck" cellpadding="8" style="display:none;" class="table-one">
                    </table>

                    <table id="t_alreadyRefuse" cellpadding="8" style="display:none;" class="table-one">
                    </table>

                    <table id="t_outTimeNotPlayMoney" cellpadding="8" style="display:none;" class="table-one">
                    </table>
                    <table id="t_backout" cellpadding="8" style="display:none;" class="table-one">
                    </table>
                </div>
            </div>
            <div class="btn-single" style="display:none;">
                <span class="every-span span-two">未确认到款</span>
                <span class="every-span span-two">已确认的到款</span>
                <div class="table-list">
                    <table id="t_notEnterGetBargain" cellpadding="8" class="table-two">
                    </table>

                     <table id="t_alreadyEnterGetBargain" cellpadding="8" class="table-two" style="display:none;" >
                    </table>
                </div>
            </div>
            <div class="btn-single" style="display:none;">
                <span class="every-span span-three">未确认的签约</span>
                <span class="every-span span-three">已确认的签约</span>
                <div class="table-list">
                    <table id="t_notEnterSign" cellpadding="8" class="table-three">
                    </table>

                    <table id="t_alreadyEnterSign" cellpadding="8" class="table-three" style="display:none;">
                    </table>
                </div>
            </div>
        </div>
        <div class="look-reject" style="display:none;">
            <div class="sure-reject">
                <h1>拒绝</h1>
            </div>
            <p>钱太少了</p>
        </div>
        <div class="payment" style="display:none;">
            <div class="sure-pay">
                <h1>确认到款</h1>
            </div>
            <p>是否确认已收到中介的汇款？</p>
            <div class="sure-btn">
                <button>确认</button>
            </div>
        </div>
    </body>
</html>