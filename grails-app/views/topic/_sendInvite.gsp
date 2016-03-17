<div id="sendinvitation" class="modal fade"><!--BEGINNING OF SEND INVITATION MODAL-->
    <div class="modal-dialog">
        <div class="modal-content">
            <!--Header-->
            <div class="modal-header">
                <span type="button" class="close" data-dismiss="modal">X</span>
                <h4 class="modal-title">Send Invitation</h4>
            </div>
            <!--Body-->
        <div class="modal-body">
            <g:form class="form-horizontal" controller="topic" action="invite">
                <div class="form-group">
                    <label for="email" class="control-label col-lg-2">Email*</label>

                    <div class="col-lg-10">
                        <g:field type="email" class="form-control" placeholder="Email" name="email"></g:field>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-lg-2" for="topic">Topic*</label>

                    <div class="dropdown col-sm-6">
                        <g:select class="dropdown-toggle btn btn-default" name="topic" from="${subscribedTopics}" optionKey="id" noSelection="['': '-Choose topic-']"/>
                    </div>
                    <!--Form Closing-->
                </div>
                <!--Footer-->
                <div class="modal-footer">
                    <g:submitButton name="Invite" class="btn-primary btn" type="submit" value="Invite"/>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                </div>
                </div>

            </g:form>
        </div>
    </div>
</div>
