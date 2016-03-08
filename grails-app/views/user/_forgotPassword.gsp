<div id="forgotPassword" class="modal-content modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <!--Header-->
    <div class="modal-header">
        <span type="button" class="close" data-dismiss="modal"></span>
        <h4 class="modal-title">Create Topic</h4>
    </div>
    <!--Body-->
    <div class="modal-body">
        <g:form class="form-horizontal">
            <div class="form-group">
                <label for="password" class="control-label col-xs-4">Enter new password*</label>

                <div class="col-xs-8">
                    <g:passwordField name="password" class="form-control" placeholder="Name"></g:passwordField>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="confirmPassword">Re-enter new password*</label>

                <div class="col-xs-8">
                    <g:passwordField name="confirmPassword" class="form-control"
                                     placeholder="Name"></g:passwordField>
                </div>
                <!--Form Closing-->
            </div>
            <!--Footer-->
            <div class="modal-footer">
                %{--<g:actionSubmit value="save" class="btn btn-primary"></g:actionSubmit>--}%
                <button type="button" class="btn btn-primary" data-dismiss="modal">Save</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
            </div>
        </g:form>
    </div>
</div>

