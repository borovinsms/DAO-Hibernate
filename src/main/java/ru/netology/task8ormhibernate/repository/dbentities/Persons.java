package ru.netology.task8ormhibernate.repository.dbentities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(indexes = @Index(name = "city_index", columnList = "cityOfLiving"))
public class Persons {

    @Id
    private Metrics id;

    @Column(nullable = false, length = 11)
    private String phoneNumber;

    @Column(nullable = false)
    private String cityOfLiving;
}