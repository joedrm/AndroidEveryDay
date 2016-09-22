<%--
  Created by IntelliJ IDEA.
  User: wdy
  Date: 16/9/22
  Time: 下午2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/servlet/loginServlet" method="get">
        用户名: <input type="text" name="username"/><br/>
        密码: <input type="password" name="password"/><br/>
        <input type="submit" value="登录"><br/>

    </form>
</body>
</html>
