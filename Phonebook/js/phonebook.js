/**
 * Created by tim on 31.08.2016.
 */
$(document).ready(function () {
    $("html").niceScroll({cursorcolor:"#a19f98", autohidemode: true});
    $("#phoneBookTable").niceScroll({cursorcolor:"#a19f98", autohidemode: false});

    var index = 1;
    $("#saveToPhoneBookButton").click(function (){
        var firstName = $("#firstName").val();
        var secondName = $("#secondName").val();
        var middleName = $("#middleName").val();
        var phoneNumber = $("#phoneNumber").val();
        var comments = $("#comments").val();

        alert(firstName + secondName + middleName + phoneNumber + comments);
    });

});

