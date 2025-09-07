package com.car_rental.Car_Builder.controller;

import com.car_rental.Car_Builder.model.*;
import com.car_rental.Car_Builder.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired private CarRepository carRepo;


    // Browse cars
    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

}
