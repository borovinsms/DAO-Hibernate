package ru.netology.task8ormhibernate.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class Person {

    private final String name;

    private final String surname;

    private final int age;

    private final String phone;

    private final String city;

    @JsonCreator
    public Person(String name, String surname, int age, String phone, String city) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.city = city;
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = null;
        this.city = null;
    }
}