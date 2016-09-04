/**
 * Created by tim on 31.08.2016.
 */
$(document).ready(function () {
    $("html").niceScroll();
    $("#booking-table").niceScroll();
    $("#prev-orders-table").niceScroll();

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
            },
            slide: function (event, ui) {
                $(sliderValID).val(ui.value);//При изменении значения ползунка заполняем элемент с id contentSlider
                if (ui.value > 100) {
                    $(sliderID).slider("option", "step", 100);
                } else {
                    $(sliderID).slider("option", "step", 10);
                }
            },
        });

        ++index;
        value = $(sliderID).slider("value");
        $(sliderValID).val(value);

        $(sliderValID).change(function() {
            if ($(sliderValID).val() > $(sliderID).slider("option", "max")) {
                //alert("Max value " + $(sliderID).slider("option", "max"))
                $(sliderID).slider('value', $(sliderID).slider("option", "max"));
                $(sliderValID).val($(sliderID).slider("option", "max"));
            } else if ($(sliderValID).val() < $(sliderID).slider("option", "min")) {
                //alert("Min value " + $(sliderID).slider("option", "min"))
                $(sliderID).slider('value', $(sliderID).slider("option", "min"));
                $(sliderValID).val($(sliderID).slider("option", "min"));
            } else {
                $(sliderID).slider('value', $(sliderValID).val());
            }
        });
    });
});

