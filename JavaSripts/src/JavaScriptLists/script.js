/*
 Создайте список чисел
 • Отсортируйте его по убыванию
 • Получите подсписок из первых 5 элементов и подсписок
 из последних 5 элементов
 • Найдите сумму элементов списка, которые являются
 четными числами
 • Создайте список чисел от 1 до 100, в таком порядке
 */

var list = [5, 23, 6, 1, -45, 0, 31, 7, 10, -6, 2, 12, 3];

function listToString(printingList) {
    var str = "{";
    str += printingList.join("; ");
    str += "}";
    return str;
}
console.log("Unsorted list: " + listToString(list));

list.sort(function (num1, num2) {
    return num2 - num1;
});
console.log("Sorted list: " + listToString(list));

var firstFiveElementsList = list.slice(0, 5);
console.log("First five elements: " + listToString(firstFiveElementsList));

var lastFiveElementsList = list.slice(list.length - 5);
console.log("Last five elements: " + listToString(lastFiveElementsList));

function sumEvenNums(fullList) {
    var result = 0;
    for (var i = 0; i < fullList.length; ++i) {
        if (fullList[i] % 2 === 0) {
            result += fullList[i];
        }
    }
    return result;
}

console.log("Sum even numbers of list: " + sumEvenNums(list));

var firstHundredList = [];
for (var i = 0; i < 100; ++i) {
    firstHundredList.push(i + 1);
}
console.log("First hundred list: " + listToString(firstHundredList));