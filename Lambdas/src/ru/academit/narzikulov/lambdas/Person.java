package ru.academit.narzikulov.lambdas;

/**
 * Created by tim on 16.07.2016.
 */
public class Person {
    private String name;
    private Integer age;

    public Person (String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
