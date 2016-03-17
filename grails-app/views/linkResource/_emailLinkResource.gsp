<%@ page import="com.ttnd.linksharing.User" %>
<div id="sharelink" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <!--Header-->
            <div class="modal-header">
                <span type="button" class="close" data-dismiss="modal">X</span>
                <h4 class="modal-title">Share Link</h4>
            </div>
            <!--Body-->
        <div class="modal-body">
            <g:form name="emailForm" class="form-horizontal" action="create" controller="linkResource">
                <div class="form-group">
                    <label for="link" class="control-label col-lg-2">Link*</label>

                    <div class="col-lg-10">
                        <g:textField name="link" class="form-control" placeholder="Link"></g:textField>
                    </div>
                </div>

                <div class="form-group">
                    <label for="description" class="control-label col-lg-2">Description</label>

                    <div class="col-xs-10">
                        <g:textArea name="description" rows="5" class="form-control"
                                    placeholder="Description"></g:textArea>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-lg-2" for="topicName">Topic* :</label>

                    <div class="dropdown col-sm-6">

                        <g:select name="topicName" class="dropdown-toggle btn btn-default" from="${subscribedTopics}" optionKey="id"
                                  noSelection="['': '-Choose topic-']"/>

                    </div>
                </div>

                <!--Form Closing-->
            </div>
            <!--Footer-->
            <div class="modal-footer">
                <g:submitButton name="share" value="Share" type="submit" class="btn btn-primary"
                                controller="linkResource" action="create"></g:submitButton>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
            </g:form>
        </div>
        </div>
    </div>
</div>
</div>
<!--END OF SHARE LINK MODAL-->
