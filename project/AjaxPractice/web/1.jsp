<%--
  Created by IntelliJ IDEA.
  User: wdy
  Date: 16/9/28
  Time: 下午3:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax练习</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myjs.js"></script>
</head>
<script type="text/javascript">

    window.onload = function() {
        //1、获取XMLHttpRequest对象
        var req = getXMLHttpRequest();

        //4、处理响应结果
        req.onreadystatechange = function(){
//            alert(req.readyState);
            if(req.readyState==4){
                //alert(req.status);//查看服务器端响应状态
                if(req.status==200){//服务器响应一切正常
                    alert(req.responseText);
                }
            }
        }

        //2、建立一个连接
        req.open("get","${pageContext.request.contextPath }/servlet/ajaxDemoServlet");
        //3、发送请求
        req.send(null);
    }

</script>
<body>

</body>
</html>
