<div class="panel panel-default"><!--Top posts panel-->
    <div class="panel panel-heading">Top posts</div>

    <div class="panel-body">

        <div class="col-sm-10">
            <g:each var="resource" in="${resources}">
                <div class="col-xs-2">
                    <ls:userImage userId="${resource.createdBy.id}"/>
                </div>

                <div class="col-xs-10">
                    <p>"${resource.createdBy.fullName}"
                        <inline style="margin-left:1em; color:#d2d4d9">@"${resource.createdBy.userName}" 5min</inline>
                        <inline style="float:right"><a href="#"><u>Grails</u></a></inline>
                        <br/>

                        "${resource}" <br>
                    </p>
                </div>
            </g:each>

            <p style="float:right">
                <a href="#"><u>View Posts</u></a>
            </p><br>
        </div>
    </div>
</div> <!--closure of panel-->