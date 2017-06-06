<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link  rel="icon" href="static/images/titleLogo.png"  />
	<title>中介管理后台</title>
	<link rel="stylesheet" type="text/css" href="static/css/reset.css">
	<link rel="stylesheet" type="text/css" href="static/css/index.css">
	<script src="static/js/echarts.js" type="text/javascript" ></script>
</head>
<body>
	<header id="" class="index-header">
		<nav>
			<ul>
				<li><a href="index.html" title="" class="index-logo">中介管理后台</a></li>
				<li><a  class="active" href="index.html" title="">首页</a></li>
				<li><a href="search.html" title="">房源搜索</a></li>
				<li><a href="" title="">经纪人管理</a></li>
				<li><a href="" title="">客户管理</a></li>
				<li><a href="" title="">对账单</a></li>
				<li><a href="" title="">业务</a></li>
				<li>欢迎您：<span>${data.userCaption}</span></li>
			</ul>
		</nav>
	</header><!-- /header -->
	<!-- message -->
	<div class="contain">
		<nav class="message">
			<ul>
				<li><span>88</span><p>今日认购</p></li>
				<li><span>88</span><p>今日备案</p></li>
				<li><span>88</span><p>今日带看</p></li>
				<li><span>88</span><p>即将过期</p></li>
			</ul>
		</nav>
		<div class="tables">
			<table class="show">
				<thead>
					<tr>
						<th>姓名</th>
						<th>项目名称</th>
						<th>当前状态</th>
						<th>认购时间</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>张三</td>
						<td>绿城紫桂公寓</td>
						<td>等待价格审批</td>
						<td>2016-10-30 13:55</td>
					</tr>
					<tr>
						<td>张三</td>
						<td>绿城紫桂公寓</td>
						<td>等待价格审批</td>
						<td>2016-10-30 13:55</td>
					</tr>
				</tbody>
			</table>

			<table class="tab-pane">
				<thead>
					<tr>
						<th>姓名</th>
						<th>项目名称</th>
						<th>当前状态</th>
						<th>认购时间</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>张三</td>
						<td>绿城紫桂公寓</td>
						<td>等待价格审批</td>
						<td>2016-10-30 13:55</td>
					</tr>
					<tr>
						<td>张三</td>
						<td>绿城紫桂公寓</td>
						<td>等待价格审批</td>
						<td>2016-10-30 13:55</td>
					</tr>
				</tbody>
			</table>

			<table class="tab-pane">
				<thead>
					<tr>
						<th>姓名</th>
						<th>项目名称</th>
						<th>当前状态</th>
						<th>认购时间</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>张三</td>
						<td>绿城紫桂公寓</td>
						<td>等待价格审批</td>
						<td>2016-10-30 13:55</td>
					</tr>
					<tr>
						<td>张三</td>
						<td>绿城紫桂公寓</td>
						<td>等待价格审批</td>
						<td>2016-10-30 13:55</td>
					</tr>
				</tbody>
			</table>

			<table class="tab-pane">
				<thead>
					<tr>
						<th>姓名</th>
						<th>项目名称</th>
						<th>当前状态</th>
						<th>认购时间</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>张三</td>
						<td>绿城紫桂公寓</td>
						<td>等待价格审批</td>
						<td>2016-10-30 13:55</td>
					</tr>
					<tr>
						<td>张三</td>
						<td>绿城紫桂公寓</td>
						<td>等待价格审批</td>
						<td>2016-10-30 13:55</td>
					</tr>
				</tbody>
			</table>

		</div>

		
		<div id="main" style="width: 960px;height: 400px;"></div>
		



	<script type="text/javascript">
		// 基于准备好的dom，初始化echart实例
			var myChart = echarts.init(document.getElementById('main'));
		// 指定图表的配置项和数据
		var option = {
			title: {
				text:'归归'
			},
			tooltip:{},
			legend:{
				data:['销量']
			},
			xAxis: {
				data: [1,2,3,4,5,6,7,8,9,10,11,12]
			},
			yAxis:{

			},
			series:[{
				name:'销量',
				type:'line',
				data:[5,15,20,45,18,89],
			}]
		};
		//使用刚指定的配置项和数据显示图表
		myChart.setOption(option);
		// 处理点击事件并且跳转到相应的百度搜索页面
		myChart.on('click',function(params){
			window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));
		});
		</script>

	</div>
</body>
</html>