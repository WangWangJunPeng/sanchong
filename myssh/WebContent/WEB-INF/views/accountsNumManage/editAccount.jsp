<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-成交业务</title>
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="static/css/editAccount.css" />
         <link rel="stylesheet" type="text/css" href="static/css/validation.css" /> 
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/validation.js"></script>
        <script type="text/javascript" src="static/js/editAccount.js"></script>
    </head>
    <body>
        <%@include file="../publicpage/publicHeader.jsp" %>
        <div class="edit-list">
            <div class="edit-account">
                <p style="font-size:14px;color:#0c95db;"><a href="to_pro_index" style="font-size:14px;color:#0c95db;">首页</a>&nbsp;-&nbsp;<a href="to_account_manager_page" style="font-size:14px;color:#0c95db;">账号管理</a>&nbsp;-&nbsp;修改账号</p>
            </div>
            <div class="edit-menu">
                <form action="account_manager_to_add_or_update" class="form-box" method="post" id="alertAccountVerify">
                    <div class="form-div">
                    	<input name="userId" type="hidden" value="${user.userId}"/>
                        <label class="form-label" >权限</label>
                        <c:if test="${user.roleId=='4'}">
                        	<input type="radio" name="rightSign" value="engineer" class="form-input" checked="checked" /><span>案场助理</span>
                        	<input type="radio" name="rightSign" value="agent" class="form-input"/><span>置业顾问</span>
                        </c:if>
                        <c:if test="${user.roleId=='3'}">
                        	<input type="radio" name="rightSign" value="engineer" class="form-input"/><span>案场助理</span>
                        	<input type="radio" name="rightSign" value="agent" class="form-input" checked="checked"/><span>置业顾问</span>
                        </c:if>
                    </div>
                    <div class="form-div">
                        <label  class="form-label">姓名<b>*</b></label>
                        <input type="text" name="userCaption" class="form-input length" value="${user.userCaption}"/>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >电话<b>*</b></label>
                        <input type="text" name="phone" class="form-input length" value="${user.phone}"/>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >身份证号码<b>*</b></label>
                        <input type="text" name="idCard" class="form-input length" value="${user.idCard}"/>
                    </div>
                    <div class="btn">
                        <input name="returnSign" type="button" data-value="0" value="提交后返回列表" onclick="addAccountForm(this)"/>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>