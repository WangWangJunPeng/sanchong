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
			getProjectReportForms();
			bindClick(); 
			//省市区联动
			 //省市区ajax请求
           $("#province").change(function(){
            			var pId = $("#province").val();
            			if(pId == ""){
            				$("#market").empty();
            				$("<option value=''>请选择</option>").appendTo($("#market"))
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
        							 }
        						  });
            			}
            		})
            		/* function changeMarket(){
            			var pId = $("#market").val();
            			//alert(pId);
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
            		} */
            		
            		/* $("#market").change(function(){
            			changeMarket();
            		}) */
			
		});
		
		 function bindClick(){
	        	$("#b1").click(function(){
	        		window.location.href = "system_poroject_report";	//全部状态的门店	
	        	});
	        	$("#b2").click(function(){
	        		window.location.href = "system_shop_report"; //只显示申请中的门店	
	        	});
	        	$("#b3").click(function(){
	        		window.location.href = "system_medi_report";	//只显示审核通过的门店			
	        	});
	        }
		
		//获取签到认购等数据
		function getProjectReportForms(){
			var city = "";
			if($('#province').val() != ""){
				city = $('#province').val() + "-" + $('#market').val();
			}
			$.ajax({
				type : "post",
				async : false,
				url : "system_project_report_forms",
				data : {
					startTime : $("#start").val(),
					endTime : $("#end").val(),
					city : city
				},
				success : function(data){
					fillPorjectInfo(data);
				},
			error : function(){
				alert("请求失败")
			}
			});
		}
		
		function fillPorjectInfo(data){
			var s = "<tr><td>项目</td><td>房源数</td><td>外场成交数</td><td>内场成交数含带客成交数</td><td>带看佣金比例</td><td>分销佣金比例</td></tr>";
			var index = 1;
			$.each(data, function(v, o){
				s += '<tr><td>' + o.project+ '</td>';
				s += '<td>' + o.houseCount + '</td>';
				s += '<td>' + o.outsideCount + '</td>';
				s += '<td>' + o.insideCount + '</td>';
				s += '<td>' + o.daikanMoney +' %' +  '</td>';
				s += '<td>' + o.fenxiaoMoney +' %'+'</td></tr>';
				index += 1;
			});
			if (data.length > 0) {
				$("#countInfo").html(s); /*当服务器有数据传送过来,将所有的元素都添加到id为systemCountInfo中*/
			} else {
				$("#countInfo")
						.html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
			}
			
		}
		
		function searchCount(){
			getProjectReportForms();
		}
	</script>
</head>
<body>







	<%@include file="public/systempublicpage.jsp" %>
	<div class="box">
		<div class="sure-bill">
			<p><a href="system_index">首页</a>&nbsp;-&nbsp;项目报表</p>
		</div>
		<div class="bill-content">
			<div class="table-list">
				<div class="form-label">
	            	<button style="ext-decoration: none; background-color:#66C6BA;display:inline-block;width:100px;height:33px;line-height: 30px;font-size: 15px;color:#EEEEEE;background-color:#0c95db;border-radius: 6px;margin-left: 20px; margin-top:10px" id="b1"  value="">项目报表</button>
	            	<button style="ext-decoration: none;display:inline-block;width:100px;height:33px;line-height: 30px;font-size: 15px;color:#666666;background-color:#e0e0e0;border-radius: 6px;margin-left: 20px; margin-top:10px" id="b2" value="0">门店报表</button>
	            	<button style="ext-decoration: none;display:inline-block;width:100px;height:33px;line-height: 30px;font-size: 15px;color:#666666;background-color:#e0e0e0;border-radius: 6px;margin-left: 20px; margin-top:10px" id="b3"  value="1">经纪人报表</button>
	            </div>
			</div>
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