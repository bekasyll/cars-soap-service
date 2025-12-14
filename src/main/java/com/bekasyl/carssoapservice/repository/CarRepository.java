package com.bekasyl.carssoapservice.repository;

import com.bekasyl.carssoapservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrandAndModel(String brand, String model);
}
