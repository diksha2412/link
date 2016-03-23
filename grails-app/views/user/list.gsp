<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Admin Title bar</title>
</head>

<body>
<div class="panel panel-default panel-primary">
    <div class="panel-heading">
        <div class="row">
            <div class="col-xs-3">
                Users
            </div>

            <div class="col-xs-offset-2 col-xs-7">
                <g:form name="adminUsersSearchForm" class="form-inline" controller="user" action="list">
                    <div class="form-group">
                        <div class="input-group">
                            <select name="active" id="active" class="btn btn-default">
                                <option value="${null}">All users</option>
                                <option value="${true}">Active users</option>
                                <option value="${false}">Inactive users</option>
                            </select>

                            &nbsp;&nbsp;&nbsp;

                            <span class="input-group-btn">
                                <button type="submit" class="btn btn-primary glyphicon glyphicon-search searchButtons"
                                        type="submit"></button>
                            </span>

                            <span class="input-group-btn">
                                <input type="text" id="q" name="q" class="form-control input-group"
                                       placeholder="Search">
                            </span>

                            <span class="input-group-btn">
                                <button class="btn btn-primary glyphicon-remove searchButtons">
                                </button>
                            </span>

                        </div>
                    </div>
                </g:form>
            </div>
        </div>
    </div>

    <div class="panel-body">

       <g:render template="userList" />

        <div class="pagination">
            <util:remotePaginate controller="user" action="paginate" total="${totalCount}" update="listUpdate" max="5"/>
        </div>

    </div>
</div>

</body>

</html>