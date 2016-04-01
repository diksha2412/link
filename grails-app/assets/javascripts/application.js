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

jQuery(document).ready(function () {
    $(".markReadStatus").click(function (e) {
        e.preventDefault()
        alert(jQuery(this).data('resourceid'))
        $.ajax({
            url: "/readingItem/changeIsRead",
            data: {resourceId: $(this).data('resourceid'), isRead: $(this).data('isread')},
            success: location.reload()
        });
    });

   /* $(".loginForm").submit(function () {
        $.ajax({
            url: "/login/login",
            data: {userName: $(this).attr('topicId'), serious: $(this).val()},
            success: ajaxSuccess()
        });
    });
*/

    $('.subscription').click(function (e) {
        alert(jQuery(this).attr('topicId'))
        e.preventDefault();
        $.ajax({
            url: "/subscription/delete",
            data: {topicId: $(this).attr('topicId')},
            success: function(data){
                $("#subscriptions").html(data);
            }
        });
    });

    $('.subscriptionSave').click(function (e) {
        alert(jQuery(this).attr('topicId'))
        e.preventDefault();
        $.ajax({
            url: "/subscription/save",
            data: {topicId: $(this).attr('topicId')},
            success: location.reload()
        });
    });


    $(".seriousness").change(function () {
        $.ajax({
            url: "/subscription/update",
            data: {topicId: $(this).attr('topicId'), serious: $(this).val()},
            success: ajaxSuccess()
        });
    });

    $(".visibility").change(function () {
        $.ajax({
            url: "/topic/update",
            data: {topicId: $(this).attr('topicId'), visibility: $(this).val()},
            success: ajaxSuccess()
        });
    });

    $(".edit-topic").on('click',function(){
        var topicId=$(this).attr('data-topicId');
        alert(topicId);
        $("#editForm"+topicId).css({'display': 'block'});
    });

    $(".saveTopicNameButton").click(function () {
        var topicId = $(this).attr('topicId')
        $.ajax({
            url: "/topic/titleUpdate",
            data: {topicId: topicId, title: $("#name" + topicId).val()},
            success: ajaxSuccess()
        })
    });

    $(".cancelTopicNameButton").on('click',function(e){
        e.preventDefault()
        var topicId=$(this).attr('topicId');
        alert(topicId);
        $("#editForm"+topicId).css({'display': 'none'});
    });


    $(".topicDelete").click(function (e) {
        var topicId = $(this).attr('topicId');
        alert(topicId);
        e.preventDefault();
        $.ajax({
            url: "/topic/delete",
            data: {topicId: topicId},
            success: ajaxSuccess()
        });
    });

    $(function () {
        $('#registrationForm').validate({
            rules: {
                'firstName': {
                    required: true
                },
                'lastName': {
                    required: true
                },
                'password': {
                    required: true,
                    minlength: 5
                },
                'confirmPassword': {
                    required: true,
                    confirm: true,
                    equalTo: "#password"
                },
                'userName': {
                    required: true,
                    remote: {
                        url: "/login/validateUserName",
                        type: "post"
                    }
                },
                'email': {
                    required: true,
                    email: true,
                    remote: {
                        url: "/login/validateEmail",
                        type: "post"
                    }
                }
            },
            messages: {
                'firstName': {
                    required: "First name can't be blank"
                },
                'lastName': {
                    required: "Last name can't be blank"
                },
                'password': {
                    required: "Password can't be blank",
                    minlength: "Password should be at least 5 character long"
                },
                'confirmPassword': {
                    required: "Confirm password can't be blank",
                    equalTo: "passwords don't match"
                },
                'email': {
                    required: "Email address can't be blank",
                    remote: "Email address entered is already used"
                },
                'username': {
                    required: "User name can't be blank",
                    remote: "User name entered already exist"
                }
            }
        });

        $('.resourceSearch').validate({
            rules: {
                'queryString' : {
                    required: true
                }
            },
            messages : {
                'queryString': {
                    required: "enter the resources you want to search for"
                }
            }
        });

        $('.forgotPassword').validate({
            rules: {
                'emailID' : {
                    required: true,
                    remote: {
                        url: "/login/validateEmail",
                        type: "post"
                    }
                }
            },
            messages : {
                'emailID': {
                    required: "enter the email ID",
                    remote: "This email ID is not taken. Enter a valid one."
                }
            }
        });

        $('.createTopic').validate({
            rules: {
                'name' : {
                    required: true,
                    remote: {
                        url: "/topic/validateName",
                        type: "post"
                    }
                }
            },
            messages : {
                'name': {
                    required: "enter the name of the topic",
                    remote: "topic with given name already exists"
                }
            }
        });

        $('.changePassword').validate({
            rules: {
                'oldPwd' : {
                    required: true,
                    remote: {
                        url: "/user/validatePassword",
                        type: "post"
                    }
                },
                'newPwd':{
                    required: true,
                    minlength: 5
                },
                'confirmNewPwd':{
                    required: true,
                    equalTo: '#newPwd'
                },
            },
            messages : {
                'oldPwd': {
                    required: "enter the old password",
                    remote: "incorrect password !!"
                },
                'newPwd': {
                    required: "enter the new password",
                    minlength: "Password should be at least 5 character long"
                },
                'confirmNewPwd': {
                    required: "re-enter new password",
                    equalTo: "Passwords don't match !!"
                }
            }
        });
    });
});
