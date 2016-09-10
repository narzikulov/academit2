/**
 * Created by tim on 31.08.2016.
 */
$(document).ready(function () {
    //$("html").niceScroll({cursorcolor:"#a19f98", autohidemode: true});
    //$("#phoneBookTable").niceScroll({cursorcolor:"#a19f98", autohidemode: false});

    var index = 1;
    $("#saveToPhoneBookButton").click(function (){
        var firstName = $("#firstName").val();
        var lastName = $("#lastName").val();
        var middleName = $("#middleName").val();
        var phoneNumber = $("#phoneNumber").val();
        var comments = $("#comments").val();

        var indexTDTag = "<td class='indexNumber'>" + index + "</td>";
        var firstNameTDTag = "<td class='firstName'>" + firstName + "</td>";
        var secondNameTDTag = "<td class='lastName'>" + lastName + "</td>";
        var middleNameTDTag = "<td class='middleName'>" + middleName + "</td>";
        var phoneNumberTDTag = "<td class='phoneNumber'>" + phoneNumber + "</td>";
        var commentsTDTag = "<td class='comments'>" + comments + "</td>";
        var delRecTDTag = "<td class='deleteRecord'><img src='img/basket.png'></td>";

        $("#phoneBookTable tbody").append("<tr>" + indexTDTag + firstNameTDTag + secondNameTDTag + middleNameTDTag + phoneNumberTDTag + commentsTDTag + delRecTDTag + "</tr>");
        ++index;
    });

    $("#clearForm").click(function (){
            var firstName = $("#firstName").val("");
            var lastName = $("#lastName").val("");
            var middleName = $("#middleName").val("");
            var phoneNumber = $("#phoneNumber").val("");
            var comments = $("#comments").val("");
        });

    $("#phoneBookTable td img").click(function (){

    });

});

