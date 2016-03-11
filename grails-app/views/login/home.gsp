<%--
  Created by IntelliJ IDEA.
  User: diksha
  Date: 26/2/16
  Time: 10:39 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>home page</title>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>

<body>
<div class="row">
    <div class="col-xs-7">
        <g:render template="recentShares"></g:render>
        <ls:topPosts/>
    </div>

    <div class="col-xs-5">
        <g:render template="login"></g:render>
        <g:render template="/user/register"></g:render>
    </div>
</div>

</body>
</html>