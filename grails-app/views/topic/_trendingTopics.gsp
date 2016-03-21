<%@ page import="com.enums.Visibility" %>
<div class="panel panel-default"><!--BEGINNING OF TRENDING TOPICS PANEL-->
    <div class="panel-heading">
        <h3 class="panel-title">Trending Topics</h3></div>

    <div class="panel-body" style="overflow-y: auto; height: 300px">

        <g:each in="${trendingTopics}" var="topic">

            <div class="row-sm-6">

                <div class="col-xs-3">
                    <ls:userImage userId="${topic.createdBy.id}"/>
                </div>


                <inline>
                    <span style="float:left">
                        <g:link controller="topic" action="show" params='[topicId: "${topic.id}}"]'>${topic.name}</g:link>
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
                </inline>
                <hr>
            </div>
        </g:each>
    </div>
</div>