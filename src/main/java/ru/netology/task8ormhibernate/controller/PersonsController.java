package ru.netology.task8ormhibernate.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.task8ormhibernate.model.Person;
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
    public List<Person> getPersonsByCity(@PathVariable String city) {
        return service.getPersonsByCity(city);
    }

    @GetMapping("/less/{age}")
    public List<Person> getPersonsYoungerThan(@PathVariable int age) {
        return service.getPersonsYoungerThan(age);
    }

    @GetMapping("/anthroponym")
    public Person getPersonByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return service.getPersonByNameAndSurname(name, surname);
    }

    @GetMapping("/save")
    public Person save(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam int age,
            @RequestParam String phone,
            @RequestParam String city) {
        return service.save(new Person(name, surname, age, phone, city));
    }

    @PostMapping("/save")
    public List<Person> saveAll(@RequestBody List<Person> persons) {
        return service.saveAll(persons);
    }

    @GetMapping("/id")
    public Person getById(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam int age
    ) {
        return service.getById(new Person(name, surname, age));
    }

    @GetMapping("/exist")
    public boolean existById(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam int age
    ) {
        return service.existsById(new Person(name, surname, age));
    }

    @GetMapping("/all")
    public List<Person> getAll() {
        return service.getAll();
    }

    @PostMapping("/all")
    public List<Person> getAllById(@RequestBody List<Person> persons) {
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
        service.deleteById(new Person(name, surname, age));
    }

    @DeleteMapping("/")
    public void delete(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam int age,
            @RequestParam String phone,
            @RequestParam String city
    ) {
        service.delete(new Person(name, surname, age, phone, city));
    }

    @DeleteMapping("/all-id")
    public void deleteAllById(@RequestBody List<Person> persons) {
        service.deleteAllById(persons);
    }

    @DeleteMapping("/list")
    public void deleteAllPersons(@RequestBody List<Person> persons) {
        service.deleteAllPersons(persons);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        service.deleteAll();
    }
}