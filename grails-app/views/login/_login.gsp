<div class="panel panel-default">
    <div class="panel panel-heading">
        <h3 class="panel-title">Login</h3>
    </div>

    <div class="panel-body">
        %{--<g:form class="form-horizontal loginForm" controller="login" action="login">
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
                    <input type="submit" class="btn btn-primary" value="Submit">
                </div>
            </div>
        </g:form>--}%

        <form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
            <p>
                <label for='username'><g:message code="springSecurity.login.username.label"/>:</label>
                <input type='text' class='text_' name='j_username' id='username'/>
            </p>

            <p>
                <label for='password'><g:message code="springSecurity.login.password.label"/>:</label>
                <input type='password' class='text_' name='j_password' id='password'/>
            </p>

            <p id="remember_me_holder">
                <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
            </p>

            <p>
                <input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'/>
            </p>
        </form>

    </div>
</div>