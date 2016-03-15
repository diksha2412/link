<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta content="main" name="layout">
</head>

<body>

<div class="row">

    <div class="col-xs-5">
        <ls:trendingTopics/>
        <g:render template="/login/topPosts" model="['resources': resources]"></g:render>
    </div>

    <div class="col-xs-7">

        <div class="panel panel-default">

            <div class="panel-heading">
                <h3 class="panel-title">Search for "${queryString}"</h3>
            </div>

            <div class="panel-body" style="overflow-y: auto; height: 300px">
                <g:each in="${resources}" var="resource">
                    <div class="col-xs-2">
                        <ls:userImage userId="${resource.createdBy.id}"/>
                    </div>

                    <p>${resource.createdBy.fullName}
                        <inline style="margin-left:1em; color:#d2d4d9">@${resource.createdBy.userName} 5min</inline>
                        <inline style="float:right"><a href="#"><u>${resource.topic}</u></a></inline>
                        <br/>${resource.description}
                    </p>

                    <p style="float:right">
                        <ls:checkType id="${resource.id}"/>
                        <a href="#"><u>Mark as Read</u></a>
                        <g:link controller="resource" action="show" params="[resourceId: resource.id]">View Post</g:link>
                    </p><br><hr>

                </g:each>
            </div>
        </div>
    </div>
</div>
</body>
</html>