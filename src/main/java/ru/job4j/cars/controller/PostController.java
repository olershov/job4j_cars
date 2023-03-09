package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.service.post.PostService;
import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

@RequestMapping("/post")
@AllArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String all(Model model) {
        model.addAttribute("posts", postService.findAllOrderById());
        return "post/all";
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model) {
        model.addAttribute("post", new Post());
        return "post/add";
    }

    @PostMapping("/createPost")
    public String createSession(@ModelAttribute Post post,
                                @RequestParam("file") MultipartFile file) throws IOException {
        post.setPhoto(file.getBytes());
        postService.create(post);
        return "redirect:/post/";
    }

    @GetMapping("/photoPost/{postId}")
    public ResponseEntity<Resource> download(@PathVariable("postId") Integer postId) {
        Post post = postService.findById(postId).get();
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(post.getPhoto().length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(post.getPhoto()));
    }
}
