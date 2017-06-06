<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link  rel="icon" href="static/images/titleLogo.png"  />
<title>平台运管管理中心-案场客户管理中心</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/reset.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/lib/laydate/need/laydate.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/commend.css">
<link rel="stylesheet" type="text/css" href="static/css/accountManage.css" />
<link rel="stylesheet" href="static/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/jquery.dialogbox.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/laydate/laydate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/sureBill.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.dialogBox.js"></script>
<script type="text/javascript" src="static/js/layer.js"></script>
<script type="text/javascript" src="static/layui/plugins/layui/layui.js"></script>
</head>
<style>


.mask{background-color:#333;opacity:0.4;filter:alpha(opacity=50);position:absolute;left:0;top:0;z-index:10;}
 table{width:96%;border:1px solid #a6a6a6;margin:0 auto;margin-top: 5px;background-color:#ffffff;}
 table th{font-size: 14px;color:#333333;border:1px solid #a6a6a6;}
 table td{font-size: 12px;color:#666666;text-align: center;border:1px solid #a6a6a6;}
 table td span{cursor: pointer;}
</style>
<body>
	<script type="text/javascript">
	$(document).ready(function(){
		
		$(".add-tag").click(function(){
			
			$('#new_cu_list').children().remove();
			$('#update_cu_list').children().remove();
			$('#newnew').attr("display","none;");
			$('#upup').attr("display","none;");
			
	        layer.open({
	              title:['客户信息导入'],  
	              type: 1,
	              btn:['确定'],
	              btnAlign:'c',
	              skin: 'layui-layer-rim', //加上边框
	              area: ['400px', '200px'], //宽高
	              content: '<div><form id="excelInfo" enctype ="multipart/form-data"><div style="top: 50%;position: absolute;left: 72px;"><input type="file" id="myFile" name="file"/></div><div><select name="projectId" id="projectSelect"  style="position: absolute;left: 18%;top: 10px; height: 30px;"><option value="">请选择案场</option></select></div></form></div>',
	              yes:function(obj){
	                var result = $("#myFile").val();
	                var formData = new FormData($( "#myFile" )[0]);
	                var point = result.lastIndexOf("."); 
	                var type = result.substr(point);
	                 if ($("#myFile").val()!="" && type == ".xls") {
	                    layer.confirm('确认要将文件中的信息导入到客户列表中吗？', {
	                    title:['操作确认'],
	                    btn: ['确定','取消'],
	                    btnAlign:'c'
	                    }, function(){
	                        layer.msg('正在上传', {icon:1,time:1},function(){
	                        	layer.closeAll();
	                        	console.log(new FormData($("#excelInfo").serialize()));
	                			$.ajax({
	                				url:"to_input_customer_excell",
	                   				type:"post",
	                   				async : false,
	                				data:new FormData($("#excelInfo")[0]),
	                				processData: false,
	                			    contentType: false,
	                				success: function(data){
	                					 fillCustomerAddInfo(data.data.newCustomer);
	                				
	                					 fillCustomerUpdateInfo(data.data.updateCustomer);
	                					 
	                					 
	                				}
	                   			});
	                         }); 
	                        
	                    })
	                    
	                 }else{
	                    layer.alert("请正确上传文件");
	                 } 

	        }
	    })
	    getProjectMenu2();//获取案场
		});
		
		
		
	});

//将导入的客户信息显示出来
function fillCustomerAddInfo(data){
	 var sWidth=document.body.scrollWidth;
	    var sHeight=document.body.scrollHeight; 
	    //获取页面的可视区域高度和宽度
	    var wHeight=document.documentElement.clientHeight;
	    var oMask=document.createElement("div");
	        oMask.className="mask";
	        oMask.style.height=sHeight+"px";
	        oMask.style.width=sWidth+"px";
	        document.body.appendChild(oMask);  
	        $(".closeBtner").click(function(){
	        	$(".mask").remove();
	        	$(".listTotal").hide();
	        	window.location.reload();
	        })
	        $(".listTotal").show();
	var s = "<tr><td>来访时间</td><td>客户姓名</td><td>客户电话</td><tr>";
	$.each(data, function(v,o){
		
		s += '<tr><td>' + o.lastTime + '</td>';
		s += '<td>' + o.projectCustomerName + '</td>';
		s += '<td>' + o.projectCustomerPhone + '</td>';
		/* s += '<td>' + o.ownerUserId + '</td>'; */
		s += '</tr>';
		
	});
	console.log(data.length)
	if(data.length > 0){
		$('#new_cu_list').html(s);
		$('#newnew').show();
		$('#isNull').remove();
	}else{
		$('#isNull').show();
	}
}

function fillCustomerUpdateInfo(data){
	 var sWidth=document.body.scrollWidth;
	    var sHeight=document.body.scrollHeight; 
	    //获取页面的可视区域高度和宽度
	    var wHeight=document.documentElement.clientHeight;
	    var oMask=document.createElement("div");
	        oMask.className="mask";
	        oMask.style.height=sHeight+"px";
	        oMask.style.width=sWidth+"px";
	        document.body.appendChild(oMask);
	        $(".closeBtner").click(function(){
	        	$(".mask").remove();
	        	$(".listTotal").hide();
	        })
	        $(".listTotal").show();
	var s = "<tr><td>来访时间</td><td>客户姓名</td><td>客户电话</td><tr>";
	$.each(data, function(v,o){
		
		s += '<tr><td>' + o.lastTime + '</td>';
		s += '<td>' + o.projectCustomerName + '</td>';
		s += '<td>' + o.projectCustomerPhone + '</td>';
		/* s += '<td>' + o.ownerUserId + '</td>'; */
		s += '</tr>';
		
	});
	console.log(data.length)
	if(data.length > 0){
		$('#update_cu_list').html(s);
		$('#upup').show();
		$('#isNull').remove();
	}
}
	


//获取案场菜单
function getProjectMenu2(){
	$.ajax({
		type : "post",
		url : "get_project_menu",
		async : false,
		dataType:"json",
		success : function(data) {
			//console.log(data);
			setProjectMenu2(data);
		}
	});
}
function setProjectMenu2(data){
	$.each(data,function(v, o) {
		$("#projectSelect").append('<option value="'+o.proId+'">'+o.proName+'</option>');
	});
}

	</script>
</head>
	<body>
		<%@include file="/WEB-INF/views/system/public/systempublicpage.jsp"%>
        <div class="account-list">
            <div class="account-manage">
                <p><a href="system_index">首页</a>&nbsp;-&nbsp;客户管理中心</p>
            </div>
            <div class="add-account">
                <a href="#" class="add-tag">导入</a>
                
               
            </div>
             <div style="height:10px;"></div>
        </div>
        
	<div class="listTotal"
		style="width: 600px;height:700px;position: absolute; margin: auto; top: 200px; left: 0; right: 0; z-index: 100; display: none; background: #fff;">

		<p id="isNull" style="dispaly: none; margin-left: 30%;">更新失败，请检查客户信息是否有改动</p>
		<div style="overflow:auto;height:600px;">
			<div style="display: none;" id="newnew">
				<p style="margin-left: 42%;">新增客户</p>
				<table cellpadding="8" id="new_cu_list">
				</table>
			</div>
			<div style="display: none;" id="upup">
				<p style="margin-left: 42%;">更新客户</p>
				<table cellpadding="8" id="update_cu_list">
				</table>
			</div>
		</div>
			<button class="closeBtner" style="width: 70px;height: 35px; background: #169bd5; position: absolute;left: 43%;bottom: 20px;color:#fff;">确认</button>
	</div>
	<div id="simple-dialogBox"></div>
</body>
</html>