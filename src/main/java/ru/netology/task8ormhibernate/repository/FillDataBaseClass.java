package ru.netology.task8ormhibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.netology.task8ormhibernate.repository.dbintities.Metrics;
import ru.netology.task8ormhibernate.repository.dbintities.Persons;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Component
public class FillDataBaseClass implements CommandLineRunner {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) {
        final var names = List.of("Misha", "Vasya", "Petya", "Harry", "Vitaliy", "Vadim");
        final var surnames = List.of("Oganesov", "Petrov", "Drokin", "Pankratov", "Zubkov", "Gibert");
        final var cities = List.of("Moscow", "Bryansk", "Vologda", "Voronezh", "Tula", "Tver");

        final var random = new Random();

        IntStream.range(0, 100).forEach(i -> {
            final var person = Persons.builder()
                    .id(Metrics.builder()
                            .name(names.get(random.nextInt(names.size())))
                            .surname(surnames.get(random.nextInt(surnames.size())))
                            .age(random.nextInt(1, 60))
                            .build())
                    .phoneNumber("xxxxxxxxxxx")
                    .cityOfLiving(cities.get(random.nextInt(cities.size())))
                    .build();
            entityManager.merge(person);
        });
    }
}