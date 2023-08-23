package ru.netology.task8ormhibernate.repositories.dbentities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Metrics implements Serializable {

    private String name;

    private String surname;

    @Range(min = 1, max = 60)
    private int age;
}