<div class="panel panel-default"><!--FIRST PANEL-->
    <div class="panel-body">

        <ls:userImage userId="${session.userId}"/>

        %{--<asset:image src="user.png" class="img-thumbnail; col-xs-3" alt="Responsive image"/>--}%

        <span class="strong" style="float:left">${user.fullName}</span><br>

        <span style="float:left; color:grey; font-size:10px">@${user.userName}</span><br>

        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

        <span style="color:grey; float:left">Subscriptions</span>

        <span style="color:grey">Topics</span>
        <br>


        <span style="float:left; margin-left:9em"><ls:subscriptionCount/></span>

        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

        <span style=" margin-left:2em"><ls:topicCount/></span>

    </div>
</div> <!-- First Panel close-->