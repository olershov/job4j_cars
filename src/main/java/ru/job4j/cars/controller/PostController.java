package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.job4j.cars.service.post.PostService;

@AllArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

}
