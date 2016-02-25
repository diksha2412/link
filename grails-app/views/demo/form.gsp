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
<g:form action="save">
    name: <g:textField name="fname"></g:textField><br>
    password :<g:passwordField name="password"></g:passwordField><br>
    male: <g:radio name="gender" value="male"></g:radio>
    female: <g:radio name="gender" value="female"></g:radio><br>
    verticals: <br>
    grails<g:checkBox name="grails"></g:checkBox>
    amc<g:checkBox name="amc"></g:checkBox><br>
    <g:submitButton name="submit"></g:submitButton>

</g:form>


</div>
</body>
</html>