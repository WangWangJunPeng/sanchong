<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-客户管理</title>
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="static/css/customerManagement.css" />
        <link rel="stylesheet" href="static/css/jqpagination.css" />
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/customerManagement.js"></script>
        <script type="text/javascript" src="static/js/jquery.jqpagination.js"></script>
    </head>
    <body>
        <%@include file="../publicpage/publicHeader.jsp" %>
        <div class="management-list">
            <div class="customer-manage">
                <p style="font-size:14px;color:#0c95db;"><a href="to_pro_index" style="font-size:14px;color:#0c95db;">首页</a>&nbsp;-&nbsp;客户管理</p>
            </div>
            <div class="add-customer">
                <!-- <a href="addCustomer.html"></a> -->
                <div class="user-search">
                    <form action="" class="form-div">
                        <input id="selectValue" type="text" placeholder="客户姓名，电话"/>
                        <input type="button" value="搜索" onclick="selectCustomers()"/>
                    </form>
                    <!-- <span>显示所有用户</span> -->
                    <span onclick="setPopBox()">设置选中客户的所属</span>
                </div>
                <table cellpadding="8" id="t_customerInfo">
                </table>
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
        <div class="set-belong" style="display:none">
            <div class="belong-title">
                <h1>设置客户所属</h1>
            </div>
            <p>将选中的客户重新分配给</p>
            <form action="update_pro_customers_info" class="form-div" method="post">
            	<input type="hidden" id="selectedCustomorId" name="proCursId"/>
                <select name="agentId" id="agentMenu">
                    <option value="请选择置业顾问" id="agNext">请选择置业顾问</option>
                </select>
                <div class="sure-back">
                    <input type="submit" value="确认返回" />
                </div>
            </form>
        </div>
    </body>
</html>