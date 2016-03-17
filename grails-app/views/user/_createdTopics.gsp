<!--TOPICS PANEL-->
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Topics</h3>
    </div>

    <div class="panel-body">
        <g:each in="${topicsCreated}" var="topic">
            <div class="col-xs-3">
            <ls:userImage userId="${topic.createdBy.id}"/>
            </div>


            <span style="float:left; color:grey">@${topic.createdBy.userName}</span>
            <span style="margin-left:50px; color:grey">Subscriptions</span>
            <span style="float:right; color:grey">Topics</span>
            <br>
            <span style="margin-left:100px"><ls:subscriptionCount topicId="${topic.id}"/> </span>
            <span style="float:right"><ls:topicCount /> </span>
            <br>

            <div style="float:right">
                <ls:showSeriousness topicId="${topic.id}"/>
                <ls:showVisibility topicId="${topic.id}"/>

                <i class="glyphicon glyphicon-envelope" style="font-size:25px"></i>&nbsp;&nbsp;
                <i class="fa fa-pencil-square-o" style="font-size:25px"></i>&nbsp;&nbsp;
                <i class="glyphicon glyphicon-trash" style="font-size:25px"></i><br>
            </div>
            <hr>
        </g:each>
    </div>
</div> <!--END OF TOPICS PANEL-->