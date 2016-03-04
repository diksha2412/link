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
<div class="row"><!-- for two halves-->
    <div class="col-lg-5"><!-- FIRST HALF-->
    <g:render template="userInfo"><</g:render>
    <g:render template="/subscription/subscriptions"></g:render>
    <g:render template="/topic/trendingTopics"></g:render>

    </div>      <!--END OF FIRST HALF-->

    <div class="col-lg-7">
<g:render template="inbox"></g:render>
    </div>
</body>
</html>