<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link  rel="icon" href="static/images/titleLogo.png"  />
<title>日志列表</title>
</head>
<body>
<h1>日志列表</h1>
<hr>
<ul>
<c:forEach items="${logsData}" var="logs">
	<li><a href="<%=request.getContextPath()%>/downloadLog?name=${logs}">${logs}</a></li>
</c:forEach>
</ul>

</body>
</html>