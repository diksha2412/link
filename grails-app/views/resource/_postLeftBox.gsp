<div class="panel panel-default"><!--FIRST PANEL-->
    <div class="panel-body">
        <div class="row-sm-5">
            <asset:image src="user.png" class="img-thumbnail; col-xs-3" alt="Responsive image"/>
            <inline>
                <span style="float:left">${resource.createdBy.fullName}</span>
                <span style="float:right"><a href="#">${resource.description}</a></span>
            </inline><br>
            <span style="float:left; color:grey">@${resource.createdBy.userName}</span>
            <span style="float:right; color:grey">2:45 PM 22 FEB 2014</span><br><br>

            <span style="float: right">

                <g:select name="score" from="[1,2,3,4,5]"
                          noSelection="['':'select score']"/>

                <g:submitButton name="vote" action="save" controller="ResourceRating"></g:submitButton>
            </span>

        </div><br><br>


        <div class="row-sm-7">
            <p class="text:justify"
               style="font-size:50px; margin-left:1em">${resource.description}</p>
            <br>

            <div style="float:right">
                <ls:deleteResource resource="${resource}"/> &nbsp;&nbsp;
                <a href="#">Edit</a>&nbsp;&nbsp;
                <a href="#">Download</a>&nbsp;&nbsp;
                <a href="#">View full site</a>
            </div>
        </div>
    </div> <!--END OF PANEL BODY-->
</div> <!--END OF PANEL-->
