<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link  rel="icon" href="static/images/titleLogo.png"  />
<title>平台运管管理中心-成交业务</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/commend.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/index.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/houseManage.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/lib/laydate/need/laydate.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css/accountManage.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		getPartnerRelList();
	});

	function setPartnerInfo(data) {
		//console.log(data);
		var s = "<tr><th rowspan='2'>账户名称</th><th rowspan='2'>合伙人姓名</th><th colspan='4'>关联案场</th><th colspan='4'>关联门店</th><th rowspan='2'>操作</th></tr><tr><th>案场名称</th>"+
            		"<th>关系建立时间</th><th>关系结束时间</th><th>有效年限</th><th>门店名称</th><th>关系建立时间</th><th>关系结束时间</th><th>有效年限</th></tr>";
		$.each(data,function(v, o) {
			var max=0;
			var projects = o.projects;
			var shops = o.shops;
			max = projects.length;
			if(max<shops.length){
				max = shops.length;
			}
			
			s += '<tr><td rowspan="'+max+'">' + o.phone+ '</td>';
			s += '<td rowspan="'+max+'">' + o.partnerName + '</td>';
			if(projects.length>0){
				s += '<td>' + projects[0].projectName + '</td>';
				s += '<td>' + projects[0].createTime + '</td>';
				s += '<td>' + projects[0].removeTime + '</td>';
				s += '<td>' + projects[0].validity + '</td>';
			}else{
				s += '<td></td>';
				s += '<td></td>';
				s += '<td></td>';
				s += '<td></td>';
			}
			if(shops.length>0){
				s += '<td>' + shops[0].shopName + '</td>';
				s += '<td>' + shops[0].createTime + '</td>';
				s += '<td>' + shops[0].removeTime + '</td>';
				s += '<td>' + shops[0].validity + '</td>';
			}else{
				s += '<td></td>';
				s += '<td></td>';
				s += '<td></td>';
				s += '<td></td>';
			}
			s += '<td rowspan="'+max+'"><span class="open-pop" data-value=""><a href="query_partner?userId='+o.partnerId+'">修改</a></span></td>';
			for(var i=1;i<max;i++){
				if(projects.length>i){
					s += '<tr><td>' + projects[i].projectName + '</td>';
					s += '<td>' + projects[i].createTime + '</td>';
					s += '<td>' + projects[i].removeTime + '</td>';
					s += '<td>' + projects[i].validity + '</td>';
				}else{
					s += '<tr><td></td>';
					s += '<td></td>';
					s += '<td></td>';
					s += '<td></td>';
				}
				if(shops.length>i){
					s += '<td>' + shops[i].shopName + '</td>';
					s += '<td>' + shops[i].createTime + '</td>';
					s += '<td>' + shops[i].removeTime + '</td>';
					s += '<td>' + shops[i].validity + '</td></tr>';
				}else{
					s += '<td></td>';
					s += '<td></td>';
					s += '<td></td>';
					s += '<td></td></tr>';
				}
			}
		});

		if (data.length > 0) {
			$("#accountManagerList").html(s);
		} else {
			$("#accountManagerList").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
		}
	}

	

	function getPartnerRelList() {
		$.ajax({
			type : "post",
			url : "partner_relation_list",
			data : {
			},
			success : function(data) {
				data = eval("(" + data + ")");
				setPartnerInfo(data.root);
			}
		});
	}

	
</script>
</head>
<body>
	<%@include file="public/systempublicpage.jsp"%>
	<div class="account-list">
		<div class="account-manage">
			<p><a href="system_index">首页</a>&nbsp;-&nbsp;合伙人管理</p>
		</div>
		<div class="add-account">
			<a href="<%=request.getContextPath()%>/system_toAddPartner" class="add-tag">新增合伙人</a> <a href="<%=request.getContextPath()%>/toAdPartner_Relation"
				class="add-tag">新建合伙人关联</a>
			<form class="form-div">
				<select id="selectStyleId">
					<option value="">所有状态</option>
					<option value="1">启用</option>
					<option value="3">禁用</option>
				</select> 
			</form>
		
			<table id="accountManagerList">
				
                </table>
            </div>
        </div>
       
    </body>
</html>