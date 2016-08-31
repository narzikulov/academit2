/**
 * Created by tim on 31.08.2016.
 */
$(document).ready(function () {
    var value = 0;

    $("#etslider").slider({
        value: 200,
        min: 0,
        max: 1000,
        step: 1,
        create: function (event, ui) {
            value = $("#etslider").slider("value");
            $("#etSliderVal").val(ui.val);//Заполняем этим значением элемент с id
        },
        slide: function (event, ui) {
            $("#etSliderVal").val(ui.value);//При изменении значения ползунка заполняем элемент с id contentSlider
        },
    });

    value = $("#etslider").slider("value");
    $("#etSliderVal").val(value);


});

