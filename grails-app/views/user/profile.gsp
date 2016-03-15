
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User profile</title>
    <meta name="layout" content="main">
</head>

<body>
<div class="row">
    <div class="col-xs-5">
        <g:render template="userInfo" model="[user: user]"></g:render>
        <g:render template="topics" model="[topicsCreated: topicsCreated]"/>
    </div>

    <div class="col-xs-7">
        <g:render template="posts" model="[resources: resources]"/>
    </div>
</div>
</body>
</html>