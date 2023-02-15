package ru.job4j.cars.service.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.post.PostRepository;
import java.util.List;
import java.util.Optional;

/**
 * Класс - реализация сервиса объявлений
 * @see ru.job4j.cars.model.Post
 */
@AllArgsConstructor
@Service
public class HbmPostService implements PostService {

    private final PostRepository repository;

    @Override
    public Post create(Post post) {
        return repository.create(post);
    }

    @Override
    public Post update(Post post) {
        return repository.update(post);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<Post> findAllOrderById() {
        return repository.findAllOrderById();
    }

    @Override
    public Optional<Post> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Post> findCreatedLastDay() {
        return repository.findCreatedLastDay();
    }

    @Override
    public List<Post> findWithPhoto() {
        return repository.findWithPhoto();
    }

    @Override
    public List<Post> findByBrand(String key) {
        return repository.findByBrand(key);
    }
}
