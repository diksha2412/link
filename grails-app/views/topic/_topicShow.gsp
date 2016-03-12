<div class="panel panel-default"><!--BEGINNING OF SUBSCRIPTIONS PANEL-->
    <div class="panel-heading">
        <h3 class="panel-title">Topic: "${topic.name}"</h3></div>

    <div class="panel-body">
        <div class="row-sm-6">
            <asset:image src="user.png" class="img-thumbnail; col-xs-3" alt="Responsive image"/>
            <inline>
                <span class="strong">${topic.createdBy.fullName}</span> <br/>

            </inline>
            <inline>
                <span style="float:left; color:grey">@${topic.createdBy.userName}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color:grey; margin-left:50px">Subscription</span>&nbsp;&nbsp;&nbsp;
                <span style="color:grey;float:right">Posts</span>
            </inline><br>
            <inline>
                <span style="float:left"><ls:showSubscribe topicId="${topic.id}"/></span>&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color:grey; margin-left: 75px"><ls:subscriptionCount topicId="${topic.id}"/></span>
                <span style="color:grey; float:right"><ls:resourceCount topicId="${topic.id}"/></span>
            </inline><br>

            <div style="float:right">
                <span class="dropdown">

                    <g:select name="seriousness" from="${com.enums.Seriousness.values()}"
                              noSelection="['': 'select visibility']"/>
                    %{--<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        Serious
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="#">Very Serious</a></li>
                        <li><a href="#">Casual</a></li>
                    </ul>--}%
                </span>

                &nbsp;&nbsp;
                <i class="glyphicon glyphicon-envelope" style="font-size:20px" data-target="#sendinvitation"
                   data-toggle="modal"></i>
            </div>
        </div>
    </div>
</div> <!--END OF PANEL BODY-->