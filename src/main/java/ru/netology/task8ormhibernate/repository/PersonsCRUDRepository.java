package ru.netology.task8ormhibernate.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.task8ormhibernate.repository.dbentities.Metrics;
import ru.netology.task8ormhibernate.repository.dbentities.Persons;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsCRUDRepository extends JpaRepository<Persons, Metrics> {

    List<Persons> findByCityOfLiving(String cityOfLiving, Sort sort);

    List<Persons> findByIdAgeLessThanOrderByIdAge(int age, Sort sort);

    Optional<Persons> findFirst1ByIdNameAndIdSurname(String name, String surname);
}
