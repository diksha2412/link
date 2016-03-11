<div class="panel panel-default"><!--BEGINNING OF TRENDING TOPICS PANEL-->
    <div class="panel-heading">
        <h3 class="panel-title">Trending Topics</h3></div>

    <div class="panel-body" style="overflow-y: auto; height: 300px">

        <g:each in="${trendingTopics}" var="topic">

            <div class="row-sm-6">
                <asset:image src="user.png" class="img-thumbnail; col-xs-3" alt="Responsive image"/>

                <inline>
                    <span style="float:left">
                        <g:link controller="topic" action="show"
                                params='[topicId: "${topic.id}}"]'>${topic.name}</g:link>
                    </span><br>
                </inline>
                <inline>
                    <span style="float:left; color:grey">@${topic.createdBy.userName}</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span style="color:grey; margin-left:50px">Subscription</span>&nbsp;&nbsp;&nbsp;
                    <span style="color:grey;float:right">Posts</span>
                </inline><br>
                <inline>
                    <span style="float:left">

                        <ls:showSubscribe topicId="${topic.id}"></ls:showSubscribe>

                    </span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                    <span style="margin-left:7em"> <ls:subscriptionCount topicId="${topic.id}"/> </span>

                    <span style="float:right"><ls:resourceCount topicId="${topic.id}"/></span>
                </inline><hr>

            </div>
        </g:each>
        <hr>

        <div class="row-sm-6">
            <asset:image src="user.png" class="img-thumbnail; col-xs-3" alt="Responsive image"/>
            <input type="text" id="id1" placeholder="Grails">
            <input type="button" value="save">
            <input type="button" value="cancel"><br>
            <inline>
                <span style="float:left; color:grey">@Uday</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color:grey; margin-left:50px">Subscription</span>&nbsp;&nbsp;&nbsp;
                <span style="color:grey;float:right">Posts</span>
            </inline><br>
            <inline>
                <span style="float:left"><a href="#">Unsubscribe</a></span>&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="margin-left:48px">50</span>
                <span style="float:right">30</span>
            </inline><br><br>

            <div style="float:right">
                <span class="dropup">
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
                &nbsp;
                &nbsp;

                <span class="dropup">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        Public
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="#">Private</a></li>
                    </ul>
                </span>
                &nbsp;&nbsp;
                <i class="glyphicon glyphicon-envelope" style="font-size:20px"></i>&nbsp;&nbsp;
                <i class="fa fa-pencil-square-o" style="font-size:20px"></i>&nbsp;&nbsp;
                <i class="glyphicon glyphicon-trash" style="font-size:20px"></i>
            </div>
        </div>

    </div> <!--END OF PANEL BODY-->
</div> <!--END OF TRENDING TOPICS PANEL-->