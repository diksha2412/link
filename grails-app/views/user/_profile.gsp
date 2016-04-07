<div class="panel panel-default"><!--BEGINNING OF PROFILE PANEL-->
    <div class="panel panel-heading">Profile</div>

    <div class="panel-body">
        <g:uploadForm class="form-horizontal" controller="user" action="edit">
            <div class="form-group">
                <label class="control-label col-xs-3">First Name*</label>

                <div class="col-xs-9">
                    <g:textField name="firstName" class="form-control" value="${user.firstName}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-3">Last Name*</label>

                <div class="col-xs-9">
                    <g:textField name="lastName" class="form-control" value="${user.lastName}"/>
                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-xs-3">Username*</label>

                <div class="col-xs-9">
                    <g:textField name="username" value="${user.username}" class="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-3">Photo</label>

                <div class="col-xs-4">
                    <input type="file" name="photo" id="photo">
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-9"></div>

                <div class="col-xs-3">
                    <g:submitButton name="Update" type="submit" class="btn btn-primary"></g:submitButton>
                </div>
            </div>
        </g:uploadForm>
    </div>
</div> <!--END OF PROFILE PANEL-->
