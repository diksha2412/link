<div class="panel panel-default"><!--BEGINNING OF SUBSCRIPTIONS PANEL-->
    <div class="panel-heading">
        <h3 class="panel-title">Subscriptions</h3></div>

    <div class="panel-body" style="overflow-y: auto; height: 300px">

        <g:each in="${subscriptions}" var="subscription">

            <div class="row-sm-6">

                <div class="col-xs-3">
                    <ls:userImage userId="${subscription.topic.createdBy.id}"/>
                </div>

                <span class="strong">${subscription.topic.createdBy.fullName}</span>

                <span style="float:right">
                    <g:link controller="topic" action="show"
                            params='[topicId: "${subscription.topic.id}"]'>${subscription.topic}</g:link>
                </span><br>

                <span style="float:left; color:grey">@${subscription.topic.createdBy.userName}</span>

                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                <span style="color:grey; margin-left:50px">Subscription</span>&nbsp;&nbsp;&nbsp;

                <span style="color:grey;float:right">Posts</span><br>

                <span style="float:left"><ls:showSubscribe topicId="${subscription.topic.id}"/></span>

                &nbsp;&nbsp;&nbsp;&nbsp;


                <span style="margin-left:6em">
                    <ls:subscriptionCount topicId="${subscription.topic.id}"/>
                </span>


                <span style="margin-left:10em"><ls:resourceCount topicId="${subscription.topic.id}"/></span>
                <br><br>

                <div style="float:right">
                <span class="dropdown">

                    <ls:canUpdateTopic topicId="${subscription.topic.id}">

                        <ls:showVisibility topicId="${subscription.topic.id}"/>

                        </span>


                        <i class="glyphicon glyphicon-envelope" data-toggle="modal" data-target="#sendinvitation" style="font-size:20px"></i>&nbsp;&nbsp;

                        <i class="fa fa-pencil-square-o edit-topic" data-topicId="${subscription.topic.id}" style="font-size:20px"></i>&nbsp;&nbsp;

                        <g:link name="delete" controller="topic" action="delete" params='[topicId: "${subscription.topic.id}"]' style="font-size:20px">
                            <i class="glyphicon glyphicon-trash topic-delete" controller="topic" action="delete" data-topicId="${subscription.topic.id}" style="font-size:20px">
                            </i>
                        </g:link>
                    </ls:canUpdateTopic>
                </div>
            </div><br>
            <hr>

            <!--hidden div for edit begins-->

            <div style="display: none; float: right" id="editForm${subscription.topic.id}">
                <div class="row">
                    <g:textField name="name${subscription.topic.id}" value="${subscription.topic.name}"/>

                    <g:hiddenField name="topicId" id="topicId${subscription.topic.id}" value="${subscription.topic.id}"/>

                    <button class="saveTopicNameButton btn-primary" class="saveTopicNameButton" topicId="${subscription.topic.id}">Save</button>

                    <button class="cancelTopicNameButton btn-primary" topicId="${subscription.topic.id}">Cancel</button>
                </div>
            </div>

            <!--hidden div for edit ends here-->
        </g:each>

    %{--<div class="row-sm-6">
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
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu3"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    Serious
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
                    <li><a href="#">Very Serious</a></li>
                    <li><a href="#">Casual</a></li>
                </ul>
            </span>

            &nbsp;&nbsp;
            <i class="glyphicon glyphicon-envelope" style="font-size:20px" data-target="#sendinvitation"
               data-toggle="modal"></i>

        </div>
   </div>--}%
    </div> <!--END OF PANEL BODY-->
</div> <!--END OF SUBSCRIPTIONS PANEL-->
