<div id="forgotPassword" class="modal-content modal fade modal-dialog" role="dialog" aria-labelledby="myModalLabel">
    <!--Header-->
    <div class="modal-header">
        <span type="button" class="close" data-dismiss="modal"></span>
        <h4 class="modal-title">Forgot Password</h4>
    </div>
    <!--Body-->
    <div class="modal-body">
        <g:form class="form-horizontal" controller="user" action="sendForgetPasswordEmail">
            <div class="form-group">
                <label class="control-label col-xs-4">Enter your e-mailID*</label>

                <div class="col-xs-8">
                    <g:field type="email" name="emailID" class="form-control"/>
                </div>
            </div>

                     <div class="modal-footer">
                <g:submitButton class="btn btn-primary" name="save" type="submit" value="send"/>

                <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
            </div>
        </g:form>
    </div>
</div>