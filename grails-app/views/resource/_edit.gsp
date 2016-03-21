<div id="editDescription" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <!--Header-->
            <div class="modal-header">
                <span type="button" class="close" data-dismiss="modal">X</span>
                <h4 class="modal-title">Edit Resource</h4>
            </div>
            <!--Body-->
            <div class="modal-body">
                <g:form class="form-horizontal" controller="resource" action="edit">
                    <g:hiddenField name="resourceId" value="${resource.id}"/>

                    <div class="form-group">
                        <label class="control-label col-lg-2">Description</label>

                        <div class="col-xs-10">
                            <g:textArea rows="5" name="description" class="form-control" placeholder="Description" required="required"></g:textArea>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <g:actionSubmit value="Update" action="edit" class="btn btn-primary"/>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                    </div>
                </g:form>

            </div>

        </div>
    </div>
</div>