/**
 * Created by tim on 31.08.2016.
 */
$(document).ready(function () {
    $("html").niceScroll({cursorcolor: "#a19f98", autohidemode: true});
    $("#phoneBookTable").niceScroll({cursorcolor: "RGB(173, 217, 83", autohidemode: false, cursorwidth: '10px'});
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

    var delRecordFunction = function delRecord (el){
        $(el).closest("tr").remove();
        $("#errorMessage").text("");
        attention.toggleClass("attention", false);
        reorderRows();
        reFillTable();
    }

    var delSelectedRecordsFunction = function delSelectedRecords (el){
        var allCheckboxes = $("#phoneBookTable").find(".deleteRecordCheckBox");
        var allCheckedCheckboxes = allCheckboxes.filter(":checked").filter(":visible");
        $(allCheckedCheckboxes).closest("tr").remove();
        reorderRows();
        reFillTable();
        $("#delAllCheckbox").prop("checked", false);
        $("#errorMessage").text("");
        $("#delChecked").prop("disabled", true);
    }

    function showConfirm(el, func) {
        $.confirm({
            title: "Удаление записи!",
            content: "Вы уверены, что хотите удалить запись?",
            confirmButton: "Да",
            cancelButton: "Нет",
            backgroundDismiss: true,
            //theme: "supervan", //прозрачный затененный фон окна подтверждения
            theme: "white",
            closeIcon: true,
            keyboardEnabled: true,
            columnClass: 'col-md-6 col-md-offset-4',
            container: "#allPhoneBookTable",
            confirm: function(){func(el)},
            cancel: function(){
            }
        })
    }

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

        var filledTRTag = "<tr>";
        if (index % 2 === 0) {
            filledTRTag = "<tr class='filled'>";
        }

        //TODO uncomment
        if (isContactInPhonebook()) {
            if ((firstName != "" || lastName != "") && phoneNumber != "") {
                $("#phoneBookTable tbody").append(filledTRTag + markTDTag + indexTDTag + lastNameTDTag + firstNameTDTag + middleNameTDTag + phoneNumberTDTag + commentsTDTag + delRecTDTag + "</tr>");
                ++index;
                //TODO uncomment
                clearForm();
                $("#errorMessage").text("");
                attention.toggleClass("attention", false);

                // :last потому что событие навешивается на каждый элемент группы, а нужно на один - последний
                // без :last получается, что на первой строке навешивается столько событий, сколько строк в Phonebook
                $("#phoneBookTable .deleteRecord img:last").click(function () {
                    var el = this;
                    showConfirm(el, delRecordFunction);
                });

                $(":checkbox").change(function () {
                    $("#delChecked").prop("disabled", $(":checked").length === 0);
                });
            } else {
                $("#errorMessage").text("Не заполнены обязательные поля, помеченные звездочкой!");
                attention.toggleClass("attention", true);
            }
        } else {
            $("#errorMessage").text("Адресат с таким номером телефона уже в адресной книге!");
        }

    }); // конец функции нажатия кнопки записи

    $("#filterApply").click(function () {
        $("#phoneBookTable tr").show("slow");
        var filterValue = $("#filter input").val().toLowerCase().replace(/\s+/g, '');
        var allPhonebookRecords = $("#phoneBookTable tr");
        $(allPhonebookRecords).each(function (i, str) {
            if ($(str).text().toLowerCase().indexOf(filterValue) === -1) {
                $(this).hide();
            }
        });

        var allVisibleCheckedCheckboxes = $("#phoneBookTable").find(".deleteRecordCheckBox").filter(":checked").filter(":visible");
        $("#delChecked").prop("disabled", allVisibleCheckedCheckboxes.length === 0);

    });

    $("#filterClear").click(function () {
        $("#filter input.filter").val("");
        $("#phoneBookTable tr").show("slow");
        var allVisibleCheckedCheckboxes = $("#phoneBookTable").find(".deleteRecordCheckBox").filter(":checked").filter(":visible");
        if (allVisibleCheckedCheckboxes.length > 0) {
            $("#delChecked").prop("disabled", false);
        }
    });

    $("#phoneBookTableFixedTitle").find(".deleteRecordCheckBox").change(function () {
        var allCheckboxes = $("#phoneBookTable").find(".deleteRecordCheckBox").filter(":checked").filter(":visible");
        $("#delChecked").prop("disabled", allVisibleCheckedCheckboxes.length > 0);
    });

    $("#delChecked").click(function () {
    var el = 0;
        showConfirm(el, delSelectedRecordsFunction);
    });

    function clearForm() {
        $("#inputFormTableDiv .inputFormTable .inputTD").val("");
        $("#errorMessage").text("");
        var attention = $(".inputFormTable .titleTD span");
        attention.toggleClass("attention", false);
    }

    $("#clearForm").click(function () {
        clearForm();
    });
});
