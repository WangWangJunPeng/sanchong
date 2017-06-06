<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<title>门店管理后台</title>
	<!-- <link rel="stylesheet" type="text/css" href="static/css/reset.css">
	<link rel="stylesheet" type="text/css" href="static/css/commend.css">
	<link rel="stylesheet" type="text/css" href="static/css/search.css">
	<link rel="stylesheet" type="text/css" href="static/css/paging.css"> -->
	<link rel="stylesheet" href="static/layui/plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="static/layui/css/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	<link rel="stylesheet" href="static/layui/css/table.css" />
	<!-- <link rel="stylesheet" type="text/css" href="static/layui/common/global.css">
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="static/layui/common/personal.css">
	<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css"> -->
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript" src="static/layui/plugins/layui/layui.js"></script>
	<!-- <script type="text/javascript" src="static/layui/js/province.js"></script>
	<script type="text/javascript" src="static/layui/js/data.js"></script> -->
	<!-- <script src="static/js/echarts.js" type="text/javascript" ></script> -->
	<!-- <script type="text/javascript" src="static/js/citySelect2.js"></script> -->
	
	<script type="text/javascript">
	
	 
	
	    
	    
	</script>

</head>
<body>
	
	<div class="admin-main">
	
				<blockquote class="layui-elem-quote">
				<form class="layui-form" action="" id="myForm">
				<div class="layui-form-item">
				
	  				<select name="sheng" class="prov" id="prov4" lay-ignore style="width:160px;height:38px;padding-left:5px;border:1px solid #e6e6e6;color:#666;">
	  				</select>
	    			<select name="shi" class="city" id="city4" lay-ignore style="width:160px;height:38px;padding-left:5px;border:1px solid #e6e6e6;color:#666;">
	    			</select>
	        		<select name="qu" class="dist" id="area4" lay-ignore style="width:160px;height:38px;padding-left:5px;border:1px solid #e6e6e6;color:#666;">
	        		</select>
	    		
				
					<!-- <div class="layui-input-inline">
						<select name="sheng" id="prov4" lay-filter="sheng">
							<option value="" id="pFirst" selected>--</option>
						</select>
					</div>
					<div class="layui-input-inline">
						<select name="shi" id="city4" lay-filter="shi">
							
							<option value="" id="cFirst" selected>--</option>
						</select>
					</div>
					<div class="layui-input-inline">
						<select name="qu" id="area4" lay-filter="qu">
							
							<option value="" id="aFirst" selected>--</option>
						</select>
					</div>  -->
					<input type="text" lay-verify="title"   autocomplete="off" id="projectName" name="projectName" placeholder="请输入案场名称" class="layui-input" style="width:15%;display: inline-block;">
					<button class="layui-btn"  type="button"  onclick="getSearchProject()"><i class="layui-icon">&#xe615;</i></button>
						<input type="checkbox" name="isYouhui"  title="优惠案场" checked="checked" id="youhui">
						<input type="checkbox" name="isDaiKan"  title="支持带看" checked="checked" id="daikan">
				</div>
				</form>
				<form class="layui-form" action="oneHouse"  method="post" onsubmit="return empty()">
					<input type="text" id="houseNum" name="houseNum" lay-verify="title" autocomplete="off" placeholder="请输入APP上查看到的房源ID" class="layui-input" style="width:20%;display: inline-block;">
					<button class="layui-btn" type="submit" >搜索房源</button>
				</form>
				</blockquote>
				<fieldset class="layui-elem-field">
					<legend>案场</legend>
					<div class="layui-field-box layui-form">
						<table class="layui-table admin-table" id="pInfo">
							
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
	
	

