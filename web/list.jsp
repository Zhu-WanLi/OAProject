<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.javaweb.bean.Dept" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>List</title>

    <script type="text/javascript">
        function del(dno){
            if(window.confirm("确定删除吗？")){
                document.location.href="${pageContext.request.contextPath}/dept/delete?deptno="+dno
                window.confirm("删除成功")
            }
        }
    </script>

    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>


<h3>欢迎${name}</h3>
<h4>在线人数：${onlineCount}</h4>
<a href="dept/exit">[退出登录]</a>
<h1 align="center">部门列表</h1>
<hr>
<table border=1px" align="center" width="50%">
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>

    <c:forEach items="${deptList}" var="dept" varStatus="varStatus">
        <tr>
            <th>${varStatus.count}</th>
            <th>${dept.deptno}</th>
            <th>${dept.name}</th>
            <th>
                <a href="javascript:void(0)" onclick="del(${dept.deptno})">删除</a>
                <a href="dept/edit?deptno=${dept.deptno}">修改</a>
                <a href="dept/detail?deptno=${dept.deptno}">详情</a>
            </th>
        </tr>
    </c:forEach>

</table>
<br>
<a href="add.html">新增部门</a>
</body>
</html>
