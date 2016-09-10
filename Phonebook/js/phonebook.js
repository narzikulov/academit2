/**
 * Created by tim on 31.08.2016.
 */
$(document).ready(function () {
    $("html").niceScroll({cursorcolor:"#a19f98", autohidemode: true});
    $("div #phoneBookTable").niceScroll({cursorcolor:"#a19f98", autohidemode: false, cursorwidth: '10px'});

    var index = 1;

    var reorderRows = function() {
        var rows = $("#phoneBookTable table").find("tbody").find("tr");
        index = rows.length + 1;

        $(rows).each(function(i) {
            $(this).find("td").eq(0).text(i + 1);
            //if ((i + 1) % 2 === 0) {
//                $(this).setAttribute("class", "filled");
//            }
        });
    };

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
        var delRecTDTag = "<td class='deleteRecord'><img src='img/basket.png'><input type='checkbox' class='deleteRecordCheckBox'></td>";

        var filledTRTag = "<tr>";
        if (index % 2 === 0) {
            filledTRTag = "<tr class='filled'>";
        }

        if ((firstName != "" || lastName != "") && phoneNumber != "") {
            $("#phoneBookTable tbody").append(filledTRTag + indexTDTag + firstNameTDTag + secondNameTDTag + middleNameTDTag + phoneNumberTDTag + commentsTDTag + delRecTDTag + "</tr>");
            ++index;
            //clearForm;
            $("div #errorMessage").text("");
        } else {
            $("div #errorMessage").text("Не заполнены обязательные поля, помеченные звездочкой!");
        }

        $(".deleteRecord img").click(function () {
            $(this).closest("tr").remove();
            reorderRows();
        });

    });


    var clearForm = function (){
                var firstName = $("#firstName").val("");
                var lastName = $("#lastName").val("");
                var middleName = $("#middleName").val("");
                var phoneNumber = $("#phoneNumber").val("");
                var comments = $("#comments").val("");
    }

    $("#clearForm").click(clearForm);

    $("#phoneBookTable td img").click(function (){

    });

});