/* 	var defaults = {
			  s1:'sheng',
			  s2:'shi',
			  s3:'qu',
			  v1:null,
			  v2:null,
			  v3:null
			  
			}; */
	var proValue;
	var proHtml;
	var cityValue;
	var cityHtml;
	var areaValue;
	var areaHtml; 
		 layui.use(['form', 'layedit', 'laydate'], function() {
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
				
			}); 

	 $(function(){
					getCityInfo();
					getProvince();
					
		 	$("#prov4").children().each(function(){
				if($(this).val() == proValue){
					$(this).attr("selected","selected");
				console.log($(this).val());
				changeCity();
				}
				
			})
					
			$("#city4").children().each(function(){
				
					if($(this).val() == cityValue){
				changeMarket();
						$(this).attr("selected","selected");
				console.log($(this).val());
					}
					
				})
			$("#area4").children().each(function(){
					if($(this).val() == areaValue){
						$(this).attr("selected","selected");
				console.log($(this).val());
					}
				})
					
					$("#prov4").change(function() {
						changeCity();
					});


					

					
					$("#city4").change(function() {
						changeMarket();
					})

					


					
					getProjectInfo();
					
					
					
				});
	 
	 
	
	 $.ajaxSetup({  
		    async : false  
		});  
	 
	 
	//省市区ajax请求
		function getProvince() {
			$.ajax({
				type : "post",
				async : false,
				url : "get_all_prov",
				success : function(data) {
					var data = eval('(' + data + ')');
					addProvinceName(data.root);
					//changeCity();
				},

			});
		}
		function addProvinceName(data) {
			$.each(data, function(v, o) {
				var s = "<option value="+o.cityId+">" + o.cityName + "</option>";
				$(s).appendTo($("#prov4"));
				
			});

		}
		function changeCity() {
			var pId = $("#prov4").val();
			$("#city4").empty();
			$.post("menu_list_city_area", {
				"shengOrShi" : pId
			}, function(data) {
				var data = eval('(' + data + ')');
				for (var i = 0; i < data.root.length; i++) {
					$("<option value='"+data.root[i].cityId+"'>"+ data.root[i].cityName + "</option> ").appendTo($("#city4"));
				}
				//changeMarket();
				

			});
		}
		
		function changeMarket() {
			var pId = $("#city4").val();
			var areaId = $("#area4").val();	//当前用户所在的区号
			//alert(pId);
			$("#area4").empty();
			$.post("menu_list_city_area", {
				"shengOrShi" : pId
			}, function(data) {
				var data = eval('(' + data + ')');
				for (var i = 0; i < data.root.length; i++) {
					$("<option value='"+data.root[i].cityId+"'>"+ data.root[i].cityName+ "</option> ").appendTo($("#area4"));
				}
					/* $("#area4").prepend("<option value='"+""+"'>"+ "请选择" + "</option> ");	//获取当前用户所在区并显示在select第一个option中  */
				$("#area4 option:first").prop("selected", 'selected');		//设置强制显示第一个option 
			});
		
		}
	 

		function getProjectInfo() {
			$.ajax({
				type : "post",
				async : false,
				url : "findProjectHouse",
				data : {
					sheng : $('#prov4').val(),
					shi : $('#city4').val(),
					qu : $('#area4').val(),
					projectName : $('#projectName').val(),
					isYouhui : $("input[name='isYouhui']:checked").val(),
					isDaiKan : $("input[name='isDaiKan']:checked").val()
				},
				success : function(data) {
					data = eval("(" + data + ")");
					getFindProjectHouse(data.root);
				}
			});
		}
		function getCityInfo() {
			$.ajax({
				type : "post",
				async : false,
				url : "get_city_name_by_parent_id",
				success : function(data) {
					var s1 = "<option value="+data.provId+">" + data.provName
							+ "</option>";
					var s2 = "<option value="+data.cityId+">" + data.cityName
							+ "</option>";
					var s3 = "<option value="+data.areaId+">" + data.areaName
							+ "</option>";
							
					proValue = data.provId;
					cityValue = data.cityId;
					areaValue = data.areaId;
							
							 
					/* $(s1).appendTo($("#prov4"));
					$(s2).appendTo($("#city4"));
					$(s3).appendTo($("#area4"));
					console.log(s3) */
					/* $("#area4").prepend("<option value='"+""+"'>"+ "请选择" + "</option> ");
				 	$("#prov4 option:first").prop("selected", 'selected');
					$("#city4 option:first").prop("selected", 'selected');
					$("#area4 option:first").prop("selected", 'selected');  */
				
					
				}
			});
		}
		function getFindProjectHouse(data) {
			var s = '<tr><th>案场名称</th><th>开发商</th><th>均价(元)</th><th>支持带看</th>';
			s += '<th>支持异地</th><th>快速结佣</th><th>带看佣金</th><th>分销佣金</th><th>优惠信息</th><th>操作</th></tr>';

			$.each(data, function(v, o) {
				s += '<tr><td>' + o.projectName + '</td><td>' + o.developer
						+ '</td><td>' + o.averagePrice + '</td>';
				if (o.isDaiKan == 0) {
					s += '<td>否</td>';
				} else if (o.isDaiKan == 1) {
					s += '<td>是</td>';
				} else if (o.isDaiKan == null) {
					s += '<td></td>';
				}
				if (o.isYiDi == 0) {
					s += '<td>否</td>';
				} else if (o.isYiDi == 1) {
					s += '<td>是</td>';
				} else if (o.isYiDi == null) {
					s += '<td></td>';
				}
				if (o.isFast == 0) {
					s += '<td>否</td>';
				} else if (o.isFast == 1) {
					s += '<td>是</td>';
				} else if (o.isFast == null) {
					s += '<td></td>';
				}
				s += '<td>' + o.daiKanMoney + '</td><td>' + o.fenXiaoMoney
						+ '</td><td>' + o.information + '</td>';
				s += '<td><a href="zhongjieHouseList?projectId=' + o.projectId
						+ '" style="color:#0c95db;">可售房源</a></td></tr>';
			});
			if (data.length > 0) {
				$('#pInfo').html(s);
			} else {
				$("#pInfo")
						.html(
								"<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
			}
		}

		function getSearchProject() {
			getProjectInfo();
		}
		function empty(){
			if($("#houseNum").val()==""){
				alert("请输入搜索条件");
				return false;
			}
		}
		
	
		
		/* layui.use(['jquery','layer','element','laypage'],function(){
		      window.jQuery = window.$ = layui.jquery;
		      window.layer = layui.layer;
	          var element = layui.element(),
	              laypage = layui.laypage;

	            
	          laypage({
						cont: 'page',
						pages: 10 //总页数
							,
						groups: 5 //连续显示分页数
							,
						jump: function(obj, first) {
							//得到了当前页，用于向服务端请求对应数据
							var curr = obj.curr;
							if(!first) {
								//layer.msg('第 '+ obj.curr +' 页');
							}
						}
					});

	    });  */
		
	</script>

	<!-- <header id="" class="index-header">
		<nav>
			<ul>
				<li><a href="index.html" title="" class="index-logo">中介门店管理后台</a></li>
				<li><a  href="index.html" title="">首页</a></li>
				<li><a  class="active" href="" title="">房源搜索</a></li>
				<li><a href="" title="">经纪人管理</a></li>
				<li><a href="" title="">客户管理</a></li>
				<li><a href="" title="">对账单</a></li>
				<li><a href="" title="">业务</a></li>
				<li><a href="" title="">欢迎您：<span>User</span></a></li>
			</ul>
		</nav>
	</header>/header -->
	<%-- <%@include file="../publicpage/shoppublicpage.jsp" %> --%>
	
	<!-- <div class="contain" style="width:960px;margin:0 auto;background:#eaeaea;">
		<p style="font-size:12px;color:#0c95db;padding-top:10px;margin-left:10px;">首页-房源搜索</p>
		<div style="background:#fff;padding-top:10px;width:99%;margin:10px auto;padding-bottom:10px;">
		<div class="btn-group"  >
			案场搜索
			<form action="" method="post" id="myForm" style="margin-left:10px;">
				市区联动
				<div id="city_4">
	  				<select name="sheng" class="prov" id="prov4" style="height:20px;width:70px">
	  				</select>
	    			<select name="shi" class="city" id="city4" style="height:20px;width:70px">
	    			</select>
	        		<select name="qu" class="dist" id="area4" style="height:20px;width:70px">
	        		</select>
	    		</div>
				<input id="projectName" name="projectName" class="btn-text btn-text-1" placeholder="输入案场名称"></input>
				<button class="btn-search" type="button" onclick="getSearchProject()">搜索案场</button>
				<input name="isYouhui" type="checkbox" checked="checked" id="youhui">优惠案场</input>
				<input name="isDaiKan" type="checkbox" checked="checked" id="daikan">支持带看</input>
			</form>
			<form action="oneHouse" target="_blank" method="post" onsubmit="return empty()">
				<input name="houseNum" class="btn-text btn-text-2" placeholder="请输入APP上查看到的房源ID"  id="houseNum"></input>
				<button class="btn-search" type="submit" >搜索房源</button>
			</form>
			
		</div>
		<table class="message" id="pInfo" cellpadding="8">
			
		</table>

		</div>

	<div style="height:10px;"></div>
	</div>


	<script type="text/javascript" src="static/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="static/js/query.js"></script>
	
	<script>
			// 市区二级联动
		/* 	var selectVal = new CitySelect({
				data   : data,
				provId : "#prov4",
				cityId : '#city4',
				areaId : '#area4'
			}); */
			
			    
//			var selectVa2 = new CitySelect({
//				data   : data,
//				provId : "#prov5",
//				cityId : '#city5',
//				areaId : '#area5',
//				isSelect: false
//			});
			

			// 分页功能
			
			
	</script> -->
</body>
</html>