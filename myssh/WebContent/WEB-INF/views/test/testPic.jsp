<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="static/js/echarts.js"></script>

</head>
<body>
 <div id="main" style="width: 1000px;height:400px;"></div>

<script>

var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
$.get("json_test").done(function(obj){
	 var datas = JSON.parse(obj);
	//console.log(datas.series[1]); 
	var dataNam=[];
	for(var i=0;i<datas.series.length;i++){
		dataNam.push(datas.series[i].name);
	}
	myChart.setOption({
        title: {
            text: datas.title
        },
        tooltip: {},
        legend: {
            data:dataNam
        },
        xAxis: {
            data: datas.xName
        },
        yAxis: {},
        series: datas.series
    }); 
})
	
		
	
        	



</script>	
</body>
</html>