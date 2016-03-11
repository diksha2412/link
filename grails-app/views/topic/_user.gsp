<div class="panel panel-default"><!--BEGINNING OF posts-->
    <div class="panel-heading">
        <h6 class="panel-title">Users: "Grails"</h6>
    </div>

    <div class="panel-body">
        <g:each in="${users}" var="user">
            <asset:image src="user.png" class="img-thumbnail; col-xs-3" alt="Responsive image"/>

            <span class="strong" style="float:left">${user.fullName}</span><br>

            <span style="float:left; color:grey; font-size:10px">@${user.userName}</span><br>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="color:grey; float:left">Subscriptions</span>
            <span style="color:grey">Topics</span>
            <br>

            &nbsp;&nbsp;
            <span style="float:left; margin-left:1em">
                <ls:subscriptionCount topicId="${topic.id}"/>
            </span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="color:grey; margin-left:5em">
                <ls:resourceCount topicId="${topic.id}"/>
            </span>
            <hr>
        </g:each>

    </div>
</div> <!-- First Panel close-->