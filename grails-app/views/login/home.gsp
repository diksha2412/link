
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>home page</title>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
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