<div class="panel panel-default"><!--BEGINNING OF CHANGE PASSWORD PANEL-->
    <div class="panel panel-heading">Change Password</div>

    <div class="panel-body">
        <g:form class="form-horizontal changePassword" controller="user" action="changePassword">
            <div class="form-group">
                <label class="control-label col-xs-3">Password*</label>

                <div class="col-xs-9">
                    <g:passwordField name="oldPwd" class="form-control" id="inputPassword"></g:passwordField>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-3">Change Password*</label>

                <div class="col-xs-9">
                    <g:passwordField name="newPwd" class="form-control" id="inputNewPassword"></g:passwordField>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-3">Re-enter Password*</label>

                <div class="col-xs-9">
                    <g:passwordField name="confirmNewPwd" class="form-control" id="inputNewPassword"></g:passwordField>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-9"></div>

                <div class="col-xs-3">
                    <g:actionSubmit value="Update" type="submit" action="changePassword"
                                    class="btn btn-primary"></g:actionSubmit>
                </div>
            </div>
        </g:form>
    </div>
</div>
