package ru.netology.task8ormhibernate.service;

import org.springframework.stereotype.Service;
import ru.netology.task8ormhibernate.repository.IRepository;
import ru.netology.task8ormhibernate.model.Persons;

import java.util.List;

@Service
public class PersonsService {

    private final IRepository repository;

    public PersonsService(IRepository repository) {
        this.repository = repository;
    }

    public List<Persons> getPersonsByCity(String city) {
        return repository.getPersonsByCity(city);
    }
}