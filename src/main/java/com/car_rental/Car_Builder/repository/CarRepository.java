package com.car_rental.Car_Builder.repository;

import com.car_rental.Car_Builder.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long>  {
}
