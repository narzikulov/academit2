/**
 * Created by tim on 31.08.2016.
 */
$(document).ready(function () {
    $("html").niceScroll({cursorcolor: "#a19f98", autohidemode: true});
    $("div #phoneBookTable").niceScroll({cursorcolor: "#a19f98", autohidemode: false, cursorwidth: '10px'});

    var index = 1;

    var reorderRows = function () {
        var rows = $("#phoneBookTable table").find("tbody").find("tr");
        index = rows.length + 1;

        $(rows).each(function (i) {
            $(this).find("td").eq(0).text(i + 1);
        });
    };

    var reFillTable = function () {
        $("#phoneBookTable table").find("tbody").find("tr").each(function (i) {
            if ((i + 1) % 2 === 0) {
                this.setAttribute("class", "filled");
            } else {
                this.setAttribute("class", "unfilled");
            }
        });
    };

    $(".inputFormTable .inputTD").focus(function () {
        $(this).select();
    });

    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();
    var middleName = $("#middleName").val();
    var phoneNumber = $("#phoneNumber").val();
    var comments = $("#comments").val();

    function isContactInPhonebook () {
        //var findFirstName = alert($("#phoneBookTable .firstName").has(firstName).length);
    }

    $("#saveToPhoneBookButton").click(function () {
        var indexTDTag = "<td class='indexNumber'>" + index + "</td>";
        var firstNameTDTag = "<td class='firstName'>" + firstName + "</td>";
        var secondNameTDTag = "<td class='lastName'>" + lastName + "</td>";
        var middleNameTDTag = "<td class='middleName'>" + middleName + "</td>";
        var phoneNumberTDTag = "<td class='phoneNumber'>" + phoneNumber + "</td>";
        var commentsTDTag = "<td class='comments'>" + comments + "</td>";
        var delRecTDTag = "<td class='deleteRecord'><img src='img/basket.png'><input type='checkbox' class='deleteRecordCheckBox'></td>";

        $("#phoneBookTable .deleteRecordCheckBox").click(
            function () {
                if ($(this).prop('checked')) {
                    $(this).attr("checked", "checked");
                } else {
                    $(this).removeAttr("checked");
                }
            }
        );

        var filledTRTag = "<tr>";
        if (index % 2 === 0) {
            filledTRTag = "<tr class='filled'>";
        }

        var attention = $(".inputFormTable .titleTD span");
        if ((firstName != "" || lastName != "") && phoneNumber != "") {
            isContactInPhonebook();
            $("#phoneBookTable tbody").append(filledTRTag + indexTDTag + firstNameTDTag + secondNameTDTag + middleNameTDTag + phoneNumberTDTag + commentsTDTag + delRecTDTag + "</tr>");
            ++index;
            //clearForm();
            $("#errorMessage").text("");
            attention.attr("class", "");
        } else {
            $("div #errorMessage").text("Не заполнены обязательные поля, помеченные звездочкой!");
            attention.attr("class", "attention");
        }


    }); // конец функции нажатия кнопки записи

    $(".deleteRecord img").click(function () {
        if (confirm("Удалить?")) {
            $("div #errorMessage").text("");
            attention.attr("class", "");
            $(this).closest("tr").remove();
            reorderRows();
            reFillTable();
        }
    });

    $("#phoneBookTableFixedTitle .deleteRecordCheckBox").click(
        function () {
            var allCheckboxes = $("#phoneBookTable").find(".deleteRecordCheckBox");
            if ($(this).prop('checked')) {
                allCheckboxes.each(function () {
                    this.checked = true;
                });
            } else {
                allCheckboxes.each(function () {
                    this.checked = false;
                });
            }
        }
    );

    $("#delChecked").click(function () {
        var allCheckboxes = $("#phoneBookTable").find(".deleteRecordCheckBox");
        var allCheckedCheckboxes = allCheckboxes.filter(
            function () {
                return $(this).prop("checked");
            }
        );
        $(allCheckedCheckboxes).closest("tr").remove();
        reorderRows();
        reFillTable();
        document.getElementById('delAllCheckbox').checked = false;
    });

    function clearForm() {
        $("#firstName").val("");
        $("#lastName").val("");
        $("#middleName").val("");
        $("#phoneNumber").val("");
        $("#comments").val("");
        $("div #errorMessage").text("");
        var attention = $(".inputFormTable .titleTD span");
        attention.attr("class", "");
    };

    $("#clearForm").click(function () {
        clearForm();
    });

});
