package ru.netology.task8ormhibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.netology.task8ormhibernate.exception.NotFoundPersonException;
import ru.netology.task8ormhibernate.model.Person;

import java.util.List;

@Repository
public class DBIRepository implements IRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> getPersonsByCity(String city) {
        final var query
                = entityManager.createQuery(
                "select new ru.netology.task8ormhibernate.model.Person(" +
                        "p.id.name, p.id.surname, p.id.age, p.phoneNumber, p.cityOfLiving) " +
                        "from Persons p " +
                        "where p.cityOfLiving = :city " +
                        "order by p.id.name, p.id.surname, p.id.age",
                Person.class
        );
        query.setParameter("city", city);
        final var persons = query.getResultList();
        if (persons.isEmpty()) throw new NotFoundPersonException("City not found");
        return persons;
    }
}
