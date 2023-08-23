package ru.netology.task8ormhibernate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.task8ormhibernate.model.Persons;
import ru.netology.task8ormhibernate.service.PersonsService;

import java.util.List;

@RestController
public class PersonsController {

    private final PersonsService service;

    public PersonsController(PersonsService service) {
        this.service = service;
    }

    @GetMapping("/persons/by-city")
    public List<Persons> getPersonsByCity(@RequestParam String city) {
        return service.getPersonsByCity(city);
    }
}