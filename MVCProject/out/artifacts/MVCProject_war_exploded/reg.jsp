<%--
  Created by IntelliJ IDEA.
  User: wdy
  Date: 16/9/20
  Time: 下午4:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/servlet/regservlet" method="post">
        用户名: <input type="text" name="username"/><br/>
        密码: <input type="password" name="password"/><br/>
        确认密码: <input type="password" name="repassword"/><br/>
        邮箱: <input type="text" name="email"/><br/>
        生日: <input type="text" name="birthday"/><br/>

        <input type="submit" value="注册"><br/>

    </form>
</body>
</html>
