<div class="panel panel-default"><!--BEGINNING OF TRENDING TOPICS PANEL-->
    <div class="panel-heading">
        <h3 class="panel-title">Topics(Created Topics)</h3></div>

    <div class="panel-body" style="overflow-y: auto; height: 300px">

        <g:each in="${topics}" var="topic">

            <span style="float:left">
                <g:link controller="topic" action="show"
                        params='[topicId: "${topic.id}}"]'>${topic.name}</g:link>
            </span>
            <span style="color:grey; margin-left:50px">Subscription</span>&nbsp;&nbsp;&nbsp;
            <span style="color:grey;float:right">Posts</span>
            <br>
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
            <i class="glyphicon glyphicon-envelope" style="font-size:20px"></i>&nbsp;&nbsp;

            <span style="margin-left:7em"><ls:subscriptionCount topicId="${topic.id}"/></span>

            <span style="float:right"><ls:resourceCount topicId="${topic.id}"/></span>
            <hr>


            <span style="float:left">
                <g:link controller="topic" action="show"
                        params='[topicId: "${topic.id}}"]'>${topic.name}</g:link>
            </span>

            <span style="color:grey; margin-left:50px">Subscription</span>&nbsp;&nbsp;&nbsp;
            <span style="color:grey;float:right">Posts</span>
            <br>

            <ls:showSubscribe topicId="${topic.id}"/>

            <span style="margin-left:7em"><ls:subscriptionCount topicId="${topic.id}"/></span>

            <span style="float:right"><ls:resourceCount topicId="${topic.id}"/></span>
            <hr>
        </g:each>
    </div>
</div>




