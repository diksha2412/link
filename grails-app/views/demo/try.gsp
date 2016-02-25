<%--
  Created by IntelliJ IDEA.
  User: diksha
  Date: 25/2/16
  Time: 10:34 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title></title>
</head>

<body>

<g:each in ="${users}" var="user">
    render "${user.firstName}"
</g:each>

</body>
</html>