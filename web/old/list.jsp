<%@ page import="java.util.ArrayList" %>
<%@ page import="com.javaweb.bean.Dept" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 朱文龙
  Date: 2023/7/8
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>

    <script type="text/javascript">
     function del(dno){
           if(window.confirm("确定删除吗？")){
               document.location.href="<%=request.getContextPath()%>/dept/delete?deptno="+dno
               window.confirm("删除成功")
           }
     }
</script>
</head>
<body>
     <h3>欢迎<%=session.getAttribute("name")%></h3>
     <a href="<%=request.getContextPath()%>/dept/exit">[退出登录]</a>
     <h1 align="center">部门列表</h1>
     <hr>
     <table border=1px" align="center" width="50%">
            <tr>
                     <th>序号</th>
                     <th>部门编号</th>
                     <th>部门名称</th>
                     <th>操作</th>
            </tr>

             <%
                     int i=0;
                     ArrayList<Dept> deptList = (ArrayList<Dept>)request.getAttribute("deptList");
                     for (Dept dept : deptList) {
                         String deptno=dept.getDeptno();
                         String dname=dept.getName();
                         String loc=dept.getLoc();

             %>


                 <tr>
                     <th><%=++i%></th>
                     <th><%=deptno%></th>
                     <th><%=dname%></th>
                      <th>
                              <a href="javascript:void(0)" onclick="del(<%=deptno%>)">删除</a>
                              <a href="<%=request.getContextPath()%>/dept/edit?deptno=<%=deptno%>">修改</a>
                              <a href="<%=request.getContextPath()%>/dept/detail?deptno=<%=deptno%>">详情</a>
                          </th>
                  </tr>

             <%
                     }
             %>
     </table>
         <br>
             <a href="<%=request.getContextPath()%>/old/add.htmlhtml">新增部门</a>
</body>
</html>
