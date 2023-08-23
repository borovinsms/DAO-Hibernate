package ru.netology.task8ormhibernate.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(indexes = @Index(name = "city_index", columnList = "cityOfLiving"))
public class Persons {

    @EmbeddedId
    private Metrics id;

    @Column(nullable = false, length = 11)
    private String phoneNumber;

    @Column(nullable = false)
    private String cityOfLiving;

    private String name;

    private String surname;

    private int age;

    private String phone;

    private String city;

    @JsonCreator
    public Persons(String name, String surname, int age, String phone, String city) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.city = city;
    }

    public Persons(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = null;
        this.city = null;
    }
}