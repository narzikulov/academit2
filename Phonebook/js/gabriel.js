/**
 * Created by tim on 31.08.2016.
 */
$(document).ready(function () {
    $("html").niceScroll({cursorcolor:"#a19f98", autohidemode: true});
    $("#booking-table").niceScroll({cursorcolor:"#a19f98", autohidemode: false});
    $("#prev-orders-table").niceScroll({cursorcolor:"#a19f98", autohidemode: false});


    //var bookingTable = document.querySelectorAll("#booking-table table tr");
    var index = 0;
    var bookingTableDemandETValueArray = $("#booking-table table tr");
    bookingTableDemandETValueArray.each(function () {
        var value = 0;

        var sliderID = "etSlider" + index;
        var sliderValID = "etSliderVal" + index;

        $(this).find("div[name=slider]").attr("id", sliderID);
        $(this).find("input[name=sliderVal]").attr("id", sliderValID);

        sliderID = "#" + sliderID;
        sliderValID = "#" + sliderValID;

        $(sliderID).slider({
            value: 0,
            min: 0,
            max: 1000,
            step: 10,
            create: function (event, ui) {
                value = $(sliderID).slider("value");
                $(sliderValID).val(ui.val);//Заполняем этим значением элемент с id

                var sliderValueTooltipTag = "<div class='tooltip'></div>";
                //var sliderValueTooltipTag = "<div class='tooltip" + index + "'></div>";
                //alert(sliderValueTooltipTag);
                $(sliderID + " > span").append(sliderValueTooltipTag);
            },
            slide: function (event, ui) {
                if (ui.value > 99) {
                    $(sliderID).slider("option", "step", 100);
                }
                if (ui.value <= 100) {
                    $(sliderID).slider("option", "step", 10);
                }

                $(sliderValID).val(ui.value);//При изменении значения ползунка заполняем элемент с id contentSlider

                //Вывод значения над слайдером
                //$(sliderID + " > span").text($(sliderValID).val());
                $(sliderID + " > span div").text($(sliderValID).val());


            }
        });

        value = $(sliderID).slider("value");
        $(sliderValID).val(value);

        // Проверка на ввод числа. Бездействие при попытке ввести не цифры или вставить не цифры с помощью мыши
        $(sliderValID).bind("change keyup input click", function() {
            if (this.value.match(/[^0-9]/g)) {
                this.value = this.value.replace(/[^0-9]/g, '');
            }
        });

        // Если поле ET пустое, то скрипт заменяет на 0
        $(sliderValID).blur(function () {
            if ($(sliderValID).val() === "") {
                $(sliderValID).val("0");
            }
        });

        $(sliderValID).change(function () {
             $(sliderValID).val(Math.round($(sliderValID).val() / 10) * 10);

            if ($(sliderValID).val() > $(sliderID).slider("option", "max")) {
                //alert("Max value " + $(sliderID).slider("option", "max"))
                $(sliderID).slider("value", $(sliderID).slider("option", "max"));
                $(sliderValID).val($(sliderID).slider("option", "max"));
            } else if ($(sliderValID).val() < $(sliderID).slider("option", "min")) {
                //alert("Min value " + $(sliderID).slider("option", "min"))
                $(sliderID).slider("value", $(sliderID).slider("option", "min"));
                $(sliderValID).val($(sliderID).slider("option", "min"));
            } else {
                $(sliderID).slider("value", $(sliderValID).val());
            }
            $(sliderID + " > span div").text($(sliderValID).val());
        });

        ++index;

    });
});

