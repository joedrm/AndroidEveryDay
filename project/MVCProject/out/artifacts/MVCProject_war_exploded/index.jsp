<%--
  Created by IntelliJ IDEA.
  User: wdy
  Date: 16/9/20
  Time: 下午3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
  String path = request.getContextPath();
  String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
      <a href="/login.jsp">登录</a>
      <a href="/reg.jsp">注册</a>
  </body>
</html>
