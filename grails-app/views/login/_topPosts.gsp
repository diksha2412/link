<div class="panel panel-default"><!--Top posts panel-->
    <div class="panel panel-heading">Top posts</div>

    <div class="panel-body">
        <img src="/home/diksha/Downloads/user.png" class="img-thumbnail; col-xs-2" alt="Responsive image">

        <div class="col-sm-10">
            <p>Uday Pratap Singh
                <inline style="margin-left:1em; color:#d2d4d9">@Uday 5min</inline>
                <inline style="float:right"><a href="#"><u>Grails</u></a></inline>
                <br/>
                <g:each var="i" in="${resources}">
                    "${i}" <br>
                </g:each>
            </p>

            <p style="float:right">
                <a href="#"><u>View Posts</u></a>
            </p><br>
        </div>
    </div>
</div> <!--closure of panel-->