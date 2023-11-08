package com.example.test.model;

import com.example.test.enums.TypeOfCar;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_name")
    private String car_name;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "volume")
    private float volume;

    @Column(name = "year")
    private Integer year;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeOfCar type;

}
