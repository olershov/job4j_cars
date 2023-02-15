package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.job4j.cars.service.user.UserService;

@AllArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

}
