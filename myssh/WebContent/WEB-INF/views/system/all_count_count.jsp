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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/sureBill.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/commend.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/lib/laydate/need/laydate.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/laydate/laydate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/sureBill.js"></script>
</head>
<body>
	<script type="text/javascript">
		/*当页面加载完成时调用*/
		$(document).ready(function() {
			getAllSystemData();
			getAllVisitPersonData();
			getAllMeidData()
			
			
			
		});
		/*ajax同步发请求（当页面加载的时候）*/
		function getAllSystemData() {
			$.ajax({
				type : "post", /*为post方式*/
				async : false, /*开启同步请求，true为异步请求*/
				url : "all_system_count", /*url为发请求的url，利用Controller@RequestMapping进行拦截*/
				success : function(data) { /*当请求成功之后回调*/
					data = eval("(" + data + ")");
					fillSystemInfo(data.root); /*获取json串,并传给这个方法*/
				}
			});
		}

		function fillSystemInfo(data) {
			var s = "<tr><td>项目总数</td><td>置业顾问总数</td><td>房源总数</td><td>门店总数</td><td>经纪人总数</td><td>合伙人总数</td></tr>";
			$.each(data, function(v, o) { /*o为json的数据（后台传过来的）*/
				s += '<tr><td>' + o.projectCount + '</td>';
				s += '<td>' + o.adviserCount + '</td>';
				s += '<td>' + o.houseCount + '</td>';
				s += '<td>' + o.shopCount + '</td>';
				s += '<td>' + o.agentCount + '</td>';
				s += '<td>' + o.partnerCount + '</td></tr>';
			});

			if (data.length > 0) {
				$("#systemCountInfo").html(s); /*当服务器有数据传送过来,将所有的元素都添加到id为systemCountInfo中*/
			} else {
				$("#systemCountInfo")
						.html(
								"<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
			}
		}
		//获取签到认购等数据
		function getAllVisitPersonData(){
			$.ajax({
				type : "post",
				async : false,
				url : "all_visit_person_count",
				data : {
					startTime : $("#start").val(),
					endTime : $("#end").val(),
				},
				success : function(data){
					data = eval("(" + data + ")");
					fillVisitInfo(data.root);
				},
			error : function(){
				alert("失败")
			}
			});
		}
		
		function fillVisitInfo(data){
			var s = "<tr><td>到访</td><td>登记</td><td>认购</td><td>签约</td><td>到款</td><td>中介客户贡献度</td><td>中介客户成交贡献度</td></tr>";
			$.each(data, function(v, o){
				s += '<tr><td>' + o.visiterNum+ '/' +o.visitCount+ '组' + '</td>';
				s += '<td>' + o.writeCount + '</td>';
				s += '<td>' + '申请：'+ o.applyCount +'<br/>'+ '同意：'+ o.agreeCount +'<br/>'+ '否决：'+ o.votedCount + '</td>';
				s += '<td>' + o.writedCount + '</td>';
				s += '<td>' + o.getMoneyCount + '</td>';
				s += '<td>' + o.mediumPerc +' %'+'</td>';
				s += '<td>' + o.mediumTurnove +' %' +  '</td></tr>';
				
			});
			if (data.length > 0) {
				$("#countInfo").html(s); /*当服务器有数据传送过来,将所有的元素都添加到id为systemCountInfo中*/
			} else {
				$("#countInfo")
						.html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
			}
			
		}
		
		function searchCount(){
			getAllVisitPersonData();
			getAllMeidData();
		}
		
		
		//获取签到认购等数据
		function getAllMeidData(){
			$.ajax({
				type : "post",
				async : false,
				url : "all_meid_data_count",
				data : {
					startTime : $("#start").val(),
					endTime : $("#end").val(),
				},
				success : function(data){
					data = eval("(" + data + ")");
					fillMeidInfo(data.root);
				},
			error : function(){
				alert("失败")
			}
			});
		}
		
		function fillMeidInfo(data){
			var s = "<tr><td>备案</td><td>确认</td><td>否决</td><td>认购</td><td>签约成功率</td></tr>";
			$.each(data, function(v, o){
				s += '<tr><td>' + o.applyCount + '</td>';
				s += '<td>' + o.confirmCount + '</td>';
				s += '<td>' + o.vetoCount + '</td>';
				s += '<td>' + o.loanCount + '</td>';
				s += '<td>' + o.writeSuccessCount +' %'+'</td>';
				
			});
			if (data.length > 0) {
				$("#meidCountInfo").html(s); /*当服务器有数据传送过来,将所有的元素都添加到id为systemCountInfo中*/
			} else {
				$("#meidCountInfo")
						.html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
			}
			
		}
		
		
		//页面倒计时并刷新 
		var intDiff = parseInt(600);//倒计时总秒数量
		function timer(intDiff){
		    window.setInterval(function(){
		    var day=0,
		        hour=0,
		        minute=0,
		        second=0;//时间默认值       
		    if(intDiff > 0){
		        day = Math.floor(intDiff / (60 * 60 * 24));
		        hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
		        minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
		        second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
		    }else{  //当时间耗尽，刷新页面 
		    	 window.location.reload();
		    }
		    if (minute <= 9) minute = '0' + minute;
		    if (second <= 9) second = '0' + second;
		   
		    $(".timeShow").html('本页数据还剩下<font>'+minute+'分'+second+'</font>秒刷新,刷新间隔时间: 10 分钟');
		    intDiff--;
		    
		    }, 1000);
		    
		} 
		$(function(){
		    timer(intDiff);
		});
		
		
		
	</script>
</head>
<body>



	<%@include file="public/systempublicpage.jsp" %>
	<div class="box">
		<div class="sure-bill">
			<p>首页</p>
		</div>
		<div class="bill-content">
			<div class="table-list">
				<span class="timeShow"></span>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<table id="systemCountInfo" style="table-layout: fixed;">

				</table>
			</div>
		</div>

		<div class="bill-content">
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
			<strong>项目</strong>
				<table cellpadding="8" id="countInfo">
				</table>
			</div>
			
				<div class="table-list">
				<strong>中介</strong>
					<table id="meidCountInfo" style="table-layout: fixed;">
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