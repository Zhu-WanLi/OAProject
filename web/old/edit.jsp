<%@ page import="com.javaweb.bean.Dept" %><%--
  Created by IntelliJ IDEA.
  User: 朱文龙
  Date: 2023/7/8
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Dept dept = (Dept)request.getAttribute("dept");

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>部门修改</title>
</head>
<body>
    <h1>修改部门信息</h1>
    <form action="<%=request.getContextPath()%>/dept/modify" method="post">
      部门编号<input type="text" name="deptno" value="<%=dept.getDeptno()%>" readonly/><br>
      部门名称<input type="text" name="dname" value="<%=dept.getName()%>"/><br>
      部门位置<input type="text" name="loc" value="<%=dept.getLoc()%>"/><br>
      <input type='submit' value='修改'/><br>
    </form>
</body>
</html>