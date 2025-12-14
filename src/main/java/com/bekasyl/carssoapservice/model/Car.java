package com.bekasyl.carssoapservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String color;
    private String year;

    @Column(name = "countryoforigin")
    private String countryOfOrigin;
}
