<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-成交业务</title>
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="static/css/addAccount.css" />
        <link rel="stylesheet" type="text/css" href="static/css/validation.css" /> 
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        
        <script type="text/javascript" src="static/js/validation.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/addAccount.js"></script>
    </head>
    <body>
        <%@include file="../publicpage/publicHeader.jsp" %>
        <div class="new-list">
            <div class="new-account">
                <p style="font-size:14px;color:#0c95db;"><a href="to_pro_index" style="font-size:14px;color:#0c95db;">首页</a>&nbsp;-&nbsp;新增账号</p>
            </div>
            <div class="add-menu">
                <form class="form-box" method="post" id="addCountVerify">
                    <div class="form-div">
                        <label  class="form-label" >权限</label>
                        <input type="radio" name="rightSign" value="engineer" class="form-input" checked="checked" /><span>案场助理</span>
                        <input type="radio" name="rightSign" value="agent" class="form-input"/><span>置业顾问</span>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >姓名<b>*</b></label>
                        <input type="text" name="userCaption" class="form-input length" />
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >电话<b>*</b></label>
                        <input type="text" name="phone" id="phone" class="form-input length" /><a href="#" style="color:#2772DB;font-size: 10px;" id="phone_ok"></a><a href="#" style="color:#ff0000;font-size: 10px;" id="phone_exist"></a>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >身份证号码<b>*</b></label>
                        <input type="text" name="idCard" class="form-input length" />
                    </div>
                    <div class="btn">
                        <input name="returnSign" id="forNext" type="button" value="提交后继续新增下一个" data-value="1" onclick="addAccountForm(this)"/>
                        <input name="returnSign" id="forReturn" type="button" value="提交后返回列表" data-value="0" onclick="addAccountForm(this)"/>
                    </div>
                </form>
            </div>
        </div>
        <script type="text/javascript" src="static/js/layer.js"></script>
    </body>
</html>