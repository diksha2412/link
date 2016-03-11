<div class="panel panel-default"><!--BEGINNING OF SUBSCRIPTIONS PANEL-->
    <div class="panel-heading">
        <h3 class="panel-title">Subscriptions</h3></div>

    <div class="panel-body" style="overflow-y: auto; height: 300px">

        <g:each in="${subscriptions}" var="subscription">

            <div class="row-sm-6">

                <ls:userImage userId="${subscription.user.id}"/>
                %{--<asset:image src="user.png" class="img-thumbnail; col-xs-3" alt="Responsive image"/>--}%

                <span class="strong">${subscription.user.fullName}</span>

                <span style="float:right">

                    <g:link controller="topic" action="show"
                            params='[topicId: "${subscription.topic.id}"]'>${subscription.topic}</g:link>

                </span><br>

                <span style="float:left; color:grey">@${subscription.user.userName}</span>

                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                <span style="color:grey; margin-left:50px">Subscription</span>&nbsp;&nbsp;&nbsp;
                <span style="color:grey;float:right">Posts</span>
                <br>

                <span style="float:left"><ls:showSubscribe topicId="${subscription.topic.id}"/></span>

                &nbsp;&nbsp;&nbsp;&nbsp;

                <span style="margin-left:6em">

                    <ls:subscriptionCount topicId="${subscription.topic.id}"/>

                </span>

                <span style="margin-left:10em"><ls:resourceCount topicId="${subscription.topic.id}"/></span>
                <br><br>

                <div style="float:right">
                    <span class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            Serious
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="#">Casual</a></li>
                            <li><a href="#">Very Serious</a></li>
                        </ul>
                    </span>

                    <span class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            Private
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="#">Public</a></li>
                        </ul>
                    </span>

                    <i class="glyphicon glyphicon-envelope" style="font-size:20px"></i>&nbsp;&nbsp;
                    <i class="fa fa-pencil-square-o" style="font-size:20px"></i>&nbsp;&nbsp;
                    <i class="glyphicon glyphicon-trash" style="font-size:20px"></i>
                </div>
            </div><br>
            <hr>
        </g:each>

        <div class="row-sm-6">
            <asset:image src="user.png" class="img-thumbnail; col-xs-3" alt="Responsive image"/>
            <inline>
                <span class="strong">Uday Pratap Singh</span>
                <span style="float:right"><a href="#">Grails</a></span><br>
            </inline>
            <inline>
                <span style="float:left; color:grey">@Uday</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color:grey; margin-left:50px">Subscription</span>&nbsp;&nbsp;&nbsp;
                <span style="color:grey;float:right">Posts</span>
            </inline><br>
            <inline>
                <span style="float:left"><a href="#">Subscribe</a></span>&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color:grey; margin-left:50px">50</span>
                <span style="color:grey; float:right">30</span>
            </inline><br>

            <div style="float:right">
                <span class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        Serious
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="#">Very Serious</a></li>
                        <li><a href="#">Casual</a></li>
                    </ul>
                </span>

                &nbsp;&nbsp;
                <i class="glyphicon glyphicon-envelope" style="font-size:20px" data-target="#sendinvitation"
                   data-toggle="modal"></i>

            </div>
        </div>
    </div> <!--END OF PANEL BODY-->
</div> <!--END OF SUBSCRIPTIONS PANEL-->
