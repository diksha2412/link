// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require jquery
//= require_tree .
//= require_self


function ajaxSuccess(result) {

    if (result) {
        var jsonResponseDiv = $(".jsonResponse");

        if (result.message) {

            alert("succcess")
            jsonResponseDiv.text(result.message);
            jsonResponseDiv.addClass("alert alert-success");
        }
        else {
            alert("error")
            jsonResponseDiv.text(result.error);
            jsonResponseDiv.addClass("alert alert-danger");
        }
        jsonResponseDiv.css({'display': 'block'})
    }
}

/*
(document).ready(function () {
    $(".markReadStatus").click(function(){
        $.ajax({
            url: "/readingItem/changeIsRead",
            data: {resourceId: $(this).attr('resourceId'), isRead: $(this).attr('isRead')},
            success: location.reload()
        });
    });


}
    $('.subscription').click(function (e) {
        e.preventDefault();
        $.ajax({
            url: "/subscription/delete",
            data: {topicId: $(this).attr('topicId')},
            success: ajaxSuccess
        });
    });
);
*/
