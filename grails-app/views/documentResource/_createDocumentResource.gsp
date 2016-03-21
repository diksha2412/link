<div id="sharedocument" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <span type="button" class="close" data-dismiss="modal">X</span>
                <h4 class="modal-title">Share Document</h4>
            </div>
        <div class="modal-body">
            <g:uploadForm class="form-horizontal" controller="documentResource">
                <div class="form-group">
                    <label class="control-label col-xs-2">Document*</label>

                    <div class="col-lg-7">
                        <input type="file" id="file" class="form-control" name="file">
                    </div>

                </div>

                <div class="form-group">
                    <label class="control-label col-lg-2">Description</label>

                    <div class="col-xs-10">
                        <textarea rows="5" name="description" class="form-control" placeholder="Description"
                                  required></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-lg-2">Topic* :</label>

                    <div class="dropdown col-sm-6">
                        <g:select name="topic" class="dropdown-toggle btn btn-default" from="${subscribedTopics}"
                                  optionKey="id"
                                  noSelection="['': '-Choose topic-']"></g:select>
                    </div>
                </div>
                </div>
                <div class="modal-footer">
                    <g:actionSubmit value="share" action="save" class="btn btn-primary"/>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                </div>
            </g:uploadForm>

        </div>

    </div>
</div>