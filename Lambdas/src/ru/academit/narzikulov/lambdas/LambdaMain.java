package ru.academit.narzikulov.lambdas;

/*
Создать класс Person с полями имя и возраст. Сделать конструктор,
который принимает эти параметры. Сделать геттеры для полей
• В main создать список из нескольких людей
• При помощи лямбда-функций:
• А) получить список уникальных имен
• Б) вывести список уникальных имен в формате:
Имена: Иван, Сергей, Петр.
• В) получить список людей младше 18, посчитать для них средний
возраст
• Г) при помощи группировки получить Map, в котором ключи –
имена, а значения – средний возраст
• Д) получить людей, возраст которых от 20 до 45, вывести в консоль
их имена в порядке убывания возраста
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tim on 16.07.2016.
 */
public class LambdaMain {
    public static void main() {
        List<Person> list = new ArrayList<>();
        list.add(new Person ("Иван", 31));
        list.add(new Person ("Василий", 27));
        list.add(new Person ("Егор", 12));
        list.add(new Person ("Андрей", 17));
        list.add(new Person ("Максим", 41));
        list.add(new Person ("Егор", 65));
        list.add(new Person ("Андрей", 28));

        list.stream().map(s -> s.getName()).distinct().forEach(System.out::println);

        List<Person> list1 = list;
        System.out.println(list1.stream().map(s -> s.getName()).distinct().collect(Collectors.joining(", ")));

        System.out.println("Names are younger than 18");
        list.stream().filter(p -> p.getAge() < 18).map(p -> p.getName()).forEach(System.out::println);

        System.out.println("Average age for persns younger than 18");
        list.stream().filter(p -> p.getAge() < 18).mapToInt(p -> p.getAge()).average().ifPresent(System.out::println);

        list.stream().collect(Collectors.groupingBy(p -> p.getName(), Collectors.averagingInt(p -> p.getAge())))
            .forEach((name, age) -> System.out.println(name + ": " + age));

        list.stream().filter(p -> p.getAge() > 20 && p.getAge() < 45)
                .sorted((p, p1) -> {
                    return p1.getAge().compareTo(p.getAge());
                })
                .forEach(p -> System.out.println(p.getName() + " " + p.getAge()));
    }
}
