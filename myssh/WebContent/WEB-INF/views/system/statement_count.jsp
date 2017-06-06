<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link  rel="icon" href="static/images/titleLogo.png"  />
<title>案场助理个人中心-价格优惠条款</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/reset.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/sureBill.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/commend.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/lib/laydate/need/laydate.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/laydate/laydate.js"></script>

</head>
<body>
	<script type="text/javascript">
	
	
		/*当页面加载完成时调用*/
		$(document).ready(function() {
			
		
			getProjectStatementForms();
			
			
			
			//省市区联动
			 //省市区ajax请求
            $("#province").change(function(){
            			var pId = $("#province").val();
            			if(pId == ""){
            				$("#market").empty();
            				$("#area").empty();
            				$("<option value=''>请选择</option>").appendTo($("#market"))
            				$("<option value=''>请选择</option>").appendTo($("#area"))
            			}else{
            			$("#market").empty();
            			$.post("menu_list_city_area",
        						{
        						    "shengOrShi":pId
        						  },
        						  function(data){  
        							 var data = eval('('+ data +')');
        							 for(var i=0;i<data.root.length;i++){
        								 $("<option value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option>").appendTo($("#market"))
        								 changeMarket()
        							 }
        							 
        						  });
            				
            			}
            		})
            function changeMarket(){
    			var pId = $("#market").val();
    			if(pId == ""){
    				$("#market").empty();
    				$("<option value=''>请选择</option>").appendTo($("#area"))
    			}else{
    			$("#area").empty();
    			$.post("menu_list_city_area",
						{
						    "shengOrShi":pId
						  },
						  function(data){  
							 var data = eval('('+ data +')');
							 for(var i=0;i<data.root.length;i++){
								 $("<option value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option> ").appendTo($("#area"));
							 }
						  });
    			}
    		}
    		
			
    		$("#market").change(function(){
    			changeMarket();
    		})
    		
    		
    		
		});
		
		
		function getProjectStatementForms(){
			var city = "";
			if($('#province').val() != ""){
				city = $('#province').val() + "-" + $('#market').val() + "-" +$('#area').val();
			}
			$.ajax({
				type : "post",
				async : false,
				url : "system_statement_count",
				data : {
					startTime : $("#start").val(),
					endTime : $("#end").val(),
					city : city,
				},
				success : function(data){
					fillProjectStatementInfo(data);
				},
			error : function(){
				alert("请求失败")
			}
			});
		}
		
		function fillProjectStatementInfo(data){
			var s = "<tr><td>项目</td><td>佣金类型</td><td>佣金金额</td><td>门店</td><td>经纪人</td><td>佣金来源</td><td>带看时间</td><td>签约时间</td><td>结款时间</td><td>确认到款<br>时间</td><td>结款<br>状态</td><td>操作</td></tr>";
			$.each(data, function(v, o){
				console.log(o);
				s += '<tr><td>' + o.projectName+ '</td>';
				
				if(o.moneyType == "0")
					s += '<td>分销佣金</td>';
					else if(o.moneyType == 1)
					s += '<td>带看佣金</td>';
					else
					s += '<td>-无-</td>';
					
				if(o.moneyType == 0)
					s += '<td>' + o.fenXiaoMoney + '</td>';
					else if(o.moneyType == 1)
					s += '<td>' + o.daiKanMoney + '</td>';
					else
					s += '<td>-无-</td>';	
					
				if(o.shopName != null && o.shopName != "")	
					s += '<td>' + o.shopName + '</td>';
					else
						s += '<td>-无-</td>';
				if(o.agentUserId != null && o.agentUserId != "")		
					s += '<td>' + o.agentUserId + '</td>';
					else
						s += '<td> -无- </td>';	
				s += '<td>' + o.houseInfo + '</td>';
				if(o.applyTime != null)
					s += '<td>' + o.applyTime.substring(0, 10) + '</td>';
					else
						s += '<td>***</td>';
				if(o.contractConfirmTime != null)
					s += '<td>' + o.contractConfirmTime.substring(0, 10) + '</td>';
					else
						s += '<td>***</td>';
				if(o.systemPayConfirmTime != null)
					s += '<td>' + o.systemPayConfirmTime.substring(0, 10) + '</td>';
					else
						s += '<td>***</td>';
				if(o.systemReceiveConfirmTime != null)
					s += '<td>' + o.systemReceiveConfirmTime.substring(0, 10) + '</td>';
					else
						s += '<td>***</td>';
				
				if(o.isSystemPayConfirm == null || o.isSystemPayConfirm == 0)
					s += '<td>未结款</td>';
				if(o.isSystemPayConfirm == 1)
					s += '<td>已结款</td>';
				if(o.isSystemPayConfirm == 2)
					s += '<td>已完成</td>';
				if(o.isSystemPayConfirm == 2)
					s += '<td><a href="edit_commission_statement?houseNum='+o.houseNum+'&isConfirm=0" style="background:#FF4545; width:70px; height:25px; line-height:25px; color:#ffffff; border:0; display: inline-block; border-radius:4px" id="callOff">取消到款</a></td></tr>';
					else
						s += '<td><a href="edit_commission_statement?houseNum='+o.houseNum+'&isConfirm=1" style="background:#0c95db;  width:70px; height:25px; line-height:25px; color:#ffffff; border:0; display: inline-block; border-radius:4px" id="callOn">确认到款</a></td></tr>';
			});
			if (data.length > 0) {
				$("#countInfo").html(s); 
			} else {
				$("#countInfo")
						.html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
			}
			
		}
		
		function searchCount(){
			getProjectStatementForms();
		}
	</script>
