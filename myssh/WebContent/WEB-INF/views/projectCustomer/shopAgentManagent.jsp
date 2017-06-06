<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link  rel="icon" href="static/images/titleLogo.png"  />
    <title>门店管理后台</title>
    <link rel="stylesheet" href="static/layui/plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="static/layui/css/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	<link rel="stylesheet" href="static/layui/css/table.css" />
   <!--  <link rel="stylesheet" type="text/css" href="static/css/reset.css">
    <link rel="stylesheet" type="text/css" href="static/css/commend.css">-->
    <link rel="stylesheet" type="text/css" href="static/css/shopAgentManagent.css"> 
    <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
     <script type="text/javascript" src="static/js/shopAgentManagent.js"></script>
     <script type="text/javascript" src="static/layui/plugins/layui/layui.js"></script>
   
</head>
<body>
	<div class="admin-main">
	
	
				<blockquote class="layui-elem-quote">
				<form class="layui-form" action="" >
				<div class="layui-form-item">
				<div class="layui-input-inline">
					<select class="prov" id="selectRole">
				         <option value="">请选择权限</option>
				         <option value="">所有权限</option>
	                     <option value="shopowner">店长</option>
	                     <option value="medi">经纪人</option>
		  			</select>
			    </div>
				<div class="layui-input-inline">
				<select class="prov" id="enOrDisable">
			         <option value="">请选择状态</option>
			         <option value="">所有状态</option>
                     <option value="1">启用</option>
                     <option value="3">禁用</option>
	  				</select>
			    </div>
			    <button class="layui-btn" type="button" onclick="selectShoperManager()">搜索</button>
				</div>
				</form>
				<input id="hidUserId" type="hidden" value="${sessionScope.userInfo.userId}"/>
				</blockquote>
				<fieldset class="layui-elem-field">
					<legend>经纪人列表</legend>
					<div class="layui-field-box layui-form">
						<table class="layui-table admin-table" id="shopsManeger">
							
						</table>
					</div>
				</fieldset>
				<div class="admin-table-page">
					<div id="paged" class="page">
					</div>
				</div>
			</div>
	<script type="text/javascript" src="static/js/query.js"></script>
	<script type="text/javascript">
		layui.use(['form', 'layedit','layer', 'laydate'], function() {
			var form = layui.form(),
				layer = layui.layer,
				layedit = layui.layedit,
				laydate = layui.laydate;
	
			//创建一个编辑器
			var editIndex = layedit.build('LAY_demo_editor');
			//自定义验证规则
			 form.verify({
				title: function(value) {
					if(value.length < 5) {
						return '标题至少得5个字符啊';
					}
				},
				pass: [/(.+){6,12}$/, '密码必须6到12位'],
				content: function(value) {
					layedit.sync(editIndex);
				}
			});
	
			//监听提交
			form.on('submit(demo1)', function(data) {
				layer.alert(JSON.stringify(data.field), {
					title: '最终的提交信息'
				})
				return false;
			});
			
			//重置用户
			$(document).on("click",'.reset-pop',function(){
			  var userId = $(this).data("value");
				layer.msg('是否需要重置？', {
				  time: 0 //不自动关闭
				  ,btn: ['确认', '取消']
				  ,yes: function(index){
					  $.ajax({
							type : "post",
							url : "account_manager_to_update_userInfo",
							data : {userId : userId,doSign : "reset"},
							success : function(data) {
								
								getShopUsersInfo();
							}
						});
				    layer.close(index);
				    layer.msg('重置成功', {
					      icon: 6
					      ,time: 1000
					    });
				    
				  }
				});
			});
			
			//禁用用户
			$(document).on("click",".stop-pop",function(){
				var userId = $(this).data("value");
				layer.msg('是否禁用？', {
				  time: 0 //不自动关闭
				  ,btn: ['确认', '取消']
				  ,yes: function(index){
					  $.ajax({
							type : "post",
							url : "account_manager_to_update_userInfo",
							data : {userId : userId, userStatus: 3,doSign : "enableOrdisable"},
							success : function(data) {
								getShopUsersInfo();
							}
						});
				    layer.close(index);
				    layer.msg('禁用成功', {
					      icon: 6
					      ,time: 1000
					    });
				    
				  }
				});
			});
			//启用用户
			$(document).on("click",".open-pop",function(){
				var userId = $(this).data("value");
				layer.msg('是否启用？', {
				  time: 0 //不自动关闭
				  ,btn: ['确认', '取消']
				  ,yes: function(index){
					  $.ajax({
							type : "post",
							url : "account_manager_to_update_userInfo",
							data : {userId : userId,userStatus: 1,doSign : "enableOrdisable"},
							success : function(data) {
								getShopUsersInfo();
							}
						});
				    layer.close(index);
				    layer.msg('启用成功', {
					      icon: 6
					      ,time: 1000
					    });
				  }
				});
			});
			
			//删除用户
			$(document).on("click",".cancel-pop",function(){
				var userId = $(this).data("value");
				layer.msg('是否删除该账号？', {
				  time: 0 //不自动关闭
				  ,btn: ['确认', '取消']
				  ,yes: function(index){
					  $.ajax({
							type : "post",
							url : "account_manager_to_update_userInfo",
							data : {userId : userId, userStatus: 2, doSign : "delete"},
							success : function(data) {
								getShopUsersInfo();
							}
						});
				    layer.close(index);
				    layer.msg('删除成功', {
					      icon: 6
					      ,time: 1000
					    });
				  }
				});
			});
			
			
		});
	</script>
	  <%-- <%@include file="../publicpage/shoppublicpage.jsp" %> --%>
      <%-- <div class="container">
       
            <p style="padding-top:10px;margin-left:10px;font-size:12px;color:#0c95db;"><a href="to_store_index" style="font-size:12px;color:#0c95db;">首页</a>-经纪人管理</p>
       
        <div class="box" style="background-color: #ffffff;">
             <div class="add-account">
                <a href="goto_medi_manage_page" class="add-tag" style="text-decoration: none;">新增经纪人</a>
                <form action="" class="form-div">
                        <select id="selectRole">
                            <option value="">所有权限</option>
                            <option value="shopowner">店长</option>
                            <option value="medi">经纪人</option>
                        </select>
                        <select id="enOrDisable">
                            <option value="">所有状态</option>
                            <option value="1">启用</option>
                            <option value="3">禁用</option>
                        </select>
                        <input type="button" value="搜索" onclick="selectShoperManager()" />
                </form>
                <input id="hidUserId" type="hidden" value="${sessionScope.userInfo.userId}"/>
                <table cellpadding="8" id="shopsManeger">
                </table>
            </div>
            
        </div>
        <div style="height:20px;"></div>
    </div>--%>
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