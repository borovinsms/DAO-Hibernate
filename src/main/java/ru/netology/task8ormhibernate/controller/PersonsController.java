package ru.netology.task8ormhibernate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.task8ormhibernate.model.Persons;
import ru.netology.task8ormhibernate.service.PersonsService;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonsController {

    private final PersonsService service;

    public PersonsController(PersonsService service) {
        this.service = service;
    }
    
    @GetMapping("/{city}")
    public List<Persons> getPersonsByCity(@PathVariable String city) {
        return service.getPersonsByCity(city);
    }

    @GetMapping("/less/{age}")
    public List<Persons> getPersonsYoungerThan(@PathVariable int age) {
        return service.getPersonsYoungerThan(age);
    }

    @GetMapping("/anthroponym")
    public Persons getPersonByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return service.getPersonByNameAndSurname(name, surname);
    }

    @GetMapping("/save")
    public Persons save(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam int age,
            @RequestParam String phone,
            @RequestParam String city) {
        return service.save(new Persons(name, surname, age, phone, city));
    }

    @PostMapping("/save")
    public List<Persons> saveAll(@RequestBody List<Persons> persons) {
        return service.saveAll(persons);
    }

    @GetMapping("/id")
    public Persons getById(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam int age
    ) {
        return service.getById(new Persons(name, surname, age));
    }

    @GetMapping("/exist")
    public boolean existById(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam int age
    ) {
        return service.existsById(new Persons(name, surname, age));
    }

    @GetMapping("/all")
    public List<Persons> getAll() {
        return service.getAll();
    }

    @PostMapping("/all")
    public List<Persons> getAllById(@RequestBody List<Persons> persons) {
        return service.getAllById(persons);
    }

    @GetMapping("/count")
    public long count() {
        return service.getCount();
    }

    @DeleteMapping("/id")
    public void deleteById(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam int age
    ) {
        service.deleteById(new Persons(name, surname, age));
    }

    @DeleteMapping("/")
    public void delete(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam int age,
            @RequestParam String phone,
            @RequestParam String city
    ) {
        service.delete(new Persons(name, surname, age, phone, city));
    }

    @DeleteMapping("/all-id")
    public void deleteAllById(@RequestBody List<Persons> persons) {
        service.deleteAllById(persons);
    }

    @DeleteMapping("/list")
    public void deleteAllPersons(@RequestBody List<Persons> persons) {
        service.deleteAllPersons(persons);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        service.deleteAll();
    }
}