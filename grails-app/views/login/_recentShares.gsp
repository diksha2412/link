<div class="panel panel-default">
    <div class="panel panel-heading">Recent shares</div>

    <div class="panel-body" style="overflow-y: auto; height: 300px">

        <g:each in="${recentShares}" var="share">
        <img src="user.png" class="img-thumbnail; col-xs-2" alt="Responsive image">
            %{--<asset:image src="user.png" class="img-thumbnail; col-xs-2" alt="Responsive image"/>--}%
            <div class="col-sm-10">
                <p>${share.createdBy.userName}
                    <inline style="margin-left:1em; color:#d2d4d9">@${share.createdBy.firstName} 5min</inline>
                    <inline style="float:right">
                        <g:link controller="topic" action="show"
                                params='[topicId: "${share.topic.id}"]'>"${share.topic}"</g:link>
                    </inline>
                    <br/>${share.description}
                </p>

                <p style="float:right">
                    <g:link controller="resource" action="show"
                            params='[topicId: "${share.topic.id}"]'>"${share.topic}"</g:link>
                    %{--<a href="#"><u>View Posts</u></a>--}%
                </p><br>
            </div>
        </g:each>
    </div>
</div>

