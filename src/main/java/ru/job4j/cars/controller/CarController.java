package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.job4j.cars.service.car.CarService;

@AllArgsConstructor
@Controller
public class CarController {

    private final CarService carService;

}
