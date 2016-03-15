<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <title>edit</title>
    <meta name="layout" content="main">
</head>

<body>
<!--Header class-->
<div class="container-fluid">

    <div class="row">.

        <div class="col-lg-5">
            <g:render template="/user/userInfo" model="[user: user]"></g:render>
            <g:render template="createdTopics" model="[topicsCreated: topicsCreated]"/>
        </div>

        <div class="col-lg-7">
            <g:render template="/user/profile" model="[user: user]"/>
            <g:render template="/user/changePassword"/>
        </div>
    </div>
</div>
</body>
</html>