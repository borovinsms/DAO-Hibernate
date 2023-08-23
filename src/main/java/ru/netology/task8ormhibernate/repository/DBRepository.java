package ru.netology.task8ormhibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.netology.task8ormhibernate.model.Persons;

import java.util.List;

@Repository
public class DBRepository implements IRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Persons> getPersonsByCity(String city) {
        final var query
                = entityManager.createQuery(
                "select new ru.netology.task8ormhibernate.model.Persons(" +
                        "p.id.name, p.id.surname, p.id.age, p.phoneNumber, p.cityOfLiving) " +
                        "from Persons p " +
                        "where p.cityOfLiving = :city " +
                        "order by p.id.name, p.id.surname, p.id.age",
                Persons.class
        );
        query.setParameter("city", city);
        final var persons = query.getResultList();
        if (persons.isEmpty()) throw new EntityNotFoundException("City not found");
        return persons;
    }
}