</head>
<body>

	<%@include file="public/systempublicpage.jsp" %>
	<div class="box">
		<div class="sure-bill">
			<p><a href="system_index">首页</a>&nbsp;-&nbsp;对账单</p>
		</div>

		<div class="bill-content">
			<select id="province" name="province" class="prov" style="margin-left:10px; margin-top:6px; height:23px; width:91px">
			<option value="">请选择</option>
			<c:forEach var="p" items="${provinces}">
				<option value="${p.cityId }">${p.cityName }</option>
			</c:forEach>
			</select> 
			<select id="market" name="market" class="city" style="margin-top:6px; height:23px; width:91px">
				<option value="">请选择</option>
			</select>
			 <select id="area" class="area" style="margin-top:6px; height:23px; width:91px">
			 	<option value="">请选择</option>
			 </select>
			<form>
				<input type="text" class="laydate-icon icon-li" placeholder="起始时间"
					name="startTime" id="start" /> <span class="">&nbsp;-&nbsp;</span>
				<input type="text" class="laydate-icon icon-li" placeholder="结束时间"
					name="deliveryTime" id="end" /> <input type="button" value="搜索"
					onclick="searchCount()" />
			</form>
			<div class="start-end">
				<p>
					时间：<span class="start-time"></span>至<span class="end-time"></span>
				</p>
			</div>
			<div class="table-list">
				<table cellpadding="8" id="countInfo">
				</table>
			</div>
		</div>
		<div style="width: 10px; height: 10px;"></div>
	</div>
	
	
</body>


<script type="text/javascript">
        var start = {
              elem: '#start',
              format: 'YYYY-MM-DD hh:mm:ss',
              // min: laydate.now(), //设定最小日期为当前日期
              max: laydate.now(), //最大日期
              istime: true,
              istoday: false,
              choose: function(datas){
                 end.min = datas; //开始日选好后，重置结束日的最小日期
                 end.start = datas //将结束日的初始值设定为开始日
                 
                $(".start-time").html($("#start").val());
              }
            };
        var end = {
          elem: '#end',
          format: 'YYYY-MM-DD hh:mm:ss',
          //min: laydate.now(),
          max: laydate.now(),
          istime: true,
          istoday: false,
          choose: function(datas){
            start.max = datas; //结束日选好后，重置开始日的最大日期
            $(".end-time").html($("#end").val());
          }
        };
        laydate(start);
        laydate(end);
    </script>

</html>