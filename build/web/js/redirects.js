function toRegistration() {
    $.ajax({
        type: 'GET',
        url: 'rest/user/logout',
        dataType: 'json',
        success: function (data) {
            logout();
            $('#content').load('registration.html');
        }
    });
}

function toProfile() {
    $("#content").load("profile.html");
}