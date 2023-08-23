package ru.netology.task8ormhibernate.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.netology.task8ormhibernate.repositories.dbentities.Metrics;
import ru.netology.task8ormhibernate.repositories.dbentities.Persons;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsCRUDRepository extends JpaRepository<Persons, Metrics> {

    @Query("select p from Persons p where p.cityOfLiving = ?1")
    List<Persons> getByCity(String city, Sort sort);

    @Query("select p from Persons p where p.id.age < :age")
    List<Persons> getLessAge(@Param("age") int age, Sort sort);

    @Query(value = "select * from persons where name = ?1 and surname = ?2 limit 1", nativeQuery = true)
    Optional<Persons> findFirst1ByIdNameAndIdSurname(String name, String surname);
}
