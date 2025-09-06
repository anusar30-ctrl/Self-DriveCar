package com.car_rental.Car_Builder.controller;

import com.car_rental.Car_Builder.model.Car;
import com.car_rental.Car_Builder.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/cars")

public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // GET all cars
    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    // POST add new car
    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }

    // DELETE car
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

}
