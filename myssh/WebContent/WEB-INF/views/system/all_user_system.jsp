<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link  rel="icon" href="static/images/titleLogo.png"  />
<title>案场助理个人中心-价格优惠条款</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/reset.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/lib/laydate/need/laydate.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/commend.css">
<link rel="stylesheet" type="text/css" href="static/css/accountManage.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/jquery.dialogbox.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/laydate/laydate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/sureBill.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/usersInfoSystem.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.dialogBox.js"></script>
</head>
<body>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".add-tag").click(function(){
			$('#simple-dialogBox').dialogBox({
				width: 150,
				height: 100,
				title: '提示',
				autoHide: true,
				time: 1500,
				hasClose: true,
				hasMask: true,
				content: "该功能暂时未开放"
			});
			/* alert("该功能暂时未开放"); */
		});
		
	});
	</script>
</head>
	<body>
		<%@include file="/WEB-INF/views/system/public/systempublicpage.jsp"%>
        <div class="account-list">
            <div class="account-manage">
                <p><a href="system_index">首页</a>&nbsp;-&nbsp;账号管理</p>
            </div>
            <div class="add-account">
                <a href="#" class="add-tag">新增</a>
                <form class="form-div">
	                <select id="status">
	                    <option value="">所有状态</option>
	                    <option value="1">启用</option>
	                    <option value="2">禁用</option>
	                </select>
	                <input type="button" id="search" value="搜索"/>
                </form>
                <table cellpadding="8" id="system_user_info">
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
        <div id="simple-dialogBox"></div>
	</body>
</html>