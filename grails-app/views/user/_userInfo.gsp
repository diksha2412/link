<div class="panel panel-default"><!--FIRST PANEL-->
    <div class="panel-body">
    <div class="col-xs-3">

        <ls:userImage userId="${session.userId}"/>
    </div>

        %{--<asset:image src="user.png" class="img-thumbnail; col-xs-3" alt="Responsive image"/>--}%

        <span class="strong" style="float:left">${user.fullName}</span><br>

        <span style="float:left; color:grey; font-size:10px">@${user.userName}</span><br>

        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

        <span style="color:grey; float:left">Subscriptions</span>

        <span style="color:grey">Topics</span>
        <br>


        <span style="float:left"><ls:subscriptionCount/></span>

        <span style=" margin-left:8em"><ls:topicCount/></span>

    </div>
</div> <!-- First Panel close-->