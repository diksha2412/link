<%--
  Created by IntelliJ IDEA.
  User: diksha
  Date: 3/3/16
  Time: 4:05 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta name="layout" content="main">
</head>

<body>

<div class="row">
    <div class="col-xs-5">
        <g:render template="/topic/topicShow"></g:render>
    </div>

    <div class="col-xs-7">
        <g:render template="/topic/posts"></g:render>
    </div>

</div>

<div class="row">
    <div class="col-xs-5">
        <g:render template="user"></g:render>
    </div>
</div>

</body>
</html>