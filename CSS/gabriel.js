/**
 * Created by tim on 31.08.2016.
 */
$(document).ready(function () {
    var value1 = 0;
    $("#etslider1").slider({
        value: 200,
        min: 0,
        max: 1000,
        step: 1,
        create: function (event, ui) {
            value1 = $("#etslider1").slider("value");
            $("#etSliderVal1").val(ui.val);//Заполняем этим значением элемент с id
        },
        slide: function (event, ui) {
            $("#etSliderVal1").val(ui.value);//При изменении значения ползунка заполняем элемент с id contentSlider
        },
    });

    value1 = $("#etslider1").slider("value");
    $("#etSliderVal1").val(value1);

    var value2 = 0;
    $("#etslider2").slider({
        value: 650,
        min: 0,
        max: 1000,
        step: 1,
        create: function (event, ui) {
            value2 = $("#etslider2").slider("value");
            $("#etSliderVal2").val(ui.val);//Заполняем этим значением элемент с id
        },
        slide: function (event, ui) {
            $("#etSliderVal2").val(ui.value);//При изменении значения ползунка заполняем элемент с id contentSlider
        },
    });

    value2 = $("#etslider2").slider("value");
    $("#etSliderVal2").val(value2);

    var value3 = 0;
    $("#etslider3").slider({
        value: 650,
        min: 0,
        max: 1000,
        step: 1,
        create: function (event, ui) {
            value3 = $("#etslider3").slider("value");
            $("#etSliderVal3").val(ui.val);//Заполняем этим значением элемент с id
        },
        slide: function (event, ui) {
            $("#etSliderVal3").val(ui.value);//При изменении значения ползунка заполняем элемент с id contentSlider
        },
    });

    value3 = $("#etslider3").slider("value");
    $("#etSliderVal3").val(value3);

    var value4 = 0;
    $("#etslider4").slider({
        value: 150,
        min: 0,
        max: 1000,
        step: 1,
        create: function (event, ui) {
            value4 = $("#etslider4").slider("value");
            $("#etSliderVal4").val(ui.val);//Заполняем этим значением элемент с id
        },
        slide: function (event, ui) {
            $("#etSliderVal4").val(ui.value);//При изменении значения ползунка заполняем элемент с id contentSlider
        },
    });

    value4 = $("#etslider4").slider("value");
    $("#etSliderVal4").val(value4);


    var value5 = 0;
    $("#etslider5").slider({
        value: 350,
        min: 0,
        max: 1000,
        step: 1,
        create: function (event, ui) {
            value5 = $("#etslider5").slider("value");
            $("#etSliderVal5").val(ui.val);//Заполняем этим значением элемент с id
        },
        slide: function (event, ui) {
            $("#etSliderVal5").val(ui.value);//При изменении значения ползунка заполняем элемент с id contentSlider
        },
    });

    value5 = $("#etslider5").slider("value");
    $("#etSliderVal5").val(value5);

    var value6 = 0;
    $("#etslider6").slider({
        value: 200,
        min: 0,
        max: 1000,
        step: 1,
        create: function (event, ui) {
            value6 = $("#etslider6").slider("value");
            $("#etSliderVal6").val(ui.val);//Заполняем этим значением элемент с id
        },
        slide: function (event, ui) {
            $("#etSliderVal6").val(ui.value);//При изменении значения ползунка заполняем элемент с id contentSlider
        },
    });

    value6 = $("#etslider6").slider("value");
    $("#etSliderVal6").val(value6);

    var value7 = 0;
    $("#etslider7").slider({
        value: 50,
        min: 0,
        max: 1000,
        step: 1,
        create: function (event, ui) {
            value7 = $("#etslider7").slider("value");
            $("#etSliderVal7").val(ui.val);//Заполняем этим значением элемент с id
        },
        slide: function (event, ui) {
            $("#etSliderVal7").val(ui.value);//При изменении значения ползунка заполняем элемент с id contentSlider
        },
    });

    value7 = $("#etslider7").slider("value");
    $("#etSliderVal7").val(value7);

    var value8 = 0;
    $("#etslider8").slider({
        value: 950,
        min: 0,
        max: 1000,
        step: 1,
        create: function (event, ui) {
            value8 = $("#etslider8").slider("value");
            $("#etSliderVal8").val(ui.val);//Заполняем этим значением элемент с id
        },
        slide: function (event, ui) {
            $("#etSliderVal8").val(ui.value);//При изменении значения ползунка заполняем элемент с id contentSlider
        },
    });

    value8 = $("#etslider8").slider("value");
    $("#etSliderVal8").val(value8);


    var value9 = 0;
    $("#etslider9").slider({
        value: 500,
        min: 0,
        max: 1000,
        step: 1,
        create: function (event, ui) {
            value9 = $("#etslider9").slider("value");
            $("#etSliderVal9").val(ui.val);//Заполняем этим значением элемент с id
        },
        slide: function (event, ui) {
            $("#etSliderVal9").val(ui.value);//При изменении значения ползунка заполняем элемент с id contentSlider
        },
    });

    value9 = $("#etslider9").slider("value");
    $("#etSliderVal9").val(value9);

});

