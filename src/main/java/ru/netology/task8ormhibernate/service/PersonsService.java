package ru.netology.task8ormhibernate.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.netology.task8ormhibernate.exception.NotFoundException;
import ru.netology.task8ormhibernate.model.Person;
import ru.netology.task8ormhibernate.repository.PersonsCRUDRepository;
import ru.netology.task8ormhibernate.repository.dbentities.Metrics;
import ru.netology.task8ormhibernate.repository.dbentities.Persons;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonsService {

    private final Sort sort = Sort.by("id_surname", "id_name", "id_age");

    private final PersonsCRUDRepository repository;

    private Person convertEntityToPerson(Persons p) {
        final var id = p.getId();
        return new Person(id.getName(), id.getSurname(), id.getAge(), p.getPhoneNumber(), p.getCityOfLiving());
    }

    private Persons convertPersonToEntity(Person person) {
        return Persons.builder()
                .id(Metrics.builder()
                        .name(person.getName())
                        .surname(person.getSurname())
                        .age(person.getAge())
                        .build()
                )
                .phoneNumber(person.getPhone())
                .cityOfLiving(person.getCity())
                .build();
    }

    private Metrics convertPersonToId(Person person) {
        return Metrics.builder()
                .name(person.getName())
                .surname(person.getSurname())
                .age(person.getAge())
                .build();
    }

    private List<Person> convertEntitiesToPersonList(List<Persons> persons, String exceptionMessage) {
        final var personList = persons.stream().map(this::convertEntityToPerson).toList();
        if (persons.isEmpty()) throw new NotFoundException(exceptionMessage);
        return personList;
    }

    private List<Metrics> convertPersonsToIdList(List<Person> persons) {
        return persons.stream().map(this::convertPersonToId).toList();
    }

    private List<Persons> convertPersonsToEntity(List<Person> persons) {
        return persons.stream().map(i ->
                Persons.builder()
                        .id(convertPersonToId(i))
                        .phoneNumber(i.getPhone())
                        .cityOfLiving(i.getCity())
                        .build()
        ).toList();
    }

    public List<Person> getPersonsByCity(String city) {
        return convertEntitiesToPersonList(
                repository.findByCityOfLiving(city, sort), "Persons from " + city + " not found");
    }

    public List<Person> getPersonsYoungerThan(int age) {
        return convertEntitiesToPersonList(
                repository.findByIdAgeLessThanOrderByIdAge(age, sort),
                "Persons younger than " + age + " not found"
        );
    }

    public Person getPersonByNameAndSurname(String name, String surname) {
        final var optional = repository.findFirst1ByIdNameAndIdSurname(name, surname);
        if (optional.isEmpty())
            throw new NotFoundException("The person with name " + name + "and surname " + surname + " not found");
        return convertEntityToPerson(optional.get());
    }

    public Person save(Person person) {
        return convertEntityToPerson(repository.save(convertPersonToEntity(person)));
    }

    public List<Person> saveAll(List<Person> persons) {
        return repository.saveAll(convertPersonsToEntity(persons)).stream()
                .map(this::convertEntityToPerson)
                .toList();
    }

    public Person getById(Person person) {
        return convertEntityToPerson(
                repository.findById(convertPersonToId(person))
                        .orElseThrow(() -> new NotFoundException("Person not found")));
    }

    public boolean existsById(Person person) {
        return repository.existsById(convertPersonToId(person));
    }

    public List<Person> getAll() {
        return repository.findAll(sort).stream()
                .map(this::convertEntityToPerson)
                .toList();
    }

    public List<Person> getAllById(List<Person> persons) {
        return convertEntitiesToPersonList(
                repository.findAllById(convertPersonsToIdList(persons)), "No matches found");
    }

    public long getCount() {
        return repository.count();
    }

    public void deleteById(Person person) {
        repository.deleteById(convertPersonToId(person));
    }

    public void delete(Person person) {
        repository.delete(convertPersonToEntity(person));
    }

    public void deleteAllById(List<Person> persons) {
        repository.deleteAllById(persons.stream().map(this::convertPersonToId).toList());
    }

    public void deleteAllPersons(List<Person> persons) {
        repository.deleteAll(persons.stream().map(this::convertPersonToEntity).toList());
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}