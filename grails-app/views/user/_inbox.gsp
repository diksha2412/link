<div class="panel panel-default"><!--BEGINNING OF INBOX-->
    <div class="panel-heading">
        <h3 class="panel-title">Inbox</h3>
    </div>

    <div class="panel-body" style="overflow-y: auto; height: 300px">
        <g:each in="${readingItems}" var="readingItem">

            <div class="col-xs-2">
                <ls:userImage userId="${readingItem.resource.createdBy.id}"/>
            </div>

            <p>${readingItem.resource.createdBy.fullName}

                <inline style="margin-left:1em; color:#d2d4d9">@${readingItem.resource.createdBy.userName} 5min</inline>
                <inline style="float:right">

                    <g:link controller="topic" action="show"
                            params='[topicId: "${readingItem.resource.topic.id}"]'>"${readingItem.resource.topic.name}"</g:link></inline>

                <br/>${readingItem.resource.description} this is readingItem no:${readingItem.id}
            </p>

            <p style="float:right">

                <ls:checkType id="${readingItem.resource.id}"></ls:checkType>

                <ls:markRead isRead="${readingItem.isRead}" class="markReadStatus" resourceId="${readingItem.id}"/>

                <g:link controller="resource" action="show" params='[resourceId: "${readingItem.resource.id}"]'>View Post</g:link>

            </p><br>

            <hr>
        </g:each>

    </div>
</div>