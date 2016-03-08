<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>resource show page</title>
    <meta name="layout" content="main">
</head>

<body>

<div class="row"><!-- for two halves-->

    <div class="col-lg-7"><!-- FIRST HALF-->
    <g:render template="postLeftBox" model="[resource: resource]"></g:render>
    </div>

    <div class="col-lg-5">
        <g:render template="/topic/trendingTopics" model="[trendingTopics: trendingTopics]"></g:render>
    </div>

</div>        <!--ROW END-->

</body>
</html>