/**
 * Created by tim on 31.08.2016.
 */
$(document).ready(function () {
    $("html").niceScroll({cursorcolor: "#a19f98", autohidemode: true});
    $("#phoneBookTable").niceScroll({cursorcolor: "#a19f98", autohidemode: false, cursorwidth: '10px'});
    var attention = $(".inputFormTable .titleTD span");
    var index = 1;

    var reorderRows = function () {
        var rows = $("#phoneBookTable table").find("tbody").find("tr");
        index = rows.length + 1;

        $(rows).each(function (i) {
            $(this).find("td").eq(1).text(i + 1);
        });
    };

    var reFillTable = function () {
        $("#phoneBookTable table").find("tbody").find("tr").each(function (i) {
            $(this).toggleClass("filled", (i + 1) % 2 === 0);
            $(this).toggleClass("unfilled", (i + 1) % 2 !== 0);
        });
    };

    $(".inputFormTable .inputTD").focus(function () {
        $(this).select();
    });

    $("#saveToPhoneBookButton").click(function () {
        //Экранирование пользовательского ввода данных
        function htmlEncode(val){
            return $("<div/>").text(val).html();
        }

        var lastName = htmlEncode($("#lastName").val());
        var firstName = htmlEncode($("#firstName").val());
        var middleName = htmlEncode($("#middleName").val());
        var phoneNumber = htmlEncode($("#phoneNumber").val());
        var comments = htmlEncode($("#comments").val());

        function isContactInPhonebook () {
            var phoneNumbersArray = $("#phoneBookTable").find(".phoneNumber").map(function () {
                return $(this).text();
            });
            return $.inArray(phoneNumber, phoneNumbersArray) < 0;
        }

        var markTDTag = "<td class='mark'><input type='checkbox' class='deleteRecordCheckBox'></td>";
        var indexTDTag = "<td class='indexNumber'>" + index + "</td>";
        var lastNameTDTag = "<td class='lastName'>" + lastName + "</td>";
        var firstNameTDTag = "<td class='firstName'>" + firstName + "</td>";
        var middleNameTDTag = "<td class='middleName'>" + middleName + "</td>";
        var phoneNumberTDTag = "<td class='phoneNumber'>" + phoneNumber + "</td>";
        var commentsTDTag = "<td class='comments'>" + comments + "</td>";
        var delRecTDTag = "<td class='deleteRecord'><img src='img/basket.png' class='confirmed'></td>";

        $("#phoneBookTable").find(".deleteRecordCheckBox:last").change(function () {
            if ($(this).prop('checked')) {
                $(this).attr("checked", "checked");
                $("#delChecked").prop("disabled", false);
            } else {
                $(this).removeAttr("checked");
                $("#delChecked").prop("disabled", true);
            }
        });

        var filledTRTag = "<tr>";
        if (index % 2 === 0) {
            filledTRTag = "<tr class='filled'>";
        }

        if (isContactInPhonebook()) {
            if ((firstName != "" || lastName != "") && phoneNumber != "") {
                $("#phoneBookTable tbody").append(filledTRTag + markTDTag + indexTDTag + lastNameTDTag + firstNameTDTag + middleNameTDTag + phoneNumberTDTag + commentsTDTag + delRecTDTag + "</tr>");
                ++index;
                //clearForm();
                $("#errorMessage").text("");
                attention.attr("class", "");
            } else {
                $("#errorMessage").text("Не заполнены обязательные поля, помеченные звездочкой!");
                attention.attr("class", "attention");
            }
        } else {
            $("#errorMessage").text("Адресат с таким номером телефона уже в адресной книге!");
        }

        // :last потому что событие навешивается на каждый элемент группы, а нужно на один - последний
        // без :last получается, что на первой строке навешивается столько событий, сколько строк в Phonebook
        $("#phoneBookTable .deleteRecord img:last").click(function () {
            if (confirm("Вы уверены, что хотите удалить запись?")) {
                $(this).closest("tr").remove();
                $("div #errorMessage").text("");
                attention.attr("class", "");
                reorderRows();
                reFillTable();
            }
        });

        //Подстановка значений записи тел. книги в поля ввода при клике на строку
        /*$("#phoneBookTable").find("tr").click(function () {
            $("#lastName").val($(this).find("td.lastName:eq(0)").text());
            $("#firstName").val($(this).find("td.firstName:eq(0)").text());
            $("#middleName").val($(this).find("td.middleName:eq(0)").text());
            $("#phoneNumber").val($(this).find("td.phoneNumber:eq(0)").text());
            $("#comments").val($(this).find("td.comments:eq(0)").text());
        });*/
    }); // конец функции нажатия кнопки записи

    $("#filterApply").click(function () {
        $("#phoneBookTable tr").show("slow");
        var filterValue = $("#filter input").val().toLowerCase().replace(/\s+/g, '');
        var allPhonebookRecords = $("#phoneBookTable tr");
        $(allPhonebookRecords).each(function (i, str) {
            if ($(str).text().toLowerCase().indexOf(filterValue) === -1) {
                $(this).hide("slow");
            }
        });
    });

    $("#filterClear").click(function () {
        $("#filter input.filter").val("");
        $("#phoneBookTable tr").show("slow");
    });

    $("#phoneBookTableFixedTitle").find(".deleteRecordCheckBox").click(function () {
        var allCheckboxes = $("#phoneBookTable").find(".deleteRecordCheckBox");
        allCheckboxes.prop('checked', $(this).prop('checked'));
    });

    $(":checkbox").change(function() {
        $("#delChecked").prop("disabled", $(this).is(":not(:checked)"));
    });

    $("#delChecked").click(function () {
        if (confirm("Вы уверены, что хотите удалить все отмеченные записи?")) {
            var allCheckboxes = $("#phoneBookTable").find(".deleteRecordCheckBox");
            var allCheckedCheckboxes = allCheckboxes.filter(":checked");
            $(allCheckedCheckboxes).closest("tr").remove();
            reorderRows();
            reFillTable();
            $("#delAllCheckbox").prop("checked", false);
            $("div #errorMessage").text("");
            $("#delChecked").prop("disabled", true);
        }
    });

    function clearForm() {
        $("#inputFormTableDiv .inputFormTable .inputTD").val("");
        $("#errorMessage").text("");
        var attention = $(".inputFormTable .titleTD span");
        attention.attr("class", "");
    }

    $("#clearForm").click(function () {
        clearForm();
    });

});
