function getBookings(data){
    $('#userpg-content').append("<h3>Bookings</h3>");
    for(var i = 0; i < data.length; i++) {
        buildBookings(i,data[i]);
    }
}

function buildBookings(i,booking) {
    var id = "booking" + i;
    var bookingsHtml =
            "<div class='BookingId'><p>"+booking.BookingId+"</p></div>" +
            "<div>" +
            "<div class='BookingInfo vCenter-hFlexStart'>" +
            "<div class='BookingNo vCenter-hFlexStart'><p class='label'>Booking#:&nbsp;</p><p>"+booking.BookingNo+"</p></div>" +
            "<div class='BookingDate vCenter-hFlexStart'><p class='label'>Booking Date:&nbsp;</p><p>"+booking.BookingDate+"</p></div>" +
            "<div class='TravelerCount vCenter-hFlexStart'><p class='label'>Num Travelers:&nbsp;</p><p>"+booking.TravelerCount+"</p></div>" +
            "</div>" +
            "<div class='BookingDetails vCenter-hFlexStart'><p class='label'>Booking Details:&nbsp;</p></div>";
    for(var n = 0; n < booking.BookingDetails.length; n++) {
        var detail = booking.BookingDetails[n];
        bookingsHtml +=
                "<div class='Detail'>" +
                "<div class='ItineraryNo vCenter-hFlexStart'><p class='label'>Itinerary No:&nbsp;</p><p>"+detail.ItineraryNo+"</p></div>" +
                "<div class='vCenter-hSpaceBetween'>" +
                "<div class='TripStart vCenter-hFlexStart'><p class='label'>Trip Start:&nbsp;</p><p>"+detail.TripStart+"</p></div>" +
                "<div class='TripEnd vCenter-hFlexStart'><p class='label'>Trip End:&nbsp;</p><p>"+detail.TripEnd+"</p></div>" +
                "</div>" +
                "<div class='vCenter-hSpaceBetween'>" +
                "<div class='Description'><p class='label'>Description:&nbsp;</p><p>"+detail.Description+"</p></div>" +
                "<div class='Destination'><p class='label'>Destination:&nbsp;</p><p>"+detail.Destination+"</p></div>" +
                "</div>" +
                "<div class='BasePrice vCenter-hFlexEnd'><p class='label'>BasePrice:&nbsp;</p><p>$"+detail.BasePrice+".00</p></div>" +
                "</div>";
    }
    bookingsHtml += "</div>";
    $('#userpg-content').append("<div id='"+id+"' class='vCenter-hFlexStart material'>"+bookingsHtml+"</div>");
}
