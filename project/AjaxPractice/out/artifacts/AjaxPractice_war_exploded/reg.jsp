<%--
  Created by IntelliJ IDEA.
  User: wdy
  Date: 16/9/28
  Time: 下午4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/myjs.js"></script>
    <title>验证用户是否存在</title>
    <script type="text/javascript">
        function ckname() {
            var name = document.getElementById("um");
            var xmlhttp = getXMLHttpRequest();

            xmlhttp.onreadystatechange = function(){
                if (xmlhttp.readyState == 4){
//            alert(xmlhttp.readyState);
                    if (xmlhttp.status == 200){
//                alert(xmlhttp.status);
                        var msg = document.getElementById("msg");
                        alert(xmlhttp.responseText);
                        if (xmlhttp.responseText=="true"){
                            msg.innerHTML = "用户名已存在"
                        }else {
                            msg.innerHTML = "可以使用"
                        }
                    }
                }
            };
            xmlhttp.open("get","${pageContext.request.contextPath }/servlet/ckNameServlet?name="+name.value);
            xmlhttp.send(null);
        }
    </script>
</head>

<body>
    用户名:<input type="text" name="username" id="um" onblur="ckname()"> <span id="msg"></span> <br/>
    密 码: <input type="password" name="password">
</body>
</html>
