<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>部门修改</title>
</head>
<body>
<h1>修改部门信息</h1>
<form action="${pageContext.request.contextPath}/dept/modify" method="post">
  部门编号<input type="text" name="deptno" value="${dept.deptno}" readonly/><br>
  部门名称<input type="text" name="dname" value="${dept.name}"/><br>
  部门位置<input type="text" name="loc" value="${dept.loc}"/><br>
  <input type='submit' value='修改'/><br>
</form>
</body>
</html>