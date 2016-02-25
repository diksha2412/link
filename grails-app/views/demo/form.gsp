<%--
  Created by IntelliJ IDEA.
  User: diksha
  Date: 25/2/16
  Time: 10:52 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>


===${user}=========
 <g:renderErrors bean="${user}" field="firstName"></g:renderErrors>

<g:form action="save">
    name: <g:textField name="firstName" value="${user?.firstName}"/><br>
    password :<g:passwordField name="password" value="${user?.password}"/><br>
    <g:submitButton name="submit"></g:submitButton>
</g:form>

<ls:showUserList/>

</div>
</body>
</html>