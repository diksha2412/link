<div class="panel panel-default">
    <div class="panel-heading">
        <h6 class="panel-title">Posts: "${topic.name}"</h6>
    </div>

    <div class="panel-body">
        <g:each in="${topic.resources}" var="resource">
            <asset:image src="user.png" class="img-thumbnail; col-xs-2" alt="Responsive image"/>
            <p>${resource.createdBy.fullName}
                <inline style="margin-left:1em; color:#d2d4d9">@${resource.createdBy.userName}
                    <span style="float: right">${resource.dateCreated}</span></inline>
                <inline style="float:right"></inline>
                <br/>${resource.description}
            </p>

            <p style="float:right">
                <ls:checkType id="${resource.id}"/>
                <g:if test="${session.userId}">
                    <a href="#">Mark as Read</a>
                </g:if>
                <g:link controller="resource" action="show" params='[resourceId: "${resource.id}"]'>View Post</g:link>

            </p><br>

            <hr>
        </g:each>
    </div>
</div>