package ru.netology.task8ormhibernate.repository;

import ru.netology.task8ormhibernate.model.Persons;

import java.util.List;

public interface IRepository {

    List<Persons> getPersonsByCity(String city);
}