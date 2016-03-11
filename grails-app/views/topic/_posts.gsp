<div class="panel panel-default"><!--BEGINNING OF posts-->
    <div class="panel-heading">
        <h6 class="panel-title">Posts: "${topic.name}"</h6>
    </div>

    <div class="panel-body">
        <g:each in="${topic.resources}" var="resource">
            <asset:image src="user.png" class="img-thumbnail; col-xs-2" alt="Responsive image"/>
            <p>${resource.createdBy.fullName}
                <inline style="margin-left:1em; color:#d2d4d9">@${resource.createdBy.userName}
                <span style="float: right"> ${resource.dateCreated} </span></inline>
                <inline style="float:right"></inline>
                <br/>${resource.description}
            </p>

            <p style="float:right">
                <ls:checkType id="${resource.id}"/>

                <a href="#"><u>Mark as Read</u></a>
                <a href="#"><u>View Posts</u></a>
            </p><br>

            <hr>
            %{--<asset:image src="user.png" class="img-thumbnail; col-xs-2" alt="Responsive image"/>
            <p>Uday Pratap Singh.
                <inline style="margin-left:1em; color:#d2d4d9">@Uday</inline>
                <inline style="float:right"></inline>
                <br>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
            dolore magna aliqua.
            </p>

            <div>
                <p style="float:right">
                    <a href="#"><ins>Download</ins></a>
                    <a href="#"><ins>View full Size</ins></a>
                    <a href="#"><ins>Mark as Read</ins></a>
                    <a href="#"><ins>View Posts</ins></a>
                </p>
            </div>--}%
        </g:each>
    </div>
</div> <!--End of inbox-->