<%@ page import="com.javaweb.bean.Dept" %><%--
  Created by IntelliJ IDEA.
  User: 朱文龙
  Date: 2023/7/8
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
	Dept dept = (Dept)request.getAttribute("dept");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>详情</title>
	</head>
	<body>
		<h1>部门详情</h1>
		<hr>
                        部门编号：<%=dept.getDeptno()%><br>
                        部门名称：<%=dept.getName()%><br>
                        部门位置：<%=dept.getLoc()%><br>
		<input type="button" value="后退" onclick="window.history.back()"/>
	</body>
</html>

