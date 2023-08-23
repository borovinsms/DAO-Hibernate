package ru.netology.task8ormhibernate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.netology.task8ormhibernate.model.Persons;
import ru.netology.task8ormhibernate.model.Metrics;
import ru.netology.task8ormhibernate.exception.NotFoundException;
import ru.netology.task8ormhibernate.repository.PersonsCRUDRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonsService {

    private final Sort sort = Sort.by("id_surname", "id_name", "id_age");

    private final PersonsCRUDRepository repository;

    private Persons convertEntityToPerson(Persons p) {
        final var id = p.getId();
        return new Persons(id.getName(), id.getSurname(), id.getAge(), p.getPhoneNumber(), p.getCityOfLiving());
    }

    private Persons convertPersonToEntity(Persons person) {
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

    private Metrics convertPersonToId(Persons person) {
        return Metrics.builder()
                .name(person.getName())
                .surname(person.getSurname())
                .age(person.getAge())
                .build();
    }

    private List<Persons> convertEntitiesToPersonList(List<Persons> persons, String exceptionMessage) {
        if (persons.isEmpty()) throw new NotFoundException(exceptionMessage);
        return persons;
    }

    private List<Metrics> convertPersonsToIdList(List<Persons> persons) {
        return persons.stream().map(this::convertPersonToId).collect(Collectors.toList());
    }

    private List<Persons> convertPersonsToEntity(List<Persons> persons) {
        return persons.stream().map(i ->
                Persons.builder()
                        .id(convertPersonToId(i))
                        .phoneNumber(i.getPhone())
                        .cityOfLiving(i.getCity())
                        .build()
        ).collect(Collectors.toList());
    }

    public List<Persons> getPersonsByCity(String city) {
        return convertEntitiesToPersonList(
                repository.findByCityOfLiving(city, sort), "Persons from " + city + " not found");
    }

    public List<Persons> getPersonsYoungerThan(int age) {
        return convertEntitiesToPersonList(
                repository.findByIdAgeLessThanOrderByIdAge(age, sort),
                "Persons younger than " + age + " not found"
        );
    }

    public Persons getPersonByNameAndSurname(String name, String surname) {
        final var optional = repository.findFirst1ByIdNameAndIdSurname(name, surname);
        if (optional.isEmpty())
            throw new NotFoundException("The person with name " + name + "and surname " + surname + " not found");
        return convertEntityToPerson(optional.get());
    }

    public Persons save(Persons person) {
        return convertEntityToPerson(repository.save(convertPersonToEntity(person)));
    }

    public List<Persons> saveAll(List<Persons> persons) {
        return repository.saveAll(convertPersonsToEntity(persons)).stream()
                .map(this::convertEntityToPerson)
                .collect(Collectors.toList());
    }

    public Persons getById(Persons person) {
        return convertEntityToPerson(
                repository.findById(convertPersonToId(person))
                        .orElseThrow(() -> new NotFoundException("Persons not found")));
    }

    public boolean existsById(Persons person) {
        return repository.existsById(convertPersonToId(person));
    }

    public List<Persons> getAll() {
        return repository.findAll(sort).stream()
                .map(this::convertEntityToPerson)
                .collect(Collectors.toList());
    }

    public List<Persons> getAllById(List<Persons> persons) {
        return convertEntitiesToPersonList(
                repository.findAllById(convertPersonsToIdList(persons)), "No matches found");
    }

    public long getCount() {
        return repository.count();
    }

    public void deleteById(Persons person) {
        repository.deleteById(convertPersonToId(person));
    }

    public void delete(Persons person) {
        repository.delete(convertPersonToEntity(person));
    }

    public void deleteAllById(List<Persons> persons) {
        repository.deleteAllById(persons.stream().map(this::convertPersonToId).collect(Collectors.toList()));
    }

    public void deleteAllPersons(List<Persons> persons) {
        repository.deleteAll(persons.stream().map(this::convertPersonToEntity).collect(Collectors.toList()));
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}