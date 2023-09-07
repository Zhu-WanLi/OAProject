<%--
  Created by IntelliJ IDEA.
  User: 朱文龙
  Date: 2023/7/8
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>welcome</title>
</head>
<body>
<h1>LOGIN PAGE</h1>
<br>
<form action="<%=request.getContextPath()%>/dept/login" method="post">
  用户名:<input type="text" name="username"><br>
  密码：<input type="password" name="password"><br>
  <input type="checkbox" name="f" value="1">十天内免登陆<br>
  <input type="submit" value="login"><br>
</form>

</body>
</html>