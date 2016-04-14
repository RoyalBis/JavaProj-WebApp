function addProfileBtn(data) {
    $("#profileBtn").show().click(function() {
        $("#content").load("profile.html");
    });
    $("#profileBtn p").html(data.UserName);
}

function addBookingsBtn() {
    $("#bookingsBtn").show().click(function () {
        $.ajax({
            type: 'GET',
            url: 'rest/user/bookings',
            dataType: 'json',
            success: function (data) {
                //isprofile();
                $('#userpg-content').empty();
                getBookings(data);
            }
        });
    });
}

function addLoginBtn() {
    $("#loginBtn").show().click(function () {
        $('#content').load('login.html');
    });
}

function addLogoutBtn() {
    $("#logoutBtn").show().click(function () {
        $.ajax({
            type: 'GET',
            url: 'rest/user/logout',
            dataType: 'json',
            success: function (data) {
                logout();
                $('#content').empty();
            }
        });
    });
}

// function isprofile() {
//     if(!($('#content').has('#pagecontent').length) ) {
//         $("#content").load("profile.html");
//     }
// }


