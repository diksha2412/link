<div class="panel panel-default">
    <div class="panel panel-heading">Recent shares</div>

    <div class="panel-body" style="overflow-y: auto; height: 300px">

        <g:each in="${recentShares}" var="share">

            <div class="col-xs-2">

                <ls:userImage userId = "${share.createdBy.id}"/>
                %{--<p>"${share.createdBy.id}" </p>--}%

                %{--<asset:image src="user.png" class="img-thumbnail" alt="Responsive image"/>--}%
            </div>

            <div class="col-sm-10">

                <p>${share.createdBy.fullName}

                    <inline style="margin-left:1em; color:#d2d4d9">@${share.createdBy.firstName} 5min</inline>
                    <inline style="float:right">

                        <g:link controller="topic" action="show"
                                params='[topicId: "${share.topic.id}"]'>"${share.topic}"</g:link>

                    </inline>

                    <br/><br/>${share.description}

                </p>

                <p style="float:right">
                    <g:link controller="resource" action="show"
                            params='[resourceId: "${share.id}"]'>View Post</g:link>

                </p><br>
            </div>
            <hr>
        </g:each>
    </div>
</div>

