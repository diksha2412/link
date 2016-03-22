<div id="createtopic" class="modal fade"><!--BEGINNING OF CREATE TOPIC MODAL-->
    <div class="modal-dialog">
        <div class="modal-content">
            <!--Header-->
            <div class="modal-header">
                <span type="button" class="close" data-dismiss="modal">X</span>
                <h4 class="modal-title">Create Topic</h4>
            </div>

            <div class="modal-body">

                <g:form class="form-horizontal createTopic" controller="topic" action="save">
                    <div class="form-group">
                        <label class="control-label col-lg-2">Topic Name*</label>

                        <div class="col-lg-10">
                            <g:textField name="name" class="form-control" placeholder="Name" required=""></g:textField>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="visibility">Visibility*</label>

                        <div class="dropdown col-sm-6">

                            <g:select class="dropdown-toggle btn btn-default" name="visibility"
                                      from="${com.enums.Visibility.values()}"
                                      noSelection="['': 'select visibility']" />

                        </div>
                    </div>
                    <!--Footer-->
                    <div class="modal-footer">

                    <g:submitButton name="save" value="save" class="btn btn-primary"></g:submitButton>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                </g:form>
            </div>
            </div>

        </div></div></div>