<%@ page import="com.ttnd.linksharing.User; com.enums.Visibility" %>
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
    <nav class="navbar navbar-default">
        <div class="navbar-header">
            <a class="navbar-brand" href="${createLink(controller: 'user', action: 'index')}"><ins>Link Sharing</ins>
            </a>
        </div>

        <div style="float: right; margin-top: 1%">
            <i class="fa fa-comment fa-lg" data-target="#createtopic" data-toggle="modal"></i>&nbsp;&nbsp;
            <i class="glyphicon glyphicon-envelope fa-lg" data-target="#sendinvitation"
               data-toggle="modal"></i>&nbsp;&nbsp;
            <i class="glyphicon glyphicon-link fa-lg" data-target="#sharelink" data-toggle="modal"></i>&nbsp;&nbsp;
            <i class="fa fa-external-link fa-lg" data-target="#sharedocument"
               data-toggle="modal"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <i class="glyphicon glyphicon-user fa-lg"></i>&nbsp;&nbsp;


            <span class="dropdown" style="margin-right: 1%">
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    ${com.ttnd.linksharing.User.get(session.userId).firstName}
                    <span class="caret"></span>
                </button>

                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

                    <li><g:link controller="user" action="showEditProfile">Profile</g:link></li>

                    <g:if test="${com.ttnd.linksharing.User.get(session.userId).admin}">
                        <li><g:link controller="user" action="list">Users</g:link></li>
                    </g:if>

                    <li><g:link controller="login" action="logout">Logout</g:link></li>
                </ul>
            </span>
        </div>

        <g:form class="navbar-form resourceSearch" role="Search" controller="resource" action="searchString">
            <span class="form-group" style="margin-left: 47%">
                <span class="glyphicon glyphicon-search"></span>
                <g:textField type="text" name="queryString" class="form-control" placeholder="Search"/>&nbsp;&nbsp;

                <g:submitButton name="search" value="search" type="submit" class="btn btn-primary"></g:submitButton>
            </span>&nbsp;&nbsp;
        </g:form>

    </nav>

    <g:render template="/topic/createTopic"></g:render>

    <g:render template="/topic/sendInvite"></g:render>

    <g:render template="/documentResource/createDocumentResource"></g:render>

    <g:render template="/linkResource/emailLinkResource"></g:render>

</g:if>

<g:else>
    <div class="navbar navbar-default">
        <nav class="navbar header">
            <a class="navbar-brand" href="#">Link sharing</a>
            <g:form class="navbar-form resourceSearch" role="search" controller="resource" action="searchString">
                <div class="form-group">
                    <span class="glyphicon glyphicon-search" style="margin-left:47em"></span>
                    <g:textField type="text" name="queryString" class="form-control" placeholder="Search"/>&nbsp;&nbsp;
                    <g:submitButton name="search" value="search" type="submit" class="btn btn-primary"></g:submitButton>
                </div>
            </g:form>
        </nav>
        <g:render template="/user/forgotPassword"></g:render>
    </div>
</g:else>

<div class="container">
    <div class="jsonResponse" style="display:none"></div>
</div>

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

<g:layoutBody/>

</body>
</html>
