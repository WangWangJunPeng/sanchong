<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-成交业务</title>
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="static/css/accountManage.css" />
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/accountManage.js"></script> 
    </head>
    <body>
        <%@include file="../publicpage/publicHeader.jsp" %>
        <div class="account-list">
            <div class="account-manage">
                <p style="font-size:14px;color:#0c95db;"><a href="to_pro_index"  style="font-size:14px;color:#0c95db;">首页</a>&nbsp;-&nbsp;账号管理</p>
            </div>
            <div class="add-account">
                <a href="goto_accountsnum_manage_page" class="add-tag">新增</a>
                <form class="form-div">
	                <select id="selectStyleId">
	                    <option value="">所有状态</option>
	                    <option value="1">启用</option>
	                    <option value="3">禁用</option>
	                </select>
	                <input type="button" value="搜索" onclick="getSelectList()" />
                </form>
                <input id="hidUserId" type="hidden" value="${sessionScope.userInfo.userId}"/>
                <table cellpadding="8" id="accountManagerList">
                </table>
               
            </div>
             <div style="height:10px;"></div>
        </div>
        <div class="is-reset" style="display:none;">
            <p>是否需要重置？</p>
            <div class="btn">
                <input type="button" value="确定" class="sure-btn" />
                <input type="button" value="关闭" class="close-btn" />
            </div>     
        </div>
        <div class="is-stop" style="display:none;">
            <p>是否禁用？</p>
            <div class="btn">
                <input type="button" value="确定" class="sure-btn" />
                <input type="button" value="关闭" class="close-btn" />
            </div>
        </div>
        <div class="is-open" style="display:none;">
            <p>是否启用？</p>
            <div class="btn">
                <input type="button" value="确定" class="sure-btn" />
                <input type="button" value="关闭" class="close-btn" />
            </div>
        </div>
        <div class="is-cancel" style="display:none;">
            <p>是否删除？</p>
            <div class="btn">
                <input type="button" value="确定" class="sure-btn" />
                <input type="button" value="关闭" class="close-btn" />
            </div>
        </div>
    </body>
</html>