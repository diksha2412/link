<div class="panel panel-default"><!--Login Panel-->
    <div class="panel panel-heading">
        <h3 class="panel-title">Login</h3>
    </div>

    <div class="panel-body">
        <g:form class="form-horizontal loginForm" controller="login" action="login">
            <div class="form-group">
                <label for="userName" class="control-label col-xs-2">UserName*</label>

                <div class="col-xs-10">
                    <g:textField name="userName" type="email" class="form-control" id="userName" placeholder="userName" required=""></g:textField>
                </div>
            </div>

            <div class="form-group">
                <label for="inputPassword" class="control-label col-xs-2">Password*</label>

                <div class="col-xs-10">
                    <g:passwordField name="password" class="form-control" id="inputPassword" placeholder="Password" required=""></g:passwordField>
                </div>
            </div>

            <div class="form-group">
                <div style="margin-left:200px">
                    <g:link data-target="#forgotPassword" data-toggle="modal"><ins>Forgot Password</ins></g:link>

                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <g:submit value="login" type="submit" class="btn btn-primary "></g:submit>
                </div>
            </div>
        </g:form>

    </div>
</div>  <!--closure of login panel-->