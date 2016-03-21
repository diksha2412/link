
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>dashboard</title>
    <meta name="layout" content="main">
</head>

<body>

<g:if test="${flash.message}">
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Success!</strong> ${flash.message}
    </div>
</g:if>
<g:elseif test="${flash.error}">
    <div class="alert alert-danger">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Danger!</strong> ${flash.error}
    </div>
</g:elseif>

<div class="row">
    <div class="col-xs-5">
        <g:render template="/user/userInfo" model="[user: user]"></g:render>
        <g:render template="/subscription/subscriptions" model="[subscriptions: subscriptions]"></g:render>
        <ls:trendingTopics/>

    </div>

    <div class="col-xs-7">
        <g:render template="/user/inbox" model="[readingItems: readingItems]"></g:render>
    </div>
</div>
</body>
</html>