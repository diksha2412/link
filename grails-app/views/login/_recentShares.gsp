<div class="panel panel-default">
    <div class="panel panel-heading">Recent shares</div>
    <div class="panel-body">

        <g:each in ="${recentShares}" var="share">
            %{--<img src="${resource(dir: '/home/diksha/downloads', file: 'user.png')}" alt="Grails"/>--}%
            <img src="user.png" class="img-thumbnail; col-xs-2" alt="Responsive image">
            <div class="col-sm-10">
                <p>${share.createdBy.userName}
                    <inline style="margin-left:1em; color:#d2d4d9">@${share.createdBy.firstName} 5min</inline>
                    <inline style="float:right"><a href="#"><u>${share.topic}</u></a></inline>
                    <br/>${share.description}
                </p>

                <p style="float:right">
                    <a href="#"><u>View Posts</u></a>
                </p><br>
            </div>
        </g:each>


        %{--<img src="/home/diksha/Downloads/user.png" class="img-thumbnail; col-xs-2" alt="Responsive image">--}%
        %{--<!--<div style="float:left;font-size:60px" class="glyphicon glyphicon-user"></div> -->--}%
        %{--<div class="col-sm-10">--}%
            %{--<p>Uday Pratap Singh--}%
                %{--<inline style="margin-left:1em; color:#d2d4d9">@Uday 5min</inline>--}%
                %{--<inline style="float:right"><a href="#"><u>Grails</u></a></inline>--}%
                %{--<br/>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.--}%
            %{--</p>--}%

            %{--<p style="float:right">--}%
                %{--<a href="#"><u>View Posts</u></a>--}%
            %{--</p>--}%
        %{--</div>--}%
    </div>
</div> <!--end of panel-->
