<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta content="main" name="layout">
</head>

<body>

<div class="row">
    <div class="col-xs-5">
        <g:render template="/topic/trendingTopics"></g:render>
    </div>

    <div class="panel panel-default"><!--BEGINNING OF INBOX-->
        <div class="panel-heading">
            <h3 class="panel-title">Search for "test"</h3>
        </div>

        <div class="panel-body">
            <asset:image src="user.png" class="img-thumbnail; col-xs-2" alt="Responsive image"/>
            <p>Uday Pratap Singh
                <inline style="margin-left:1em; color:#d2d4d9">@Uday 5min</inline>
                <inline style="float:right"><a href="#"><u>Grails</u></a></inline>
                <br/>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            </p>

            <p style="float:right">
                <a href="#"><u>Download</u></a>
                <a href="#"><u>View full Size</u></a>
                <a href="#"><u>Mark as Read</u></a>
                <a href="#"><u>View Posts</u></a>
            </p><br>

            <hr>
            <asset:image src="user.png" class="img-thumbnail; col-xs-2" alt="Responsive image"/>
            <p>Uday Pratap Singh.
                <inline style="margin-left:1em; color:#d2d4d9">@Uday</inline>
                <inline style="float:right"><a href="#"><ins>Grails</ins></a></inline>
                <br>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            </p>

            <div>
                <p style="float:right">
                    <a href="#"><ins>Download</ins></a>
                    <a href="#"><ins>View full Size</ins></a>
                    <a href="#"><ins>Mark as Read</ins></a>
                    <a href="#"><ins>View Posts</ins></a>
                </p>
            </div>
        </div>
    </div> <!--End of inbox-->

</div>

<div class="col-xs-5">
    <g:render template="/login/topPosts"></g:render>
</div>

</body>
</html>