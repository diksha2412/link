<%--
  Created by IntelliJ IDEA.
  User: diksha
  Date: 26/2/16
  Time: 10:41 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>dashboard</title>
    <meta name="layout" content="main">
</head>

<body>
<div class="row">
    <div class="col-xs-5">
        <g:render template="userInfo"></g:render>
        <g:render template="/subscription/subscriptions"></g:render>
        <ls:trendingTopics/>
        %{--<g:render template="/topic/trendingTopics"></g:render>--}%

    </div>

    <div class="col-xs-7">
        <g:render template="inbox"></g:render>
    </div>
</div>
</body>
</html>