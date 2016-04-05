
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>dashboard</title>
    <meta name="layout" content="main">
</head>

<body>

<div class="row">
    <div class="col-xs-5">
        <g:render template="/user/userInfo" model="[user: user]"></g:render>
        <g:render template="/subscription/subscriptions" model="[subscriptions: subscriptions]"></g:render>
        <ls:trendingTopics/>

    </div>

    <div class="col-xs-7">
        %{--<g:render template="/user/inbox" model="[readingItems: readingItems]"></g:render>--}%
    </div>
</div>
</body>
</html>