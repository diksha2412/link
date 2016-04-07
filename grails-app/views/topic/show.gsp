
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta name="layout" content="main">
</head>

<body>

<div class="row">
    <div class="col-xs-5">
        <g:render template="/topic/topicShow" model="['topic': topic]"></g:render>
        <g:render template="user" model="[users: users]"></g:render>
    </div>

    <div class="col-xs-7">
        <g:render template="/topic/posts" model="[topic: topic]"></g:render>
    </div>

</div>

</body>
</html>