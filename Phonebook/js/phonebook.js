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
                //this.setAttribute("class", "filled");
                $(this).toggleClass("filled", true);
                $(this).toggleClass("unfilled", false);
            } else {
                //this.setAttribute("class", "unfilled");
                $(this).toggleClass("filled", false);
                $(this).toggleClass("unfilled", true);
            }
        });
    };

    $(".inputFormTable .inputTD").focus(function () {
        $(this).select();
    });

    $("#saveToPhoneBookButton").click(function () {
        var firstName = $("#firstName").val();
        var lastName = $("#lastName").val();
        var middleName = $("#middleName").val();
        var phoneNumber = $("#phoneNumber").val();
        var comments = $("#comments").val();

        function isContactInPhonebook () {
            var phoneNumbersArray = $("#phoneBookTable").find(".phoneNumber").map(function () {
                return $(this).text();
                });
            return $.inArray(phoneNumber, phoneNumbersArray) < 0;
        }

        var indexTDTag = "<td class='indexNumber'>" + index + "</td>";
        var firstNameTDTag = "<td class='firstName'>" + firstName + "</td>";
        var secondNameTDTag = "<td class='lastName'>" + lastName + "</td>";
        var middleNameTDTag = "<td class='middleName'>" + middleName + "</td>";
        var phoneNumberTDTag = "<td class='phoneNumber'>" + phoneNumber + "</td>";
        var commentsTDTag = "<td class='comments'>" + comments + "</td>";
        var delRecTDTag = "<td class='deleteRecord'><img src='img/basket.png'><input type='checkbox' class='deleteRecordCheckBox'></td>";

        $("#phoneBookTable").find(".deleteRecordCheckBox").click(
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

        if (isContactInPhonebook()) {
            if ((firstName != "" || lastName != "") && phoneNumber != "") {
                $("#phoneBookTable tbody").append(filledTRTag + indexTDTag + firstNameTDTag + secondNameTDTag + middleNameTDTag + phoneNumberTDTag + commentsTDTag + delRecTDTag + "</tr>");
                ++index;
                //clearForm();
                $("#errorMessage").text("");
                attention.attr("class", "");
            } else {
                $("div #errorMessage").text("Не заполнены обязательные поля, помеченные звездочкой!");
                attention.attr("class", "attention");
            }
        } else {
            $("div #errorMessage").text("Адресат с таким номером телефона уже в адресной книге!");
        }

        $(".deleteRecord img").click(function () {
            $("div #errorMessage").text("");
            attention.attr("class", "");
            $(this).closest("tr").remove();
            reorderRows();
            reFillTable();
        });

        $("#phoneBookTable").find("tr").click(function () {
            $("#firstName").val($(this).find("td.firstName:eq(0)").text());
            $("#lastName").val($(this).find("td.lastName:eq(0)").text());
            $("#middleName").val($(this).find("td.middleName:eq(0)").text());
            $("#phoneNumber").val($(this).find("td.phoneNumber:eq(0)").text());
            $("#comments").val($(this).find("td.comments:eq(0)").text());
        });


    }); // конец функции нажатия кнопки записи

    $("#filterApply").click(function () {
        var filterValue = $("#filter input").val();
        var allPhonebookRecords = $("#phoneBookTable tr");
        $(allPhonebookRecords).each(function (i, str) {
            //alert($(str).text().indexOf(filterValue));
            if ($(str).text().indexOf(filterValue) === -1) {
                $(this).hide("slow");
            }
        });
    });

    $("#filterClear").click(function () {
        $("#filter input.filter").val("");
        $("#phoneBookTable tr").show("slow");
    });

    $("#phoneBookTableFixedTitle").find(".deleteRecordCheckBox").click(
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
        $("div #errorMessage").text("");
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
    }

    $("#clearForm").click(function () {
        clearForm();
    });

});
