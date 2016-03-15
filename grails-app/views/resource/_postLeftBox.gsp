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
                <g:form controller="resourceRating" action="save" params="[resourceId: resource.id]">
                    <g:select name="score" from="[1, 2, 3, 4, 5]"
                              value="${com.ttnd.linksharing.ResourceRating.findByResource(resource)?.score}"
                              noSelection="['': 'select score']"/>

                %{--<g:hiddenField name="score" value="${resource.id}"/>--}%

                    <g:submitButton name="vote"></g:submitButton>
                </g:form>
            </span>

        </div><br><br>

        <div class="row-sm-7">
            <p class="text:justify"
               style="font-size:50px; margin-left:1em">${resource.description}</p>
            <br>

            <div style="float:right">
                <ls:deleteResource resource="${resource}"/> &nbsp;&nbsp;
                <g:render template="edit" model="[resource: resource]"/>
                <g:link data-toggle="modal" data-target="#editDescription">Edit</g:link>

                <ls:checkType id="${resource.id}"></ls:checkType>

            </div>
        </div>
    </div>
</div>
