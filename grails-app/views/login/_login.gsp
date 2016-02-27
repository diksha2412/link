<div class="panel panel-default" style="float: right"><!--Login Panel-->
    <div class="panel panel-heading">Login</div>

    <div class="panel-body">
        <g:form class="form-horizontal">
            <div class="form-group">
                <label for="userName" class="control-label col-xs-2">Username</label>

                <div class="col-xs-10">
                    <g:textField name="userName" placeholder="userName"/>
                </div>
            </div>

            <div class="form-group">
                <label for="inputPassword" class="control-label col-xs-2">Password</label>

                <div class="col-xs-10">
                    <g:passwordField name="password" id="inputPassword" placeholder="Password"></g:passwordField>
                </div>
            </div>

            <div class="form-group">
                <div style="margin-left:200px">
                    <a href="#"><ins>Forgot Password</ins></a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                    <g:actionSubmit value="login" action="login"></g:actionSubmit>
                </div>
            </div>
        </g:form>

    </div>
</div>