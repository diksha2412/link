<div class="panel panel-default">
    <div class="panel-body">
        <div class="col-xs-3">

            <ls:userImage userId="${com.ttnd.linksharing.User.loggedInUser().id}"/>
        </div>


        <span class="strong" style="float:left">${user.fullName}</span><br>

        <span style="float:left; color:grey; font-size:10px">
            <g:link controller="user" action="showProfile">@${user.username}</g:link>
        </span><br>

        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

        <span style="color:grey; float:left">Subscriptions</span>

        <span style="color:grey">Topics</span>
        <br>


        <span style="float:left"><ls:subscriptionCount/></span>

        <span style=" margin-left:8em"><ls:topicCount/></span>

    </div>
</div>