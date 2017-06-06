<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link rel="icon" href="static/images/titleLogo.png" />
<title>案场助理个人中心-房源管理</title>
<link rel="stylesheet" type="text/css" href="static/css/houseManage.css" />
<link rel="stylesheet" type="text/css" href="static/css/reset.css" />
<!-- <link rel="stylesheet" href="static/css/jqpagination.css" /> -->
<!--  <link rel="stylesheet" type="text/css" href="static/css/paging.css" /> -->
<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>

<!-- <script type="text/javascript" src="static/js/query.js"></script>
        <script type="text/javascript" src="static/js/paging.js"></script> -->
<script type="text/javascript" src="static/js/houseManage.js"></script>
<!--  <script type="text/javascript" src="static/js/jquery.jqpagination.js"></script> -->
<!-- 翻页插件 -->
<link rel="stylesheet" href="static/css/layui.css" media="all" />
<script type="text/javascript" src="static/js/layer.js"></script>
<script type="text/javascript" src="static/layui/plugins/layui/layui.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		getAllHousesInfo();
		getHouseTypeMenus();
		//初始化翻页信息
		toPage();
	});

	//分页参数设置
	var startAllAppoint = 0;
	var limitAllAppoint = 15;
	var currentPageAllAppoint = 1;
	var totalPageAllAppoint = 0;
	function getAllHousesInfo() {
		$.ajax({
			type : "post",
			async : false,
			url : "selectHouse",
			data : {
				start : startAllAppoint,
				limit : limitAllAppoint,
				houseTypeId : $("#htMenu").val(),
				houseStatus : $("#houseStatus").val(),
				houseKind : $("#houseKind").val()
			}, //$('#myForm').serialize(),
			success : function(data) {
				data = eval("(" + data + ")");
				fillHousesInfo(data.root);
				startAllAppoint = data.currentResult;
				totalPageAllAppoint = data.totalPage;
				checkBoxSelect();

			}
		});
	}
	function toPage(){
   		
   		layui.use(['laypage','layer'], function() {
			var layer = layui.layer,
				laypage = layui.laypage;
			//调用分页
			  laypage({
			    cont: 'paged'
			    ,pages: totalPageAllAppoint //得到总页数
			    ,curr: currentPageAllAppoint
			    ,skip: true
			    ,jump: function(obj, first){
			    	currentPageAllAppoint = obj.curr;
			    	startAllAppoint = (obj.curr-1)*limitAllAppoint;
			    	getAllHousesInfo();
			      if(!first){ //一定要加此判断，否则初始时会无限刷新
			          //location.href = '?page='+obj.curr;
			        }
			    }
			  });
			
			
		});
   	};
	function selectAllHousesInfo() {
		getAllHousesInfo();
		currentPageAllAppoint = 1;
		toPage();
	}

	function fillHousesInfo(data) {
		var s = '<tr><th><input type="checkbox" name="allChoose" id="allChoose" />';
		s += '</th><th>房号</th><th>类型</th><th>楼栋号</th><th>房型</th><th>建筑面积</th>';
		s += '<th>使用面积</th><th><span class="price">列表价(元)</span><span class="sort"></span>';
		s += '</th><th><span class="price">供货价(元)</span><span class="sort"></span></th>';
		s += '<th><span class="price">底价(元)</span><span class="sort"></span><th>发布时间</th><th>房源状态</th><th>操作</th></tr>';
		$
				.each(
						data,
						function(v, o) {
							s += '<tr><td><input type="checkbox" class="chooseSingle" name="chooseOne" value="'+o.houseNum+'"/></td>';
							s += '<td>' + o.houseNo + '</td>';
							//(房源类型，0:公寓、1:排屋、2:独栋、3:商住两用、4:办公室、5:酒店式公寓、6:商铺、7:车位、8:车库、9:储藏室)
							if (o.houseKind == 0) {
								s += '<td>公寓</td>';
							} else if (o.houseKind == 1) {
								s += '<td>排屋</td>';
							} else if (o.houseKind == 2) {
								s += '<td>独栋</td>';
							} else if (o.houseKind == 3) {
								s += '<td>商住两用</td>';
							} else if (o.houseKind == 4) {
								s += '<td>办公室</td>';
							} else if (o.houseKind == 5) {
								s += '<td>酒店式公寓</td>';
							} else if (o.houseKind == 6) {
								s += '<td>商铺</td>';
							} else if (o.houseKind == 7) {
								s += '<td>车位</td>';
							} else if (o.houseKind == 8) {
								s += '<td>车库</td>';
							} else if (o.houseKind == 9) {
								s += '<td>储藏室</td>';
							}
							s += '<td>' + o.buildingNo + '</td>';
							s += '<td>' + o.houseStyle + '</td>';
							s += '<td>' + o.buildArea + '㎡</td>';
							s += '<td>' + o.usefulArea + '㎡</td>';
							s += ' <td>' + o.listPrice + '</td>';
							s += '<td>' + o.shopPrice + '</td>';
							s += '<td>' + o.minimumPrice + '</td>';
							s += '<td>' + o.shelvTime + '</td>';
							if (o.houseStatus == 0) {
								//(房源状态，0：初始；1：上架；2：删除；3：销控；4：订购；5：签约)
								s += '<td>初始</td>';
							} else if (o.houseStatus == 1) {
								s += '<td>上架</td>';
							} else if (o.houseStatus == 2) {
								s += '<td>删除</td>';
							} else if (o.houseStatus == 3) {
								s += '<td>销控</td>';
							} else if (o.houseStatus == 4) {
								s += '<td>订购</td>';
							} else if (o.houseStatus == 5) {
								s += '<td>签约</td>';
							}
							s += '<td><a href="to_update_house_info?hNum='
									+ o.houseNum
									+ '"><img src="static/images/pen.png"  alt="" /></a>';
							s += '<a href="delete_curren_house_info?hNum='
									+ o.houseNum
									+ '"><img src="static/images/can.png" alt="" /></a></td></tr> ';
						});

		if (data.length > 0) {
			$("#housesInfo").html(s);
		} else {
			$("#housesInfo")
					.html(
							"<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
		}
	}
	//户型菜单
	function getHouseTypeMenus() {
		$.ajax({
			type : "post",
			async : false,
			url : "upAndDown_menu_list_house_type",
			success : function(data) {
				data = eval("(" + data + ")");
				initHouseType(data.root);
			}
		});
	}
	function initHouseType(data) {
		$.each(data, function(rowNum, o) {
			//console.log(o);
			$("#htNext").after('<option value="'+o+'">' + o + '</option>');
		});
	}
</script>
</head>
<body>
	<%@include file="../publicpage/publicHeader.jsp"%>
	<div class="container">
		<div class="home-houseManage">
			<p style="font-size: 14px; color: #0c95db;">
				<a href="to_pro_index" style="font-size: 12px; color: #0c95db;">首页</a>&nbsp;-&nbsp;房源管理
			</p>
		</div>
		<div class="house-resource">
			<div class="resource-btn">
				<a href="to_add_house_manage_info">新增房源信息</a>
				<button class="btn2">批量删除</button>
				<button class="btn3">导入</button>
				<button class="btnIn">导出房源信息</button>
			</div>
			<div class="house-search">
				<form id="myForm" action="">
					<select name="houseTypeId" id="htMenu">
						<option value="" id="htNext">选择房型</option>
					</select>
					<select name="houseStatus" id="houseStatus">
						<option value="">房源状态</option>
						<option value="0">初始</option>
						<option value="1">上架</option>
						<option value="2">删除</option>
						<option value="3">销控</option>
						<option value="4">订购</option>
						<option value="5">签约</option>
					</select> <select name="houseKind" id="houseKind">
						<option value="">房源类型</option>
						<option value="0">公寓</option>
						<option value="1">排屋</option>
						<option value="2">独栋</option>
						<option value="3">商住两用</option>
						<option value="4">办公室</option>
						<option value="5">酒店式公寓</option>
						<option value="6">商铺</option>
						<option value="7">车位</option>
						<option value="8">车库</option>
						<option value="9">储藏室</option>
					</select> <input type="button" value="搜索" class="btn-search"
						onclick="selectAllHousesInfo()" />
					<!-- <span class="reset">重置</span> -->
				</form>
			</div>
			<div class="house-list">
				<table cellpadding="8" cellspacing="0" id="housesInfo">
				</table>
			</div>
		</div>
		<div class="admin-main">
			<div class="admin-table-page">
				<div id="paged" class="page"></div>
			</div>		
		</div>
		
		<div style="height: 50px;"></div>
	</div>
</body>

</html>