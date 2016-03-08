<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <asset:javascript src="jquery-2.2.1.min.js"/>
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <g:layoutHead/>

</head>

<body>
<g:if test="${session.userId}">
    <nav  class="navbar navbar-default">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><ins>Link Sharing</ins></a>
        </div>
        <form class="navbar-form" role="Search">
            <div class="form-group">
                <span class="glyphicon glyphicon-search" style="margin-left:43em"></span>
                <input type="text" class="form-control" placeholder="Search">&nbsp;&nbsp;
                <i class="fa fa-comment" data-target="#createtopic" data-toggle="modal"></i>&nbsp;&nbsp;
                <i class="glyphicon glyphicon-envelope" data-target="#sendinvitation" data-toggle="modal"></i>&nbsp;&nbsp;
                <i class="glyphicon glyphicon-link" data-target="#sharelink" data-toggle="modal"></i>&nbsp;&nbsp;
                <i class="fa fa-external-link" data-target="#sharedocument" data-toggle="modal"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;

                <span class="dropdown" style="float:right">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        Uday
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">Users </a></li>
                        <li><a href="#">Topics </a></li>
                        <li><a href="#">Posts</a></li>
                        <li><a href="#">LOgOut</a></li>
                    </ul>
                </span>

            </div>
        </form>
    </nav>

    <g:render template="/topic/createTopic"></g:render>

    <g:render template="/documentResource/createDocumentResource"></g:render>

    <g:render template="/linkResource/emailLinkResource"></g:render>
    </g:if>

<g:else>
    <div class="navbar navbar-default">
        <nav class="navbar header">
            <a class="navbar-brand" href="#">Link sharing</a>
            <g:form class="navbar-form " role="search">
                <div class="form-group">
                    <span class="glyphicon glyphicon-search" style="margin-left:47em"></span>
                    <input type="text"class="form-control" placeholder="Search" ></input>
                </div>
            </g:form>
        </nav>
        <g:render template="/user/forgotPassword"></g:render>
    </div>
</g:else>
<g:layoutBody/>
</body>
</html>
