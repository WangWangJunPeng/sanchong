<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function(){
		getMapList();
	});
	function getMapList(){
		$.ajax({
			url:"getProAndShopInfo",
			dataType:"json",//服务器返回的数据类型
			type : "post",
			//async : false,
			success:function(data){
				console.log(data.pro);
				console.log(data.shop);
				console.log("入住数量:"+(data.shop.length-data.noInCount));
				console.log("未入住量"+data.noInCount);
			},
			error:function(){
				alert("信息获取失败");
			}
		});
	}
</script>
</head>
<body>
	
</body>
</html>