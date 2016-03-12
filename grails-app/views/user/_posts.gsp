<div class="panel panel-default"><!--BEGINNING OF INBOX-->
    <div class="panel-heading">
        <h3 class="panel-title">Inbox</h3>
    </div>

    <div class="panel-body" style="overflow-y: auto; height: 300px">
        <g:each in="${resources}" var="resource">

            <div class="col-xs-3">
                <ls:userImage userId="${session.userId}"/>
            </div>

            <p>${resource.createdBy.fullName}

                <inline style="margin-left:1em; color:#d2d4d9">@${resource.createdBy.userName} 5min</inline>
                <inline style="float:right">

                    <g:link controller="topic" action="show"
                            params='[topicId: "${resource.topic.id}"]'>"${resource.topic.name}"</g:link></inline>

                <br/>${resource.description} this is post no:${resource.id}
            </p>

            <p style="float:right">

                <ls:checkType id="${resource.id}"></ls:checkType>

                <ls:markRead readingItem="${resource}"></ls:markRead>

                <g:link controller="resource" action="show"
                        params='[resourceId: "${resource.id}"]'>View Post</g:link>

                %{--<a href="#"><u>View Posts</u></a>--}%
            </p><br>

            <hr>
        </g:each>
    </div>
</div> <!--End of inbox-->