<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link  rel="icon" href="static/images/titleLogo.png"  />
	<title>修改经纪人</title>
	<link rel="stylesheet" href="static/layui/plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="static/layui/css/global.css" media="all">
	<!-- <link rel="stylesheet" type="text/css" href="static/css/reset.css">
	<link rel="stylesheet" type="text/css" href="static/css/commend.css">
	<link rel="stylesheet" type="text/css" href="static/css/shopAddAgent.css"> -->
	
	<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="static/layui/plugins/layui/layui.js"></script>
</head>
<body>

	<div class="admin-main">
	<span class="layui-breadcrumb">
	  <a href="to_medi_manager_page">经纪人管理</a>
	  <a><cite>修改经纪人</cite></a>
	</span>
	
		<form class="layui-form" action="medi_manager_to_add_or_update" method="post" id="createForm">
			<input name="userId" type="hidden" value="${user.userId}"/>
		  <div class="layui-form-item">
		    <label class="layui-form-label">经纪人类型</label>
		    <div class="layui-input-block">
		    	<c:if test="${user.roleId=='1'}">
		    	 <input type="radio" name="rightSign" id="kind1" value="medi" title="经纪人" checked="checked">
			     <input type="radio" name="rightSign" id="kind2" value="shopowner" title="店长">
	    	    </c:if>
		    	<c:if test="${user.roleId=='2'}">
		    	 <input type="radio" name="rightSign" id="kind1" value="medi" title="经纪人">
			     <input type="radio" name="rightSign" id="kind2" value="shopowner" title="店长" checked="checked">
	    	    </c:if>
		    </div>
		    <div class="layui-form-item">
	    	    <label class="layui-form-label">姓名</label>
			    <div class="layui-input-inline">
		    	    <input type="text" name="userCaption" value="${user.userCaption}" id="userCaption" lay-verify="title" autocomplete="off" placeholder="请输入姓名" class="layui-input">
			    </div>
		    </div>
		    <div class="layui-form-item">
	    	    <label class="layui-form-label">电话</label>
			    <div class="layui-input-inline">
		    	    <input type="text" name="phone" value="${user.phone}" id="phone" lay-verify="phone" autocomplete="off" placeholder="请输入手机号码" class="layui-input">
			    </div>
		    </div>
		    <div class="layui-form-item">
	    	    <label class="layui-form-label">身份证</label>
			    <div class="layui-input-inline">
		    	    <input type="text" name="idCard" value="${user.idCard}" id="id_card" lay-verify="identity" autocomplete="off" placeholder="请输入身份证号码" class="layui-input">
			    </div>
		    </div>
		    <div class="layui-form-item">
			    <div class="layui-input-block">
		    	    <button class="layui-btn" id="forSave" name="returnSign" lay-submit="" lay-filter="demo1">提交后返回列表</button>
			    </div>
		    </div>
		  </div>
		  </form>
	  </div>
	<script type="text/javascript">
	layui.use(['form', 'layedit', 'laydate','element'], function(){
		  var form = layui.form()
		  ,layer = layui.layer
		  ,layedit = layui.layedit
		  ,laydate = layui.laydate()
		  ,element = layui.element();
		  
		  //创建一个编辑器
		  var editIndex = layedit.build('LAY_demo_editor');
		 
		  //自定义验证规则
		  form.verify({
		    title: function(value){
		      if(value.length < 2){
		        return '标题至少得5个字符啊';
		      }
		    }
		    ,pass: [/(.+){6,12}$/, '密码必须6到12位']
		    ,content: function(value){
		      layedit.sync(editIndex);
		    }
		  });
		  
		  //监听指定开关
		  form.on('switch(switchTest)', function(data){
		    layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
		      offset: '6px'
		    });
		    layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
		  });
		  
		  //监听提交
		  /* form.on('submit(demo1)', function(data){
		    layer.alert(JSON.stringify(data.field), {
		      title: '最终的提交信息'
		    })
		    return false;
		  }); */
		  
		  
		});
	</script>



	<%-- <%@include file="../publicpage/shoppublicpage.jsp" %>
	
	<div class="contain">
		<div class="formMiddle">
			<form action="medi_manager_to_add_or_update" method="post" id="createForm">
				<input name="userId" type="hidden" value="${user.userId}"/>
				<section>
					<p>经纪人类型</p>
					<c:if test="${user.roleId=='1'}">
						<input type="radio" value="medi" id="kind1" name="rightSign" checked="checked">	
						<label for="kind1">经纪人</label>								
						<input type="radio" value="shopowner" id="kind2" name="rightSign">
						<label for="kind2">店长</label>
					</c:if>
					<c:if test="${user.roleId=='2'}">
						<input type="radio" value="medi" id="kind1" name="rightSign">	
						<label for="kind1">经纪人</label>								
						<input type="radio" value="shopowner" id="kind2" name="rightSign" checked="checked">
						<label for="kind2">店长</label>
					</c:if>
				</section>
				<section>
					<label for="name" style="margin-left:45px;">姓名<span>*</span></label>
					<input type="text" name="userCaption" value="${user.userCaption}" id="userCaption">
				</section>
				<section>
					<label for="phone" style="margin-left:45px;">电话<span>*</span></label>
					<input type="text" name="phone" value="${user.phone}" id="phone">
				</section>
				<section>
					<label for="id_card" style="margin-left:-10px;">身份证号码<span>*</span></label>
					<input type="text" name="idCard" value="${user.idCard}" id="id_card">
				</section>
				<input type="submit" id="forSave" name="returnSign" value="提交后返回列表">
				<!-- <button name="returnSign" type="submit" id="forReturn" style="margin-left:100px;">提交后返回经纪人列表</button> -->
			</form>
		</div>
	</div> --%>
</body>
</html>