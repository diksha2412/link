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
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="link" class="control-label col-lg-2">Link*</label>
                        <div class="col-lg-10">
                            <input type="url" class="form-control" placeholder="Link">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-lg-2">Description</label>
                        <div class="col-xs-10">
                            <textarea rows="5" class="form-control" placeholder="Description" required></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-2" for="pwd">Topic* :</label>
                        <div class="dropdown col-sm-6">

                            <g:select name="topic" from="${}"
                                      noSelection="['':'-Choose topic-']"/>

                        </div>
                    </div>
                </form>
                <!--Form Closing-->
            </div>
            <!--Footer-->
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" >Share</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
            </div>

        </div>
    </div>
</div>
<!--END OF SHARE LINK MODAL-->
