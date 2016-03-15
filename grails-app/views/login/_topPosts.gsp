<div class="panel panel-default"><!--Top posts panel-->
    <div class="panel panel-heading">Top posts</div>

    <div class="panel-body" style="overflow-y: auto; height: 300px">

        <g:each var="resource" in="${resources}">
            <div class="col-xs-2">
                <ls:userImage userId="${resource.createdBy.id}"/>
            </div>

            <div class="col-xs-10">
                <p>${resource.createdBy.fullName}
                    <inline style="margin-left:1em; color:#d2d4d9">@"${resource.createdBy.userName}" 5min</inline>
                    <inline style="float:right"><a href="#"><u>${resource.topic}</u></a></inline>
                    <br/>

                    ${resource.description} <br>
                </p>

                <p style="float:right">
                    <a href="#"><u>View Post</u></a>
                </p><br><br>
            </div>
            <hr>
        </g:each>
    </div>
</div> <!--closure of panel-->