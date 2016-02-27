<div id="sharedocument" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <!--Header-->
            <div class="modal-header">
                <span type="button" class="close" data-dismiss="modal">X</span>
                <h4 class="modal-title">Share Document</h4>
            </div>
            <!--Body-->
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="link" class="control-label col-lg-2">Document*</label>
                        <div class="col-lg-7">
                            <input type="text" class="form-control">
                        </div>
                        <div class="col-lg-3">
                            <input type="button" value="browse"></input>
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
                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true">Topic
                                <span class="caret"></span>
                            </button>

                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <li><a href="#">Bootstrap</a></li>
                                <li><a href="#">Groovy</a></li>
                                <li><a href="#">Grails</a></li>
                            </ul>
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