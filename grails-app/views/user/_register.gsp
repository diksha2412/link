<div class="panel panel-default"><!--beginning of register panel-->
    <div class="panel panel-heading">Register</div>

    <div class="panel-body">
        <g:uploadForm class="form-horizontal" controller="user" name="registrationForm" id="registrationForm">
            <div class="form-group">
                <label class="col-xs-3 contr">First Name*</label>

                <div class="col-xs-9">
                    <g:textField name="firstName" id="firstName" class="form-control" placeholder="First Name" required=""></g:textField>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-3">Last Name*</label>

                <div class="col-xs-9">
                    <g:textField name="lastName" class="form-control" id="lastName" placeholder="Last Name" required=""></g:textField>
                </div>
            </div>

            <div class="form-group">
                <label for="email" class="control-label col-xs-3">Email*</label>

                <div class="col-xs-9">
                    <g:textField name="email" type="email" class="form-control" id="email" placeholder="Email" required=""></g:textField>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-3">Username*</label>

                <div class="col-xs-9">
                    <g:textField name="userName" class="form-control" id="userName" placeholder="UserName" required=""></g:textField>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-3">Password*</label>

                <div class="col-xs-9">
                    <g:passwordField name="password" type="password" class="form-control" id="password" placeholder="******" required=""></g:passwordField>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-3">Confirm Password*</label>

                <div class="col-xs-9">
                    <g:passwordField name="confirmPassword" class="form-control" id="confirmPassword" placeholder="*****" required=""></g:passwordField>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-3">Photo</label>

                <div class="col-xs-8">
                    <input type="file" id="file" name="file" class="form-control">
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-9"></div>

                <div class="col-xs-3">
                    <g:actionSubmit value="submit" type="submit" class="btn btn-primary" action="register" controller="user"></g:actionSubmit>
                </div>
            </div>
        </g:uploadForm>
    </div>
</div> <!--end of panel-->