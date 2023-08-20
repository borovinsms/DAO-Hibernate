package ru.netology.task8ormhibernate.repository;

import ru.netology.task8ormhibernate.model.Person;

import java.util.List;

public interface IRepository {

    List<Person> getPersonsByCity(String city);
}