<div class="panel panel-default"><!--BEGINNING OF INBOX-->
    <div class="panel-heading">
        <h3 class="panel-title">Inbox</h3>
    </div>

    <div class="panel-body" style="overflow-y: auto; height: 300px">
        <g:each in="${readingItems}" var="readingItem">
        <asset:image src="user.png" class="img-thumbnail; col-xs-2" alt="Responsive image"/>

        <p>${readingItem.resource.createdBy.fullName}

            <inline style="margin-left:1em; color:#d2d4d9">@${readingItem.resource.createdBy.userName} 5min</inline>
            <inline style="float:right">

                <g:link controller="topic" action="show"
                        params='[topicId: "${readingItem.resource.topic.id}"]'>"${readingItem.resource.topic.name}"</g:link></inline>

                %{--<a href="#"><u>${readingItem.resource.topic}</u></a></inline>--}%
            <br/>${readingItem.resource.description}
        </p>

        <p style="float:right">

            <ls:checkType id="${readingItem.resource.id}"></ls:checkType>

            %{--<a href="#"><u>Download</u></a>--}%
            %{--<a href="#"><u>View full Size</u></a>--}%

            <ls:markRead readingItem="${readingItem}"></ls:markRead>

            <g:link controller="resource" action="show"
                    params='[resourceId: "${readingItem.resource.id}"]'>View Post</g:link>

            %{--<a href="#"><u>View Posts</u></a>--}%
        </p><br>

        <hr>
            </g:each>
        %{--<asset:image src="user.png" class="img-thumbnail; col-xs-2" alt="Responsive image"/>
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
        </div>--}%
    </div>
</div> <!--End of inbox-->