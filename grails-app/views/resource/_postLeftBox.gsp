<%@ page import="com.ttnd.linksharing.User" %>
<div class="panel panel-default"><!--FIRST PANEL-->
    <div class="panel-body">
        <div class="row-sm-5">

            <div class="col-xs-4">
                <ls:userImage userId="${resource.createdBy.id}"/>
            </div>
            <inline>
                <span style="float:left">${resource.createdBy.fullName}</span>
                <span style="float:right"><a href="#">${resource.description}</a></span>
            </inline><br>

            <span style="float:left; color:grey">@${resource.createdBy.username}</span>
            <span style="float:right; color:grey">2:45 PM 22 FEB 2014</span><br><br>

            <span style="float: right">
                <g:if test="${session.userId}">
                    <g:form controller="resourceRating" action="save" params="[resourceId: resource.id]">
                        <g:select name="score" from="[1, 2, 3, 4, 5]"
                                  class="dropdown-toggle btn btn-default"
                                  value="${com.ttnd.linksharing.ResourceRating.findByResourceAndUser(resource, com.ttnd.linksharing.User.get(session.userId))?.score}"
                                  noSelection="['': 'select score']"/>

                        <g:submitButton name="vote" class="btn btn-primary"></g:submitButton>
                    </g:form>
                </g:if>
            </span>

        </div><br><br>

        <div class="row-sm-7">
            <p class="text:justify"
               style="font-size:50px; margin-left:1em">${resource.description}</p>
            <br>

            <div style="float:right">
                <g:if test="${session.userId}">
                    <ls:deleteResource resource="${resource}"/> &nbsp;&nbsp;
                    <g:render template="edit" model="[resource: resource]"/>

                    <g:link data-toggle="modal" data-target="#editDescription">Edit</g:link>
                </g:if>
                <ls:checkType id="${resource.id}"></ls:checkType>

            </div>
        </div>
    </div>
</div>
